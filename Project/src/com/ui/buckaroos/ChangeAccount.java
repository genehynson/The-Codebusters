package com.ui.buckaroos;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.controller.buckaroos.UserAccountController;
import com.example.buckaroos.R;
import com.model.buckaroos.Account;

/**
 * @author Daniel Carnauba
 * @version 1.0
 */
public class ChangeAccount extends Activity {
    private List<Account> userAccounts = new ArrayList<Account>();
//    private ListView listView;

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

//    	listView.setOnItemClickListener(new OnItemClickListener() {
//    		public void onItemClick(AdapterView<?> parent, View view,
//    				int position, long id) {
//    			// When clicked, show a toast with the TextView text
//    			Toast.makeText(getApplicationContext(),
//    					((TextView) view).getText(), Toast.LENGTH_SHORT).show();
//    		}
//    	});
//
    }
    
    

    private void populateAccountList() {
        UserAccountController controller = new UserAccountController(this);
        userAccounts = controller.getAllUserAccounts();
    }

    private void populateListView() {
        ArrayAdapter<Account> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.accountsListView);
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<Account> {
        public MyListAdapter() {
            super(ChangeAccount.this, R.layout.item_view, userAccounts);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Make sure we have a view to work with(may have been given null
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.item_view,
                        parent, false);
            }

            Account current = userAccounts.get(position);

            TextView accountText = (TextView) itemView
                    .findViewById(R.id.item_userAccountName);
            accountText.setText(current.getName());

            TextView accountBalanceText = (TextView) itemView
                    .findViewById(R.id.item_userAccountBalance);
            NumberFormat us = NumberFormat.getCurrencyInstance();
            accountBalanceText.setText("Balance: "
                    + us.format(current.getBalance()));
            return itemView;
        }
    }
}

//controller.setCurrentAccount(controller.getUserAccount(changeAccount.getText().toString()));
//startActivity(new Intent(ChangeAccount.this, LoginSuccess.class));


