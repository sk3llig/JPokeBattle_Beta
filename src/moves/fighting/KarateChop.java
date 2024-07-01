package moves.fighting;

import moves.Move;
import pokemon.Type;

public class KarateChop extends Move {
    public KarateChop() {
        super("Karate Chop",
                Type.FIGHTING,
                25,
                "Karate Chop has an increased critical hit ratio.");
    }
}
