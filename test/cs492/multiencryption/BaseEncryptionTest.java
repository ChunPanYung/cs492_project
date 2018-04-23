package cs492.multiencryption;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;


public class BaseEncryptionTest {

	@Disabled
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
	public void passwordHash() throws InvalidKeySpecException, NoSuchAlgorithmException {
		// List of char array as password
		char[] password_1 = {'P', 'a', 'n', 'c', 'a', 'k', 'e'},
		       password_2 = {'P', 'u', 'd', 'd', 'i', 'n', 'g'},
		       password_3 = {'L', 'o', 'l', 'l',  'i', 'p', 'o', 'p'};

		// Generate password and store their output :3
		String pancake = BaseEncryption.passwordHash(password_1);
		String pudding = BaseEncryption.passwordHash(password_2);
		String lollipop = BaseEncryption.passwordHash(password_3);

		// Print password and its length
		System.out.println("Password: " + pancake);
		System.out.println("Length: " + pancake.length());

		System.out.println("Password: " + pudding);
		System.out.println("Length: " + pudding.length());

		System.out.println("Password: " + lollipop);
		System.out.println("Length: " + lollipop.length());


	}

	@Disabled
	@Test
	public void writeRead() throws IOException {
		String pudding = "Pudding is the best dessert you can have! OwO";

		BaseEncryption.saveVolume(pudding);
		BaseEncryption.loadVolume("Pudding.txt");

	}



	@Test
	public void encryptTest() throws InvalidKeySpecException, NoSuchAlgorithmException {

		// List of char array as password
		char[] password = {'P', 'a', 'n', 'c', 'a', 'k', 'e'};
		// Generate password and store their output :3
		String key = BaseEncryption.passwordHash(password);

		// Use randomZeroes() as hash for testing
		char[] txt = (BaseEncryption.randomZeroes(100));
		// print hash
		System.out.println(txt);

		System.out.println("-----------");

		ArrayList<Character> charList = BaseEncryption.encryptVolume(txt, key);

		// Print
		System.out.println(charList.size());




	}






}
