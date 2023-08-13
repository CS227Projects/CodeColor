
public class test {
	
	public static void main(String args[]) {
		Puzzle p = new Puzzle();
		boolean win = p.playRound();
		while (!win) {
			p.playRound();
		}
		
		System.out.println("Correct!");
	}
}
