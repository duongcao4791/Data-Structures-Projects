package cao.cs146.project3;

import java.util.*;

public class Maze {
	private Random random; // Random maze
    private Vertex vertexList[]; // Stores each Vertex
    private Vertex adjMatrix[][]; // 2D representation of maze
    private int adjMatrixSize; // Size of the maze

    //Generates random number between 0 and 1
    public double myRandom() {
        return random.nextDouble();
    }
    
    // Constructs the Maze
    public Maze(int size) {
        random = new Random();
        vertexList = new Vertex[size * size];
        adjMatrix = new Vertex[size][size];
        adjMatrixSize = adjMatrix.length - 1;

        int count = 0;

        for (int j = 0; j < size; j++) {
            for (int k = 0; k < size; k++) { // matrix to 0
                Vertex vertex = new Vertex(j, k);
                adjMatrix[j][k] = vertex;
                vertexList[count] = vertex;
                count++;
            }
        }
        createMaze();
    }

    //Creates the maze
    public void createMaze() {
        // Creates stack
        Stack<Vertex> vertexStack = new Stack<Vertex>();
        Vertex currentVertex = adjMatrix[0][0];
        int visitedVertex = 1;
        int totalVertex = vertexList.length;

        while (visitedVertex < totalVertex) {
            List<Vertex> neighborList = findNeighbors(currentVertex);
            Vertex selectedVertex = currentVertex;

            // Continues and goes to the next Vertex
            if (neighborList.size() > 0 ) {
                vertexStack.push(currentVertex);
                int Value = (int) (myRandom() * neighborList.size());
                currentVertex = neighborList.get(Value);
                breakWalls(currentVertex, selectedVertex);
                currentVertex.adjList.add(selectedVertex);
                selectedVertex.adjList.add(currentVertex);
                visitedVertex++;
            }
            else {
                currentVertex = vertexStack.pop();
            }
        }

        adjMatrix[0][0].UP = false;
        adjMatrix[adjMatrixSize][adjMatrixSize].DOWN = false;
    }

    //Prints out the maze 
    public String printMaze() {
        String maze = "";
        int size = adjMatrix.length;

        for (int i = 0; i < size; i++) {
            maze += (i == 0) ? "+ " : "+-";
        }
        maze += "+\n";

        for (int i = 0; i < size; i++) {
            maze += "|";
            for (int j = 0; j < size - 1; j++){
                maze += (adjMatrix[j][i].step > -1) ? adjMatrix[j][i].step % 10 : " ";
                maze += (adjMatrix[j][i].RIGHT) ?  "|" : " ";
            }
            maze += (adjMatrix[size - 1][i].step > -1) ? adjMatrix[size-1][i].step % 10 + "|\n+" : " |\n+";
            if (i < size - 1) {
                for (int j = 0; j < size; j++) {
                    maze += (adjMatrix[j][i].DOWN) ? "-+" :" +";
                }
                maze += "\n";
            }
        }

        for (int i = 0; i < size; i++) {
            maze += (!adjMatrix[i][size-1].DOWN) ? " ":"-+";
        }

        maze += "+";
        return maze;
    }

    //Solves the given Maze
    public String solveMaze() {
        String maze = "";
        int size = adjMatrix.length;

        // Top part of the maze
        for (int i = 0; i < size; i++){
            maze += (i == 0) ? "+#" : "+-";
        }
        maze += "+\n";

        // Prints sides and bottom part of the maze
        for (int i = 0; i < size; i++){
            maze += "|";
            for (int j = 0; j < size - 1; j++){
                if (adjMatrix[j][i].RIGHT) {
                    maze += (adjMatrix[j][i].value.equals("#")) ? "#|" : " |";
                }
                else {
                    if (adjMatrix[j][i].value.equals("#")) {
                        maze += "#";
                        maze += (adjMatrix[j + 1][i].value.equals("#")) ? "#" : " ";
                    }
                    else maze += "  ";
                }
            }

            maze += adjMatrix[size - 1][i].value.equals("#") ? "#|\n+" : " |\n+";
            if (i < size - 1) {
                for (int j = 0; j < size; j++) {
                    if (adjMatrix[j][i].DOWN) maze += "-";
                    else maze += (adjMatrix[j][i].value.equals("#")) ? "#" :" ";
                    maze += "+";
                }
                maze += "\n";
            }
        }

        for (int i = 0; i < size; i++) {
            maze += (!adjMatrix[i][size-1].DOWN) ? "#":"-+";
        }

        maze += "+";
        return maze;
    }

