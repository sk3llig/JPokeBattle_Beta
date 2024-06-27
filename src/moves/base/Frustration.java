package moves.base;

import pokemon.Type;

public class Frustration extends DefaultMoves{
    public Frustration() {
        super("Frustration",
                Type.NORMAL,
                100,
                32,
                "Frustration's power increases based on the user's lack of friendship with its Trainer" +
                " moves a Pokémon learns. It deals damage with no additional effects.");
    }
}
