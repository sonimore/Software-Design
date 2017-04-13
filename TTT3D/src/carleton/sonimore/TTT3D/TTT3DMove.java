//package carleton.sonimore.TTT3D;

/**
 * Created by Jeff Ondich
 * Edited by Sonia Moreno and Nyla Worker
 * We added a getCoordinates method.
 */
public class TTT3DMove {
    public int level;
    public int row;
    public int column;
    public Character player;

    TTT3DMove(int level, int row, int column, Character player) {
        this.level = level;
        this.row = row;
        this.column = column;
        this.player = player;
    }

    /**
     * This methods allows easy comparison in testing.
     * @return a string with the coordinates in this order "LevelColumRow"
     */
    public String getCoord(){
        String coordinates;
        coordinates = Integer.toString(level) + Integer.toString(column) + Integer.toString(row);
        return coordinates;
    }

}