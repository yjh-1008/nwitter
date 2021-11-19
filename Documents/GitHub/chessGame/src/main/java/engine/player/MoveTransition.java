package engine.player;

import engine.chessBoard.Board;
import engine.chessBoard.Move;

public class MoveTransition {

    private final Board transitinoBoard;
    private final Move move;
    private final MoveStatus moveStatus;

    public MoveTransition(Board transitinoBoard, Move move, MoveStatus moveStatus) {
        this.transitinoBoard = transitinoBoard;
        this.move = move;
        this.moveStatus = moveStatus;
    }

    public MoveStatus getMoveStatus(){
        return this.moveStatus;
    }
}
