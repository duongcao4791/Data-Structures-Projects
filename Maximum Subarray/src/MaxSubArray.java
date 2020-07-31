import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
/**
 * Function to sold problem by the given file
 * @author duong
 *
 */
public class MaxSubArray {
   public static void main(String args[]) throws Exception 
   {
	   
      Scanner in = new Scanner(new BufferedReader(new FileReader("maxSumtest.txt")));  //Get input file
      try
  	{
    	/**
    	 * Give output console to CaoDuongArray File
    	 */
    	File file = new File("CaoDuongArray.txt");
  		FileOutputStream fos = new FileOutputStream(file);
  		PrintStream ps = new PrintStream(fos);
  		System.setOut(ps);
  	}
  	catch(IOException e)
  	{
  	System.out.println("Error during reading/writing");
  	}
	     
      /**
       * Method to copy elements from file to array
       */
      while(in.hasNextLine())
      {
    	  String line = in.nextLine(); //Each array of numbers is on a single line
    	  if(line.equals("")) //Takes care of the fact that each array is separated by an empty line
    		  line = in.nextLine();
			
    	  String filteredLine = line.substring(1).replaceAll("[^0-9-]", " "); //Replaces all brackets and commas with a space
    	  String[] valuesAsStr = filteredLine.split("\\s+"); // Fills valuesAsStr with strings containing a single integer value
			
    	  int[] values = new int[valuesAsStr.length-3]; // Creates the integer array that stores the values 
			
    	  for(int i = 0; i < valuesAsStr.length-3; i++) // Fills the final array with integers by parsing them one by one from valuesAsStr
    	  {
    		  values[i] = Integer.parseInt(valuesAsStr[i]); 
    	  }
    	  
    	  /**
    	   * Print out a line corresponding to the example above would be [array], sum, arrive, depart:
    	   */
    	  System.out.println(Arrays.toString(values) + ", " +BruteForceMaxSubarray.maxSum(values).sum + ", " + BruteForceMaxSubarray.maxSum(values).arrive + ", " + BruteForceMaxSubarray.maxSum(values).depart);
      }
      in.close();
   }
}