package tile;

import movement.Move;
import movement.Orientation;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Representation of an Adventurer as a tile.
 *
 * @author minibuz
 */
public class Adventurer implements Tile {

    private int x;
    private int y;
    private final String name;
    private Orientation orientation;
    // LinkedList
    private final Queue<Move> moves;
    private int treasures = 0;

    private Adventurer(int x, int y, String name, Orientation orientation, LinkedList<Move> moves) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.orientation = orientation;
        this.moves = moves;
    }

    /**
     * Create a new Adventurer based on an array of information.<br>
     *
     * @param lineInformation
     *          Array as ["A",name,x,y,direction,moves]
     * @return
     *          New adventurer
     */
    public static Adventurer newInstance(String[] lineInformation) {
        if (lineInformation.length != 6) {
            throw new IllegalArgumentException();
        }
        int x = Integer.parseInt(lineInformation[2]);
        int y = Integer.parseInt(lineInformation[3]);
        String name = lineInformation[1];
        Orientation orientation = Orientation.getOrientation(lineInformation[4]);
        LinkedList<Move> moves = Move.ofAsList(lineInformation[5]);

        return new Adventurer(x, y, name, orientation, moves);
    }

    public void rotateLeft() {
        int newOrientation = (this.orientation.ordinal() - 1) % Orientation.values().length;
        if (newOrientation < 0 ) {
            newOrientation += 4;
        }
        this.orientation = Orientation.values()[newOrientation];
    }

    public void rotateRight() {
        int newOrientation = (this.orientation.ordinal() + 1) % Orientation.values().length;
        this.orientation = Orientation.values()[newOrientation];
    }

    public Move getNextMove() {
        return moves.poll();
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.x = coordinate.x();
        this.y = coordinate.y();
    }

    public void addTreasure() {
        treasures++;
    }

    @Override
    public String toString() {
        return "A - " + name + " - " + x + " - " + y + " - " + orientation.toString().charAt(0) + " - " + treasures;
    }

    @Override
    public Coordinate getCoordinate() {
        return new Coordinate(x, y);
    }

    public int getTotalMoves() {
        return moves.size();
    }
}
