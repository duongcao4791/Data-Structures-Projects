package cao.cs146.project3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MazeJUnitTest {

	// 2x2 Maze
    @Test
    public void Maze2x2() {
        System.out.println("------- 2x2 Maze -------");
        Maze Maze = new Maze(2);
        System.out.print(Maze.printMaze());
        System.out.print(Maze.printDFS());
        System.out.print(Maze.printBFS());
        assertEquals(Maze.printDFS(), Maze.printBFS());
    }

    // 5x5 Maze
    @Test
    public void Maze5x5() {
        System.out.println("------- 5x5 Maze -------");
        Maze Maze = new Maze(5);
        System.out.print(Maze.printMaze());
        System.out.print(Maze.printDFS());
        System.out.print(Maze.printBFS());
        assertEquals(Maze.printDFS(), Maze.printBFS());
    }

 // 10x10 Maze
    @Test
    public void Maze10x10() {
        System.out.println("------- 10x10 Maze -------");
        Maze Maze = new Maze(10);
        System.out.print(Maze.printMaze());
        System.out.print(Maze.printDFS());
        System.out.print(Maze.printBFS());
        assertEquals(Maze.printDFS(), Maze.printBFS());
    }

}
