//package com.ui.buckaroos;
//
//import com.controller.buckaroos.UserAccountController;
//import com.example.buckaroos.R;
//import com.example.buckaroos.R.id;
//import com.example.buckaroos.R.layout;
//import com.example.buckaroos.R.menu;
//
//import android.os.Bundle;
//import android.app.Activity;
//import android.content.Intent;
//import android.view.Menu;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//public class ChangeAccount extends Activity implements OnClickListener {
//
//  private Button save;
//  private EditText changeAccount;
//  private UserAccountController controller;
//  
//  @Override
//  protected void onCreate(Bundle savedInstanceState) {
//      super.onCreate(savedInstanceState);
//      setContentView(R.layout.activity_change_account);
//      initialize();
//  }
//
//  @Override
//  public boolean onCreateOptionsMenu(Menu menu) {
//      // Inflate the menu; this adds items to the action bar if it is present.
//      getMenuInflater().inflate(R.menu.change_account, menu);
//      return true;
//  }
//  
//  public void initialize() {
//      changeAccount = (EditText) findViewById(R.id.changeAccount);
//      save = (Button) findViewById(R.id.saveChangeAccount);
//      save.setOnClickListener(this);
//      controller = new UserAccountController(this);
//      getActionBar().hide();
//  }
//
//  @Override
//  public void onClick(View v) {
//      if (controller.getUserAccount(changeAccount.getText().toString()) != null) {
//          controller.setCurrentAccount(controller.getUserAccount(changeAccount.getText().toString()));
//          startActivity(new Intent(ChangeAccount.this, LoginSuccess.class));
//      } else {
//          Toast toast = Toast.makeText(this, "No such account.", Toast.LENGTH_SHORT);
//          toast.show();
//      }
//  }
//
//}
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
    UserAccountController controller = new UserAccountController(this);
    
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

    private void registerClickCallback() {
    	ListView listView = (ListView) findViewById(R.id.accountsListView);
        listView.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view,
        			int position, long id) {
        		// When clicked, show a toast with the TextView text
        		Account clickedAccount = userAccounts.get(position);
        		controller.setCurrentAccount(clickedAccount);
        		startActivity(new Intent(ChangeAccount.this, AccountOverview.class));
        	}
        });		
	}

	private void populateAccountList() {
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
