package com.utility.buckaroos;

import java.text.DateFormat;
import java.text.ParseException;
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
        DateFormat df = DateFormat.getTimeInstance();
        return df.format(date);
    }

    /**
     * Converts a Date object into a string.
     *
     * @param date The date object to be converted to a string.
     * @return The string conversion of the date object.
     */
    public String convertDateToString(Date date) {
        DateFormat df = DateFormat.getDateInstance();
        return df.format(date);
    }

    /**
     * Converts a string to a Date object.
     *
     * @param dateString The date string passed in for conversion.
     * @return The date object converted from the string.
     */
    public Date convertStringToDate(String dateString) {
        DateFormat df = DateFormat.getDateInstance();
        Date date = new Date();
        try {
            date = df.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
