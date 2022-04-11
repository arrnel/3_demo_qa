package guru.qa.com.demoqa;

import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import guru.qa.com.demoqa.models.registration.RegistrationActions;
import guru.qa.com.demoqa.objects.user.User;
import guru.qa.com.demoqa.objects.user.userObjects.*;
import guru.qa.com.demoqa.setup.TestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.codeborne.selenide.Selenide.open;

class FormTests extends TestBase {

    final Logger log = LoggerFactory.getLogger(FormTests.class);
    Faker faker = new Faker(new Locale("ru"));

    @BeforeEach
    void setupBeforeEachTests() {
        open("/automation-practice-form");
    }

    User userData(User user) {

        String  firstname = faker.name().firstName(),
                lastName = faker.name().lastName(),
                email = new Faker(new Locale("en")).internet().emailAddress(),
                phoneNumber = faker.phoneNumber().subscriberNumber(10),
                dateOfBirth = new SimpleDateFormat("dd.MM.yyyy").format(faker.date().birthday()),
                image = "image.png",
                address = faker.address().fullAddress();

        Gender gender = Gender.values()[new Random().nextInt(Gender.values().length)];

        List<Hobby> hobbies = getRandomHobbies();
        List<Subject> subjects = getRandomSubjects();

        State state = State.values()[new Random().nextInt(State.values().length)];

        List<City> cities = State.availableCitiesOfState(state);
        City city = cities.get(faker.random().nextInt(cities.size()));

        user.setFirstName(firstname)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setPhoneNumber(phoneNumber)
                .setDateOfBirth(dateOfBirth)
                .setSubjects(subjects)
                .setHobbies(hobbies)
                .setAddress(address)
                .setPicture(image)
                .setState(state)
                .setCity(city);

        return user;

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

    @Test
    void testCorrectName() {

        //Data
        User user = new User();
        user = userData(user);

        //Test
        log.info("Запуск теста");

        RegistrationActions registration = new RegistrationActions();

        registration.
                fillFirstName(user.getFirstName()).
                fillLastName(user.getLastName()).
                selectGender(user.getGender()).
                fillEmail(user.getEmail()).
                fillPhoneNumber(user.getPhoneNumber()).
                fillBDDate(user.getDayOfBirth(), user.getMonthOfBirth(), user.getYearOfBirth()).
                fillSubjects(user.getSubjects()).
                selectHobbies(user.getHobbies()).
                uploadPicture(user.getPicture()).
                fillAddress(user.getAddress()).
                fillState(user.getState()).
                fillCity(user.getCity()).
                submit();

        //Assertions
        registration.assertFormValues(user);

        log.info("Конец теста");
    }

    @AfterEach
    void setupAfterEachTests() {
        Selenide.closeWindow();
    }


}