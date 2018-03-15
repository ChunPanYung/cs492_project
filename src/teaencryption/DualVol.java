package teaencryption;


import java.security.SecureRandom;

public class DualVol extends Tea{

	// Inherit: private final static int DELTA = 0x9e3779b9;

	// Extends password from x character to the size of volume that's being encrypted
	// Output is String extended to the size of volume
	private String passExtend(String password, int size){


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
	public String firstEncrypt(String Volume, String key){}

	// Encrypt the second "real volume"
	// after the first volume has been encrypted
	public String secondEncrypt(String Volume, String key){}

	// Decrypt volume, it doesn't differentiate between real or fake volume
	public String decryptVol(String Volume, String key){}

}
