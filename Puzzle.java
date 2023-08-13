import java.util.Random;
import java.util.Scanner;
public class Puzzle {
	private Node[] correctNodes = new Node[6];
	private Node[] resetNodes;
	private String[] colorOptions = {"Red", "Orange", "Yellow", "Blue", "Green", "Purple"};
	private Node[] guessNodes = new Node[6];	
	private int[] correctTally = new int[6];
	private Scanner scan = new Scanner(System.in);
	
	/*
	 * randomly selects 6 of the colors from the options of color
	 *  to be the correct answer in the set order
	 *  
	 *  makes resetNodes, a copy of correctNodes that is reused every time correctNodes
	 *  is altered by other functions
	 */
	public Puzzle() {
		Random r = new Random();
		for (int i = 0; i < correctNodes.length; i++) {
			String color = colorOptions[r.nextInt(colorOptions.length)];
			correctNodes[i] = new Node(color);
		}
		resetNodes = correctNodes;
		
	}
	
	/*
	 * @param n
	 * the node we want to see if present in correctNodes
	 * 
	 * checks through correct nodes to determine if a certain node color is within the solution.
	 * Makes sure the node doesn't make a false positive for a node that already has a correct node
	 * 
	 * @return index of node with same color
	 */
	private int contains(Node n) {
		for (int i = 0; i < correctNodes.length; i++) {
			if (correctNodes[i].equals(n) && !correctNodes[i].equals(guessNodes[i])) {
				Node e = new Node("Empty");
				correctNodes[i] = e;
				return i;
			}
		}
		return -1;
	}
	
	/*
	 * @param n
	 * the node we want to compare to the correct array
	 * 
	 * @param index 
	 * the index of the node so we can compare it to its corresponding node in correct array
	 */
	private boolean equals(Node n, int index) {
		if (correctNodes[index].equals(n)) {
			Node e = new Node("Empty");
			correctNodes[index] = e;
			return true;
		}
		return false;
	}
	
	
	/*
	 * 
	 * asks for six colors, then compares to see if any are in right spot or if the color is present in code
	 * 
	 * depending on the answer the tally array keeps track of correct colors and locations, correct colors but wrong locations,
	 * and all other cases
	 * 
	 * then uses print function to display these amounts
	 */
	public boolean playRound() {
		int count = 0;
		System.out.println("Pick 6 colors: Red, Orange, Yellow, Green, Blue, or Purple.\n");
		while (count < 6) {
			System.out.print("Pick a color (" + (count+1) + "/6): ");
			String color = scan.next();
			boolean valid = false;
			
			for (String c: colorOptions) {
				if (c.substring(0,1).equals(color.substring(0,1))) {
					valid = true;
					Node n = new Node(c);
					guessNodes[count] = n;
					break;
				}
			}
			
			if (valid) {
				count++;
			}
			
			else {
				System.out.println("Invalid color.");
			}
		}
		return checkGuess();
	}
	
	private boolean checkGuess() {
		int correctCount = 0;
		
		for (int i = 0; i < correctNodes.length; i++) {
			if (equals(guessNodes[i], i)) {
				correctTally[i] = 2;
				correctCount++;
			}
			else {
				int index = contains(guessNodes[i]);
				if (index >= 0) {
					correctTally[i] = 1;
				}
				else {
					correctTally[i] = 0;
				}
			}
		}
		correctNodes = resetNodes;
		System.out.println(this);
		if (correctCount == correctNodes.length) {
			return true;
		}
		
		return false;
	}
	
	/*
	 * loops through the tally array counting correct spots and colors (2), correct colors but wrong spots (1), or incorrect (-1)
	 */
	public String toString() {
		String response = "";
		response += "Previous attempt: \n";
		for (Node n: guessNodes) {
			response += n.getColor() + " ";
		}
		response += "\n";
		int correct = 0;
		int wrongSpot = 0;
		for (int i = 0; i < correctTally.length; i++) {
			if (correctTally[i] == 2) {
				correct++;
			} 
			if (correctTally[i] == 1) {
				wrongSpot++;
			}
		}
		response += correct + " pegs in the right spot.\n";
		response += wrongSpot + " pegs in the puzzle, but in incorrect spot.\n";
		return response;
	}
}
