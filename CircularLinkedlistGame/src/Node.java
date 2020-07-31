//Class Node
public class Node {
		private int value;
		private Node link;
	
		//Constructor
		public Node(int value) {
			this.value = value;
			
		}

		//Function to set value to current node
		public void setValue(int value) 
		{
			this.value = value;
		}
		
		//function to get value from current node
		public int getValue() 
		{
			return value;
		}

		//Function to set link to next node
		public void setlink(Node link) 
		{
			this.link = link;
		}
		
		//function to get link to next node
		public Node getlink() 
		{
		return link;
		}

}
