package guru.qa.com.demoqa.models.registration;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import guru.qa.com.demoqa.helpers.DateConverter;
import guru.qa.com.demoqa.objects.user.User;
import guru.qa.com.demoqa.objects.user.userObjects.*;
import guru.qa.com.demoqa.setup.ElementAction;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationActions extends RegistrationLocators{

    Faker faker = new Faker();
    ElementAction elementAction = new ElementAction();
    final Logger log = LoggerFactory.getLogger(RegistrationActions.class);
    RegistrationLocators locator = new RegistrationLocators();

    public RegistrationActions fillFirstName(String firstName) {

        if (firstName != null) {
            if (!firstName.equals("")) {
                elementAction.fillData(locator.firstName(), firstName);
                log.info("Заполнена фамалия: \"" + firstName + "\".");
            }
        }

        return this;

    }

    public RegistrationActions fillLastName(String lastName) {

        if (lastName != null) {
            if (!lastName.equals("")) {
                elementAction.fillData(locator.lastName(), lastName);
                log.info("Заполнено имя: \"" + lastName + "\".");
            }
        }

        return this;

    }

    public RegistrationActions selectGender(Gender gender) {

        if (gender != null) {
            $(locator.gender(gender)).click();
            log.info("Заполнен пол: \"" + Gender.getGenderValue(gender) + "\".");
        }

        return this;

    }

    public RegistrationActions fillEmail(String email) {

        if (email != null) {

            if (!email.equals("")) {
                elementAction.fillData(locator.email(), email);
                log.info("Заполнен email: \"" + email + "\".");
            }

        }

        return this;

    }

    public RegistrationActions fillPhoneNumber(String phoneNumber) {

        if (phoneNumber != null) {

            if (!phoneNumber.equals("")) {

                elementAction.fillData(locator.phoneNumber(), phoneNumber);
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

        locator.dateOfBirth().shouldBe(visible).click();
        locator.calendar().shouldBe(visible);

        return this;

    }

    RegistrationActions selectYear(int year) {

        locator.yearOfBD().shouldBe(visible).selectOptionByValue(String.valueOf(year));

        return this;

    }

    RegistrationActions selectMonth(int month) {

        locator.monthOfBD().shouldBe(visible).selectOption(month - 1);

        return this;

    }

    void waitForCalendarNotVisible() {
        $(byClassName("react-datepicker__month-container")).shouldNotBe(visible);
    }

    RegistrationActions selectDayOfBirth(int dayOfBirth) {

        locator.dayOfBirth(dayOfBirth).shouldBe(visible).click();
        log.info("Выбран день рождения");

        return this;

    }

    public RegistrationActions fillSubjects(List<Subject> subjects) {

        if (subjects.size() != 0) {

            for (Subject subject : subjects) {
                locator.subject().shouldBe(visible).setValue(Subject.getSubjectValue(subject).substring(0, 3));
                locator.subjectName(subject).shouldBe(visible).click();
            }

        }

        log.info("Заполнены предметы: \"" + subjects + "\".");

        return this;

    }

    public RegistrationActions selectHobbies(List<Hobby> hobbies) {

        if (hobbies.size() != 0) {
            for (int i = 0; i <= hobbies.size() - 1; i++) {
                $x(String.format("//*[@id='hobbiesWrapper']//div[./label[text()='%s']]", Hobby.getHobbyValue(hobbies.get(i)))).click();
            }
            log.info("Заполнены хобби: \"" + hobbies + "\"");
        }

        return this;

    }

    public RegistrationActions uploadPicture(String fileName) {

        if (fileName != null) {
            if (!fileName.equals("")) {
                elementAction.uploadFile(locator.picture(), "src/test/resources/" + fileName);
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

        String actualName = modalElementValue("Student Name").shouldBe(visible).getText(),
                actualEmail = modalElementValue("Student Email").shouldBe(visible).getText(),
                actualGender = modalElementValue("Gender").shouldBe(visible).getText(),
                actualPhoneNumber = modalElementValue("Mobile").shouldBe(visible).getText(),
                actualBD = modalElementValue("Date of Birth").shouldBe(visible).getText(),
                actualSubjects = modalElementValue("Subjects").shouldBe(visible).getText(),
                actualHobbies = modalElementValue("Hobbies").shouldBe(visible).getText(),
                actualPicture = modalElementValue("Picture").shouldBe(visible).getText(),
                actualAddress = modalElementValue("Address").shouldBe(visible).getText(),
                actualStateAndCity = modalElementValue("State and City").shouldBe(visible).getText();

        Assertions.assertEquals(expectedName, actualName);
        Assertions.assertEquals(expectedEmail, actualEmail);
        Assertions.assertEquals(expectedGender, actualGender);
        Assertions.assertEquals(expectedMobile, actualPhoneNumber);
        Assertions.assertEquals(expectedDateOfBirth, actualBD);
        Assertions.assertEquals(expectedSubjects, actualSubjects);
        Assertions.assertEquals(expectedHobbies, actualHobbies);
        Assertions.assertEquals(expectedPicture, actualPicture);
        Assertions.assertEquals(expectedAddress, actualAddress);
        Assertions.assertEquals(expectedStateAndCity, actualStateAndCity);

        log.info("Ошибок нет. Форма заполнена корректно.");

    }

    SelenideElement modalElementValue(String variable) {

        String xpath = "//div[@class='modal-body']//td[text()='" + variable + "']/following-sibling::td";
        log.info(xpath);

        return $x(xpath);

    }

    public RegistrationActions fillAddress(String address) {

        if (address != null) {
            if (!address.equals("")) {
                elementAction.fillData(locator.address(), address);
                log.info("Заполнен адрес: \"" + address + "\".");
            }
        }

        return this;

    }

    public RegistrationActions fillState(State state) {

        if (state != null) {
            elementAction.fillDropDown(locator.state(), State.getStateValue(state));
            log.info("Выбран регион: \"" + State.getStateValue(state) + "\".");
        }

        return this;

    }

    public RegistrationActions fillCity(City city) {

        if (city != null) {
            elementAction.fillDropDown(locator.city(), City.getCityValue(city));
            log.info("Выбран город: \"" + City.getCityValue(city) + "\".");
        }

        return this;

    }

    public RegistrationActions submit() {

        $(byText("Submit")).shouldBe(visible).click();

        return this;

    }

    public List<Subject> getRandomSubjects() {

        List<Subject> subjects = new ArrayList<>(EnumSet.allOf(Subject.class));
        Collections.shuffle(subjects);
        subjects = subjects.subList(0, faker.random().nextInt(1, subjects.size()));

        return subjects;

    }

    public List<Hobby> getRandomHobbies() {

        List<Hobby> hobbies = new ArrayList<>(EnumSet.allOf(Hobby.class));
        Collections.shuffle(hobbies);
        hobbies = hobbies.subList(0, faker.random().nextInt(1, hobbies.size()));

        return hobbies;

    }
}
