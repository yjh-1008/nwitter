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

public class Bishop extends Piece{
    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES= {-9 -7,7,9};


    public Bishop(final Alliance pieceAlliance,final int piecePotition) {
        super(PieceType.BISHOP,piecePotition, pieceAlliance);
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

                    if(!candidateDestinationTile.isTileOccupied()){//is not empty tile
                        legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                    }else {
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Alliance pieceAliance = pieceAtDestination.getPieceAlliance();
                        if (this.pieceAlliance != pieceAliance) {//tile piece is enemy
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
    public Bishop movedPiece(final Move move) {
        return new Bishop(move.getMovedPiece().getPieceAlliance(),move.getDestinationCoordinate());
    }

    @Override
    public String toString(){
        return PieceType.BISHOP.toString();
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
         return  BoardUtils.FIRST_COLUMN[currentPosition] &&(candidateOffset==-9|| candidateOffset==7);
    }
    private static boolean isEightColumnExclusion(final int currentPosition, final int candidateOffset){
        return  BoardUtils.EIGHT_COLUMN[currentPosition] &&(candidateOffset==-7|| candidateOffset==9);
    }
}
