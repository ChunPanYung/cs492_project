package cs492.multiencryption;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Arrays;

import static cs492.multiencryption.CryptoUtil.stringToList;

public class BaseEncryption {

	// Class variables
	private static final SecureRandom RANDOM = new SecureRandom();
	private static final int KEYLEN = 256;
	private static final int ITERATION = 65536;
	private static final byte[] SALT = {13, 99, 69};


	// Generate random number according to size
	// Output: randomly generated volume
	public static char[] randomZeroes (int size) {
		// Create array according to size
		char[] array = new char[size];


		// char variable for storing random char generated by SecureRandom() temporarily
		char pudding;
		// fill the array with random number
		for (int i = 0; i < size; i++) {
			// Generate a random int and put it into pudding
			pudding = (char) RANDOM.nextInt();
			// Put the current pudding into array[index]
			array[i] = pudding;
		}

		return array;
	} // end randomZeroes()

	// output a string as text file (UTF-16)
	public static void saveVolume(String input) throws IOException {
		// Specify FileOutputStream
		FileOutputStream outputStream = new FileOutputStream("Pudding.txt");
		// Specify encoding
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-16BE");
		// BufferedWriter
		BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

		// Write file
		bufferedWriter.write(input);
		// close file
		bufferedWriter.close();


	}

	// Read file content (UTF-16)
	public static ArrayList<Character> loadVolume(String fileName) throws IOException {
		// Specify input stream
		FileInputStream inputStream = new FileInputStream(fileName);
		// Specify encoding
		InputStreamReader reader = new InputStreamReader(inputStream, "UTF-16BE");

		// Read character one by one and print them out on screen
		int character;
		ArrayList<Character> arrChar = new ArrayList<Character>(100);
		while ( (character = reader.read()) != -1 ) {
			arrChar.add((char) character);
		}
		// close file reader
		reader.close();

		// return
		return arrChar;

	} // end loadVolume

	// Get random salt
	static byte[] getSalt() {
		// use SecureRandom to generate bytes
		byte[] retVal = new byte[16];
		RANDOM.nextBytes(retVal);

		return retVal;
	} // end getSalt()


	// Hashing password and return 256 bits (long[2])
	// Output long array
	static SecretKey passwordHash(String password, byte[] salt)
		throws NoSuchAlgorithmException, InvalidKeySpecException {

		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATION, KEYLEN);
		SecretKeyFactory secretKeyFactory =
			SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

		return secretKeyFactory.generateSecret(spec);
	} // end passHash()

	// Get random IV
	static IvParameterSpec getIV() {
		// use SecureRandom to generate bytes
		byte[] retVal = new byte[16];
		RANDOM.nextBytes(retVal);

		return new IvParameterSpec(retVal);
	} // end getIV()



	public static byte[] encryptVolume(SecretKey key, String plainText,
	                                   IvParameterSpec iv) {

		Cipher cipher;
		byte[] retVal = null;


		try {

			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			cipher.doFinal(plainText.getBytes());

		} catch (Exception e) {
			e.printStackTrace();
		} // end try... catch()

		return retVal;

	} // end encryptVolume()

	// Decrypt the text using Tea Encryption and hashed password
	// The length of hash is 44

	// It will use the method from Tea.java to encrypt the txt
	//
	public static byte[] decryptVolume(SecretKey key, String cipherText,
	                                   IvParameterSpec iv) {

		Cipher decryption;
		byte[] retVal = null;

		try {

			decryption = Cipher.getInstance("AES/CBC/PKCS5Padding");
			decryption.init(Cipher.DECRYPT_MODE, key, iv);
			retVal = decryption.doFinal(cipherText.getBytes());

		} catch (Exception e) {
			e.printStackTrace();
		} // end try... catch()


		return retVal;
	} // end encryptVolume()
} // end class()
