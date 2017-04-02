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
    void winningMoves() {
        TTT3DMover mov = new TTT3DMover();
        String boardString = "XOXO XO-- XO-- ----"
                + "-O-- ---- ---- ----"
                + "-O-- ---- ---- ----"
                + "---- ---- ---- ----"
        TTT3DBoard board = boardFromString(boardString);
        List moves = new ArrayList();
        TTT3DMove move1 = new TTT3DMove(1, 4, 2, 'O');
        TTT3DMove move2 = new TTT3DMove(4, 1, 2, 'O');
        moves.add(0, move1);
        moves.add(1, move2);
        List<TTT3DMove> newBoard = mov.winningMoves(board);
        assertEquals( moves, newBoard);

        boardString = "XOXO OXOX XOXO OXOX"
               + "OXOX XOXO OXOX XOXO"
               + "XOXO OXOX XOXO OXOX"
               + "OXOX XOXO OXOX XOX-";

        board = boardFromString(boardString);
        newBoard = mov.winningMoves(board);
        moves = Collections.emptyList();
        assertEquals(moves, mov);
    }

    @org.junit.jupiter.api.Test
    void blockingMoves() {

    }

    @org.junit.jupiter.api.Test
    void forcingMoves() {

    }

    @org.junit.jupiter.api.Test
    void bestMove() {

    }
    public TTT3DBoard boardFromString(String s){
        Character[] boardArr = toCharacterArray(s);

        TTT3DBoard board = new TTT3DBoard('O');
        return board;

    }

    public Character[] toCharacterArray(String str) {

        if ( str == null ) {
            return null;
        }

        int len = str.length();
        Character[] array = new Character[len];
        for (int i = 0; i < len ; i++) {
            array[i] = new Character(str.replaceAll(" ", "").charAt(i));
        }

        return array;
    }
}