package guru.qa.com.demoqa.setup;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBase {
    final Logger log = LoggerFactory.getLogger(TestBase.class);
    /**
     * Действия перед запусками всех тестов
     */
    @BeforeAll
    static void setupBeforeAllTests() {

        new TestBase().log.info("Запуск тестов");


        Configuration.holdBrowserOpen = false; // Браузер не будет закрываться по окончанию теста
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    /**
     * Действия после прогона всех тестов
     */
    @AfterAll
    static void setupAfterAllTests() {
        new TestBase().log.info("Прогон тестов завершён");
    }

}
