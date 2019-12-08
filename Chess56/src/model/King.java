package model;

import java.util.ArrayList;
import java.util.List;

/**
 * King chess piece.
 * 
 * @author Akashkumar Patel
 * @author Umang Patel
 *
 */
public class King extends Piece {
	
	/**
	 * Create a king.
	 * @param owner The player that owns the piece.
	 */
	public King(Player owner) {
		super(owner);
	}
	
	public List<Integer> getPossibleMoves(Board board, int fromSquare) {
		int rank = fromSquare / 10;
		rank *= 10;
		int file = fromSquare % 10;
		List<Integer> moveList = new ArrayList<Integer>();
		
		if(file+1 < 8)
			if(board.getPiece(rank+file+1) == null || !board.getPiece(rank+file+1).getColor().equals(getColor()))
				moveList.add(rank+file+1);
		
		if(file-1 >= 0)
			if(board.getPiece(rank+file-1) == null || !board.getPiece(rank+file-1).getColor().equals(getColor()))
				moveList.add(rank+file-1);
		
		if(rank+10 < 80) {
			if(board.getPiece(rank+10+file) == null || !board.getPiece(rank+10+file).getColor().equals(getColor()))
				moveList.add(rank+10+file);
			
			if(file+1 < 8)
				if(board.getPiece(rank+10+file+1) == null || !board.getPiece(rank+10+file+1).getColor().equals(getColor()))
					moveList.add(rank+10+file+1);
			
			if(file-1 >= 0)
				if(board.getPiece(rank+10+file-1) == null || !board.getPiece(rank+10+file-1).getColor().equals(getColor()))
					moveList.add(rank+10+file-1);
		}
		
		if(rank-10 >= 0) {
			if(board.getPiece(rank-10+file) == null || !board.getPiece(rank-10+file).getColor().equals(getColor()))
				moveList.add(rank-10+file);
			
			if(file+1 < 8)
				if(board.getPiece(rank-10+file+1) == null || !board.getPiece(rank-10+file+1).getColor().equals(getColor()))
					moveList.add(rank-10+file+1);
			
			if(file-1 >= 0)
				if(board.getPiece(rank-10+file-1) == null || !board.getPiece(rank-10+file-1).getColor().equals(getColor()))
					moveList.add(rank-10+file-1);
		}
		
		if(!hasMoved()) {
			if(board.getPiece(rank+file+1) == null && board.getPiece(rank+file+2) == null) {
				if(board.getPiece(rank+file+3) != null &&
					board.getPiece(rank+file+3) instanceof Rook &&
					!board.getPiece(rank+file+3).hasMoved()) {
					moveList.add(rank+file+2);
					moveList.add(-1*(rank+file+2));
				}
			}
			
			if(board.getPiece(rank+file-1) == null &&
				board.getPiece(rank+file-2) == null &&
				board.getPiece(rank+file-3) == null) {
				if(board.getPiece(rank+file-4) != null &&
					board.getPiece(rank+file-4) instanceof Rook &&
					!board.getPiece(rank+file-4).hasMoved()) {
					moveList.add(rank+file-2);
					moveList.add(-1*(rank+file-2));
				}
			}
		}
		
		return moveList;
	}
	
	public List<Integer> getPossibleAttacks(Board board, int fromSquare) {
		return getPossibleMoves(board, fromSquare);
	}
	
	public String toString() {
		return super.toString() + "K";
	}
}
