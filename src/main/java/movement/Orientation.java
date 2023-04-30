package movement;

import tile.Coordinate;

public enum Orientation {

    NORTH(0, -1),
    EAST(1, 0),
    SOUTH(0, 1),
    WEST(-1, 0);

    private final int x;
    private final int y;

    Orientation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate getCoordinate() {
        return new Coordinate(x, y);
    }

    public static Orientation getOrientation(String value) {
        return switch (value) {
            case "N" -> NORTH;
            case "E" -> EAST;
            case "S" -> SOUTH;
            case "W" -> WEST;
            default -> throw new IllegalArgumentException();
        };
    }
}
