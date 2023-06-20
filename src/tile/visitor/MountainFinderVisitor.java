package tile.visitor;

import tile.impl.Mountain;

public class MountainFinderVisitor implements TileVisitor {

    @Override
    public boolean visit(Mountain mountain) {
        return true;
    }
}
