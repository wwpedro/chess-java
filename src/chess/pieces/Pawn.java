package chess.pieces;

import boadrgame.Board;
import boadrgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	public Pawn(Board board, Color color) {
		super(board, color);
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		
		if(getColor()== Color.WHITE) {
			p.setValues(position.getRow()-1, position.getcolumn());
			if(getBoard().positionExists(p)&& !getBoard().thereIsAPiece(p)) {
				//se a linha de cima tiver vazia o pião pode andar
				mat[p.getRow()][p.getcolumn()] = true;
			}
			p.setValues(position.getRow()-2, position.getcolumn());
			//para testar a primeira casa , assim verifica se 2 casas pra frente está livre
			Position p2 = new Position(position.getRow()-1, position.getcolumn());
			if(getBoard().positionExists(p)&& !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2)&& !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				//primeira jogada move 2 casas
				mat[p.getRow()][p.getcolumn()] = true;
			}
			p.setValues(position.getRow()-1, position.getcolumn()-1);
			if(getBoard().positionExists(p)&& isThereOponetPiece(p)) {
				
				mat[p.getRow()][p.getcolumn()] = true;
			}
			p.setValues(position.getRow()-1, position.getcolumn()+1);
			if(getBoard().positionExists(p)&& isThereOponetPiece(p)) {
				mat[p.getRow()][p.getcolumn()] = true;
			}
		}//peças pretas
		else {
			p.setValues(position.getRow()+1, position.getcolumn());
			if(getBoard().positionExists(p)&& !getBoard().thereIsAPiece(p)) {
				//se a linha de cima tiver vazia o pião pode andar
				mat[p.getRow()][p.getcolumn()] = true;
			}
			p.setValues(position.getRow()+2, position.getcolumn());
			//para testar a primeira casa , assim verifica se 2 casas pra frente está livre
			Position p2 = new Position(position.getRow()+1, position.getcolumn());
			if(getBoard().positionExists(p)&& !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2)&& !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				//primeira jogada move 2 casas
				mat[p.getRow()][p.getcolumn()] = true;
			}
			p.setValues(position.getRow()+1, position.getcolumn()-1);
			if(getBoard().positionExists(p)&& isThereOponetPiece(p)) {
				
				mat[p.getRow()][p.getcolumn()] = true;
			}
			p.setValues(position.getRow()+1, position.getcolumn()+1);
			if(getBoard().positionExists(p)&& isThereOponetPiece(p)) {
				mat[p.getRow()][p.getcolumn()] = true;
			}
		}
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}
	
}
