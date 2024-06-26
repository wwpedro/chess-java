package chess.pieces;

import boadrgame.Board;
import boadrgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knigth extends ChessPiece{

	public Knigth(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "N";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		//testando direções
		Position p = new Position(0, 0);
		
		
		p.setValues(position.getRow()-1, position.getcolumn()-2);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getcolumn()]=true;
		}
		
		p.setValues(position.getRow()-2, position.getcolumn()-1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getcolumn()]=true;
		}
		
		p.setValues(position.getRow()-2, position.getcolumn()+1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getcolumn()]=true;
		}
		
		p.setValues(position.getRow()-1, position.getcolumn()+2);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getcolumn()]=true;
		}
		
		p.setValues(position.getRow()+1, position.getcolumn()+2);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getcolumn()]=true;
		}
		
		p.setValues(position.getRow()+2, position.getcolumn()+1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getcolumn()]=true;
		}
		
		p.setValues(position.getRow()+2, position.getcolumn()-1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getcolumn()]=true;
		}
		
		p.setValues(position.getRow()+1, position.getcolumn()-2);
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
