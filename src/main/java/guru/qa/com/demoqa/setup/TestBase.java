package guru.qa.com.demoqa.setup;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBase {

    static TestStand testStand = TestStand.LOCAL;

    final Logger log = LoggerFactory.getLogger(TestBase.class);


    /**
     * Действия перед запусками всех тестов
     */
    @BeforeAll
    static void setupBeforeAllTests() {

        new TestBase().log.info("Запуск тестов");

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.holdBrowserOpen = false; // Браузер не будет закрываться по окончанию теста
        Configuration.baseUrl = "https://demoqa.com";


        if (testStand == TestStand.REMOTE){
            remoteWebDriver();
        }else if(testStand == TestStand.LOCAL){
            localWebDriver();
        }else{
            throw new IllegalArgumentException();
        }

    }

    static void remoteWebDriver(){

        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.browserVersion = "100.0";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;

    }

    static void localWebDriver(){

        Configuration.browserSize = "2560x1440";
        Configuration.browser = "chrome";
        Configuration.browserVersion = "101.0";

    }

    /**
     * Действия после прогона всех тестов
     */
    @AfterAll
    static void setupAfterAllTests() {
        new TestBase().log.info("Прогон тестов завершён");
    }

}
