package com.dag;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class DirectedAcyclicGraph{
	//Hashmap to store the source vertex and all the destination nodes in list
	private Map<String,List<String>> nodes = new HashMap<String,List<String>>();
	
	//Create a vertex if the vertex is not present
	public void addVertex (String node) {
        if (nodes.containsKey(node)) return;
        nodes.put(node, new ArrayList<String>());
    }
	
	//Method to add the edge and return true of the edge is added else return false
	public boolean addEdge(String source, String destination) {
		
		//Add vertex if the vertex is not present
        this.addVertex(source); 
        this.addVertex(destination);
        nodes.get(source).add(destination);
        
        //Check if the graph is a directed acyclic graph after each edge is added, if the edge is not added, the method will return false else true
        Boolean b = isDAG();
        if(b){
        	return true;
        }
        else{
        	nodes.get(source).remove(destination);
        	return false;
        }
    }
	
	//To check if the graph is a Directed Acyclic graph
	public boolean isDAG () {
        return topologicalSort() != null;
    }
	
	//Method for topological sort
	public List<String> topologicalSort () {
		//Map to maintain the indegree
        Map<String, Integer> deg = inDegree();
        
        //VErticas with 0 indegree
        Stack<String> zeroIndegree = new Stack<String>();     
        
        for (String vertex: deg.keySet()) {
            if (deg.get(vertex) == 0) 
            	zeroIndegree.push(vertex);
        }
        
        //The result which has the topological order
        List<String> result = new ArrayList<String>();
        while (!zeroIndegree.isEmpty()) {
            String v = zeroIndegree.pop();                  
            result.add(v);                          
           
            for (String node: nodes.get(v)) {
                deg.put(node, deg.get(node) - 1);
                
                if (deg.get(node) == 0) 
                	zeroIndegree.push(node);
            }
        }
       //Check on entire graph
        if (result.size() != nodes.size()) return null;
        return result;
    }
	
	//Indegree of each vertex as a map
	public Map<String,Integer> inDegree () {
        Map<String,Integer> res = new HashMap<String,Integer>();
        for (String node: nodes.keySet()) 
        	res.put(node, 0);       
        for (String source: nodes.keySet()) {
            for (String to: nodes.get(source)) {
            	//Increment the indegree by 1
                res.put(to, res.get(to) + 1);           
            }
        }
        return res;
    }
	
	//Main method
	public static void main (String[] args) {
        DirectedAcyclicGraph graph = new DirectedAcyclicGraph();
        Boolean result = graph.addEdge("A", "B");
        System.out.println(result);
        Boolean result2 = graph.addEdge("B", "C");
        System.out.println(result2);
        Boolean result3 = graph.addEdge("D", "C");
        System.out.println(result3);
        Boolean result4 = graph.addEdge("D", "C"); 
        System.out.println(result4);
        Boolean result5 = graph.addEdge("B", "D"); 
        System.out.println(result5);
        Boolean result6 = graph.addEdge("C", "A");
        System.out.println(result6);
        
    }
}