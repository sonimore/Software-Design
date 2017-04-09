package carleton.sonimore.TTT3D;

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
    @org.junit.jupiter.api.Test
    void winningMoves() {
        TTT3DMover mov = new TTT3DMover();
        String boardString = "XOXO XO-- XO-- ----"
                + "X--- ---- ---- ----"
                + "X--- ---- ---- ----"
                + "---- ---- ---- ----";
        TTT3DBoard board = new TTT3DBoard(boardString,'X');
        List<TTT3DMove> newBoard = mov.winningMoves(board);
        //There should be two winning moves for X
        assertEquals(newBoard.size(),2);
    }

    @org.junit.jupiter.api.Test
    void winningMoves2mov() {
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "XOXO XO-- XO-- ----"
                + "---- ---- ---- ----"
                + "---- ---- ---- ----"
                + "---- ---- ---- ----";
        TTT3DBoard board = new TTT3DBoard(boardString,'O');
        List moves = new ArrayList();
        TTT3DMove move1 = new TTT3DMove(0, 3, 1, 'O');
        moves.add(0, move1);
        System.out.println("Level: " + move1.level + " Row: " + move1.row + " Column: " + move1.column);
        List<TTT3DMove> newBoard = mov.winningMoves(board);
        assertEquals(moves, newBoard);
    }


    @org.junit.jupiter.api.Test
    void winningMoves2MovesList() {
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "XOXO XO-- XO-- ----"
                + "-O-- ---- ---- ----"
                + "-O-- ---- ---- ----"
                + "---- ---- ---- ----";
        TTT3DBoard board = new TTT3DBoard(boardString, 'O');
        List moves = new ArrayList();
        TTT3DMove move1 = new TTT3DMove(0, 3, 1, 'O');
        TTT3DMove move2 = new TTT3DMove(3, 0, 1, 'O');
        moves.add(0, move1);
        moves.add(1, move2);
        //we will make it so that it outputs in newBoard in the same order as moves.
        List newBoard = mov.winningMoves(board);
        assertEquals(moves, newBoard);
    }

    @org.junit.jupiter.api.Test
    void winningMovesEmpty() {
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "XOXO OXOX XOXO OXOX"
                + "OXOX XOXO OXOX XOXO"
                + "XOXO OXOX XOXO OXOX"
                + "OXOX XOXO OXOX XOX-";
        TTT3DBoard board = new TTT3DBoard(boardString, 'O');
        List newBoard = mov.winningMoves(board);
        assertTrue(newBoard.isEmpty());
    }

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
    void winningMovesRow() {
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "X-O- -XO- --X- --O-"
                + "---- ---- ---- ----"
                + "---- ---- ---- ----"
                + "---- ---- ---- ----";

        TTT3DBoard board = new TTT3DBoard(boardString, 'O');
        List newBoard = mov.winningMoves(board);
        TTT3DMove move1 = new TTT3DMove(1,3,3,'O' );
        List moves = new ArrayList();
        moves.add(0, move1);
        assertEquals(moves, newBoard);
    }

    @org.junit.jupiter.api.Test
    void winningMovesDiagonal() {
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "XO-- ---- ---- ----"
                + "O--- -X-- ---- ----"
                + "---- ---- --X- ----"
                + "---- ---- ---- ----";

        TTT3DBoard board = new TTT3DBoard(boardString, 'O');
        List newBoard = mov.winningMoves(board);
        TTT3DMove move1 = new TTT3DMove(4,4,4,'O');
        List moves = new ArrayList();
        moves.add(0, move1);
        assertEquals(moves, newBoard);
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
        TTT3DBoard board = new TTT3DBoard(boardString,'X');
        List moves = new ArrayList();
        TTT3DMove move1 = new TTT3DMove(1, 4, 1, 'X');
        moves.add(0, move1);
        List<TTT3DMove> newBoard = mov.blockingMoves(board);
        assertEquals(moves, newBoard);
        //test that all the moves the current moves in them

    }
    @org.junit.jupiter.api.Test
    void blockingMoveslevel() {
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "XOXO ---- ---- ----"
                + "XO-- ---- ---- ----"
                + "XO-- ---- ---- ----"
                + "---- ---- ---- ----";
        TTT3DBoard board = new TTT3DBoard(boardString,'X');
        List moves = new ArrayList();
        TTT3DMove move1 = new TTT3DMove(4, 1, 2, 'X');
        moves.add(0, move1);
        List<TTT3DMove> newBoard = mov.blockingMoves(board);
        assertEquals(moves, newBoard);
    }

    @org.junit.jupiter.api.Test
    void blockingMovesrow() {
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "XOXO ---- ---- ----"
                + "XO-- ---- ---- ----"
                + "XO-- ---- ---- ----"
                + "---- ---- ---- ----";
        TTT3DBoard board = new TTT3DBoard(boardString,'X');
        List moves = new ArrayList();
        TTT3DMove move1 = new TTT3DMove(4, 2, 1, 'X');
        moves.add(0, move1);
        List<TTT3DMove> newBoard = mov.blockingMoves(board);
        assertEquals(moves, newBoard);
    }

    @org.junit.jupiter.api.Test
    void blockingMovescolum() {
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "OOO- ---- ---- ----"
                + "XX-- ---- ---- ----"
                + "X--- ---- ---- ----"
                + "---- ---- ---- ----";
        TTT3DBoard board = new TTT3DBoard(boardString,'X');
        List moves = new ArrayList();
        TTT3DMove move1 = new TTT3DMove(1, 1, 4, 'X');
        moves.add(0, move1);
        List<TTT3DMove> newBoard = mov.blockingMoves(board);
        assertEquals(moves, newBoard);
    }

    @org.junit.jupiter.api.Test
    void blockingMovesdiagonal() {
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "O--- ---- ---- ----"
                + "XX-- O--- ---- ----"
                + "X--- ---- O--- ----"
                + "---- ---- ---- ----";
        TTT3DBoard board = new TTT3DBoard(boardString,'X');
        List moves = new ArrayList();
        TTT3DMove move1 = new TTT3DMove(4, 4, 4, 'X');
        moves.add(0, move1);
        List<TTT3DMove> newBoard = mov.blockingMoves(board);
        assertEquals(moves, newBoard);
    }

    @org.junit.jupiter.api.Test
    void blockingMovesdiagonallev() {
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "O--- -O-- --O- ----"
                + "XX-- ---- ---- ----"
                + "X--- ---- ---- ----"
                + "---- ---- ---- ----";
        TTT3DBoard board = new TTT3DBoard(boardString,'X');
        List moves = new ArrayList();
        TTT3DMove move1 = new TTT3DMove(1, 4, 4, 'X');
        moves.add(0, move1);
        List<TTT3DMove> newBoard = mov.blockingMoves(board);
        assertEquals(moves, newBoard);
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
    void forcingMovesO() {
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "--XO XO-- XO-- ---- "
                + "XO-- ---- ---- ----"
                + "XO-- ---- ---- ----"
                + "---- ---- ---- ----";
        TTT3DBoard board = new TTT3DBoard(boardString,'0');
        List moves = new ArrayList();
        TTT3DMove move1 = new TTT3DMove(1, 2, 1, 'O');
        moves.add(0, move1);
        List<TTT3DMove> newBoard = mov.forcingMoves(board);
        assertEquals(moves, newBoard);

    }

    @org.junit.jupiter.api.Test
    void forcingMovesX() {
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "-OXO XO-- XO-- ----"
                + "XO-- ---- ---- ----"
                + "XO-- ---- ---- ----"
                + "---- ---- ---- ----";
        TTT3DBoard board = new TTT3DBoard(boardString,'X');
        List moves = new ArrayList();
        TTT3DMove move1 = new TTT3DMove(1, 1, 1, 'X');
        moves.add(0, move1);
        List<TTT3DMove> newBoard = mov.forcingMoves(board);
        assertEquals(moves, newBoard);
    }

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

    // Test code for best move taking into account the strategy
    // that player should use to get as close to winning in as few moves
    @org.junit.jupiter.api.Test
    void bestMove() {
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "XOXX XOOX XOXO O-X-"
                + "---- ---- ---- ----"
                + "---- ---- ---- ----"
                + "---- ---- ---- ----";

        TTT3DBoard newBoard = new TTT3DBoard(boardString, 'O');
        TTT3DMove move = new TTT3DMove(1, 4, 2, 'O');
        TTT3DMove best = mov.bestMove(newBoard);
        assertEquals(move, best);
    }

    @org.junit.jupiter.api.Test
    void bestMove2(){
        TTT3DMover mov = new TTT3DMover();
        String boardString =  "-O-- OX-- XO-- ----"
                + "--O- -X-- ---- ----"
                + "-X-- -O-- ---- ----"
                + "---- ---- -XX- ----";
        TTT3DBoard newBoard = new TTT3DBoard(boardString, 'O');
        TTT3DMove move = new TTT3DMove(3, 2,1,'O');
        TTT3DMove best = mov.bestMove(newBoard);
        assertEquals(move, best);
    }
}