package cs492.multiencryption;


import javax.crypto.*;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class OffsetCryptography extends BaseCryptography {

	// Encrypt the byte based on offset
	public static CryptoData offsetEncrypt(SecretKey key, CryptoData data,
	                                       byte[] salt, int offset)
					throws NoSuchPaddingException, NoSuchAlgorithmException, IOException,
	               InvalidAlgorithmParameterException, InvalidKeyException,
	               BadPaddingException, IllegalBlockSizeException {

		AlgorithmParameters params;

		// if data.getParams() is null,
		if (data.getParams() == null) {
			params = getEncryptCipher(key, salt);
		} else { // else use data.params
			params = data.getParams();
		}

		// Specify algorithm
		Cipher pbeCipher = Cipher.getInstance(ALGORITHM);
		// Initializing it with PBE Cipher with key and parameters
		pbeCipher.init(Cipher.DECRYPT_MODE, key, params);
		// Encrypt starts from offset
		byte[] partEncrypt = pbeCipher.doFinal(data.getCryptoByte(offset));

		data.setCryptoByte(partEncrypt, offset);

		return data;

	} // end offsetEncrypt




} // end class OffsetCryptography
