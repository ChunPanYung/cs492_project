package cs492.multiencryption;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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



	// Hashing password and return 128bits (long[2])
	// Output long array
	public static long[] passExtend(String text) throws NoSuchAlgorithmException {
		// Set algorithm to sha-256
		MessageDigest md = MessageDigest.getInstance( "SHA-256" );

		// Get the text as UTF-16BE
		md.update( text.getBytes( StandardCharsets.UTF_16BE ) );
		// Convert it to byte array
		byte[] digest = md.digest();

		// return long array
		return toLongArray(digest);
	}

	// Casting and concatenage byte[] into long[]
	// return long[]
	// It's asuume that char[] is in the muliplication of 8 (need improvement)
	private static long[] toLongArray(byte[] arr) {
		// Array should be in the number of 8x,
		// because long int is 8 bytes long
		// and char is 2 bytes long
		if (arr.length % 8 != 0) {
			throw new ArrayIndexOutOfBoundsException();
		} // end if statement

		// int i: length of return array == (arr.length / 8)
		int longSize = arr.length / 8;
		// Initializing long array
		long[] retVal = new long[longSize];

		// establish i as the last array index of long int,
		// and k as the last array index of char.
		int i = longSize - 1;
		int k = arr.length - 1;
		int byteInLong = 0;

		// use for loop to cast char[] into long[]
		while (i >= 0) {
			// For every long(64-bit), we put 8 bytes (8-bit each) into it
			while (byteInLong < 16) {
				// shift left by 8 bits
				retVal[i] = retVal[i] << 8;
				// Casting 8 bytes into single long int
				retVal[i] = retVal[i] & arr[k];
				// increment charInLong: it indicates how many char has cast into long so far
				byteInLong++;
				// decrease current index of char array
				k--;
			} // end while loop

			// Decrease i
			i--;
		} // end while loop

		return retVal;
	} // end toLongArray()

} // end class()
