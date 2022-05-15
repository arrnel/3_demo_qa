package guru.qa.com.demoqa.models.registration;

import guru.qa.com.demoqa.allure.AllureModels;
import guru.qa.com.demoqa.allure.AttachmentType;
import guru.qa.com.demoqa.helpers.DateConverter;
import guru.qa.com.demoqa.helpers.UserHelper;
import guru.qa.com.demoqa.objects.calendar.Calendar;
import guru.qa.com.demoqa.objects.user.User;
import guru.qa.com.demoqa.objects.user.userObjects.*;
import guru.qa.com.demoqa.setup.ElementAction;
import io.qameta.allure.Allure;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Дейстия на странице регистрации пользователя
 */
public class RegistrationActions {

    final Logger log = LoggerFactory.getLogger(RegistrationActions.class);
    ElementAction elementAction = new ElementAction();
    RegistrationLocators locator = new RegistrationLocators();

    /**
     * Заполнение поля "First Name"
     *
     * @param firstName имя пользователя
     */
    @NotNull
    public RegistrationActions fillFirstName(String firstName) {

        if (firstName != null && !firstName.equals("")) {

            Allure.step("Заполнить 'First Name': " + firstName, () ->
                    elementAction.setValue(locator.firstName(), firstName)
            );

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

            Allure.step("Заполнить 'Last Name': " + lastName, () ->
                    elementAction.setValue(locator.lastName(), lastName)
            );
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

            String genderText = Gender.getGenderValue(gender);

            Allure.step("Заполнить 'Gender': " + genderText, () ->
                    elementAction.click(locator.gender(gender))
            );

            log.info("Заполнен пол: \"" + genderText + "\".");

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

            Allure.step("Заполнить 'Email': " + email, () ->
                    elementAction.setValue(locator.email(), email)
            );

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

            Allure.step("Заполнить 'Mobile': " + phoneNumber, () ->
                    elementAction.setValue(locator.phoneNumber(), phoneNumber)
            );

            log.info("Заполнена номер телефона: \"" + phoneNumber + "\".");

        }

        return this;

    }


    /**
     * <h2>Выбор даты рождения</h2>
     *
     * @param day   день рождения
     * @param month месяц рождения
     * @param year  год рождения
     */
    @NotNull
    public RegistrationActions fillDate(int day, int month, int year) {

        Allure.step(String.format("Заполнить 'Date of Birth': %s.%s.%s", day, month, year), () -> {
            Calendar calendar = new Calendar();
            calendar.fillDate(day, month, year);
        });

        return this;

    }


    /**
     * Заполнение поля "Subject"
     *
     * @param subjects - Список с enum Subject
     */
    @NotNull
    public RegistrationActions fillSubjects(List<Subject> subjects) {

        if (subjects.size() != 0) {

            Allure.step("Заполнить Subjects: " + subjects, () -> {

                for (Subject subject : subjects) {
                    elementAction.setValue(locator.subject(), Subject.getSubjectValue(subject).substring(0, 3));
                    elementAction.click(locator.subjectName(subject));
                }

            });

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


            Allure.step("Выбрать hobby(-es): " + hobbies, () -> {

                String hobbyText;
                for (int i = 0; i <= hobbies.size() - 1; i++) {

                    hobbyText = Hobby.getHobbyValue(hobbies.get(i));
                    elementAction.click(locator.hobby(hobbyText));

                }

            });

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

            Allure.step("Загрузить фотографию", () ->
                    elementAction.uploadFile(locator.picture(), "src/test/resources/" + fileName)
            );

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

            Allure.step("Заполнить адрес: " + address, () ->
                    elementAction.setValue(locator.address(), address)
            );

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

        String stateText = State.getStateValue(state);

        Allure.step("Выбрать State: " + stateText, () ->
                elementAction.fillDropDown(locator.state(), stateText)
        );

        log.info("Выбран регион: \"" + stateText + "\".");

        return this;

    }

    /**
     * Заполнение "City"
     *
     * @param city город
     */
    @NotNull
    public RegistrationActions fillCity(City city) {

        String cityText = City.getCityValue(city);

        Allure.step("Выбрать City: " + cityText, () ->
                elementAction.fillDropDown(locator.city(), cityText)
        );

        log.info("Выбран город: \"" + cityText + "\".");


        return this;

    }

    /**
     * Подтверждение регистрации
     */
    public RegistrationActions submit() {

        Allure.step("Подтверждение", () ->
                elementAction.submit()
        );

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

        Allure.step("Проверка данных формы: ", () -> {

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


            Assertions.assertAll("Проверка данных формы: ", () -> {
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
            });


        });

        log.info("Ошибок нет. Форма заполнена корректно.");

    }

}
