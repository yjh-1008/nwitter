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

public class Knight extends Piece{

    private final static int[] CANDIDATE_MOVE_COORDINATES={-17,-15,-10,-6,6,10,15,17};

    public Knight( final Alliance pieceAlliance,final int piecePotition) {
        super(PieceType.KNIGHT,piecePotition, pieceAlliance);
    }

    public Collection<Move> calculateLegalMoves(final Board board) {
        int candidateDestinationCoordinate;
        List<Move> legalMoves=new ArrayList<>();
        for(int currentCandidateOffset: CANDIDATE_MOVE_COORDINATES){
            candidateDestinationCoordinate=this.piecePotition+currentCandidateOffset;
            if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                if(isFirstColumnExclusion(this.piecePotition, currentCandidateOffset)||
                    isSecondColumnExclusion(this.piecePotition,currentCandidateOffset)||
                        isSeventhColumnExclusion(this.piecePotition,currentCandidateOffset)||
                        isEightColumnExclusion(this.piecePotition,currentCandidateOffset)){
                    continue;
                }
                 final Tile candidateDestinationTile= board.getTile(candidateDestinationCoordinate);

                 if(!candidateDestinationTile.isTileOccupied()){//비어있는 타일이 아니라면
                    legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                 }else{
                     final Piece pieceAtDestination=candidateDestinationTile.getPiece();
                     final Alliance pieceAliance= pieceAtDestination.getPieceAlliance();
                     if(this.pieceAlliance != pieceAliance){//해당 위치의 말이 적군의 말이라면
                         legalMoves.add(new Move.AttackMove(board,this, candidateDestinationCoordinate,pieceAtDestination));
                     }
                 }
            }

        }
        return ImmutableList.copyOf(legalMoves);
    }
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition]&&((candidateOffset ==-17) || (candidateOffset==-10) ||
                (candidateOffset==6) || (candidateOffset==15));
    }

    @Override
    public Knight movedPiece(final Move move) {
        return new Knight(move.getMovedPiece().getPieceAlliance(),move.getDestinationCoordinate());
    }

    @Override
    public String toString(){
        return PieceType.KNIGHT.toString();
    }
    private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.SECOND_COLUMN[currentPosition] && ((candidateOffset==-10)||(candidateOffset==6));
    }
    private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.SEVENTH_COLUMN[currentPosition] &&  ((candidateOffset==-6)||(candidateOffset==10));
    }
    private static boolean isEightColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.EIGHT_COLUMN[currentPosition] &&  ((candidateOffset==-15)||(candidateOffset==-6)||
                (candidateOffset==10)|| (candidateOffset==17));
    }
}
