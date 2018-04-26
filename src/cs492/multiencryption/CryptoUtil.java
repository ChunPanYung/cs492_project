package cs492.multiencryption;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;

/**
 * Contains Utility function for BaseEncryption.java
 */
public class CryptoUtil {

	// Convert the ArrayList<Character> into String
	public static String listToString(ArrayList<Character> arr) {

		// Initialize the StringBuilder with the size of ArrayList
		StringBuilder sb = new StringBuilder(arr.size());
		for (char c : arr) {
			sb.append(c);
		}

		return sb.toString();
	} // end listToString()

	// Convert the String into ArrayList<Character>
	public static ArrayList<Character> stringToList(String str) {

		// Get the string length so it doesn't need to calculate twice
		int strLen = str.length();
		ArrayList<Character> arrList = new ArrayList<Character>(strLen);

		// Cycle and add it one by one
		for (int i = 0; i < strLen; i++) {
			arrList.add(str.charAt(i));
		}
		return arrList;
	} // end stringToList()

	// Convert from byte[] to String
	public static String byteToStr(byte[] arr) {
		return new String(arr, StandardCharsets.UTF_8);
	} // end byteArrToStr()

	// Convert from String to byte[]
	public static byte[] strToByte(String s) {
	 return s.getBytes(StandardCharsets.UTF_8);
	}

	// Convert from SecretKey to String
	public static String keyToStr(SecretKey key) {

		return Base64.getEncoder().encodeToString(key.getEncoded());

	} // end keyToStr()

	// Convert the AlgorithmParameters to byte
	public static byte[] algorToByte(Cipher c) throws IOException {
		return c.getParameters().getEncoded();
	} // end getAl

// Convert the byte to AlgorithmParameters
	public static AlgorithmParameters byteToAlgor(byte[] b)
	       throws IOException, NoSuchAlgorithmException {

		AlgorithmParameters algor =
			AlgorithmParameters.getInstance(BaseCryptography.getAlgorithm());

		algor.init(b);

		return algor;
	} // end byteToAlgor()



} // end algorToByte()


