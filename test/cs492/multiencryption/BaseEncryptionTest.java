package cs492.multiencryption;

import org.junit.jupiter.api.*;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

import static cs492.multiencryption.BaseEncryption.getSalt;
import static cs492.multiencryption.BaseEncryption.passwordHash;


public class BaseEncryptionTest {

	@Disabled
	@Test
	public void randomZeroes() {
		// Call method, convert char[] to string
		String pancake = String.valueOf(BaseEncryption.randomZeroes(100));
		// Print
		System.out.println("The length is " + pancake.length());

		System.out.println(pancake);

	}

	@Disabled
	@Test
	public void testPasswordHash() throws InvalidKeySpecException, NoSuchAlgorithmException {
		// List of char array as password
		String password1 = "pancake",
		       password2 = "pudding",
		       password3 = "lolipop";
		// salt for each password
		byte[] salt1 = getSalt(),
		       salt2 = getSalt(),
		       salt3 = getSalt();
		// SecretKey (hash of both salt and password)
		SecretKey key1 = null,
		          key2 = null,
		          key3 = null;

		try { // hash and convert into SecretKey
			key1 = passwordHash(password1, salt1);
			key2 = passwordHash(password2, salt2);
			key3 = passwordHash(password3, salt3);
		} catch (Exception e) {
			e.printStackTrace();
		} // end try ...catch()

		// Convert them into string
		String pancake = CryptoUtil.keyToStr(key1);
		String pudding = CryptoUtil.keyToStr(key2);
		String lollipop = CryptoUtil.keyToStr(key3);


		System.out.println("===");

		// Print password and its length
		System.out.println("Password: " + pancake);
		System.out.println("Length: " + pancake.length());

		System.out.println("\nPassword: " + pudding);
		System.out.println("Length: " + pudding.length());

		System.out.println("\nPassword: " + lollipop);
		System.out.println("Length: " + lollipop.length());

		System.out.println("===");
	}




	@Disabled
	@Test
	public void writeRead() throws IOException {
		String pudding = "Pudding is the best dessert you can have! OwO";

		BaseEncryption.saveVolume(pudding);
		BaseEncryption.loadVolume("Pudding.txt");

	}


	@Test
	public void encryptTest() {

		String password = "pancake";
		byte[] salt = getSalt();
		SecretKey key = null;

		try { // generate key based on password and salt
			key = BaseEncryption.passwordHash(password, salt);
		} catch (Exception e) {
			e.printStackTrace();
		} // end try...catch()

		// print info
		System.out.println("===");
		System.out.println("The Length of SecretKey is: " + key.getEncoded().length);
		System.out.println("The SecretKey Algorithm is: " + key.getAlgorithm());
		System.out.println("===\n");

		IvParameterSpec iv = BaseEncryption.getIV();
		String plainText = "Unlimited Pancake Works!";

		byte[] cipherText = BaseEncryption.encryptVolume(key, plainText, iv);

		// Print info
		System.out.println("===");
		System.out.println("Password: " + password);
		System.out.println("Plain Text: " + plainText);
		System.out.println("Cipher Text: " + CryptoUtil.byteArrToStr(cipherText));
		System.out.println("---");

		// Decryption and print out
		byte[] decryptText = BaseEncryption.decryptVolume(key,
		                     CryptoUtil.byteArrToStr(cipherText), iv);

		// print info
		System.out.println("Decrypted Text: " + CryptoUtil.byteArrToStr(decryptText));
		System.out.println("===");

	}






} // end BaseEncryptionTest()
