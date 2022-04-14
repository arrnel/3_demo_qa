package guru.qa.com.demoqa.helpers;

import com.github.javafaker.Faker;
import guru.qa.com.demoqa.objects.user.userObjects.Hobby;
import guru.qa.com.demoqa.objects.user.userObjects.Subject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public class UserHelper {

    @NotNull
    public String getAllSubjectsInString(List<Subject> subjects) {
        StringBuilder text = new StringBuilder();

        for (int i = 0; i < subjects.size(); i++){
            text.append(Subject.getSubjectValue(subjects.get(i)));
            if(i != subjects.size() - 1){
                text.append(", ");
            }
        }

        return text.toString();
    }

    @NotNull
    public String getAllHobbiesInString(List<Hobby> hobbies) {
        StringBuilder text = new StringBuilder();

        for (int i = 0; i < hobbies.size(); i++){
            text.append(Hobby.getHobbyValue(hobbies.get(i)));
            if(i != hobbies.size() - 1){
                text.append(", ");
            }
        }

        return text.toString();
    }

    /**
     * @return Возвращает рандомный список из списка enum Hobby
     */
    public List<Hobby> getRandomHobbies() {
        Faker faker = new Faker();
        List<Hobby> hobbies = new ArrayList<>(EnumSet.allOf(Hobby.class));
        Collections.shuffle(hobbies);
        hobbies = hobbies.subList(0, faker.random().nextInt(1, hobbies.size()));

        return hobbies;

    }

    /**
     * @return Возвращает рандомный список из списка enum Subject
     */
    public List<Subject> getRandomSubjects() {
        Faker faker = new Faker();
        List<Subject> subjects = new ArrayList<>(EnumSet.allOf(Subject.class));
        Collections.shuffle(subjects);
        subjects = subjects.subList(0, faker.random().nextInt(1, subjects.size()));

        return subjects;

    }
}
