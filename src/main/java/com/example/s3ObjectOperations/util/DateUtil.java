package com.example.s3ObjectOperations.util;

import java.util.Date;

/**
 * @author Vivek Kumar Sinha
 */
public class DateUtil {

    public static Date getDeltaAddedDate(Date date, Integer deltaInMinutes) {
        long millis = date.getTime();
        millis += (deltaInMinutes * 1000 * 60);
        return new Date(millis);
    }
}
