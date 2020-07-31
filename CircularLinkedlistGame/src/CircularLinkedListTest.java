import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CircularLinkedListTest {

		@Test

		public void Test() {

		CircularList prisoners=new CircularList();
	    assertTrue(prisoners.isEmpty()); //before inserting, list is empty
	    assertEquals(0, prisoners.getSize()); // Size is 0
	    prisoners.insert(5);
	    assertFalse(prisoners.isEmpty()); // after inserting element, list is not empty
	    assertEquals(1,prisoners.getSize()); //size is 1

		}

		@Test

		public void Case1() {
		CircularList prisoners=new CircularList();
		prisoners.insert(1);
		prisoners.insert(2);
		prisoners.insert(3);
		prisoners.insert(4);
		prisoners.insert(5);
		prisoners.insert(6);
		assertEquals(prisoners.remove(2),1);
		}

		@Test

		public void Case2() {

		CircularList prisoners=new CircularList();
		prisoners.insert(1);
		assertEquals(prisoners.remove(9),1);
		}

		@Test
		public void Case3() {

		CircularList prisoners=new CircularList();
		prisoners.insert(1);
		prisoners.insert(2);
		prisoners.insert(3);
		prisoners.insert(4);
		prisoners.insert(5);
		prisoners.insert(6);
		prisoners.insert(7);
		assertEquals(prisoners.remove(7),4);
		}

		@Test

		public void Case4() {
			
		CircularList prisoners=new CircularList();
		prisoners.insert(1);
		prisoners.insert(2);
		prisoners.insert(3);
		prisoners.insert(4);
		prisoners.insert(5);
		prisoners.insert(6);
		prisoners.insert(7);
		prisoners.insert(8);
		prisoners.insert(9);
		prisoners.insert(10);
		prisoners.insert(11);
		prisoners.insert(12);
		assertEquals(prisoners.remove(8),2);

		}

		@Test

		public void Case5() {
			
		CircularList prisoners=new CircularList();
		prisoners.insert(1);
		prisoners.insert(2);
		prisoners.insert(3);
		prisoners.insert(4);
		prisoners.insert(5);
		assertEquals(prisoners.remove(1),3);

		}

	}

