package tile.impl;

import movement.Move;
import movement.Orientation;
import tile.Coordinate;
import tile.Tile;
import tile.visitor.TileVisitor;

import java.util.LinkedList;
import java.util.List;

/**
 * An Adventurer as a Tile
 *
 * @author Minibuz
 */
public class Adventurer implements Tile {

    private Coordinate coordinate;
    private final String name;
    private Orientation orientation;
    private final List<Move> moves;
    private int treasures = 0;

    private Adventurer(Coordinate coordinate, String name, Orientation orientation, List<Move> moves) {
        this.coordinate = coordinate;
        this.name = name;
        this.orientation = orientation;
        this.moves = moves;
    }

    /**
     * Create a new adventurer based on an array of informations.
     * The array of string should be like :
     * <p>
     *     ["A", name as {@link String}, x as {@link Integer}, y as {@link Integer}, orientation as {@link String}, moves as {@link String}]
     *
     * @param lineInformation
     *          Array of {@link String}
     * @return
     *          Adventurer
     */
    public static Adventurer newInstance(String[] lineInformation) {
        if (lineInformation.length != 6) {
            throw new IllegalArgumentException();
        }

        String name = lineInformation[1];
        int x = Integer.parseInt(lineInformation[2]);
        int y = Integer.parseInt(lineInformation[3]);
        Orientation orientation = Orientation.getOrientation(lineInformation[4]);
        LinkedList<Move> moves = Move.ofAsList(lineInformation[5]);

        return new Adventurer(new Coordinate(x, y), name, orientation, moves);
    }

    /**
     * Get the next move and removes it from the move list.
     *
     * @return
     *          First move on the move list
     */
    public Move getNextMove() {
        return moves.remove(0);
    }

    /**
     * Get the orientation of the adventurer.
     *
     * @return
     *          {@link Orientation}
     */
    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    /**
     * Change the coordinate of the adventurer.
     *
     * @param coordinate
     *          {@link Coordinate} with the new x and y
     */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * Add one treasure to the adventurer.
     */
    public void addTreasure() {
        treasures++;
    }

    /**
     * Get the total number of moves in the move list.
     *
     * @return
     *          The amount of moves in the move list.
     */
    public int getTotalMoves() {
        return moves.size();
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public boolean accept(TileVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "Adventurer{" +
                "x=" + coordinate.x() +
                ", y=" + coordinate.y() +
                ", name='" + name + '\'' +
                ", orientation=" + orientation +
                ", moves=" + moves +
                ", treasures=" + treasures +
                '}';
    }
}
