package com.model.buckaroos;

/**
 * Each transaction has an amount and type (withdraw/deposit)
 * @author Gene
 *
 */
public class Transaction {
	
	private double amount;
	private String type;
	private String currency;
	private String category;
	private String time;

	public Transaction(double amount, String currency, String type, String category, String time) {
		
		this.type = type;
		this.amount = amount;
		this.currency = currency;
		this.category = category;
		this.time = time;
	}
	
	public String toString() {
		return type + " of  " + amount + " in " + currency + " for/from " + category + " at " + time;
	}
}
