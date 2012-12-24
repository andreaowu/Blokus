package duo;

import java.awt.Point;
import java.util.HashMap;

import org.junit.Test;
import ucb.junit.textui;
import static org.junit.Assert.*;
import static duo.Color.*;
import java.util.ArrayList;

/** Unit tests for the duo package.
 * @author Andrea Wu*/
public class Testing {

    /** Run all JUnit tests in the duo package. */
    public static void main(String[] ignored) {
        textui.runClasses(duo.Testing.class);
    }
    
    @Test
    public void testthis() {
        HashMap<String, ArrayList<String>> incoming = new HashMap<String, ArrayList<String>>();
        ArrayList<String> s = new ArrayList<String>();
        s.add("hello");
        incoming.put("hi", s);
        incoming.get("hi").add("rawr");
        assertEquals(incoming.get("hi"), "hello, rawr");
    }

    @Test
    public void emptyBoard() {
        Board b = new Board();
        for (int c = 0; c < b.SIZE; c += 1) {
            for (int r = 0; r < b.SIZE; r += 1) {
                assertEquals(EMPTY, b.get(c, r));
            }
        }
    }

    /** BOARD CLASS. */

    /** turn() test. */
    @Test
    public void turnTest() {
        Board b = new Board();
        assertEquals(ORANGE, b.turn());
    }

    /** makeArray() test. */
    @Test
    public void makeArray() {
        Board b = new Board();
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
        assertEquals(result, b.makeArray());
    }

    /** isWellFormed() tests. */
    @Test
    public void isWellFormed1() {
        Board b = new Board();
        assertEquals(true, b.isWellFormed("W000"));
    }

    @Test
    public void isWellFormed2() {
        Board b = new Board();
        assertEquals(true, b.isWellFormed("Zad0"));
    }

    @Test
    public void isWellFormed3() {
        Board b = new Board();
        assertEquals(false, b.isWellFormed("wbd0"));
    }

    @Test
    public void isWellFormed4() {
        Board b = new Board();
        assertEquals(false, b.isWellFormed("Ifd1"));
    }

    @Test
    public void isWellFormed5() {
        Board b = new Board();
        assertEquals(false, b.isWellFormed("Lce2"));
    }

    @Test
    public void isWellFormed6() {
        Board b = new Board();
        assertEquals(false, b.isWellFormed("Udc8"));
    }

    /** getPointForPiece(String piece) tests. */
    @Test
    public void getPointForPiece1() {
        Board b = new Board();
        Point[] p = new Point[] {new Point(0, 0), new Point(1, 0),
            new Point(1, 1), new Point(2, 1), new Point(2, 2)};
        assertArrayEquals(p, b.getPointForPiece("W", ORANGE));
    }

    /** removePointsForPiece(String piece, Color c) tests. */
    @Test
    public void removePointsForPiece1() {
        MutableBoard b = new MutableBoard();
        b.removePointsForPiece("W", ORANGE);
        assertNull(b.getPointForPiece("W", ORANGE));
    }

    @Test
    public void removePointsForPiece2() {
        MutableBoard b = new MutableBoard();
        b.removePointsForPiece("W", VIOLET);
        assertNull(b.getPointForPiece("W", VIOLET));
    }

    /** putPiece(String s, Point[] piece, Color c) tests. */
    @Test
    public void putPiece1() {
        MutableBoard b = new MutableBoard();
        b.removePointsForPiece("W", ORANGE);
        Point[] p = new Point[] {new Point(0, 0), new Point(1, 0),
            new Point(1, 1), new Point(2, 1), new Point(2, 2)};
        b.putPiece("W", p, ORANGE);
        assertNotNull(b.getPointForPiece("W", ORANGE));
    }

    @Test
    public void putPiece2() {
        MutableBoard b = new MutableBoard();
        b.removePointsForPiece("W", VIOLET);
        Point[] p = new Point[] {new Point(0, 0), new Point(1, 0),
            new Point(1, 1), new Point(2, 1), new Point(2, 2)};
        b.putPiece("W", p, VIOLET);
        assertNotNull(b.getPointForPiece("W", VIOLET));
    }

    /** cFill(Point[] n) tests. */
    @Test
    public void cFill1() {
        Board b = new Board();
        Point[] p = new Point[] {new Point(0, 0), new Point(1, 0),
            new Point(1, 1), new Point(1, 2)};
        assertTrue(b.cFill(p));
    }

