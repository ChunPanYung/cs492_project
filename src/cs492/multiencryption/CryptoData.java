package cs492.multiencryption;

import javax.crypto.Cipher;
import javax.crypto.spec.PBEParameterSpec;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * This class is used to pass encrypted/decrypted data
 * As well as Algorithm used for the said crypto data
 */
public class CryptoData {



	private byte[] cryptoByte;
	private byte[] params;

	public CryptoData() {
	 this.cryptoByte = null;
	 this.params = null;
	} // end Constructor()

	// byte[] txt: encrypted/decrypted message
	public CryptoData(byte[] txt) {
		this.cryptoByte = txt;
		this.params = null;
	} // end Constructor()

	// byte[] txt: encrypted/decrypted message
	// AlgorithmParameters params: AlgorithmParameters
	public CryptoData(byte[] txt, byte[] params) {
		this.cryptoByte = txt;
		this.params = params;
	} // end Constructor()

	// byte[] txt: encrypted/decrypted message
	// AlgorithmParameters params: AlgorithmParameters
	public CryptoData(byte[] txt, Cipher c) throws IOException {
		this.cryptoByte = txt;
		setParams(c);
	} // end Constructor()


	// Return [encrypted/decrypted] message
	public byte[] getCryptoByte() {
		return cryptoByte;
	}

	// Return part of message based on offset
	public byte[] getCryptoByte(int offset) {
	return Arrays.copyOfRange(cryptoByte, offset, cryptoByte.length);
	}

	// Return [encrypted/decrypted] message
	public void setCryptoByte(byte[] arr) {
		cryptoByte = arr;
	}

	// Return part of message based on offset
	public void setCryptoByte(byte[] arr, int offset) {

		// Throw exception if the whole array can't be copied
		if (arr.length + offset != cryptoByte.length)
			throw new IndexOutOfBoundsException("Can't copy the whole array!");

		// use for loop to copy
		while ( (offset < cryptoByte.length) && (offset < arr.length) ) {
			cryptoByte[offset] = arr[offset];
			offset++;
		}
	}



	public void setParams(Cipher c) throws IOException {
		this.params = c.getParameters().getEncoded();
	}

	public AlgorithmParameters getParams() throws NoSuchAlgorithmException,
	                                              IOException {
		AlgorithmParameters algorParams =
			AlgorithmParameters.getInstance(BaseCryptography.getAlgorithm());

		algorParams.init(params);

		return algorParams;
	}

	public byte[] getParamsArr() {
		return this.params;
	}



} // end CryptoData class
