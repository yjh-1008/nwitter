package engine.player;

import engine.chessBoard.Board;
import engine.chessBoard.Move;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import engine.pieces.King;
import engine.pieces.Piece;
import engine.setting.Alliance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Player {
    protected final Board board;
    protected final King playerKing;//Special Piece
    protected final Collection<Move> legalMoves;
    private final boolean isInCheck;

    public King getPlayerKing(){
        return this.playerKing;
    }
    public Collection<Move> getLegalMoves(){
        return this.legalMoves;
    }
    public Player(Board board, Collection<Move> legalMoves, Collection<Move> opponentMoves){
        this.board= board;
        this.playerKing= establishKing();
        this.legalMoves=ImmutableList.copyOf(Iterables.concat(legalMoves,calculateKingCastles(legalMoves,opponentMoves)));
        this.isInCheck= !Player.calculateAttackOnTile(this.playerKing.getPiecePosition(),opponentMoves).isEmpty();
    }

    protected static Collection<Move> calculateAttackOnTile(int piecePosition, Collection<Move> moves){
        final List<Move> attackMoves = new ArrayList<>();
        for(final Move move: moves){
            if(piecePosition==move.getDestinationCoordinate()){
                attackMoves.add(move);
            }
        }
        return ImmutableList.copyOf(attackMoves);
    }

    public boolean isMoveLegal(final Move move){
        return this.legalMoves.contains(move);
    }

    public boolean isInCheck(){
        return this.isInCheck;
    }
    //below Method

    protected boolean hasEscapeMoves() {
        for(final Move move: this.legalMoves){
            final MoveTransition transition= makeMove(move);
            if(transition.getMoveStatus().isDone()){
                return true;
            }
        }
        return false;
    }

    public boolean isInCheckMate(){
        return this.isInCheck && !hasEscapeMoves();
    }
    public boolean isInStaleMate(){
        return !this.isInCheck && !hasEscapeMoves();
    }
    public boolean isCastled(){
        return false;
    }

    public MoveTransition makeMove(final Move move){
        if(!isMoveLegal(move)){
            return new MoveTransition(this.board,move,  MoveStatus.ILLEGAL_MOVE);
        }

        final Board transitionBoard= move.execute();
        final Collection<Move> kingAttacks= Player.calculateAttackOnTile(transitionBoard.currentPlayer().getOpponent().getPlayerKing().getPiecePosition(),
        transitionBoard.currentPlayer().getLegalMoves());
        if(!kingAttacks.isEmpty()){
            return new MoveTransition(this.board,move,MoveStatus.LEAVES_PLAYER_IN_CHECK);
        }
        return new MoveTransition(transitionBoard,move, MoveStatus.DONE);
    }
    private King establishKing(){
        for(final Piece piece : getActivatePieces()){
            if(piece.getPieceType().isKing()){
                return (King) piece;
            }
        }
        throw new RuntimeException("Should not reach here! Not a valid board");
    }

    public abstract Collection<Piece> getActivatePieces();
    public abstract Alliance getAlliance();
    public abstract Player getOpponent();
    protected abstract Collection<Move> calculateKingCastles(Collection<Move> playerLegals, Collection<Move> opponentsLegals);
}
