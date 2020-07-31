import java.util.Arrays;
/**
 * Brute Force Althgorithm
 * @author duong
 *
 */
public class BruteForceMaxSubarray{
		public int sum = 0;
		public int arrive;
		public int depart;
	/**
	 * Method to get the Maximum SubArray Sum
	 * @param A - array
	 * @return result integer
	 */
	public static BruteForceMaxSubarray maxSum(int[] A) {
       
		BruteForceMaxSubarray result = new BruteForceMaxSubarray();
        int maxSum = 0;
        for (int i = 0; i < A.length-1; i++) {
        	/**
        	 * If all elements is negative, sum = 0
        	 */
        	if(allNegatives(A)) {
        		result.sum = 0;
        		result.arrive = -1;
        		result.depart = 0;
        	}
        	
            maxSum += A[i];
            for (int j = i+1; j < A.length; j++) {
                maxSum += A[j];
                if (maxSum > result.sum) {
                    result.sum = maxSum;
                    result.arrive = i;
                    result.depart = j;
                }
            }
            maxSum = 0;
        }
        return result;
    }
	/**
	 * Method to check all the elements is negative
	 * @param array - array
	 * @return boolean
	 */
	public static boolean allNegatives(int[] array) {
	     //@bowmore suggestion edit
	     return array != null && Arrays.stream(array).allMatch(i -> i < 0);
	}
	/**
	 * Test Case
	 * @param args - main method
	 */
	/* 
	public static void main(String[] args) {
		   int [] a = {8, -39, -24, -30, 41, -8, 22, -13, 49, 17, 9, 0, -4, -10, 19, 3, 44, -38, -37, -7, 33, 5, -14, 15, -16, -49, 48, -6, 1, 47, -27, -48, 26, 45, 46, -19, 2, -5, 38, 40, 28, 39, 4, 37, 12, 21, -1, -42, -32, 34, -36, 32, -41, -45, -31, -50, 10, -3, 23, 30, 43, -21, -47, -40, -35, -2, -34, -28, -12, -9, 24, 36, -23, 13, -25, 6, 25, 11, 20, 18, 16, -11, 31, -15, 35, -33, 42, 7, -22, -29, 29, -17, -20, -43, -18, 27, -46, -26, -44, 14}; 
		   int [] b = {-3, -4, -5, -6, -7};
		   int [] c = {-2, -3, 4, -1, -2, 1, 5, -3};
		   System.out.println(maxSum(a).sum + " " + maxSum(a).arrive + " " + maxSum(a).depart);
		   System.out.println(maxSum(b).sum + " " + maxSum(b).arrive + " " + maxSum(b).depart);
		   System.out.println(maxSum(c).sum + " " + maxSum(c).arrive + " " + maxSum(c).depart);
                                    
		    }  
		    */
	 }
