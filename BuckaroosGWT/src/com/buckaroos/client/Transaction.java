package com.buckaroos.client;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Intent;
import android.widget.Toast;

import com.buckaroos.shared.ControllerInterface;
import com.buckaroos.shared.UserAccountController;
import com.buckaroos.utility.CredentialConfirmer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.ui.buckaroos.LoginSuccess;
import com.ui.buckaroos.Transaction;

public class Transaction extends Composite {

	private static TransactionUiBinder uiBinder = GWT
			.create(TransactionUiBinder.class);

	interface TransactionUiBinder extends UiBinder<Widget, Transaction> {
	}
	
	private Button save, date;
	private TextBox amount, category;
	private RadioButton withdraw, deposit;
	//radio group
	private ControllerInterface controller;
//	private TimePicker time;
	private static Date dateChosen;
	private static boolean dateChanged = false;
	private SimpleDateFormat dateFormat;
	
	public Transaction() {
		initWidget(uiBinder.createAndBindUi(this));
		controller = new UserAccountController();
    	dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm", Locale.ENGLISH);
    	dateChosen = new Date();
    	
    	save = new Button();
    	date = new Button();
		save.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				 int hour = time.getCurrentHour();
		            int minute = time.getCurrentMinute();
		            dateChosen.setHours(hour);
		            dateChosen.setMinutes(minute);
		            if (!amount.getText().toString().equals("")) {
		                newAmount = Double.parseDouble(amount.getText().toString());
		                String categoryText = category.getText().toString();
		                if (withdraw.isChecked()) {
		                    controller.addWithdrawal(newAmount, "dollars", categoryText,
		                            dateChosen);
		                    startActivity(new Intent(Transaction.this,
		                            LoginSuccess.class));
		                } else if (deposit.isChecked()) {
		                    controller.addDeposit(newAmount, "dollars", categoryText, dateChosen);
		                    startActivity(new Intent(Transaction.this,

		            }
		            }
			}
		});
		
		date.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				 dateChanged = true;
				 RootPanel.
			}
		});
	}

}
