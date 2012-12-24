package duo;

import static duo.Color.*;
import java.util.ArrayList;
import java.awt.Point;
import java.util.Hashtable;

/** Represents a Blokus Duo(tm) game board.
 * @author Andrea Wu
 */
class Board {

    /** Number of rows and columns in a board. */
    public static final int SIZE = 14;
    /** Size - 2 used to check boundaries. */
    public static final int BOUNDS = 12;
    /** Size - 1 used to check boundaries. */
    public static final int BOUNDS2 = 13;
    /** "a" in int form. */
    public static final int HEXA = 10;
    /** "b" in int form. */
    public static final int HEXB = 11;
    /** Number of pieces total. */
    public static final int NUMBEROFPIECES = 21;
    /** Number of orientations possible. */
    public static final int NUMBEROFO = 8;
    /** Number of orientations possible. */
    public static final int NEGATIVE = -100;
    /** Private variable _board. */
    private Color[][] _board;
    /** Private variable _moveLstOrange.
     * Keeps track of what pieces are available. */
    private Hashtable<String, Point[]> _moveLstOrange;
    /** ArrayList<String> form of _moveLstOrang. */
    private ArrayList<String> _moveLstOrangeA;
    /** Private variable _moveLstViolet.
     * Keeps track of what pieces are available. */
    private Hashtable<String, Point[]> _moveLstViolet;
    /** ArrayList<String> form of _moveLstViolet. */
    private ArrayList<String> _moveLstVioletA;
    /** Private variable _orientation.  */
    private static Hashtable<String, Point[]> _orientation;
    /** Private variable who's turn. */
    private Color _turn;
    /** Private variable of orange's points. */
    private int _orangePoints;
    /** Private variable of violet's points. */
    private int _violetPoints;
    /** Private variable number to make to int. */
    public static final int ADJUSTMENT = 48;
    /** Private variable number to change to hex int. */
    public static final int HEX = 87;

    /** Board Constructor. */
    Board() {
        _board = new Color[SIZE][SIZE];
        Hashtable<String, Point[]> moveLst = new Hashtable<String, Point[]>();
        Hashtable<String, Point[]> orient = new Hashtable<String, Point[]>();
        this.putIntoHashtable(moveLst);
        orient.put("0", new Point[] {new Point(1, 1)});
        orient.put("1", new Point[] {new Point(0, -1)});
        orient.put("2", new Point[] {new Point(-1, 0)});
        orient.put("3", new Point[] {new Point(0, 1)});
        orient.put("4", new Point[] {new Point(1, 1)});
        orient.put("5", new Point[] {new Point(0, -1)});
        orient.put("6", new Point[] {new Point(-1, 0)});
        orient.put("7", new Point[] {new Point(0, 1)});
        _orientation = orient;
        _moveLstOrange = new Hashtable<String, Point[]>(moveLst);
        _moveLstViolet = new Hashtable<String, Point[]>(moveLst);
        _turn = Color.ORANGE;
        _moveLstOrangeA = new ArrayList<String>(makeArray());
        _moveLstVioletA = new ArrayList<String>(makeArray());
        _orangePoints = 0;
        _violetPoints = 0;
    }

