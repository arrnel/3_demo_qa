package guru.qa.ne;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import guru.qa.ne.setup.TestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormTests extends TestBase{
    
    @AfterEach
    void setup(){

    }

    @AfterEach

    @Test
    void testCorrectName() {

        String name = "Dmitriy";
        open("https://demoqa.com/automation-practice-form");

        $(byId("firstName")).shouldBe(visible).click();
        $(byId("firstName")).setValue("Dmitriy");

        assertEquals(name,$(byId("firstName")).getValue());
    }
}
