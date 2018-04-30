package cs492.multiencryption;

import javax.crypto.Cipher;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;
/**
 * This class is used to pass encrypted/decrypted data
 * As well as Algorithm used for the said crypto data
 *
 * This is only used for BaseCryptography.java,
 * it's not being used for any child class.
 */
public class CryptoData {

	private byte[] cryptoByte;
	private byte[] params;

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



} // end CryptoData class
