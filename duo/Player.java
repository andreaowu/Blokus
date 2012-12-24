package duo;

import static duo.Color.*;

/** Abstract class of all players.
 * @author Andrea Wu
 */
abstract class Player {
    /** Make the next move in the game I am currently playing. */
    abstract void move();

    /** My name. */
    private final String _name;
    /** The piece color I am playing. */
    private Color _color;
    /** My current game. */
    protected Game _game;
    /** The UI I use for input and messages. */
    protected TextUI _ui;
    /** The previous move of Orange. */
    private String prevMoveOrange;
    /** The previous move of Violet. */
    private String prevMoveViolet;

    /** A Player named NAME who uses UI (if needed) for input and messages. */
    Player(String name, TextUI ui) {
        _name = name;
        _ui = ui;
        prevMoveOrange = "";
        prevMoveViolet = "";
    }

    /** Returns my name. */
    String getName() {
        return _name;
    }

    /** Returns the color I am playing. */
    Color getColor() {
        return _color;
    }

    /** Join GAME as the COLOR player. */
    void startGame(Game game, Color color) {
        _game = game;
        _color = color;
    }

    /** Returns opposite color of C. */
    static Color opponent(Color c) {
        if (c == ORANGE) {
            return VIOLET;
        } else {
            return ORANGE;
        }
    }

    /** Returns prevMove of C. */
    String getPrevMove(Color c) {
        if (c == ORANGE) {
            return prevMoveOrange;
        } else {
            return prevMoveViolet;
        }
    }

    /** Sets the prev of C to be S. */
    void setPrevMove(Color c, String s) {
        if (c == ORANGE) {
            prevMoveOrange = s;
        } else {
            prevMoveViolet = s;
        }
    }
}
