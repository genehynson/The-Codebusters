package com.password.buckaroos;

import java.util.ArrayList;

public class Account {

	private String name;
	private int amount;
	private ArrayList<Transaction> transactions;
	
	public Account(String name, int amount) {
		this.setName(name);
		this.setAmount(amount);
		transactions = new ArrayList<Transaction>();
	}
	
	public Account(String name) {
		this.setName(name);
		setAmount(0);
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		if (amount > this.amount) {
			transactions.add(new Transaction(this.amount - amount));
			
		} else {
			transactions.add(new Transaction((this.amount - amount)*(-1)));
		}
		this.amount = amount;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (o instanceof Account){
			if (((Account) o).getName().equals(this.getName()) && ((Account) o).getAmount() == this.getAmount()) {
				return true;
			}
		}
		return false;
	}
	
}
