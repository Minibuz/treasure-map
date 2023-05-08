package tile;

/**
 * Representation of the coordinate on a map.
 *
 * @author minibuz
 *
 * @param x
 * @param y
 */
public record Coordinate(int x, int y) {

    public Coordinate move(Coordinate coordinate) {
        return new Coordinate(this.x + coordinate.x, this.y + coordinate.y);
    }
}
