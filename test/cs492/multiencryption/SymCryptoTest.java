package cs492.multiencryption;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class SymCryptoTest {

	@Disabled
	@Test
	public void testCrypto() throws InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {

		char[] s1 = "pudding".toCharArray();
		byte[] txt = "Unlimited Pancake Workd!".getBytes();

		byte[] salt = BaseCryptography.getSalt(); // salt
		SecretKey key = SymmetricCryptography.passwordHash(s1, salt); // get key
		IvParameterSpec iv = BaseCryptography.getIV(salt);

		// Encryption and print
		byte[] ciphertext = SymmetricCryptography.encryptVolume(txt, key, iv);
		CryptoUtil.printByte(ciphertext);
		CryptoUtil.printByteLength(ciphertext);

		byte[] plaintext = SymmetricCryptography.decryptVolume(ciphertext, key, iv);
		CryptoUtil.printByte(plaintext);
		CryptoUtil.printByteLength(plaintext);
	}

	@Test
	public void testOffsetCrypto() throws InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException, IOException {

		char[] s1 = "pudding".toCharArray();
		byte[] txt = "Unlimited Pancake Workd!".getBytes();

		byte[] salt = BaseCryptography.getSalt(); // salt
		SecretKey key = SymmetricCryptography.passwordHash(s1, salt); // get key
		IvParameterSpec iv = BaseCryptography.getIV(salt); // get iv

		// Encryption and print
		byte[] ciphertext = OffsetCryptography.offsetEncrypt(txt, key, iv, 10);
		CryptoUtil.printByte(ciphertext);
		CryptoUtil.printByteLength(ciphertext);

		byte[] plaintext = OffsetCryptography.offsetDecrypt(ciphertext, key, iv, 10);
		CryptoUtil.printByte(plaintext);
		CryptoUtil.printByteLength(plaintext);





	}





}
