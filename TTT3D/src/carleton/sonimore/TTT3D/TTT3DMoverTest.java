package carleton.sonimore.TTT3D;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Sonia on 3/30/2017.
 */
class TTT3DMoverTest {
    @org.junit.jupiter.api.BeforeEach
    void setUp() {

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {

    }

    @org.junit.jupiter.api.Test
    void winningMovesColumn() {
        TTT3DMover mov = new TTT3DMover();
        String boardString = "XOXO XO-- XO-- ----"
                + "-O-- ---- ---- ----"
                + "-O-- ---- ---- ----"
                + "---- ---- ---- ----";
        TTT3DBoard board = new TTT3DBoard(boardString, 'O');
        List moves = new ArrayList();
        TTT3DMove move1 = new TTT3DMove(1, 4, 2, 'O');
        TTT3DMove move2 = new TTT3DMove(4, 1, 2, 'O');
        moves.add(0, move1);
        moves.add(1, move2);
        List newBoard = mov.winningMoves(board);
        assertEquals(moves, newBoard);
    }

    @org.junit.jupiter.api.Test
    void winningMovesEmpty() {
        TTT3DMover mov = new TTT3DMover();
        String boardString = "XOXO OXOX XOXO OXOX"
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
        String boardString = "---- ---- ---- ----"
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
        String boardString = "--O- -XO- X--- --OX"
                + "---- ---- ---- ----"
                + "---- ---- ---- ----"
                + "---- ---- ---- ----";

        TTT3DBoard board = new TTT3DBoard(boardString, 'O');
        List newBoard = mov.winningMoves(board);
        TTT3DMove move1 = new TTT3DMove(1,3,3);
        List moves = new ArrayList();
        moves.add(0, move1);
        assertEquals(moves, newBoard);
    }

    @org.junit.jupiter.api.Test
    void winningMovesDiagonal() {
        TTT3DMover mov = new TTT3DMover();
        String boardString = "XO-- ---- ---- ----"
                + "O--- -X-- ---- ----"
                + "---- ---- --X- ----"
                + "---- ---- ---- ----";

        TTT3DBoard board = new TTT3DBoard(boardString, 'O');
        List newBoard = mov.winningMoves(board);
        TTT3DMove move1 = new TTT3DMove(4,4,4,'O');
        List moves(0, move1);
        assertEquals(moves, newBoard);
    }

    @org.junit.jupiter.api.Test
    void blockingMoves() {
        TTT3DMover mov = new TTT3DMover();
        String boardString = "XOXX XOOX XOXO ----"
                + "---- ---- ---- ----"
                + "---- ---- ---- ----"
                + "---- ---- ---- ----";

        TTT3DBoard board = new TTT3DBoard(boardString, 'O');
        List newBoard = mov.winningMoves(board);
        TTT3DMove move1 = new TTT3DMove(1, 4,1,'O');
        List moves = new ArrayList();
        moves.add(move1);
        assertEquals(moves, newBoard);
    }

    @org.junit.jupiter.api.Test
    void forcingMoves() {

    }

    @org.junit.jupiter.api.Test
    void bestMove() {
        TTT3DMover mov = new TTT3DMover();
        String boardString = "XOXX XOOX XOXO O-X-"
                + "---- ---- ---- ----"
                + "---- ---- ---- ----"
                + "---- ---- ---- ----";

        TTT3DBoard newBoard = new TTT3DBoard(boardString, 'O');
        TTT3DMove move = new TTT3DMove(1, 4, 2, 'O');
        TTT3DMove best = mov.bestMove(newBoard);
        assertEquals(move, best); //compares 2 moves
    }
    @org.junit.jupiter.api.Test
        void bestMove2(){
        TTT3DMover mov = new TTT3DMover();
        String boardString = "-O-- OX-- XO-- ----"
                    + "--O- -X-- ---- ----"
                    + "-X-- -O-- ---- ----"
                    + "---- ---- -XX- ----";
        TTT3DBoard newBoard = new TTT3DBoard(boardString, 'O');
        TTT3DMove move = new TTT3DMove(3, 2,1,'O');
        TTT3DMove best = mov.bestMove(newBoard);
        assertEquals(move, best);
    }
}