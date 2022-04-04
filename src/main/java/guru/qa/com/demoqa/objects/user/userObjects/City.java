package guru.qa.com.demoqa.objects.user.userObjects;

public enum City {
    DELHI, GURGAON, NOIDA, AGRA, LUCKNOW, MERRUT, KARNAL, PANIPAT, JAIPUR, JAISELMER;

    public static String getCityValue(City city){
        switch (city) {
            case DELHI:
                return  "Delhi";
            case GURGAON:
                return  "Gurgaon";
            case NOIDA:
                return "Noida";
            case AGRA:
                return "Agra";
            case LUCKNOW:
                return "Lucknow";
            case MERRUT:
                return "Merrut";
            case KARNAL:
                return "Karnal";
            case PANIPAT:
                return "Panipat";
            case JAIPUR:
                return "Jaipur";
            case JAISELMER:
                return "Jaiselmer";
            default:
                return null;

        }
    }

}
