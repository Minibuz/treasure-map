package tile.visitor;

import tile.impl.Treasure;

public class TreasureFinderVisitor implements TileVisitor {

    @Override
    public boolean visit(Treasure treasure) {
        return true;
    }
}
