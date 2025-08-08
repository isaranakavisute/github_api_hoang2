package com.example.super_springboot.helper.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateUtils {

    public static int diffDay(LocalDate date1, LocalDate date2) {
        return (int) ChronoUnit.DAYS.between(date2, date1);
    }
}
