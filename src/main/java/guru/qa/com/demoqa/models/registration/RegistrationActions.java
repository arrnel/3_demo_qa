package guru.qa.com.demoqa.models.registration;

import guru.qa.com.demoqa.helpers.DateConverter;
import guru.qa.com.demoqa.helpers.UserHelper;
import guru.qa.com.demoqa.objects.user.User;
import guru.qa.com.demoqa.objects.user.userObjects.*;
import guru.qa.com.demoqa.setup.ElementAction;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;

/**
 * Дейстия на странице регистрации пользователя
 */
public class RegistrationActions {

    ElementAction elementAction = new ElementAction();
    final Logger log = LoggerFactory.getLogger(RegistrationActions.class);
    RegistrationLocators locator = new RegistrationLocators();

    /**
     * Заполнение поля "First Name"
     *
     * @param firstName имя пользователя
     */
    @NotNull
    public RegistrationActions fillFirstName(String firstName) {

        if (firstName != null && !firstName.equals("")) {

            elementAction.setValue(locator.firstName(), firstName);
            log.info("Заполнена фамилия: \"" + firstName + "\".");

        }

        return this;

    }

    /**
     * Заполнение поля "Last Name"
     *
     * @param lastName фамилия пользователя
     */
    @NotNull
    public RegistrationActions fillLastName(String lastName) {

        if (lastName != null && !lastName.equals("")) {

            elementAction.setValue(locator.lastName(), lastName);
            log.info("Заполнено имя: \"" + lastName + "\".");

        }

        return this;

    }

    /**
     * Выбор пола пользователя
     *
     * @param gender пол пользователя
     */
    @NotNull
    public RegistrationActions selectGender(Gender gender) {

        if (gender != null) {

            elementAction.click(locator.gender(gender));
            log.info("Заполнен пол: \"" + Gender.getGenderValue(gender) + "\".");

        }

        return this;

    }

    /**
     * Заполнение поля "Email"
     *
     * @param email почтовый ящик пользователя
     */
    @NotNull
    public RegistrationActions fillEmail(String email) {

        if (!email.equals("")) {

            elementAction.setValue(locator.email(), email);
            log.info("Заполнен email: \"" + email + "\".");

        }

        return this;

    }

    /**
     * Заполнение поля "Mobile(10 Digits)"
     *
     * @param phoneNumber номер телефона пользователя
     */
    @NotNull
    public RegistrationActions fillPhoneNumber(String phoneNumber) {

        if (!phoneNumber.equals("")) {

            elementAction.setValue(locator.phoneNumber(), phoneNumber);
            log.info("Заполнена номер телефона: \"" + phoneNumber + "\".");

        }

        return this;

    }

    /**
     * <h2>Выбор даты</h2>
     * Дата передаётся в формате "dd.MM.yyyy" в user.setDateOfBirth.<br/>
     * После этого происходит парсинг дня, месяца и года рождения.<br/>
     * А также формируется дата для проверки на форме после подтверждения
     *
     * @param day   день рождения
     * @param month месяц рождения
     * @param year  год рождения
     */
    @NotNull
    public RegistrationActions fillDate(int day, int month, int year) {

        if (day != 0 && month != 0 && year != 0) {

            RegistrationActions registration = new RegistrationActions();
            registration.openCalendar().selectMonth(month).selectYear(year).selectDayOfBirth(day).waitForCalendarNotVisible();

            log.info("Заполнена дата. День:\"" + day + "\", месяц:\"" + month + "\", год:\"" + year + "\".");
        }

        return this;

    }

    /**
     * Открытие календаря
     */
    RegistrationActions openCalendar() {

        elementAction.click(locator.dateOfBirth());
        locator.calendar().shouldBe(visible);

        return this;

    }

    /**
     * Выбор года в календаре
     *
     * @param year - год [-999999999 ; 999999999]
     */
    @NotNull
    RegistrationActions selectYear(int year) {

        if (year != 0) {
            locator.yearOfBD().shouldBe(visible).selectOptionByValue(String.valueOf(year));
            log.info("Выбран год: " + year);
        }

        return this;

    }

    /**
     * Выбор месяца в календаре
     *
     * @param month - месяц [1 ; 12]
     */
    @NotNull
    RegistrationActions selectMonth(int month) {

        if (month != 0) {
            locator.monthOfBD().shouldBe(visible).selectOption(month - 1);
            log.info("Выбран месяц: " + month);
        }

        return this;

    }

    /**
     * Выбор дня в рамках текущего месяца
     *
     * @param day - день [1 ; 31]
     */
    @NotNull
    RegistrationActions selectDayOfBirth(int day) {

        if (day != 0) {
            elementAction.click(locator.dayOfBirth(day));
            log.info("Выбран день");
        }

        return this;

    }

