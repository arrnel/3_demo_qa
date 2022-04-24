package guru.qa.com.demoqa.objects.calendar;

import guru.qa.com.demoqa.models.registration.RegistrationActions;
import guru.qa.com.demoqa.setup.ElementAction;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;

@Slf4j
public class Calendar {

    ElementAction elementAction = new ElementAction();
    final CalendarLocators locator = new CalendarLocators();

    /**
     * <h2>Выбор даты</h2>
     * Дата передаётся в формате "dd.MM.yyyy" в user.setDateOfBirth.<br/>
     * После этого происходит парсинг дня, месяца и года рождения.<br/>
     * А также формируется дата для проверки на форме после подтверждения
     *
     * @param day   день рождения
     * @param month месяц рождения
     * @param year  год рождения
     */
    @NotNull
    public Calendar fillDate(int day, int month, int year) {

        if (day != 0 && month != 0 && year != 0) {

            RegistrationActions registration = new RegistrationActions();
            openCalendar().selectMonth(month).selectYear(year).selectDayOfBirth(day).waitForCalendarNotVisible();

            log.info("Заполнена дата. День:\"" + day + "\", месяц:\"" + month + "\", год:\"" + year + "\".");
        }

        return this;

    }

    /**
     * Открытие календаря
     */
    Calendar openCalendar() {

        elementAction.click(locator.dateOfBirth());
        locator.calendar().shouldBe(visible);

        return this;

    }

    /**
     * Выбор года в календаре
     *
     * @param year - год [-999999999 ; 999999999]
     */
    @NotNull
    Calendar selectYear(int year) {

        if (year != 0) {
            locator.yearOfBD().shouldBe(visible).selectOptionByValue(String.valueOf(year));
            log.info("Выбран год: " + year);
        }

        return this;

    }

    /**
     * Выбор месяца в календаре
     *
     * @param month - месяц [1 ; 12]
     */
    @NotNull
    Calendar selectMonth(int month) {

        if (month != 0) {
            locator.monthOfBD().shouldBe(visible).selectOption(month - 1);
            log.info("Выбран месяц: " + month);
        }

        return this;

    }

    /**
     * Выбор дня в рамках текущего месяца
     *
     * @param day - день [1 ; 31]
     */
    @NotNull
    Calendar selectDayOfBirth(int day) {

        if (day != 0) {
            elementAction.click(locator.dayOfBirth(day));
            log.info("Выбран день");
        }

        return this;

    }

    /**
     * Ожидание отстутствия календаря на фронте
     */
    void waitForCalendarNotVisible() {
        $(byClassName("react-datepicker__month-container")).shouldNotBe(visible);
    }

}
