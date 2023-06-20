package tile.visitor;

import tile.impl.Adventurer;
import tile.impl.Mountain;
import tile.impl.Treasure;

public interface TileVisitor {

    default boolean visit(Adventurer adventurer) {
        // Empty by default
        return false;
    }

    default boolean visit(Mountain mountain) {
        // Empty by default
        return false;
    }

    default boolean visit(Treasure treasure) {
        // Empty by default
        return false;
    }
}
