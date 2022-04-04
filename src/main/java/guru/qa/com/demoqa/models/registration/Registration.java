package guru.qa.com.demoqa.models.registration;

import com.codeborne.selenide.SelenideElement;
import guru.qa.com.demoqa.helpers.DateConverter;
import guru.qa.com.demoqa.objects.user.User;
import guru.qa.com.demoqa.objects.user.userObjects.*;
import guru.qa.com.demoqa.setup.ElementAction;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;


import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$$;

public class Registration {
    private final Logger log = LoggerFactory.getLogger(Registration.class);
    ElementAction elementAction = new ElementAction();

    public Registration fillFirstName(String firstName) {
        elementAction.fillData($(byId("firstName")), firstName);
        return this;
    }

    public Registration fillLastName(String lastName) {
        elementAction.fillData($(byId("lastName")), lastName);
        return this;
    }

    public Registration selectGender(Gender gender) {
        $(byText(Gender.getGenderValue(gender))).click();
        return this;
    }

    public Registration fillEmail(String email) {
        elementAction.fillData($(byId("userEmail")), email);
        return this;
    }

    public Registration fillPhoneNumber(String phoneNumber) {
        elementAction.fillData($(byId("userNumber")), phoneNumber);
        return this;
    }

    public Registration fillBDDate(int day, int month, int year) {
        $(byId("dateOfBirthInput")).shouldBe(visible).click();
        $x("//div[@class='react-datepicker']").shouldBe(visible);

        $(".react-datepicker__month-select").shouldBe(visible).selectOption(month - 1);
        $(".react-datepicker__year-select").shouldBe(visible).selectOptionByValue(String.valueOf(year));

        selectDayOfBirth(day);

        waitForCalendarNotVisible();

        return this;
    }

    void waitForCalendarNotVisible() {
        $(byClassName("react-datepicker__month-container")).shouldNotBe(visible);
    }

    void selectDayOfBirth(int dayOfBirth) {
        String dayOfBirthLocator = "//div[@class='react-datepicker__month']/div[contains(@class,'react-datepicker__week')]/div[text()='" + dayOfBirth + "']";
        if (dayOfBirth >= 22) {
            if ($$x(dayOfBirthLocator).size() == 2) {
                $$(dayOfBirthLocator).get(1).click();
            }
        } else {
            $x(dayOfBirthLocator).click();
        }
    }

    public Registration fillSubjects(ArrayList<Subject> subjects) {
        for (int i = 0; i < subjects.size(); i++) {
            $("[id=subjectsInput]").shouldBe(visible).setValue(Subject.getSubjectValue(subjects.get(i)).substring(0, 3));
            $x("//div[contains(@class,'subjects-auto-complete__option') and text()='" + Subject.getSubjectValue(subjects.get(i)) + "']").shouldBe(visible).click();
        }
        return this;
    }

    public Registration selectHobbies(ArrayList<Hobby> hobbies) {
        Hobby hobby;
        for (int i = 0; i <= hobbies.size() - 1; i++) {
//            $x("//*[@id='hobbiesWrapper']//div[./label[text()='" + Hobby.getHobbyValue(hobbies.get(i)) + "']]/input").setSelected(true);
//            $x("//*[@id='hobbiesWrapper']//div[./label[text()='" + Hobby.getHobbyValue(hobbies.get(i)) + "']]/label").click();
            $x("//*[@id='hobbiesWrapper']//div[./label[text()='" + Hobby.getHobbyValue(hobbies.get(i)) + "']]").click();
            $x("//*[contains(@class,'complete__menu') and not(contains(@class,'multi'))]").shouldNotBe(exist);
        }
        return this;
    }

    public Registration uploadPicture(String fileName) {
        uploadFile($(byId("uploadPicture")), "src/test/resources/" + fileName);
        return this;
    }

    public void uploadFile(SelenideElement element, String filePath){
        element.uploadFile(new File(filePath));
    }

    public Registration assertFormValues(User user) {
        DateConverter convertDate = new DateConverter();
        Assertions.assertTrue($x(assertionXpath("Student Name", user.getFirstName() + " " + user.getLastName())).isDisplayed());
        Assertions.assertTrue($x(assertionXpath("Student Email", user.getEmail())).isDisplayed());
        Assertions.assertTrue($x(assertionXpath("Gender", Gender.getGenderValue(user.getGender()))).isDisplayed());
        Assertions.assertTrue($x(assertionXpath("Mobile", user.getPhoneNumber())).isDisplayed());
        Assertions.assertTrue($x(assertionXpath("Date of Birth", convertDate.englishFormalFormat(convertDate.stringToLocalDate(user.getDateOfBirth(), "dd.MM.yyyy")))).isDisplayed());
        Assertions.assertTrue($x(assertionXpath("Subjects", user.getAllSubjectsInString(user.getSubjects()))).isDisplayed());
        Assertions.assertTrue($x(assertionXpath("Hobbies", user.getAllHobbiesInString(user.getHobbies()))).isDisplayed());
        Assertions.assertTrue($x(assertionXpath("Picture", user.getPicture())).isDisplayed());
        Assertions.assertTrue($x(assertionXpath("Address", user.getAddress())).isDisplayed());
        Assertions.assertTrue($x(assertionXpath("State and City", State.getStateValue(user.getState()) + " " + City.getCityValue(user.getCity()))).isDisplayed());
        return this;
    }

    String assertionXpath(String variable, String value) {
        String xpath = "//div[@class='modal-body']//td[1 and text()='" + variable + "']/../td[2 and text()='" + value + "']";
        System.out.println(xpath);
        return xpath;
    }

    public Registration fillAddress(String address) {
        elementAction.fillData($x("//*[@id='currentAddress']"), address);
        return this;
    }

    public Registration fillState(State state) {
        elementAction.fillDropDown($x("//*[@id='state']"), State.getStateValue(state));
        return this;
    }

    public Registration fillCity(City city) {
        elementAction.fillDropDown($x("//*[@id='city']"), City.getCityValue(city));
        return this;
    }

    public Registration submit() {
        $(byText("Submit")).shouldBe(visible).click();
        return this;
    }
}
