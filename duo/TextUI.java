package duo;

import static duo.Color.*;
import java.util.Scanner;

/** A simple textual implementation of the user interface.
 *  @author Andrea Wu
 */
class TextUI implements UI {
    /** A scanner for the TextUI class. */
    private Scanner sc;

    /** Constructor for TextUI. */
    TextUI() {
        sc = new Scanner(System.in);
    }

    @Override
    public String getMove() {
        System.out.print("Type in move: ");
        return sc.next();
    }

    @Override
    public void reportWinner(int orangeScore, int violetScore) {
        if (orangeScore > violetScore) {
            System.out.println("Orange wins (" + orangeScore + "-"
                    + violetScore + ")");
        } else if (orangeScore < violetScore) {
            System.out.println("Violet wins (" + violetScore + "-"
                    + orangeScore + ")");
        } else {
            System.out.println("Tie game (" + violetScore + "-"
                    + orangeScore + ")");
        }
    }

    @Override
    public void reportBoard(Board board) {
        this.reportBoardStandard(board);
    }

    @Override
    public void reportBoardStandard(Board board) {
        System.out.println("===");
        for (int j = board.BOUNDS2; j >= 0; j -= 1) {
            System.out.print("  ");
            for (int i = 0; i < board.SIZE; i += 1) {
                if (board.get(i, j) == EMPTY) {
                    System.out.print("-");
                } else if (board.get(i, j) == ORANGE) {
                    System.out.print("O");
                } else {
                    System.out.print("V");
                }
            }
            System.out.println();
        }
        System.out.println("===");
    }

    @Override
    public void reportMove(Color color, int numMoves, String move) {
        if (numMoves == 1) {
            System.out.println(color + " just did " + move
                    + " and a total number"
                    + " of 1 move has been made");
        } else {
            System.out.println(color + " just did " + move
                    + " and a total number"
                    + " of " + numMoves + " moves have been made");
        }
    }

    @Override
    public void reportError(String message) {
        System.err.println(message);
    }

    @Override
    public void report(String message) {
        System.out.println(message);
    }

}
