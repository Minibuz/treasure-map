package tile.impl;

import tile.Coordinate;
import tile.visitor.TileVisitor;

/**
 * A Treasure as a Tile
 *
 * @author Minibuz
 */
public class Treasure extends ImmovableTile {

    private int treasuresLeft;

    public Treasure(Coordinate coordinate, int treasuresLeft) {
        super(coordinate);
        this.treasuresLeft = treasuresLeft;
    }

    public static Treasure newInstance(String[] lineInformation) {
        if (lineInformation.length != 4) {
            throw new IllegalArgumentException();
        }

        int x = Integer.parseInt(lineInformation[1]);
        int y = Integer.parseInt(lineInformation[2]);
        int amountOfTreasure = Integer.parseInt(lineInformation[3]);

        return new Treasure(new Coordinate(x, y), amountOfTreasure);
    }

    /**
     * Remove one treasure from the treasure.
     */
    public boolean removeTreasure() {
        treasuresLeft--;
        return treasuresLeft>0;
    }

    @Override
    public boolean accept(TileVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "T" +
                " - " + super.getCoordinate().x() +
                " - " + super.getCoordinate().y() +
                " - " + treasuresLeft;
    }
}
