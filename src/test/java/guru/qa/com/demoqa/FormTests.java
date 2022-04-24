package guru.qa.com.demoqa;

import com.codeborne.selenide.Selenide;
import guru.qa.com.demoqa.models.registration.RegistrationActions;
import guru.qa.com.demoqa.objects.user.User;
import guru.qa.com.demoqa.setup.TestBase;
import guru.qa.com.demoqa.templates.UsersTemplates;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.open;

class FormTests extends TestBase {

    RegistrationActions registration;
    UsersTemplates users = new UsersTemplates();
    final Logger log = LoggerFactory.getLogger(FormTests.class);

    @BeforeEach
    void setupBeforeEachTests() {

        open("/automation-practice-form");
    }

    @Test
    void testCorrectName() {
        User user = User.builder().build();
        //Data
        user = users.userWithAllCorrectData(user);

        //Test
        log.info("Запуск теста");

        registration = new RegistrationActions();

        registration.
                fillFirstName(user.getFirstName()).
                fillLastName(user.getLastName()).
                selectGender(user.getGender()).
                fillEmail(user.getEmail()).
                fillPhoneNumber(user.getPhoneNumber()).
                fillDate(user.getDayOfBirth(), user.getMonthOfBirth(), user.getYearOfBirth()).
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