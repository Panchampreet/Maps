package basicgraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Class that implements a directed graph with adjacency matrix representation

public class GraphAdjMatrix extends Graph {

	private final int defaultNumVertices = 5;
	private int[][] adjMatrix;
	/
	public GraphAdjMatrix () {
		adjMatrix = new int[defaultNumVertices][defaultNumVertices];
	}
	
	// If there's need to increase dimensions of matrix, double them to amortize cost
	public void implementAddVertex() {
		int v = getNumVertices();
		if (v >= adjMatrix.length) {
			int[][] newAdjMatrix = new int[v*2][v*2];
			for (int i = 0; i < adjMatrix.length; i ++) {
				for (int j = 0; j < adjMatrix.length; j ++) {
					newAdjMatrix[i][j] = adjMatrix[i][j];
				}
			}
			adjMatrix = newAdjMatrix;
		}
		for (int i=0; i < adjMatrix[v].length; i++) {
			adjMatrix[v][i] = 0;
		}
	}
	
	// Multiple edges allowed between two points
	public void implementAddEdge(int v, int w) {
		adjMatrix[v][w] += 1;
	}
	
	/** If there are multiple edges between the vertex
	 * and one of its out-neighbors, this neighbor
	 * appears once in the list for each of these edges.
	 */
	public List<Integer> getNeighbors(int v) {
		List<Integer> neighbors = new ArrayList<Integer>();
		for (int i = 0; i < getNumVertices(); i ++) {
			for (int j=0; j< adjMatrix[v][i]; j ++) {
				neighbors.add(i);
			}
		}
		return neighbors;
	}
	
// Multiple-edges handling same as above method
	public List<Integer> getInNeighbors(int v) {
		List<Integer> inNeighbors = new ArrayList<Integer>();
		for (int i = 0; i < getNumVertices(); i ++) {
			for (int j=0; j< adjMatrix[i][v]; j++) {
				inNeighbors.add(i);
			}
		}
		return inNeighbors;
	}
	
	// To do
	public List<Integer> getDistance2(int v) {
		return null;
	}
	
	public String adjacencyString() {
		int dim = adjMatrix.length;
		String s = "Adjacency matrix";
		s += " (size " + dim + "x" + dim + " = " + dim* dim + " integers):";
		for (int i = 0; i < dim; i ++) {
			s += "\n\t"+i+": ";
			for (int j = 0; j < adjMatrix[i].length; j++) {
			s += adjMatrix[i][j] + ", ";
			}
		}
		return s;
	}

}

