import java.io.*;
import java.util.Random;
import java.util.Scanner;
public class musicList {
	
	static Random random = new Random();
    public static void main(String args[]) throws IOException
    {
    	String[] playlist = new String[459]; 
	    //setSeed value input
    	System.out.println("setSeed random: ");
	    Scanner input = new Scanner(System.in);
	    int rd = input.nextInt();
	    random.setSeed(rd);
	    
	    Reader(playlist);		
	    Shuffle(playlist);
	    Writer(playlist);
	    input.close();
    }
	    
    /* Reader Class
     * Classs to read data from input file
     * Copy from the input file to array
     */
    static void Reader(String[] array) throws IOException {
	       BufferedReader in = new BufferedReader(new FileReader("Playlist.txt"));	 //read Playlist.txt
	       int index = 0;
	       String line;
	       while((line = in.readLine())!=null)		//copy from Playlist.txt to array
	       {
	           array[index] = line;
	           index++;
	       }
	       in.close();
    }
    
    /* Writer Class
     * Class to write data from array to output file
     */
    static void Writer(String[] array) throws IOException {
    	FileWriter out = new FileWriter("CaoDuongPlaylist.txt");
    		for(int i=0; i<array.length; i++)
		    {
	    	   out.write(array[i]+"\n");
	    	   System.out.println(array[i]);
		    }
	    	out.close();
	 }
    
    /*  Fisher–Yates shuffle algorithm
     *  function to shuffle array
     */
    static void Shuffle(String[] array) {   
	       for(int i = array.length - 1; i>0; i--)
	       {
	           int j = random.nextInt(i);
	          
	           //Swap two elements
	           String tmp = array[j];
	           array[j] = array[i];
	           array[i]= tmp;
	       }
 }
}