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

    public SelenideElement firstName() {
        return $(byId("firstName"));
    }

    public SelenideElement lastName() {
        return $(byId("lastName"));
    }

    public By gender(Gender gender) {
        return byText(Gender.getGenderValue(gender));
    }

    public SelenideElement email() {
        return $(byId("userEmail"));
    }

    public SelenideElement phoneNumber() {
        return $(byId("userNumber"));
    }

    public SelenideElement dateOfBirth() {
        return $(byId("dateOfBirthInput"));
    }

    public SelenideElement yearOfBD() {
        return $(".react-datepicker__year-select");
    }

    public SelenideElement monthOfBD() {
        return $(".react-datepicker__month-select");
    }

    public SelenideElement calendar() {
        return $x("//div[@class='react-datepicker']");
    }

    public SelenideElement dayOfBirth(int dayOfBirth) {
        return $x(String.format("//div[text()='%s' and contains(@class,'react-datepicker__day') and not(contains(@class,'react-datepicker__day--outside-month'))]", dayOfBirth));
    }

    public SelenideElement subject() {
        return $("[id=subjectsInput]");
    }

    public SelenideElement subjectName(Subject subject) {
        return $x("//div[contains(@class,'subjects-auto-complete__option') and text()='" + Subject.getSubjectValue(subject) + "']");
    }

    public SelenideElement address() {
        return $x("//*[@id='currentAddress']");
    }

    public SelenideElement state() {
        return $x("//*[@id='state']");
    }

    public SelenideElement city() {
        return $x("//*[@id='city']");
    }

    public SelenideElement picture() {
        return $(byId("uploadPicture"));
    }
}
