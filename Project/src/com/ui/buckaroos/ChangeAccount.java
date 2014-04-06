package com.ui.buckaroos;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.controller.buckaroos.UserAccountController;
import com.example.buckaroos.R;
import com.model.buckaroos.Account;

/**
 * This class implements a list view. It allows the user to switch between
 * accounts by scrolling through a list of accounts. The list items are
 * clickable, so the user can just click on a account item and the application
 * will perform a transition between accounts.
 *
 * @author Daniel Carnauba
 * @version 1.0
 */
public class ChangeAccount extends Activity {
    private List<Account> userAccounts = new ArrayList<Account>();
    private final UserAccountController controller =
            new UserAccountController(this);

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.account_overview, menu);
        return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_account);
        populateAccountList();
        populateListView();

        registerClickCallback();

    }

    /**
     * Defines a click call back listener making the list view responsive.
     */
    private void registerClickCallback() {
        ListView listView = (ListView) findViewById(R.id.accountsListView);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                Account clickedAccount = userAccounts.get(position);
                controller.setCurrentAccount(clickedAccount);
                startActivity(new Intent(ChangeAccount.this,
                        AccountOverview.class));
            }
        });
    }

    /**
     * Populates the account list.
     */
    private void populateAccountList() {
        userAccounts = controller.getAllUserAccounts();
    }

    /**
     * Populates the list view.
     */
    private void populateListView() {
        ArrayAdapter<Account> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.accountsListView);
        list.setAdapter(adapter);
    }

    /**
     * This private class defines a list adapter object.
     *
     * @author Daniel Carnauba
     * @version 1.0
     */
    private class MyListAdapter extends ArrayAdapter<Account> {
        /**
         * Constructs a List adapter object.
         */
        public MyListAdapter() {
            super(ChangeAccount.this, R.layout.item_view, userAccounts);
        }

        @Override
        public View getView(int position, View convertView,
                ViewGroup parent) {
            // Make sure we have a view to work with(may have been given null
            View itemView = convertView;
            if (itemView == null) {
                itemView =
                        getLayoutInflater().inflate(R.layout.item_view,
                                parent, false);
            }

            Account current = userAccounts.get(position);

            TextView accountText =
                    (TextView) itemView
                            .findViewById(R.id.item_userAccountName);
            accountText.setText(current.getNickName());

            TextView accountBalanceText =
                    (TextView) itemView
                            .findViewById(R.id.item_userAccountBalance);
            NumberFormat us = NumberFormat.getCurrencyInstance();
            accountBalanceText.setText("Balance: "
                    + us.format(current.getBalance()));
            return itemView;
        }
    }
}
