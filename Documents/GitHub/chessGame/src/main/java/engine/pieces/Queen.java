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

public class Queen extends Piece{
    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES= {-9,-8,-7,1,1,7,8,9};
    public Queen(final Alliance pieceAlliance,final int piecePotition) {
        super(PieceType.QUEEN,piecePotition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves=new ArrayList<>();
        for(final int candidateCoordinateOffset:CANDIDATE_MOVE_VECTOR_COORDINATES){
            int candidateDestinationCoordinate=this.piecePotition;
            while(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){

                if(isFirstColumnExclusion(candidateDestinationCoordinate,candidateCoordinateOffset)||
                        isEightColumnExclusion(candidateDestinationCoordinate,candidateCoordinateOffset)){
                    break;
                }

                candidateDestinationCoordinate+=candidateCoordinateOffset;

                if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                    final Tile candidateDestinationTile= board.getTile(candidateDestinationCoordinate);

                    if(!candidateDestinationTile.isTileOccupied()){//비어있는 타일이 아니라면
                        legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                    }else {
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Alliance pieceAliance = pieceAtDestination.getPieceAlliance();
                        if (this.pieceAlliance != pieceAliance) {//해당 위치의 말이 적군의 말이라면
                            legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public Queen movedPiece(final Move move) {
        return new Queen(move.getMovedPiece().getPieceAlliance(),move.getDestinationCoordinate());
    }
    @Override
    public String toString(){
        return PieceType.QUEEN.toString();
    }
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        return  BoardUtils.FIRST_COLUMN[currentPosition] &&( candidateOffset==-1||candidateOffset==-9|| candidateOffset==7);
    }
    private static boolean isEightColumnExclusion(final int currentPosition, final int candidateOffset){
        return  BoardUtils.EIGHT_COLUMN[currentPosition] &&(candidateOffset==1||candidateOffset==-7|| candidateOffset==9);
    }
}
