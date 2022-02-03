package nearestAndBranchBound;

import java.util.ArrayList;
import java.util.List;


public class Path {
	 private Vertex startVertex; // The first vertex in course
     private Vertex currentVertex; // present vertex
     private List<Vertex> pathList = new ArrayList<>(); // List of Vertexs
     
     // Constructor with startVertex
     public Path(Vertex vertex) {
         this.startVertex = vertex;
         this.currentVertex = vertex;
         this.pathList.add(vertex);
     }

     // Constructor without vertex
     public Path() {
         this.startVertex = null;
     }

     public Vertex getStartVertex() {
         return startVertex;
     }

     public Vertex getCurrentVertex() {
         return currentVertex;
     }

     public void setCurrentVertex(Vertex currentVertex) {
         this.currentVertex = currentVertex;
     }

     public List<Vertex> getCourseList() {
         return pathList;
     }

     @Override
     public String toString() {
         String str = "";
         if (!pathList.isEmpty()) {
             // Shortest  course for display
             for (Vertex v : pathList) str += v.getVertexIndex() + ", ";
             // Remove comma and space from end of string
             str = str.substring(0, str.length() - 2);
         }
         return "Path=[" + str + ']';
     }
 
}
