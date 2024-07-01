package moves.normal;

import pokemon.Type;

public class Tackle extends DefaultMoves{
    public Tackle() {
        super("Tackle",
                Type.NORMAL,
                20,
                "Tackle deals damage and has no secondary effect.");
    }
}