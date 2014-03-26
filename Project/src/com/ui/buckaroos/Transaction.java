package com.ui.buckaroos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TimePicker;
import android.widget.Toast;

import com.controller.buckaroos.ControllerInterface;
import com.controller.buckaroos.UserAccountController;
import com.example.buckaroos.R;

/**
 * Transaction for Buckaroos Transaction activity.
 * 
 * @author Daniel Carnauba
 * @version 1.0
 * 
 */
public class Transaction extends Activity implements OnClickListener,
        OnCheckedChangeListener {

    private Button save, date;
    private EditText amount, category;
    private RadioButton withdraw, deposit;
    private RadioGroup radioGroup;
    private ControllerInterface controller;
    private TimePicker time;
    private static Date dateChosen;
    private static boolean dateChanged = false;
    SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        initialize();
    }

    private void initialize() {
        controller = new UserAccountController(this);
        dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm", Locale.ENGLISH);
        dateChosen = new Date();

        amount = (EditText) findViewById(R.id.accountNickName);
        category = (EditText) findViewById(R.id.category);
        save = (Button) findViewById(R.id.saveButton);
        save.setOnClickListener(this);
        date = (Button) findViewById(R.id.dateButton);
        date.setOnClickListener(this);
        withdraw = (RadioButton) findViewById(R.id.withdrawButton);
        deposit = (RadioButton) findViewById(R.id.depositButton);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);
        time = (TimePicker) findViewById(R.id.timePicker);

        getActionBar().hide();
        setDateText();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.transaction, menu);
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onClick(View v) {
        double newAmount = 0;
        switch (v.getId()) {
        case R.id.saveButton:
            int hour = time.getCurrentHour();
            int minute = time.getCurrentMinute();
            // Calendar.set(Calendar.HOUR_OF_DAY, hour);
            dateChosen.setHours(hour);
            // Calendar.set(Calendar.MINUTE, minute);
            dateChosen.setMinutes(minute);
            if (!amount.getText().toString().equals("")) {
                newAmount = Double.parseDouble(amount.getText().toString());
                String categoryText = category.getText().toString();
                if (withdraw.isChecked()) {
                    controller.addWithdrawal(newAmount, "dollars",
                            categoryText, dateChosen);
                    startActivity(new Intent(Transaction.this,
                            LoginSuccess.class));
                    Toast toast =
                            Toast.makeText(this, "Withdraw Saved.",
                                    Toast.LENGTH_SHORT);
                    toast.show();
                } else if (deposit.isChecked()) {
                    controller.addDeposit(newAmount, "dollars", categoryText,
                            dateChosen);
                    Toast toast =
                            Toast.makeText(this, "Deposit Saved.",
                                    Toast.LENGTH_SHORT);
                    toast.show();
                    startActivity(new Intent(Transaction.this,
                            LoginSuccess.class));
                } else {
                    Toast toast =
                            Toast.makeText(this, "All fields required.",
                                    Toast.LENGTH_SHORT);
                    toast.show();
                }

            } else {
                Toast toast =
                        Toast.makeText(this, "All fields required.",
                                Toast.LENGTH_SHORT);
                toast.show();
            }
            break;
        case R.id.dateButton:
            dateChanged = true;
            startActivity(new Intent(Transaction.this, DateChooser.class));
        }
    }

    public void setDateText() {
        if (dateChanged) {
            date.setText(dateFormat.format(dateChosen).toString());
        }
    }

    public void setDate(Date date) {
        Transaction.dateChosen = date;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        group.check(checkedId);
    }

}
