package com.buckaroos.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.buckaroos.shared.ControllerInterface;
import com.buckaroos.shared.UserAccountController;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Reports extends Composite {

	private static ReportsUiBinder uiBinder = GWT.create(ReportsUiBinder.class);

	@UiField
	Button menu;
	FlexTable table;
	Label title;
	Label dates;
	
    private ControllerInterface controller;
    private Map<String, Double> categoryTotals;
    private List<String> categoryNames;
    private Panel vPanel;
    

    interface ReportsUiBinder extends UiBinder<Widget, Reports> {
    }

    public Reports() {
    	initWidget(uiBinder.createAndBindUi(this));
    	controller = new UserAccountController();
    	categoryTotals = new HashMap<String, Double>();
    	categoryNames = new ArrayList<String>();
    	title = new Label();
    	title.setText("Reports");
    	dates = new Label();
    	String beginDate = "Jan 1st";
    	String afterDate = "Jan 31st";
    	//String beginDate = controller.beginDate;
    	//String endDate = controller.afterDate;
    	dates.setText(beginDate + " - " + afterDate);
    	menu = new Button();
    	menu.setText("Account Overview");
		menu.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("page").clear();
				AccountOverview ao = new AccountOverview();
			}
		});
		table = new FlexTable();
//		categoryNames = controller.getTransactionNamesInDate();
//		categoryTotals = controller.getTransactionsInDate();
		categoryNames.add("test1");
		categoryNames.add("test2");
		categoryTotals.put("test1", 1000.0);
		categoryTotals.put("test2", 500.0);
		String name = "";
		String total = "";
		NumberFormat us = NumberFormat.getCurrencyFormat();
		for (int i = 0; i < categoryNames.size(); i++) {
			name = categoryNames.get(i);
			total = us.format(categoryTotals.get(name));
			table.setText(i,0, name);
			table.setText(i,1, "Total: " + total);
		}
		vPanel = new VerticalPanel();
		vPanel.add(title);
		vPanel.add(dates);
		vPanel.add(table);
		vPanel.add(menu);
		RootPanel.get("page").add(vPanel);
		
    }

}
