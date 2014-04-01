package com.ui.buckaroos;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;

import com.controller.buckaroos.UserAccountController;
import com.example.buckaroos.R;

/**
 * This class defines the Date chooser object to allow the user to select a
 * date.
 * 
 * @author Gene Hynson
 * @version 1.0
 */
public class DateChooser extends Activity implements OnClickListener {

    private Button save;
    private DatePicker chooser;
    private Calendar cal;
    private Date date;
    private int currentYear;
    private int currentMonth;
    private int currentDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_chooser);

        date = new Date();
        cal = Calendar.getInstance();
        cal.setTime(date);
        currentYear = cal.get(Calendar.YEAR);
        currentMonth = cal.get(Calendar.MONTH);
        currentDay = cal.get(Calendar.DAY_OF_MONTH);

        UserAccountController.setTheDate(date);

        save = (Button) findViewById(R.id.saveDate);
        save.setOnClickListener(this);
        chooser = (DatePicker) findViewById(R.id.datePicker);
        chooser.init(currentYear, currentMonth, currentDay, dateChanged);
        getActionBar().hide();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.date_chooser, menu);
        return true;
    }

    /*
     * Defines a listener for the date changed object.
     */
    private final DatePicker.OnDateChangedListener dateChanged =
            new DatePicker.OnDateChangedListener() {

                @SuppressWarnings("deprecation")
                @Override
                public void onDateChanged(DatePicker view, int year,
                        int monthOfYear, int dayOfMonth) {
                    date.setDate(dayOfMonth);
                    date.setMonth(monthOfYear);
                    date.setYear(year);
                    UserAccountController.setTheDate(date);
                }
            };

    @Override
    public void onClick(View v) {
        startActivity(new Intent(DateChooser.this, Transaction.class));
    }

}
