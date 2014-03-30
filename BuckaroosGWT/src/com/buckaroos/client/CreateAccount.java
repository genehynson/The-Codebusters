package com.buckaroos.client;

import com.buckaroos.shared.UserAccountController;
import com.buckaroos.utility.CredentialConfirmer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class CreateAccount extends Composite {

	private static CreateAccountUiBinder uiBinder = GWT
			.create(CreateAccountUiBinder.class);

	interface CreateAccountUiBinder extends UiBinder<Widget, CreateAccount> {
	}
	
	private TextBox accountName, accountNickName, startingBalance, interestRate;
	private Button create;
	private UserAccountController controller;

	public CreateAccount() {
		initWidget(uiBinder.createAndBindUi(this));
		controller = new UserAccountController();
		create = new Button();
		create.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				double balance = 0;
				double interest = 0;
				String nickname;
				if (!accountName.getText().toString().equals("")) {
					if (!startingBalance.getText().toString().equals("")) {
						balance = Double.parseDouble(startingBalance.getText()
								.toString());
					}
					if (!interestRate.getText().toString().equals("")) {
						interest = Double
								.parseDouble(interestRate.getText().toString());
					}
					if (!accountNickName.getText().toString().equals("")) {
						nickname = accountNickName.getText().toString();
					} else {
						nickname = accountName.getText().toString();
					}

					controller.addAccount(accountName.getText().toString(), nickname, balance,
							interest);
					RootPanel.get("").add(new ChangeAccount());
				}
			}
		});
	}

}
