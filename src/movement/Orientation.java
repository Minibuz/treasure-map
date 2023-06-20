package movement;

import tile.Coordinate;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumeration with the four directions.
 * Each direction contains the coordinate change for a move in that direction.
 *
 * @author Minibuz
 */
public enum Orientation {
    NORTH("N", new Vector(0, -1)),
    EAST("E", new Vector(1, 0)),
    SOUTH("S", new Vector(0, 1)),
    WEST("W", new Vector(-1, 0));

    private static final Map<String, Orientation> BY_LETTER = new HashMap<>();
    static {
        for (Orientation e : values()) {
            BY_LETTER.put(e.letter, e);
        }
    }

    private final String letter;
    private final Vector vector;

    Orientation(String letter, Vector vector) {
        this.letter = letter;
        this.vector = vector;
    }

    /**
     * Return the coordinate associate with the direction.
     *
     * @return
     *          {@link Coordinate} for a move in the direction.
     */
    public Vector getVector() {
        return vector;
    }

    /**
     * Transform a letter as a {@link String} to an {@link Orientation}
     *
     * @param name
     *          {@link String}
     * @return
     *          {@link Orientation}
     */
    public static Orientation getOrientation(String name) {
        return BY_LETTER.get(name);
    }

    /**
     * Rotate the adventurer to the left.
     * <p>
     * Ex:
     * If ( orientation = North ) orientation = West
     */
    public Orientation rotateLeft() {
        int newOrientation = (this.ordinal() - 1 + Orientation.values().length) % Orientation.values().length;
        return Orientation.values()[newOrientation];
    }

    /**
     * Rotate the adventurer to the right.
     * <p>
     * Ex:
     * If ( orientation = North ) orientation = East
     */
    public Orientation rotateRight() {
        int newOrientation = (this.ordinal() + 1 + Orientation.values().length) % Orientation.values().length;
       return Orientation.values()[newOrientation];
    }
}
