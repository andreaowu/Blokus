package duo;

import java.util.Random;
import static duo.Color.*;

/** Supervisor for a game of Duo.
 *  @author Andrea Wu
 */
class Game {

    /** The players in this game: orange is _player[0] and violet
     *  is _player[1]. */
    private final Player[] _player = new Player[2];
    /** The current board. */
    private MutableBoard _board;
    /** The UI I and my players use for input and output. */
    private final TextUI _ui;
    /** A random number generator for use by my players. */
    private Random _rand;
    /** The number of moves made thus far. */
    private int _numMoves;

    /** A new Game representing one game between ORANGEPLAYER and
     *  VIOLETPLAYER.  Initially, the board is empty. Uses UI for input
     *  and output.  If SEED is non-zero, uses it to seed a random
     *  number generator. */
    Game(Player orangePlayer, Player violetPlayer, TextUI ui, long seed) {
        _player[0] = orangePlayer;
        _player[1] = violetPlayer;
        _ui = ui;
        if (seed == 0) {
            _rand = new Random();
        } else {
            _rand = new Random(seed);
        }
        _board = new MutableBoard();
    }

    /** Returns the current board. */
    MutableBoard getBoard() {
        return _board;
    }

    /** Returns the number of moves that have been made. */
    int getNumMoves() {
        return _numMoves;
    }

    /** Perform MOVE on the current board (the color of the piece
     *  placed depends on whose move it is). */
    void move(String move) {
        if (move.charAt(0) == 'q') {
            System.exit(0);
        } else if (move.charAt(0) == 'b') {
            _ui.reportBoardStandard(getBoard());
        } else if (_board.isLegal(move)) {
            _board.makeMove(move);
            _numMoves += 1;
        } else {
            _ui.reportError("Error: Illegal move " + move);
            if (this.getBoard().turn() == _player[0].getColor()) {
                _player[0].move();
            } else {
                _player[1].move();
            }
        }
    }

    /** Starting from the current board, complete a game between the
     *  two players, reporting all results on my Reporter. */
    void play() {
        Player p0 = _player[0];
        Player p1 = _player[1];
        p0.startGame(this, ORANGE);
        p1.startGame(this, VIOLET);
        while (_board.hasMove(getBoard().turn())) {
            if (this.getBoard().turn() == ORANGE) {
                p0.move();
            } else {
                p1.move();
            }
        }
        if (_board.getPiecesLeft(ORANGE).size() == 0
            && p0.getPrevMove(p0.getColor()).charAt(0) == '1') {
            _board.setPoints(ORANGE, 5);
        }
        if (_board.getPiecesLeft(VIOLET).size() == 0
            && p1.getPrevMove(p1.getColor()).charAt(0) == '1') {
            _board.setPoints(VIOLET, 5);
        }
        _ui.reportWinner(_board.getPoints(ORANGE), _board.getPoints(VIOLET));
    }

    /** Returns a uniformly distributed pseudo-random integer between
     *  0 and N-1 (inclusive).  Assumes N > 0. */
    int nextRand(int n) {
        return _rand.nextInt(n);
    }

    /** Returns a uniformly distributed pseudo-random int. */
    int nextRand() {
        return _rand.nextInt();
    }


}
