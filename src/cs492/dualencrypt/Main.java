/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs492.dualencrypt;

import cs492.teaencryption.Tea;

/**
 *
 * @author chun pan yung
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
	
	
	// test here	
	test(01, "0xAF6BABCDEF00F000FFFFFFFFABCDEF01", "0x01CA456789ABCDEF");
	test(02, "0xa56babcdf000ffffffffffffabcdef01", "0x123456789abcdef");
	test(03, "0xa56babcdffffffffffffffffabcdef01", "0x123456789abcdef");
	
    } // end main()
    
    private static void test(int i, String key, String plainText) {
	
	System.out.println("TEST CASE " + i);
	
	// first print both key and Plaintext
	System.out.println("Plaintext: " + plainText);
	System.out.println("Key: " + key);
	
	// Get Ciphertext
	String cipher = Tea.encrypt(plainText, key);
	
	// Then print Ciphertext, and decrypted ciphertext
	System.out.println("\nCipherText: " + cipher);
	System.out.println("Decrypted CipherText: " + Tea.decrypt(cipher, key));
	System.out.print("\n\n");
	
	
    } // end test()
   
    
}
