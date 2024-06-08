package chess;

import boadrgame.Board;
import boadrgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	
	private Board board;
	
	public ChessMatch() {
		board = new Board(8,8);
		initialSetup();
	}
	
	public ChessPiece[][] getPieces(){
		/*
		 * chess em outra camda retorna Piece mas aqui o tipo é ChessPiece
		 * assim ele nao enxerga uma camada mais interna de tabuleiro da peça matriz
		 * e sim as peças de xadres que vão ficra nessa camada
		 */
		
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for(int i=0; i<board.getRows(); i++) {
			for(int j=0; j<board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}
	
	private void initialSetup() {
		board.placePiece(new Rook(board,Color.WHITE), new Position(2, 1));
		board.placePiece(new King(board,Color.BLACK), new Position(0, 4));
		board.placePiece(new King(board,Color.WHITE), new Position(7, 4));
	}
}
