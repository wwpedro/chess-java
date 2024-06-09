package chess.pieces;

import boadrgame.Board;
import boadrgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{

	public King(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "K";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		//testando direções
		Position p = new Position(0, 0);
		
		//a cima
		p.setValues(position.getRow()-1, position.getcolumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getcolumn()]=true;
		}
		//baixo
		p.setValues(position.getRow()+1, position.getcolumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getcolumn()]=true;
		}
		//esquerda
		p.setValues(position.getRow(), position.getcolumn()-1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getcolumn()]=true;
		}
		//diretia
		p.setValues(position.getRow(), position.getcolumn()+1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getcolumn()]=true;
		}
		//diagonal(noroeste)
		p.setValues(position.getRow()-1, position.getcolumn()-1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getcolumn()]=true;
		}
		//diagonal(nordeste)
		p.setValues(position.getRow()-1, position.getcolumn()+1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getcolumn()]=true;
		}
		//diagonal(sudoeste)
		p.setValues(position.getRow()+1, position.getcolumn()-1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getcolumn()]=true;
		}
		//diagonal(suldeste)
		p.setValues(position.getRow()+1, position.getcolumn()+1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getcolumn()]=true;
		}
		
		
		return mat;
	}
	
	//regra para mover rei
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p == null || p.getColor() != getColor();	
	}
	
}
