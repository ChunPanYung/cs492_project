package cs492.multiencryption;

import java.util.ArrayList;

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

}
