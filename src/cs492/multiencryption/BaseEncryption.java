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
		// set return char array size of "int size"
		char[] retArr = new char[size];
		// Cast the size into BigInteger
		BigInteger bigSize = BigInteger.valueOf(size);
		// Cast the string as BigInteger
		BigInteger bigInt = new BigInteger(password);
		// Use mod to expand the password
		BigInteger modInt = bigInt.mod(bigSize);

	return retArr;

	} // end passExtend()



} // end class()