    @Test
    public void cFill2() {
        Board b = new Board();
        Point[] p = new Point[] {new Point(1, 5), new Point(1, 0),
            new Point(1, 1), new Point(1, 2)};
        assertFalse(b.cFill(p));
    }

    /** getPointsForOrientation(String orientation) tests. */
    @Test
    public void getPointsForOrientation1() {
        Board b = new Board();
        Point[] p = new Point[] {new Point(1, 1)};
        assertArrayEquals(p, b.getPointsForOrientation("0"));
    }

    @Test
    public void getPointsForOrientation2() {
        Board b = new Board();
        Point[] p = new Point[] {new Point(0, -1)};
        assertArrayEquals(p, b.getPointsForOrientation("1"));
    }

    @Test
    public void getPointsForOrientation3() {
        Board b = new Board();
        Point[] p = new Point[] {new Point(-1, 0)};
        assertArrayEquals(p, b.getPointsForOrientation("2"));
    }

    @Test
    public void getPointsForOrientation4() {
        Board b = new Board();
        Point[] p = new Point[] {new Point(0, 1)};
        assertArrayEquals(p, b.getPointsForOrientation("3"));
    }

    @Test
    public void getPointsForOrientation5() {
        Board b = new Board();
        Point[] p = new Point[] {new Point(1, 1)};
        assertArrayEquals(p, b.getPointsForOrientation("4"));
    }

    @Test
    public void getPointsForOrientation6() {
        Board b = new Board();
        Point[] p = new Point[] {new Point(0, -1)};
        assertArrayEquals(p, b.getPointsForOrientation("5"));
    }

    @Test
    public void getPointsForOrientation7() {
        Board b = new Board();
        Point[] p = new Point[] {new Point(-1, 0)};
        assertArrayEquals(p, b.getPointsForOrientation("6"));
    }

    @Test
    public void getPointsForOrientation8() {
        Board b = new Board();
        Point[] p = new Point[] {new Point(0, 1)};
        assertArrayEquals(p, b.getPointsForOrientation("7"));
    }

    /** setPlayer(Color player) tests. */

    @Test
    public void setPlayer1() {
        Board b = new Board();
        Color c = VIOLET;
        b.setPlayer(c);
    }

    /** isLegal(String move) tests. */

    @Test
    public void isLegal1() {
        Board b = new Board();
        assertTrue(b.isLegal("T000"));
    }

    @Test
    public void isLegal2() {
        Board b = new Board();
        assertFalse(b.isLegal("T480"));
    }

    /** sides. */
    @Test
    public void isLegal3() {
        MutableBoard b = new MutableBoard();
        b.makeMove("T000");
        assertFalse(b.isLegal("V210"));
    }

    /** overlapping. */
    @Test
    public void isLegal4() {
        MutableBoard b = new MutableBoard();
        b.makeMove("T000");
        assertFalse(b.isLegal("V000"));
    }

    @Test
    public void isLegal5() {
        MutableBoard b = new MutableBoard();
        b.makeMove("T000");
        b.makeMove("Vdd0");
        assertFalse(b.isLegal(""));
    }

    /** corners. */
    @Test
    public void isLegal6() {
        MutableBoard b = new MutableBoard();
        b.makeMove("U004");
        b.makeMove("Uc00");
        assertFalse(b.isLegal("P320"));
    }

    /** corners. */
    @Test
    public void isLegal7() {
        MutableBoard b = new MutableBoard();
        b.makeMove("U004");
        b.makeMove("Uc00");
        assertTrue(b.isLegal("P230"));
    }

    /** corners. */
    @Test
    public void isLegal8() {
        MutableBoard b = new MutableBoard();
        b.makeMove("U004");
        b.makeMove("Uc00");
        assertFalse(b.isLegal("Pcc0"));
    }

    @Test
    public void isLegal9() {
        MutableBoard b = new MutableBoard();
        assertFalse(b.isLegal("Udd4"));
    }

    /** hasMove(). */
    @Test
    public void hasMove1() {
        MutableBoard b = new MutableBoard();
        assertTrue(b.hasMove(ORANGE));
    }

