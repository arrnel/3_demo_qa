package guru.qa.com.demoqa.testCasesData;

import com.github.javafaker.Faker;
import guru.qa.com.demoqa.models.registration.RegistrationActions;
import guru.qa.com.demoqa.objects.user.User;
import guru.qa.com.demoqa.objects.user.userObjects.*;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * Шаблоны пользователей
 */
public class UsersTemplate {

    /**
     * @param user Объект пользователя
     * @return Пользователь с всеми заполнеными корректными и генерируемыми данными
     */
    public User userWithAllCorrectData(User user) {

        Faker faker = new Faker(new Locale("ru"));

        String  firstname = faker.name().firstName(),
                lastName = faker.name().lastName(),
                email = new Faker(new Locale("en")).internet().emailAddress(),
                phoneNumber = faker.phoneNumber().subscriberNumber(10),
                dateOfBirth = new SimpleDateFormat("dd.MM.yyyy").format(faker.date().birthday()),
                image = "image.png",
                address = faker.address().fullAddress();

        Gender gender = Gender.values()[new Random().nextInt(Gender.values().length)];

        RegistrationActions registration = new RegistrationActions();
        List<Hobby> hobbies = registration.getRandomHobbies();
        List<Subject> subjects = registration.getRandomSubjects();

        State state = State.values()[new Random().nextInt(State.values().length)];

        List<City> cities = State.availableCitiesOfState(state);
        City city = cities.get(faker.random().nextInt(cities.size()));

        user.setFirstName(firstname)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setPhoneNumber(phoneNumber)
                .setDateOfBirth(dateOfBirth)
                .setSubjects(subjects)
                .setHobbies(hobbies)
                .setAddress(address)
                .setPicture(image)
                .setState(state)
                .setCity(city);

        return user;

    }
}