    /**
     * Ожидание отстутствия календаря на фронте
     */
    void waitForCalendarNotVisible() {
        $(byClassName("react-datepicker__month-container")).shouldNotBe(visible);
    }

    /**
     * Заполнение поля "Subject"
     *
     * @param subjects - Список с enum Subject
     */
    @NotNull
    public RegistrationActions fillSubjects(List<Subject> subjects) {

        if (subjects.size() != 0) {

            for (Subject subject : subjects) {
                elementAction.setValue(locator.subject(), Subject.getSubjectValue(subject).substring(0, 3));
                elementAction.click(locator.subjectName(subject));
            }

        }

        log.info("Заполнены предметы: \"" + subjects + "\".");

        return this;

    }

    /**
     * Выбор "Hobby"
     *
     * @param hobbies - Список с enum HOBBY
     */
    @NotNull
    public RegistrationActions selectHobbies(List<Hobby> hobbies) {

        if (hobbies.size() != 0) {

            String hobbyText;

            for (int i = 0; i <= hobbies.size() - 1; i++) {

                hobbyText = Hobby.getHobbyValue(hobbies.get(i));
                elementAction.click(locator.hobby(hobbyText));

            }

            log.info("Заполнены хобби: \"" + hobbies + "\"");

        }

        return this;

    }

    /**
     * Загрузка изображения в "Picture"
     *
     * @param fileName - Имя изображения с расширением в "/src/test/resources/..."
     */

    public RegistrationActions uploadPicture(String fileName) {

        if (!fileName.equals("")) {

            elementAction.uploadFile(locator.picture(), "src/test/resources/" + fileName);
            log.info("Загружена фотография: \"" + fileName + "\".");

        }

        return this;

    }


    /**
     * Заполнение "Address"
     *
     * @param address - адрес
     */
    @NotNull
    public RegistrationActions fillAddress(String address) {

        if (!address.equals("")) {
            elementAction.setValue(locator.address(), address);
            log.info("Заполнен адрес: \"" + address + "\".");
        }


        return this;

    }

    /**
     * Заполнение "State"
     *
     * @param state регион
     */
    @NotNull
    public RegistrationActions fillState(State state) {

        elementAction.fillDropDown(locator.state(), State.getStateValue(state));
        log.info("Выбран регион: \"" + State.getStateValue(state) + "\".");

        return this;

    }

    /**
     * Заполнение "City"
     *
     * @param city город
     */
    @NotNull
    public RegistrationActions fillCity(City city) {

        elementAction.fillDropDown(locator.city(), City.getCityValue(city));
        log.info("Выбран город: \"" + City.getCityValue(city) + "\".");


        return this;

    }

    /**
     * Подтверждение регистрации
     */
    public RegistrationActions submit() {

        elementAction.submit();
        return this;

    }

    /**
     * Проверка введеных данных и данных на форме
     *
     * @param user - Объект пользователя с регистрационными данными
     */
    @NotNull
    public void assertFormValues(User user) {

        DateConverter convertDate = new DateConverter();
        UserHelper userHelper = new UserHelper();

        log.info("Проверяем соответствие введеных значений с результатами на форме.");

        String expectedName = String.format("%s %s", user.getFirstName(), user.getLastName()),
                expectedEmail = user.getEmail(),
                expectedGender = Gender.getGenderValue(user.getGender()),
                expectedMobile = user.getPhoneNumber(),
                expectedDateOfBirth = convertDate.englishFormalFormat(convertDate.stringToLocalDate(user.getDateOfBirth(), "dd.MM.yyyy")),
                expectedSubjects = userHelper.getAllSubjectsInString(user.getSubjects()),
                expectedHobbies = userHelper.getAllHobbiesInString(user.getHobbies()),
                expectedPicture = user.getPicture(),
                expectedAddress = user.getAddress(),
                expectedStateAndCity = String.format("%s %s", State.getStateValue(user.getState()), City.getCityValue(user.getCity()));


        String actualName = elementAction.getText(locator.modalElementValue("Student Name")),
                actualEmail = elementAction.getText(locator.modalElementValue("Student Email")),
                actualGender = elementAction.getText(locator.modalElementValue("Gender")),
                actualPhoneNumber = elementAction.getText(locator.modalElementValue("Mobile")),
                actualBD = elementAction.getText(locator.modalElementValue("Date of Birth")),
                actualSubjects = elementAction.getText(locator.modalElementValue("Subjects")),
                actualHobbies = elementAction.getText(locator.modalElementValue("Hobbies")),
                actualPicture = elementAction.getText(locator.modalElementValue("Picture")),
                actualAddress = elementAction.getText(locator.modalElementValue("Address")),
                actualStateAndCity = elementAction.getText(locator.modalElementValue("State and City"));

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
}
