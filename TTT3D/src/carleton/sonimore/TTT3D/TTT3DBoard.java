//package carleton.sonimore.TTT3D;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
/**
 * Created by nylaworker on 3/30/17.
 */
/**
 * TTT3DBoard represents a simple 4x4x4 3D tic-tac-toe game. Each instance
 * stores includes the contents of each of the 64 squares, plus an indicator
 * of whose turn it is ('X' or 'O').
 *
 * To keep things simple, we use the uppercase letters X and O rather than
 * something more complicated (e.g. an enumerated type) to represent players
 * and their moves. Empty squares are represented using a hyphen.
 *
 * FOR DISCUSSION:
 * (1) How do you feel about my use of "this" to refer to instance variables?
 * We won't use this throughout this project.
 * (2) Does it make sense to define BOARD_SIZE and EMPTY_SQUARE instead of
 * just using 4 and '-' throughout the code? If so, then why not also define
 * constants for 'X' and 'O'?
 *(3)What is missing?
 *        Setter for whoseTurn?
 *        Has somebody won yet?
 *
 * @author Jeff Ondich
 * @version 30 March 2017
 */
public class TTT3DBoard {
    public final static Character EMPTY_SQUARE = '-';
    public final static int BOARD_SIZE = 4;

    private Character squareValues[];
    private Character whoseTurn;

    /**
     * Initialize an empty game board.
     */
    public TTT3DBoard(String boardStr, Character whoStarts) {

        int squareArrayLength = BOARD_SIZE * BOARD_SIZE * BOARD_SIZE;
        this.squareValues = new Character[squareArrayLength];
        for (int i = 0; i < squareArrayLength ; i++) {
            this.squareValues[i] = new Character(boardStr.replaceAll(" ", "").charAt(i));
        }
        if(whoStarts == 'X'){
            this.whoseTurn = whoStarts;
        }
        else{
            this.whoseTurn = 'O';
        }
    }



    /**
     *
     * @param whoseTurn
     */
    public void setWhoseTurn(Character whoseTurn){
        this.whoseTurn = whoseTurn;
    }
    /**
     * Copy constructor.
     * @param otherBoard the board to be copied
     */
    public TTT3DBoard(TTT3DBoard otherBoard) {
        int squareArrayLength = BOARD_SIZE * BOARD_SIZE * BOARD_SIZE;
        this.squareValues = new Character[squareArrayLength];
        System.arraycopy(otherBoard.squareValues, 0, this.squareValues, 0, squareArrayLength);
    }

    /**
     * @return 'X' or 'O', depending on whose turn it is
     */
    public Character getWhoseTurn() {
        return this.whoseTurn;
    }

    /**
     * @param level the level of the board position
     * @param row the row of the board position
     * @param column the column of the board position
     * @return the value ('X', 'O', or EMPTY_SQUARE) located at the specified
     * position on the game board
     */
    public Character valueInSquare(int level, int row, int column) {
        if (level < 0 || level >= BOARD_SIZE) {
            throw new IllegalArgumentException("Illegal level number " + level);
        }
        if (row < 0 || row >= BOARD_SIZE) {
            throw new IllegalArgumentException("Illegal row number " + row);
        }
        if (column < 0 || column >= BOARD_SIZE) {
            throw new IllegalArgumentException("Illegal column number " + column);
        }
        return this.squareValues[indexForPosition(level, row, column)];
    }

    //public void boardPrint()

    /**
     * @param board Takes the board that is going to be shown to the player
     * @return the board as a string. This is also a printer method.
     */
    public String boardToString(TTT3DBoard board){
        String str = new String();
        int rowPrint = 1;
        int levelPrint = 0;

        for(int i=0; i < BOARD_SIZE*BOARD_SIZE*BOARD_SIZE; i++) {

            if(rowPrint == 4 ){
                if (levelPrint == 4){
                    System.out.println();
                    levelPrint = 0;
                }
                str =   str + String.valueOf(squareValues[i])+" ";
                System.out.print(str);
                str = new String();
                rowPrint = 1;
                levelPrint++;

            }
            else {
                str = str + String.valueOf(squareValues[i]);
                rowPrint++;
            }
        }
        System.out.println();
        System.out.println(board.getWhoseTurn());

        return str;

    }

