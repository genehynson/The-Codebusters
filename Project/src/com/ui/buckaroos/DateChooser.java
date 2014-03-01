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

    private static int selectedYear;
    private static int selectedMonth;
    private static int selectedDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_chooser);

        today = new Date();
        cal = Calendar.getInstance();
        cal.setTime(today);
        selectedYear = cal.get(Calendar.YEAR);
        selectedMonth = cal.get(Calendar.MONTH);
        selectedDay = cal.get(Calendar.DAY_OF_MONTH);
        save = (Button) findViewById(R.id.saveDate);
        save.setOnClickListener(this);
        chooser = (DatePicker) findViewById(R.id.datePicker);
        chooser.init(selectedYear, selectedMonth, selectedDay, dateChanged);
        getActionBar().hide();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.date_chooser, menu);
        return true;
    }

    private DatePicker.OnDateChangedListener dateChanged = new DatePicker.OnDateChangedListener() {

        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear,
                int dayOfMonth) {
            selectedYear = year;
            selectedMonth = monthOfYear;
            selectedDay = dayOfMonth;
        }

    };

    @Override
    public void onClick(View v) {
        startActivity(new Intent(DateChooser.this, Transaction.class));
    }

    public int getDay() {
        return selectedDay;
    }

    public int getMonth() {
        return selectedMonth;
    }

    public int getYear() {
        return selectedYear;
    }

}
