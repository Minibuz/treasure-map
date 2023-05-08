package movement;

import java.util.LinkedList;

/**
 * Enumeration of the possible moves made by an Adventurer.
 *
 * @author minibuz
 */
public enum Move {

    LEFT,
    RIGHT,
    ADVANCE;

    /**
     * Transform a string of initials as moves. <br>
     * From "AGB" to [Advance, Left, Right]
     *
     * @param string
     *          List of all moves with initials
     * @return
     *          List of all moves
     */
    public static LinkedList<Move> ofAsList(String string) {
        LinkedList<Move> moves = new LinkedList<>();
        for (char character : string.toCharArray()) {
            Move move = switch (character) {
                case 'G' -> LEFT;
                case 'D' -> RIGHT;
                case 'A' -> ADVANCE;
                default -> throw new IllegalArgumentException();
            };
            moves.add(move);
        }
        return moves;
    }
}
