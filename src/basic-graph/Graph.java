
package basicgraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import util.GraphLoader;

// An abstract class that implements a directed graph.

public abstract class Graph {

	private int numVertices;
	private int numEdges;
	//optional labels to vertices 
	private Map<Integer,String> vertexLabels;
	
	public Graph() {
		numVertices = 0;
		numEdges = 0;
		vertexLabels = null;
	}

	public int getNumVertices() {
		return numVertices;
	}
	
	
	public int getNumEdges() {
		return numEdges;
	}
	

	public int addVertex() {
		implementAddVertex();
		numVertices ++;
		return (numVertices-1);
	}
	

	public abstract void implementAddVertex();
	
	public void addEdge(int v , int w) {
		numEdges ++;
		if (v < numVertices && w < numVertices) {
			implementAddEdge(v , w);			
		}
		else {
			throw new IndexOutOfBoundsException();
		}
	}
	

	public abstract void implementAddEdge(int v, int w);
	
	// Get all out-neighbors of a given vertex.
	public abstract List<Integer> getNeighbors(int v); 
	
	// Get all in-neighbors of a given vertex
	public abstract List<Integer> getInNeighbors(int v);
	
	 // return The degree sequence of this graph.
	public List<Integer> degreeSequence() {
		// To do
		int outDegreeCount=0;
		int inDegreeCount=0;
		return null;
	}
	
	// Get all the vertices 2 hops away
	public abstract List<Integer> getDistance2(int v); 

	public String toString() {
		String s = "\nGraph with " + numVertices + " vertices and " + numEdges + " edges.\n";
		s += "Degree sequence: " + degreeSequence() + ".\n";
		if (numVertices <= 20) s += adjacencyString();
		return s;
	}

	
	public abstract String adjacencyString();

	
	// The next methods implement labeled vertices.
	// Basic graphs may or may not have labeled vertices.
	
	/**
	 * Create a new map of vertex indices to string labels
	 * (Optional: only if using labeled vertices.)
	 */
	public void initializeLabels() {
		vertexLabels = new HashMap<Integer,String>();
	}	
	/**
	 * Test whether some vertex in the graph is labeled 
	 * with a given index.
	 * @param The index being checked
	 * @return True if there's a vertex in the graph with this index; false otherwise.
	 */
	public boolean hasVertex(int v)
	{
		return v < getNumVertices();
	}
	
	/**
	 * Test whether some vertex in the graph is labeled 
	 * with a given String label
	 * @param The String label being checked
	 * @return True if there's a vertex in the graph with this label; false otherwise.
	 */
	public boolean hasVertex(String s)
	{
		return vertexLabels.containsValue(s);
	}
	
	/**
	 * Add label to an unlabeled vertex in the graph.
	 * @param The index of the vertex to be labeled.
	 * @param The label to be assigned to this vertex.
	 */
	public void addLabel(int v, String s) {
		if (v < getNumVertices() && !vertexLabels.containsKey(v)) 
		{
			vertexLabels.put(v, s);
		}
		else {
			System.out.println("ERROR: tried to label a vertex that is out of range or already labeled");
		}
	}
	
	/**
	 * Report label of vertex with given index
	 * @param The integer index of the vertex
	 * @return The String label of this vertex 
	 */
	public String getLabel(int v) {
		if (vertexLabels.containsKey(v)) {
			return vertexLabels.get(v);
		}
		else return null;
	}

	/**
	 * Report index of vertex with given label.
	 * (Assume distinct labels for vertices.)
	 * @param The String label of the vertex
	 * @return The integer index of this vertex 
	 */
	public int getIndex(String s) {
		for (Map.Entry<Integer,String> entry : vertexLabels.entrySet()) {
			if (entry.getValue().equals(s))
				return entry.getKey();
		}
		System.out.println("ERROR: No vertex with this label");
		return -1;
	}
	

	
	public static void main (String[] args) {
		GraphLoader.createIntersectionsFile("data/maps/myucsd.map", "data/intersections/myucsd.intersections");
		

		// For testing of Part 1 functionality
		// Add your tests here to make sure your degreeSequence method is returning
		// the correct list, after examining the graphs.
		System.out.println("Loading graphs based on real data...");
		System.out.println("Goal: use degree sequence to analyse graphs.");
		
		System.out.println("****");
		System.out.println("Roads / intersections:");
		GraphAdjList graphFromFile = new GraphAdjList();
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", graphFromFile);
		System.out.println(graphFromFile);
		
		System.out.println("Observe all degrees are <= 12.");
		System.out.println("****");

		System.out.println("\n****");
		
		// You can test with real road data here.  Use the data files in data/maps
		
		System.out.println("Flight data:");
		GraphAdjList airportGraph = new GraphAdjList();
		GraphLoader.loadRoutes("data/airports/routesUA.dat", airportGraph);
		System.out.println(airportGraph);
		System.out.println("Observe most degrees are small (1-30), eight are over 100.");
		System.out.println("****");
		
		//For testing Part 2 functionality
		// Test your distance2 code here.
		System.out.println("Testing distance-two methods on sample graphs...");
		System.out.println("Goal: implement method using two approaches.");


		
	}
}
