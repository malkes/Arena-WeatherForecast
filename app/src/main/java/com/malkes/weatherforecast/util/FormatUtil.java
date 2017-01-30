package com.malkes.weatherforecast.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Malkes on 29/01/17.
 */

public class FormatUtil {
    public static String PATTERN_DDMM = "dd/MM";

    public static String formatDate(long timestamp, String pattern){
        Date date = new Date(timestamp*1000L); //Converting UNIX Time to Timestamp in milliseconds;

        return new SimpleDateFormat(pattern, Locale.getDefault()).format(date);
    }

    public static String formatTemperature(double temp){
        return String.format(Locale.getDefault(),"%dºF", (int) temp);
    }
}
