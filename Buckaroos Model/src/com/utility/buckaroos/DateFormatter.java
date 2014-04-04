package com.utility.buckaroos;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class defines a date formatter object.
 * 
 * @author Daniel Carnauba
 * @version 1.0
 *
 */
public class DateFormatter {

    /**
     * Converts an integer time to a string representation.
     *
     * @param date The date from which the time is being derived from.
     * @return The formatted time string representation.
     */
    public String convertTimeToString(Date date) {
        String formattedTime = null;
        if (date != null) {
            DateFormat df = new SimpleDateFormat("hh:mm:ss");
            formattedTime = df.format(date);
        }
        return formattedTime;
    }

    /**
     * Converts a Date object into a string.
     *
     * @param date The date object to be converted to a string.
     * @return The string conversion of the date object.
     */
    public String convertDateToString(Date date) {
        String formattedDate = null;
        if (date != null) {
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            formattedDate = df.format(date);
        }
        return formattedDate;
    }

    /**
     * Converts a string to a Date object.
     *
     * @param dateString The date string passed in for conversion.
     * @return The date object converted from the string.
     */
    public Date convertStringToDate(String dateString) {
        Date theDate = null;
        if (dateString != null) {
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            try {
                theDate = df.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return theDate;
    }
}
