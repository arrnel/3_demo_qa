package guru.qa.com.demoqa.objects.user.userObjects;

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
}
