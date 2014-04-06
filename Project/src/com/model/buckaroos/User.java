package com.model.buckaroos;

/**
 * This class defines an User object. Class designed to store the user's
 * credentials
 *
 * @author Jordan LeRoux
 * @version 1.0
 */
public class User {

    private String accountName;
    private String password;
    private String email;

    /**
     * Constructs the User object.
     *
     * @param accountName The user's account name.
     * @param password The user's password.
     * @param email The user's email.
     */
    public User(String accountName, String password, String email) {
        this.accountName = accountName;
        this.password = password;
        this.email = email;
    }

    /**
     * Gets the login account name.
     *
     * @return The user's account name.
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Sets the login account name.
     *
     * @param accountName The account name to be set.
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * Gets the user's password.
     *
     * @return The user's login account password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's login account password.
     *
     * @param password The user's login account password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user's login account email.
     *
     * @return The user's login account email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets user login account email.
     *
     * @param email The user's login account email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o instanceof User) {
            if (((User) o).getAccountName().equalsIgnoreCase(
                    this.getAccountName())
                    && ((User) o).getEmail() == this.getEmail()) {
                return true;
            }
        }
        return false;
    }

}
