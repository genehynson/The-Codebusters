package com.tests.buckaroos;

import static org.junit.Assert.*;

import org.junit.Test;

import com.utility.buckaroos.Account;


public class AccountEqualsTest {
	
	
	private Account a;
	
	public AccountEqualsTest() {
		a = new Account("username", "name", "nickName", 100, 5);
	}
	
	@Test
	public void testAccountsDifferentBalance() {
		assertTrue(a.equals(new Account("username", "name", "nickName", 200, 5)));
		assertTrue(a.equals(new Account("username", "name", "nickName", 100, 5)));
		assertTrue(a.equals(new Account("username", "name", "nickName", -100, 5)));
		assertTrue(a.equals(new Account("username", "name", "nickName", 0, 5)));

	}
	
	@Test
	public void testAccountDifferentName() {
		assertFalse(a.equals(new Account("username", "bob", "nickName", 100, 5)));
		assertFalse(a.equals(new Account("username", "", "nickName", 100, 5)));
		assertTrue(a.equals(new Account("username", "Name", "nickName", 100, 5)));
		assertTrue(a.equals(new Account("username", "name", "nickName", 100, 5)));
		
	}
	
	@Test
	public void testAccountDifferentInterestRate() {
		assertTrue(a.equals(new Account("username", "name", "nickName", 100, 4)));
		assertTrue(a.equals(new Account("username", "name", "nickName", 100, 5)));
		assertTrue(a.equals(new Account("username", "name", "nickName", 100, 0)));
		assertTrue(a.equals(new Account("username", "name", "nickName", 100, -5)));

	}
	
	@Test
	public void testAccountDifferentUsername() {
		assertFalse(a.equals(new Account("joe", "name", "nickName", 100, 5)));
		assertFalse(a.equals(new Account("", "name", "nickName", 100, 5)));
		assertTrue(a.equals(new Account("Username", "name", "nickName", 100, 5)));
		assertTrue(a.equals(new Account("username", "name", "nickName", 100, 5)));

	}
	
	@Test
	public void testAccountDifferentNickName() {
		assertTrue(a.equals(new Account("username", "name", "chicken", 100, 5)));
		assertTrue(a.equals(new Account("username", "name", "nickName", 100, 5)));
		assertTrue(a.equals(new Account("username", "name", "", 100, 5)));
		assertTrue(a.equals(new Account("username", "name", null, 100, 5)));

	}

}
