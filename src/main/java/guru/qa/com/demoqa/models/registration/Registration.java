package guru.qa.com.demoqa.models.registration;

import com.codeborne.selenide.ElementsCollection;
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

    ElementAction elementAction = new ElementAction();
    final Logger log = LoggerFactory.getLogger(Registration.class);

    public Registration fillFirstName(String firstName) {
        if (firstName!=null) {
            if (!firstName.equals("")) {
                elementAction.fillData($(byId("firstName")), firstName);
                log.info("Заполнена фамалия: \"" + firstName + "\".");
            }
        }
        return this;
    }

    public Registration fillLastName(String lastName) {
        if (lastName!=null) {
            if (!lastName.equals("")) {
                elementAction.fillData($(byId("lastName")), lastName);
                log.info("Заполнено имя: \"" + lastName + "\".");
            }
        }
        return this;
    }

    public Registration selectGender(Gender gender) {
        if (gender!=null) {
            $(byText(Gender.getGenderValue(gender))).click();
            log.info("Заполнен пол: \"" + Gender.getGenderValue(gender) + "\".");
        }
        return this;
    }

    public Registration fillEmail(String email) {
        if (email!=null) {
            if (!email.equals("")) {
                elementAction.fillData($(byId("userEmail")), email);
                log.info("Заполнен email: \"" + email + "\".");
            }
        }
        return this;
    }

    public Registration fillPhoneNumber(String phoneNumber) {
        if (phoneNumber!=null) {
            if (!phoneNumber.equals("")) {
                elementAction.fillData($(byId("userNumber")), phoneNumber);
                log.info("Заполнена номер телефона: \"" + phoneNumber + "\".");
            }
        }
        return this;
    }

    public Registration fillBDDate(int day, int month, int year) {
        if(day!=0 && month !=0 && year!=0){

            Registration registration = new Registration();
            registration.openCalendar()
                    .selectMonth(month)
                    .selectYear(year).
                    selectDayOfBirth(day)
                    .waitForCalendarNotVisible();

            log.info("Заполнена дата. День:\"" + day + "\", месяц:\"" + month + "\", год:\"" + year + "\".");
        }

        return this;
    }

    Registration openCalendar() {
        $(byId("dateOfBirthInput")).shouldBe(visible).click();
        $x("//div[@class='react-datepicker']").shouldBe(visible);
        return this;
    }

    Registration selectYear(int year) {
        $(".react-datepicker__year-select").shouldBe(visible).selectOptionByValue(String.valueOf(year));
        return this;
    }

    Registration selectMonth(int month) {
        $(".react-datepicker__month-select").shouldBe(visible).selectOption(month - 1);
        return this;
    }

    Registration waitForCalendarNotVisible() {
        $(byClassName("react-datepicker__month-container")).shouldNotBe(visible);
        return this;
    }

    Registration selectDayOfBirth(int dayOfBirth) {
        String dayOfBirthLocator = "//div[@class='react-datepicker__month']/div[contains(@class,'react-datepicker__week')]/div[text()='" + dayOfBirth + "']";
        ElementsCollection similarDaysCounts = $$x(dayOfBirthLocator).filterBy(visible);
        if (dayOfBirth >= 22) {
            if (similarDaysCounts.size() > 1) {
                similarDaysCounts.get(1).click();
            }else{
                similarDaysCounts.get(0).click();
            }
        } else {
            similarDaysCounts.get(0).click();
        }
        log.info("Выбран день рождения");
        return this;
    }

    public Registration fillSubjects(ArrayList<Subject> subjects) {
        if (subjects.size()!=0) {
            for (int i = 0; i < subjects.size(); i++) {
                $("[id=subjectsInput]").shouldBe(visible).setValue(Subject.getSubjectValue(subjects.get(i)).substring(0, 3));
                $x("//div[contains(@class,'subjects-auto-complete__option') and text()='" + Subject.getSubjectValue(subjects.get(i)) + "']").shouldBe(visible).click();
            }
            log.info("Заполнены предметы: \"" + subjects + "\".");
        }
        return this;
    }

    public Registration selectHobbies(ArrayList<Hobby> hobbies) {
        if (hobbies.size()!=0) {
            for (int i = 0; i <= hobbies.size() - 1; i++) {
                $x("//*[@id='hobbiesWrapper']//div[./label[text()='" + Hobby.getHobbyValue(hobbies.get(i)) + "']]").click();
                $x("//*[contains(@class,'complete__menu') and not(contains(@class,'multi'))]").shouldNotBe(exist);
            }
            log.info("Заполнены хобби: \"" + hobbies + "\"");
        }
        return this;
    }

    public Registration uploadPicture(String fileName) {
        if (fileName!=null) {
            if (!fileName.equals("")) {
                elementAction.uploadFile($(byId("uploadPicture")), "src/test/resources/" + fileName);
                log.info("Загружена фотография: \"" + fileName + "\".");
            }
        }
        return this;
    }



    public Registration assertFormValues(User user) {
        DateConverter convertDate = new DateConverter();

        log.info("Проверяем соответствие введеных значений с результатами на форме.");
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
        log.info("Ошибок нет. Форма заполнена корректно.");

        return this;
    }

    String assertionXpath(String variable, String value) {
        String xpath = "//div[@class='modal-body']//td[1 and text()='" + variable + "']/../td[2 and text()='" + value + "']";
        log.info(xpath);
        return xpath;
    }

    public Registration fillAddress(String address) {
        if (address!=null) {
            if (!address.equals("")) {
                elementAction.fillData($x("//*[@id='currentAddress']"), address);
                log.info("Заполнен адрес: \"" + address + "\".");
            }
        }
        return this;
    }

    public Registration fillState(State state) {
        if (state != null) {
            elementAction.fillDropDown($x("//*[@id='state']"), State.getStateValue(state));
            log.info("Выбран регион: \"" + State.getStateValue(state) + "\".");
        }
        return this;
    }

    public Registration fillCity(City city) {
        if(city != null) {
            elementAction.fillDropDown($x("//*[@id='city']"), City.getCityValue(city));
            log.info("Выбран город: \"" + City.getCityValue(city) + "\".");
        }
        return this;
    }

    public Registration submit() {
        $(byText("Submit")).shouldBe(visible).click();
        return this;
    }
}
