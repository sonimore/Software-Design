//package carleton.sonimore.TTT3D;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/**
 * Created by Nyla Worker and Sonia Moreno.
 */
class TTT3DMoverTest {
    private  TTT3DBoard board;
    private TTT3DMover mover;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        mover = new TTT3DMover();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        board = null;
    }

    /**
     * Here the tests of winning moves begin. It tests different boards and the winning moves
     * it can take. It checks whether the lengths of the list are equal or if they have the same content.
     * Also it checks whether the player is correct as well as whether rows, culumns and levels are working propperly.
     */
    /**
     * Checks that there are two winning moves.
     */
    @org.junit.jupiter.api.Test
    void winningMovesRowandLevel() {
        TTT3DMover mov = new TTT3DMover();
        String boardString = "XOXO XO-- XO-- ----"
                + "X--- ---- ---- ----"
                + "X--- ---- ---- ----"
                + "---- ---- ---- ----";
        TTT3DBoard board = new TTT3DBoard(boardString,'X');
        List<TTT3DMove> winningBoard = mov.winningMoves(board);
        assertEquals(winningBoard.size(),2);
    }

    /**
     * Checks rows
     */
    @org.junit.jupiter.api.Test
    void winningMovesRow() {
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "XOXO XO-- XO-- ----"
                + "---- ---- ---- ----"
                + "---- ---- ---- ----"
                + "---- ---- ---- ----";
        TTT3DBoard board = new TTT3DBoard(boardString,'O');
        List<TTT3DMove> moves = new ArrayList();
        TTT3DMove move1 = new TTT3DMove(0, 3, 1, 'O');
        moves.add(move1);
        List<TTT3DMove> newBoard = mov.winningMoves(board);
        assertEquals(moves.get(0).getCoord(), newBoard.get(0).getCoord());
    }
    /**
     * Checks columns
     */

    @org.junit.jupiter.api.Test
    void winningMovesLevel() {
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "X--O ---- -O-- ----"
                + "X--- ---- ---- ----"
                + "X--- ---- ---- ----"
                + "---- ---- ---- ----";
        TTT3DBoard board = new TTT3DBoard(boardString, 'X');
        List<TTT3DMove> moves = new ArrayList();
        TTT3DMove move1 = new TTT3DMove(3, 0, 0, 'X');
        moves.add(move1);
        //we will make it so that it outputs in newBoard in the same order as moves.
        List<TTT3DMove> newBoard = mov.winningMoves(board);
        assertEquals(moves.get(0).getCoord(), newBoard.get(0).getCoord());
    }
    /**
     * That a full board returns nothing
     */
    @org.junit.jupiter.api.Test
    void winningMovesEmpty() {
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "XOXO OXOX XOXO OXOX"
                + "OXOX XOXO OXOX XOXO"
                + "XOXO OXOX XOXO OXOX"
                + "OXOX XOXO OXOX XOXX";
        TTT3DBoard board = new TTT3DBoard(boardString, 'X');
        List<TTT3DMove> newBoard = mov.winningMoves(board);
        assertEquals(newBoard.size(), 0);
    }
    /**
     * th
     */
    @org.junit.jupiter.api.Test
    void winningMovesEdgeCase() {
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "---- ---- ---- ----"
                + "---- ---- ---- ----"
                + "---- ---- ---- ----"
                + "---- ---- ---- ----";

        TTT3DBoard board = new TTT3DBoard(boardString, 'O');
        List newBoard = mov.winningMoves(board);
        assertTrue(newBoard.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void winningMovesDiagonalRow() {
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "X-O- -XO- X-X- --O-"
                + "---- ---- ---- ----"
                + "---- ---- ---- ----"
                + "---- ---- ---- ----";

        TTT3DBoard board = new TTT3DBoard(boardString, 'X');
        List<TTT3DMove> newBoard = mov.winningMoves(board);
        TTT3DMove move1 = new TTT3DMove(0,3,3,'X' );
        List<TTT3DMove> moves = new ArrayList();
        moves.add(0, move1);
        assertEquals(moves.get(0).getCoord(), newBoard.get(0).getCoord());
    }

    @org.junit.jupiter.api.Test
    void winningMovesSuperDiagonal() {
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "XO-- ---- ---- ----"
                + "O--- -X-- ---- ----"
                + "---- ---- --X- ----"
                + "---- ---- ---- ----";

        TTT3DBoard board = new TTT3DBoard(boardString, 'X');
        List<TTT3DMove> newBoard = mov.winningMoves(board);
        TTT3DMove move1 = new TTT3DMove(3,3,3,'X');
        List<TTT3DMove> moves = new ArrayList();
        moves.add(0, move1);
        assertEquals(moves.get(0).getCoord(), newBoard.get(0).getCoord());
    }
    @org.junit.jupiter.api.Test
    void winningMovescolum() {
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "OOO- ---- ---- ----"
                + "XX-- ---- ---- ----"
                + "X--- ---- ---- ----"
                + "---- ---- ---- ----";
        TTT3DBoard board = new TTT3DBoard(boardString,'O');
        List<TTT3DMove> moves = new ArrayList();
        TTT3DMove move1 = new TTT3DMove(0, 0, 3, 'X');
        moves.add(0, move1);
        List<TTT3DMove> newBoard = mov.winningMoves(board);
        assertEquals(moves.get(0).getCoord(), newBoard.get(0).getCoord());
    }

    /**
     * This tests that both items outputed by the winning moves are the same as the expected.
     */
    @org.junit.jupiter.api.Test
    void winningMoves2colums() {
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "OOO- ---- ---- ----"
                + "OOO- ---- ---- ----"
                + "X--- ---- ---- ----"
                + "---- ---- ---- ----";
        TTT3DBoard board = new TTT3DBoard(boardString,'O');
        List<TTT3DMove> moves = new ArrayList();
        TTT3DMove move1 = new TTT3DMove(1, 0, 3, 'O');
        TTT3DMove move2 = new TTT3DMove(0,0,3,'O');
        moves.add(0, move1);
        moves.add(1,move2);
        List<TTT3DMove> newBoard = mov.winningMoves(board);
        assertEquals(moves.get(1).getCoord(), newBoard.get(0).getCoord());
        assertEquals(moves.get(0).getCoord(), newBoard.get(1).getCoord());

        //assertThat(newBoard, CoreMatchers.hasItems(1,2,3,4,5));
    }


    /**
     * Here the tests of blocking moves begin. It tests different boards and the blocking moves
     * it can take. It checks whether the lenghts of the lists are equal or if they have the same content.
     *Also it checks whether the player is correct as well as whether row, culums and level are working propperly.
     */

    @org.junit.jupiter.api.Test
    void blockingMovesRow() {
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "XOXO XO-- XO-- ----"
                + "---- ---- ---- ----"
                + "---- ---- ---- ----"
                + "---- ---- ---- ----";
        TTT3DBoard board = new TTT3DBoard(boardString,'O');
        List<TTT3DMove> moves = new ArrayList();
        TTT3DMove move1 = new TTT3DMove(0, 3, 0, 'X');
        moves.add(0, move1);
        List<TTT3DMove> newBoard = mov.blockingMoves(board);
        assertEquals(moves.get(0).getCoord(), newBoard.get(0).getCoord());
        //test that all the moves the current moves in them

    }
    @org.junit.jupiter.api.Test
    void blockingMoveslevel() {
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "XOXO ---- ---- ----"
                + "XO-- ---- ---- ----"
                + "XO-- ---- ---- ----"
                + "---- ---- ---- ----";
        TTT3DBoard board = new TTT3DBoard(boardString,'O');
        List<TTT3DMove> moves = new ArrayList();
        TTT3DMove move1 = new TTT3DMove(3, 0, 0, 'X');
        moves.add(0, move1);
        List<TTT3DMove> newBoard = mov.blockingMoves(board);
        assertEquals(moves.get(0).getCoord(), newBoard.get(0).getCoord());
    }

    @org.junit.jupiter.api.Test
    void blockingMoves2Moves() {
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "XXX- ---- ---- ----"
                + "XO-- ---- ---- ----"
                + "XO-- ---- ---- ----"
                + "---- ---- ---- ----";
        TTT3DBoard board = new TTT3DBoard(boardString,'O');
        List<TTT3DMove> moves = new ArrayList();
        TTT3DMove move1 = new TTT3DMove(3, 0, 0, 'X');
        TTT3DMove move2 = new TTT3DMove(0, 0, 3, 'X');
        moves.add(move1);
        moves.add(move2);
        List<TTT3DMove> newBoard = mov.blockingMoves(board);
        assertEquals(moves.get(0).getCoord(), newBoard.get(1).getCoord());
        assertEquals(moves.get(1).getCoord(), newBoard.get(0).getCoord());
    }

    @org.junit.jupiter.api.Test
    void blockingMovesColum() {
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "OOO- ---- ---- ----"
                + "XX-- ---- ---- ----"
                + "X--- ---- ---- ----"
                + "---- ---- ---- ----";
        TTT3DBoard board = new TTT3DBoard(boardString,'X');
        List<TTT3DMove> moves = new ArrayList();
        TTT3DMove move1 = new TTT3DMove(0, 0, 3, 'X');
        moves.add(0, move1);
        List<TTT3DMove> newBoard = mov.blockingMoves(board);
        assertEquals(moves.get(0).getCoord(), newBoard.get(0).getCoord());
    }

    @org.junit.jupiter.api.Test
    void blockingMovesdiagonal() {
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "O--- ---- ---- ----"
                + "XX-- O--- ---- ----"
                + "X--- ---- O--- ----"
                + "---- ---- ---- ----";
        TTT3DBoard board = new TTT3DBoard(boardString,'X');
        List<TTT3DMove> moves = new ArrayList();
        TTT3DMove move1 = new TTT3DMove(3, 3, 0, 'X');
        moves.add(move1);
        List<TTT3DMove> newBoard = mov.blockingMoves(board);

        assertEquals(moves.get(0).getCoord(), newBoard.get(0).getCoord());
    }

    @org.junit.jupiter.api.Test
    void blockingMovesdiagonallev() {
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "O--- -O-- --O- ----"
                + "XX-- ---- ---- ----"
                + "X--- ---- ---- ----"
                + "---- ---- ---- ----";
        TTT3DBoard board = new TTT3DBoard(boardString,'X');
        List<TTT3DMove> moves = new ArrayList();
        TTT3DMove move1 = new TTT3DMove(0, 3, 3, 'X');
        moves.add(0, move1);
        List<TTT3DMove> newBoard = mov.blockingMoves(board);
        assertEquals(moves.get(0).getCoord(), newBoard.get(0).getCoord());
    }

    void blockingMovesEmpty() {
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "---- ---- ---- ----"
                + "---- ---- ---- ----"
                + "---- ---- ---- ----"
                + "---- ---- ---- ----";
        TTT3DBoard board = new TTT3DBoard(boardString,'X');
        List<TTT3DMove> newBoard = mov.blockingMoves(board);
        assertTrue(newBoard.isEmpty());
    }

    /**
     * Here the tests of forcing moves begin. It tests different boards and the forcing winning moves
     * it can take. It checks whether the lenghts of the list are equal or if they have the same content.
     * Also it checks whether the player is correct as well as whether row, culums and level are working propperly.
     *
     */

    @org.junit.jupiter.api.Test
    void forcingMoves0() {
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "--XO XO-- XO-- ---- "
                + "XO-- ---- ---- ----"
                + "XO-- ---- ---- ----"
                + "---- ---- ---- ----";
        TTT3DBoard board = new TTT3DBoard(boardString,'X');
        List<TTT3DMove> moves = new ArrayList();
        TTT3DMove move1 = new TTT3DMove(0, 0, 0, 'X');
        moves.add(move1);
        List<TTT3DMove> newBoard = mov.forcingMoves(board);
        assertEquals(moves.get(0).getCoord(), newBoard.get(0).getCoord());

    }

    @org.junit.jupiter.api.Test
    void forcingMoves1() {
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "-XX- -O-- -O-- ----"
                + "XO-- ---- ---- ----"
                + "XO-- ---- ---- ----"
                + "---- ---- ---- ----";
        TTT3DBoard board = new TTT3DBoard(boardString,'X');
        List<TTT3DMove> moves = new ArrayList();
        TTT3DMove move1 = new TTT3DMove(0, 0, 0, 'X');
        moves.add(move1);
        List<TTT3DMove> newBoard = mov.forcingMoves(board);
        assertEquals(moves.get(0).getCoord(), newBoard.get(0).getCoord());
    }
    @org.junit.jupiter.api.Test
    void forcingMovesEmpty() {
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "---- ---- ---- ----"
                + "---- ---- ---- ----"
                + "---- ---- ---- ----"
                + "---- ---- ---- ----";
        TTT3DBoard board = new TTT3DBoard(boardString,'X');
        List<TTT3DMove> newBoard = mov.forcingMoves(board);
        assertTrue(newBoard.isEmpty());
    }

}