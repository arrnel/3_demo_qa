package guru.qa.com.demoqa;

import com.codeborne.selenide.Selenide;
import guru.qa.com.demoqa.allure.AllureModels;
import guru.qa.com.demoqa.models.registration.RegistrationActions;
import guru.qa.com.demoqa.objects.user.User;
import guru.qa.com.demoqa.setup.TestBase;
import guru.qa.com.demoqa.templates.UsersTemplates;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Dimension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Tag("demo_qa")
class FormTests extends TestBase {

    RegistrationActions registration;
    AllureModels allure = new AllureModels();
    UsersTemplates users = new UsersTemplates();
    final Logger log = LoggerFactory.getLogger(FormTests.class);

    @BeforeEach
    void setupBeforeEachTests() {
        open("/automation-practice-form");
        Selenide.zoom(0.75);
    }

    @Test
    @DisplayName("Проверка заполнения формы demoqa")
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
        allure.allAttachments("Форма подтверждения",
                "Скриншот формы подтверждения",
                "Видео теста",
                "Логи браузера");
        Selenide.closeWindow();
    }


}