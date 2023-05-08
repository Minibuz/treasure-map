package movement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

class MoveTest {

    @Test
    @DisplayName("Test of as list : AAA")
    void testOfAsList1() {
        LinkedList<Move> moves = Move.ofAsList("AAA");
        Assertions.assertEquals(List.of(Move.ADVANCE, Move.ADVANCE, Move.ADVANCE), moves);
    }

    @Test
    @DisplayName("Test of as list : GAG")
    void testOfAsList2() {
        LinkedList<Move> moves = Move.ofAsList("GAG");
        Assertions.assertEquals(List.of(Move.LEFT, Move.ADVANCE, Move.LEFT), moves);
    }

    @Test
    @DisplayName("Test of as list : DAD")
    void testOfAsList3() {
        LinkedList<Move> moves = Move.ofAsList("DAD");
        Assertions.assertEquals(List.of(Move.RIGHT, Move.ADVANCE, Move.RIGHT), moves);
    }

    @Test
    @DisplayName("Test of as list throw")
    void testOfAsListThrow() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Move.ofAsList("T"));
    }
}