/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teaencryption;

import java.util.BitSet;

/**
 * This whole class is set a "static", much like Math class()
 * You can directly use its method without calling constructor first.
 * 
 * There's only two public methods: encrypt() and decrypt().
 * 
 * @author chun pan yung
 */
public class Tea {
    
    private final static int DELTA = 0x9e3779b9;
    
    // String key should be in the form of Hex: "0xAAA..."
    // plainText should be euqaled or less than 128
    //
    // If null return, error
    public static String encrypt(String plainText, String key) {
        
	// break the key into four parts
        int[] k = keyToArr(key);
	// break the string into two parts
	int[] txt = textToArr(plainText);
	
	// if they are length of 1, return empty string (error)
	if (txt.length == 1 || k.length == 1) 
	    return "";
	
	// Calculation
	int sum = 0;
	for (int i = 0; i < 32; i++) {
	    sum += DELTA;
	    txt[0] += (txt[1] << 4 + k[0]) ^ (txt[1] + sum) ^
		    (txt[1] >>> 5 + k[1]);
	    txt[1] += (txt[0] << 4 + k[2]) ^ (txt[0] + sum) ^
		    (txt[0] >>> 5 + k[3]);
	}
	
	// concatenate 
	return "0x" + Integer.toHexString(txt[0]) + Integer.toHexString(txt[1]);
	
        
    } // end of encrypt()
    
    public static String decrypt(String cipherText, String key) {
	// break the key into four parts
	int[] k = keyToArr(key);
	// break the string into two parts
	int[] txt = textToArr(cipherText);
	
	// if they are length of 1, return empty string (error)
	if (txt.length == 1 || k.length == 1) 
	    return "";
	
	int sum = DELTA << 5;
	for (int i = 0; i < 32; i++) {
	    txt[1] -= (txt[0] << 4 + k[2]) ^ (txt[0] + sum) ^
		    (txt[0] >>> 5 + k[3]);
	    txt[0] -= (txt[1] << 4 + k[0]) ^ (txt[1] + sum) ^
		    (txt[1] >>> 5 + k[1]);
	    sum -= DELTA;
	} // end for loop
	
	// concatenate
	return "0x" + Integer.toHexString(txt[0]) + Integer.toHexString(txt[1]);
    } // end decrypt()
    
    // This will transmute the String Key into 4 keys
    // each key consist of "0x" at the beginning, then letters(0-9, A-F)
    // Each letter is a Hex representation (4-bit).
    // Overall length should not exceed 34.
    //
    // if return array of 1, it means error
    private static int[] keyToArr(String key) {
	
	// get length
	int len = key.length();
	
	// intentionally set is as array of 4.
	// last one indicate error if -1
	int[] retValue = new int[4];
	
	
	// Since the String key is string, we can use substring to 
	// separate it into 4 string (ignore first two chars "0x")
	// then we can convert it into int type
	if (!"0x".equals(key.substring(0, 2))) {
	    int[] errorValue = new int[1];
	    return errorValue;
	// the length of key should not be greater than 34 (include the "0x" at
	// the front
	} else if (len > 34) {
	    int[] errorValue = new int[1];
	    return errorValue;
	}
	
	
	// index for ret.
	int i = 3;
	// while the length is greater than or equal to 10
	while (len >= 10) {
	    // parse the last 8 hex number into rest[i]
	    retValue[i] = (int) Long.parseLong(key.substring(len - 8, len), 16);
	    // decrease total len by 8
	    len -= 8;
	    // move the i to previous index
	    i--;
	}
	
	// by now if len is greater than 2
	// i should always be greater than zero,
	// add here just be safe
	if (len > 2 && i >= 0) {
	    // put the rest of string into rest[i] (hex form parsing)
	    retValue[i] = Integer.parseInt(key.substring(2, len), 16);
	}
	// If the i is not less than zero at the moment, 
	// we don't need to do anything because we initialize the array
	// as all zero.
	
	return retValue; 
    } // end of keyToArr();
    
    // convert 64-bit Plaintext into array
    // the format of str will be "0xaa..." (hex form)
    private static int[] textToArr(String str) {
	
	
	
	// intentionally initialize as 129, last index indicate error (if 1)
	int[] retValue = new int[2];
	
	// get the length of str
	int len = str.length();
	
	// early return and error detection
	// every string should starts with "0x"
	if (!"0x".equals(str.substring(0,2))) {
	    int[] errorValue = new int[1];
	    return errorValue;
	// the len should not exceed 18 ("0x" included)
	} else if (len > 18) {
	    int[] errorValue = new int[1];
	    return errorValue;
	}
	
	// for keep track of index
	int i = 1;
	// while the len is greater or equal to 10
	while (len >= 10) {
	    // parse the last 8 hex number into rest[i]
	    retValue[i] = (int) Long.parseLong(str.substring(len - 8, len), 16);
	    // decrease the total length by 8
	    len -= 8;
	    // move the index;
	    i--;
	}
    
	// parse any leftover string (hex number)
	if (len > 2 && i >= 0) {
	    // put the rest of string into rest[i] (hex form parsing)
	    retValue[i] = Integer.parseInt(str.substring(2, len), 16);
	}
	// If the i is not less than zero at the moment, 
	// we don't need to do anything because we initialize the array
	// as all zero.
    
	return retValue;
	
    }
   
}
