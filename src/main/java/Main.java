import boards.frmChessBoard;
import com.github.bhlangonijr.chesslib.*;
import com.github.bhlangonijr.chesslib.move.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws MoveGeneratorException {
        // Creates a new chessboard in the standard initial position
        //frmChessBoard GUIboard = new frmChessBoard();
        Board board = new Board();

        board.doMove(new Move(Square.E2, Square.E4));
        MoveList moves = null;
        moves = MoveGenerator.generateLegalMoves(board);
        double startTime = 0, endTime = 0;
        int counter;
        long total=0;
        //Timing
        //for(int iter = 0; iter<10;iter++) {
            startTime = System.nanoTime();
            for (counter = 1; counter < 53; counter++) {
                board.doMove(moves.getFirst());
                moves = MoveGenerator.generateLegalMoves(board);
            }
            total=total+counter;
            endTime = endTime + System.nanoTime();
        //}
        double duration = (endTime - startTime);
        System.out.println("Nanoseconds: "+ duration);
        double ms = duration/1000000;
        System.out.println("Milliseconds: " + ms);
        double msForEachCall = ms/total;

        System.out.println("Time for " + total + " calls of generating a legalmove: " + ms + "ms");
        System.out.println("Thats " + msForEachCall + " ms for each call");
        System.out.println("so for 10 seconds that is " + 10000000/msForEachCall + " calls");
        //Timing end.
        System.out.println("number of iterations " + total);
        System.out.println("Legal moves: " + moves);

        //Make a move from E2 to E4 squares
        //GUIboard.doMove("e7e2");
        //GUIboard.doMove("e2e3");
        //GUIboard.doMove("e8f4");


        //print the chessboard in a human-readable form
        //System.out.println(board.toString());
        /*System.out.println("colors(0)");
        Scanner scanner = new Scanner(System.in);
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            String command = null;
            try {
                command = input.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (command != null) System.out.println(command);
        }*/
    }
}
