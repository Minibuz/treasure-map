package tile;

public class Treasure implements Tile {

    private final int x;
    private final int y;
    private int amountOfChest;

    public Treasure(int x, int y, int amountOfChest) {
        this.x = x;
        this.y = y;
        this.amountOfChest = amountOfChest;
    }

    public void removeChest() {
        if ( this.amountOfChest <= 0 ) {
           throw new IllegalStateException("TODO Error");
        }
        this.amountOfChest = this.amountOfChest - 1;
    }

    @Override
    public Coordinate getCoordinate() {
        return new Coordinate(x, y);
    }

    @Override
    public String toString() {
        return "T - " + x + " - " + y + " - " + amountOfChest;
    }
}
