package com.ui.buckaroos;

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
import com.model.buckaroos.AccountTransaction;

/**
 * This class implements a ListView that allows the user to view an account
 * overview.
 *
 * @author Daniel Carnauba
 * @version 1.0
 */
public class AccountOverview extends Activity implements OnClickListener {
    private List<AccountTransaction> accountTransaction =
            new ArrayList<AccountTransaction>();
    private final UserAccountController controller =
            new UserAccountController(this);
    private TextView accountName;
    private Button menu;
    private Button report;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
        report = (Button) findViewById(R.id.reports);
        report.setOnClickListener(this);

    }

    /**
     * Populates the account list.
     */
    private void populateAccountList() {
        accountTransaction = controller.getAllAccountTransactions();
    }

    /**
     * Populates the list view.
     */
    private void populateListView() {
        ArrayAdapter<AccountTransaction> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.transactionListView);
        list.setAdapter(adapter);
    }

    /**
     * This class defines an array adapter object.
     * @author Daniel Carnauba
     * @version 1.0
     */
    private class MyListAdapter extends ArrayAdapter<AccountTransaction> {

        /**
         * Constructs a list adapter for the list view.
         */
        public MyListAdapter() {
            super(AccountOverview.this, R.layout.transaction_view,
                    accountTransaction);
        }

        @Override
        public View getView(int position, View convertView,
                ViewGroup parent) {
            // Make sure we have a view to work with(may have been given null
            View itemView = convertView;
            if (itemView == null) {
                itemView =
                        getLayoutInflater().inflate(
                                R.layout.transaction_view, parent, false);
            }

            AccountTransaction current = accountTransaction.get(position);

            TextView accountBalanceText =
                    (TextView) itemView
                            .findViewById(R.id.item_transaction);
            accountBalanceText.setText(current.toString());
            return itemView;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.menu:
            startActivity(new Intent(AccountOverview.this,
                    LoginSuccess.class));
            break;
        case R.id.reports:
            startActivity(new Intent(AccountOverview.this,
                    StartEndDate.class));
            break;
        default:
            break;
        }
    }
}
