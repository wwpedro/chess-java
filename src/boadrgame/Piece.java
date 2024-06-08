package boadrgame;

public abstract class Piece {
	
	/* 1	ela nao é da camada do xadrez 
	 * 	por isso não queremos que seja visivel 
	 * 	em outro pacote
	*/
	protected Position position;
	private Board board;
	
	// 2 inicialmente "position" é nulo por isso só o tabuleiro é argumento
	public Piece(Board board) {
		this.board = board;
		// 3position = null;(por padrão)
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	//5 tabuleiro é de uso da camada de tabuleiro, vai ser só exposto pra algumas classes
	protected Board getBoard() {
		return board;
	}
	// 4 board n precisa de set pq não pode ser alterado
	
	
	public abstract boolean[][] possibleMoves();
	
	public  boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getcolumn()];
	}
	
	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves();
		for (int i=0; i<mat.length; i++) {
			for (int j=0; j<mat.length; j++) {
				if (mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
}
