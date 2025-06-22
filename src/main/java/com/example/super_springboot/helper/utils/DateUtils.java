package com.example.super_springboot.helper.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateUtils {

    public static LocalDate toLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static Date toDate(LocalDate localDate) {
        return Date.from(
                localDate.atStartOfDay(ZoneId.systemDefault())
                        .toInstant());
    }

    public static int diffDay(LocalDate date1, LocalDate date2) {
        return (int) ChronoUnit.DAYS.between(date2, date1);
    }
}
