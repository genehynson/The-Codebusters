package com.buckaroos.client;

import com.buckaroos.shared.ControllerInterface;
import com.buckaroos.shared.UserAccountController;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class WelcomeScreen extends Composite implements EntryPoint {

	private static WelcomeScreenUiBinder uiBinder = GWT
			.create(WelcomeScreenUiBinder.class);

	interface WelcomeScreenUiBinder extends UiBinder<Widget, WelcomeScreen> {
	}

	public WelcomeScreen() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	private HorizontalPanel hpanel;
    private UserAccountController controller;
    
    @UiField
    Button bReg, bLog;

	@Override
	public void onModuleLoad() {
        controller = new UserAccountController();
        bReg = new Button();
        bLog = new Button();
        hpanel = new HorizontalPanel();
//        if (controller.getLoginAccount("admin") == null) {
//            controller.addLoginAccount("admin", "pass123", " ");
//        }
        bReg.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
		        RootPanel.get("page").clear();
		        Register r = new Register();
			}
		});
        bLog.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
		        RootPanel.get("page").clear();
		        Login l = new Login();
			}
		});
        bLog.setText("Login");
        bReg.setText("Register");
        hpanel.add(bLog);
        hpanel.add(bReg);
        RootPanel.get("page").clear();
        RootPanel.get("page").add(hpanel);
		
	}

}
