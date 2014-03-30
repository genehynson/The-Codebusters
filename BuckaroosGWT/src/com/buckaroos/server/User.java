package com.buckaroos.server;

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
        this.password = password;
        this.email = email;
    }

    /**
     * Return login account name
     * @return
     */
	public String getAccountName() {
        return accountName;
    }

	/**
	 * Set login account name
	 * @param accountName
	 */
	public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

	/**
	 * Return password
	 * @return
	 */
	public String getPassword() {
        return password;
    }
	
	/**
	 * Set login password
	 * @param password
	 */
    public void setPassword(String password) {
        this.password = password;
    }

   /**
    * Returns user's email
    * @return
    */
	public String getEmail() {
        return email;
    }

	/**
	 * Sets user email
	 * @param email
	 */
	public void setEmail(String email) {
        this.email = email;
    }

	@Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;
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