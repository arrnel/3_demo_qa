package guru.qa.com.demoqa.models.registration;

import com.codeborne.selenide.SelenideElement;
import guru.qa.com.demoqa.objects.user.userObjects.Gender;
import guru.qa.com.demoqa.objects.user.userObjects.Subject;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class RegistrationLocators {

    /**
     * @return Возвращает поле ввода "First Name"
     */
    public SelenideElement firstName() {
        return $(byId("firstName"));
    }

    /**
     * @return Возвращает поле ввода "Last Name"
     */
    public SelenideElement lastName() {
        return $(byId("lastName"));
    }

    /**
     * @param gender Пол
     * @return Возвращает родителя чекбокса пола
     */
    public By gender(Gender gender) {
        return byText(Gender.getGenderValue(gender));
    }

    /**
     * @return Возвращает поле ввода "Email"
     */
    public SelenideElement email() {
        return $(byId("userEmail"));
    }

    /**
     * @return Возвращает поле ввода "Номер телефона"
     */
    public SelenideElement phoneNumber() {
        return $(byId("userNumber"));
    }

    /**
     * @return Возвращает поле ввода "Date of Birth"
     */
    public SelenideElement dateOfBirth() {
        return $(byId("dateOfBirthInput"));
    }

    /**
     * @return Возвращает селектор "Year"
     */
    public SelenideElement yearOfBD() {
        return $(".react-datepicker__year-select");
    }

    /**
     * @return Возвращает селектор "Month"
     */
    public SelenideElement monthOfBD() {
        return $(".react-datepicker__month-select");
    }

    /**
     * @param dayOfBirth - день
     * @return Возвращает локатор дня текущего месяца в календаре
     */
    public SelenideElement dayOfBirth(int dayOfBirth) {
        return $x(String.format("//div[text()='%s' and contains(@class,'react-datepicker__day') and not(contains(@class,'react-datepicker__day--outside-month'))]", dayOfBirth));
    }

    /**
     * @return Возвращает календарь
     */
    public SelenideElement calendar() {
        return $x("//div[@class='react-datepicker']");
    }

    /**
     * @return Возвращает поле ввода "Subject"
     */
    public SelenideElement subject() {
        return $("[id=subjectsInput]");
    }

    /**
     * @param subject наименование предмета
     * @return Возвращает элемент предмета из выпадающего списка
     */
    public SelenideElement subjectName(Subject subject) {
        return $x("//div[contains(@class,'subjects-auto-complete__option') and text()='" + Subject.getSubjectValue(subject) + "']");
    }

    /**
     * @return Возвращает поле ввода "Address"
     */
    public SelenideElement address() {
        return $x("//*[@id='currentAddress']");
    }

    /**
     * @return Возвращает поле "Select State"
     */
    public SelenideElement state() {
        return $x("//*[@id='state']");
    }

    /**
     * @return Возвращает поле "Select City"
     */
    public SelenideElement city() {
        return $x("//*[@id='city']");
    }

    /**
     * @return Возвращает поле ввода для загрузки изображения "Picture"
     */
    public SelenideElement picture() {
        return $(byId("uploadPicture"));
    }

    /**
     * @param variable - Label в submission form
     * @return Возвращает элемент значения от label
     */
    public SelenideElement modalElementValue(String variable) {
        return $x("//div[@class='modal-body']//td[text()='" + variable + "']/following-sibling::td");
    }
}
