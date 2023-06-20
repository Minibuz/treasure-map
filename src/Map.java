import movement.Move;
import movement.Orientation;
import tile.*;
import tile.impl.Adventurer;
import tile.impl.Mountain;
import tile.impl.Treasure;
import tile.visitor.AdventurerFinderVisitor;
import tile.visitor.MountainFinderVisitor;
import tile.visitor.TreasureFinderVisitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Map with an array of tiles, 2D, with all the adventurers.<br>
 * Can be created then proceed before printing to a file.
 *
 * @author Minibuz
 */
public class Map {

    public static final String LINE_SPLITTER = " - ";
    public static final TileRegistry registry = new TileRegistry();
    static {
        registry.register( "A", Adventurer::newInstance);
        registry.register("M", Mountain::newInstance);
        registry.register("T", Treasure::newInstance);
    }

    private final int x;
    private final int y;

    private final List<Treasure> treasures;
    private final List<Mountain> mountains;
    private final List<Adventurer> adventurers;

    private Map(int x, int y, List<Treasure> treasures, List<Mountain> mountains, List<Adventurer> adventurers) {
        this.x = x;
        this.y = y;
        this.treasures = treasures;
        this.mountains = mountains;
        this.adventurers = adventurers;
    }

    public static Map generateMapFromFile(Path path) throws IOException {
        Objects.requireNonNull(path);

        List<String> lines = Files.readAllLines(path);

        String mapInformationLine = lines.get(0);

        String[] mapInformation = mapInformationLine.split(LINE_SPLITTER);
        if (mapInformation.length != 3 && !"C".equals(mapInformation[0])) {
            throw new IllegalArgumentException("Given file is wrong");
        }
        int xSize = Integer.parseInt(mapInformation[1]);
        int ySize = Integer.parseInt(mapInformation[2]);

        List<Tile> tiles = lines.stream()
                .skip(1)
                .map(line -> line.split(LINE_SPLITTER))
                .map(elements -> registry.create(elements[0], elements))
                .toList();

        TreasureFinderVisitor treasureFinderVisitor = new TreasureFinderVisitor();
        MountainFinderVisitor mountainFinderVisitor = new MountainFinderVisitor();
        AdventurerFinderVisitor adventurerFinderVisitor = new AdventurerFinderVisitor();

        List<Treasure> treasures = tiles.stream()
                .filter(tile -> tile.accept(treasureFinderVisitor))
                .map(tile -> (Treasure) tile)
                .collect(Collectors.toList());
        List<Adventurer> adventurers = tiles.stream()
                .filter(tile -> tile.accept(adventurerFinderVisitor))
                .map(tile -> (Adventurer) tile)
                .toList();
        List<Mountain> mountains = tiles.stream()
                .filter(tile -> tile.accept(mountainFinderVisitor))
                .map(tile -> (Mountain) tile)
                .toList();

        return new Map(xSize, ySize, treasures, mountains, adventurers);
    }

    public void proceed() {
        int maxRounds = this.adventurers.stream().map(Adventurer::getTotalMoves).max(Integer::compareTo).orElse(0);
        IntStream.range(0, maxRounds).forEach(__ -> {
            for (Adventurer adventurer : adventurers) {
                Move adventurerNextMove = adventurer.getNextMove();
                Orientation orientation = adventurer.getOrientation();
                switch (adventurerNextMove) {
                    case LEFT -> adventurer.setOrientation(orientation.rotateLeft());
                    case RIGHT -> adventurer.setOrientation(orientation.rotateRight());
                    case ADVANCE -> advanceAdventurer(adventurer);
                }
            }
        });
    }

    private void advanceAdventurer(Adventurer adventurer) {
        Coordinate coordinate = adventurer.getCoordinate();
        Orientation orientation = adventurer.getOrientation();
        Coordinate newCoordinate = coordinate.move(orientation.getVector());

        if (adventurers.stream().anyMatch(tile -> tile.getCoordinate().equals(newCoordinate))) {
            return;
        }
        if (mountains.stream().anyMatch(tile -> tile.getCoordinate().equals(newCoordinate))) {
            return;
        }

        adventurer.setCoordinate(newCoordinate);
        Optional<Treasure> treasureOpt = treasures.stream().filter(tile -> tile.getCoordinate().equals(newCoordinate)).findFirst();
        if (treasureOpt.isPresent()) {
            Treasure treasure = treasureOpt.get();
            adventurer.addTreasure();
            if (!treasure.removeTreasure()) {
                treasures.remove(treasure);
            }
        }
    }

    @Override
    public String toString() {
        return "C" + LINE_SPLITTER + this.x + LINE_SPLITTER + this.y;
    }

    public String toFile() {
        StringBuilder sb = new StringBuilder();
        sb.append(this).append("\n");
        for (Mountain mountain : mountains) {
            sb.append(mountain).append("\n");
        }
        for (Treasure treasure : treasures) {
            sb.append(treasure).append("\n");
        }
        for (Adventurer adventurer : adventurers) {
            sb.append(adventurer).append("\n");
        }
        return sb.toString();
    }
}
