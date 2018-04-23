package cs492.teaencryption;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeaTest {

	@Disabled
	@Test
	void testingTea() {

		test(01, "0xAF6BABCDEF00F000FFFFFFFFABCDEF01", "0x01CA456789ABCDEF");
		test(02, "0xa56babcdf000ffffffffffffabcdef01", "0x123456789abcdef");
		test(03, "0xa56babcdffffffffffffffffabcdef01", "0x123456789abcdef");


	}


	private static void test(int i, String key, String plainText) {

		System.out.println("TEST CASE " + i);

		// first print both key and Plaintext
		System.out.println("Plaintext: " + plainText);
		System.out.println("Key: " + key);

		// Get Ciphertext
		String cipher = Tea.encrypt(plainText, key);

		// Then print Ciphertext, and decrypted ciphertext
		System.out.println("\nCipherText: " + cipher);
		System.out.println("Decrypted CipherText: " + Tea.decrypt(cipher, key));
		System.out.print("\n\n");


	} // end test()
}