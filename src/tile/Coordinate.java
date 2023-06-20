package tile;

import movement.Vector;

/**
 * Coordinate containing an x index and a y index.
 *
 * @author Minibuz
 */
public record Coordinate(int x, int y) {

    /**
     * Move by a given vector.
     *
     * @param vector
     *          {@link Vector}
     * @return
     *          The merged {@link Coordinate}
     */
    public Coordinate move(Vector vector) {
        return new Coordinate(this.x + vector.x(), this.y + vector.y());
    }
}
