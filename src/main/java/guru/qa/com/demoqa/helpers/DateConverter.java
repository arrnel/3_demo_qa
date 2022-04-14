package guru.qa.com.demoqa.helpers;

import guru.qa.com.demoqa.objects.user.User;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class DateConverter {

    /**
     * @param date дата (String)
     * @param formatOfDate формат даты (DateTimeFormatter)
     * @return Возвращает LocalDate из строки
     */
    @NotNull
    public LocalDate stringToLocalDate(String date, String formatOfDate){
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(formatOfDate));
    }

    /**
     * @param localDate - дата (LocalDate)
     * @return Возвращает дату в формате "dd MMMM,yyyy". Например, "04 April,2022"
     */
    @NotNull
    public String englishFormalFormat(LocalDate localDate){
        return localDate.format(DateTimeFormatter.ofPattern("dd MMMM,yyyy", Locale.ENGLISH));
    }

//

}
