package com.ipn.escom.security;

import java.security.SecureRandom;

import org.mindrot.jbcrypt.BCrypt;

public class HashUtil {

	private static final String SECRET_KEY = "S3CR3TK3Y";

	private HashUtil() {
	}

	public static String getHash(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(10, new SecureRandom(SECRET_KEY.getBytes())));
	}

	public static boolean match(String password, String securedPassword) {
		return BCrypt.checkpw(password, securedPassword);
	}

}
