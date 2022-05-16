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

    public static TestStand testStand = TestStand.REMOTE;

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

        if (testStand == TestStand.REMOTE) {
            new TestBase().remoteWebDriver();
        } else if (testStand == TestStand.LOCAL) {
            new TestBase().localWebDriver();
        } else {
            throw new IllegalArgumentException();
        }

    }

    void remoteWebDriver() {

        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browserVersion", "100.0");

        getRemoteURL();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", System.getProperty("vncStatus", "false"));
        capabilities.setCapability("enableVideo", System.getProperty("videoStatus", "false"));
        Configuration.browserCapabilities = capabilities;

    }

    void getRemoteURL() {
        String hostRemote = System.getProperty("hostRemote", "localhost");
        if (hostRemote.equals("localhost")) {
            Configuration.remote = "https://localhost:4444/wd/hub";
        } else if (hostRemote.equals("jenkins.autotests.cloud")) {
            Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        }
    }

    void localWebDriver() {

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
