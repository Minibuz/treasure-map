package tile;

/**
 * Representation of a Mountain as a tile.
 *
 * @author minibuz
 */
public record Mountain(int x, int y) implements Tile {
    @Override
    public Coordinate getCoordinate() {
        return new Coordinate(x, y);
    }

    @Override
    public String toString() {
        return "M" +
                " - " + x +
                " - " + y;
    }
}
