package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boadrgame.Board;
import boadrgame.Piece;
import boadrgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	
	private int turn;
	private Color currentPlayer;
	private Board board;
	
	private boolean check; //ja inicia com false
	private boolean checkMate;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	
	public ChessMatch() {
		board = new Board(8,8);
		turn =1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}
	
	public int getTurn() {
		return turn;
	}
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	public boolean getCheck() {
		return check;
	}
	public boolean getCheckMate() {
		return checkMate;
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
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
	}
	
	private void initialSetup() {
		placeNewPiece('h', 7, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));

        placeNewPiece('b', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 8, new King(board, Color.BLACK));
	}
	
	public ChessPiece performCHessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		
		
		if(testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("You can't put yourself in Check");
		}
		//chesk do oponente 
		check = (testCheck(opponent(currentPlayer))) ? true : false;
		
		//verificaçã checkmate
		if(testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
		}else {
			nextTurn();
		}
		
		nextTurn();
		
		return (ChessPiece)capturedPiece;
	}
	
	//verifica se posição é valida
	private void validateSourcePosition(Position position) {
		if(!board.thereIsAPiece(position)) {
			throw new ChessException("There is no pice on source position");
		}
		if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
			throw new ChessException("the chose piece is not yours");
		}
		//existe movinetos possivei?
		if(!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("Theres is not possible moves for the chosen piece");
		}
	}
	//verifica se destino é valido
	private void validateTargetPosition(Position source, Position target) {
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can't move to target position");
		}
	}
	
	//realizando movidamneto
	private Piece makeMove(Position source, Position target) {
		Piece p = board.revovePiece(source);
		Piece capturedPiece = board.revovePiece(target);
		board.placePiece(p, target);
		
		
		if(capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		return capturedPiece;
	}
	
	
	//colorir posições possiveis
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
		
	}
	
	//ver turno
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer==Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	
	//check se a pessoa tenta se mover e entra em check tem que desfazer movimento
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		Piece p = board.revovePiece(target);
		board.placePiece(p, source);
		
		
		if(capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
	}
	
	//devolve o ponente d uma cor 
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	//metodo importante para loc o rei de uma determinada cor
	private ChessPiece king(Color color) {
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor()==color).collect(Collectors.toList());
		
		for(Piece p : list) {
			if(p instanceof King) {
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("There is no "+ color+ " king on the board");
	}
	
	//check: vai percorrer todas as casas possivel para o oponenete e verificar se algum opennete tem acesso true a vc então o metodo  ativa
	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor()==opponent(color)).collect(Collectors.toList());
		
		for(Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();
			if(mat[kingPosition.getRow()][kingPosition.getcolumn()]) {
				return true;
			}
		}
		return false;
	}
	
	//ckeck mat
	private boolean testCheckMate(Color color) {
		if(!testCheck(color)) {
			return false;
		}
		
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor()==color).collect(Collectors.toList());
		
		for(Piece p: list) {
			boolean[][] mat = p.possibleMoves();
			for(int i = 0 ; i< board.getRows(); i++) {
				for(int j = 0 ; j< board.getRows(); j++) {
					//verifica se é um movimento possivel
					if(mat[i][j]) {
						Position source = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position(i,j);
						
						Piece capturedPiece = makeMove(source, target);
						//testa se o rei da cor ainda está em check
						boolean testCheck = testCheck(color);
						undoMove(source, target, capturedPiece);
						if(!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
}
