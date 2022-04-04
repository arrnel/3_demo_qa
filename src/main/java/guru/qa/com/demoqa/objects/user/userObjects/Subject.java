package guru.qa.com.demoqa.objects.user.userObjects;

public enum Subject {

    HINDI, ENGLISH, MATHS, PHYSICS, CHEMISTRY, BIOLOGY, COMPUTER_SCIENCE, COMMERCE, ACCOUNTING, ECONOMICS, ARTS, SOCIAL_STUDIES, HISTORY, CIVICS;

    public static String getSubjectValue(Subject subject){
        switch (subject){
            case HINDI:
                return "Hindi";
            case ENGLISH:
                return "English";
            case MATHS:
                return "Maths";
            case PHYSICS:
                return "Physics";
            case CHEMISTRY:
                return "Chemistry";
            case BIOLOGY:
                return "Biology";
            case COMPUTER_SCIENCE:
                return "Computer Science";
            case COMMERCE:
                return "Commerce";
            case ACCOUNTING:
                return "Accounting";
            case ECONOMICS:
                return "Economics";
            case ARTS:
                return "Arts";
            case SOCIAL_STUDIES:
                return "Social Studies";
            case HISTORY:
                return "History";
            case CIVICS:
                return "Civics";
        }

        return null;

    }
}
