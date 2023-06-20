package tile.impl;

import tile.Coordinate;
import tile.Tile;

public abstract class ImmovableTile implements Tile {

    private final Coordinate coordinate;

    protected ImmovableTile(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }
}
