package com.ui.buckaroos;

import java.text.NumberFormat;
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

/**
 * This class defines an activity to display account reports.
 *
 * @author Gene Hynson
 * @author Daniel Carnauba
 * @version 1.0
 */
public class Reports extends Activity implements OnClickListener {

    private ControllerInterface controller;
    private Button menu;
    private Map<String, Double> categoryTotals;
    private List<String> categoryNames;

    // private Map<String, Double> spendingMap = new HashMap<String, Double>();
    // private List<String> categoryNames = new ArrayList<String>();

    // private List<Double> categoryTotals = new ArrayList<Double>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        controller = new UserAccountController(this);
        // categoryTotals = new HashMap<String, Double>();
        // categoryNames = new ArrayList<String>();
        menu = (Button) findViewById(R.id.menuButton);
        menu.setOnClickListener(this);
        getActionBar().hide();
        // spendingMap = controller.generateSpendingCategoryReport();
        // for (Double total : spendingMap.values()) {
        // categoryTotals.add(total);
        // System.out.println(total);
        // }
        // for (String category : spendingMap.keySet()) {
        // categoryNames.add(category);
        // System.out.println();
        // }
        categoryTotals = controller.getTransactionsInDate();
        categoryNames = controller.getTransactionNamesInDate();
        // categoryTotals = controller.getIncomeTransactionsInDate();
        // categoryNames = controller.getIncomeTransactionNamesInDate();

        populateListView();
    }

    /**
     *
     */
    private void populateListView() {
        ArrayAdapter<String> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.spendingView);
        list.setAdapter(adapter);
    }

    /**
     * This private class defines a List Adapter object.
     *
     * @author Daniel Carnauba
     * @version 1.0
     */
    private class MyListAdapter extends ArrayAdapter<String> {
        public MyListAdapter() {
            super(Reports.this, R.layout.reports_view, categoryNames);
        }

        @Override
        public View getView(int position, View convertView,
                ViewGroup parent) {
            // Make sure we have a view to work with(may have been given null
            View itemView = convertView;
            if (itemView == null) {
                itemView =
                        getLayoutInflater().inflate(R.layout.reports_view,
                                parent, false);
            }

            String name = categoryNames.get(position);
            double total = categoryTotals.get(name);
            // double total = spendingMap.get(name);
            System.out.println(name + " -> " + total);

            TextView accountBalanceText =
                    (TextView) itemView.findViewById(R.id.item_report);
            accountBalanceText.setText(name);

            TextView balanceText =
                    (TextView) itemView
                            .findViewById(R.id.item_report_balace);
            NumberFormat us = NumberFormat.getCurrencyInstance();
            balanceText.setText(us.format(total));

            // TextView dateText =
            // (TextView) itemView.findViewById(R.id.dateRange);
            // dateText.setText("From: " + UserAccountController.getBeginDate()
            // + " To: " + UserAccountController.getEndDate());
            // dateText.setText("testing date");
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