    /** Puts all the pieces into constructor's MOVELST hashtable. */
    void putIntoHashtable(Hashtable<String, Point[]> moveLst) {
        moveLst.put("W", new Point[] {new Point(0, 0), new Point(1, 0),
            new Point(1, 1), new Point(2, 1), new Point(2, 2)});
        moveLst.put("Z", new Point[] {new Point(0, 2), new Point(1, 0),
            new Point(1, 1), new Point(1, 2), new Point(2, 0)});
        moveLst.put("I", new Point[] {new Point(0, 0), new Point(0, 1),
            new Point(0, 2), new Point(0, 3), new Point(0, 4)});
        moveLst.put("L", new Point[] {new Point(0, 0), new Point(0, 1),
            new Point(0, 2), new Point(0, 3), new Point(1, 0)});
        moveLst.put("U", new Point[] {new Point(0, 0), new Point(0, 1),
            new Point(0, 2), new Point(1, 0), new Point(1, 2)});
        moveLst.put("T", new Point[] {new Point(0, 0), new Point(1, 0),
            new Point(1, 1), new Point(1, 2), new Point(2, 0)});
        moveLst.put("X", new Point[] {new Point(0, 1), new Point(1, 0),
            new Point(1, 1), new Point(1, 2), new Point(2, 1)});
        moveLst.put("V", new Point[] {new Point(0, 0), new Point(1, 0),
            new Point(2, 0), new Point(2, 1), new Point(2, 2)});
        moveLst.put("F", new Point[] {new Point(0, 1), new Point(1, 0),
            new Point(1, 1), new Point(1, 2), new Point(2, 2)});
        moveLst.put("P", new Point[] {new Point(0, 0), new Point(0, 1),
            new Point(0, 2), new Point(1, 1), new Point(1, 2)});
        moveLst.put("Y", new Point[] {new Point(0, 0), new Point(0, 1),
            new Point(0, 2), new Point(0, 3), new Point(1, 2)});
        moveLst.put("N", new Point[] {new Point(0, 1), new Point(0, 2),
            new Point(0, 3), new Point(1, 0), new Point(1, 1)});
        moveLst.put("z", new Point[] {new Point(0, 1), new Point(1, 0),
            new Point(1, 1), new Point(2, 0)});
        moveLst.put("i", new Point[] {new Point(0, 0), new Point(0, 1),
            new Point(0, 2), new Point(0, 3)});
        moveLst.put("d", new Point[] {new Point(0, 0), new Point(1, 0),
            new Point(1, 1), new Point(1, 2)});
        moveLst.put("s", new Point[] {new Point(0, 0), new Point(0, 1),
            new Point(1, 0), new Point(1, 1)});
        moveLst.put("t", new Point[] {new Point(0, 0), new Point(1, 0),
            new Point(1, 1), new Point(2, 0)});
        moveLst.put("3", new Point[] {new Point(0, 0), new Point(0, 1),
            new Point(0, 2)});
        moveLst.put("v", new Point[] {new Point(0, 0), new Point(0, 1),
            new Point(1, 0)});
        moveLst.put("2", new Point[] {new Point(0, 0), new Point(0, 1)});
        moveLst.put("1", new Point[] {new Point(0, 0)});
    }

    /** Board constructor that takes in BOARD. */
    Board(Board board) {
        this._board = new Color[SIZE][SIZE];
        for (int j = board.BOUNDS2; j >= 0; j -= 1) {
            for (int i = 0; i < board.SIZE; i += 1) {
                _board[i][j] = board._board[i][j];
            }
        }
        this._moveLstOrange = new Hashtable<String,
            Point[]>(board._moveLstOrange);
        this._moveLstViolet = new Hashtable<String,
            Point[]>(board._moveLstViolet);
        this._moveLstOrangeA = new ArrayList<String>(board._moveLstOrangeA);
        this._moveLstVioletA = new ArrayList<String>(board._moveLstVioletA);
        this._turn = board._turn;
        this._orientation = board._orientation;
        this._orangePoints = board._orangePoints;
        this._violetPoints = board._violetPoints;
    }

    /** Returns an ArrayList<String> of all the pieces. */
    static ArrayList<String> makeArray() {
        ArrayList<String> result = new ArrayList<String>();
        result.add("W");
        result.add("F");
        result.add("Z");
        result.add("Y");
        result.add("L");
        result.add("U");
        result.add("T");
        result.add("X");
        result.add("I");
        result.add("V");
        result.add("P");
        result.add("N");
        result.add("z");
        result.add("i");
        result.add("d");
        result.add("s");
        result.add("t");
        result.add("3");
        result.add("v");
        result.add("2");
        result.add("1");
        return result;
    }

    /** Return the current contents of the square in column COL and row ROW. */
    Color get(int col, int row) {
        if (_board[col][row] == null) {
            return EMPTY;
        } else {
            return _board[col][row];
        }
    }

    /** Return the color of player whose turn it is. */
    Color turn() {
        return _turn;
    }

    /** Returns true iff MOVE is a syntactically correct move. */
    static boolean isWellFormed(String move) {
        if (move.length() == 4) {
            String legalMoves = "W, Z, I, L, U, T, X, V, F, P,"
                + " Y, N, z, i, d, s, t, 3, v, 2, 1";
            boolean pieceTF = false;
            boolean columnTF = false;
            boolean rowTF = false;
            boolean orientationTF = false;
            char piece = move.charAt(0);
            String p = "" + piece;
            char column = move.charAt(1);
            String c = "" + column;
            char row = move.charAt(2);
            String r = "" + row;
            char orientation = move.charAt(3);
            String o = "" + orientation;
            if (legalMoves.contains(p)) {
                pieceTF = true;
            }
            if (c.matches("[0-9,a-d]")) {
                columnTF = true;
            }
            if (r.matches("[0-9,a-d]")) {
                rowTF = true;
            }
            if (o.matches("[0-7]")) {
                orientationTF = true;
            }
            if (pieceTF && columnTF && rowTF && orientationTF) {
                return true;
            }
        }
        return false;
    }