    /** getNumberOfPieces(Color c). */
    @Test
    public void getNumberOfPieces1() {
        MutableBoard b = new MutableBoard();
        assertEquals(21, b.getNumberOfPieces(ORANGE));
    }

    @Test
    public void getNumberOfPieces2() {
        MutableBoard b = new MutableBoard();
        assertEquals(21, b.getNumberOfPieces(VIOLET));
    }

    @Test
    public void getNumberOfPieces3() {
        MutableBoard b = new MutableBoard();
        b.removePointsForPiece("W", ORANGE);
        b.removePointsForPiece("X", ORANGE);
        b.removePointsForPiece("F", ORANGE);
        b.removePointsForPiece("I", ORANGE);
        b.removePointsForPiece("1", ORANGE);
        assertEquals(16, b.getNumberOfPieces(ORANGE));
    }

    @Test
    public void getNumberOfPieces4() {
        MutableBoard b = new MutableBoard();
        b.removePointsForPiece("d", VIOLET);
        b.removePointsForPiece("T", VIOLET);
        b.removePointsForPiece("s", VIOLET);
        b.removePointsForPiece("2", VIOLET);
        b.removePointsForPiece("V", VIOLET);
        b.removePointsForPiece("W", VIOLET);
        b.removePointsForPiece("X", VIOLET);
        b.removePointsForPiece("F", VIOLET);
        b.removePointsForPiece("I", VIOLET);
        b.removePointsForPiece("1", VIOLET);
        assertEquals(11, b.getNumberOfPieces(VIOLET));
    }

    /** setPoints(Color c, int i), getPoints(Color c).  */
    @Test
    public void setgetPoints1() {
        MutableBoard b = new MutableBoard();
        b.setPoints(ORANGE, 5);
        assertEquals(5, b.getPoints(ORANGE));
    }

    @Test
    public void setgetPoints2() {
        MutableBoard b = new MutableBoard();
        b.setPoints(VIOLET, 12);
        assertEquals(12, b.getPoints(VIOLET));
    }

    /** getPiecesLeft(Color c). */
    @Test
    public void getPiecesLeft() {
        MutableBoard b = new MutableBoard();
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
        assertEquals(result, b.getPiecesLeft(ORANGE));
    }

    @Test
    public void getPiecesLeft2() {
        MutableBoard b = new MutableBoard();
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
        assertEquals(result, b.getPiecesLeft(VIOLET));
    }

    @Test
    public void getPiecesLeft3() {
        MutableBoard b = new MutableBoard();
        ArrayList<String> result = new ArrayList<String>();
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
        b.removePointsForPiece("W", ORANGE);
        b.removePointsForPiece("F", ORANGE);
        b.removePointsForPiece("Z", ORANGE);
        b.removePointsForPiece("Y", ORANGE);
        b.removePointsForPiece("L", ORANGE);
        b.removePointsForPiece("U", ORANGE);
        b.removePointsForPiece("T", ORANGE);
        assertEquals(result, b.getPiecesLeft(ORANGE));
    }

    @Test
    public void getPiecesLeft4() {
        MutableBoard b = new MutableBoard();
        ArrayList<String> result = new ArrayList<String>();
        result.add("U");
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
        b.removePointsForPiece("W", ORANGE);
        b.removePointsForPiece("F", ORANGE);
        b.removePointsForPiece("Z", ORANGE);
        b.removePointsForPiece("Y", ORANGE);
        b.removePointsForPiece("L", ORANGE);
        b.removePointsForPiece("U", VIOLET);
        b.removePointsForPiece("T", ORANGE);
        assertEquals(result, b.getPiecesLeft(ORANGE));
    }

    /** turn(). */

    @Test
    public void turn1() {
        MutableBoard b = new MutableBoard();
        assertEquals(ORANGE, b.turn());
    }

    @Test
    public void turn2() {
        MutableBoard b = new MutableBoard();
        b.makeMove("W000");
        assertEquals(VIOLET, b.turn());
    }

    /** getBoard(). */
    @Test
    public void getBoard() {
        MutableBoard b = new MutableBoard();
        b.makeMove("W000");
        Color[][] bo = b.getBoard();
        assertEquals(bo[0][0], ORANGE);
    }

