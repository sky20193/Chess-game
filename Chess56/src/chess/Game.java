package chess;

import model.*;

import java.util.List;
import java.util.Scanner;

/**
 * This class handles the main logic of the chess game.
 * 
 * @author Akashkumar Patel
 * @author Umang Patel
 */
public class Game {
	boolean gameOver;
	Player player1;
	Player player2;
	Player currentPlayer;
	Board board;
	private String lastMove;
	private static final int VALID = 1;
	private static final int CHECKMATE = 2;
	private static final int INVALID = 0;
	private static final int STALEMATE = 3;
	private static final int PROMOTION = 4;
	private static final int CASTLING = 5;
	private static final int ENPASSANT = 6;
	
	
	/**
	 * Initializes game object to default values of having a White player and a Black player with all 
	 * standard pieces in their default locations on a new chessboard.
	 */
	public Game() {
		gameOver = false;
		player1 = new Player("White");
		player2 = new Player("Black");
		currentPlayer = player1;
		board = new Board();
		
		//add white pieces to board
		board.add(00, new Rook(player1));
		board.add(01, new Knight(player1));
		board.add(02, new Bishop(player1));
		board.add(03, new Queen(player1));
		board.add(04, new King(player1));
		board.add(05, new Bishop(player1));
		board.add(06, new Knight(player1));
		board.add(07, new Rook(player1));
		board.add(10, new Pawn(player1));
		board.add(11, new Pawn(player1));
		board.add(12, new Pawn(player1));
		board.add(13, new Pawn(player1));
		board.add(14, new Pawn(player1));
		board.add(15, new Pawn(player1));
		board.add(16, new Pawn(player1));
		board.add(17, new Pawn(player1));
		
		//add black pieces to board
		board.add(70, new Rook(player2));
		board.add(71, new Knight(player2));
		board.add(72, new Bishop(player2));
		board.add(73, new Queen(player2));
		board.add(74, new King(player2));
		board.add(75, new Bishop(player2));
		board.add(76, new Knight(player2));
		board.add(77, new Rook(player2));
		board.add(60, new Pawn(player2));
		board.add(61, new Pawn(player2));
		board.add(62, new Pawn(player2));
		board.add(63, new Pawn(player2));
		board.add(64, new Pawn(player2));
		board.add(65, new Pawn(player2));
		board.add(66, new Pawn(player2));
		board.add(67, new Pawn(player2));
	}
	
	/**
	 * Core game loop that reads input and tracks the state of the game.
	 */
	public void play() {
		int result = 0;
		boolean drawRequested = false;
		Scanner scanner = new Scanner(System.in);
		
		while(!gameOver) {
			
			System.out.print(board);
			
			while(result != VALID) {
				System.out.print(currentPlayer.getColor() + "'s move: ");
				String input = scanner.nextLine();
				System.out.println();
				
				if(input.equals("resign")) {
					switchPlayers();
					System.out.println(currentPlayer.getColor() + " wins");
					gameOver = true;
					break;
				}
				
				if(input.equals("draw") && drawRequested) {
					gameOver = true;
					break;
				}
				
				result = playTurn(input);
				
				if(result == VALID) {
					switchPlayers();
					lastMove = input;
					if(isInCheck(currentPlayer, board))
						System.out.println("Check\n");
				} else if(result == CHECKMATE) {
					System.out.println("Checkmate");
					//switchPlayers();
					System.out.println(currentPlayer.getColor() + " wins");
					gameOver = true;
					break;
				} else if(result == STALEMATE) {
					System.out.println("Stalemate");
					System.out.println("draw");
					gameOver = true;
					break;
				} else if(result == INVALID) {
					System.out.println("Illegal move, try again");
					continue;
				}

				drawRequested = (input.endsWith("draw?"))? true : false;
			}
			result = INVALID;
		}
		scanner.close();
	}
	
