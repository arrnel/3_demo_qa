package guru.qa.com.demoqa.templates;

import com.github.javafaker.Faker;
import guru.qa.com.demoqa.helpers.UserHelper;
import guru.qa.com.demoqa.models.registration.RegistrationActions;
import guru.qa.com.demoqa.objects.user.User;
import guru.qa.com.demoqa.objects.user.userObjects.*;
import lombok.Builder;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * Шаблоны пользователей
 */
public class UsersTemplates {
    User user;
    RegistrationActions registration = new RegistrationActions();

    /**
     * @return Пользователь с всеми заполнеными корректными и генерируемыми данными
     */
    public User userWithAllCorrectData() {
        Faker faker = new Faker(new Locale("ru"));

        String  firstname = faker.name().firstName(),
                lastName = faker.name().lastName(),
                email = new Faker(new Locale("en")).internet().emailAddress(),
                phoneNumber = faker.phoneNumber().subscriberNumber(10),
                dateOfBirth = new SimpleDateFormat("dd.MM.yyyy").format(faker.date().birthday()),
                image = "image.png",
                address = faker.address().fullAddress();

        Gender gender = Gender.values()[new Random().nextInt(Gender.values().length)];

        UserHelper userHelper = new UserHelper();
        List<Hobby> hobbies = userHelper.getRandomHobbies();
        List<Subject> subjects = userHelper.getRandomSubjects();

        State state = State.values()[new Random().nextInt(State.values().length)];

        List<City> cities = State.availableCitiesOfState(state);
        City city = cities.get(faker.random().nextInt(cities.size()));

        user = User.builder()
                .firstName(firstname)
                .lastName(lastName)
                .email(email)
                .gender(gender)
                .phoneNumber(phoneNumber)
                .dateOfBirth(dateOfBirth)
                .subjects(subjects)
                .hobbies(hobbies)
                .address(address)
                .picture(image)
                .state(state)
                .city(city)
                .build();

        return user;

    }
}
