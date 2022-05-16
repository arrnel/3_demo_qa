package guru.qa.com.demoqa;

import com.codeborne.selenide.Selenide;
import guru.qa.com.demoqa.allure.AllureModels;
import guru.qa.com.demoqa.models.registration.RegistrationActions;
import guru.qa.com.demoqa.objects.user.User;
import guru.qa.com.demoqa.setup.TestBase;
import guru.qa.com.demoqa.templates.UsersTemplates;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.open;

@Tag("demo_qa")
class FormTests extends TestBase {

    RegistrationActions registration = new RegistrationActions();
    AllureModels allure = new AllureModels();
    UsersTemplates users = new UsersTemplates();
    final Logger log = LoggerFactory.getLogger(FormTests.class);

    @BeforeEach
    void setupBeforeEachTests() {
        open("/automation-practice-form");
        registration.removeBanners();
    }

    @Test
    @DisplayName("Проверка заполнения формы demoqa")
    void testCorrectName() {

        //Data
        User user = User.builder().build();
        user = users.userWithAllCorrectData(user);

        //Test
        log.info("Запуск теста");

        registration.fillUserInformation(user).submit();

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