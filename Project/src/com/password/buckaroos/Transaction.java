package com.password.buckaroos;

public class Transaction {
	
	private int amount;
	private String type;

	public Transaction(int amount) {
		if (amount > 0) {
			type = "Deposit";
		} else {
			type = "Withdraw";
		}
		this.amount = amount;
	}
	
	public String toString() {
		return type + ": " + amount;
	}
}
