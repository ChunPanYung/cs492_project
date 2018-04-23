package cs492.teaencryption;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeaModTest {


	@Test
	void testingTeaMod() throws Exception {



		test(01, "AF6BABCDEF00F000FFFFFFFFABCDEF01", "01CA4567890BCDEF");
		test(02, "a56babcdf000ffffffffffffabcdef01", "0123456789abcdef");
		test(03, "a56babcdffffffffffffffffabcdef01", "0123456789abcdef");



	}

	@Test
	void testToHexString() {


	}


	private static void test(int i, String key, String plainText) throws Exception {

		System.out.println("TEST CASE " + i);

		// first print both key and Plaintext
		System.out.println("Plaintext: " + plainText);
		System.out.println("Key: " + key);

		// Get Ciphertext
		String cipher = TeaMod.encrypt(plainText, key);

		// Then print Ciphertext, and decrypted ciphertext
		System.out.println("\nCipherText: " + cipher);
		System.out.println("Decrypted CipherText: " + TeaMod.decrypt(cipher, key));
		System.out.print("\n\n");


	} // end test()
}