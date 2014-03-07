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

public class StartEndDate extends Activity implements OnClickListener {

    private Button continueButton;
    private DatePicker fromChooser, endChooser;
    private Date today;
    private Calendar cal;

    private int currentYear;
    private int currentMonth;
    private int currentDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_end_date);
        
        today = new Date();
        cal = Calendar.getInstance();
        cal.setTime(today);
        currentYear = cal.get(Calendar.YEAR);
        currentMonth = cal.get(Calendar.MONTH);
        currentDay = cal.get(Calendar.DAY_OF_MONTH);
        
        UserAccountController.setBeginDate(today);
        UserAccountController.setEndDate(today);

        continueButton = (Button) findViewById(R.id.continueButton);
        continueButton.setOnClickListener(this);
        fromChooser = (DatePicker) findViewById(R.id.fromDatePicker);
        fromChooser.init(currentYear, currentMonth, currentDay, dateChanged);
        endChooser = (DatePicker) findViewById(R.id.endDatePicker);
        endChooser.init(currentYear, currentMonth, currentDay, dateChanged);
        getActionBar().hide();
    }

    private DatePicker.OnDateChangedListener dateChanged =
            new DatePicker.OnDateChangedListener() {

                @SuppressWarnings("deprecation")
				@Override
                public void onDateChanged(DatePicker view, int year,
                        int monthOfYear, int dayOfMonth) {
                    if (view.getId() == R.id.fromDatePicker) {
                    	Date begin = new Date(year - 1900, monthOfYear, dayOfMonth);
                        UserAccountController.setBeginDate(begin);
                    } else {
                    	Date end = new Date(year - 1900, monthOfYear, dayOfMonth);
                        UserAccountController.setEndDate(end);
                    }
                }

            };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start_end_date, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(StartEndDate.this, Reports.class));
    }
}