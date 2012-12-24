package duo;

import static duo.Color.*;
import java.util.ArrayList;

import java.awt.Point;

/** Represents a Blokus Duo(tm) game board that may be changed.  This
 *  is a subtype of Board so that Board itself can represent a
 *  non-modifiable game board.
 * @author Andrea Wu
 */
class MutableBoard extends Board {

    /** A new, empty MutableBoard. */
    MutableBoard() {
        super();
    }

    /** A new MutableBoard whose initial contents are copied from
     *  BOARD. */
    MutableBoard(Board board) {
        super(board);
    }

    /** Make the indicated MOVE on the current board for the player
     *  that is on move if move is okay. */
    void makeMove(String move) {
        if (this.isLegal(move)) {
            this.doMove(move);
        }
    }

    /** Make the indicated MOVE on the current board for the player
     *  that is on move. */
    void doMove(String move) {
        String p = "" + move.charAt(0);
        String o = "" + move.charAt(3);
        int c = (int) move.charAt(1) - ADJUSTMENT;
        if (move.charAt(1) == 'a' || move.charAt(1) == 'b'
            || move.charAt(1) == 'c' || move.charAt(1) == 'd') {
            c = (int) move.charAt(1) - HEX;
        }
        int r = (int) move.charAt(2) - ADJUSTMENT;
        if (move.charAt(2) == 'a' || move.charAt(2) == 'b'
            || move.charAt(2) == 'c' || move.charAt(2) == 'd') {
            r = (int) move.charAt(2) - HEX;
        }
        int maxX = 0;
        int maxY = 0;
        Point[] points = this.getPointForPiece(p, this.turn());
        int size = points.length;
        for (int i = 0; i < size; i += 1) {
            int x = (int) points[i].getX();
            if (maxX < x) {
                maxX = x;
            }
        }
        for (int j = 0; j < size; j += 1) {
            int y = (int) points[j].getY();
            if (maxY < y) {
                maxY = y;
            }
        }
        Point[] result = this.flip(this.translate(this.rotate(points,
                o, size), maxX, maxY, o, c, r, size), maxX, size, o);
        ArrayList<Point> here = new ArrayList<Point>();
        for (int m = 0; m < size; m += 1) {
            here.add(result[m]);
        }
        for (int j = 0; j < size; j += 1) {
            int x = (int) result[j].getX();
            int y = (int) result[j].getY();
            Color[][] b = this.getBoard();
            b[x][y] = this.turn();
        }
        this.removePointsForPiece(p, this.turn());
        if (this.turn() == ORANGE) {
            this.setPoints(ORANGE, points.length);
            this.setPlayer(VIOLET);
        } else {
            this.setPoints(VIOLET, points.length);
            this.setPlayer(ORANGE);
        }
    }

    /** Returns a point[] with POINTS rotated,
     * O is orientation number, and SIZE is number of points in POINTS. */
    Point[] rotate(Point[] points, String o, int size) {
        Point[] orientationNumbers = getPointsForOrientation(o);
        int cos = (int) orientationNumbers[0].getX();
        int sin = (int) orientationNumbers[0].getY();
        if (o.equals("0") || o.equals("4")) {
            return points;
        } else {
            Point[] result = new Point[size];
            for (int i = 0; i < size; i += 1) {
                int xCoord = (int) points[i].getX();
                int yCoord = (int) points[i].getY();
                result[i] = new Point(cos * xCoord - sin * yCoord, sin * xCoord
                        + cos * yCoord);
            }
            return result;
        }
    }

