package model;

import java.util.List;

/**
 * Abstract class representing a generic chess piece.
 * @author Umang Patel
 * @author Akashkumar Patel
 */
public abstract class Piece {
	String color;
	boolean hasMoved;
	
	public Piece(Player owner) {
		color = owner.getColor();
		hasMoved = false;
	}
	
	/**
	 * Returns the color of the piece.
	 * @return The color of the piece.
	 */
	public String getColor() {
		return color;
	}
	
	/**
	 * Indicate that a piece has moved atleast once.
	 */
	public void setHasMoved() {
		hasMoved = true;
	}
	
	/**
	 * Check if a piece has moved.
	 * @return true if the piece has moved atleast once, false otherwise.
	 */
	public boolean hasMoved() {
		return hasMoved;
	}
	
	/**
	 * Calculate which tiles a piece can move to on a given chessboard.
	 * @param board The chessboard.
	 * @param fromSquare The current location of the piece.
	 * @return A list of integers representing the tiles the piece can move to.
	 */
	public abstract List<Integer> getPossibleMoves(Board board, int fromSquare);
	
	/**
	 * Calculate which tiles a piece can potentially attack. Also includes those that the piece can move to, even if not occupied by an opponent's piece.
	 * @param board The chessboard.
	 * @param fromSquare The current location of the piece.
	 * @return A list of integers representing the tiles the piece can move to.
	 */
	public abstract List<Integer> getPossibleAttacks(Board board, int fromSquare);
	
	/**
	 * Returns string representation of the piece.
	 */
	public String toString() {
		String c = (color.equals("White"))? "w" : "b";
		
		return c;
	}
}
