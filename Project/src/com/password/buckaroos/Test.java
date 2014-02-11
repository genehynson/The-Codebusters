package com.password.buckaroos;

public class Test {
	public static void main(String[] args) {
//		AppPropertyWriter k = new AppPropertyWriter();
		CredentialConfirmer test = new CredentialConfirmer();
		if (test.doesAccountExist("admin")) {
			System.out.println(test.isPasswordCorrect("admin", "pass123"));
		}
	}

}
