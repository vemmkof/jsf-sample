package com.ipn.escom.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HashUtil {

	private static final Logger LOGGER = Logger.getLogger(HashUtil.class.getName());

	private HashUtil() {
	}

	public static String getHash(String password) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			final byte[] encodedhash = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
			return bytesToHex(encodedhash);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}

	private static String bytesToHex(byte[] hash) {
		StringBuilder hexString = new StringBuilder();
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xFF & hash[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}
}
