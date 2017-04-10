
package carleton.sonimore.TTT3D;


import java.util.ArrayList;
import java.util.List;

/**
 * TTT3DMover's job is to analyze a TTT3DBoard and make choices about what move
 * a player should make next. A TT3DMover object could be used as a key component
 * of the "AI" player in a full-blown 3D tic-tac-toe application.
 * <p>
 * In phase 3 of this assignment, you'll implement the methods stubbed and
 * documented below. In phase 2 (which comes before phase 3, as you might guess),
 * you'll create a JUnit TTT3DMoverTest class with a suitably rich collection of
 * unit tests for the public methods below.
 *
 * @author Jeff Ondich
 * @version 30 March 2017
 */
public class TTT3DMover {
    /**
     * Because we currently have no implementation of TTT3DMover, a default
     * constructor should suffice.
     */
    public TTT3DMover() {
    }

    /**
     * @param board a 3D tic-tac-toe board, including existing X and O positions
     *              as well as a marker for whose turn comes next
     * @return a (possibly empty) list of moves that the current player can take
     * to win the game in a single turn.
     */
    public List<TTT3DMove> winningMoves(TTT3DBoard board) {
        Character currentPlayer = board.getWhoseTurn();
        return winningMoves(board, currentPlayer);
    }

    private List<TTT3DMove> winningMoves(TTT3DBoard board, Character currentPlayer) {
        System.out.println("Current Player: " + currentPlayer);
        List<TTT3DMove> moves = new ArrayList();
        return winningMoves(board, currentPlayer, moves);
    }

    private List<TTT3DMove> winningMoves(TTT3DBoard board, Character currentPlayer, List<TTT3DMove> moves) {

//        winningMovesRow(board, currentPlayer, moves);
//        winningMovesColumn(board, currentPlayer, moves);
//        winningMovesRightDiagonal(board, currentPlayer, moves); // diagonal from top left to bottom right
//        winningMovesLeftDiagonal(board, currentPlayer, moves); // diagonal from top right to bottom left
//        winningMovesRowLevel(board, currentPlayer, moves); // rows are constant while columns and levels change
//        winningMovesColumnLevel(board, currentPlayer, moves); // columns are constant while rows and levels change
//        winningMovesVerticalLevel(board, currentPlayer, moves);
        winningMovesRightDiagonalLevel(board, currentPlayer, moves); // diagonal across levels from top left to bottom right
//        winningMovesLeftDiagonalLevel(board, currentPlayer, moves);
//        winningMovesLeftDiagonalLevel2(board, currentPlayer, moves);
//        winningMovesLeftDiagonalLevel3(board, currentPlayer, moves);
        return moves;
    }
    /**
     * @ param board the board showing the current state of the game
     * @ param currentPlayer the player who is going to make the next move
     * @ moves a list where the winning moves will be added
     *
     * finds the winning moves along a row in a single level
     */

