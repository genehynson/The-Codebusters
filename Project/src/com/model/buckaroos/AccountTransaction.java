package com.model.buckaroos;

import java.sql.Date;

/**
 * Each transaction has an amount and type (withdraw/deposit)
 * 
 * @author Gene
 * 
 */
public class AccountTransaction {

    private double amount;
    private String type;
    private String currency;
    private String category;
    // private String time;
    private Date time;

    // public AccountTransaction(double amount, String currency, String type,
    // String category, String time) {
    // this.type = type;
    // this.amount = amount;
    // this.currency = currency;
    // this.category = category;
    // this.time = time;
    // }

    public AccountTransaction(double amount, String currency, String type,
            String category, Date time) {
        this.type = type;
        this.amount = amount;
        this.currency = currency;
        this.category = category;
        this.time = time;
    }

    public String toString() {
        return type + " of  " + amount + " in " + currency + " for/from "
                + category + " at " + time;
    }
}
