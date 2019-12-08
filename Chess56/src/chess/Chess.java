package chess;

/**
 * Entry point for chess application.
 * 
 * @author Akashkumar Patel
 * @author Umang Patel
 */
public class Chess {
	/**
	 * Starts and runs a game of chess.
	 * @param args Command line arguments: unused
	 */
	public static void main(String[] args) {
		Game game = new Game();
		game.play();
	}
}
