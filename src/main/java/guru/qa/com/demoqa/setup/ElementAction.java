package guru.qa.com.demoqa.setup;

import com.codeborne.selenide.SelenideElement;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static java.lang.System.in;

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