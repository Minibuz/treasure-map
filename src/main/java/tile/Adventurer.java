package tile;

import movement.Move;
import movement.Orientation;

import java.util.ArrayList;
import java.util.List;

public class Adventurer implements Tile {

    private final String name;
    private int x;
    private  int y;
    private Orientation orientation;
    private final ArrayList<Move> moves;
    private int numberOfTreasure = 0;

    public Adventurer(String name, int x, int y,Orientation orientation, List<Move> moves) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        this.moves = new ArrayList<>(moves);
    }

    public String getName() {
        return name;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
    public Orientation getOrientation() {
        return orientation;
    }

    public Move getMove(int id) {
        if ( moves.size() <= id ) {
            throw new IllegalArgumentException("");
        }
        return moves.get(id);
    }

    public void addTreasure() {
        this.numberOfTreasure++;
    }
    public int getNumberOfTreasure() {
        return numberOfTreasure;
    }

    @Override
    public Coordinate getCoordinate() {
        return new Coordinate(x, y);
    }

    @Override
    public String toString() {
        return "A - " + name + " - " + x + " - " + y + " - " + orientation + " - " + numberOfTreasure;
    }

    public static Adventurer createAdventurer(String[] lineInfo) {
        if (lineInfo.length != 6) {
            throw new IllegalArgumentException();
        }

        Orientation orientation = Orientation.getOrientation(lineInfo[4]);
        List<Move> moves = Move.parseMoves(lineInfo[5]);

        return new Adventurer(
                lineInfo[1],
                Integer.parseInt(lineInfo[2]),
                Integer.parseInt(lineInfo[3]),
                orientation,
                moves
        );
    }
}
