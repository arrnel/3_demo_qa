package guru.qa.com.demoqa.objects.user.userObjects;

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

