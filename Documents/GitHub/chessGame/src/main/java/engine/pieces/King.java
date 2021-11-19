package engine.pieces;

import engine.chessBoard.Board;
import engine.chessBoard.BoardUtils;
import engine.chessBoard.Move;
import engine.chessBoard.Tile;
import com.google.common.collect.ImmutableList;
import engine.setting.Alliance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class King extends Piece{
    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES= {-9,-8,-7,1,1,7,8,9};
    public King(final Alliance pieceAlliance,final int piecePotition) {
        super(PieceType.KING,piecePotition, pieceAlliance);
    }
    //King move one block any where. But contain enemy attack root, King can't move there.
    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        for(final int currentCandidateOffset:CANDIDATE_MOVE_VECTOR_COORDINATES){

            final int candidateDestinationCoordinate=this.piecePotition+currentCandidateOffset;

            if(isFirstColumnExclusion(this.piecePotition,currentCandidateOffset)||
                    isEightColumnExclusion(this.piecePotition,currentCandidateOffset)) {
                continue;
            }

            if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                final Tile candidateDestinationTile= board.getTile(candidateDestinationCoordinate);

                if(!candidateDestinationTile.isTileOccupied()){//is not empty tile
                    legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                }else{
                    final Piece pieceAtDestination=candidateDestinationTile.getPiece();
                    final Alliance pieceAliance= pieceAtDestination.getPieceAlliance();
                    if(this.pieceAlliance != pieceAliance){//tile is not ememy piece
                        legalMoves.add(new Move.AttackMove(board,this, candidateDestinationCoordinate,pieceAtDestination));
                    }
                }
            }
            }
            return ImmutableList.copyOf(legalMoves);
    }
    @Override
    public King movedPiece(final Move move) {
        return new King(move.getMovedPiece().getPieceAlliance(),move.getDestinationCoordinate());
    }

    @Override
    public String toString(){
        return PieceType.KING.toString();
    }
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition]&&((candidateOffset ==-9) || (candidateOffset==-1) ||
                (candidateOffset==7));
    }

    private static boolean isEightColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.EIGHT_COLUMN[currentPosition] && ((candidateOffset==-7)||(candidateOffset==1)||
                candidateOffset==9);
    }
}
