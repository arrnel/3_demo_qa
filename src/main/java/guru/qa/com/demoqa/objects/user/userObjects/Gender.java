package guru.qa.com.demoqa.objects.user.userObjects;

public enum Gender {
    MALE, FEMALE, OTHER;

    public static String getGenderValue(Gender gender) {
        String genderValue;
        if (gender == Gender.MALE) {
            genderValue = "Male";
        } else if (gender == Gender.FEMALE) {
            genderValue = "Female";
        } else {
            genderValue = "Other";
        }

        return genderValue;
    }

}
