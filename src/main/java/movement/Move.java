package movement;

import java.util.ArrayList;
import java.util.List;

public enum Move {

    A,
    D,
    G;

    public static List<Move> parseMoves(String movesStr) {
        List<Move> moves = new ArrayList<>();
        for (int i = 0; i < movesStr.length(); i++) {
            Move move = parseMove(movesStr.substring(i, i+1));
            moves.add(move);
        }
        return moves;
    }

    public static Move parseMove(String moveStr) {
        if (moveStr.length() > 1) {
            throw new IllegalArgumentException();
        }
        return Move.valueOf(moveStr);
    }
}
