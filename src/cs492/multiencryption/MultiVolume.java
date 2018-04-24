package cs492.multiencryption;




public class MultiVolume {

	// Inherit: private final static int DELTA = 0x9e3779b9;



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



} // end MultiVolume()
