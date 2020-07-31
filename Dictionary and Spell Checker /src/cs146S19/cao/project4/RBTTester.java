package cs146S19.cao.project4;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

public class RBTTester {

	@Test
	// Test the Red Black Tree
	public void test() {
		RedBlackTree rbt = new RedBlackTree();
		rbt.insert("D");
		rbt.insert("B");
		rbt.insert("A");
		rbt.insert("C");
		rbt.insert("F");
		rbt.insert("E");
		rbt.insert("H");
		rbt.insert("G");
		rbt.insert("I");
		rbt.insert("J");
		assertEquals("DBACFEHGIJ", makeString(rbt));
		String str=     "Color: 1, Key:D Parent: \n"+
                "Color: 1, Key:B Parent: D\n"+
                "Color: 1, Key:A Parent: B\n"+
                "Color: 1, Key:C Parent: B\n"+
                "Color: 1, Key:F Parent: D\n"+
                "Color: 1, Key:E Parent: F\n"+
                "Color: 0, Key:H Parent: F\n"+
                "Color: 1, Key:G Parent: H\n"+
                "Color: 1, Key:I Parent: H\n"+
                "Color: 0, Key:J Parent: I\n";
		assertEquals(str, makeStringDetails(rbt));

	}
	@Test
	public void spellChecker() throws IOException 
	{
		
		int counter = 0;	//counter how many words are mismatched
		long startTime = System.currentTimeMillis();
		RedBlackTree dictionary = makeDictionary();
		ArrayList<String> wordsList = getPoem();
		for(int i = 0; i < wordsList.size(); i++)
		{
			//if the word mismatched to dictionary, increase counter
			if(dictionary.lookup(wordsList.get(i)) == null)
			{
				counter++;
			}	
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Runtime of spellChecker: " + (endTime-startTime) + " milliseconds");
		System.out.println("Number of words in poem: " + wordsList.size());
		System.out.println("Number of mismatched/nonexistent words: " + counter);
		//System.out.println(wordsList);
	}
	
	public static RedBlackTree makeDictionary() throws IOException
		{
			RedBlackTree dictionary = new RedBlackTree();
			try 
			{
				BufferedReader dict = new BufferedReader(new FileReader("dictionary.txt"));	//Read the dictionary text file
				long startDict = System.currentTimeMillis();  //start Time
				String line;
				
				//insert line by line to RBT
				while((line = dict.readLine()) != null) 
				{
					dictionary.insert(line);
				}
				
				long endDict = System.currentTimeMillis();		//end Time
				System.out.println("Time to create dictionary: " + ((endDict - startDict)) + " milliseconds");
				dict.close(); 
			}
			catch(IOException e)
		  	{
		  	System.out.println("Error during reading/writing!!!");
		  	}
			return dictionary;	
		}
	
	public static ArrayList<String> getPoem()
	{
		ArrayList<String> wordsList = new ArrayList<String>();
		try 
		{
			
			BufferedReader poem = new BufferedReader(new FileReader("Poem.txt"));		//reads the poem text file
			long startPoem = System.currentTimeMillis();			//start Time
			String line;
			
			//puts the words from the Poem line into ArrayList
			while((line = poem.readLine()) != null)
			{
				String[] changedLine = line.replaceAll("[^a-zA-Z ]","").toLowerCase().split("\\s+");
				for(int i = 0; i< changedLine.length; i++)
				{
					wordsList.add(changedLine[i]);
				}
			}
			long endPoem = System.currentTimeMillis();				//end Time
			System.out.println("Time to add words to lists: " + (endPoem - startPoem) + " milliseconds");
			poem.close();
		}
		catch(Exception e)
		{
			System.out.println("Error during reading/writing!!!");
		}
		return wordsList;
		
	}
	
	public static String makeString(RedBlackTree t) {
		class MyVisitor implements RedBlackTree.Visitor {
			String result = "";

			public void visit(RedBlackTree.Node n) {
				result = result + n.key;
			}
		}
		;
		MyVisitor v = new MyVisitor();
		t.preOrderVisit(v);
		return v.result;
	}

	public static String makeStringDetails(RedBlackTree t) {
		{
			class MyVisitor implements RedBlackTree.Visitor {
				String result = "";

				public void visit(RedBlackTree.Node n) {
					if (n.parent == null){
						result = result + "Color: " + n.color + ", Key:" + n.key + " Parent: \n";
					}
					else if (!(n.key).equals(""))
						result = result + "Color: " + n.color + ", Key:" + n.key + " Parent: "+ n.parent.key +"\n";

				}
			}
			;
			MyVisitor v = new MyVisitor();
			t.preOrderVisit(v);
			return v.result;
		}
	}
}
