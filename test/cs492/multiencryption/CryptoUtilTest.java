package cs492.multiencryption;

import org.junit.jupiter.api.Test;

public class CryptoUtilTest {


	@Test
	public void testPrintByte() {

		byte[] s = "pudding".getBytes();


		CryptoUtil.printByte(s);
	}




}
