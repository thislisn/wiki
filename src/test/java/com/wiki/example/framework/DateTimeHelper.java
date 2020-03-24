package com.wiki.example.framework;

import org.joda.time.LocalDateTime;
import org.joda.time.YearMonth;

import java.util.Locale;

public class DateTimeHelper {
    private static final int ANY_YEAR = 2020;

    public static String getFullMonthName(int monthOfYear) {
        YearMonth md = new YearMonth(ANY_YEAR, monthOfYear);
        return md.monthOfYear().getAsText(new Locale("en"));
    }

    public static LocalDateTime getToday(){
        return org.joda.time.LocalDateTime.now();
    }

    public static LocalDateTime getTomorrow(){
        return getToday().plusDays(1);
    }
}