    /** get(int col, int row). */
    @Test
    public void get1() {
        MutableBoard b = new MutableBoard();
        b.makeMove("W000");
        b.makeMove("Tb00");
        assertEquals(b.get(12, 0), VIOLET);
    }

    @Test
    public void get2() {
        MutableBoard b = new MutableBoard();
        b.makeMove("W000");
        b.makeMove("Tb00");
        assertEquals(b.get(0, 0), ORANGE);
    }

    /** setPlayer(Color player). */
    @Test
    public void setPlayer() {
        MutableBoard b = new MutableBoard();
        b.setPlayer(VIOLET);
        assertEquals(b.turn(), VIOLET);
    }

    /** MUTABLEBOARD CLASS. */

    /** rotate(Point[] points, String o, int size). */

    @Test
    public void rotate1() {
        MutableBoard b = new MutableBoard();
        Point[] points = b.getPointForPiece("Y", b.turn());
        String o = "1";
        int size = 5;
        Point[] results = new Point[] {new Point(0, 0), new Point(1, 0),
            new Point(2, 0), new Point(3, 0), new Point(2, -1)};
        assertArrayEquals(results, b.rotate(points, o, size));
    }

    @Test
    public void rotate2() {
        MutableBoard b = new MutableBoard();
        Point[] points = b.getPointForPiece("X", b.turn());
        String o = "2";
        int size = 5;
        Point[] results = new Point[] {new Point(0, -1), new Point(-1, 0),
            new Point(-1, -1), new Point(-1, -2), new Point(-2, -1)};
        assertArrayEquals(results, b.rotate(points, o, size));
    }

    @Test
    public void rotate3() {
        MutableBoard b = new MutableBoard();
        Point[] points = b.getPointForPiece("I", b.turn());
        String o = "3";
        int size = 5;
        Point[] results = new Point[] {new Point(0, 0), new Point(-1, 0),
            new Point(-2, 0), new Point(-3, 0), new Point(-4, 0)};
        assertArrayEquals(results, b.rotate(points, o, size));
    }

    @Test
    public void rotate4() {
        MutableBoard b = new MutableBoard();
        Point[] points = b.getPointForPiece("N", b.turn());
        String o = "4";
        int size = 5;
        Point[] results = new Point[] {new Point(0, 1), new Point(0, 2),
            new Point(0, 3), new Point(1, 0), new Point(1, 1)};
        assertArrayEquals(results, b.rotate(points, o, size));
    }

    @Test
    public void rotate5() {
        MutableBoard b = new MutableBoard();
        Point[] points = b.getPointForPiece("F", b.turn());
        String o = "5";
        int size = 5;
        Point[] results = new Point[] {new Point(1, 0), new Point(0, -1),
            new Point(1, -1), new Point(2, -1), new Point(2, -2)};
        assertArrayEquals(results, b.rotate(points, o, size));
    }

    @Test
    public void rotate6() {
        MutableBoard b = new MutableBoard();
        Point[] points = b.getPointForPiece("Z", b.turn());
        String o = "6";
        int size = 5;
        Point[] results = new Point[] {new Point(0, -2), new Point(-1, 0),
            new Point(-1, -1), new Point(-1, -2), new Point(-2, 0)};
        assertArrayEquals(results, b.rotate(points, o, size));
    }

    @Test
    public void rotate7() {
        MutableBoard b = new MutableBoard();
        Point[] points = b.getPointForPiece("T", b.turn());
        String o = "7";
        int size = 5;
        Point[] results = new Point[] {new Point(0, 0), new Point(0, 1),
            new Point(-1, 1), new Point(-2, 1), new Point(0, 2)};
        assertArrayEquals(results, b.rotate(points, o, size));
    }

    @Test
    public void rotate8() {
        MutableBoard b = new MutableBoard();
        Point[] points = b.getPointForPiece("W", b.turn());
        String o = "0";
        int size = 5;
        Point[] results = new Point[] {new Point(0, 0), new Point(1, 0),
            new Point(1, 1), new Point(2, 1), new Point(2, 2)};
        assertArrayEquals(results, b.rotate(points, o, size));
    }

    /** translate(Point[] points, int maxX, int maxY,
        String o, int c, int r, int size). */

