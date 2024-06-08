package chess;

import boadrgame.Position;

public class ChessPosition {
	// criada para atender o sistema de coordenadas impresso a-g e 1-8
	private char column;
	private int row;
	
	public ChessPosition(char column, int row) {
		if (column < 'a' || column > 'h' || row < 1 || row > 8 ) {
			throw new ChessException("Error in Chess Position. Valid values are from a1 to h8");
		}
		this.column = column;
		this.row = row;
	}

	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	/* matrix_row = 8 - chess_row (8-8= 0 , linha 0)
	 * 
	 * matrix column = matrix_column - 'a' (a-a=0, a-b=1 ... coluna 0, 1 ..)
	 * 
	 */
	
	protected Position toPosition() {
		return new Position(8-row, column-'a');
	}
	
	protected static ChessPosition fromPosition(Position position) {
		return new ChessPosition((char)('a' - position.getcolumn()), 8- position.getRow());
	}
	
	@Override
	public String toString() {
		// "" + (força a concatenar string)
		return "" + column + row;
	}
}
