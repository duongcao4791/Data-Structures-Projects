import static org.junit.jupiter.api.Assertions.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.junit.jupiter.api.Test;
/**
 * Test Class
 * @author duong
 *
 */
class MaxSumTest {

	/**
	 * Test case use input and output file
	 * @throws IOException
	 */
	@Test
	void TestFile() throws IOException  {
		
		BufferedReader Out=new BufferedReader (new FileReader ("CaoDuongArray.txt")); 
		BufferedReader In=new BufferedReader (new FileReader ("maxSumtest.txt"));
		String expectedLine;
		
		while ((expectedLine = In.readLine ()) != null) {
			String actualLine = Out.readLine ();
			assertEquals (expectedLine, actualLine);
		}
		Out.close();
		In.close();
	}
	/**
	 * Test case using Brute Force Algorithm
	 */
	@Test
	void BruteForceTest()
	{
		int[] a = {-4, -5, -8, -2, -7, -8, 3, -25, 1, 30, -11, -30, -27, -1, 48, 44, -40, 32, -23};
		int[] b = { 3, -7, -3, 6, 4, 3, 8, 12, 44, 6, 46, -33, -15, 39, -19, 19};
		int[] c = {-30, 41, -8, 22, -13, 49, 17, 9, 0, -4, -10, 19, 3, 44, -38, -37, -7, 33, 5, -14, 15, -16, -49, 48, -6, 1, 4};
		
		assertEquals(92, BruteForceMaxSubarray.maxSum(a).sum);
		assertEquals(14, BruteForceMaxSubarray.maxSum(a).arrive);
		assertEquals(15, BruteForceMaxSubarray.maxSum(a).depart);
		
		assertEquals(129, BruteForceMaxSubarray.maxSum(b).sum);
		assertEquals(3, BruteForceMaxSubarray.maxSum(b).arrive);
		assertEquals(10, BruteForceMaxSubarray.maxSum(b).depart);
		
		assertEquals(169, BruteForceMaxSubarray.maxSum(c).sum);
		assertEquals(1, BruteForceMaxSubarray.maxSum(c).arrive);
		assertEquals(13, BruteForceMaxSubarray.maxSum(c).depart);
	}
	
	/**
	 * Test case using Divide and Conquer Algorithm
	 */
	@Test
	void DevideConquerTest()
	{
		int[] a = {-4, -5, -8, -2, -7, -8, 3, -25, 1, 30, -11, -30, -27, -1, 48, 44, -40, 32, -23};
		int[] b = { 3, -7, -3, 6, 4, 3, 8, 12, 44, 6, 46, -33, -15, 39, -19, 19};
		int[] c = {-30, 41, -8, 22, -13, 49, 17, 9, 0, -4, -10, 19, 3, 44, -38, -37, -7, 33, 5, -14, 15, -16, -49, 48, -6, 1, 4};
		
		assertEquals(92, DevideConquerMaxSubarray.maxSum(a, 0, a.length-1).sum);
		assertEquals(14, DevideConquerMaxSubarray.maxSum(a, 0, a.length-1).arrive);
		assertEquals(15, DevideConquerMaxSubarray.maxSum(a, 0, a.length-1).depart);
		
		assertEquals(129, DevideConquerMaxSubarray.maxSum(b, 0, b.length-1).sum);
		assertEquals(3, DevideConquerMaxSubarray.maxSum(b, 0, b.length-1).arrive);
		assertEquals(10, DevideConquerMaxSubarray.maxSum(b, 0, b.length-1).depart);
		
		assertEquals(169, DevideConquerMaxSubarray.maxSum(c, 0, c.length-1).sum);
		assertEquals(1, DevideConquerMaxSubarray.maxSum(c, 0, c.length-1).arrive);
		assertEquals(13, DevideConquerMaxSubarray.maxSum(c, 0, c.length-1).depart);
	}
	
	/**
	 * Test case using Kadane's Algorithm
	 */
	@Test 
	void KadaneTest()
	{

		int[] a = {-4, -5, -8, -2, -7, -8, 3, -25, 1, 30, -11, -30, -27, -1, 48, 44, -40, 32, -23};
		int[] b = { 3, -7, -3, 6, 4, 3, 8, 12, 44, 6, 46, -33, -15, 39, -19, 19};
		int[] c = {-30, 41, -8, 22, -13, 49, 17, 9, 0, -4, -10, 19, 3, 44, -38, -37, -7, 33, 5, -14, 15, -16, -49, 48, -6, 1, 4};
		
		assertEquals(92, KadaneMaxSubarray.maxSum(a).sum);
		assertEquals(14, KadaneMaxSubarray.maxSum(a).arrive);
		assertEquals(15, KadaneMaxSubarray.maxSum(a).depart);
		
		assertEquals(129, KadaneMaxSubarray.maxSum(b).sum);
		assertEquals(3, KadaneMaxSubarray.maxSum(b).arrive);
		assertEquals(10, KadaneMaxSubarray.maxSum(b).depart);
		
		assertEquals(169, KadaneMaxSubarray.maxSum(c).sum);
		assertEquals(1, KadaneMaxSubarray.maxSum(c).arrive);
		assertEquals(13, KadaneMaxSubarray.maxSum(c).depart);
	}
	
}


