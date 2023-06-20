package movement;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Enumeration of the possible moves by an adventurer.
 *
 * @author Minibuz
 */
public enum Move {

    LEFT("G"),
    RIGHT("D"),
    ADVANCE("A");

    private static final Map<String, Move> BY_LETTER = new HashMap<>();
    static {
        for (Move e : values()) {
            BY_LETTER.put(e.letter, e);
        }
    }

    private final String letter;

    Move(String letter) {
        this.letter = letter;
    }

    /**
     * Transform a string such as "AGD" to a list of moves like (ADVANCE, LEFT, RIGHT).
     *
     * @param string
     *          {@link String}
     * @return
     *          The list of moves as a fifo queue
     */
    public static LinkedList<Move> ofAsList(String string) {
        LinkedList<Move> moves = new LinkedList<>();
        for (char character : string.toCharArray()) {
            Move move = Move.getMove(character + "");
            moves.add(move);
        }
        return moves;
    }

    /**
     * Transform a letter as a {@link String} to an {@link Move}
     *
     * @param name
     *          {@link String}
     * @return
     *          {@link Move}
     */
    public static Move getMove(String name) {
        return BY_LETTER.get(name);
    }
}
