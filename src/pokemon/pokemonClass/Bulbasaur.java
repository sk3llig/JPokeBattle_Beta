package pokemon.pokemonClass;

import moves.grass.GigaDrain;
import moves.grass.RazorLeaf;
import moves.grass.VineWhip;
import pokemon.Pokemon;
import pokemon.Type;

import java.io.Serializable;

public class Bulbasaur extends Pokemon implements Serializable {

    public Bulbasaur(){
        super("Bulbasaur",
                6,
                100,
                "MALE",
                Type.GRASS,
                50,
                49,
                45,
                64,
                "src/Img/bulbasaur.png",
                16,
                new Pokemon("Ivysaur", 6, 45, "MALE", Type.GRASS, 50, 49, 45,64, "src/Img/bulbasaur.png",25,null)
        );

        //settiamo il  dizionario delle mosse da imparare in base al livello --> mossa
        addMoveByLevel(8,new VineWhip());
        addMoveByLevel(12,new RazorLeaf());
        addMoveByLevel(16,new GigaDrain());


    }
}