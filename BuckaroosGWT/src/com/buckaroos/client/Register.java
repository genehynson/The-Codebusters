package com.buckaroos.client;

import com.buckaroos.shared.UserAccountController;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Register extends Composite {

	private static RegisterUiBinder uiBinder = GWT
			.create(RegisterUiBinder.class);

	interface RegisterUiBinder extends UiBinder<Widget, Register> {
	}
	@UiField
	Label title, username, email, password;
	TextBox etName, etEmail, etPass;
	Button bRegister;
	
	private UserAccountController controller;
	private Panel vPanel;

	public Register() {
		initWidget(uiBinder.createAndBindUi(this));
		controller = new UserAccountController();
		title = new Label();
		title.setText("Register");
		username = new Label();
		username.setText("Username:");
		email = new Label();
		email.setText("Email:");
		password = new Label();
		password.setText("Password:");
		etName = new TextBox();
		etEmail = new TextBox();
		etPass = new TextBox();
		bRegister = new Button();
		bRegister.setText("Register");
		vPanel = new VerticalPanel();
		bRegister.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						if (!etName.getText().toString().equals("")
								&& !etEmail.getText().toString().equals("")
								&& !etPass.getText().toString().equals("")) {
							if (controller.getLoginAccount(etName.getText().toString()) == null) {
								controller.addLoginAccount(etName.getText().toString(), etPass
										.getText().toString(), etEmail.getText().toString());
								RootPanel.get("page").clear();
								ChangeAccount ca = new ChangeAccount();
							} else {
								//say "account already exists"
							}
						} else {
							//say "all fields required"
						}
						
					}
			});
		vPanel.add(title);
		vPanel.add(username);
		vPanel.add(etName);
		vPanel.add(email);
		vPanel.add(etEmail);
		vPanel.add(password);
		vPanel.add(etPass);
		vPanel.add(bRegister);
		RootPanel.get("page").add(vPanel);
		
	}

}
