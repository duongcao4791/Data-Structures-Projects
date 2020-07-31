import java.util.Arrays;
/**
 * Devide and Conquer Althgorithm
 * @author duong
 *
 */
public class DevideConquerMaxSubarray
{
	    public int arrive;
	    public int depart;
	    public int sum;
	    
	    /**
	     * Method to get the Max Subarray by Devide and Conquer
	     * @param A - array
	     * @param low - start index
	     * @param high - end index
	     * @return integer
	     */
	    public static DevideConquerMaxSubarray maxSum(int[] A, int low, int high)
	    { 
	    	DevideConquerMaxSubarray result = new DevideConquerMaxSubarray();
	    	/**
        	 * If all elements is negative, sum = 0
        	 */
	    	if(allNegatives(A)) {
        		result.sum = 0;
        		result.arrive = -1;
        		result.depart = 0;
        	}else 
        	{
        		if (low == high) 		// check if the array only have one element
        		{
        			result.arrive = low;	
        			result.depart = high;
        			result.sum = A[low];
        			return result;
        		}
	    	
        		int mid = (low + high) / 2; // getting mid
        		/**
        		* Return the maximum of the following three
				* 	a) Maximum subarray sum in left half (Make a recursive call)
				*	b) Maximum subarray sum in right half (Make a recursive call)
				*	c) Maximum subarray sum such that the subarray crosses the midpoint
        		 */
	            
        		DevideConquerMaxSubarray resultLeft = maxSum(A, low, mid); //generating result for left array
        		DevideConquerMaxSubarray resultRight = maxSum(A, mid + 1, high); //generating results for right
        		DevideConquerMaxSubarray resultMid = maxCross(A, low, high, mid); // for middle one
	           

        		//Return largest sum
        		if (resultLeft.sum >= resultRight.sum && resultLeft.sum >= resultMid.sum)
        			return resultLeft;
        		else if (resultRight.sum >= resultLeft.sum && resultRight.sum >= resultMid.sum)
        			return resultRight;
        		else
        			return resultMid; 
	        }
			return result;
	    }
	    
	    /**
	     * Method to alculate the maximum sum of the subarray crossing the middle element
	     * @param A - array
	     * @param low - start index
	     * @param high - end index
	     * @param mid - mid index
	     * @return
	     */
	    public static DevideConquerMaxSubarray maxCross(int[] A, int low, int high, int mid) 
	    {
	    	DevideConquerMaxSubarray result = new DevideConquerMaxSubarray();
	        int sum = 0;       						
	        int leftsum = Integer.MIN_VALUE; 		
	        int leftMax=-1;   						

	        for (int i = mid; i >= low; i--) // going from mid to zero
	        {
	            sum = sum + A[i];
	            if (sum > leftsum)
	            {
	                leftsum = sum;
	                leftMax = i;
	            }
	        }

	        int rightsum = Integer.MIN_VALUE;
	        sum = 0;
	        int rightMax= -1;
	        for (int j = mid + 1; j <= high; j++) // going from mid+1 to last
	        {
	            sum = sum + A[j];
	            if (sum > rightsum)
	            {
	                rightsum = sum;
	                rightMax = j;
	            }
	        }


	        result.arrive = leftMax;
	        result.depart = rightMax;
	        result.sum = leftsum + rightsum;  // sum of left and right sub array
	        return result;

	    }
	    
	/**
	* Method to check all the elements is negative
	* @param array - array
	* @return boolean
	*/
	public static boolean allNegatives(int[] array) {
		     return array != null && Arrays.stream(array).allMatch(i -> i < 0);
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
	    System.out.println(maxSum(a,0,a.length-1).sum + " " + maxSum(a,0,a.length-1).arrive + " " + maxSum(a,0,a.length-1).depart);
	    System.out.println(maxSum(b,0,b.length-1).sum + " " + maxSum(b,0,b.length-1).arrive + " " + maxSum(b,0,b.length-1).depart);
	    System.out.println(maxSum(c,0,c.length-1).sum + " " + maxSum(c,0,c.length-1).arrive + " " + maxSum(c,0,c.length-1).depart);

	}
	*/
}