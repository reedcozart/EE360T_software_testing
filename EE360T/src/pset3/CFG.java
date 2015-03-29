package pset3;

import java.util.*;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
public class CFG {
	Set<Node> nodes = new HashSet<Node>();
	Map<Node, Set<Node>> edges = new HashMap<Node, Set<Node>>();
	
	public static class Node {
		int position;
		Method method;
		JavaClass clazz;
		Node(int p, Method m, JavaClass c) {
			position = p;
			method = m;
			clazz = c; }
		public Method getMethod() {
			return method;
		}
		public JavaClass getClazz() {
			return clazz;
		}
		public boolean equals(Object o) {
			if (!(o instanceof Node)) return false;
			Node n = (Node)o;
			return (position == n.position) && method.equals(n.method) && clazz.equals(n.clazz);
		}
		public int hashCode() {
			return position + method.hashCode() + clazz.hashCode();
		}
		public String toString() {
			return clazz.getClassName() + "." + method.getName() + method.getSignature() + ": " + position;
		} }
	
	public void addNode(int p, Method m, JavaClass c) {
		addNode(new Node(p, m, c));
	}
	private void addNode(Node n) {
		nodes.add(n);
		Set<Node> nbrs = edges.get(n);
		if (nbrs == null) {
			nbrs = new HashSet<Node>();
			edges.put(n, nbrs);
		}
	}
	public void addEdge(int p1, Method m1, JavaClass c1, int p2, Method m2, JavaClass c2) {
		Node n1 = new Node(p1, m1, c1);
		Node n2 = new Node(p2, m2, c2);
		addNode(n1);
		addNode(n2);
		Set<Node> nbrs = edges.get(n1);
		nbrs.add(n2);
		edges.put(n1, nbrs);
	}
	public void addEdge(int p1, int p2, Method m, JavaClass c) {
		addEdge(p1, m, c, p2, m, c);
	}
	public String toString() {
		return nodes.size() + " nodes\n" + "nodes: " + nodes + "\n" + "edges: " + edges;
	}
	public boolean isReachable(String methodFrom, String clazzFrom, String methodTo, String clazzTo) {
		Set<Node> visited = new HashSet<Node>();
		
		
		boolean validArguments = checkArgumentsExist(methodFrom, clazzFrom, methodTo, clazzTo);
		if(!validArguments) return validArguments;
		
		Node start = getStartNode(methodFrom, clazzFrom);
		if(start == null){
			//System.out.println("getStartNode() returned null. PROGRAM NOT WORKING");
			return false;
		}
		
		Node target = getTargetNode(methodTo, clazzTo);
		if(target == null){
			//System.out.println("getTargetNode(); returned null. PROGRAM NOT WORKING");
			return false;
		}
		
		//System.out.println("starting recursiveSearch()");
		boolean result = recursiveSearch(visited, start, target); //TODO 
		return result;
	}
	private Node getTargetNode(String methodTo, String clazzTo) {
		Node target = null;
		Iterator<Node> i2 = nodes.iterator();
		while(i2.hasNext()){ //find target node in set of nodes
			Node n = i2.next();
			if(n.getMethod().getName().equals(methodTo) && n.getClazz().getClassName().equals(clazzTo) && n.position == 0){
				target = n;
				return target;
			}
		}
		return target;
	}
	private Node getStartNode(String methodFrom, String clazzFrom) {
		Node start = getTargetNode(methodFrom, clazzFrom);
		return start;
	}
	
	private boolean checkArgumentsExist(String methodFrom, String clazzFrom,
			String methodTo, String clazzTo) {
		boolean methodFromExists = false;
		boolean methodToExists = false;
		boolean clazzFromExists = false;
		boolean clazzToExists = false;
		
		Iterator<Node> i1 = nodes.iterator();
		while(i1.hasNext()){ //make sure methodFrom exists in the graph
			Node n = i1.next();
			if(n.getMethod().getName().equals(methodFrom)) { 
				methodFromExists = true; 
				//System.out.println("MethodFrom exists in the graph");
				break;
				
			}
		}
		if(!methodFromExists) return false;
		
		Iterator<Node> i2 = nodes.iterator();
		while(i2.hasNext()){ //make sure methodTo exists in the graph
			Node n = i2.next();
			if(n.getMethod().getName().equals(methodTo))  {
				methodToExists = true;
				//System.out.println("MethodTo exists in the graph");
				break;
			}
		}
		if(!methodToExists) return false;
		
		Iterator<Node> i3 = nodes.iterator();
		while(i3.hasNext()){ //make sure methodTo exists in the graph
			Node n = i3.next();
			if(n.getClazz().getClassName().equals(clazzFrom)){  
				clazzFromExists = true; 
				//System.out.println("clazzFrom exists in the graph");
				break;
			}
		}
		if(!clazzFromExists) return false;
		
		Iterator<Node> i4 = nodes.iterator();
		while(i4.hasNext()){ //make sure methodTo exists in the graph
			Node n = i4.next();
			if(n.getClazz().getClassName().equals(clazzTo)){  
				clazzToExists = true; 
				//System.out.println("clazzTo exists in the graph");
				break;
			}
		}
		if(!clazzToExists) return false;
		
		return true;
	}
	public void print(){
		System.out.println("CFG:");
		for(Node n: this.nodes){
			System.out.println("\tNode:");
			System.out.println("\t\t"+"Position: "+n.position);
			System.out.println("\tEdges:");
			System.out.println("\t\t"+edges.get(n)+"\n");
		}
	}
	private boolean recursiveSearch(Set<Node> visited, Node start, Node target){
		boolean result = false;
		if(visited == null){
			//System.out.println("visited was passed null");
			return false;
		}
		if(start == null){
			//System.out.println("start was passed null");
			return false;
		}
		if(target == null){
			//System.out.println("target was passed null");
			return false;
		}
		
		if(!visited.contains(start)){ //we have not visited this node yet
			visited.add(start);       // add to the list of visited nodes
			String startMethodName = start.getMethod().getName();
			String targetMethodName = target.getMethod().getName();
			if(startMethodName.equals(targetMethodName)){ // we've found what we're looking for! It's reachable!
				return true;
			}
			Set<Node> neighbors = edges.get(start); //get the neighbors 
			Iterator<Node> i = neighbors.iterator();
			while(i.hasNext()){
				Node n = i.next();
				result = recursiveSearch(visited, n, target);
				if(result) return result;
			}
		}
		//System.out.println("Search has ended with target not found");
		return result;
	}
}