    @Test
    public void translate1() {
        MutableBoard b = new MutableBoard();
        Point[] points = b.getPointForPiece("z", b.turn());
        Point[] results = new Point[] {new Point(3, 6), new Point(4, 5),
            new Point(4, 6), new Point(5, 5)};
        assertArrayEquals(results, b.translate(points, 2, 1, "1", 3, 3, 4));
    }

    @Test
    public void translate2() {
        MutableBoard b = new MutableBoard();
        Point[] points = b.getPointForPiece("i", b.turn());
        Point[] results = new Point[] {new Point(5, 2), new Point(5, 3),
            new Point(5, 4), new Point(5, 5)};
        assertArrayEquals(results, b.translate(points, 0, 3, "0", 5, 2, 4));
    }

    @Test
    public void translate3() {
        MutableBoard b = new MutableBoard();
        Point[] points = b.getPointForPiece("d", b.turn());
        Point[] results = new Point[] {new Point(4, 4), new Point(5, 4),
            new Point(5, 5), new Point(5, 6)};
        assertArrayEquals(results, b.translate(points, 1, 2, "2", 3, 2, 4));
    }

    @Test
    public void translate4() {
        MutableBoard b = new MutableBoard();
        Point[] points = b.getPointForPiece("s", b.turn());
        Point[] results = new Point[] {new Point(5, 2), new Point(5, 3),
            new Point(6, 2), new Point(6, 3)};
        assertArrayEquals(results, b.translate(points, 1, 2, "3", 3, 2, 4));
    }

    @Test
    public void translate5() {
        MutableBoard b = new MutableBoard();
        Point[] points = b.getPointForPiece("z", b.turn());
        Point[] results = new Point[] {new Point(3, 6), new Point(4, 5),
            new Point(4, 6), new Point(5, 5)};
        assertArrayEquals(results, b.translate(points, 2, 1, "5", 3, 3, 4));
    }

    @Test
    public void translate6() {
        MutableBoard b = new MutableBoard();
        Point[] points = b.getPointForPiece("i", b.turn());
        Point[] results = new Point[] {new Point(5, 2), new Point(5, 3),
            new Point(5, 4), new Point(5, 5)};
        assertArrayEquals(results, b.translate(points, 0, 3, "4", 5, 2, 4));
    }

    @Test
    public void translate7() {
        MutableBoard b = new MutableBoard();
        Point[] points = b.getPointForPiece("d", b.turn());
        Point[] results = new Point[] {new Point(4, 4), new Point(5, 4),
            new Point(5, 5), new Point(5, 6)};
        assertArrayEquals(results, b.translate(points, 1, 2, "6", 3, 2, 4));
    }

    @Test
    public void translate8() {
        MutableBoard b = new MutableBoard();
        Point[] points = b.getPointForPiece("s", b.turn());
        Point[] results = new Point[] {new Point(5, 2), new Point(5, 3),
            new Point(6, 2), new Point(6, 3)};
        assertArrayEquals(results, b.translate(points, 1, 2, "7", 3, 2, 4));
    }

    /** flip(Point[] points, int maxX, int size, String o). */
    @Test
    public void flip1() {
        MutableBoard b = new MutableBoard();
        Point[] points = b.getPointForPiece("t", b.turn());
        Point[] results = new Point[] {new Point(0, 0), new Point(1, 0),
            new Point(1, 1), new Point(2, 0)};
        assertArrayEquals(results, b.flip(points, 2, 4, "0"));
    }

    @Test
    public void flip2() {
        MutableBoard b = new MutableBoard();
        Point[] points = b.getPointForPiece("V", b.turn());
        Point[] results = new Point[] {new Point(0, 0), new Point(1, 0),
            new Point(2, 0), new Point(2, 1), new Point(2, 2)};
        assertArrayEquals(results, b.flip(points, 2, 5, "1"));
    }

    @Test
    public void flip3() {
        MutableBoard b = new MutableBoard();
        Point[] points = b.getPointForPiece("F", b.turn());
        Point[] results = new Point[] {new Point(0, 1), new Point(1, 0),
            new Point(1, 1), new Point(1, 2), new Point(2, 2)};
        assertArrayEquals(results, b.flip(points, 2, 5, "2"));
    }

