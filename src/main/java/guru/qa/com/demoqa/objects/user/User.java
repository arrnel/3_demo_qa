package guru.qa.com.demoqa.objects.user;

import guru.qa.com.demoqa.helpers.DateConverter;
import guru.qa.com.demoqa.objects.user.userObjects.City;
import guru.qa.com.demoqa.objects.user.userObjects.Gender;
import guru.qa.com.demoqa.objects.user.userObjects.Hobby;
import guru.qa.com.demoqa.objects.user.userObjects.State;
import guru.qa.com.demoqa.objects.user.userObjects.Subject;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class User {

    String firstName;
    String lastName;
    String email;
    Gender gender;
    String phoneNumber;
    String dateOfBirth;
    int dayOfBirth;
    int monthOfBirth;
    int yearOfBirth;
    ArrayList<Subject> subjects;
    ArrayList<Hobby> hobbies;
    String picture;
    String address;
    State state;
    City city;
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        if (dateOfBirth==null || dateOfBirth.equals("")){

            Assertions.fail("Некорректный тип dateOfBirth: \"" + dateOfBirth + "\"");

        }else{
            try {
                DateConverter convertDate = new DateConverter();
                LocalDate localDate = convertDate.stringToLocalDate(dateOfBirth, "dd.MM.yyyy");

                setDayOfBirth(localDate.getDayOfMonth());
                setMonthOfBirth(localDate.getMonthValue());
                setYearOfBirth(localDate.getYear());


            }catch (DateTimeParseException ex){

                System.out.println(ex);
                Assertions.fail("Некорректный тип даты рождения: \"" + dateOfBirth + "\". Дата рождения должна быть в формате \"dd.MM.yyyy\", где \"dd\",\"MM\",\"yyyy\" - числа. ");

            }
        }

        this.dateOfBirth = dateOfBirth;

    }

    public int getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(int dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public int getMonthOfBirth() {
        return monthOfBirth;
    }

    public void setMonthOfBirth(int monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    public ArrayList<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(ArrayList<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAllSubjectsInString(ArrayList<Subject> subjects) {
        StringBuilder text = new StringBuilder();

        for (int i = 0; i < subjects.size(); i++){
            text.append(Subject.getSubjectValue(subjects.get(i)));
            if(subjects.size() < 2 || i != subjects.size() - 1){
                text.append(", ");
            }
        }

        return text.toString();
    }

    public String getAllHobbiesInString(ArrayList<Hobby> hobbies) {
        StringBuilder text = new StringBuilder();

        for (int i = 0; i < hobbies.size(); i++){
            text.append(Hobby.getHobbyValue(hobbies.get(i)));
            if(hobbies.size() < 2 || i != hobbies.size() - 1){
                text.append(", ");
            }
        }

        return text.toString();
    }
}
