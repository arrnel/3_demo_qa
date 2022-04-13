package guru.qa.com.demoqa.objects.user.userObjects;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public enum Hobby {
    SPORTS, READING, MUSIC;

    public static String getHobbyValue(Hobby hobby) {

        switch (hobby) {
            case SPORTS:
                return  "Sports";
            case READING:
                return  "Reading";
            case MUSIC:
                return "Music";
            default:
                return null;

        }
    }

}

