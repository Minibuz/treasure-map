package tile.visitor;

import tile.impl.Adventurer;

public class AdventurerFinderVisitor implements TileVisitor {

    @Override
    public boolean visit(Adventurer adventurer) {
        return true;
    }
}
