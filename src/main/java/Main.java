import com.github.bhlangonijr.chesslib.*;
import com.github.bhlangonijr.chesslib.move.*;

public class Main {

    public static void main(String[] args) {
        // Creates a new chessboard in the standard initial position
        Board board = new Board();

        //Make a move from E2 to E4 squares
        board.doMove(new Move(Square.E2, Square.E4));

        //print the chessboard in a human-readable form
        System.out.println(board.toString());
    }
}
