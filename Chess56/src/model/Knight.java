package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Knight chess piece.
 * 
 * @author Akashkumar Patel
 * @author Umang Patel
 *
 */
public class Knight extends Piece {

	/**
	 * Create a knight.
	 * @param owner The player that owns the piece.
	 */
	public Knight(Player owner) {
		super(owner);
	}
	
	public List<Integer> getPossibleMoves(Board board, int fromSquare) {
		int rank = fromSquare / 10;
		rank *= 10;
		int file = fromSquare % 10;
		List<Integer> moveList = new ArrayList<Integer>();
		
		if(file-2 >= 0) {
			if(rank+10 < 80) {
				if(board.getPiece(rank+10+file-2) == null || !board.getPiece(rank+10+file-2).getColor().equals(getColor()))
					moveList.add(rank+10+file-2);
			}
			
			if(rank-10 >= 0) {
				if(board.getPiece(rank-10+file-2) == null || !board.getPiece(rank-10+file-2).getColor().equals(getColor()))
					moveList.add(rank-10+file-2);
			}
		}
		
		if(file+2 < 8) {
			if(rank+10 < 80) {
				if(board.getPiece(rank+10+file+2) == null || !board.getPiece(rank+10+file+2).getColor().equals(getColor()))
					moveList.add(rank+10+file+2);
			}
			
			if(rank-10 >= 0) {
				if(board.getPiece(rank-10+file+2) == null || !board.getPiece(rank-10+file+2).getColor().equals(getColor()))
					moveList.add(rank-10+file+2);
			}
		}
		
		if(rank+20 < 80) {
			if(file+1 < 8) {
				if(board.getPiece(rank+20+file+1) == null || !board.getPiece(rank+20+file+1).getColor().equals(getColor()))
					moveList.add(rank+20+file+1);
			}
			
			if(file-1 >= 0) {
				if(board.getPiece(rank+20+file-1) == null || !board.getPiece(rank+20+file-1).getColor().equals(getColor()))
					moveList.add(rank+20+file-1);
			}
		}
		
		if(rank-20 >= 0) {
			if(file+1 < 8) {
				if(board.getPiece(rank-20+file+1) == null || !board.getPiece(rank-20+file+1).getColor().equals(getColor()))
					moveList.add(rank-20+file+1);
			}
			
			if(file-1 >= 0) {
				if(board.getPiece(rank-20+file-1) == null || !board.getPiece(rank-20+file-1).getColor().equals(getColor()))
					moveList.add(rank-20+file-1);
			}
		}
		
		return moveList;
	}
	
	public List<Integer> getPossibleAttacks(Board board, int fromSquare) {
		return getPossibleMoves(board, fromSquare);
	}
	
	public String toString() {
		return super.toString() + "N";
	}

}
