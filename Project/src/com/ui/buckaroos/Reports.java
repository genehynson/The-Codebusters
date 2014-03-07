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

    private ControllerInterface controller;
    private Button menu;
    private Map<String, Double> categoryTotals;
    private List<String> categoryNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        controller = new UserAccountController(this);
        categoryTotals = new HashMap<String, Double>();
        categoryNames = new ArrayList<String>();
        menu = (Button) findViewById(R.id.menuButton);
        menu.setOnClickListener(this);
        getActionBar().hide();
        categoryTotals = controller.getTransactionsInDate();
        categoryNames = controller.getTransactionNamesInDate();
        populateListView();
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

    @Override
    public void onClick(View v) {
        startActivity(new Intent(Reports.this, LoginSuccess.class));
    }

}
