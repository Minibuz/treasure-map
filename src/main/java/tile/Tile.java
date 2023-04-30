package tile;

import java.util.Objects;

public interface Tile {

    Coordinate getCoordinate();

    static Tile createTile(String[] lineInfo) {
        if (Objects.requireNonNull(lineInfo).length < 3) {
            throw new IllegalArgumentException();
        }

        return switch (lineInfo[0]) {
            case "M" ->
                    new Mountain(
                            Integer.parseInt(lineInfo[1]),
                            Integer.parseInt(lineInfo[2])
                    );
            case "T" ->
                    new Treasure(
                            Integer.parseInt(lineInfo[1]),
                            Integer.parseInt(lineInfo[2]),
                            Integer.parseInt(lineInfo[3])
                    );
            case "A" ->
                    Adventurer.createAdventurer(lineInfo);
            default -> throw new IllegalArgumentException();
        };
    }
}
