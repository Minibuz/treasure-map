import tile.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Map {

    private final Tile[][] tiles;
    private final List<Adventurer> adventurers;

    private Map(Tile[][] tiles, List<Adventurer> adventurers) {
        this.tiles = tiles;
        this.adventurers = adventurers;
    }

    public static Map generateMapFromFile(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        String mapInformation = lines.get(0);
        String[] dataMap = mapInformation.split(" - ");
        if (dataMap.length != 3 && !"C".equals(dataMap[0])) {
            throw new IllegalArgumentException("File doesn't have the good format");
        }

        int x = Integer.parseInt(dataMap[1]);
        int y = Integer.parseInt(dataMap[2]);
        Tile[][] tiles = new Tile[y][x];
        List<Adventurer> adventurers = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {
            generateTileFromLine(lines.get(i), tiles, adventurers);
        }

        return new Map(tiles, adventurers);
    }

    private static void generateTileFromLine(String line, Tile[][] tiles, List<Adventurer> adventurers) {
        if (line.startsWith("#")) {
            return;
        }

        String[] lineInfo = line.split(" - ");
        Tile tile = Tile.createTile(lineInfo);

        if (tile instanceof Adventurer adventurer) {
            adventurers.add(adventurer);
        }

        Coordinate tileCoordinate = tile.getCoordinate();
        tiles[tileCoordinate.y()][tileCoordinate.x()] = tile;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("C - ").append(tiles[0].length).append(" - ").append(tiles.length).append("\n");
        for (Tile[] tilesRow : tiles) {
            for (Tile tile : tilesRow) {
                if (tile != null) {
                    sb.append(tile).append("\n");
                }
            }
        }
        return sb.toString();
    }
}
