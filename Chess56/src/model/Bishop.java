package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Bishop chess piece.
 * 
 * @author Akashkumar Patel
 * @author Umang Patel
 *
 */
public class Bishop extends Piece {
	
	/**
	 * Create a bishop.
	 * @param owner The player that owns the piece.
	 */
	public Bishop(Player owner) {
		super(owner);
	}
	
	public List<Integer> getPossibleMoves(Board board, int fromSquare) {
		int rank = fromSquare / 10;
		rank *= 10;
		int file = fromSquare % 10;
		List<Integer> moveList = new ArrayList<Integer>();
		
		while(rank < 70 && file < 7) {
			rank += 10;
			file += 1;
			if(board.getPiece(rank+file) != null) {
				if(!board.getPiece(rank+file).getColor().equals(getColor()))
					moveList.add(rank+file);
				break;
			}
			else
				moveList.add(rank+file);
		}
		
		rank = fromSquare / 10;
		rank *= 10;
		file = fromSquare % 10;
		while(rank < 70 && file > 0) {
			rank += 10;
			file -= 1;
			if(board.getPiece(rank+file) != null) {
				if(!board.getPiece(rank+file).getColor().equals(getColor()))
					moveList.add(rank+file);
				break;
			}
			else
				moveList.add(rank+file);
		}
		
		rank = fromSquare / 10;
		rank *= 10;
		file = fromSquare % 10;
		while(rank > 0 && file > 0) {
			rank -= 10;
			file -= 1;
			if(board.getPiece(rank+file) != null) {
				if(!board.getPiece(rank+file).getColor().equals(getColor()))
					moveList.add(rank+file);
				break;
			}
			else
				moveList.add(rank+file);
		}
		
		rank = fromSquare / 10;
		rank *= 10;
		file = fromSquare % 10;
		while(rank > 0 && file < 7) {
			rank -= 10;
			file += 1;
			if(board.getPiece(rank+file) != null) {
				if(!board.getPiece(rank+file).getColor().equals(getColor()))
					moveList.add(rank+file);
				break;
			}
			else
				moveList.add(rank+file);
		}
		
		return moveList;
	}
	
	public List<Integer> getPossibleAttacks(Board board, int fromSquare) {
		return getPossibleMoves(board, fromSquare);
	}
	
	public String toString() {
		return super.toString() + "B";
	}
}
