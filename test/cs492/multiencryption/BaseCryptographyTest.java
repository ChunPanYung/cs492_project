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




public class BaseCryptographyTest {

	@Disabled
	@Test
	public void randomZeroes() {
		// Call method, convert char[] to string
		String pancake = String.valueOf(BaseCryptography.randomZeroes(100));
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
		byte[] salt1 = BaseCryptography.getSalt(),
		       salt2 = BaseCryptography.getSalt(),
		       salt3 = BaseCryptography.getSalt();
		// SecretKey (hash of both salt and password)
		SecretKey key1 = null,
		          key2 = null,
		          key3 = null;

		try { // hash and convert into SecretKey
			key1 = BaseCryptography.passwordHash(password1);
			key2 = BaseCryptography.passwordHash(password2);
			key3 = BaseCryptography.passwordHash(password3);
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
		byte[] pudding = "Pudding is the best dessert you can have! OwO".getBytes();

		BaseCryptography.saveVolume(pudding);
		BaseCryptography.loadVolume();

	}

	@Disabled
	@Test
	public void encryptTest() {

		char[] password = "pancake".toCharArray();
		byte[] salt = BaseCryptography.getSalt();
		SecretKey key = null;

		try { // generate key based on password and salt
			key = BaseCryptography.passwordHash(password);
		} catch (Exception e) {
			e.printStackTrace();
		} // end try...catch()


		IvParameterSpec iv = BaseCryptography.getIV(salt);
		byte[] plainText = CryptoUtil.strToByte("Unlimited Pancake Works!");

		CryptoData cipherText = new CryptoData(plainText);


		try { // Encryption
			cipherText = BaseCryptography.encryptVolume(key, cipherText, salt);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Encryption Failed!");
		}

		// Print info
		System.out.println("=== ");
		System.out.println("PlainText: " +
		                   CryptoUtil.byteToStr(plainText));
		System.out.println("PlainText length: " + plainText.length);

		System.out.println("Cipher Text: " +
		                   CryptoUtil.byteToStr(cipherText.getCryptoByte()));
		System.out.println("CipherText length: " + cipherText.getCryptoByte().length);
		System.out.println("---");

		// Decryption and print out
		CryptoData decryptText = null;
		try {
			decryptText = BaseCryptography.decryptVolume(key, cipherText, salt);
		} catch (Exception e) {
			System.out.println("Decryption Failed!");
			e.printStackTrace();
		}

		// print info
		System.out.println("Decrypted Text: " +
		                   CryptoUtil.byteToStr(decryptText.getCryptoByte()));
		System.out.println("Decrypted Text length: " + decryptText.getCryptoByte().length);
		System.out.println("===");

	}

	// This is used for testing SymmetricCryptography encryption method 1
	@Disabled
	@Test
	public void testEncryption1()
	       throws IllegalBlockSizeException,
	              InvalidKeyException, BadPaddingException, NoSuchAlgorithmException,
	              NoSuchPaddingException {

		// password and plaintext
		char[] password = "pudding".toCharArray();
		String str = "Unlimited Pancake Works!";

		CryptoData data = new CryptoData(CryptoUtil.strToByte(str));
		SecretKey key = null;

		try {
			key = BaseCryptography.passwordHash(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
		}

		data = testCryptoMethod.encryptTry1(key, data);

		System.out.println("The plaintext length is: " + str.length());
		System.out.println("The ciphertext length is: " + data.getCryptoByte().length);
		System.out.println("The ciphertext is: " + data.getCryptoByte().toString());

	}

	// This is used for testing SymmetricCryptography encryption method 2
	@Disabled
	@Test
	public void testEncryption2()
	       throws NoSuchPaddingException, InvalidKeyException,
	              NoSuchAlgorithmException, IllegalBlockSizeException,
	              BadPaddingException, InvalidAlgorithmParameterException {

		encryption2("pudding".toCharArray(), "Unlimited Pancake Works!");
		encryption2("pancake".toCharArray(), "I can't live without Syrup!");
		encryption2("lollipop".toCharArray(), "Excalibur");


	}


	// for testEncryption2() method
	private void encryption2(char[] password, String str)
	       throws NoSuchPaddingException, InvalidKeyException,
						NoSuchAlgorithmException, IllegalBlockSizeException,
						BadPaddingException, InvalidAlgorithmParameterException {

		CryptoData data = new CryptoData(CryptoUtil.strToByte(str));


		data = testCryptoMethod.encryptTry2(data);

		System.out.println("The plaintext length is: " + str.length());
		System.out.println("The ciphertext length is: " + data.getCryptoByte().length);
		System.out.println("The ciphertext is: " + data.getCryptoByte().toString());
	}

	@Test
	public void testEncryptTry3() throws InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {

		// password
		char[] password1 = "pancake".toCharArray();

		// Get salt
		byte[] salt = BaseCryptography.getSalt();
		// Get key
		SecretKey key = SymmetricCryptography.passwordHash(password1, salt);

		// print out info
		encryption3(key, salt, "Pudding Art Online");


	}

	// for testEncryption2() method
	private void encryption3(SecretKey key, byte[] iv, String str)
					throws NoSuchPaddingException, InvalidKeyException,
					NoSuchAlgorithmException, IllegalBlockSizeException,
					BadPaddingException, InvalidAlgorithmParameterException {

		CryptoData data = new CryptoData(CryptoUtil.strToByte(str));

		data = testCryptoMethod.encryptTry3(data, key, iv);



		System.out.println("The plaintext is: " + str);
		System.out.println("The plaintext length is: " + str.length());
		System.out.println("The ciphertext length is: " + data.getCryptoByte().length);
		CryptoUtil.printByte(data.getCryptoByte());
	}

}
