package cs492.dualencrypt;


import cs492.teaencryption.Tea;

import java.math.BigInteger;
import java.security.SecureRandom;

public class DualVol extends Tea {

	// Inherit: private final static int DELTA = 0x9e3779b9;

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



	} // end passExtend()

	// Divide a char (16-bit) primitive data type into 2 bytes (1 byte == 8 bits)
	//
	// Return: byte[2]
	private byte[] divChar(char c) {
		// Get the rightmost 8-bit
		byte rightmost = (byte) c;
		// Get the leftmost 8-bit
		byte leftmost = (byte) (c >>> 8);

		// return them
		byte[] retVal = {leftmost, rightmost};
		return retVal;

	} // end divChar

	// merge 2 bytes into a single char
	//
	// Return: char
	private char mergeBytes(byte[] b) {
		// return null if (array != 2)
		if (b.length != 2) {
			throw new ArrayIndexOutOfBoundsException("mergeBytes(): (byte[] b != 2)");
		} // end if statement

		// leftmost 8-bit of char is on byte[0]; rightmost 8-bit of char is on byte[1]
		char c = (char) b[0];
		// shift to left 8 times
		c = (char) (c << 8);
		// use "or" operation to get a complete char
		c = (char) (c | b[1]);

		return c;

	} // end mergeBytes()


	// Generate random number according to size
	// Output: randomly generated volume
	private char[] createVol (int size){
		// Create array according to size
		char[] array = new char[size];

		// Initializing number generator
		SecureRandom sr = new SecureRandom();

		// Fill up the whole array
		for (char pudding : array) {
			pudding = (char) sr.nextInt();

		} // end for loop

		return array;
	} // end createVol()

	// Encrypt the first "fake" volume
	// Output is volume (String)
	public String firstEncrypt(char[] Volume, String key){
		//


		return null;
	}

	// Encrypt the second "real volume"
	// after the first volume has been encrypted
	public String hiddenEncrypt(char[] Volume, String key, int offset){

		return null;
	}

	// Decrypt volume, it doesn't differentiate between real or fake volume
	public String decryptVol(char[] Volume, String key, int offset){

		return null;
	}

	// BigInteger: Unsigned shift right
	private BitInteger bitIntUnsignedShiftRight (BigInteger bigInt, int num) {


	}


} // end DualVol()