    /** Returns a Point[] with POINTS translated,
     * MAXX is maximum x-coordinate of POINTS, MAXY is maximum
     * y-coordinate of POINTS, O is orientation number, C column,
     * R row, SIZE number of points in POINTS. */
    Point[] translate(Point[] points, int maxX, int maxY,
            String o, int c, int r, int size) {
        if (o.equals("0") || o.equals("4")) {
            Point[] result = new Point[size];
            for (int i = 0; i < size; i += 1) {
                int xCoord = (int) points[i].getX();
                int yCoord = (int) points[i].getY();
                result[i] = new Point(xCoord + c, yCoord + r);
            }
            return result;
        } else if (o.equals("1") || o.equals("5")) {
            Point[] result = new Point[size];
            for (int i = 0; i < size; i += 1) {
                int xCoord = (int) points[i].getX();
                int yCoord = (int) points[i].getY();
                result[i] = new Point(xCoord + c, yCoord + maxX + r);
            }
            return result;
        } else if (o.equals("2") || o.equals("6")) {
            Point[] result = new Point[size];
            for (int j = 0; j < size; j += 1) {
                int xCoord = (int) points[j].getX();
                int yCoord = (int) points[j].getY();
                result[j] = new Point(xCoord + maxX + c, yCoord + maxY + r);
            }
            return result;
        } else {
            Point[] result = new Point[size];
            for (int k = 0; k < size; k += 1) {
                int xCoord = (int) points[k].getX();
                int yCoord = (int) points[k].getY();
                result[k] = new Point(xCoord + maxY + c, yCoord + r);
            }
            return result;
        }
    }

    /** Returns a Point[] with POINTS flipped horizontally,
     * MAXX, MINX is max/min x-coordinate of POINTS, O, SIZE is number
     * of points in POINTS. */
    Point[] flip(Point[] points, int maxX, int size, String o) {
        if (o.equals("0") || o.equals("1") || o.equals("2") || o.equals("3")) {
            return points;
        } else {
            int minX = SIZE + SIZE;
            for (int i = 0; i < points.length; i += 1) {
                if (points[i].getX() < minX) {
                    minX = (int) points[i].getX();
                }
            }
            int max = NEGATIVE;
            for (int j = 0; j < points.length; j += 1) {
                if (points[j].getX() > max) {
                    max = (int) points[j].getX();
                }
            }
            maxX = max - minX;
            if (max == minX) {
                Point[] result = new Point[size];
                for (int k = 0; k < size; k += 1) {
                    int xCoord = (int) points[k].getX();
                    int yCoord = (int) points[k].getY();
                    result[k] = new Point(xCoord, yCoord);
                }
                return result;
            } else if (maxX % 2 == 0) {
                Point[] result = new Point[size];
                for (int l = 0; l < size; l += 1) {
                    int xCoord = (int) points[l].getX();
                    int yCoord = (int) points[l].getY();
                    if (xCoord == minX) {
                        result[l] = new Point(xCoord + maxX, yCoord);
                    } else if (xCoord == minX + 1) {
                        result[l] = new Point(xCoord, yCoord);
                    } else {
                        result[l] = new Point(xCoord - maxX, yCoord);
                    }
                }
                return result;
            } else if (maxX == 3) {
                return this.helperForFlip(points, minX, maxX, max, size);
            } else {
                Point[] result = new Point[size];
                for (int t = 0; t < size; t += 1) {
                    int xCoord = (int) points[t].getX();
                    int yCoord = (int) points[t].getY();
                    if (xCoord == minX) {
                        result[t] = new Point(xCoord + maxX, yCoord);
                    } else {
                        result[t] = new Point(xCoord - maxX, yCoord);
                    }
                }
                return result;
            }
        }
    }

    /** Returns result for if MAXX == 3 in flip method, takes
     * POINTS, MINX, MAX, and SIZE as well. */
    Point[] helperForFlip(Point[] points, int minX, int maxX, int max,
            int size) {
        Point[] result = new Point[size];
        for (int t = 0; t < size; t += 1) {
            int xCoord = (int) points[t].getX();
            int yCoord = (int) points[t].getY();
            if (xCoord == minX) {
                result[t] = new Point(xCoord + maxX, yCoord);
            } else if (xCoord == minX + 1) {
                result[t] = new Point(xCoord + 1, yCoord);
            } else if (xCoord == max - 1) {
                result[t] = new Point(xCoord - 1, yCoord);
            } else {
                result[t] = new Point(xCoord - maxX, yCoord);
            }
        }
        return result;
    }
}
