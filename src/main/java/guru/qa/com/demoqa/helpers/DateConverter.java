package guru.qa.com.demoqa.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateConverter {

    public LocalDate stringToLocalDate(String date, String formatOfDate){
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(formatOfDate));
    }

    public String englishFormalFormat(LocalDate localDate){
        return localDate.format(DateTimeFormatter.ofPattern("dd MMMM,yyyy", Locale.ENGLISH));
    }
}
