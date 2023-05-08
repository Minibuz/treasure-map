import movement.Move;
import movement.Orientation;
import tile.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * Representation of a map with Adventurers and the map.
 *
 * @author minibuz
 */
public class Map {

    public static final String LINE_SPLITTER = " - ";

    // First id     :  0 for Tile where we can step on, 1 otherwise
    // Second id    :   Y
    // Third id     :   X
    private final Tile[][][] map;

    private final List<Adventurer> adventurers;

    private Map(Tile[][][] map, List<Adventurer> adventurers) {
        this.map = map;
        this.adventurers = adventurers;
    }

    /**
     * Generate a map from a file located as a given file.<br>
     * The map will be in initial state, and it needs to be proceeded to give the result of the simulation.
     *
     * @param path
     *          Path of the file with the info
     * @return
     *          New map with initial state.
     * @throws IOException
     *          File cannot be located.
     */
    public static Map generateMapFromFile(Path path) throws IOException {
        Objects.requireNonNull(path);

        List<String> lines = Files.readAllLines(path);

        String mapInformationLine = lines.get(0);
        lines.remove(0);

        String[] mapInformation = mapInformationLine.split(LINE_SPLITTER);
        if (mapInformation.length != 3 && !"C".equals(mapInformation[0])) {
            throw new IllegalArgumentException("Given file is wrong");
        }
        int xSize = Integer.parseInt(mapInformation[1]);
        int ySize = Integer.parseInt(mapInformation[2]);

        Tile[][][] tiles = new Tile[2][ySize][xSize];
        List<Adventurer> adventurers = new ArrayList<>();

        for (String line : lines) {
            String[] lineInformation = line.split(LINE_SPLITTER);
            String tileType = lineInformation[0];
            boolean canBeWalkedOn = false;
            Tile tile = switch (tileType) {
                case "A" -> {
                    Adventurer adventurer = Adventurer.newInstance(lineInformation);
                    adventurers.add(adventurer);
                    yield adventurer;
                }
                case "M" -> new Mountain(
                        Integer.parseInt(lineInformation[1]),
                        Integer.parseInt(lineInformation[2]));
                case "T" -> {
                    Treasure treasure = new Treasure(
                        Integer.parseInt(lineInformation[1]),
                        Integer.parseInt(lineInformation[2]),
                        Integer.parseInt(lineInformation[3]));
                    canBeWalkedOn = true;
                    yield treasure;
                }
                default -> throw new IllegalArgumentException("Tiles of type " + tileType + " not implemented.");
            };

            Coordinate coordinate = tile.getCoordinate();
            tiles[canBeWalkedOn?0:1][coordinate.y()][coordinate.x()] = tile;
        }

        return new Map(tiles, adventurers);
    }

    /**
     * Run the simulation.<br>
     * Can only be run once.
     */
    public void proceed() {
        int maxRounds = this.adventurers.stream().map(Adventurer::getTotalMoves).max(Integer::compareTo).orElse(0);
        IntStream.range(0, maxRounds).forEach(__ -> {
            for (Adventurer adventurer : adventurers) {
                Move adventurerNextMove = adventurer.getNextMove();
                switch (adventurerNextMove) {
                    case LEFT -> adventurer.rotateLeft();
                    case RIGHT -> adventurer.rotateRight();
                    case ADVANCE -> advanceAdventurer(adventurer);
                }
            }
        });
    }

    private void advanceAdventurer(Adventurer adventurer) {
        Coordinate coordinate = adventurer.getCoordinate();
        Orientation orientation = adventurer.getOrientation();
        Coordinate newCoordinate = coordinate.move(orientation.getCoordinate());

        if (map[1][newCoordinate.y()][newCoordinate.x()] != null) {
            return;
        }
        adventurer.setCoordinate(newCoordinate);

        Tile tile = map[0][newCoordinate.y()][newCoordinate.x()];
        if (tile instanceof Treasure treasure && treasure.haveChest()) {
            adventurer.addTreasure();
            treasure.removeOneTreasure();
            if ( !treasure.haveChest() ) {
                map[0][newCoordinate.y()][newCoordinate.x()] = null;
            }
        }
    }

    @Override
    public String toString() {
        return "C" + LINE_SPLITTER + this.map[0][0].length + LINE_SPLITTER + this.map[0].length;
    }

    /**
     * Transform the map as a string to be paste to a file.
     *
     * @return
     *          File as a string
     */
    public String toFile() {
        StringBuilder sb = new StringBuilder();
        sb.append(this).append("\n");
        // Mountains
        for (Tile[] tiles : this.map[1]) {
            for (Tile tile : tiles) {
                if (tile instanceof Mountain mountain) {
                    sb.append(mountain).append("\n");
                }
            }
        }
        // Treasures
        for (Tile[] tiles : this.map[0]) {
            for (Tile tile : tiles) {
                if (tile instanceof Treasure treasure) {
                    sb.append(treasure).append("\n");
                }
            }
        }
        // Adventurers
        for (Adventurer adventurer : this.adventurers) {
            sb.append(adventurer);
        }
        return sb.toString();
    }
}
