
public class Node {
	
	private String color;
	
	public Node(String c) {
		color = c;
	}
	
	/*
	 * returns the color of the node
	 */
	public String getColor() {
		return color;
	}
	
	/*
	 * @param n
	 * the node we are comparing the current node to
	 * 
	 * determines if two nodes have the same color
	 */
	public boolean equals(Node n) {
		return (n.getColor().equals(getColor()));
	}
}
