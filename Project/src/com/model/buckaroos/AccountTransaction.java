package com.model.buckaroos;

import java.sql.Date;

/**
 * This class defines an AccountTransaction object. Each transaction has an
 * amount, currency Type, category, creation date, date of transaction, and
 * time.
 * 
 * @author Gene
 * @version 1.0
 * 
 */
public class AccountTransaction {

    private double amount;
    private String type;
    private String currencyType;
    private String category;
    private String dateOfTransaction;
    private Date dateCreated;
    private String time;

    /**
     * Constructs an AccountTransaction object.
     * 
     * @param amount The transaction amount.
     * @param currency The currency type.
     * @param type The transaction type (Withdrawal/Deposit).
     * @param category The transaction category.
     * @param creationDate The date of creation of this transaction.
     * @param dateOfTransaction The date set by the user.
     * @param time The time in which this transaction takes place.
     */
    public AccountTransaction(double amount, String currencyType, String type,
            String category, Date creationDate, String dateOfTransaction,
            String time) {
        this.type = type;
        this.amount = amount;
        this.currencyType = currencyType;
        this.category = category;
        this.dateCreated = creationDate;
        this.dateOfTransaction = dateOfTransaction;
        this.time = time;
    }

    /**
     * Sets the transaction's amount.
     * 
     * @param amount The amount to be set.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Sets the transaction's type.
     * 
     * @param type The type to set (Withdrawal/Deposit).
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets the transaction's currency type
     * 
     * @param currency the currency to set
     */
    public void setCurrency(String currencyType) {
        this.currencyType = currencyType;
    }

    /**
     * Sets the transaction's category.
     * 
     * @param category The category to be set.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Sets the date on which the transaction takes place.
     * 
     * @param dateOfTransaction The dateOfTransaction to set.
     */
    public void setDateOfTransaction(String dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    /**
     * Sets the transaction's date of creation.
     * 
     * @param dateCreated The dateCreated to set.
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * Sets the transaction's time.
     * 
     * @param time The time to set.
     */
    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return type + " of  " + amount + " in " + currencyType + " for/from "
                + category + " on " + dateOfTransaction + " at " + time;
    }

    /**
     * Gets the date of transaction set by user.
     * 
     * @return The date transaction date set by the user.
     */
    public String getDate() {
        return dateOfTransaction;
    }

    /**
     * Gets the transaction's type.
     * 
     * @return The type of this transaction (Withdrawal or Deposit).
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the transaction's amount.
     * 
     * @return The amount of this transaction.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Gets the transaction's category.
     * 
     * @return The category of this transaction.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets the date of creation for this transaction.
     * 
     * @return The date on which this transaction was created.
     */
    public Date getCreationDate() {
        return dateCreated;
    }
}
