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

public class WhitePlayer extends Player{
    public WhitePlayer(final Board board,final Collection<Move> whiteStandardLegalMoves,final Collection<Move> blackStandardLegalMoves) {
        super(board, whiteStandardLegalMoves, blackStandardLegalMoves);

    }

    @Override
    public Collection<Piece> getActivatePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.WHITE;
    }

    @Override
    public Player getOpponent() {
        return this.board.blackPlayer();
    }

    @Override
    protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegals,
                                                    final Collection<Move> opponentsLegals) {
        final List<Move> KingCastles = new ArrayList<>();
        if(this.playerKing.isFirstMove() && !this.isInCheck()){
            if(this.board.getTile(61).isTileOccupied() &&
            !this.board.getTile(62).isTileOccupied()){
                final Tile rookTile=this.board.getTile(63);
                if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()){
                    if(Player.calculateAttackOnTile(61,opponentsLegals).isEmpty()&&
                    Player.calculateAttackOnTile(62,opponentsLegals).isEmpty()&&
                    rookTile.getPiece().getPieceType().isRook()){
                        KingCastles.add(new Move.KingSideCastleMove(this.board,this.playerKing,62,
                                (Rook)rookTile.getPiece(),rookTile.getTileCoordinate(),59));
                    }


                }
            }
            if(this.board.getTile(59).isTileOccupied()&&
            !this.board.getTile(58).isTileOccupied()&&
                    !this.board.getTile(57).isTileOccupied()){
                final Tile rookTile=this.board.getTile(56);
                if(rookTile.isTileOccupied()&& rookTile.getPiece().isFirstMove()){
                    KingCastles.add(new Move.QueenSideCastleMove(this.board,this.playerKing,58,
                            (Rook)rookTile.getPiece(),rookTile.getTileCoordinate(),     59));
                }
            }
        }

        return ImmutableList.copyOf(KingCastles);
    }
}
