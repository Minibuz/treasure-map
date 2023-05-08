package tile;

import movement.Move;
import movement.Orientation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class AdventurerTest {

    private String[] getLine() {
        return List.of("A", "Nom", "5", "5", "N", "AAA").toArray(new String[0]);
    }

    @Test
    void testNewInstance() {
        Adventurer adventurer = Adventurer.newInstance(getLine());
        Assertions.assertEquals("A - Nom - 5 - 5 - N - 0", adventurer.toString());
    }

    @Test
    void testNewInstanceThrow() {
        String[] information = List.of("A").toArray(new String[0]);
        Assertions.assertThrows(IllegalArgumentException.class, () -> Adventurer.newInstance(information));
    }

    @Test
    void testGetNextMove() {
        Adventurer adventurer = Adventurer.newInstance(getLine());
        Assertions.assertEquals(Move.ADVANCE, adventurer.getNextMove());
    }

    @Test
    void testGetOrientation() {
        Adventurer adventurer = Adventurer.newInstance(getLine());
        Assertions.assertEquals(Orientation.NORTH, adventurer.getOrientation());
    }

    @Test
    void testGetTotalMoves() {
        Adventurer adventurer = Adventurer.newInstance(getLine());
        Assertions.assertEquals(3, adventurer.getTotalMoves());
    }

    @Test
    void testRotateLeft() {
        Adventurer adventurer = Adventurer.newInstance(getLine());
        adventurer.rotateLeft();
        Assertions.assertEquals(Orientation.WEST, adventurer.getOrientation());
    }

    @Test
    void testRotateRight() {
        Adventurer adventurer = Adventurer.newInstance(getLine());
        adventurer.rotateRight();
        Assertions.assertEquals(Orientation.EAST, adventurer.getOrientation());
    }

}