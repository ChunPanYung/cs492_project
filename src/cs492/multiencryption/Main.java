package cs492.multiencryption;

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

/**
 * This class served as commandline interface for using:
 * SymmetricCryptography.java, CrypotoUtil.java and its parent classes
 */

public class Main {


	public static void main(String[] args) {

		// if no args, exit program
		if (args.length == 0) {
			System.out.println("No args given, exit.");
			System.exit(0);
		}


		/*
		   Options for args:
		   --encrypt : encryption using password and salt
		   --decrypt : decryption using password and salt
		   --create  : create a volume

		   Both encryption and decryption can have up to two args.
		   First one indicates password for encryption/decryption,
		   second one indicates offset (hidden partition).
		 */




		if (args[0].equals("--create")) {

			create(args);

		} else if (args[0].equals("--decrypt")) {

			try {
				encrypt(args);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (args[0].equals("--encrypt")) {

			try {
				decrypt(args);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}




	}

	// Create a volume contains random zeroes,
	// then save it as file, it also creates random salt and save it as file
	private static void create(String[] args) {

		// Create a volume that fills with random zeroes
		byte[] b = BaseCryptography.randomZeroes(100);
		// Get salt and save it into file
		byte[] salt = BaseCryptography.getSalt();
		// Save both volume and salt
		try {
			BaseCryptography.saveVolume(b);
			SymmetricCryptography.saveSalt(salt);
		} catch (IOException e) {
			e.printStackTrace();
		} // end try ...catch()


	}

	// Up to 3 args[] from parameter:
	// First one indicates what method is being called ("--encrypt");
	// Second one indicates password;
	// Third one indicates offset (optional).
	private static void encrypt(String[] args)
	        throws IOException, InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {

		// Preparation: load salt and volume, then process info to get SecretKey and iv
		byte[] txt = BaseCryptography.loadVolume(); // load volume
		byte[] salt = SymmetricCryptography.loadSalt(); // load salt
		SecretKey key = SymmetricCryptography.passwordHash(args[1].toCharArray(),
		                                                   salt);
		IvParameterSpec iv = BaseCryptography.getIV(salt);

		byte[] volume = null;
		// if the length is only 2 (no offset is given)
		if (args.length == 2) {
			volume = SymmetricCryptography.encryptVolume(txt, key, iv);
		// if offset is given
		} else if (args.length == 3) {
			volume = OffsetCryptography.offsetEncrypt(txt, key, iv, Integer.parseInt(args[2]));
		}

		BaseCryptography.saveVolume(volume);
	}

	private static void decrypt(String[] args)
	        throws IOException, InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {

			// Preparation: load salt and volume, then process info to get SecretKey and iv
			byte[] txt = BaseCryptography.loadVolume(); // load volume
			byte[] salt = SymmetricCryptography.loadSalt(); // load salt
			SecretKey key = SymmetricCryptography.passwordHash(args[1].toCharArray(),
							salt);
			IvParameterSpec iv = BaseCryptography.getIV(salt);

			byte[] volume = null;
			// if the length is only 2 (no offset is given)
			if (args.length == 2) {
				volume = SymmetricCryptography.decryptVolume(txt, key, iv);
				// if offset is given
			} else if (args.length == 3) {
				volume = OffsetCryptography.offsetDecrypt(txt, key, iv, Integer.parseInt(args[2]));
			}

			BaseCryptography.saveVolume(volume);

	}

}
