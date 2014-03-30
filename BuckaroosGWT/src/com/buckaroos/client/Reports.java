package com.buckaroos.client;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.buckaroos.shared.ControllerInterface;
import com.buckaroos.shared.UserAccountController;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class Reports extends Composite {

	private static ReportsUiBinder uiBinder = GWT.create(ReportsUiBinder.class);

    private ControllerInterface controller;
    private Button menu;
    private Map<String, Double> categoryTotals;
    private List<String> categoryNames;
    private FlexTable table;

    interface ReportsUiBinder extends UiBinder<Widget, Reports> {
    }

    public Reports() {
    	initWidget(uiBinder.createAndBindUi(this));
    	controller = new UserAccountController();
    	categoryTotals = new HashMap<String, Double>();
    	categoryNames = new ArrayList<String>();
		menu.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("").add(new AccountOverview());
			}
		});
		table = new FlexTable();
		categoryNames = controller.getTransactionNamesInDate();
		categoryTotals = controller.getTransactionsInDate();
		String name = "";
		String total = "";
		NumberFormat us = NumberFormat.getCurrencyInstance();
		for (int i = 0; i < categoryNames.size(); i++) {
			name = categoryNames.get(i);
			total = us.format(categoryTotals.get(name));
			table.setText(i,0, name);
			table.setText(i,1, "Total: " + total);
		}
    }

}
