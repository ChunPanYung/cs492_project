package cs492.multiencryption;

import org.junit.jupiter.api.*;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
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
		char[] password1 = "pancake".toCharArray(),
		       password2 = "pudding".toCharArray(),
		       password3 = "lolipop".toCharArray();
		// salt for each password
		byte[] salt1 = getSalt(),
		       salt2 = getSalt(),
		       salt3 = getSalt();
		// SecretKey (hash of both salt and password)
		SecretKey key1 = null,
		          key2 = null,
		          key3 = null;

		try { // hash and convert into SecretKey
			key1 = passwordHash(password1);
			key2 = passwordHash(password2);
			key3 = passwordHash(password3);
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

		char[] password = "pancake".toCharArray();
		byte[] salt = getSalt();
		SecretKey key = null;

		try { // generate key based on password and salt
			key = BaseEncryption.passwordHash(password);
		} catch (Exception e) {
			e.printStackTrace();
		} // end try...catch()

		// print info
		System.out.println("===");
		System.out.println("Begin Encryption");
		System.out.println("The Length of SecretKey is: " + key.getEncoded().length);
		System.out.println("The SecretKey Algorithm is: " + key.getAlgorithm());
		System.out.println("The salt before encryption: " + salt.toString());

		IvParameterSpec iv = BaseEncryption.getIV();
		byte[] plainText = CryptoUtil.strToByte("Unlimited Pancake Works!");

		CryptoData cipherText = null;


		try { // Encryption
			cipherText = BaseEncryption.encryptVolume(key, plainText, salt);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Encryption Failed!");
		}
		// End Encryption
		System.out.println("---");
		System.out.println("End Encryption");
		System.out.println("===\n\n");

		// Print info
		System.out.println("=== ");
		System.out.println("Begin Decryption");
		System.out.println("Password: " + password.toString());
		System.out.println("Plain Text: " + plainText);
		System.out.println("Cipher Text: " +
		                   CryptoUtil.byteToStr(cipherText.getCryptoByte()));
		System.out.println("The salt before decryption: " + salt.toString());
		System.out.println("---");

		// Decryption and print out
		CryptoData decryptText = null;
		try {
			decryptText = BaseEncryption.decryptVolume(key, cipherText);
		} catch (Exception e) {
			System.out.println("Decryption Failed!");
			e.printStackTrace();
		}

		// print info
		System.out.println("Decrypted Text: " +
		                   CryptoUtil.byteToStr(decryptText.getCryptoByte()));
		System.out.println("===");

	}






} // end BaseEncryptionTest()
