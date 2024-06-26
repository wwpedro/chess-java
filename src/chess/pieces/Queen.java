package chess.pieces;

import boadrgame.Board;
import boadrgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece {

	public Queen(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// rook=torre
		return "Q";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// noroeste
		p.setValues(position.getRow() - 1, position.getcolumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			// repete enquanto existe casa vazias a cima
			mat[p.getRow()][p.getcolumn()] = true;
			p.setValues(p.getRow() - 1, p.getcolumn() - 1);
		}
		// ver se existe peça adversaria
		if (getBoard().positionExists(p) && isThereOponetPiece(p)) {
			mat[p.getRow()][p.getcolumn()] = true;
		}

		// nordeste
		p.setValues(position.getRow() - 1, position.getcolumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			// repete enquanto existe casa vazias a cima
			mat[p.getRow()][p.getcolumn()] = true;
			p.setValues(p.getRow() - 1, p.getcolumn() + 1);
		}
		// ver se existe peça adversaria
		if (getBoard().positionExists(p) && isThereOponetPiece(p)) {
			mat[p.getRow()][p.getcolumn()] = true;
		}

		// sudeste
		p.setValues(position.getRow() + 1, position.getcolumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			// repete enquanto existe casa vazias a cima
			mat[p.getRow()][p.getcolumn()] = true;
			p.setValues(p.getRow() + 1, p.getcolumn() + 1);
		}
		// ver se existe peça adversaria
		if (getBoard().positionExists(p) && isThereOponetPiece(p)) {
			mat[p.getRow()][p.getcolumn()] = true;
		}

		// sudoeste
		p.setValues(position.getRow() + 1, position.getcolumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			// repete enquanto existe casa vazias a cima
			mat[p.getRow()][p.getcolumn()] = true;
			p.setValues(p.getRow() + 1, p.getcolumn() - 1);
		}
		// ver se existe peça adversaria
		if (getBoard().positionExists(p) && isThereOponetPiece(p)) {
			mat[p.getRow()][p.getcolumn()] = true;
		}

		// a cima
		p.setValues(position.getRow() - 1, position.getcolumn());
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			// repete enquanto existe casa vazias a cima
			mat[p.getRow()][p.getcolumn()] = true;
			p.setRow(p.getRow() - 1);
		}
		// ver se existe peça adversaria
		if (getBoard().positionExists(p) && isThereOponetPiece(p)) {
			mat[p.getRow()][p.getcolumn()] = true;
		}

		// esquerda
		p.setValues(position.getRow(), position.getcolumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			// repete enquanto existe casa vazias a cima
			mat[p.getRow()][p.getcolumn()] = true;
			p.setcolumn(p.getcolumn() - 1);
			;
		}
		// ver se existe peça adversaria
		if (getBoard().positionExists(p) && isThereOponetPiece(p)) {
			mat[p.getRow()][p.getcolumn()] = true;
		}

		// direita
		p.setValues(position.getRow(), position.getcolumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			// repete enquanto existe casa vazias a cima
			mat[p.getRow()][p.getcolumn()] = true;
			p.setcolumn(p.getcolumn() + 1);
			;
		}
		// ver se existe peça adversaria
		if (getBoard().positionExists(p) && isThereOponetPiece(p)) {
			mat[p.getRow()][p.getcolumn()] = true;
		}

		// baixo
		p.setValues(position.getRow() + 1, position.getcolumn());
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			// repete enquanto existe casa vazias a cima
			mat[p.getRow()][p.getcolumn()] = true;
			p.setRow(p.getRow() + 1);
		}
		// ver se existe peça adversaria
		if (getBoard().positionExists(p) && isThereOponetPiece(p)) {
			mat[p.getRow()][p.getcolumn()] = true;
		}

		return mat;
	}

}
