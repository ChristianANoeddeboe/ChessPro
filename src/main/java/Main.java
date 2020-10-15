import boards.frmChessBoard;
import com.github.bhlangonijr.chesslib.*;
import com.github.bhlangonijr.chesslib.move.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Creates a new chessboard in the standard initial position
        final frmChessBoard GUIboard = new frmChessBoard();
        Board board = new Board();

        //Make a move from E2 to E4 squares
        GUIboard.doMove("e7e2");
        GUIboard.doMove("e2e3");
        GUIboard.doMove("e8f4");
        board.doMove(new Move(Square.E2, Square.E4));

        //print the chessboard in a human-readable form
        //System.out.println(board.toString());
        System.out.println("colors(0)");
        Scanner scanner = new Scanner(System.in);
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            String command = null;
            try {
                command = input.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (command != null) System.out.println(command);;

        }
    }
}