    @Test
    public void flip4() {
        MutableBoard b = new MutableBoard();
        Point[] points = b.getPointForPiece("L", b.turn());
        Point[] results = new Point[] {new Point(0, 0), new Point(0, 1),
            new Point(0, 2), new Point(0, 3), new Point(1, 0)};
        assertArrayEquals(results, b.flip(points, 1, 5, "3"));
    }

    @Test
    public void flip5() {
        MutableBoard b = new MutableBoard();
        Point[] points = b.getPointForPiece("W", b.turn());
        Point[] results = new Point[] {new Point(2, 0), new Point(1, 0),
            new Point(1, 1), new Point(0, 1), new Point(0, 2)};
        assertArrayEquals(results, b.flip(points, 2, 5, "4"));
    }

    @Test
    public void flip6() {
        MutableBoard b = new MutableBoard();
        Point[] points = b.getPointForPiece("V", b.turn());
        Point[] results = new Point[] {new Point(2, 0), new Point(1, 0),
            new Point(0, 0), new Point(0, 1), new Point(0, 2)};
        assertArrayEquals(results, b.flip(points, 2, 5, "5"));
    }

    @Test
    public void flip7() {
        MutableBoard b = new MutableBoard();
        Point[] points = b.getPointForPiece("Y", b.turn());
        Point[] results = new Point[] {new Point(1, 0), new Point(1, 1),
            new Point(1, 2), new Point(1, 3), new Point(0, 2)};
        assertArrayEquals(results, b.flip(points, 1, 5, "6"));
    }

    @Test
    public void flip8() {
        MutableBoard b = new MutableBoard();
        Point[] points = b.getPointForPiece("P", b.turn());
        Point[] results = new Point[] {new Point(1, 0), new Point(1, 1),
            new Point(1, 2), new Point(0, 1), new Point(0, 2)};
        assertArrayEquals(results, b.flip(points, 1, 5, "7"));
    }


    /** hasMove. */

    @Test
    public void hasMove() {
        MutableBoard b = new MutableBoard();
        b.makeMove("W000");
        b.makeMove("Wb01");
        b.makeMove("F332");
        b.makeMove("F831");
        b.makeMove("N653");
        b.makeMove("2901");
        b.makeMove("Ya34");
        b.makeMove("X960");
        b.makeMove("Pb74");
        b.makeMove("1811");
        b.makeMove("Z884");
        b.makeMove("s620");
        b.makeMove("V504");
        assertTrue(b.hasMove(b.turn()));
    }

    /** PLAYER CLASS. */

    /** opponent(). */
    @Test
    public void opponent1() {
        TextUI ui = new TextUI();
        Human orangePlayer = new Human("player1", ui);
        Human violetPlayer = new Human("player2", ui);
        Game g = new Game(orangePlayer, violetPlayer, ui, 1L);
        orangePlayer.startGame(g, ORANGE);
        assertEquals(orangePlayer.opponent(ORANGE), VIOLET);
    }

    @Test
    public void opponent2() {
        TextUI ui = new TextUI();
        Human orangePlayer = new Human("player1", ui);
        Human violetPlayer = new Human("player2", ui);
        Game g = new Game(orangePlayer, violetPlayer, ui, 1L);
        orangePlayer.startGame(g, ORANGE);
        assertEquals(orangePlayer.opponent(VIOLET), ORANGE);
    }

    /** setPrevMove(Color c, String s), getPrevMove(Color c). */
    @Test
    public void getPrevMove1() {
        TextUI ui = new TextUI();
        Human orangePlayer = new Human("player1", ui);
        Human violetPlayer = new Human("player2", ui);
        Game g = new Game(orangePlayer, violetPlayer, ui, 1L);
        orangePlayer.startGame(g, ORANGE);
        orangePlayer.setPrevMove(ORANGE, "W000");
        assertEquals(orangePlayer.getPrevMove(ORANGE), "W000");
    }

    /** setPrevMove(Color c, String s). */
    @Test
    public void getPrevMove2() {
        TextUI ui = new TextUI();
        Human orangePlayer = new Human("player1", ui);
        Human violetPlayer = new Human("player2", ui);
        Game g = new Game(orangePlayer, violetPlayer, ui, 1L);
        orangePlayer.startGame(g, ORANGE);
        orangePlayer.setPrevMove(VIOLET, "F335");
        assertEquals(orangePlayer.getPrevMove(VIOLET), "F335");
    }
}
