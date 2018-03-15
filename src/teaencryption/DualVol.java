package teaencryption;


import java.security.SecureRandom;

public class DualVol extends Tea{

	// Inherit: private final static int DELTA = 0x9e3779b9;

	// Extends password from x character to the size of volume that's being encrypted
	// Output is String extended to the size of volume
	private char[] passExtend(String password, int size){
		// set return char array size of "int size"
		char[] retArr = new char[size];

		retArr = password % size;


	} // end passExtend()

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
	}

	// Encrypt the first "fake" volume
	// Output is volume (String)
	private String firstEncrypt(char[] Volume, String key){}

	// Encrypt the second "real volume"
	// after the first volume has been encrypted
	private String secondEncrypt(char[] Volume, String key, int offset){}

	// Decrypt volume, it doesn't differentiate between real or fake volume
	private String decryptVol(char[] Volume, String key, int offset){}

}
