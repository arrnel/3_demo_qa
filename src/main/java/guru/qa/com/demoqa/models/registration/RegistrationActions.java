package guru.qa.com.demoqa.models.registration;

import com.codeborne.selenide.ElementsCollection;
import guru.qa.com.demoqa.helpers.DateConverter;
import guru.qa.com.demoqa.objects.user.User;
import guru.qa.com.demoqa.objects.user.userObjects.*;
import guru.qa.com.demoqa.setup.ElementAction;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationActions {

    ElementAction elementAction = new ElementAction();
    final Logger log = LoggerFactory.getLogger(RegistrationActions.class);

    public RegistrationActions fillFirstName(String firstName) {

        if (firstName != null) {
            if (!firstName.equals("")) {
                elementAction.fillData($(byId("firstName")), firstName);
                log.info("Заполнена фамалия: \"" + firstName + "\".");
            }
        }

        return this;

    }

    public RegistrationActions fillLastName(String lastName) {

        if (lastName != null) {
            if (!lastName.equals("")) {
                elementAction.fillData($(byId("lastName")), lastName);
                log.info("Заполнено имя: \"" + lastName + "\".");
            }
        }

        return this;

    }

    public RegistrationActions selectGender(Gender gender) {

        if (gender != null) {
            $(byText(Gender.getGenderValue(gender))).click();
            log.info("Заполнен пол: \"" + Gender.getGenderValue(gender) + "\".");
        }

        return this;

    }

    public RegistrationActions fillEmail(String email) {

        if (email != null) {

            if (!email.equals("")) {
                elementAction.fillData($(byId("userEmail")), email);
                log.info("Заполнен email: \"" + email + "\".");
            }

        }

        return this;

    }

    public RegistrationActions fillPhoneNumber(String phoneNumber) {

        if (phoneNumber != null) {

            if (!phoneNumber.equals("")) {

                elementAction.fillData($(byId("userNumber")), phoneNumber);
                log.info("Заполнена номер телефона: \"" + phoneNumber + "\".");

            }

        }

        return this;

    }

    public RegistrationActions fillBDDate(int day, int month, int year) {

        if (day != 0 && month != 0 && year != 0) {

            RegistrationActions registration = new RegistrationActions();
            registration.openCalendar().selectMonth(month).selectYear(year).selectDayOfBirth(day).waitForCalendarNotVisible();

            log.info("Заполнена дата. День:\"" + day + "\", месяц:\"" + month + "\", год:\"" + year + "\".");
        }

        return this;

    }

    RegistrationActions openCalendar() {

        $(byId("dateOfBirthInput")).shouldBe(visible).click();
        $x("//div[@class='react-datepicker']").shouldBe(visible);

        return this;

    }

    RegistrationActions selectYear(int year) {

        $(".react-datepicker__year-select").shouldBe(visible).selectOptionByValue(String.valueOf(year));

        return this;

    }

    RegistrationActions selectMonth(int month) {

        $(".react-datepicker__month-select").shouldBe(visible).selectOption(month - 1);

        return this;

    }

    void waitForCalendarNotVisible() {
        $(byClassName("react-datepicker__month-container")).shouldNotBe(visible);
    }

    RegistrationActions selectDayOfBirth(int dayOfBirth) {

        String dayOfBirthLocator = "//div[@class='react-datepicker__month']/div[contains(@class,'react-datepicker__week')]/div[text()='" + dayOfBirth + "']";
        ElementsCollection similarDaysCounts = $$x(dayOfBirthLocator).filterBy(visible);
        if (dayOfBirth >= 22) {
            if (similarDaysCounts.size() > 1) {
                similarDaysCounts.get(1).click();
            } else {
                similarDaysCounts.get(0).click();
            }
        } else {
            similarDaysCounts.get(0).click();
        }
        log.info("Выбран день рождения");

        return this;

    }

    public RegistrationActions fillSubjects(List<Subject> subjects) {

        if (subjects.size() != 0) {
            for (Subject subject : subjects) {
                $("[id=subjectsInput]").shouldBe(visible).setValue(Subject.getSubjectValue(subject).substring(0, 3));
                $x("//div[contains(@class,'subjects-auto-complete__option') and text()='" + Subject.getSubjectValue(subject) + "']").shouldBe(visible).click();
            }
            log.info("Заполнены предметы: \"" + subjects + "\".");
        }

        return this;

    }

    public RegistrationActions selectHobbies(List<Hobby> hobbies) {

        if (hobbies.size() != 0) {
            for (int i = 0; i <= hobbies.size() - 1; i++) {
                $x("//*[@id='hobbiesWrapper']//div[./label[text()='" + Hobby.getHobbyValue(hobbies.get(i)) + "']]").click();
                $x("//*[contains(@class,'complete__menu') and not(contains(@class,'multi'))]").shouldNotBe(exist);
            }
            log.info("Заполнены хобби: \"" + hobbies + "\"");
        }

        return this;

    }

    public RegistrationActions uploadPicture(String fileName) {

        if (fileName != null) {
            if (!fileName.equals("")) {
                elementAction.uploadFile($(byId("uploadPicture")), "src/test/resources/" + fileName);
                log.info("Загружена фотография: \"" + fileName + "\".");
            }
        }

        return this;

    }

    public void assertFormValues(User user) {

        DateConverter convertDate = new DateConverter();

        String expectedName = String.format("%s %s", user.getFirstName(), user.getLastName()),
                expectedEmail = user.getEmail(),
                expectedGender = Gender.getGenderValue(user.getGender()),
                expectedMobile = user.getPhoneNumber(),
                expectedDateOfBirth = convertDate.englishFormalFormat(convertDate.stringToLocalDate(user.getDateOfBirth(), "dd.MM.yyyy")),
                expectedSubjects = user.getAllSubjectsInString(user.getSubjects()),
                expectedHobbies = user.getAllHobbiesInString(user.getHobbies()),
                expectedPicture = user.getPicture(),
                expectedAddress = user.getAddress(),
                expectedStateAndCity = String.format("%s %s", State.getStateValue(user.getState()), City.getCityValue(user.getCity()));

        log.info("Проверяем соответствие введеных значений с результатами на форме.");

        Assertions.assertTrue($x(assertionXpath("Student Name", expectedName)).isDisplayed());
        Assertions.assertTrue($x(assertionXpath("Student Email", expectedEmail)).isDisplayed());
        Assertions.assertTrue($x(assertionXpath("Gender", expectedGender)).isDisplayed());
        Assertions.assertTrue($x(assertionXpath("Mobile", expectedMobile)).isDisplayed());
        Assertions.assertTrue($x(assertionXpath("Date of Birth", expectedDateOfBirth)).isDisplayed());
        Assertions.assertTrue($x(assertionXpath("Subjects", expectedSubjects)).isDisplayed());
        Assertions.assertTrue($x(assertionXpath("Hobbies", expectedHobbies)).isDisplayed());
        Assertions.assertTrue($x(assertionXpath("Picture", expectedPicture)).isDisplayed());
        Assertions.assertTrue($x(assertionXpath("Address", expectedAddress)).isDisplayed());
        Assertions.assertTrue($x(assertionXpath("State and City", expectedStateAndCity)).isDisplayed());

        log.info("Ошибок нет. Форма заполнена корректно.");

    }

    String assertionXpath(String variable, String value) {

        String xpath = "//div[@class='modal-body']//td[1 and text()='" + variable + "']/../td[2 and text()='" + value + "']";
        log.info(xpath);

        return xpath;

    }

    public RegistrationActions fillAddress(String address) {

        if (address != null) {
            if (!address.equals("")) {
                elementAction.fillData($x("//*[@id='currentAddress']"), address);
                log.info("Заполнен адрес: \"" + address + "\".");
            }
        }

        return this;

    }

    public RegistrationActions fillState(State state) {

        if (state != null) {
            elementAction.fillDropDown($x("//*[@id='state']"), State.getStateValue(state));
            log.info("Выбран регион: \"" + State.getStateValue(state) + "\".");
        }

        return this;

    }

    public RegistrationActions fillCity(City city) {

        if (city != null) {
            elementAction.fillDropDown($x("//*[@id='city']"), City.getCityValue(city));
            log.info("Выбран город: \"" + City.getCityValue(city) + "\".");
        }

        return this;

    }

    public RegistrationActions submit() {

        $(byText("Submit")).shouldBe(visible).click();

        return this;

    }
}
