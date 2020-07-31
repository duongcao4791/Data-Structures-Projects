import java.util.Scanner;

//Java Program to test the Circular Linkedlist Game
//Create a circular linkedlist of nodes
//Add nodes to the list 
//Simulate the elimination process by deleting the nodes by the given step
//Find the last node as the last prisoner alive

public class CircularList {
		
		// initiate the firstNode and the lastNode node of the linkedlist
		private Node firstNode;			
		private Node lastNode;
		private int size;

		//constructor
		public CircularList(){
			firstNode = null;
			lastNode = null;
			size = 0;
		}
		
		 // Function to check if list is empty 
		public boolean isEmpty() {
			return firstNode == null;
			}
		
		/* Function to get size of the list */
	    public int getSize()
	    {
	        return size;
	    }
	    
	    
	    // Function to insert elements
		public void insert(int value) 
		{
			if (firstNode == null) 
			{
				Node nd = new Node(value);
				this.firstNode = nd;
				
				//function to make the LinkedList circularly
				nd.setlink(firstNode); 
				this.lastNode = firstNode;
			} 
			else {
				add(value, lastNode);
			}
		size++;

		}

		//add method
		private void add(int value, Node node) 
		{
			Node nd = new Node(value);
			node.setlink(nd);

			//function to make the LinkedList circularly
			nd.setlink(firstNode); 
			this.lastNode = nd;

		}

		// function to remove prisoner at k position
		public int remove(int k) 
		{		
			//Set the first person is the first node 
			Node del = this.firstNode;

			// run the program until we get the lastNode
			while (size != 1) 
			{	
				for (int i = 1; i <= k; i++) 
				{
					del = del.getlink();	//get the next node will be deleted
				}
				
				delete(del.getValue());			//remove the node
				del = del.getlink();		
				}
			return this.firstNode.getValue();
			}
		
		//delete method
		private void delete(int value) 
		{
			if (this.firstNode.getValue() == value) 
			{
				this.lastNode.setlink(this.firstNode.getlink());
				this.firstNode = this.firstNode.getlink();	
				size--;
				return;
			}

			Node newNode = this.firstNode;
			while (newNode != this.lastNode) 
			{
				if (newNode.getlink().getValue() != value)  //compare to the delete value
				{
					newNode = newNode.getlink();		//move to the next node
				} 
				else{
					
					newNode.setlink(newNode.getlink().getlink());
					newNode = null; 							// delete that node
				
						size--;
						return;
					}
			}
		}
		public static void main(String[] args){
				//Enter number of prison n
				System.out.printf("Enter number of prisons: ");
				Scanner sc = new Scanner(System.in);
				int n = sc.nextInt();
				
				//Enter eliminate step k
				System.out.printf("Enter step k: ");
				Scanner st = new Scanner(System.in);
				int k = st.nextInt();
				
				long startTime = System.currentTimeMillis();		//Start time of process
				
				CircularList prisoners=new CircularList();
				for(int i = 1; i<=n; i++) 
				{
					prisoners.insert(i);
				}
				
				System.out.println("By k = " + k + ", the last prison at position:" + prisoners.remove(k));
				long endTime = System.currentTimeMillis();				//get running time in ms
				long duration = (endTime-startTime);
				System.out.println("Time executed: "+ duration + "ms");	
				st.close();
				sc.close();
				
			
		}
}
