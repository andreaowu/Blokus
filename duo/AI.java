package duo;

import static duo.Color.*;
import java.util.ArrayList;
import java.awt.Point;

/** if pieces can't play...*/


/** Abstract class of all players.
 * @author Andrea Wu
 */
class AI extends Player {

    /** Number associated with winning. */
    public static final int WIN = 100;
    /** Number associated with losing. */
    public static final int LOSE = -100;
    /** Number for not returning entire list of legal moves. */
    public static final int FOUR = 4;

    /** An AI named NAME, playing the COLOR pieces, and using UI
     *  for messages.  This kind of player computes its own moves. */
    AI(String name, TextUI ui) {
        super(name, ui);
    }

    @Override
    void move() {
        String moveThis = " ";
        if (_game.getNumMoves() == 0 && _game.getBoard().isLegal("F005")) {
            moveThis = "F005";
        } else if (_game.getNumMoves() == 1) {
            if (_game.getBoard().isLegal("W000")) {
                moveThis = "W000";
            } else if (_game.getBoard().isLegal("Wb04")) {
                moveThis = "Wb04";
            }
        } else if (_game.getNumMoves() == 2
            && _game.getBoard().isLegal("X220")) {
            moveThis = "X220";
        } else if (_game.getNumMoves() == 3) {
            if (_game.getBoard().isLegal("F335")) {
                moveThis = "F335";
            } else if (_game.getBoard().isLegal("F831")) {
                moveThis = "F831";
            }
        } else if (_game.getNumMoves() == 4
            && _game.getBoard().isLegal("W444")) {
            moveThis = "W444";
        }
        if (moveThis.equals(" ")) {
            moveThis = this.findBestMove(this.getColor(), _game.getBoard(), 1,
                5);
        }
        _game.move(moveThis);
        _ui.reportMove(this.getColor(), _game.getNumMoves(), moveThis);
        if (_game.getBoard().turn() == ORANGE) {
            this.setPrevMove(ORANGE, moveThis);
        } else {
            this.setPrevMove(VIOLET, moveThis);
        }
    }

    /** A legal move for WHO that either has an estimated value >= CUTOFF
     * or that has the best estimated value for player WHO, starting from
     * position B, and looking up to DEPTH moves ahead. Returns best move. */
    String findBestMove(Color who, MutableBoard b, int depth, int cutoff) {
        if (!b.hasMove(ORANGE) && !b.hasMove(VIOLET)) {
            if (b.getPoints(who) > b.getPoints(opponent(who))) {
                return "won game";
            } else if (b.getPoints(who) < b.getPoints(opponent(who))) {
                return "lost game";
            }
        }
        if (depth == 0) {
            return this.guessBestMove(who, b);
        }
        String bestSoFar = "";
        ArrayList<String> legalMoves = this.legalMovesOnePiece(b, who,
            opponent(who), 0);
        for (int i = 0; i < legalMoves.size(); i += 1) {
            MutableBoard newB = new MutableBoard(b);
            if (bestSoFar.equals("")) {
                bestSoFar = legalMoves.get(i);
            }
            Point[] p = b.getPointForPiece("" + legalMoves.get(i).charAt(0),
                who);
            newB.makeMove(legalMoves.get(i));
            newB.putPiece("" + legalMoves.get(i).charAt(0), p, who);
            String response = this.findBestMove(opponent(who), newB, depth - 1,
                cutoff);
            if (this.value(response, opponent(who), who, newB)
                > this.value(bestSoFar, who, opponent(who), newB)) {
                bestSoFar = legalMoves.get(i);
                if (this.value(bestSoFar, who, opponent(who), newB) >= cutoff) {
                    return bestSoFar;
                }
            }
            newB.removePointsForPiece("" + legalMoves.get(i).charAt(0), who);
        }
        return bestSoFar;
    }

    /** Takes WHO and B to return the string of the best move. */
    String guessBestMove(Color who, MutableBoard b) {
        ArrayList<String> legalMoves = this.listOfLegalMoves(b, who);
        ArrayList<String> bestPieceMoves = new ArrayList<String>();
        ArrayList<String> testingNowMoves = new ArrayList<String>();
        String bPSF = "";
        String now = "placeholder";
        int strC = 0;
        int temp = 0;
        for (int j = 0; j < legalMoves.size(); j += 1) {
            String s = legalMoves.get(j);
            String piece = "" + s.charAt(0);
            if (bPSF.equals("")) {
                bPSF = "" + s.charAt(0);
                bestPieceMoves.add(s);
                strC += 1;
            } else if (piece.equals(bPSF)) {
                bestPieceMoves.add(s);
                strC += 1;
            } else if (now.equals("placeholder")) {
                testingNowMoves.add(s);
                now = "" + s.charAt(0);
                temp += 1;
            } else if (piece.equals(now)) {
                testingNowMoves.add(s);
                temp += 1;
            } else {
                if (temp < strC && b.getPointForPiece(now, who)
                        != null && b.getPointForPiece(bPSF, who) != null
                        && b.getPointForPiece(now, who).length
                        > b.getPointForPiece(bPSF, who).length) {
                    bPSF = now;
                    bestPieceMoves = testingNowMoves;
                }
                testingNowMoves.clear();
                now = "placeholder";
            }
        }
        temp = 0;
        for (int i = 0; i < bestPieceMoves.size(); i += 1) {
            if (this.value(bestPieceMoves.get(i), who, opponent(who), b)
                > temp) {
                bPSF = bestPieceMoves.get(i);
            } else if (bestPieceMoves.get(i).charAt(0) == '1') {
                return bestPieceMoves.get(i);
            }
        }
        return bPSF;
    }

