package cs492.multiencryption;

import org.junit.jupiter.api.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;


public class BaseEncryptionTest {

	@Disabled("Tested randomZeroes()")
	@Test
	public void randomZeroes() {
		// Call method, convert char[] to string
		String pancake = String.valueOf(BaseEncryption.randomZeroes(100));
		// Print
		System.out.println("The length is " + pancake.length());

		System.out.println(pancake);

	}

	@Disabled
	@Test
	public void getNextSalt() {
		// Get an array of salt
		byte[] pancake = BaseEncryption.getNextSalt();
		System.out.println(Arrays.toString(pancake));

	}

	@Test
	public void passwordHash() throws InvalidKeySpecException, NoSuchAlgorithmException {
		// List of char array as password
		char[] password_1 = {'P', 'a', 'n', 'c', 'a', 'k', 'e'},
		       password_2 = {'P', 'u', 'd', 'd', 'i', 'n', 'g'},
		       password_3 = {'L', 'o', 'l', 'l',  'i', 'p', 'o', 'p'};

		// Generate password and store their output :3
		String pancake = BaseEncryption.passwordHash(password_1, BaseEncryption.getNextSalt());
		String pudding = BaseEncryption.passwordHash(password_2, BaseEncryption.getNextSalt());
		String lollipop = BaseEncryption.passwordHash(password_3, BaseEncryption.getNextSalt());

		// Print password and its length
		System.out.println("Password: " + pancake);
		System.out.println("Length: " + pancake.length());

		System.out.println("Password: " + pudding);
		System.out.println("Length: " + pudding.length());

		System.out.println("Password: " + lollipop);
		System.out.println("Length: " + lollipop.length());


	}

	@Test
	public void passExtend() {
	}






}