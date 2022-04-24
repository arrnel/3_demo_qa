package guru.qa.com.demoqa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import guru.qa.com.demoqa.setup.ElementAction;
import guru.qa.com.demoqa.setup.TestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selenide.*;

public class ParameterizedTests extends TestBase {

    ElementAction elementAction = new ElementAction();

    @BeforeEach
    void setup() {
        open("https://market.yandex.ru/");
    }

//    @ParameterizedTest(name = "Тест поиска товара по наименованию \"{0}\", стоимости \"{1}\" и года \"{2}\"")
//    @CsvSource
//    void test001(String itemName, String assertionHeaderText, String assertionItemText, BigDecimal valueMin, BigDecimal  valueMax, MemorySize memorySize) {
//        $("[id=logoPartMarket]").shouldBe(visible);
//        elementAction.setValue($("[id=header-search]"), itemName);
//        elementAction.click($x("//button[@type='submit']"));
//        $(byTagName("h1")).shouldBe(visible).shouldHave(text(assertionHeaderText));
//
//        elementAction.setValue($(byName("Цена от")), String.valueOf(valueMin));
//        elementAction.setValue($(byName("Цена до")), String.valueOf(valueMax));
//
//
//
//        ElementsCollection itemTitlesOnPage = $$(byTagName("h3"));
//        for (SelenideElement selenideElement : itemTitlesOnPage) {
//            selenideElement.shouldHave(text(assertionItemText));
//        }
//
//
//    }

    @AfterEach
    void tearDown() {
        Selenide.closeWindow();
    }


    enum MemorySize{
        MEM_2, MEM_4, MEM_8, MEM_16, MEM_32, MEM_64, MEM_128, MEM_256, MEM_512, MEM_1024;
    }

    String memorySizeText(MemorySize memorySize){

        switch (memorySize){
            case MEM_2:
                return "2 ГБ";
            case MEM_4:
                return "4 ГБ";
            case MEM_8:
                return "8 ГБ";
            case MEM_16:
                return "16 ГБ";
            case MEM_32:
                return "32 ГБ";
            case MEM_64:
                return "64 ГБ";
            case MEM_128:
                return "128 ГБ";
            case MEM_256:
                return "256 ГБ";
            case MEM_512:
                return "512 ГБ";
            case MEM_1024:
                return "1 ТБ";
            default:
                return "";
        }

    }

    @Test
    void test001(){
        $("[id=logoPartMarket]").shouldBe(visible);
    }

}

