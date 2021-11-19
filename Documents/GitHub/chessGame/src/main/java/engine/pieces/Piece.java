package engine.pieces;

import engine.chessBoard.Board;
import engine.chessBoard.Move;
import engine.setting.Alliance;

import java.util.Collection;

public abstract class Piece {

    protected final PieceType pieceType;
    protected final int piecePotition;
    protected final Alliance pieceAlliance;//동맹
    protected final boolean isFirstMove;
    private final int cachedHashCode;

     Piece(
             final PieceType pieceType,
             final int piecePotition,
             Alliance pieceAlliance){
         this.pieceType= pieceType;
        this.piecePotition=piecePotition;
        this.pieceAlliance=pieceAlliance;
        this.isFirstMove=false;
        this.cachedHashCode= computeHashCode();
    }

    private int computeHashCode(){
        int result = pieceType.hashCode();
        result = 31 * result+pieceAlliance.hashCode();
        result = 31 * result+piecePotition;
        result =  31 *  result+(isFirstMove ?1:0);
        return result;
    }

    @Override
    public boolean equals(final Object other){
         if(this==other){
             return true;
         }
         if(!(other instanceof Piece)){
             return false;
         }
         final Piece otherPiece =(Piece) other;
         return piecePotition==otherPiece.getPiecePosition() && pieceType==otherPiece.getPieceType()&&
                 pieceAlliance==otherPiece.getPieceAlliance() && isFirstMove==otherPiece.isFirstMove();
    }
    @Override
    public int hashCode(){
        return this.cachedHashCode;
    }

    public int getPiecePosition(){
         return this.piecePotition;
    }


    public PieceType getPieceType(){
         return this.pieceType;
    }

    public Alliance getPieceAlliance(){
         return this.pieceAlliance;
    }

    public boolean isFirstMove(){
         return this.isFirstMove;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board); //각 말의 움직임을 정의하는 메소드

    public abstract Piece movedPiece(Move move);

    public enum PieceType{

        PAWN("P"){
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        KNIGHT("K"){
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        BISHOP("B"){
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        ROOK("R"){
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return true;
            }
        },
        QUEEN("Q"){
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        KING("K"){
            @Override
            public boolean isKing() {
                return true;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        };

        private  String pieceName;
        PieceType(final String pieceName){
            this.pieceName=pieceName;
        }
        @Override
        public String toString(){
            return this.pieceName;
        }
        public abstract boolean isKing();
        public abstract boolean isRook();
    }
}
