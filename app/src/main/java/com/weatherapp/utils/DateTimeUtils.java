package com.weatherapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by SAYYAD on 08-12-2017.
 */

public class DateTimeUtils {
    public static String convertDateToSpecificFormat(String inputDate, String inputFormat, String outputFormat){
        String convertedDate =null;
        try {
            SimpleDateFormat inputDateFormat=new SimpleDateFormat(inputFormat, Locale.ENGLISH);
            SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFormat, Locale.ENGLISH);

            Date dateObj = inputDateFormat.parse(inputDate);
            convertedDate = outputDateFormat.format(dateObj);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertedDate;
    }
}
