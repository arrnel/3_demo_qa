package guru.qa.com.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import guru.qa.com.demoqa.models.registration.Registration;
import guru.qa.com.demoqa.objects.user.User;
import guru.qa.com.demoqa.objects.user.userObjects.*;
import guru.qa.com.demoqa.setup.ElementAction;
import guru.qa.com.demoqa.setup.TestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.open;

class FormTests extends TestBase {

    final Logger log = LoggerFactory.getLogger(FormTests.class);
    ElementAction elementAction = new ElementAction();

    @BeforeEach
    void setupBeforeEachTests() {
        Configuration.baseUrl = "https://demoqa.com";
        open("/automation-practice-form");
    }

    User userData(User user) {

        ArrayList<Hobby> hobbies = new ArrayList<>();
        hobbies.add(Hobby.SPORTS);
        hobbies.add(Hobby.MUSIC);

        ArrayList<Subject> subjects = new ArrayList<>();
        subjects.add(Subject.COMPUTER_SCIENCE);
        subjects.add(Subject.MATHS);

        user.setFirstName("Ivan")
                .setLastName("Ivanov")
                .setEmail("my.mail@gmail.com")
                .setGender(Gender.MALE)
                .setPhoneNumber("9991112233")
                .setDateOfBirth("05.12.1970")
                .setSubjects(subjects)
                .setHobbies(hobbies)
                .setAddress("Some address information")
                .setPicture("image.png")
                .setState(State.RAJASTHAN)
                .setCity(City.JAISELMER);

        return user;
    }

    @Test
    void testCorrectName() {

        //Data
        User user = new User();
        user = userData(user);

        //Test
        log.info("Запуск теста");
        Registration registration = new Registration();

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