    private void winningMovesRow(TTT3DBoard board, Character currentPlayer,
                                 List<TTT3DMove> moves) {

        TTT3DMove move = new TTT3DMove(0, 0, 0, currentPlayer);
        int numChars = 0;
        int numDash = 0;

        for (int l = 0; l < 4; l++) {
            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 4; c++) {
                    System.out.println("Level: " + l + " Row: " + r + " Column: " + c);
                    System.out.println("VAL: " + board.valueInSquare(l, r, c));
                    if (board.valueInSquare(l, r, c).equals(currentPlayer)) {
                        numChars += 1;
                        System.out.println("Number of 'O': " + numChars);
                    } else if (board.valueInSquare(l, r, c).equals('-')) {
                        numDash += 1;
                        move = new TTT3DMove(l, r, c, currentPlayer);
                    } else {
                        numDash += 0;
                    }
                    if (numChars == 3 && numDash == 1) {
                        System.out.println("Column: " + move.column + " Row: " + move.row);
                        moves.add(move);
                    }
                }
                numDash = 0;
                numChars = 0;
            }
        }
    }

    /**
     * @ param board the board showing the current state of the game
     * @ param currentPlayer the player who is going to make the next move
     * @ moves a list where the winning moves will be added
     *
     * finds the winning moves along a column across a single levels
     */
    private void winningMovesColumn(TTT3DBoard board, Character currentPlayer, List<TTT3DMove> moves) {
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        TTT3DMove move = new TTT3DMove(0, 0, 0, currentPlayer);
        int numChars = 0;
        int numDash = 0;
        for (int l = 0; l < 4; l++) {
            for (int c = 0; c < 4; c++) {
                for (int r = 0; r < 4; r++) {
                    System.out.println("Level: " + l + " Row: " + r + " Column: " + c);
                    System.out.println("Val in square: " + board.valueInSquare(l, r, c));
                    if (board.valueInSquare(l, r, c).equals(currentPlayer)) {
                        numChars += 1;
                        System.out.println("Number of 'O': " + numChars);
                    } else if (board.valueInSquare(l, r, c).equals('-')) {
                        numDash += 1;
                        move = new TTT3DMove(l, r, c, currentPlayer);
                    } else {
                        numDash += 0;
                    }
                    if (numChars == 3 && numDash == 1) {
                        System.out.println("Column: " + move.column + " Row: " + move.row);
                        moves.add(move);
                    }
                }
                numDash = 0;
                numChars = 0;
            }
        }
    }

    /**
     * @ param board the board showing the current state of the game
     * @ param currentPlayer the player who is going to make the next move
     * @ moves a list where the winning moves will be added
     *
     * finds the winning moves along a diagonal in a single level from upper left
     * to lower right
     */
    private void winningMovesRightDiagonal(TTT3DBoard board, Character currentPlayer, List<TTT3DMove> moves) {
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        TTT3DMove move = new TTT3DMove(0, 0, 0, currentPlayer);
        int numChars = 0;
        int numDash = 0;
        for (int l = 0; l < 4; l++) {
            for (int c = 0; c < 4; c++) {
                for (int r = c; r < c + 1; r++) {
                    System.out.println("Level " + l + " Row: " + r + " Column: " + c);
                    System.out.println("Val in square: " + board.valueInSquare(l, r, c));
                    if (board.valueInSquare(l, r, c).equals(currentPlayer)) {
                        numChars += 1;
                        System.out.println("Number of 'O': " + numChars);
                    } else if (board.valueInSquare(l, r, c).equals('-')) {
                        numDash += 1;
                        move = new TTT3DMove(l, r, c, currentPlayer);
                    } else {
                        numDash += 0;
                    }
                    if (numChars == 3 && numDash == 1) {
                        System.out.println("Column: " + move.column + " Row: " + move.row + "level: ***" + move.level);
                        moves.add(move);
                    }
                }
            }
            numDash = 0;
            numChars = 0;
        }
    }

    private void winningMovesLeftDiagonal(TTT3DBoard board, Character currentPlayer, List<TTT3DMove> moves) {
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        TTT3DMove move = new TTT3DMove(0, 0, 0, currentPlayer);
        int numChars = 0;
        int numDash = 0;
        for (int l = 0; l < 4; l++) {
            for (int r = 0; r < 4; r++) {
                for (int c = 3-r; c < 4-r; c++) {
                    System.out.println("CUR Player: " + currentPlayer +  " Level: " + l + " Row: " + r + " Column: " + c);
                    System.out.println("Value in square " + board.valueInSquare(l, r, c));
                    if (board.valueInSquare(l, r, c).equals(currentPlayer)) {
                        System.out.println ("TRUE");
                        numChars += 1;
                        System.out.println("Number of 'O': " + numChars);
                    } else if (board.valueInSquare(l, r, c).equals('-')) {
                        numDash += 1;
                        move = new TTT3DMove(l, r, c, currentPlayer);
                    } else {
                        numDash += 0;
                    }
                    if (numChars == 3 && numDash == 1) {
                        System.out.println("Level " + move.level + " Column: " + move.column + " Row: " + move.row + "level: ***");
                        moves.add(move);
                    }
                }
            }
            numDash = 0;
            numChars = 0;
        }
    }

    /**
     * @ param board the board showing the current state of the game
     * @ param currentPlayer the player who is going to make the next move
     * @ moves a list where the winning moves will be added
     *
     * finds the winning moves along a row across multiple levels
     * example: x--- ---- ---- ----
     *          -x-- ---- ---- ----
     *          --x- ---- ---- ----
     *          ---* ---- ---- ----
     */
    private void winningMovesRowLevel(TTT3DBoard board, Character currentPlayer, List<TTT3DMove> moves) {
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        TTT3DMove move = new TTT3DMove(0, 0, 0, currentPlayer);
        int numChars = 0;
        int numDash = 0;

        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                for (int l = c; l < c + 1; l++) {
                    System.out.println("Level: " + l + " Row: " + r + " Column: " + c);
                    if (board.valueInSquare(l, r, c).equals(currentPlayer)) {
                        numChars += 1;
                        System.out.println("Number of 'O': " + numChars);
                    } else if (board.valueInSquare(l, r, c).equals('-')) {
                        numDash += 1;
                        move = new TTT3DMove(l, r, c, currentPlayer);
                    } else {
                        numDash += 0;
                    }
                    if (numChars == 3 && numDash == 1) {
                        System.out.println("Column: " + move.column + " Row: " + move.row);
                        moves.add(move);
                    }
                }

            }
            numDash = 0;
            numChars = 0;
        }
    }
    /**
     * @ param board the board showing the current state of the game
     * @ param currentPlayer the player who is going to make the next move
     * @ moves a list where the winning moves will be added
     *
     * finds the winning moves along a row across multiple levels
     * example: x--- ---- ---- ----
     *          ---- x--- ---- ----
     *          ---- ---- x--- ----
     *          ---- ---- ---- *---
     */
    private void winningMovesColumnLevel(TTT3DBoard board, Character currentPlayer, List<TTT3DMove> moves) {
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        TTT3DMove move = new TTT3DMove(0, 0, 0, currentPlayer);
        int numChars = 0;
        int numDash = 0;

        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 4; r++) {
                for (int l = r; l < r + 1; l++) {
                    System.out.println("Level: " + l + " Row: " + r + " Column: " + c);
                    if (board.valueInSquare(l, r, c).equals(currentPlayer)) {
                        numChars += 1;
                        System.out.println("Number of 'O': " + numChars);
                    } else if (board.valueInSquare(l, r, c).equals('-')) {
                        numDash += 1;
                        move = new TTT3DMove(l, r, c, currentPlayer);
                    } else {
                        numDash += 0;
                    }
                    if (numChars == 3 && numDash == 1) {
                        System.out.println("Column: " + move.column + " Row: " + move.row);
                        moves.add(move);
                    }
                }
            }
            numDash = 0;
            numChars = 0;
        }
    }

    /**
     * @ param board the board showing the current state of the game
     * @ param currentPlayer the player who is going to make the next move
     * @ moves a list where the winning moves will be added
     *
     * finds the winning moves along a vertical path across multiple levels
     * example: x--- ---- ---- ----
     *          x--- ---- ---- ----
     *          x--- ---- ---- ----
     *          x--- ---- ---- ----
     */
    // row across levels
    private void winningMovesVerticalLevel(TTT3DBoard board, Character currentPlayer, List<TTT3DMove> moves) {
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        TTT3DMove move = new TTT3DMove(0, 0, 0, currentPlayer);
        int numChars = 0;
        int numDash = 0;

        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                for (int l = 0; l < 4; l++) {
                    System.out.println("Row: " + r + " Column: " + c);
                    if (board.valueInSquare(l, r, c).equals(currentPlayer)) {
                        numChars += 1;
                        System.out.println("Number of 'O': " + numChars);
                    } else if (board.valueInSquare(l, r, c).equals('-')) {
                        numDash += 1;
                        move = new TTT3DMove(l, r, c, currentPlayer);
                    } else {
                        numDash += 0;
                    }
                    if (numChars == 3 && numDash == 1) {
                        System.out.println("Column: " + move.column + " Row: " + move.row);
                        moves.add(move);
                    }
                }
                numDash = 0;
                numChars = 0;
            }
        }
    }
    /**
     * @ param board the board showing the current state of the game
     * @ param currentPlayer the player who is going to make the next move
     * @ moves a list where the winning moves will be added
     *
     * finds the winning moves along a diagonal across the levels from upper left
     * to lower right
     */
    private void winningMovesRightDiagonalLevel (TTT3DBoard board, Character currentPlayer, List<TTT3DMove> moves) {
        System.out.println("HERE-----------------------------------------------------------------------------------------------------------");
        TTT3DMove move = new TTT3DMove(0, 0, 0, currentPlayer);
        int numChars = 0;
        int numDash = 0;
        for (int c = 0; c < 4; c++) {
            for (int r = c; r < c + 1; r++) {
                for (int l = r; l < r + 1; l++) {
                    System.out.println("Level " + l + " Row: " + r + " Column: " + c);
                    System.out.println("Val in square: " + board.valueInSquare(l, r, c));
                    if (board.valueInSquare(l, r, c).equals(currentPlayer)) {
                        numChars += 1;
                        System.out.println("Number of 'O': " + numChars);
                    } else if (board.valueInSquare(l, r, c).equals('-')) {
                        numDash += 1;
                        move = new TTT3DMove(l, r, c, currentPlayer);
                    } else {
                        numDash += 0;
                    }
                    if (numChars == 3 && numDash == 1) {
                        System.out.println("Column: " + move.column + " Row: " + move.row + "level: ***" + move.level);
                        moves.add(move);
                    }
                }
            }

        }

    }

    private void winningMovesLeftDiagonalLevel (TTT3DBoard board, Character currentPlayer, List<TTT3DMove> moves) {
        System.out.println("HERE-----------------------------------------------------------------------------------------------------------");
        TTT3DMove move = new TTT3DMove(0, 0, 0, currentPlayer);
        int numChars = 0;
        int numDash = 0;
        for (int c = 0; c < 4; c++) {
            for (int r = c; r < c + 1; r++) {
                for (int l = 3 - r; l < 4 - r; l++) {
                    System.out.println("Level " + l + " Row: " + r + " Column: " + c);
                    System.out.println("Val in square: " + board.valueInSquare(l, r, c));
                    if (board.valueInSquare(l, r, c).equals(currentPlayer)) {
                        numChars += 1;
                        System.out.println("Number of 'O': " + numChars);
                    } else if (board.valueInSquare(l, r, c).equals('-')) {
                        numDash += 1;
                        move = new TTT3DMove(l, r, c, currentPlayer);
                    } else {
                        numDash += 0;
                    }
                    if (numChars == 3 && numDash == 1) {
                        System.out.println("Column: " + move.column + " Row: " + move.row + "level: ***" + move.level);
                        moves.add(move);
                    }
                }
            }
            numDash = 0;
            numChars = 0;
        }
    }

    private void winningMovesLeftDiagonalLevel2 (TTT3DBoard board, Character currentPlayer, List<TTT3DMove> moves) {
        System.out.println("HERE-----------------------------------------------------------------------------------------------------------");
        TTT3DMove move = new TTT3DMove(0, 0, 0, currentPlayer);
        int numChars = 0;
        int numDash = 0;
        for (int c = 0; c < 4; c++) {
            for (int r = 3 - c; r < 4 - c; r++) {
                for (int l = 3 - c; l < 4 - c; l++) {
                    System.out.println("Level " + l + " Row: " + r + " Column: " + c);
                    System.out.println("Val in square: " + board.valueInSquare(l, r, c));
                    if (board.valueInSquare(l, r, c).equals(currentPlayer)) {
                        numChars += 1;
                        System.out.println("Number of 'O': " + numChars);
                    } else if (board.valueInSquare(l, r, c).equals('-')) {
                        numDash += 1;

                        move = new TTT3DMove(l, r, c, currentPlayer);
                    } else {
                        numDash += 0;
                    }
                    if (numChars == 3 && numDash == 1) {
                        System.out.println("Column: " + move.column + " Row: " + move.row + "level: ***" + move.level);
                        moves.add(move);
                    }
                }
            }
            numDash = 0;
            numChars = 0;
        }

    }

    private void winningMovesLeftDiagonalLevel3 (TTT3DBoard board, Character currentPlayer, List<TTT3DMove> moves) {
        System.out.println("HERE-----------------------------------------------------------------------------------------------------------");
        TTT3DMove move = new TTT3DMove(0, 0, 0, currentPlayer);
        int numChars = 0;
        int numDash = 0;
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                for (int l = 3 - c; l < 4 - c; l++) {
                    System.out.println("Level " + l + " Row: " + r + " Column: " + c);
                    System.out.println("Val in square: " + board.valueInSquare(l, r, c));
                    if (board.valueInSquare(l, r, c).equals(currentPlayer)) {
                        numChars += 1;
                        System.out.println("Number of 'O': " + numChars);
                    } else if (board.valueInSquare(l, r, c).equals('-')) {
                        numDash += 1;
                        System.out.print("DASH");
                        move = new TTT3DMove(l, r, c, currentPlayer);
                        System.out.println("Column: " + move.column + " Row: " + move.row + "level: ***" + move.level);
                    } else {
                        numDash += 0;
                    }
                    if (numChars == 3 && numDash == 1) {
                        System.out.println("Column: " + move.column + " Row: " + move.row + "level: ***" + move.level);
                        moves.add(move);
                    }
                }
            }
            numDash = 0;
            numChars = 0;
        }

    }



    /**
     * @param board a 3D tic-tac-toe board, including existing X and O positions
     *              as well as a marker for whose turn comes next
     * @return a (possibly empty) list of moves that the non-current player could take
     * to win the game in a single turn. That is, these are positions where the current
     * player should play to avoid losing on the opponent's next turn.
     */
    public List<TTT3DMove> blockingMoves(TTT3DBoard board) {
        return new ArrayList<TTT3DMove>();
    }

    /**
     * @param board a 3D tic-tac-toe board, including existing X and O positions
     *              as well as a marker for whose turn comes next
     * @return a (possibly empty) list of moves that the current player could take
     * to force a win. A move is "forcing" if it results in at least two different
     * ways for the current player to win on the next move. In other words, after a
     * forcing move, the opponent will be forced to make two different blocking moves
     * in a single turn to avoid losing.
     */
    public List<TTT3DMove> forcingMoves(TTT3DBoard board) {
        Character currentPlayer = board.getWhoseTurn();
        return forcingMoves(board, currentPlayer);
    }
    public List<TTT3DMove> forcingMoves(TTT3DBoard board, Character currentPlayer) {
        List moves = new ArrayList();
        int numChars = 0;
        int numDash = 0;
        int l;
        int c;
        int r;
        for (l = 0; l < 3; l++) {
            for (c = 0; c < 3; c++) {
                for (r = 0; r < 3; r++) {
                    if (board.valueInSquare(l, r, c).equals('-')) {
                        TTT3DMove move = new TTT3DMove(l, r, c, currentPlayer);
                        board.makeMove(move);
                        List<TTT3DMove> listOfWins = winningMoves(board);
                        if (listOfWins.size() >= 2) {
                            moves.add(move);
                            //undo move
                            TTT3DMove undoMove = new TTT3DMove(l, r, c, '-');
                            // board.makeMove(undoMove);
                        }
                    }
                }
            }
        }
        return moves;
    }

    /**
     * @param board a 3D tic-tac-toe board, including existing X and O positions
     *              as well as a marker for whose turn comes next
     * @return the move that this object determines would be the best choice for the
     * board's current player.
     */
    public TTT3DMove bestMove(TTT3DBoard board) {
        return new TTT3DMove(0, 0, 0, board.getWhoseTurn());
    }
}
