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

import com.example.buckaroos.R;

public class DateChooser extends Activity implements OnClickListener {

    private Button save;
    private DatePicker chooser;
    private Date today;
    private Calendar cal;
    private static Date date;
    private int currentYear;
    private int currentMonth;
    private int currentDay;
    private Transaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_chooser);
        transaction = new Transaction();
        
        today = new Date();
        cal = Calendar.getInstance();
        cal.setTime(today);
        currentYear = cal.get(Calendar.YEAR);
        currentMonth = cal.get(Calendar.MONTH);
        currentDay = cal.get(Calendar.DAY_OF_MONTH);
        date = new Date();
        
        save = (Button) findViewById(R.id.saveDate);
        save.setOnClickListener(this);
        chooser = (DatePicker) findViewById(R.id.datePicker);
        chooser.init(currentYear, currentMonth, currentDay, dateChanged);
        getActionBar().hide();
        
        setDateForTransaction(currentYear, currentMonth, currentDay);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.date_chooser, menu);
        return true;
    }
    
    @SuppressWarnings("deprecation")
	private void setDateForTransaction(int year, int month, int day) {
		date.setDate(day);
    	date.setMonth(month);
    	date.setYear(year);
    	transaction.setDate(date);
    }

    private DatePicker.OnDateChangedListener dateChanged =
            new DatePicker.OnDateChangedListener() {

                @Override
                public void onDateChanged(DatePicker view, int year,
                        int monthOfYear, int dayOfMonth) {
                    setDateForTransaction(year, monthOfYear, dayOfMonth);
                }
            };

    @Override
    public void onClick(View v) {
        startActivity(new Intent(DateChooser.this, Transaction.class));
    }

}
