package com.codingTest.codingTest.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTimeUtil {
    public static String DATE_FORMAT = "yyyy-MM-dd";

    public static final String convertDateToStringCustomized(Date date, String pattern) {
        try {
            if (date != null) {
                String dateEng = new SimpleDateFormat(pattern).format(date).toUpperCase();
                return dateEng;
            } else {
                return null;
            }
        } catch (Exception e) {
            return "";
        }
    }

    public static final Date convertStringToDateCustomized(String date, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        Date result = null;

        if (date != null) {
            try {
                result = df.parse(date);
            } catch (ParseException e) {
                result = null;
            }
            return result;
        } else {
            return null;
        }
    }

    public static long differenceDate(String startDate, String endDate) throws ParseException{
        long result;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date StartDate = df.parse(startDate);
        Date EndDate =df.parse(endDate);

        long data =Math.abs(EndDate.getTime()-StartDate.getTime());
        result = TimeUnit.MILLISECONDS.toDays(data);
        return result;
    }
}
