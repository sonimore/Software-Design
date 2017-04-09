package carleton.sonimore.TTT3D;

import java.util.List;

public class Main {

  public static void main(String[] args) {
//TTT3DBoard board1 = new TTT3DBoard("/Users/Sonia/cs257assignments-sonimore/board.txt");
//System.out.print(board1.boardToString(board1));
//    System.out.print(board1.valueInSquare(0,0,3));
//      TTT3DMove a = new TTT3DMove(1,2,3,'X');
      String boardString =  "X--- ---- ---- ----"
              + "---- X--- ---- ----"
              + "---- ---- X--- ----"
              + "---- ---- ---- ----";

      TTT3DBoard myBoard = new TTT3DBoard(boardString,'X');

      System.out.print(myBoard.boardToString(myBoard));
      //System.out.print(boardString);
      TTT3DMover myMover = new TTT3DMover();
      List<TTT3DMove> list = myMover.winningMoves(myBoard);
      TTT3DMove printMove = list.get(0);



//      TTT3DMove printMove2 = list.get(1);
      System.out.println("bleee Row: " + printMove.row + " Column: " + printMove.column + " Level: " + printMove.level);
//      System.out.println("bleee Row: " + printMove2.row + " Column: " + printMove2.column + " Level: " + printMove2.level);
      System.out.println("Size: " + list.size());
  }
}