    /** Returns true iff MOVE is currently legal. */
    boolean isLegal(String move) {
        if (this.isWellFormed(move) && this.getPointForPiece(""
                + move.charAt(0), this.turn()) != null) {
            MutableBoard b = new MutableBoard(this);
            String ori = "" + move.charAt(3);
            Color[][] bo = this.getBoard();
            Point[] piece = getPointForPiece("" + move.charAt(0), this.turn());
            int size = piece.length;
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
            for (int i = 0; i < size; i += 1) {
                if (maxX < (int) piece[i].getX()) {
                    maxX = (int) piece[i].getX();
                }
            }
            for (int j = 0; j < size; j += 1) {
                int y = (int) piece[j].getY();
                if (maxY < y) {
                    maxY = y;
                }
            }
            Point[] n = b.flip(b.translate(b.rotate(piece,
                ori, size), maxX, maxY, ori, c, r, size), maxX, size, ori);
            ArrayList<Point> here = new ArrayList<Point>();
            for (int m = 0; m < size; m += 1) {
                here.add(n[m]);
            }
            if (!this.overl(n, size, this.getBoard()) || !oB(n)) {
                return false;
            }
            if (this.getNumberOfPieces(this.turn()) == NUMBEROFPIECES) {
                if (!this.sides(n, here, this.getBoard(), size, this)
                        || !this.cFill(n)) {
                    return false;
                }
            } else if (!this.corners(n, size, this.getBoard(), here, this)
                    || !this.sides(n, here, bo, size, this)) {
                return false;
            }
            return true;
        }
        return false;
    }

    /** Takes POINTSAREHERE, SIZE of array, BO, POINTSPRESENT, and OLDB board
     * to return whether the piece is touching a corner of its own color. */

    boolean corners(Point[] pointsAreHere, int size, Color[][] bo,
    ArrayList<Point> pointsPresent, Board oldB) {
        Color[][] old = oldB._board;
        ArrayList<Point> corners = this.theseAreCorners(pointsAreHere, size,
            pointsPresent);
        ArrayList<Point> possibilities = new ArrayList<Point>();
        for (int g = 0; g < corners.size(); g += 1) {
            int x = (int) corners.get(g).getX();
            int y = (int) corners.get(g).getY();
            Point h = new Point(x - 1, y - 1);
            Point j = new Point(x - 1, y + 1);
            Point k = new Point(x + 1, y - 1);
            Point l = new Point(x + 1, y + 1);
            if ((!pointsPresent.contains(h)) && (!possibilities.contains(h))) {
                possibilities.add(h);
            }
            if ((!pointsPresent.contains(j)) && (!possibilities.contains(j))) {
                possibilities.add(j);
            }
            if ((!pointsPresent.contains(k)) && (!possibilities.contains(k))) {
                possibilities.add(k);
            }
            if ((!pointsPresent.contains(l)) && (!pointsPresent.contains(l))) {
                possibilities.add(l);
            }
        }
        boolean cornerTouch = false;
        for (int p = 0; p < possibilities.size(); p += 1) {
            int x = (int) possibilities.get(p).getX();
            int y = (int) possibilities.get(p).getY();
            if (x >= 0 && y >= 0 && x <= BOUNDS2 && y <= BOUNDS2) {
                if (bo[x][y] == this.turn()) {
                    cornerTouch = true;
                }
            }
        }
        return cornerTouch;
    }

    /** Returns an ArrayList<String> of corners with POINTSAREHERE, SIZE,
     * and POINTSPRESENT. */
    ArrayList<Point> theseAreCorners(Point[] pointsAreHere, int size,
            ArrayList<Point> pointsPresent) {
        ArrayList<Point> corners = new ArrayList<Point>();
        for (int a = 0; a < size; a += 1) {
            int x = (int) pointsAreHere[a].getX();
            int y = (int) pointsAreHere[a].getY();
            if (!((pointsPresent.contains(new Point(x + 1, y))
                    && pointsPresent.contains(new Point(x - 1, y)))
                    || (pointsPresent.contains(new Point(x, y + 1))
                            && pointsPresent.contains(new Point(x, y - 1))))) {
                corners.add(new Point(x, y));
            }
        }
        return corners;
    }

