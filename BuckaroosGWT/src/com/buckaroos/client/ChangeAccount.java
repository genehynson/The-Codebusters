package com.buckaroos.client;

import java.util.ArrayList;
import java.util.List;

import com.buckaroos.server.Account;
import com.buckaroos.shared.ControllerInterface;
import com.buckaroos.shared.UserAccountController;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ChangeAccount extends Composite {

	private static ChangeAccountUiBinder uiBinder = GWT
			.create(ChangeAccountUiBinder.class);

	interface ChangeAccountUiBinder extends UiBinder<Widget, ChangeAccount> {
	}

	@UiField
	FlexTable table;
	Label title;
	Button createAccount;
	
    private List<Account> userAccounts;
    private ControllerInterface controller;
    private Panel vPanel;
    
    
	public ChangeAccount() {
		initWidget(uiBinder.createAndBindUi(this));
		createAccount = new Button();
		createAccount.setText("Create New Account");
		createAccount.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//how will we display this? Go to ChanceAccount? 
				RootPanel.get("page").clear();
				CreateAccount ca = new CreateAccount();
			}
		});
		title = new Label();
		title.setText("Select Account");
		userAccounts = new ArrayList<Account>();
		controller = new UserAccountController();
		table = new FlexTable();
		vPanel = new VerticalPanel();
		userAccounts = controller.getAllUserAccounts();
//		for (int i = 0; i < userAccounts.size(); i++) {
		for (int i = 0; i < 2; i++) {
//				table.setText(i, 0, userAccounts.get(i).getName());
//				table.setText(i, 1, String.valueOf(userAccounts.get(i).getBalance()));
			table.setText(i, 0, "test account");
			table.setText(i, 2, "test amount");
		}
		table.addClickHandler(new ClickHandler() {
		    public void onClick(ClickEvent event) {
		        Cell cell = table.getCellForEvent(event);
		        int receiverRowIndex = cell.getRowIndex();
		        controller.setCurrentAccount(controller.getUserAccount(table.getText(receiverRowIndex, 0)));
		        RootPanel.get("page").clear();
		        AccountOverview ao = new AccountOverview();
		    }
		});
		title.addStyleName("white-text");
		table.addStyleName("white-text");
		vPanel.add(title);
		vPanel.add(table);
		vPanel.add(createAccount);
		RootPanel.get("page").add(vPanel);
	}

}