	/**
	 * Validates a move and processes the turn, modifying the board as necessary.
	 * 
	 * @param move The input string representing the spaces to move to/from.
	 * @return The status of the move: returns a value representing whether the move was valid, invalid, or resulted in either checkmate or stalemate.
	 */
	public int playTurn(String move) {
		int fromSquare = Game.translate(move.substring(0, 2));
		int toSquare = Game.translate(move.substring(3,5));
		
		Player opponent = (currentPlayer == player1)? player2 : player1;
		
		if(board.getPiece(fromSquare) == null)
			return INVALID;
		
		if(!board.getPiece(fromSquare).getColor().equals(currentPlayer.getColor())) {
			return INVALID;
		}
		
		List<Integer> possibleMoves = board.getPiece(fromSquare).getPossibleMoves(board, fromSquare);
		
		if(!possibleMoves.contains(new Integer(toSquare))) {
			return INVALID;
		}
		
		
		int moveType = -1;
		
		if(board.getPiece(fromSquare) instanceof Pawn) {
			int lastRank = (currentPlayer.getColor().equals("White"))? 7 : 0;
			if((toSquare/10) == lastRank)
				moveType = PROMOTION;
		}
		
		if(possibleMoves.contains(new Integer(toSquare * -1))) {
			if(board.getPiece(fromSquare) instanceof King)
				moveType = CASTLING;
			else if(board.getPiece(fromSquare) instanceof Pawn)
				moveType = ENPASSANT;
		}
		
		if(moveType == -1) {
			Board copy = Board.copy(board);
			copy.move(fromSquare, toSquare);
			if(isInCheck(currentPlayer, copy))
				return INVALID;
			else {
				board.move(fromSquare, toSquare);
				board.getPiece(toSquare).setHasMoved();
			}
		} else if(moveType == PROMOTION) {
			Board copy = Board.copy(board);
			copy.move(fromSquare, toSquare);
			
			if(move.contains("N"))
				copy.add(toSquare, new Knight(currentPlayer));
			else if(move.contains("B"))
				copy.add(toSquare, new Bishop(currentPlayer));
			else if(move.contains("R"))
				copy.add(toSquare, new Rook(currentPlayer));
			else
				copy.add(toSquare, new Queen(currentPlayer));
			
			if(isInCheck(currentPlayer, copy))
				return INVALID;
			else {
				board.move(fromSquare, toSquare);
				board.remove(toSquare);
				if(move.contains("N"))
					board.add(toSquare, new Knight(currentPlayer));
				else if(move.contains("B"))
					board.add(toSquare, new Bishop(currentPlayer));
				else if(move.contains("R"))
					board.add(toSquare, new Rook(currentPlayer));
				else
					board.add(toSquare, new Queen(currentPlayer));
			}
		} else if(moveType == CASTLING) {
			if(isInCheck(currentPlayer, board))
				return INVALID;
			
			if(toSquare < fromSquare) {
				//queenside castling
				Board copy = Board.copy(board);
				copy.move(fromSquare, fromSquare-1);
				if(isInCheck(currentPlayer, copy))
					return INVALID;
				
				copy = Board.copy(board);
				copy.move(fromSquare, toSquare);
				copy.move(fromSquare-4, toSquare+1);
				if(isInCheck(currentPlayer, copy))
					return INVALID;
				else {
					board.move(fromSquare, toSquare);
					board.move(fromSquare-4, toSquare+1);
					board.getPiece(toSquare).setHasMoved();
					board.getPiece(toSquare+1).setHasMoved();
				}
			}
			else {
				//kingside castling
				Board copy = Board.copy(board);
				copy.move(fromSquare, fromSquare+1);
				if(isInCheck(currentPlayer, copy))
					return INVALID;
				
				copy = Board.copy(board);
				copy.move(fromSquare, toSquare);
				copy.move(fromSquare+3, toSquare-1);
				if(isInCheck(currentPlayer, copy))
					return INVALID;
				else {
					board.move(fromSquare, toSquare);
					board.move(fromSquare+3, toSquare-1);
					board.getPiece(toSquare).setHasMoved();
					board.getPiece(toSquare-1).setHasMoved();
				}
			}
		} else if(moveType == ENPASSANT) {
			int lastTo = Game.translate(lastMove.substring(0,2));
			int lastFrom = Game.translate(lastMove.substring(3,5));
			if(currentPlayer.getColor().equals("White")) {
				if(lastTo == toSquare-10 && lastFrom == toSquare+10) {
					Board copy = Board.copy(board);
					copy.move(fromSquare, toSquare);
					copy.remove(toSquare-10);
					
					if(isInCheck(currentPlayer, copy))
						return INVALID;
					else {
						board.move(fromSquare, toSquare);
						board.remove(toSquare-10);
					}
				} else
					return INVALID;
			} else {
				if(lastTo == toSquare+10 && lastFrom == toSquare-10) {
					Board copy = Board.copy(board);
					copy.move(fromSquare, toSquare);
					copy.remove(toSquare+10);
					
					if(isInCheck(currentPlayer, copy))
						return INVALID;
					else {
						board.move(fromSquare, toSquare);
						board.remove(toSquare+10);
					}
				} else
					return INVALID;
			}
		}
				
		if(!hasMoves(opponent, board)) {
			if(!isInCheck(opponent, board))
				return STALEMATE;
			else
				return CHECKMATE;
		}
		
		return VALID;
	}
	
