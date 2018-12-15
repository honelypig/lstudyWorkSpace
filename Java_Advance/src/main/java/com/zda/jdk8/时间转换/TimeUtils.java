package com.zda.jdk8.时间转换;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//java.time.
//         Clock
//        Duration
//        Instant
//        LocalDate
//        LocalDateTime
//        LocalTime
//        MonthDay
//        OffsetDateTime
//        OffsetTime
//        Period
//        Year
//        YearMonth
//        ZonedDateTime
//        ZoneId
//        ZoneOffset
public class TimeUtils {
    public static Date getNow(String format) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        return sdf.parse(new Date().toString());
    }

}
