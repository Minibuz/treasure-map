package tile;

import tile.visitor.TileVisitor;

/**
 * Tile interface, can be used on a grid.
 *
 * @author Minibuz
 */
public interface Tile {

    /**
     * Get the {@link Coordinate} of the {@link Tile}
     *
     * @return
     *          {@link Coordinate} of the {@link Tile}
     */
    Coordinate getCoordinate();

    boolean accept(TileVisitor visitor);
}
