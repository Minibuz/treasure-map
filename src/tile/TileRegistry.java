package tile;

import java.util.HashMap;
import java.util.function.Function;

public class TileRegistry {
    private final HashMap<String, Function<String[], ? extends Tile>> map = new HashMap<>();

    public void register(String name, Function<String[], ? extends Tile> function) {
        map.put(name, function);
    }

    public Tile create(String name, String[] elements) {
        return map.computeIfAbsent(name, n -> {
                    throw new IllegalArgumentException("Unknown " + n);
                }).apply(elements);
    }
}
