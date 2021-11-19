package engine.player;

import engine.chessBoard.Board;
import engine.chessBoard.Move;
import engine.chessBoard.Tile;
import com.google.common.collect.ImmutableList;
import engine.pieces.Piece;
import engine.pieces.Rook;
import engine.setting.Alliance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BlackPlayer extends Player{
    public BlackPlayer(final Board board, final Collection<Move> blackStandardLegalMoves,final Collection<Move> whiteStandardLegalMoves) {
        super(board, blackStandardLegalMoves, whiteStandardLegalMoves);
    }

    @Override
    public Collection<Piece> getActivatePieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.BLACK;
    }

    @Override
    public Player getOpponent() {
        return this.board.whitePlayer();
    }

    @Override
    protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegals,
                                                    final Collection<Move> opponentsLegals) {
        final List<Move> KingCastles= new ArrayList();
        if(this.playerKing.isFirstMove() && !this.isInCheck()){
            if(this.board.getTile(5).isTileOccupied() &&
                    !this.board.getTile(6).isTileOccupied()){
                final Tile rookTile=this.board.getTile(7);
                if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()){
                    if(Player.calculateAttackOnTile(5,opponentsLegals).isEmpty()&&
                            Player.calculateAttackOnTile(6,opponentsLegals).isEmpty()&&
                            rookTile.getPiece().getPieceType().isRook()){
                        KingCastles.add(new Move.KingSideCastleMove(this.board,this.playerKing,6,
                                (Rook)rookTile.getPiece(),rookTile.getTileCoordinate(),5));
                    }


                }
            }
            if(this.board.getTile(1).isTileOccupied()&&
                    !this.board.getTile(2).isTileOccupied()&&
                    !this.board.getTile(3).isTileOccupied()){
                final Tile rookTile=this.board.getTile(0);
                if(rookTile.isTileOccupied()&& rookTile.getPiece().isFirstMove()){
                    KingCastles.add(new Move.QueenSideCastleMove(this.board,this.playerKing,2,
                            (Rook)rookTile.getPiece(),rookTile.getTileCoordinate(),    3 ));
                }
            }
        }

        return ImmutableList.copyOf(KingCastles);
    }
}
