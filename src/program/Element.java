package program;

import java.util.ArrayList;
import java.util.List;

public class Element {
	int idElement;
	List<Node> nodes = new ArrayList<Node>();                 //4 wêz³y w elemencie
	List<Integer> contactEdges = new ArrayList<Integer>();    //numery krawêdzi kontaktowych, maks. 2 krawêdzie
	
	public Element(int id){
		this.idElement=id;
	}
	
	public void addNode(Node node){
		nodes.add(node);
	}
	
	public void removeNode(Node node){
		nodes.remove(node);
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	
	public void checkContactEdges(){
		if(nodes.get(0).status && nodes.get(1).status){
			contactEdges.add(1);
		}
		if(nodes.get(1).status && nodes.get(2).status){
			contactEdges.add(2);
		}
		if(nodes.get(2).status && nodes.get(3).status){
			contactEdges.add(3);
		}
		if(nodes.get(3).status && nodes.get(0).status){
			contactEdges.add(4);
		}
	}
}
