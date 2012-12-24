package duo;

import static duo.Color.*;

/** Abstract class of all players.
 * @author Andrea Wu
 */
class Human extends Player {

    /** A Human player named NAME, playing the COLOR pieces, and using UI
     *  for input and messages.  This kind of player prompts for moves
     *  from the user. */
    Human(String name, TextUI ui) {
        super(name, ui);
    }

    @Override
    void move() {
        String moveThis = _ui.getMove();
        if (moveThis.charAt(0) == 'b') {
            _ui.reportBoardStandard(_game.getBoard());
            this.move();
        } else if (moveThis.charAt(0) == 'q') {
            System.exit(0);
        } else {
            if (_game.getBoard().turn() == ORANGE) {
                this.setPrevMove(ORANGE, moveThis);
            } else {
                this.setPrevMove(VIOLET, moveThis);
            }
            _game.move(moveThis);
        }
    }

}