    /** Takes POINTSAREHERE, POINTSPRESENT, BO, SIZE and OLD board to return
     * whether the piece is touching a side of its own color. */
    boolean sides(Point[] pointsPresent, ArrayList<Point>
    pointsAreHere, Color[][] bo, int size, Board old) {
        for (int h = 0; h < size; h += 1) {
            int x = (int) pointsPresent[h].getX();
            int y = (int) pointsPresent[h].getY();
            if ((x >= 0 && y >= 0 && x <= BOUNDS && y <= BOUNDS2)
                    && (!pointsAreHere.contains(new Point(x + 1, y)))) {
                if (bo[x + 1][y] == old._turn) {
                    return false;
                }
            }
            if ((x >= 1 && y >= 0 && x <= SIZE && y <= BOUNDS2)
                    && (!pointsAreHere.contains(new Point(x - 1, y)))) {
                if (bo[x - 1][y] == old._turn) {
                    return false;
                }
            }
            if ((x >= 0 && y >= 0 && x <= BOUNDS2 && y <= BOUNDS)
                    && (!pointsAreHere.contains(new Point(x, y + 1)))) {
                if (bo[x][y + 1] == old._turn) {
                    return false;
                }
            }
            if ((x >= 0 && y >= 1 && x <= BOUNDS2 && y <= SIZE)
                    && (!pointsAreHere.contains(new Point(x, y - 1)))) {
                if (bo[x][y - 1] == old._turn) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Takes P to return whether the piece is touching
     * a side of its own color. False = touching. */
    boolean sides(Point p) {
        int x = (int) p.getX();
        int y = (int) p.getY();
        Color[][] b = this._board;
        if (x >= 0 && y >= 0 && x <= BOUNDS && y <= BOUNDS2
                && b[x + 1][y] == this.turn()) {
            return false;
        }
        if (x >= 0 && y >= 0 && x <= BOUNDS2 && y <= BOUNDS
                && b[x][y + 1] == this.turn()) {
            return false;
        }
        if (x >= 1 && x <= SIZE && y >= 0 && y <= BOUNDS2
                && b[x - 1][y] == this.turn()) {
            return false;
        }
        if (x >= 0 && x <= BOUNDS2 && y >= 1 && y <= SIZE
                && b[x][y - 1] == this.turn()) {
            return false;
        }
        return true;
    }

    /** Takes points N and returns whether corners are filled. */
    boolean cFill(Point[] n) {
        for (int i = 0; i < n.length; i += 1) {
            int x = (int) n[i].getX();
            int y = (int) n[i].getY();
            if ((x == 0 && y == 0) || (x == 0 && y == BOUNDS2)
                    || (x == BOUNDS2 && y == BOUNDS2)
                    || (x == BOUNDS2 && y == 0)) {
                return true;
            }
        }
        return false;
    }

    /** Takes PIECES, OLDB, and SIZE.
     *  Returns false if overlapping, true if not. */
    boolean overl(Point[] pieces, int size, Color[][] oldB) {
        for (int i = 0; i < size; i += 1) {
            int x = (int) pieces[i].getX();
            int y = (int) pieces[i].getY();
            if (x >= 0 && x <= BOUNDS2 && y >= 0 && y <= BOUNDS2) {
                if (oldB[x][y] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Takes PIECES and sees if they're
     * within the board size. Returns false if not. */
    boolean oB(Point[] pieces) {
        for (int i = 0; i < pieces.length; i += 1) {
            int x = (int) pieces[i].getX();
            int y = (int) pieces[i].getY();
            if (x < 0 || x > BOUNDS2 || y < 0 || y > BOUNDS2) {
                return false;
            }
        }
        return true;
    }

    /** Returns true if the person whose turn it is C
     * still has an available move.*/
    boolean hasMove(Color c) {
        ArrayList<Point> test = new ArrayList<Point>();
        Color[][] b = this.getBoard();
        for (int i = 0; i < SIZE; i += 1) {
            for (int j = 0; j < SIZE; j += 1) {
                int x = i;
                int y = j;
                if (b[i][j] == null) {
                    test.add(new Point(i, j));
                }
            }
        }
        ArrayList<Point> result = new ArrayList<Point>();
        for (int k = 0; k < test.size(); k += 1) {
            int x = (int) test.get(k).getX();
            int y = (int) test.get(k).getY();
            Point p = new Point(x, y);
            if (x >= 0 && y >= 0 && x <= BOUNDS && y <= BOUNDS && (b[x + 1]
                [y + 1] == c || b[x + 1][y + 1] == null) && this.sides(p)) {
                result.add(p);
            } else if (x >= 1 && y >= 0 && x <= SIZE && y <= BOUNDS && (b[x - 1]
                [y + 1] == c || b[x - 1][y + 1] == null) && this.sides(p)) {
                result.add(p);
            } else if (x >= 1 && y >= 1 && x <= SIZE && y <= SIZE
                && (b[x - 1][y - 1] == c
                || b[x - 1][y - 1] == null) && this.sides(p)) {
                result.add(p);
            } else if (x >= 0 && y >= 1 && x <= BOUNDS && y <= SIZE
                && (b[x + 1][y - 1] == c
                || b[x + 1][y - 1] == null) && this.sides(p)) {
                result.add(p);
            }
        }
        ArrayList<String> s;
        if (c == ORANGE) {
            s = _moveLstOrangeA;
        } else {
            s = _moveLstVioletA;
        }
        for (int r = 0; r < result.size(); r += 1) {
            int x = (int) result.get(r).getX();
            int y = (int) result.get(r).getY();
            if (x >= HEXA || y >= HEXA) {
                if (this.helperForHasMove(x, y, s)) {
                    return true;
                } else {
                    continue;
                }
            }
            for (int l = 0; l < s.size(); l += 1) {
                for (int o = 0; o < NUMBEROFO; o += 1) {
                    if (this.isLegal("" + s.get(l) + x + y + o)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /** Turns X and Y into chars to test in isLegal.
     * Returns true or false for moves in S. */
    boolean helperForHasMove(int x, int y, ArrayList<String> s) {
        char changedX = 'x';
        char changedY = 'y';
        if (x == HEXA) {
            changedX = 'a';
        } else if (x == HEXB) {
            changedX = 'b';
        } else if (x == BOUNDS) {
            changedX = 'c';
        } else if (x == BOUNDS2) {
            changedX = 'd';
        }
        if (y == HEXA) {
            changedY = 'a';
        } else if (y == HEXB) {
            changedY = 'b';
        } else if (y == BOUNDS) {
            changedY = 'c';
        } else if (y == BOUNDS2) {
            changedY = 'd';
        }
        for (int l = 0; l < s.size(); l += 1) {
            for (int o = 0; o < NUMBEROFO; o += 1) {
                if (changedX != 'x' && changedY != 'y') {
                    if (this.isLegal("" + s.get(l) + changedX + changedY + o)) {
                        return true;
                    }
                } else if (changedX != 'x') {
                    if (this.isLegal("" + s.get(l) + changedX + y + o)) {
                        return true;
                    }
                } else {
                    if (this.isLegal("" + s.get(l) + x + changedY + o)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /** Returns the point[] of PIECE and C. */
    Point[] getPointForPiece(String piece, Color c) {
        if (c == ORANGE) {
            return _moveLstOrange.get(piece);
        } else {
            return _moveLstViolet.get(piece);
        }
    }

    /** Removes the point[] of PIECE and C from hashtable
     * and ArrayList of pieces left. */
    void removePointsForPiece(String piece, Color c) {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> getFrom = new ArrayList<String>();
        if (c == ORANGE) {
            _moveLstOrange.remove(piece);
            getFrom = _moveLstOrangeA;
        } else {
            _moveLstViolet.remove(piece);
            getFrom = _moveLstVioletA;
        }
        for (int i = 0; i < getFrom.size(); i += 1) {
            if (!getFrom.get(i).equals(piece)) {
                result.add(getFrom.get(i));
            }
        }
        if (c == ORANGE) {
            _moveLstOrangeA = result;
        } else {
            _moveLstVioletA = result;
        }
    }

    /** Puts the point[] of PIECE, LETTER, and C. */
    void putPiece(String letter, Point[] piece, Color c) {
        if (c == ORANGE) {
            _moveLstOrange.put("" + letter, piece);
        } else {
            _moveLstViolet.put("" + letter, piece);
        }
    }

    /** Returns the number of elements in hashtable for C. */
    int getNumberOfPieces(Color c) {
        if (c == ORANGE) {
            return _moveLstOrange.size();
        } else {
            return _moveLstViolet.size();
        }
    }

    /** Returns void but sets the number of points for C and I. */
    void setPoints(Color c, int i) {
        if (c == ORANGE) {
            _orangePoints += i;
        } else {
            _violetPoints += i;
        }
    }

    /** Returns the number of points for C. */
    int getPoints(Color c) {
        if (c == ORANGE) {
            return _orangePoints;
        } else {
            return _violetPoints;
        }
    }

    /** Returns the available pieces for C. */
    ArrayList<String> getPiecesLeft(Color c) {
        if (c == ORANGE) {
            return _moveLstOrangeA;
        } else {
            return _moveLstVioletA;
        }
    }

    /** Returns the point[] of ORIENTATION. */
    Point[] getPointsForOrientation(String orientation) {
        return _orientation.get(orientation);
    }

    /** Returns the board. */
    Color[][] getBoard() {
        return _board;
    }

    /** Returns nothing but sets _turn as PLAYER. */
    void setPlayer(Color player) {
        this._turn = player;
    }
}