    /** Returns the number of corners of S plus number of
     * corners the piece blocks; takes ME, OPP, and B also. */
    int value(String s, Color me, Color opp, MutableBoard b) {
        if (s.equals("won game")) {
            return WIN;
        } else if (s.equals("lost game")) {
            return LOSE;
        }
        int result = 0;
        Color[][] bo = b.getBoard();
        Point[] p = b.getPointForPiece("" + s.charAt(0), me);
        ArrayList<Point> pA = new ArrayList<Point>();
        for (int m = 0; m < p.length; m += 1) {
            pA.add(p[m]);
        }
        ArrayList<Point> corners = b.theseAreCorners(p, p.length, pA);
        result += this.cornersFree(b, corners, bo);
        result += p.length;
        return result;
    }

    /** Using B, CORNERS, and BO, returns the number of free corners. */
    int cornersFree(Board b, ArrayList<Point> corners, Color[][] bo) {
        int result = 0;
        for (int i = 0; i < corners.size(); i += 1) {
            int x = (int) corners.get(i).getX();
            int y = (int) corners.get(i).getY();
            if (x >= 0 && y >= 0 && x <= b.BOUNDS && y <= b.BOUNDS
                    && bo[x + 1][y + 1] == null) {
                result += 1;
            }
            if (x >= 0 && y > 0 && x <= b.BOUNDS && y <= b.SIZE
                    && bo[x + 1][y - 1] == null) {
                result += 1;
            }
            if (x > 0 && y > 0 && x <= b.SIZE && y <= b.SIZE
                    && bo[x - 1][y - 1] == null) {
                result += 1;
            }
            if (x > 0 && y >= 0 && x <= b.SIZE && y <= b.BOUNDS
                    && bo[x - 1][y + 1] == null) {
                result += 1;
            }
        }
        return result;
    }

    /** Returns a list of legal moves on B for C.
     * Takes V to see how much of the list to return. */
    static ArrayList<String> listOfLegalMoves(MutableBoard b, Color c) {
        ArrayList<String> piecesLeft = b.getPiecesLeft(c);
        ArrayList<String> legalMoves = new ArrayList<String>();
        for (int i = 0; i < piecesLeft.size(); i += 1) {
            for (int l = 0; l < b.SIZE; l += 1) {
                for (int k = 0; k < b.SIZE; k += 1) {
                    String q = "" + l;
                    String w = "" + k;
                    if (l == b.HEXA) {
                        q = "a";
                    } else if (l == b.HEXB) {
                        q = "b";
                    } else if (l == b.BOUNDS) {
                        q = "c";
                    } else if (l == b.BOUNDS2) {
                        q = "d";
                    }
                    if (k == b.HEXA) {
                        w = "a";
                    } else if (k == b.HEXB) {
                        w = "b";
                    } else if (k == b.BOUNDS) {
                        w = "c";
                    } else if (k == b.BOUNDS2) {
                        w = "d";
                    }
                    for (int o = 0; o < b.NUMBEROFO; o += 1) {
                        if (b.isLegal("" + piecesLeft.get(i)
                            + q + w + o)) {
                            legalMoves.add("" + piecesLeft.get(i) + q + w + o);
                        }
                    }
                }
            }
        }
        return legalMoves;
    }

    /** Returns a list of legal best moves for each piece left for ME in B.
     * Also takes OPP and R. */
    ArrayList<String> legalMovesOnePiece(MutableBoard b, Color me, Color opp,
        int r) {
        ArrayList<String> piecesLeft = b.getPiecesLeft(me);
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < piecesLeft.size(); i += 1) {
            if (piecesLeft.get(i).equals("1") && result.size() != 0) {
                break;
            }
            int temp = 0;
            String bestMoveForP = "";
            for (int l = 0; l < b.SIZE; l += 1) {
                for (int k = 0; k < b.SIZE; k += 1) {
                    String q = "" + l;
                    String w = "" + k;
                    if (l == b.HEXA) {
                        q = "a";
                    } else if (l == b.HEXB) {
                        q = "b";
                    } else if (l == b.BOUNDS) {
                        q = "c";
                    } else if (l == b.BOUNDS2) {
                        q = "d";
                    }
                    if (k == b.HEXA) {
                        w = "a";
                    } else if (k == b.HEXB) {
                        w = "b";
                    } else if (k == b.BOUNDS) {
                        w = "c";
                    } else if (k == b.BOUNDS2) {
                        w = "d";
                    }
                    for (int o = 0; o < b.NUMBEROFO; o += 1) {
                        String move = "" + piecesLeft.get(i) + q + w + o;
                        if (bestMoveForP.equals("") && b.isLegal(move)) {
                            bestMoveForP = move;
                            temp = this.value(move, me, opp, b);
                        } else if (b.isLegal(move) && this.value(move, me,
                            opp, b) > temp) {
                            bestMoveForP = move;
                        }
                    }
                }
            }
            if (r == 0 && b.getPointForPiece(piecesLeft.get(i), me).length
                <= FOUR && result.size() == 0 && !bestMoveForP.equals("")) {
                r = 1;
                result.add(bestMoveForP);
            } else if (r == 0 && b.getPointForPiece(piecesLeft.get(i),
                me).length <= FOUR && result.size() != 0) {
                r = 0;
            } else if (!bestMoveForP.equals("")) {
                result.add(bestMoveForP);
            }
        }
        return result;
    }

}
