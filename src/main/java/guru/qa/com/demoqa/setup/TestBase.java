package guru.qa.com.demoqa.setup;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    static void setupBeforeAllTests(){
        Configuration.holdBrowserOpen = true; // Браузер не будет закрываться по окончанию теста
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @AfterAll
    static void setupAfterAllTests(){
        Selenide.closeWebDriver();
    }

}
