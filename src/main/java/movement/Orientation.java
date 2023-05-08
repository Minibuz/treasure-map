package movement;

import tile.Coordinate;

/**
 * Enumeration of the directions.
 *
 * @author minibuz
 */
public enum Orientation {
    NORTH(-1, 0),
    EAST(0, 1),
    SOUTH(1, 0),
    WEST(0, -1);

    private final int x;
    private final int y;

    Orientation(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public Coordinate getCoordinate() {
        return new Coordinate(x, y);
    }

    public static Orientation getOrientation(String name) {
        return switch (name) {
            case "N" -> NORTH;
            case "E" -> EAST;
            case "S" -> SOUTH;
            case "W" -> WEST;
            default -> throw new IllegalArgumentException("Unexpected value: " + name);
        };
    }
}
