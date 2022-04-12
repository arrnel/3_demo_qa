package guru.qa.com.demoqa.objects.user.userObjects;

import java.util.List;
import java.util.Map;

public enum State {
    NCR, UTTARPRADESH, HARYANA, RAJASTHAN;

    public static String getStateValue(State state) {
        switch (state) {
            case NCR:
                return "NCR";
            case UTTARPRADESH:
                return "Uttar Pradesh";
            case HARYANA:
                return "Haryana";
            case RAJASTHAN:
                return "Rajasthan";
            default:
                return null;
        }
    }

    public static List<City> availableCitiesOfState(State state) {
        switch (state) {
            case NCR:
                return List.of(City.DELHI, City.GURGAON, City.NOIDA);
            case UTTARPRADESH:
                return List.of(City.AGRA, City.LUCKNOW, City.MERRUT);
            case HARYANA:
                return List.of(City.KARNAL, City.PANIPAT);
            case RAJASTHAN:
                return List.of(City.JAIPUR, City.JAISELMER);
            default:
                return null;
        }
    }
}
