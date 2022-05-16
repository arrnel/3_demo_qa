package guru.qa.com.demoqa.setup;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.com.demoqa.config.Credentials;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBase {

    final static Logger log = LoggerFactory.getLogger(TestBase.class);
    TestEnvironment testEnvironment = setTestEnvironment();
    Credentials credentials = ConfigFactory.create(Credentials.class);

    /**
     * Действия перед запусками всех тестов
     */
    @BeforeAll
    static void setupBeforeAllTests() {


        log.info("Запуск тестов");

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.holdBrowserOpen = false; // Браузер не будет закрываться по окончанию теста
        Configuration.baseUrl = "https://demoqa.com";

        TestEnvironment testEnv = new TestBase().testEnvironment;

        if (testEnv == TestEnvironment.REMOTE) {
            new TestBase().remoteWebDriver();
        } else if (testEnv == TestEnvironment.LOCAL) {
            new TestBase().localWebDriver();
        } else {
            throw new IllegalArgumentException("Некорректный тип окружения");
        }

    }

    /**
     * Действия после прогона всех тестов
     */
    @AfterAll
    static void setupAfterAllTests() {
        new TestBase().log.info("Прогон тестов завершён");
    }

    TestEnvironment setTestEnvironment() {

        String testEnvironmentProperty = System.getProperty("testEnvironment");

        if (testEnvironmentProperty.equals("local")) {
            testEnvironment = TestEnvironment.LOCAL;
        } else if (testEnvironmentProperty.equals("remote")) {
            testEnvironment = TestEnvironment.REMOTE;
        } else {
            log.warn("Текст testEnvironmentProperty ='" + testEnvironmentProperty + "'");
            throw new IllegalArgumentException("Некорректное значение 'testEnvironment' = '" + testEnvironmentProperty + "'. " +
                    "Для удаленного браузера = 'remote', для локального = 'local'");
        }

        return testEnvironment;
    }

    public TestEnvironment getTestEnvironment() {
        return testEnvironment;
    }

    void localWebDriver() {
        Configuration.browserSize = "2560x1440";
        Configuration.browser = "chrome";
        Configuration.browserVersion = "101.0";
    }


    void remoteWebDriver() {

        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browserVersion", "100.0");

        Configuration.remote = getRemoteURL();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;

    }

    String getRemoteURL() {

        String url = "";
        if (testEnvironment == TestEnvironment.REMOTE) {

            String hostRemote = System.getProperty("hostRemote", "localhost");

            if (hostRemote.equals("localhost")) {
                url =  "https://localhost:4444/wd/hub";
            } else if (hostRemote.equals("selenoid.autotests.cloud")) {
                url =  String.format("https://%s:%s@selenoid.autotests.cloud/wd/hub", credentials.login(), credentials.password());
            }
        }
        return url;

    }


}
