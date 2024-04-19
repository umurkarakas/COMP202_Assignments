import java.util.Arrays;
import java.util.PriorityQueue;

public class HW4 {
	
	// You can add any methods you need, both to this file and Graph.java file

	// The method for task 1 
	int totalTransitTime(Graph graph) {
		int sum = 0;
		int vertexCount = graph.vertices.size();
		Double[][] distMatrix = new Double[vertexCount][vertexCount];
		for(int i = 0; i<vertexCount; i++) {
			Arrays.fill(distMatrix[i], Double.POSITIVE_INFINITY);
			distMatrix[i][i] = 0.0;
		}
		for(Edge e: graph.edges) {
			distMatrix[graph.vertices.indexOf(e.src)][graph.vertices.indexOf(e.dst)] = (double)e.latency;
			distMatrix[graph.vertices.indexOf(e.dst)][graph.vertices.indexOf(e.src)] = (double)e.latency;
		}
		//Initialization of distance matrix
		//System.out.printf("%f %f %f %f\n%f %f %f %f\n%f %f %f %f\n%f %f %f %f\n", distMatrix[0][0],distMatrix[0][1],distMatrix[0][2],distMatrix[0][3],distMatrix[1][0],distMatrix[1][1],distMatrix[1][2],distMatrix[1][3],distMatrix[2][0],distMatrix[2][1],distMatrix[2][2],distMatrix[2][3],distMatrix[3][0],distMatrix[3][1],distMatrix[3][2],distMatrix[3][3]);
		for(int k = 0; k<vertexCount; k++) {
			for(int i = 0;i<vertexCount; i++) {
				for(int j = 0; j<vertexCount; j++) {
					if(distMatrix[i][j] > distMatrix[i][k] + distMatrix[k][j]) {
						distMatrix[i][j] = distMatrix[i][k] + distMatrix[k][j];
					}
				}
			}
		}
		for(int i = 0;i<vertexCount; i++) {
			for(int j = 0; j<vertexCount; j++) {
				sum += distMatrix[i][j];
			}
		}
		//Final distance matrix
		//System.out.printf("%f %f %f %f\n%f %f %f %f\n%f %f %f %f\n%f %f %f %f\n", distMatrix[0][0],distMatrix[0][1],distMatrix[0][2],distMatrix[0][3],distMatrix[1][0],distMatrix[1][1],distMatrix[1][2],distMatrix[1][3],distMatrix[2][0],distMatrix[2][1],distMatrix[2][2],distMatrix[2][3],distMatrix[3][0],distMatrix[3][1],distMatrix[3][2],distMatrix[3][3]);
		
		return sum;
	}

	// The method for task 2 
	int cheapestTransitTime(Graph graph) {
		Graph MST = new Graph();
		for(int i = 0; i < graph.vertices.size(); i++) {
			graph.vertexMap.put(i, -1);
		}
		PriorityQueue<Edge> edges = new PriorityQueue<Edge>(graph.edges);
		while(edges.size() > 0) {
			Edge temp = edges.remove();
			if((graph.find(graph.vertices.indexOf(temp.src)) != graph.find(graph.vertices.indexOf(temp.dst)))) {
				graph.union(graph.vertices.indexOf(temp.src), graph.vertices.indexOf(temp.dst));
				MST.addEdge(temp);
			}
		}
		return totalTransitTime(MST);
	}

	// The method for task 3 
	int timeIncrease(Graph graph) {
		return cheapestTransitTime(graph) - totalTransitTime(graph);
	}
	
}
