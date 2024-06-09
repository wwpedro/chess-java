package chess;

import boadrgame.Board;
import boadrgame.Piece;
import boadrgame.Position;

public abstract class ChessPiece extends Piece {
	
	private Color color;
	private int moveCount;
	

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	public int getMoveCount() {
		return moveCount;
	}
	
	public void increaseMoveCount() {
		moveCount++;
	}
	public void decreaseMoveCount() {
		moveCount--;
	}
	
	protected boolean isThereOponetPiece(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p.getColor() != color;
		
	}
	
	//log check
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
}
