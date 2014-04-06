package com.model.buckaroos;

/**
 * This class defines an Account object to interact with the User object. It
 * constructs a "bank" account for a system user.
 *
 * @author Gene Hynson
 * @version 1.0
 */
public class Account {

    private String name;
    private double balance = 0;
    private double interestRate;
    private final User user;
    private String nickName;

    /**
     * Constructs an Account object.
     *
     * @param name The account's name.
     * @param nickName The account's nickname.
     * @param amount The account's initial balance.
     * @param interestRate The account's interest rate.
     * @param user The account's owner.
     */
    public Account(String name, String nickName, double amount,
            double interestRate, User user) {
        this.user = user;
        this.setName(name);
        this.balance = amount;
        this.nickName = nickName;
        this.setInterestRate(interestRate);
    }

    /**
     * Gets the account's current balance.
     *
     * @return The account's current balance.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the account's balance.
     *
     * @param balance The balance to be set for.
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * Gets the account's name.
     *
     * @return The name of this account.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the account's name.
     *
     * @param name The name to be set for.
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o instanceof Account) {
            if (((Account) o).getName().equalsIgnoreCase(this.getName())
                    && ((Account) o).getBalance() == this.getBalance()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the account's interest rate.
     *
     * @return The interest rate for this account.
     */
    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(balance);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(interestRate);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result =
                prime * result
                        + ((nickName == null) ? 0 : nickName.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    /**
     * Gets the account's nickname.
     *
     * @return The nickname of this account.
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Sets the account's nickname.
     *
     * @param nickName The account's nickname.
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * Sets the interest rate for this account.
     *
     * @param interestRate The interest rate to be set for.
     */
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    /**
     * Gets this account's user.
     *
     * @return The account's rightful owner.
     */
    public User getUser() {
        return user;
    }

}
