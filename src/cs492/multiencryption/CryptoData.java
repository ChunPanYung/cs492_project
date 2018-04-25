package cs492.multiencryption;

import javax.crypto.Cipher;
import javax.crypto.spec.PBEParameterSpec;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;

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


	public byte[] getCryptoByte() {
		return cryptoByte;
	}

	public void setCryptoByte(byte[] cryptoByte) {
		this.cryptoByte = cryptoByte;
	}

	public void setParams(Cipher c) throws IOException {
		this.params = c.getParameters().getEncoded();
	}

	public AlgorithmParameters getParams() throws NoSuchAlgorithmException,
	                                              IOException {
		AlgorithmParameters algorParams =
			AlgorithmParameters.getInstance(BaseEncryption.getAlgorithm());

		algorParams.init(params);

		return algorParams;
	}

	public byte[] getParamsArr() {
		return this.params;
	}



} // end CryptoData class
