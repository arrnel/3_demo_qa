package guru.qa.com.demoqa.setup;

import com.codeborne.selenide.SelenideElement;

import java.io.File;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ElementAction {

    public void click(SelenideElement element) {
        element.shouldBe(visible).click();
    }

    public void fillData(SelenideElement element, String value) {
        element.shouldBe(visible).setValue(value);
    }

    public void fillDropDown(SelenideElement element, String value) {
        element.shouldBe(visible).click();
        element.$(byTagName("input")).shouldBe(visible).setValue(value.substring(0, 2));
        element.$(byText(value)).click();
    }

    public void submit() {
        click($(byText("Submit")));
    }

    public void uploadFile(SelenideElement element, String filePath){
        element.uploadFile(new File(filePath));
    }

}