package engine.chessBoard;

import engine.pieces.Pawn;
import engine.pieces.Piece;
import engine.pieces.Rook;

public abstract class Move {
    final Board board;
    final Piece movedPiece;
    final int destinationCoordinate;

    private Move(final Board board, final Piece movedPiece, final int destinationCoordinate){
        this.board=board;
        this.movedPiece=movedPiece;
        this.destinationCoordinate=destinationCoordinate;
    }

    @Override
    public int hashCode(){
        final int prime=31;
        int result=1;

        result= prime*result+this.destinationCoordinate;
        result=prime*result+this.movedPiece.hashCode();
        return result;
    }
    @Override
    public boolean equals(final Object other){
        if(this==other){
            return true;
        }

        if(!(other instanceof Move)){
            return false;
        }
        final Move otherMove = (Move)other;
        return getDestinationCoordinate()==otherMove.getDestinationCoordinate()&&
                getMovedPiece().equals(otherMove.getMovedPiece());
    }

    public int getCurrentCoordinate(){
        return this.getMovedPiece().getPiecePosition();
    }

    public static final Move NULL_MOVE = new NullMove();


    public Board execute() {
        final Board.Builder builder= new Board.Builder();
        for(final Piece piece: this.board.currentPlayer().getActivatePieces()){
            if(!this.movedPiece.equals(piece)){
                builder.setPiece(piece);

            }
        }
        for(final Piece piece:this.board.currentPlayer().getOpponent().getActivatePieces()){
            builder.setPiece(piece);
        }

        //move the moved piece!
        builder.setPiece(this.movedPiece.movedPiece(this));
        builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());

        return builder.build();
    }
    public int getDestinationCoordinate(){
        return this.destinationCoordinate;
    }

    public Piece getMovedPiece(){
        return this.movedPiece;
    }

    public boolean isAttack(){
        return false;
    }

    public boolean isCastlingMove(){
        return true;
    }

    public Piece getAttackedPiece(){
        return null;
    }
    public static final class  MajorMove extends Move{
        public MajorMove(final Board board, final Piece movePiece, final int destinationCoordinate){
            super(board, movePiece, destinationCoordinate);
        }

    }

    public static class AttackMove extends Move{
        final Piece attackedPiece;
        public AttackMove(final Board board, final Piece movePiece, final int destinationCoordinate, final Piece attackedPiece){
            super(board, movePiece, destinationCoordinate);
            this.attackedPiece=attackedPiece;
        }

        @Override
        public int hashCode(){
            return this.attackedPiece.hashCode()+super.hashCode();
        }

        @Override
        public boolean equals(final Object other){
            if(this==other){
                return true;
            }
            if(!(other instanceof AttackMove)){
                return false;
            }
            final AttackMove otherAttackMove = (AttackMove) other;
            return super.equals(otherAttackMove) && getAttackedPiece().equals(otherAttackMove.getAttackedPiece());
        }

        @Override
        public Board execute() {
            return null;
        }
        @Override
        public boolean isAttack(){
            return true;
        }

        @Override
        public Piece getAttackedPiece(){
            return this.attackedPiece;
        }
    }

    public static class  PawnAttackMove extends AttackMove{
        public PawnAttackMove(final Board board, final Piece movePiece, final int destinationCoordinate,final Piece attackedPiece){
            super(board, movePiece, destinationCoordinate,attackedPiece);
        }
    }

    public static final class  PawnEnPassantAttackMove extends PawnAttackMove{
        public PawnEnPassantAttackMove(final Board board, final Piece movePiece, final int destinationCoordinate,final Piece attackedPiece){
            super(board, movePiece, destinationCoordinate,attackedPiece);
        }
    }

    public static final class  PawnJump extends Move{
        public PawnJump(final Board board, final Piece movedPiece, final int destinationCoordinate){
            super(board, movedPiece, destinationCoordinate);
        }

        //error
        @Override
        public Board execute(){
            final Board.Builder builder= new Board.Builder();
            for(final Piece piece:this.board.currentPlayer().getActivatePieces()){
                if(!this.movedPiece.equals(piece)){
                    builder.setPiece(piece);
                }
            }
            for(final Piece piece: this.board.currentPlayer().getOpponent().getActivatePieces()){
                builder.setPiece(piece);
            }
            final Pawn movedPawn = (Pawn) this.movedPiece.movedPiece(this);
            builder.setPiece(movedPawn);
            builder.setEnPassantPawn(movedPawn);
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
            return  builder.build();
        }
    }

    static abstract class  CastleMove extends Move{
        protected final Rook castleRook;
        protected final int castleRookStart;
        protected final int castleRookDestination;
        public CastleMove(final Board board,
                          final Piece movePiece,
                          final int destinationCoordinate,
                          final Rook castleRook,
                          final int castleRookStart,
                          final int castleRookDestination){
            super(board, movePiece, destinationCoordinate);
            this.castleRook=castleRook;
            this.castleRookStart=castleRookStart;
            this.castleRookDestination=castleRookDestination;
        }
        public Rook getCastleRook(){
            return this.castleRook;
        }
        @Override
        public boolean isCastlingMove(){
            return true;
        }

        @Override
        public Board execute(){

            final Board.Builder builder = new Board.Builder();
            for(final Piece piece:this.board.currentPlayer().getOpponent().getActivatePieces()){
                builder.setPiece(piece);
            }
            builder.setPiece(this.movedPiece.movedPiece(this));
            builder.setPiece(new Rook(this.castleRook.getPieceAlliance(),this.castleRookDestination));
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
            return builder.build();
        }

    }

    public static final class  KingSideCastleMove extends CastleMove{
        public KingSideCastleMove(final Board board,
                                  final Piece movePiece,
                                  final int destinationCoordinate,
                                  final Rook castleRook,
                                  final int castleRookStart,
                                  final int castleRookDestination){
            super(board,
                    movePiece,
                    destinationCoordinate,castleRook,castleRookStart,castleRookDestination);
        }
        @Override
        public String toString(){
            return "0-0";
        }
    }
    public static final class  QueenSideCastleMove extends CastleMove{
        public QueenSideCastleMove(final Board board,
                                   final Piece movePiece,
                                   final int destinationCoordinate,
                                   final Rook castleRook,
                                   final int castleRookStart,
                                   final int castleRookDestination){
            super(board, movePiece, destinationCoordinate,castleRook,castleRookStart,castleRookDestination);
        }
        @Override
        public String toString(){
            return "0-0-0";
        }
    }

    public static final class  NullMove extends Move{
        public NullMove(){
            super(null, null, -1);
        }
        @Override
        public Board execute(){
            throw new RuntimeException("cannot execute the null move!!");
        }
    }

    public static class MoveFactory{
        private MoveFactory(){

            throw new RuntimeException("Not intantiable!!");
        }
        public static Move createMove(final Board board,
                                      final int currentCoordinate,
                                      final int destinationCoordinate){
            for(final Move move:board.getAllLegalMoves()){
                if(move.getCurrentCoordinate()== currentCoordinate &&
                        move.getDestinationCoordinate() == destinationCoordinate){
                    return move;
                }
            }
            return NULL_MOVE;
        }

    }
}
