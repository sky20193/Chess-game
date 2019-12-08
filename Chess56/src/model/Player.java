package model;

/**
 * Represents a player.
 * 
 * @author Akashkumar Patel
 * @author Umang Patel
 */
public class Player {
	private String color;
	
	/**
	 * Create a player of a given color.
	 * @param c The color of the player's pieces.
	 */
	public Player(String c) {
		color = c;
	}
	
	/**
	 * Get color of the player's pieces.
	 * @return The color of the pieces.
	 */
	public String getColor() {
		return color;
	}
}
