package com.model.buckaroos;

/**
 * The "bank" accounts for user.
 * 
 * @author Gene
 * 
 */
public class Account {

    private String name;
    private double balance = 0;
    private double interestRate;
    private User user;
    private String nickName;

    public Account(String name, String nickName, double amount,
            double interestRate, User user) {
        this.user = user;
        this.setName(name);
        this.balance = amount;
        this.nickName = nickName;
        this.setInterestRate(interestRate);
    }

    /**
     * Returns balance of this account
     * 
     * @return
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets account balance
     * @param balance
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * get name of account
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * set name of account
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (o instanceof Account) {
            if (((Account) o).getName().equalsIgnoreCase(this.getName())
                    && ((Account) o).getBalance() == this.getBalance()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns interest rate
     * @return
     */
    public double getInterestRate() {
        return interestRate;
    }

    /**
     * Returns nickname
     * @return
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Sets nickname
     * @param nickName
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * Set interest rate
     * @param interestRate
     */
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    /**
     * Returns this account's user
     * @return
     */
    public User getUser() {
        return user;
    }

}
