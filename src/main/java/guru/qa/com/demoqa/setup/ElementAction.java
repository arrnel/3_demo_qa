package guru.qa.com.demoqa.setup;

import com.codeborne.selenide.SelenideElement;

import java.io.File;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ElementAction {

    /**
     * Клик по элементу</>
     */
    public void click(SelenideElement element) {
        element.shouldBe(visible).click();
    }

    public String getText(SelenideElement element) {
        return element.shouldBe(visible).getText();
    }

    /**
     * Установить значение поля (ввод текста в input, textarea)</>
     */
    public void setValue(SelenideElement element, String value) {
        element.shouldBe(visible).setValue(value);
    }

    /**
     * Ввести часть текста и выбрать элемент по тексту
     */
    public void fillDropDown(SelenideElement element, String value) {
        element.shouldBe(visible).click();
        element.$(byTagName("input")).shouldBe(visible).setValue(value.substring(0, 2));
        element.$(byText(value)).click();
    }

    /**
     * Подтверждение
     */
    public void submit() {
        click($(byText("Submit")));
    }

    /**
     * Загрузка файла
     * @param element элемент, в который будет загружен файл
     * @param filePath путь до файла
     */
    public void uploadFile(SelenideElement element, String filePath){
        element.uploadFile(new File(filePath));
    }

}