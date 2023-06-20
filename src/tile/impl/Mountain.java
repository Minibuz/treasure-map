package tile.impl;

import tile.Coordinate;
import tile.visitor.TileVisitor;

/**
 * A Mountain as a Tile
 *
 * @author Minibuz
 */
public class Mountain extends ImmovableTile  {

    public Mountain(Coordinate coordinate) {
        super(coordinate);
    }

    public static Mountain newInstance(String[] lineInformation) {
        if (lineInformation.length != 3) {
            throw new IllegalArgumentException();
        }

        int x = Integer.parseInt(lineInformation[1]);
        int y = Integer.parseInt(lineInformation[2]);

        return new Mountain(new Coordinate(x, y));
    }

    @Override
    public boolean accept(TileVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "M" +
                " - " + super.getCoordinate().x() +
                " - " + super.getCoordinate().y();
    }
}
