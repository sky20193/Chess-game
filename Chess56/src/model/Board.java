package model;

/**
 * This class represents a chessboard as a one dimensional array of "tiles" that hold pieces.
 * Each index of the array represents a different tile on the chessboard, and not all indices are used.
 * The index of a tile is represented as the sum of its rank and file, where rank ranges from 0 to 70 (increments of 10)
 * and file ranges from 0 to 7 (increments of 1)
 * 
 * Example board with indices:
 * 70 71 72 73 74 75 76 77 8
 * 60 61 62 63 64 65 66 67 7
 * 50 51 52 53 54 55 56 57 6
 * 40 41 42 43 44 45 46 47 5
 * 30 31 32 33 34 35 36 37 4
 * 20 21 22 23 24 25 26 27 3
 * 10 11 12 13 14 15 16 17 2
 * 00 01 02 03 04 05 06 07 1
 *  a  b  c  d  e  f  g  h
 * @author Akashkumar Patel
 * @author Umang Patel
 */
public class Board {
	private Piece[] board;
	
	/**
	 * Creates an empty board
	 */
	public Board() {
		board = new Piece[78];
		for(int i = 0; i < board.length; i++)
			board[i] = null;		
	}
	
	/**
	 * Allows a chess piece to be "added" (placed) onto the board at a given location.
	 * 
	 * @param location The index of the space the piece should be added to.
	 * @param p The chess piece to place.
	 */
	public void add(int location, Piece p) {
		board[location] = p;
	}
	
	/**
	 * Removes a chess piece from the board.
	 * @param location The index of the space to remove a piece from
	 */
	public void remove(int location) {
		board[location] = null;
	}
	
	/**
	 * Moves a piece from one space to another.
	 * @param from The location from which a piece will be found.
	 * @param to The location to move the piece to.
	 */
	public void move(int from, int to) {
		board[to] = board[from];
		board[from] = null;
	}
	
	/**
	 * Gets the piece instance at the given location. The piece is not removed from the board.
	 * @param location The space on the board to look at.
	 * @return The piece instance located in the given space.
	 */
	public Piece getPiece(int location) {
		return board[location];
	}
	
	/**
	 * Finds a piece on the board.
	 * @param p The piece instance to find.
	 * @return The integer value of the space the piece was located in.
	 */
	public int getLocation(Piece p) {
		for(int i = 0; i < board.length; i++)
			if(board[i] == p)
				return i;
		return -1;
	}
	
	/**
	 * Makes a copy of a Board instance.
	 * @param original The board to make a copy of.
	 * @return A new Board instance with the same pieces as the original board
	 */
	public static Board copy(Board original) {
		Board copiedboard = new Board();
		for(int i = 0; i < 78; i++)
			copiedboard.add(i, original.getPiece(i));
		
		return copiedboard;
	}
	
	/**
	 * Returns a string representation of the board.
	 */
	public String toString() {
		String out = "";
		
		for(int rank = 70; rank >= 0; rank -= 10) {
			for(int file = 0; file < 8; file++ ) {
				if(board[rank+file] == null)
				{
					if((rank / 10) % 2 == 0) {
						if(file % 2 == 0)
							out += "##";
						else
							out += "  ";
					}
					else {
						if(file % 2 == 1)
							out += "##";
						else
							out += "  ";
					}
				}
				else
					out += board[rank+file].toString();
				
				out += " ";
			}
			out += "" + (rank+10) / 10 + "\n";
		}
		
		out += " a  b  c  d  e  f  g  h\n\n";
		
		return out;
	}
}
