package com.ipn.escom.security;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class HashUtilTest {

	@Test
	public void testGetHash() throws Exception {
		final String password = "abc123??";
		final String hash1 = HashUtil.getHash(password);
		final String hash2 = HashUtil.getHash(password);
		assertNotEquals(hash1, hash2);
		assertNotEquals(password, hash1);
		assertTrue(HashUtil.match(password, hash1));
		assertTrue(HashUtil.match(password, hash2));
	}

}
