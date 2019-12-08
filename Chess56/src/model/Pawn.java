package model;

import java.util.List;
import java.util.ArrayList;

/**
 * Pawn chess piece.
 * 
 * @author Akashkumar Patel
 * @author Umang Patel
 *
 */
public class Pawn extends Piece {
	
	/**
	 * Create a pawn.
	 * @param owner The player that owns the piece.
	 */
	public Pawn(Player owner) {
		super(owner);
	}
	
	public List<Integer> getPossibleMoves(Board board, int fromSquare) {
		int rank = fromSquare / 10;
		rank *= 10;
		int file = fromSquare % 10;
		List<Integer> moveList = new ArrayList<Integer>();
		
		if(getColor().equals("White")) {
			if(rank+10 < 80) {
				if(board.getPiece(rank+10+file) == null)
					moveList.add(rank+file+10);
				if(file+1 < 8) {
					if(board.getPiece(rank+10+file+1) != null)
						if(!board.getPiece(rank+10+file+1).getColor().equals(getColor()))
							moveList.add(rank+10+file+1);
				}
				if(file-1 >= 0) {
					if(board.getPiece(rank+10+file-1) != null)
						if(!board.getPiece(rank+10+file-1).getColor().equals(getColor()))
							moveList.add(rank+10+file-1);
				}
			}
			if(!hasMoved()) {
				if(board.getPiece(rank+20+file) == null)
					moveList.add(rank+file+20);
			}
			if(rank == 4) {
				if(file+1 < 8) {
					if(board.getPiece(rank+file+1) != null)
						if(board.getPiece(rank+file+1) instanceof Pawn &&
							!board.getPiece(rank+file+1).getColor().equals(getColor())) {
							moveList.add(rank+10+file+1);
							moveList.add(-1*(rank+10+file+1));
					}
				}
				if(file-1 >= 0) {
					if(board.getPiece(rank+file-1) != null)
						if(board.getPiece(rank+file-1) instanceof Pawn &&
							!board.getPiece(rank+file-1).getColor().equals(getColor())) {
							moveList.add(rank+10+file-1);
							moveList.add(-1*(rank+10+file-1));
					}
				}
			}
		}
		else {
			if(rank-10 >= 0) {
				if(board.getPiece(rank-10+file) == null)
					moveList.add(rank+file-10);
				if(file+1 < 8) {
					if(board.getPiece(rank-10+file+1) != null)
						if(!board.getPiece(rank-10+file+1).getColor().equals(getColor()))
							moveList.add(rank-10+file+1);
				}
				if(file-1 >= 0) {
					if(board.getPiece(rank-10+file-1) != null)
						if(!board.getPiece(rank-10+file-1).getColor().equals(getColor()))
							moveList.add(rank-10+file-1);
				}
			}
			if(!hasMoved()) {
				if(board.getPiece(rank-20+file) == null)
					moveList.add(rank+file-20);
			}
			if(rank == 3) {
				if(file+1 < 8) {
					if(board.getPiece(rank+file+1) != null)
						if(board.getPiece(rank+file+1) instanceof Pawn &&
							!board.getPiece(rank+file+1).getColor().equals(getColor())) {
							moveList.add(rank-10+file+1);
							moveList.add(-1*(rank-10+file+1));
					}
						
				}
				if(file-1 >= 0) {
					if(board.getPiece(rank+file-1) != null)
						if(board.getPiece(rank+file-1) instanceof Pawn &&
							!board.getPiece(rank+file-1).getColor().equals(getColor())) {
							moveList.add(rank-10+file-1);
							moveList.add(-1*(rank-10+file-1));
					}
				}
			}
		}
		
		return moveList;
	}
	
	public List<Integer> getPossibleAttacks(Board board, int fromSquare) {
		int rank = fromSquare / 10;
		int file = fromSquare % 10;
		List<Integer> moveList = new ArrayList<Integer>();
		
		if(getColor().equals("White")) {
			if(rank+10 < 80) {
				if(file+1 < 8) {
					if(board.getPiece(rank+10+file+1) != null &&
						!board.getPiece(rank+10+file+1).getColor().equals(getColor()))
						moveList.add(rank+10+file+1);
				}
				if(file-1 >= 0) {
					if(board.getPiece(rank+10+file-1) != null &&
						!board.getPiece(rank+10+file-1).getColor().equals(getColor()))
						moveList.add(rank+10+file-1);
				}
			}
		}
		else {
			if(rank-10 >= 0) {
				if(file+1 < 8) {
					if(board.getPiece(rank-10+file+1) != null &&
						!board.getPiece(rank-10+file+1).getColor().equals(getColor()))
						moveList.add(rank-10+file+1);
				}
				if(file-1 >= 0) {
					if(board.getPiece(rank-10+file-1) != null &&
						!board.getPiece(rank-10+file-1).getColor().equals(getColor()))
						moveList.add(rank-10+file-1);
				}
			}
		}
		
		return moveList;
	}
	
	public String toString() {
		return super.toString() + "p";
	}

}
