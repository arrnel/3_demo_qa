package guru.qa.com.demoqa.setup;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    /**
     * Действия перед запусками всех тестов
     */
    @BeforeAll
    static void setupBeforeAllTests(){
        Configuration.holdBrowserOpen = false; // Браузер не будет закрываться по окончанию теста
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    /**
     * Действия после прогона всех тестов
     */
    @AfterAll
    static void setupAfterAllTests(){
        Selenide.closeWebDriver();
    }

}
