package engine.pieces;

import engine.chessBoard.Board;
import engine.chessBoard.BoardUtils;
import engine.chessBoard.Move;
import engine.chessBoard.Move.*;
import com.google.common.collect.ImmutableList;
import engine.setting.Alliance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
public class Pawn extends Piece{
    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES= {8,16,7,9};
    public Pawn( final Alliance pieceAlliance,final int piecePotition) {
        super(PieceType.PAWN,piecePotition, pieceAlliance);
    }

    @Override
    public Pawn movedPiece(final Move move) {
        return new Pawn(move.getMovedPiece().getPieceAlliance(),move.getDestinationCoordinate());
    }

    @Override
    public String toString(){
        return PieceType.PAWN.toString();
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for(final int currentCandidateOffset:CANDIDATE_MOVE_VECTOR_COORDINATES){
            //폰은 아래에서 위로, 위에서 아래로만 움직이기 때문에 Alliance로 값을 지정하여 움직인다.
            final int candidateDestinationCoordinate=this.piecePotition+(this.pieceAlliance.getDirection()*currentCandidateOffset);
                if(!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                    continue;
                }
                if(currentCandidateOffset==8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                    legalMoves.add(new MajorMove(board,this,candidateDestinationCoordinate));

                }else if(currentCandidateOffset==16 &&this.isFirstMove()&&
                        (BoardUtils.SECOND_ROW[this.piecePotition])&&(this.getPieceAlliance().isBlack())
                                 ||(BoardUtils.SEVENTH_ROW[this.piecePotition]&&this.getPieceAlliance().isWhite())){
                                    final int behindCandidateDestinationCoordinate=this.piecePotition+(this.getPieceAlliance().getDirection()*8);
                                    //폰이 두칸 이동하려할 때 그 앞에는 어떤 장기말도 없어야함.
                                    if(!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() && !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                                        legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                                    }
                }else if(currentCandidateOffset==7&&
                        !((BoardUtils.EIGHT_COLUMN[this.piecePotition] && this.pieceAlliance.isWhite() ||
                                (BoardUtils.FIRST_COLUMN[this.piecePotition] && this.pieceAlliance.isBlack())))){
                    if(board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                        final Piece pieceCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                        if(this.pieceAlliance != pieceCandidate.getPieceAlliance()){
                            legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                        }
                    }

                }else if(currentCandidateOffset==9 &&
                        !((BoardUtils.FIRST_COLUMN[this.piecePotition] && this.pieceAlliance.isWhite() ||
                                (BoardUtils.EIGHT_COLUMN[this.piecePotition] && this.pieceAlliance.isBlack())))){ //예외 상황
                    if(board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                        final Piece pieceCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                        if(this.pieceAlliance != pieceCandidate.getPieceAlliance()){
                            legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                        }
                    }
                }
        }
        return ImmutableList.copyOf(legalMoves);
    }
}
