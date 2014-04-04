package com.tests.buckaroos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.utility.buckaroos.DateFormatter;

public class DateFormatterTest {

    private DateFormatter df;

    @Before
    public void setup() {
        df = new DateFormatter();
    }

    @Test
    public void convertTimeToString() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 10);
        cal.set(Calendar.MINUTE, 55);
        cal.set(Calendar.SECOND, 55);
        assertNull(df.convertTimeToString(null));
        assertEquals("10:55:55", df.convertTimeToString(cal.getTime()));
    }

    @Test
    public void convertDateToString() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2014);
        cal.set(Calendar.MONTH, 3);
        cal.set(Calendar.DAY_OF_MONTH, 4);
        assertNull(df.convertDateToString(null));
        assertEquals("2014/04/04", df.convertDateToString(cal.getTime()));
    }

    @Test
    public void convertStringToDate() {
        @SuppressWarnings("deprecation")
        Date c = new Date(114, 03, 04, 0, 0);
        assertNull(df.convertStringToDate(null));
        assertEquals(c, df.convertStringToDate("2014/04/04"));
    }
}
