import java.util.Arrays;
/**
 * Kadane's Althgorithm
 * @author duong
 *
 */
public class KadaneMaxSubarray {
	 public int arrive;
	 public int depart;
	 public int sum = 0;
	 
	 /**
	  * Function to find contiguous sub-array by Kadane's Althgorithm
	  * @param a - array
	  * @return integer result
	  */
	 public static KadaneMaxSubarray maxSum(int a[]) 
	    { 
		 KadaneMaxSubarray result = new KadaneMaxSubarray();
	        int maxTemp = 0;
	        int tempArrive = 0; 
	        int maxSum = 0;
	        
	        /**
	         * If all elements are negative
	         */
	        	if(allNegatives(a)) {
	        		result.sum = 0;
	                result.arrive = -1;
	                result.depart = 0;
	        	}
	        	else {
	        		for (int i = 0; i < a.length; i++)  
	    	        { 
	        		maxTemp = maxTemp + a[i];
	            
	        		if (maxTemp < 0)  
	        		{ 
	        			maxTemp = 0; 
	        			result.arrive = i + 1; 
	        		} 
	        			
	        		if (maxSum < maxTemp)  
	        		{ 
	        			maxSum = maxTemp;  
	        			result.sum = maxSum;
	        			result.depart = i; 
	        			tempArrive = result.arrive;    
	        		} 
	        	}
	        	result.arrive = tempArrive;
	        } 
			return result; 
	    } 
	 /**
		 * Method to check all the elements is negative
		 * @param array - array
		 * @return boolean
		 */
	 public static boolean allNegatives(int[] a) {
	     return a != null && Arrays.stream(a).allMatch(i -> i < 0);
	}
	 
	 /**
		 * Test Case
		 * @param args - main method
		 */
		/* 
	 public static void main(String[] args) 
	    { 
		  int [] a = {8, -39, -24, -30, 41, -8, 22, -13, 49, 17, 9, 0, -4, -10, 19, 3, 44, -38, -37, -7, 33, 5, -14, 15, -16, -49, 48, -6, 1, 47, -27, -48, 26, 45, 46, -19, 2, -5, 38, 40, 28, 39, 4, 37, 12, 21, -1, -42, -32, 34, -36, 32, -41, -45, -31, -50, 10, -3, 23, 30, 43, -21, -47, -40, -35, -2, -34, -28, -12, -9, 24, 36, -23, 13, -25, 6, 25, 11, 20, 18, 16, -11, 31, -15, 35, -33, 42, 7, -22, -29, 29, -17, -20, -43, -18, 27, -46, -26, -44, 14}; 
		  int [] b = {-3, -4, -5, -6, -7};
		  int [] c = {-2, -3, 4, -1, -2, 1, 5, -3};
		  System.out.println(maxSum(a).sum + " " + maxSum(a).arrive + " " + maxSum(a).depart);
		  System.out.println(maxSum(b).sum + " " + maxSum(b).arrive + " " + maxSum(b).depart);
		  System.out.println(maxSum(c).sum + " " + maxSum(c).arrive + " " + maxSum(c).depart);
	    } 
	    */
}
