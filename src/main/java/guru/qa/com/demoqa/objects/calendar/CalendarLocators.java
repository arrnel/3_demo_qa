package guru.qa.com.demoqa.objects.calendar;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CalendarLocators {


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
     * @return Возвращает поле ввода "Date of Birth"
     */
    public SelenideElement dateOfBirth() {
        return $(byId("dateOfBirthInput"));
    }

}
