package cs146S19.cao.project4;

	public class RedBlackTree<Key extends Comparable<Key>> {

		private RedBlackTree.Node<String> root;

		public static class Node<Key extends Comparable<Key>> { // changed to static

			Key key;
			Node<String> parent;
			Node<String> leftChild;
			Node<String> rightChild;
			boolean isRed;
			int color;

			public Node(Key data) {
				this.key = data;
				leftChild = null;
				rightChild = null;
			}

			public int compareTo(Node<Key> n) { // this < that <0
				return key.compareTo(n.key); // this > that >0
			}

			public boolean isLeaf() {
				// if (this.equals(root) && this.leftChild == null &&
				// this.rightChild == null)
				// return true;
				// if (this.equals(root))
				// return false;
				if (this.leftChild == null && this.rightChild == null) {
					return true;
				}
				return false;
			}
		}

		public boolean isLeaf(RedBlackTree.Node<String> n) {
			if (n.equals(root) && n.leftChild == null && n.rightChild == null) return true; 
			if (n.equals(root)) return false;
			if (n.leftChild == null && n.rightChild == null) {
				return true;
			}
			return false;
		}

		public interface Visitor<Key extends Comparable<Key>> {
			/**
			 * This method is called at each node.
			 * @param n the visited node
			 */
			void visit(Node<Key> n);
		}

		public void visit(Node<Key> n) {
			System.out.println(n.key);
		}

		public void printTree() { // preorder: visit, go left, go right
			RedBlackTree.Node<String> currentNode = root;
			printTree(currentNode);
		}

		public void printTree(RedBlackTree.Node<String> node) {
			System.out.print(node.key);
			if (node.isLeaf()) {
				return;
			}
			if(node.leftChild != null)
			{
				printTree(node.leftChild);
			}
			if(node.rightChild != null)
			{
				printTree(node.rightChild);
			}
		}

		// place a new node in the RB tree with data the parameter and color it red.
		public void addNode(String data) { // this < that <0. this > that >0
			RedBlackTree.Node<String> newNode = new RedBlackTree.Node<String>(data);
			newNode.isRed = true;
			newNode.color = 0;
			
			if (root == null) {
				root = newNode;
			} 
			else {
				RedBlackTree.Node<String> currentNode = root;
				while (true) {
					// If newNode bigger than currentNode, set newNode tobe the rightchild
					if (newNode.compareTo(currentNode) > 0) {
						if (currentNode.rightChild == null) {
							currentNode.rightChild = newNode;
							newNode.parent = currentNode;
							break;
						}
						currentNode = currentNode.rightChild;
					}
					//If newNode smaller than currentNode, set newNode to be the leftchild
					else if (newNode.compareTo(currentNode) < 0) {
						if (currentNode.leftChild == null) {
							currentNode.leftChild = newNode;
							newNode.parent = currentNode;
							break;
						}
						currentNode = currentNode.leftChild;
					} 
				}
			}
			fixTree(newNode);
		}

		public void insert(String data) {
			addNode(data);
		}

		public RedBlackTree.Node<String> lookup(String k) {
			if (root == null) {
				return null;
			} 
			else {
				RedBlackTree.Node<String> currentNode = root;
				while (!currentNode.key.equals(k)) 	// While k != currentNode
				{
					if (currentNode.key.compareTo(k) > 0) {	//if k<currentNode, move to the left child
						if (currentNode.leftChild == null) {
							return null;
						}
						currentNode = currentNode.leftChild;
					} 
					else if (currentNode.key.compareTo(k) <= 0) { //if k>=currentNode, move to the right child
						if (currentNode.rightChild == null) {
							return null;
						}
						currentNode = currentNode.rightChild;
					}
				}
				return currentNode;
			}
		}

		public RedBlackTree.Node<String> getSibling(RedBlackTree.Node<String> n) {
			Node<String> nParent = n.parent;
			if(nParent != null)
			{
				if(nParent.compareTo(n) < 0)
				{
					return nParent.leftChild;
				}
				else if(nParent.compareTo(n) > 0)
				{
					return nParent.rightChild;
				}
			}
			return null;
		}

		public RedBlackTree.Node<String> getAunt(RedBlackTree.Node<String> n) {
			Node<String> nGrandParent = getGrandparent(n);
			if(nGrandParent != null)
			{
				if(nGrandParent.compareTo(n) < 0)
				{
					return nGrandParent.leftChild;
				}
				else if(nGrandParent.compareTo(n) > 0)
				{
					return nGrandParent.rightChild;
				}
			}
			return null;
		}

		public RedBlackTree.Node<String> getGrandparent(RedBlackTree.Node<String> n) {
			if (n.parent.parent == null) {
				return null;
			}
			return n.parent.parent;
		}

		public void rotateLeft(RedBlackTree.Node<String> n) {
			RedBlackTree.Node<String> nRightChild = n.rightChild;
			n.rightChild = nRightChild.leftChild;
			
			if (nRightChild.leftChild != null)
			{
				nRightChild.leftChild.parent = n;
			}
			nRightChild.parent = n.parent;
			if (n.parent == null)
			{
				root = nRightChild;
			}
			else if(n == n.parent.leftChild)
			{
				n.parent.leftChild = nRightChild;
			}
			else{
				n.parent.rightChild = nRightChild;
			}
			nRightChild.leftChild = n;
			n.parent = nRightChild;
		}

		public void rotateRight(RedBlackTree.Node<String> n) {
			RedBlackTree.Node<String> nLeftChild = n.leftChild;
			n.leftChild = nLeftChild.rightChild;
			
			if (nLeftChild.rightChild != null)
			{
				nLeftChild.rightChild.parent = n;
			}
			nLeftChild.parent = n.parent;
			if (n.parent == null)
			{
				root = nLeftChild;
			}
			else if (n == n.parent.rightChild)
			{
				n.parent.rightChild = nLeftChild;
			}
			else{
				n.parent.leftChild = nLeftChild;
			}
			nLeftChild.rightChild = n;
			n.parent = nLeftChild;
		}

		//Followed the pseudocode in the book, turned it into code
				public void fixTree(RedBlackTree.Node<String> current) {
					//current is the root node. Make it black and quit.
					if(current == root)
					{
						current.color = 1;
					}
					//The current node is red and the parent node is red.
					else if(current.color == 0 && current.parent.color == 0)
					{
						//If the aunt node is empty or black
						if(getAunt(current) == null || getAunt(current).color == 1)
						{
							//A) grandparent –parent(is left child)— current (is right child) case. 
							//Solution: rotate the parent left and then continue recursively fixing the tree starting with the original parent
							if(current.parent == getGrandparent(current).leftChild && current == current.parent.rightChild)
							{
								rotateLeft(current.parent);
								current = current.parent;
								fixTree(current);
							}
							//B) grandparent –parent (is right child)— current (is left child) case. 
							//Solution: rotate the parent right and then continue recursively fixing the tree starting with the original parent.
							else if(current.parent == getGrandparent(current).rightChild && current == current.parent.leftChild)
							{
								rotateRight(current.parent);
								current = current.parent;
								fixTree(current);
							}
							//C) grandparent –parent (is left child)— current (is left child) case. 
							//Solution: make the parent black, make the grandparent red, rotate the grandparent to the right and quit, tree is balanced.
							else if(current.parent == getGrandparent(current).leftChild && current == current.parent.leftChild)
							{
								current.parent.color = 1;
								getGrandparent(current).color = 0;
								rotateRight(getGrandparent(current));
							}
							//D) grandparent –parent (is right child)— current (is right child) case. 
							//Solution: make the parent black, make the grandparent red, rotate the grandparent to the left, quit tree is balanced.
							else if(current.parent == getGrandparent(current).rightChild && current == current.parent.rightChild)
							{
								current.parent.color = 1;
								getGrandparent(current).color = 0;
								rotateLeft(getGrandparent(current));
							}
						}
						//Else if the aunt is red, then make the parent black,
						//make the aunt black, make the grandparent red and continue recursively fix up the tree starting with the grandparent.
						else
						{
							current.parent.color = 1;
							getAunt(current).color = 1;
							getGrandparent(current).color = 0;
							current = getGrandparent(current);
							fixTree(current);
						}
					}
				}
		public boolean isEmpty(RedBlackTree.Node<String> n) {
			if (n.key == null) {
				return true;
			}
			return false;
		}

		public boolean isLeftChild(RedBlackTree.Node<String> parent, RedBlackTree.Node<String> child) {
			if (child.compareTo(parent) < 0) {// child is less than parent
				return true;
			}
			return false;
		}

		public void preOrderVisit(Visitor<String> v) {
			preOrderVisit(root, v);
		}

		private static void preOrderVisit(RedBlackTree.Node<String> n, Visitor<String> v) {
			if (n == null) {
				return;
			}
			v.visit(n);
			preOrderVisit(n.leftChild, v);
			preOrderVisit(n.rightChild, v);
		}
	}