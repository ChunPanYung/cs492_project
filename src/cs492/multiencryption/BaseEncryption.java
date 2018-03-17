package cs492.multiencryption;

import java.math.BigInteger;
import java.security.SecureRandom;

public class BaseEncryption {

	// Generate random number according to size
	// Output: randomly generated volume
	public static char[] randomZeroes (int size) {
		// Create array according to size
		char[] array = new char[size];

		// Initializing number generator
		SecureRandom sr = new SecureRandom();

		// Fill up the whole array
		for (char pudding : array) {
			pudding = (char) sr.nextInt();

		} // end for loop

		return array;
	} // end randomZeroes()






	// Extends password from x character to the size of volume that's being encrypted
	// Output is String extended to the size of volume
	private char[] passExpand(String password, int size){
		// Cast the size into BigInteger
		BigInteger bigSize = BigInteger.valueOf(size);
		// Cast the string as BigInteger
		BigInteger bigInt = new BigInteger(password);
		// Use mod to expand the password
		BigInteger modInt = bigInt.mod(bigSize);

		// set return char array size of "int size"
		char[] retArr = new char[size];
		// Get the max size(value) of char
		char maxValue = (char) -1;
		// set a char to max value for AND operation to get the last 16 bit of modInt
		BigInteger maxCharSize = new BigInteger("-1");

		// put the value from modInt into array retArr
		for (int i = size - 1; i > -1; i--) {
			// put the modInt into retArr array using shifting operation
			retArr[i] = (char) modInt.mod(maxCharSize);
		} // end for loop


	return retArr;

	} // end passExtend()

	// Get a character and shift them left/right by x amount
	//
	// Input: single character, size to shift, boolean (true means shift right, false means shift left)
	// Output: bit that's being shift out, shifted single character



} // end class()
