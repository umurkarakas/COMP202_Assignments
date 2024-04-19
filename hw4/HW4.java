import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

public class HW4 {

	// You can add any methods you need, both to this file and Graph.java file

	// The method for task 1 
	int totalLinkCost(Graph graph) {
		int sum = 0;
		for(Edge e: graph.edges) {
			sum += e.cost;
		}
		return sum;
	}

	// The method for task 2 
	int cheapestNetwork(Graph graph) {
		int sum = 0;
		for(int i = 0; i < graph.vertices.size(); i++) {
			graph.vertexMap.put(i, -1);
		}
		PriorityQueue<Edge> edges = new PriorityQueue<Edge>(graph.edges);
		TreeMap<String,Integer> vertexIndex = new TreeMap<String,Integer>();
		for(int i = 0; i < graph.vertices.size(); i++) {
			vertexIndex.put(graph.vertices.get(i), i);
		}
		while(edges.size() > 0) {
			Edge temp = edges.remove();
			if((graph.find(vertexIndex.get(temp.src)) != graph.find(vertexIndex.get(temp.dst)))) {
				graph.union(vertexIndex.get(temp.src), vertexIndex.get(temp.dst));
				sum += temp.cost;
			}
		}
		
		/*for(Map.Entry<Integer,Integer> entry: graph.vertexMap.entrySet()) {
			Integer key = entry.getKey();
			Integer value = entry.getValue();
			for(Map.Entry<String,Integer> entry1: vertexIndex.entrySet()) {
				String key1 = entry1.getKey();
				Integer value1 = entry1.getValue();
				if(key == value1) {
					System.out.println(key1 + "(" + key + ")" + "  =  " + value);
				} else {
					continue;
				}
			}
		} */
		
		return sum;
	}

	// The method for task 3 
	int savedAmount(Graph graph) {
		return this.totalLinkCost(graph) - this.cheapestNetwork(graph);
	}	
}


