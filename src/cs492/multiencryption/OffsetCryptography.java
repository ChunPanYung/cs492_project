package cs492.multiencryption;


import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class OffsetCryptography extends SymmetricCryptography {

	// Encrypt the byte based on offset
	public static byte[] offsetEncrypt(byte[] txt, SecretKey key, IvParameterSpec iv,
	                                       int offset)
	       throws NoSuchPaddingException, NoSuchAlgorithmException,
	               InvalidAlgorithmParameterException, InvalidKeyException,
	               BadPaddingException, IllegalBlockSizeException {

		// Tested: this algorithm will give the same length of plaintext and ciphertext.
		Cipher c = Cipher.getInstance("AES/CTR/PKCS5Padding");
		c.init(Cipher.ENCRYPT_MODE, key, iv);

		// Get part txt based on offset
		byte[] partTxt = Arrays.copyOfRange(txt, offset, txt.length);
		partTxt = c.doFinal(partTxt);

		// return value
		return offsetCopy(partTxt, txt, offset);
		}

	// Encrypt the byte based on offset
	public static byte[] offsetDecrypt(byte[] txt, SecretKey key, IvParameterSpec iv,
																		 int offset)
					throws NoSuchPaddingException, NoSuchAlgorithmException,
					InvalidAlgorithmParameterException, InvalidKeyException,
					BadPaddingException, IllegalBlockSizeException {

		// Tested: this algorithm will give the same length of plaintext and ciphertext.
		Cipher c = Cipher.getInstance("AES/CTR/PKCS5Padding");
		c.init(Cipher.DECRYPT_MODE, key, iv);

		// Get part txt based on offset
		byte[] partTxt = Arrays.copyOfRange(txt, offset, txt.length);
		partTxt = c.doFinal(partTxt);

		// return value
		return offsetCopy(partTxt, txt, offset);
	}

	// Copy array src to array dst starting from offset.
	// It will replace part of array in dst, starting from offset
		private static byte[] offsetCopy(byte[] src, byte[] dst, int offset) {

		// Throw out of bound if it can't complete copy the src array to dst
			if ((dst.length - offset) != src.length) {
				System.out.println("src length: " + src.length);
				System.out.println("dst length: " + dst.length);
				throw new IndexOutOfBoundsException("It can't copy all the element in array!");
			}

			// copy using for loop loop
			for (int i = offset; i < dst.length; i++) {
				dst[i] = src[i - offset];
			}

			return dst;
		}



} // end class OffsetCryptography
