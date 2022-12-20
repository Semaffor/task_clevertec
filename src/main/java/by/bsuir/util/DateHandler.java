package by.bsuir.util;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class DateHandler {

    //Better to set locale from application.properties
    private final Locale locale;

    public DateHandler(Locale locale) {
        this.locale = locale;
    }

    public static DateHandler createDefault() {
        return new DateHandler(new Locale("ru", "RU"));
    }

    public String getSimpleDate(Date date) {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, locale);
        return dateFormat.format(date);
    }

    public String getTime(Date date) {
        DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.LONG, locale);
        return dateFormat.format(date);
    }

    public String getFullTime(Date date) {
        String time = getTime(date);
        String validTimeForFileName = time.replace(' ', '_').replace(':', '-');
        return getSimpleDate(date).replace('.', '-') + validTimeForFileName;
    }
}