	/**
	 * Determines whether or not the given player is in check on the given board.
	 * @param player The player.
	 * @param chessboard The chessboard.
	 * @return true if the player is in check, false otherwise.
	 */
	public boolean isInCheck(Player player, Board chessboard) {
		int kingLocation = -1;
		
		for(int i = 0; i < 78; i++) {
			if(chessboard.getPiece(i) == null)
				continue;
			if(chessboard.getPiece(i) instanceof King && chessboard.getPiece(i).getColor().equals(player.getColor())) {
				kingLocation = i;
				break;
			}
		}
		
		Player opponent = (player == player1)? player2 : player1;
		for(int i = 0; i < 78; i++) {
			if(chessboard.getPiece(i) != null) {
				if(chessboard.getPiece(i).getColor().equals(opponent.getColor())) {
					List<Integer> attackableSpaces = chessboard.getPiece(i).getPossibleAttacks(chessboard, i);
					if(attackableSpaces.contains(kingLocation))
						return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Determines if the player has any moves possible that result in the player not being in check.
	 * @param player The player.
	 * @param chessboard The chessboard.
	 * @return true if the player has valid moves available, false otherwise.
	 */
	public boolean hasMoves(Player player, Board chessboard) {
		for(int i = 0; i < 78; i++) {
			if(chessboard.getPiece(i) != null) {
				if(chessboard.getPiece(i).getColor().equals(player.getColor())) {
					List<Integer> moveList = chessboard.getPiece(i).getPossibleMoves(chessboard, i);
					for(Integer toSpace : moveList) {
						Board copy = Board.copy(chessboard);
						copy.move(i, toSpace);
						if(!isInCheck(player, copy))
							return true;
					}
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Simple helper method to switch who is the current player.
	 */
	public void switchPlayers() {
		currentPlayer = (currentPlayer == player1)? player2 : player1;
	}
	
	/**
	 * Translates a string representing the file and rank of a tile on the board into an integer representation.
	 * @param coordinates The string form of the tile.
	 * @return The integer representing the tile on the board.
	 */
	public static int translate(String coordinates) {
		int rank = -1, file = -1;
		switch(coordinates.charAt(0))
		{
		case 'a': file = 0; break;
		case 'b': file = 1; break;
		case 'c': file = 2; break;
		case 'd': file = 3; break;
		case 'e': file = 4; break;
		case 'f': file = 5; break;
		case 'g': file = 6; break;
		case 'h': file = 7; break;
		}
		
		rank = Integer.parseInt(coordinates.substring(1));
		rank--;
		rank *= 10;
		
		return rank+file;
	}
}