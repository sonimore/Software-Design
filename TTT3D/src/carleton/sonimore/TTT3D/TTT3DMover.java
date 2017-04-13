
//package carleton.sonimore.TTT3D;


import java.util.ArrayList;
import java.util.List;

/**
 * TTT3DMover's job is to analyze a TTT3DBoard and make choices about what move
 * a player should make next. A TT3DMover object could be used as a key component
 * of the "AI" player in a full-blown 3D tic-tac-toe application.
 *
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
        List<TTT3DMove> moves = new ArrayList();
        return winningMoves(board, currentPlayer, moves);
    }

    private List<TTT3DMove> winningMoves(TTT3DBoard board, Character currentPlayer, List<TTT3DMove> moves) {

        winningMovesRow(board, currentPlayer, moves);
        winningMovesColumn(board, currentPlayer, moves);
        winningMovesRightDiagonal(board, currentPlayer, moves); // diagonal from top left to bottom right
        winningMovesLeftDiagonal(board, currentPlayer, moves); // diagonal from top right to bottom left
        winningMovesRowLevel(board, currentPlayer, moves); // rows are constant while columns and levels change
        winningMovesColumnLevel(board, currentPlayer, moves); // columns are constant while rows and levels change
        winningMovesVerticalLevel(board, currentPlayer, moves);
        winningMovesRightDiagonalLevel(board, currentPlayer, moves); // diagonal across levels from top left to bottom right
        winningMovesLeftDiagonalLevel(board, currentPlayer, moves);
        winningMovesLeftDiagonalLevel2(board, currentPlayer, moves);
        winningMovesLeftDiagonalLevel3(board, currentPlayer, moves);
        return moves;
    }

    /**
     * The first method is well commented in between the lines to give an idea of how this works.
     * @ param board the board showing the current state of the game
     * @ param currentPlayer the player who is going to make the next move
     * @ moves a list where the winning moves will be added
     *
     * finds the winning moves along a row in a single level
     */
    private void winningMovesRow(TTT3DBoard board, Character currentPlayer,
                                 List<TTT3DMove> moves) {

        TTT3DMove move = new TTT3DMove(0, 0, 0, currentPlayer); //initiates a move
        int numChars = 0; // initiates the number of currentPlayer's
        int numDash = 0;

        for (int l = 0; l < 4; l++) { // triple for-loop loops through every level, row, and column
            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 4; c++) {
                    if (board.valueInSquare(l, r, c).equals(currentPlayer)) { // if you come across currentPlayer, add 1 to numChars
                        numChars += 1;
                    } else if (board.valueInSquare(l, r, c).equals('-')) { // if you come across dash, add 1 to numDash
                        numDash += 1;
                        move = new TTT3DMove(l, r, c, currentPlayer); // create a temporary move at the dash; it will be added to the
                        // list if there are also 3 currentPlayer's in a row
                    } else {
                        numDash += 0; // otherwise don't do anything
                    }
                    if (numChars == 3 && numDash == 1) { // once you find 3 in a row and 1 dash, add the move at the dash
                        moves.add(move);
                    }
                }
                numDash = 0; // re-initiate numDash and numChars to 0 so that it can look for the next 3 in a row
                numChars = 0;
            }
        }
    }

    /**
     * @ param board the board showing the current state of the game
     * @ param currentPlayer the player who is going to make the next move
     * @ moves a list where the winning moves will be added
     *
     * finds the winning moves along a column across a single level
     */
    private void winningMovesColumn(TTT3DBoard board, Character currentPlayer, List<TTT3DMove> moves) {
        TTT3DMove move = new TTT3DMove(0, 0, 0, currentPlayer);
        int numChars = 0;
        int numDash = 0;
        for (int l = 0; l < 4; l++) {
            for (int c = 0; c < 4; c++) {
                for (int r = 0; r < 4; r++) {
                    if (board.valueInSquare(l, r, c).equals(currentPlayer)) {
                        numChars += 1;
                    } else if (board.valueInSquare(l, r, c).equals('-')) {
                        numDash += 1;
                        move = new TTT3DMove(l, r, c, currentPlayer);
                    } else {
                        numDash += 0;
                    }
                    if (numChars == 3 && numDash == 1) {
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
        TTT3DMove move = new TTT3DMove(0, 0, 0, currentPlayer);
        int numChars = 0;
        int numDash = 0;
        for (int l = 0; l < 4; l++) {
            for (int c = 0; c < 4; c++) {
                for (int r = c; r < c + 1; r++) {
                    if (board.valueInSquare(l, r, c).equals(currentPlayer)) {
                        numChars += 1;
                    } else if (board.valueInSquare(l, r, c).equals('-')) {
                        numDash += 1;
                        move = new TTT3DMove(l, r, c, currentPlayer);
                    } else {
                        numDash += 0;
                    }
                    if (numChars == 3 && numDash == 1) {
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
     * finds the winning moves along a diagonal in a single level from upper right
     * to lower left
     */
    private void winningMovesLeftDiagonal(TTT3DBoard board, Character currentPlayer, List<TTT3DMove> moves) {
        TTT3DMove move = new TTT3DMove(0, 0, 0, currentPlayer);
        int numChars = 0;
        int numDash = 0;
        for (int l = 0; l < 4; l++) {
            for (int r = 0; r < 4; r++) {
                for (int c = 3 - r; c < 4 - r; c++) {
                    if (board.valueInSquare(l, r, c).equals(currentPlayer)) {
                        numChars += 1;
                    } else if (board.valueInSquare(l, r, c).equals('-')) {
                        numDash += 1;
                        move = new TTT3DMove(l, r, c, currentPlayer);
                    } else {
                        numDash += 0;
                    }
                    if (numChars == 3 && numDash == 1) {
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
     * -x-- ---- ---- ----
     * --x- ---- ---- ----
     * ---* ---- ---- ----
     */
    private void winningMovesRowLevel(TTT3DBoard board, Character currentPlayer, List<TTT3DMove> moves) {
        TTT3DMove move = new TTT3DMove(0, 0, 0, currentPlayer);
        int numChars = 0;
        int numDash = 0;

        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                for (int l = c; l < c + 1; l++) {
                    if (board.valueInSquare(l, r, c).equals(currentPlayer)) {
                        numChars += 1;
                    } else if (board.valueInSquare(l, r, c).equals('-')) {
                        numDash += 1;
                        move = new TTT3DMove(l, r, c, currentPlayer);
                    } else {
                        numDash += 0;
                    }
                    if (numChars == 3 && numDash == 1) {
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
     * ---- x--- ---- ----
     * ---- ---- x--- ----
     * ---- ---- ---- *---
     */
    private void winningMovesColumnLevel(TTT3DBoard board, Character currentPlayer, List<TTT3DMove> moves) {
        TTT3DMove move = new TTT3DMove(0, 0, 0, currentPlayer);
        int numChars = 0;
        int numDash = 0;

        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 4; r++) {
                for (int l = r; l < r + 1; l++) {
                    if (board.valueInSquare(l, r, c).equals(currentPlayer)) {
                        numChars += 1;
                    } else if (board.valueInSquare(l, r, c).equals('-')) {
                        numDash += 1;
                        move = new TTT3DMove(l, r, c, currentPlayer);
                    } else {
                        numDash += 0;
                    }
                    if (numChars == 3 && numDash == 1) {
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
     * x--- ---- ---- ----
     * x--- ---- ---- ----
     * x--- ---- ---- ----
     */
    // row across levels
    private void winningMovesVerticalLevel(TTT3DBoard board, Character currentPlayer, List<TTT3DMove> moves) {
        TTT3DMove move = new TTT3DMove(0, 0, 0, currentPlayer);
        int numChars = 0;
        int numDash = 0;

        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                for (int l = 0; l < 4; l++) {
                    if (board.valueInSquare(l, r, c).equals(currentPlayer)) {
                        numChars += 1;
                    } else if (board.valueInSquare(l, r, c).equals('-')) {
                        numDash += 1;
                        move = new TTT3DMove(l, r, c, currentPlayer);
                    } else {
                        numDash += 0;
                    }
                    if (numChars == 3 && numDash == 1) {
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
    private void winningMovesRightDiagonalLevel(TTT3DBoard board, Character currentPlayer, List<TTT3DMove> moves) {
        TTT3DMove move = new TTT3DMove(0, 0, 0, currentPlayer);
        int numChars = 0;
        int numDash = 0;
        for (int c = 0; c < 4; c++) {
            for (int r = c; r < c + 1; r++) {
                for (int l = r; l < r + 1; l++) {
                    if (board.valueInSquare(l, r, c).equals(currentPlayer)) {
                        numChars += 1;
                    } else if (board.valueInSquare(l, r, c).equals('-')) {
                        numDash += 1;
                        move = new TTT3DMove(l, r, c, currentPlayer);
                    } else {
                        numDash += 0;
                    }
                    if (numChars == 4) {
                        moves.add(new TTT3DMove(-1, 0, 0, currentPlayer));
                        return;
                    }
                    if (numChars == 3 && numDash == 1) {
                        moves.add(move);
                    }
                }
            }
        }
    }

    /**
     * @ param board the board showing the current state of the game
     * @ param currentPlayer the player who is going to make the next move
     * @ moves a list where the winning moves will be added
     *
     * The following winningMovesLeftDiagonalLevel 1, 2, and 3
     * find the winning moves along a diagonal across the levels from upper right
     * to bottom left
     */
    private void winningMovesLeftDiagonalLevel(TTT3DBoard board, Character currentPlayer, List<TTT3DMove> moves) {
        TTT3DMove move = new TTT3DMove(0, 0, 0, currentPlayer);
        int numChars = 0;
        int numDash = 0;
        for (int c = 0; c < 4; c++) {
            for (int r = c; r < c + 1; r++) {
                for (int l = 3 - r; l < 4 - r; l++) {
                    if (board.valueInSquare(l, r, c).equals(currentPlayer)) {
                        numChars += 1;
                    } else if (board.valueInSquare(l, r, c).equals('-')) {
                        numDash += 1;
                        move = new TTT3DMove(l, r, c, currentPlayer);
                    } else {
                        numDash += 0;
                    }
                    if (numChars == 3 && numDash == 1) {
                        moves.add(move);
                    }
                }
            }
            numDash = 0;
            numChars = 0;
        }
    }

    private void winningMovesLeftDiagonalLevel2(TTT3DBoard board, Character currentPlayer, List<TTT3DMove> moves) {
        TTT3DMove move = new TTT3DMove(0, 0, 0, currentPlayer);
        int numChars = 0;
        int numDash = 0;
        for (int c = 0; c < 4; c++) {
            for (int r = 3 - c; r < 4 - c; r++) {
                for (int l = 3 - c; l < 4 - c; l++) {
                    if (board.valueInSquare(l, r, c).equals(currentPlayer)) {
                        numChars += 1;
                    } else if (board.valueInSquare(l, r, c).equals('-')) {
                        numDash += 1;

                        move = new TTT3DMove(l, r, c, currentPlayer);
                    } else {
                        numDash += 0;
                    }
                    if (numChars == 3 && numDash == 1) {
                        moves.add(move);
                    }
                }
            }
            numDash = 0;
            numChars = 0;
        }
    }

    private void winningMovesLeftDiagonalLevel3(TTT3DBoard board, Character currentPlayer, List<TTT3DMove> moves) {
        TTT3DMove move = new TTT3DMove(0, 0, 0, currentPlayer);
        int numChars = 0;
        int numDash = 0;
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                for (int l = 3 - c; l < 4 - c; l++) {
                    if (board.valueInSquare(l, r, c).equals(currentPlayer)) {
                        numChars += 1;
                    } else if (board.valueInSquare(l, r, c).equals('-')) {
                        numDash += 1;
                        move = new TTT3DMove(l, r, c, currentPlayer);
                    } else {
                        numDash += 0;
                    }
                    if (numChars == 3 && numDash == 1) {
                        moves.add(move);
                    }
                }
            }
            numDash = 0;
            numChars = 0;
        }
    }

    /**
     * This method uses all the methods of winning moves. It inverts the players and then checks
     * the winning moves of the opposite player.
     *
     * @param board a 3D tic-tac-toe board, including existing X and O positions
     *              as well as a marker for whose turn comes next
     * @return possibly list of TT3Dmoves that block a winning move of the next player.
     */
    public List<TTT3DMove> blockingMoves(TTT3DBoard board) {
        Character currentPlayer = board.getWhoseTurn();
        Character oppositePlayer;
        if (currentPlayer == 'X') {
            oppositePlayer = 'O';
        } else {
            oppositePlayer = 'X';
        }
        return winningMoves(board, oppositePlayer);
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
    private List<TTT3DMove> blockingMoves(TTT3DBoard board, Character currentPlayer) {
        List<TTT3DMove> moves = new ArrayList();
        return winningMoves(board, currentPlayer, moves);
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
        List<TTT3DMove> forcemoves = new ArrayList();
        for (int l = 0; l < 4; l++) {
            for (int c = 0; c < 4; c++) {
                for (int r = 0; r < 4; r++) {
                    if (board.valueInSquare(l, r, c).equals('-')) {
                        TTT3DBoard temp = new TTT3DBoard(board);
                        TTT3DMove move = new TTT3DMove(l, r, c, currentPlayer);
                        temp.makeMove(move);
                        temp.setWhoseTurn(currentPlayer);
                        List<TTT3DMove> listOfWins = winningMoves(temp);
                        if (listOfWins.size() >= 2) {
                            forcemoves.add(move);
                        }
                    }
                }
            }
        }
        return forcemoves;
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


    public static void main(String[] args) {
        TTT3DMover mov = new TTT3DMover();
        TTT3DBoard board;
        try {
            if (args[0].equals("win")) {
                board = new TTT3DBoard(args[1]);
                System.out.println(args[1]);
                List<TTT3DMove> wLst = mov.winningMoves(board);

                if (wLst.size() == 0) {
                    System.out.println();
                    System.out.println("There are no winning moves possible");
                    System.out.println();
                } else {
                    System.out.println("This is/are your winning move/s");
                    board.printNewBoard(board, wLst);
                }

            } else if (args[0].equals("block")) {
                board = new TTT3DBoard(args[1]);
                List<TTT3DMove> bLst = mov.blockingMoves(board);
                if (bLst.size() == 0) {
                    System.out.println();
                    System.out.println("There are no blocking moves possible");
                    System.out.println();
                } else {
                    System.out.println("This is/are your blocking move/s");
                    board.printNewBoard(board, bLst);
                }
            } else if (args[0].equals("force")) {
                board = new TTT3DBoard(args[1]);
                List<TTT3DMove> fLst = mov.forcingMoves(board);
                if (fLst.size() == 0) {
                    System.out.println();
                    System.out.println("There are no forcing moves possible");
                    System.out.println();
                } else {
                    System.out.println("This is/are your forcing move/s");
                    System.out.print(fLst.get(0).getCoord());
                    board.printNewBoard(board, fLst);
                }
            } else {
                System.out.println();
                System.out.println("Sorry that method doesn't exists");
                System.out.println();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println();
            System.out.println("Not enough information is given. Try: java block/win/force yourboardfile.txt");
            System.out.println();
        }
    }
}

