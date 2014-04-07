package com.tests.buckaroos;

import com.utility.buckaroos.*;
import static org.junit.Assert.*;
import org.junit.Test;
/**
 * Class defines a set of JUnit test cases to test the functionality of 
 * the Date Formatter object.
 *
 * @author Mohammad Muhtadin
 * @version 1.0
 *
 */
public class UserEqualsTest {
	
	private User nuUser;
	
	public UserEqualsTest(){
		nuUser = new User("userName", "password", "user123@email.com");
	}	
	@Test
	public void testUserName() {
			assertTrue(nuUser.equals(new User("userName", "password", "user123@email.com")));
			assertTrue(nuUser.equals(new User("UserName", "password", "user123@email.com")));
			assertFalse(nuUser.equals(new User("", "password", "user123@email.com")));
			assertFalse(nuUser.equals(new User("Dean", "password", "user123@email.com")));
	}
	@Test
	public void testPassword() {
			assertTrue(nuUser.equals(new User("userName", "password", "user123@email.com")));
			assertTrue(nuUser.equals(new User("userName", "Password", "user123@email.com")));
			assertTrue(nuUser.equals(new User("userName", "", "user123@email.com")));
			assertTrue(nuUser.equals(new User("userName", "123456", "user123@email.com")));
	}
	@Test
	public void testEmail() {
			assertTrue(nuUser.equals(new User("userName", "password", "user123@email.com")));
			assertTrue(nuUser.equals(new User("userName", "password", "User123@email.com")));
			assertTrue(nuUser.equals(new User("userName", "password", "USER123@email.com")));
			assertFalse(nuUser.equals(new User("userName", "password", "")));
			assertFalse(nuUser.equals(new User("userName", "password", "123@email.com")));
	}
}

