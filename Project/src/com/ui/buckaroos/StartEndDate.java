package com.ui.buckaroos;

import java.util.Calendar;
import java.util.Date;

import com.example.buckaroos.R;
import com.example.buckaroos.R.layout;
import com.example.buckaroos.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;

public class StartEndDate extends Activity implements OnClickListener {

	private Button continueButton;
	private DatePicker fromChooser, endChooser;
	private Date today;
	private Calendar cal;
	private Reports reports;

	private static int selectedYear;
	private static int selectedMonth;
	private static int selectedDay;
	    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_end_date);
		today = new Date();
		reports = new Reports();
		cal = Calendar.getInstance();
		cal.setTime(today);
		selectedYear = cal.get(Calendar.YEAR);
		selectedMonth = cal.get(Calendar.MONTH);
		selectedDay = cal.get(Calendar.DAY_OF_MONTH);
		continueButton = (Button) findViewById(R.id.continueButton);
		continueButton.setOnClickListener(this);
		fromChooser = (DatePicker) findViewById(R.id.fromDatePicker);
		fromChooser.init(selectedYear, selectedMonth, selectedDay, dateChanged);
		endChooser = (DatePicker) findViewById(R.id.endDatePicker);
		endChooser.init(selectedYear, selectedMonth, selectedDay, dateChanged);
		getActionBar().hide();
	}
	
	private DatePicker.OnDateChangedListener dateChanged = new DatePicker.OnDateChangedListener() {

        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear,
                int dayOfMonth) {
        	if (view.getId() == R.id.fromDatePicker) {
        		reports.setBeginDate(convertDate(year, monthOfYear + 1, dayOfMonth));
        	} else {
        		reports.setEndDate(convertDate(year, monthOfYear + 1, dayOfMonth));
        	}
        }

    };

    public String convertDate(int year, int month, int day) {
    	String date;
        String monthStr = String.valueOf(month);
        String dayStr = String.valueOf(day);
        if (month < 10) {
            monthStr = "0" + String.valueOf(month);
        }
        if (day < 10) {
            dayStr = "0" + String.valueOf(day);
        }
        date = monthStr + "/" + dayStr + "/" + String.valueOf(year);
        return date;
    }

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