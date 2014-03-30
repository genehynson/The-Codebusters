package com.buckaroos.client;

import com.buckaroos.shared.ControllerInterface;
import com.buckaroos.shared.UserAccountController;
import com.buckaroos.utility.CredentialConfirmer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Login extends Composite {

	private static LoginUiBinder uiBinder = GWT.create(LoginUiBinder.class);

	interface LoginUiBinder extends UiBinder<Widget, Login> {
	}

	@UiField
	TextBox etUser, etPass;
	Button bLogin;
	Label title, username, password;

	private Panel vPanel;
    private ControllerInterface controller = new UserAccountController();
    
	public Login() {
		initWidget(uiBinder.createAndBindUi(this));
		title = new Label();
		title.setText("Login");
		username = new Label();
		username.setText("Username:");
		password = new Label();
		password.setText("Password:");
		bLogin = new Button();
		bLogin.setText("Login");
		etUser = new TextBox();
		etPass = new TextBox();
		vPanel = new VerticalPanel();
		bLogin.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				CredentialConfirmer confirm = new CredentialConfirmer();
				if (controller.confirmLogin(etUser.getText().toString(), etPass.getText().toString(), confirm) || 1==1) {
			        RootPanel.get("page").clear();
					RootPanel.get("page").add(new ChangeAccount());
				}
			}
		});
		vPanel.add(title);
		vPanel.add(username);
		vPanel.add(etUser);
		vPanel.add(password);
		vPanel.add(etPass);
		vPanel.add(bLogin);
        RootPanel.get("page").clear();
		RootPanel.get("page").add(vPanel);
	}
}