    // Find all neighbor vertex
    public List<Vertex> findNeighbors(Vertex givenVertex) {
        List<Vertex> neighborList = new ArrayList<Vertex>();
        int matrixSize = adjMatrixSize;

        //Checking all Vertices in the Neighborhood and adding them to the list only if they are full.
        if (givenVertex.y < matrixSize && checkAllNeighbors(adjMatrix[givenVertex.x][givenVertex.y + 1])) {
            neighborList.add(adjMatrix[givenVertex.x][givenVertex.y + 1]);
        }

        if (givenVertex.x < matrixSize && checkAllNeighbors(adjMatrix[givenVertex.x + 1][givenVertex.y])) {
            neighborList.add((adjMatrix[givenVertex.x + 1][givenVertex.y]));
        }

        if (givenVertex.y > 0 && checkAllNeighbors(adjMatrix[givenVertex.x][givenVertex.y - 1])) {
            neighborList.add((adjMatrix[givenVertex.x][givenVertex.y - 1]));
        }

        if (givenVertex.x > 0 && checkAllNeighbors(adjMatrix[givenVertex.x - 1][givenVertex.y])) {
            neighborList.add((adjMatrix[givenVertex.x - 1][givenVertex.y]));
        }

        return neighborList;
    }
    
    // Resets the maze
    
    public void reset() {
        for (int i = 0; i < vertexList.length; i++) {
            vertexList[i].color = VertexColor.WHITE;
            vertexList[i].step = -1;
            vertexList[i].previous = null;
        }
    }
    
    // Check if the given neighbor is already visited or not
    public boolean checkAllNeighbors(Vertex givenVertex) {
        return givenVertex.UP && givenVertex.DOWN && givenVertex.LEFT && givenVertex.RIGHT;
    }

    //Breaks down the walls
    public void breakWalls(Vertex v1, Vertex v2) {
        int size = adjMatrixSize;

        if (v1.x < size && adjMatrix[v1.x + 1][v1.y].equals(v2)) {
            v1.RIGHT = false;
            v2.LEFT = false;
        }

        if (v1.y < size && adjMatrix[v1.x][v1.y + 1].equals(v2)) {
            v1.DOWN = false;
            v2.UP = false;
        }

        if (v1.x > 0 && adjMatrix[v1.x - 1][v1.y].equals(v2)) {
            v1.LEFT = false;
            v2.RIGHT = false;
        }

        if (v1.y > 0 && adjMatrix[v1.x][v1.y - 1].equals(v2)) {
            v1.UP = false;
            v2.DOWN = false;
        }
    }


    // Prints the Breadth-first Search maze
    public String printBFS() {
        
        reset(); // Reset the maze
      
        Vertex currentVertex = vertexList[0];     // Pushes currentVertex to the stack
        Queue<Vertex> vertexStack = new LinkedList<Vertex>();
        vertexStack.add(currentVertex);
        int step = 0;

        while(!vertexStack.isEmpty() && !currentVertex.equals(vertexList[vertexList.length - 1])) {
            // Remove it, change color to black, and increment step
            currentVertex = vertexStack.remove();
            currentVertex.color = VertexColor.BLACK;
            currentVertex.step = step;
            step++;
           
            for (Vertex v : currentVertex.adjList){
                if (v.color == VertexColor.WHITE){	 // if color is white, change to gray
                    v.color = VertexColor.GRAY;
                    v.previous = currentVertex;
                    vertexStack.add(v);			 // Add vertex to the stack
                }
            }
        }

        while(currentVertex != vertexList[0]) {
            currentVertex.value = "#";
            currentVertex = currentVertex.previous;
        }
        currentVertex.value = "#";

        // Prints out DFS
        System.out.println("BFS:");
        System.out.println(printMaze());
        System.out.println();
        return solveMaze();
    }

    //Prints Depth-first Search maze
    public String printDFS() {
        
        reset();
        Vertex currentVertex = vertexList[0];
        Stack<Vertex> vertexStack = new Stack<>();
        vertexStack.push(currentVertex);
        int step = 0;

        while (!vertexStack.isEmpty() && !currentVertex.equals(vertexList[vertexList.length - 1])) {
            currentVertex = vertexStack.pop();
            currentVertex.color = VertexColor.BLACK;
            currentVertex.step = step;
            step++;

           
            for (Vertex v: currentVertex.adjList) {
                if (v.color == VertexColor.WHITE) {
                    v.color = VertexColor.GRAY;
                    v.previous = currentVertex;
                    vertexStack.add(v);
                }
            }
        }
        
        while(currentVertex != vertexList[0]) {
            currentVertex.value = "#";
            currentVertex = currentVertex.previous;
        }
        currentVertex.value = "#";

        // Prints out DFS, maze with numbers, maze with #
        System.out.println("\n\nDFS:");
        System.out.println(printMaze());
        System.out.println();
        return solveMaze();
    }

}
