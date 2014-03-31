package com.utility.buckaroos;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

    /**
     * Converts integer time to strings
     * 
     * @param hour
     * @param minute
     * @return
     */
    public String convertTimeToString(Date date) {
        // DateFormat df = new SimpleDateFormat("HH:mm");
        DateFormat df = DateFormat.getTimeInstance();
        return df.format(date);
    }

    /**
     * Converts date into string
     * 
     * @param date
     * @return
     */
    public String convertDateToString(Date date) {
        // DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat df = DateFormat.getDateInstance();
        return df.format(date);
    }

    /**
     * @param dateString
     * @return
     */
    public Date convertStringToDate(String dateString) {
        Date newDate = null;
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            if (date != null) {
                newDate = date;
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return newDate;
        // DateFormat df = DateFormat.getDateInstance();
        // Date date = new Date();
        // try {
        // date = df.parse(dateString);
        // } catch (ParseException e) {
        // e.printStackTrace();
        // }
        // return date;
    }
}
