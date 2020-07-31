package cao.cs146.project3;
import java.util.Scanner;
public class MazeTest {
	  public static void main(String args[]) {
		System.out.printf("Enter size of Maze (nxn): ");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
	    Maze m = new Maze(n);
	    System.out.println(m.printMaze());
	    System.out.print(m.printBFS());
	    System.out.print(m.printDFS());
	  }
}
