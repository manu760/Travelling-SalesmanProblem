package nearestAndBranchBound;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
	 private int vertexIndex;			         //    index of the vertex.
     private boolean visited;                // flag is true if is visited
     private List<Double> distanceList = new ArrayList<>(); // List of distance from this vertex to each other vertex
     // Constructor without info
     public Vertex(){
     }
     // Constructor with info
     public Vertex(int index, boolean visited, List<Double> distanceList) {
         this.vertexIndex = index;
         this.visited = visited;
         this.distanceList = distanceList;
     }

     public int getVertexIndex() {
         return vertexIndex;
     }

     public boolean isVisited() {
         return visited;
     }

     public void setVisited(boolean visited) {
         this.visited = visited;
     }

     public double getDistance(int index){
         return this.distanceList.get(index);
     }
     @Override
     public String toString() {
         String str = "";
         if (!this.distanceList.isEmpty()) {
             // Shortest  course for display
             for (double distance : this.distanceList) str += distance + ", ";
             // Remove comma and space from end of string
             str = str.substring(0, str.length() - 2);
         }
         return "Vertex [ ID =" + vertexIndex + ", visited=" + visited + ", distanceList =" + str + "]";
     }
}
