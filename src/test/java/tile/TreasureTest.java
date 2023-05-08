package tile;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TreasureTest {

    @Test
    void removeOneTreasure() {
        Treasure treasure = new Treasure(0, 0, 1);
        Assertions.assertTrue(treasure.haveChest());
        treasure.removeOneTreasure();
        Assertions.assertFalse(treasure.haveChest());
    }
}