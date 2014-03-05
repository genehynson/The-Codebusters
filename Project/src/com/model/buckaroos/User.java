package com.model.buckaroos;

/**
 * Class designed to store the user's credentials
 * 
 * @author Jordan
 * @version 1.0
 */
public class User {

    private String accountName;
    private String password;
    private String email;

    public User(String accountName, String password, String email) {
        this.accountName = accountName;
        // PasswordHash hasher = new PasswordHash();
        // this._password = hasher.hashPassword(password);
        this.password = password;
        this.email = email;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        // PasswordHash hasher = new PasswordHash();
        // this._password = hasher.hashPassword(_password);
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (o instanceof Account) {
            if (((User) o).getAccountName().equalsIgnoreCase(
                    this.getAccountName())
                    && ((User) o).getEmail() == this.getEmail()) {
                return true;
            }
        }
        return false;
    }

}