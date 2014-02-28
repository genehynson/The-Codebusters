package com.ui.buckaroos;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

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

import com.controller.buckaroos.UserAccountController;
import com.example.buckaroos.R;
import com.model.buckaroos.Account;
import com.model.buckaroos.AccountTransaction;

/**
 * @author Daniel Carnauba
 * @version 1.0
 */
public class AccountOverview extends Activity implements OnClickListener {
    private ArrayList<AccountTransaction> accountTransaction = new ArrayList<AccountTransaction>();
    private UserAccountController controller = new UserAccountController(this);
    private TextView accountName;
    private Button menu;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.account_overview, menu);
        return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_overview);
        populateAccountList();
        populateListView();
        accountName = (TextView) findViewById(R.id.accountText);
        accountName.setText(controller.getCurrentAccount().getName());
        menu = (Button) findViewById(R.id.menu);
        menu.setOnClickListener(this);

    }

    private void populateAccountList() {
        accountTransaction = controller.getAllAccountTransactions();
//    	accountTransaction.add(new AccountTransaction(1, "dollars", "withdraw", "Shopping", "4:00"));
//    	accountTransaction.add(new AccountTransaction(2, "dollars", "withdraw", "Shopping", "4:00"));
//    	accountTransaction.add(new AccountTransaction(3, "dollars", "deposit", "Shopping", "4:00"));
//    	accountTransaction.add(new AccountTransaction(4, "dollars", "withdraw", "Shopping", "4:00"));
//    	accountTransaction.add(new AccountTransaction(5, "dollars", "deposit", "Shopping", "4:00"));

    }

    private void populateListView() {
        ArrayAdapter<AccountTransaction> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.transactionListView);
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<AccountTransaction> {
        public MyListAdapter() {
            super(AccountOverview.this, R.layout.transaction_view, accountTransaction);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Make sure we have a view to work with(may have been given null
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.transaction_view,
                        parent, false);
            }

            AccountTransaction current = accountTransaction.get(position);
//
//            TextView accountText = (TextView) itemView
//                    .findViewById(R.id.item_userAccountName);
//            accountText.setText(current.getName());

            TextView accountBalanceText = (TextView) itemView
                    .findViewById(R.id.item_transaction);
//            NumberFormat us = NumberFormat.getCurrencyInstance();
            accountBalanceText.setText(current.toString());
            return itemView;
        }
    }

	@Override
	public void onClick(View v) {
		startActivity(new Intent(AccountOverview.this, LoginSuccess.class));
	}
}
