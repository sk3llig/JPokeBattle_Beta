package pokemon;

import moves.Move;
import pokemon.pokemonClass.Bulbasaur;
import pokemon.pokemonClass.Charmender;
import pokemon.pokemonClass.Squirtle;

import java.util.ArrayList;
import java.util.List;

public class Pokedex {
    private List<Pokemon> pokedex= new ArrayList<Pokemon>();

    public  Pokedex() {
        pokedex.add(new Bulbasaur());
        pokedex.add(new Charmender());
        pokedex.add(new Squirtle());
        pokedex.add(new Bulbasaur());
        pokedex.add(new Charmender());
        pokedex.add(new Squirtle());

    }


    public List<Pokemon> getPokedex() {
        return pokedex;

    }


    //PROVA REPLACE MOSSE e incremento livello

   public static void main(String[] args){
        Pokedex p = new Pokedex();

        Pokemon po=p.getPokedex().get(0);
        System.out.println(po.getMoves());

        po.replaceMove(po.getMoves().get(0),po.getMoveByLevel(8));

        System.out.println(po.getMoves());
       System.out.println( "Esperienza necessaria : " + po.getExpNecessaria());

       System.out.println(po);
       po.updateLevel();
       System.out.println(po);


   }

}
