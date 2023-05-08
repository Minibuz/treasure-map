package tile;

/**
 * Representation of a Treasure as a tile.
 *
 * @author minibuz
 */
public class Treasure implements Tile {

    private final int x;
    private final int y;

    private int treasuresLeft;

    public Treasure(int x, int y, int treasuresLeft) {
        this.x = x;
        this.y = y;
        this.treasuresLeft = treasuresLeft;
    }

    @Override
    public Coordinate getCoordinate() {
        return new Coordinate(x, y);
    }

    @Override
    public String toString() {
        return "T" +
                " - " + x +
                " - " + y +
                " - " + treasuresLeft;
    }

    /**
     *
     * @return
     *          True if there's still chest on the tile, false otherwise.
     */
    public boolean haveChest() {
        return treasuresLeft != 0;
    }

    public void removeOneTreasure() {
        treasuresLeft--;
    }
}
