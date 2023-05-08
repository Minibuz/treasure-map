package movement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tile.Coordinate;

class OrientationTest {

    @Test
    @DisplayName("Test get orientation North")
    void testGetOrientationN() {
        Orientation orientation = Orientation.getOrientation("N");
        Assertions.assertEquals(Orientation.NORTH, orientation);
        Assertions.assertEquals(new Coordinate(0, -1), orientation.getCoordinate());
    }

    @Test
    @DisplayName("Test get orientation East")
    void testGetOrientationE() {
        Orientation orientation = Orientation.getOrientation("E");
        Assertions.assertEquals(Orientation.EAST, orientation);
        Assertions.assertEquals(new Coordinate(1, 0), orientation.getCoordinate());
    }

    @Test
    @DisplayName("Test get orientation South")
    void testGetOrientationS() {
        Orientation orientation = Orientation.getOrientation("S");
        Assertions.assertEquals(Orientation.SOUTH, orientation);
        Assertions.assertEquals(new Coordinate(0, 1), orientation.getCoordinate());
    }

    @Test
    @DisplayName("Test get orientation West")
    void testGetOrientationW() {
        Orientation orientation = Orientation.getOrientation("W");
        Assertions.assertEquals(Orientation.WEST, orientation);
        Assertions.assertEquals(new Coordinate(-1, 0), orientation.getCoordinate());
    }

    @Test
    @DisplayName("Test get orientation throw")
    void testGetOrientationThrow() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Orientation.getOrientation("T"));
    }
}