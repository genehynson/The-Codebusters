package com.ui.buckaroos;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.controller.buckaroos.ControllerInterface;
import com.controller.buckaroos.UserAccountController;
import com.example.buckaroos.R;
import com.model.buckaroos.Account;
import com.model.buckaroos.AccountTransaction;

public class Reports extends Activity implements OnClickListener {

    private static String beginDate;
    private static String endDate;
    private ControllerInterface controller;
    private Button menu;
    private List<AccountTransaction> importedTransactions =
            new ArrayList<AccountTransaction>();
    private List<AccountTransaction> sortedTransactions =
            new ArrayList<AccountTransaction>();
    private List<String> categoryNames = new ArrayList<String>();
    private List<Account> allAccounts = new ArrayList<Account>();
    private Map<String, Double> categoryTotals;
    private Account actualCurrentAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        controller = new UserAccountController(this);
        categoryTotals = new HashMap<String, Double>();
        menu = (Button) findViewById(R.id.menuButton);
        menu.setOnClickListener(this);
        getActionBar().hide();
        actualCurrentAccount = controller.getCurrentAccount();
        importedTransactions = new ArrayList<AccountTransaction>();
        sortedTransactions = new ArrayList<AccountTransaction>();
        getTransactionsInDate();
        populateListView();
    }

    private void getTransactionsInDate() {
        allAccounts = controller.getAllUserAccounts();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date beforeDate = null;
        Date theDate = null;
        Date afterDate = null;

        try {
            beforeDate = sdf.parse(beginDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            afterDate = sdf.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String date;
        double totalSpending = 0;
        for (Account account : allAccounts) {
            controller.setCurrentAccount(account);
            importedTransactions = controller.getAllAccountTransactions();
            for (AccountTransaction transaction : importedTransactions) {
                date = transaction.getDate();
                if (!date.equals("00/00/0")) {
                    System.out.println("Date: " + date);
                    try {
                        theDate = sdf.parse(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println(beforeDate + " " + afterDate + " "
                            + theDate);
                    if ((beforeDate.before(theDate) && afterDate.after(theDate))
                            || beforeDate.equals(theDate)
                            || afterDate.equals(theDate)) {
                        if (transaction.getType().equals("Withdrawal")) {
                            if (!categoryTotals.containsKey(transaction
                                    .getCategory())) {
                                categoryTotals.put(transaction.getCategory(),
                                        transaction.getAmount());
                                categoryNames.add(transaction.getCategory());
                            } else {
                                categoryTotals.put(
                                        transaction.getCategory(),
                                        categoryTotals.get(transaction
                                                .getCategory())
                                                + transaction.getAmount());
                            }
                            totalSpending =
                                    transaction.getAmount() + totalSpending;
                            // list of all applicable transactions...isn't used.
                            sortedTransactions.add(transaction);
                        }
                    }
                }
            }
        }
        categoryTotals.put("Total", totalSpending);
        categoryNames.add("Total");
        controller.setCurrentAccount(actualCurrentAccount);
    }

    private void populateListView() {
        ArrayAdapter<String> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.spendingView);
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<String> {
        public MyListAdapter() {
            super(Reports.this, R.layout.reports_view, categoryNames);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Make sure we have a view to work with(may have been given null
            View itemView = convertView;
            if (itemView == null) {
                itemView =
                        getLayoutInflater().inflate(R.layout.reports_view,
                                parent, false);
            }

            String name = categoryNames.get(position);
            double total = categoryTotals.get(name);
            System.out.println(name + " -> " + total);

            TextView accountBalanceText =
                    (TextView) itemView.findViewById(R.id.item_report);
            accountBalanceText.setText(name);

            TextView balanceText =
                    (TextView) itemView.findViewById(R.id.item_report_balace);
            NumberFormat us = NumberFormat.getCurrencyInstance();
            balanceText.setText("Total: " + us.format(total));
            return itemView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.reports, menu);
        return true;
    }

    public void setBeginDate(String date) {
        Reports.beginDate = date;
    }

    public void setEndDate(String date) {
        Reports.endDate = date;
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(Reports.this, LoginSuccess.class));
    }

}