    /**
     *This is another constructor of the board that takes a path to a txt file, such as:
     *XOXO XO-- XO-- ----
     *X--- ---- ---- ----
     *X--- ---- ---- ----
     *---- ---- ---- ----
     *X
     * @param path it takes the path to the txt board and reads it as a board.
     * @return a TTT3Dboard from a file of a bord string
     */
    public TTT3DBoard(String path) {
        String result = "";

        int squareArrayLength = BOARD_SIZE * BOARD_SIZE * BOARD_SIZE;
        this.squareValues = new Character[squareArrayLength];

        try {
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            result = new String(encoded, StandardCharsets.UTF_8);
        } catch (IOException e) {
            // No need to do anything here.
        }
        String resultNoSp = result.replaceAll(" ", "");
        String resultNoCarr = resultNoSp.replaceAll("[\\r\\n]", "");

        for (int i = 0; i < squareArrayLength ; i++) {
            this.squareValues[i] = new Character(resultNoCarr.charAt(i));
        }


        Character whoStarts = resultNoCarr.charAt(64);

        if(whoStarts == 'X'){
            this.whoseTurn = whoStarts;
        }
        else{
            this.whoseTurn = 'O';
        }
    }



    /**
     * Apply the specified move to this game board.
     * If the move is legal, this game board reflects the change and the whoseTurn changes to the other player.
     * @param move the move to be made
     * @throws IndexOutOfBoundsException if the move position is out of bounds, in
     * which case this game board is not changed
     * @throws IllegalArgumentException if it's not currently the move's player's
     * turn, in which case this game board is not changed
     */
    public void makeMove(TTT3DMove move) {
        if (move.row < 0 || move.row >= BOARD_SIZE) {
            throw new IndexOutOfBoundsException("Illegal row number " + move.row);
        }
        if (move.column < 0 || move.column >= BOARD_SIZE) {
            throw new IndexOutOfBoundsException("Illegal column number " + move.column);
        }
        if (move.level < 0 || move.level >= BOARD_SIZE) {
            throw new IndexOutOfBoundsException("Illegal level number " + move.level);
        }
        if (move.player != this.whoseTurn) {
            throw new IllegalArgumentException("It's not " + move.player + "'s turn");
        }

        this.squareValues[indexForPosition(move.level,move.row, move.column)] = this.whoseTurn;
        this.whoseTurn = (this.whoseTurn == 'X' ? 'O' : 'X');
    }

    /**
     * @param level the level of the board position
     * @param row the row of the board position
     * @param column the column of the board position
     * @return the index to be used in the linear array squareValues to represent the
     * 3D position (level, row, column). We're storing the squares in level-major and
     * then row-major order.
     */
    private int indexForPosition(int level, int row, int column) {
        return BOARD_SIZE * BOARD_SIZE * level + BOARD_SIZE * row + column;
    }

    public void printNewBoard(TTT3DBoard board, List<TTT3DMove> moves){
        String str = new String();
        int rowPrint = 1;
        int levelPrint = 0;


        for (int i = 0; i < moves.size(); i++){
            TTT3DMove move = moves.get(i);
            int ofMove = indexForPosition(move.level,move.row,move.column);
            squareValues[ofMove] = '*';
        }

        for(int i=0; i < BOARD_SIZE*BOARD_SIZE*BOARD_SIZE; i++) {

            if(rowPrint == 4 ){
                if (levelPrint == 4){
                    System.out.println();
                    levelPrint = 0;
                }
                str =   str + String.valueOf(squareValues[i])+" ";
                System.out.print(str);
                str = new String();
                rowPrint = 1;
                levelPrint++;

            }
            else {
                str = str + String.valueOf(squareValues[i]);
                rowPrint++;
            }
        }
        System.out.println();
        System.out.println(board.getWhoseTurn());
    }
}
