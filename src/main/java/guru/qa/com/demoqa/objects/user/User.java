package guru.qa.com.demoqa.objects.user;

import com.github.javafaker.Faker;
import guru.qa.com.demoqa.helpers.DateConverter;
import guru.qa.com.demoqa.objects.user.userObjects.City;
import guru.qa.com.demoqa.objects.user.userObjects.Gender;
import guru.qa.com.demoqa.objects.user.userObjects.Hobby;
import guru.qa.com.demoqa.objects.user.userObjects.State;
import guru.qa.com.demoqa.objects.user.userObjects.Subject;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

/**
 * Данные пользователя
 */

@Data
@Builder
public class User {

    /**
     * Имя пользователя
     */
    String firstName;

    /**
     * Фамилия пользователя
     */
    String lastName;

    /**
     * Почтовый ящик пользователя
     */
    String email;

    /**
     * Пол пользователя
     */
    Gender gender;

    /**
     * Номер телефона пользователя
     */
    String phoneNumber;

    /**
     * Дата рождения пользователя
     */
    String dateOfBirth;

    /**
     * День рождения пользователя
     */
    int dayOfBirth;

    /**
     * Месяц рождения пользователя
     */
    int monthOfBirth;

    /**
     * Год рождения пользователя
     */
    int yearOfBirth;

    /**
     * Список с предметами пользователя
     */
    List<Subject> subjects;

    /**
     * Список с хобби пользователя
     */
    List<Hobby> hobbies;

    /**
     * Аватар пользователя
     */
    String picture;

    /**
     * Адрес пользователя
     */
    String address;

    /**
     * Регион пользователя
     */
    State state;

    /**
     * Город пользователя
     */
    City city;

    public static class UserBuilder {
        public String dateOfBirth;

        public UserBuilder dateOfBirth(String dateOfBirth) {

            this.dateOfBirth = dateOfBirth;

            try {

                DateConverter convertDate = new DateConverter();
                LocalDate localDate = convertDate.stringToLocalDate(dateOfBirth, "dd.MM.yyyy");

                dayOfBirth = localDate.getDayOfMonth();
                monthOfBirth = localDate.getMonthValue();
                yearOfBirth = localDate.getYear();

            } catch (DateTimeParseException ex) {

                System.out.println(ex);
                Assertions.fail("Некорректный тип даты: \"" + dateOfBirth + "\". Дата должна быть в формате \"dd.MM.yyyy\", где \"dd\",\"MM\",\"yyyy\" - числа. ");

            }
            return this;
        }
    }

}
