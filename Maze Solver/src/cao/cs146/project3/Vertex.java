package cao.cs146.project3;

import java.util.LinkedList;

//Vertex Colors
enum VertexColor {
 WHITE, GRAY, BLACK;
}

//Vertex Class
public class Vertex {
 VertexColor color = VertexColor.WHITE; // default color = White
 LinkedList<Vertex> adjList;
 Vertex previous;
 String value; //value of the vertex (ex: "#" or " ")
 int x; 
 int y; 
 int step; 
 
//Directions for the walls
boolean UP = true;
boolean DOWN = true;
boolean LEFT = true;
boolean RIGHT = true;
 

 public Vertex(int row, int column) {
     this.x = row;
     this.y = column;
     this.value = "";
     adjList = new LinkedList<>();
     this.previous = null;
     this.step = -1;
 }
}
