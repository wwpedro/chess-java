package boadrgame;

public class Board {
	
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		if(rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: there must be at leat 1 row and 1 column");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}
	
	public int getRows() {
		return rows;
	}
	public int getColumns() {
		return columns;
	}
	
	public Piece piece(int row, int column) {
		if(!positionExists(row, column)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[row][column];
	}
	//sobrecarga
	public Piece piece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[position.getRow()][position.getcolumn()];
	} 
	
	public void placePiece(Piece piece , Position position) {
		if(thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position"+ position);
		}
		
		pieces[position.getRow()][position.getcolumn()] = piece;
		//consegue acessar livremente protect pq ta no memso pacote
		piece.position = position;
	}
	
	public boolean positionExists(int row, int column) {
		return row >= 0 && row< rows && column>=0 && column < columns;
	}
	
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getcolumn());
	}
	
	public boolean thereIsAPiece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return piece(position) != null;
	}
	
	
	public Piece revovePiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		
		if(piece(position) == null) {
			return null;
		}
		
		Piece aux = piece(position);
		aux.position = null;
		pieces[position.getRow()][position.getcolumn()] = null;
		return aux;
		
		//atribui peça a aux e atribui a nulo o anterior
	}
}
