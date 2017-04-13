//package carleton.sonimore.TTT3D;

import java.util.List;

public class Main {

  public static void main(String[] args) {
      String boardString =  "---- X--- X--- X---"
                          + "---- XXX- X--- XXX-"
                          + "X--- XXX- -XX- XXX-"
                          + "X--- ---- X--- ----";

      String boardString2 =  "---- X--- X--- X---"
                           + "---- XXX- X--- XXX-"
                           + "---- XXX- -XX- XXX-"
                           + "---- ---- X--- ----";

      TTT3DBoard myBoard = new TTT3DBoard(boardString,'O');

      TTT3DMover myMover = new TTT3DMover();
      List<TTT3DMove> list = myMover.blockingMoves(myBoard);
      for (int i = 0; i < list.size(); i++){
          TTT3DMove move = list.get(i);
          System.out.println(move.getCoord());
      }
      myBoard.printNewBoard(myBoard, list);
      System.out.println("Size: " + list.size());
  }
}
