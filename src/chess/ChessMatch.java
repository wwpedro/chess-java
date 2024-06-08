package chess;

import boadrgame.Board;

public class ChessMatch {
	
	private Board board;
	
	public ChessMatch() {
		board = new Board(8,8);
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
}
