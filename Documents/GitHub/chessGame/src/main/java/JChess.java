import GUI.Table;
import engine.chessBoard.Board;

public class JChess {
    public static void main(String[] args) {
        Board board= Board.createStandardBoard();
        System.out.println(board);
        Table table= new Table();
    }
}
