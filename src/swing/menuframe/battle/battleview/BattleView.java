package swing.menuframe.battle.battleview;

import moves.Move;
import moves.base.Action;
import moves.base.BodySlam;
import moves.base.Frustration;
import moves.base.GigaImpact;
import players.Player;
import pokemon.Pokedex;
import pokemon.Pokemon;
import swing.menuframe.battle.PokeBattleInfoPanel;
import swing.menuframe.battle.PokeImgLabel;
import swing.menuframe.battle.ScoreOfBattles;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class BattleView extends JFrame implements Serializable {

    private Player giocatore1;
    private Player giocatore2;
    private Move mossaSelezionata1;
    private Move mossaSelezionata2;
    private JLabel statoBattaglia;
    private int giocatoreDiTurno = 1;
    private PokeBattleInfoPanel poke1InfoPanel;
    private PokeBattleInfoPanel poke2InfoPanel;
    private PokeImgLabel pokemon1Image;
    private PokeImgLabel pokemon2Image;
    private ScoreOfBattles scorePanel;


    // Pannelli della scelta del tipo di azioni da far fare al pokemon
    CardLayout cardLayout1;
    CardLayout cardLayout2;
    //Pannelli da associare al CardLayout
    private JPanel panPrincAzi1 = new JPanel();
    private JPanel panPrincAzi2 = new JPanel();

    // SottoPannelli
    private JPanel pannelloAzioni1;
    private JPanel pannelloAzioni2;
    // SottoPannelli Giocatore 1
    private PannelloMosse pannelloMosse1;
    private PannelloCambioPokemon pannelloCambio1;
    // SottoPannelli Giocatore 2
    private PannelloMosse pannelloMosse2;
    private PannelloCambioPokemon pannelloCambio2;

    private JButton attaccoButton;
    private JButton cambioButton;

    private Pokemon pokemon1InCampo;
    private Pokemon pokemon2InCampo;


    public BattleView(Player player1, Player player2) {
        // Creazione dei giocatori
        giocatore1 = player1;
        giocatore2 = player2;
        // Dimensioni Frame Battaglia
        setSize(600,650);//600 width and 650 height
        setLayout(null);//using no layout managers
        setLocationRelativeTo(null);//centro dello schermo
        setResizable(false);


        // Carico l'IMMAGINE DELLO SFONDO DEL COMBATTIMENTO
        BufferedImage img = null ;
        try {
            img = ImageIO.read(new File("src/Img/battleBack1.jpeg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image dimg = img.getScaledInstance(getWidth(), getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        // Impostiamo un'etichetta che rappresenta lo sfondo, assegnandoci l'immagine
        JLabel wallpaper= new JLabel("",imageIcon,JLabel.CENTER);
        wallpaper.setBounds(0,0,600,650);
        // Aggiunta dei Pokémon alle squadre dei giocatori

        // Impostazione della finestra
        setTitle("Battaglia Pokémon");

        // Pannello per le azioni del giocatore
        pannelloAzioni1 = new JPanel(new GridLayout(2,2,2,2));
        pannelloAzioni1.setBounds(245,500,345,100);
        pannelloAzioni1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
        // Bottoni del pannello azioni
        attaccoButton = new JButton("Attacca");
        cambioButton = new JButton("Cambia Pokémon");
        pannelloAzioni1.add(attaccoButton);
        pannelloAzioni1.add(cambioButton);

        // Pannello per le azioni del giocatore
        pannelloAzioni2 = new JPanel(new GridLayout(2,2,2,2));
        pannelloAzioni2.setBounds(245,500,345,100);
        pannelloAzioni2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));          // Inizializzo il pokemon attivo con il primo della squadra, quando cambio il pokemon,
        // Bottoni del pannello azioni                                                                                                                            // cambia con il metodo setPokemon1InCampo e setPokemon2InCampo
        attaccoButton = new JButton("Attacca");                                                                                                                   pokemon1InCampo = giocatore1.getTeam().get(0);
        cambioButton = new JButton("Cambia Pokémon");                                                                                                             pokemon2InCampo = giocatore2.getTeam().get(0);
        pannelloAzioni2.add(attaccoButton);
        pannelloAzioni2.add(cambioButton);                                                                                                                         // IMMAGINE POKEMON 1  --> Di Default uso quella del primo pokemon in squadra del giocatore1
                                                                                                                                                                  pokemon1Image = new PokeImgLabel(pokemon1InCampo);
        pokemon1Image.setBounds(10,250,300,300);
        this.add(pokemon1Image);

        // IMMAGINE POKEMON 2  --> Di Default uso quella del primo pokemon in squadra del giocatore2
        pokemon2Image = new PokeImgLabel(pokemon2InCampo);
        pokemon2Image.setBounds(300,100,300,300);
        this.add(pokemon2Image);

        //PANNELLO INFO POKEMON 1
        poke1InfoPanel=new PokeBattleInfoPanel(pokemon1InCampo);
        poke1InfoPanel.setBounds(350,380,200,100);
        this.add(poke1InfoPanel);

        //PANNELLO INFO POKEMON 2
        poke2InfoPanel=new PokeBattleInfoPanel(pokemon2InCampo);
        poke2InfoPanel.setBounds(50,80,200,100);
        this.add(poke2InfoPanel);

        // Pannello per lo stato della battaglia
        statoBattaglia = new JLabel("Scegli un'azione per " + (giocatoreDiTurno==1?pokemon1InCampo.getName():pokemon2InCampo.getName()));
        JPanel pannelloStato = new JPanel(new BorderLayout());
        pannelloStato.setBounds(10,500,230,100);
        pannelloStato.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
        pannelloStato.add(statoBattaglia);


        
        /*    SottoPannelli del pannelloAzioni   */
        // SottoPannelli Giocatore 1
        this.pannelloMosse1 = new PannelloMosse(pokemon1InCampo);
        this.pannelloCambio1 = new PannelloCambioPokemon(giocatore1);
        // SottoPannelli Giocatore 2
        this.pannelloMosse2 = new PannelloMosse(pokemon2InCampo);
        this.pannelloCambio2 = new PannelloCambioPokemon(giocatore2);


        // Inizializzo e posiziono il Pannello del Punteggio
        scorePanel = new ScoreOfBattles(giocatore1, giocatore2);
        scorePanel.setBounds(375,35,200,30);                // Posizionato nel frame BattagliaGUI
        add(scorePanel);                                                        // lo aggiungo alla BattagliaGUI

        
        // CardLayout per muovermi.
        cardLayout1 = new CardLayout();
        panPrincAzi1.setLayout(cardLayout1);
        cardLayout2 = new CardLayout();
        panPrincAzi2.setLayout(cardLayout2);
        
        // Aggiungo i sotto pannelli
        // Sistemo il CardLayout e aggiungo i pannelli
        panPrincAzi1.setBounds(245,500,345,100);
        panPrincAzi1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
        // Aggiungo i CardPanel
        panPrincAzi1.add(pannelloAzioni1, "Azioni1");
        panPrincAzi1.add(pannelloMosse1, "Mosse1");
        panPrincAzi1.add(pannelloCambio1, "Cambio1");
                                                                                                                
        // Sistemo il CardLayout e aggiungo i pannelli
        panPrincAzi2.setBounds(245,500,345,100);
        panPrincAzi2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
        // Aggiungo i CardPanel
        panPrincAzi2.add(pannelloAzioni2, "Azioni2");
        panPrincAzi2.add(pannelloMosse2, "Mosse2");
        panPrincAzi2.add(pannelloCambio2, "Cambio2");

        // All'inizio viene visualizzato quello del player1
        panPrincAzi1.setVisible(true);
        panPrincAzi2.setVisible(false);

        // Metto di default che si vede il pannello delle azioni
        resettaPannello1();
        resettaPannello2();

        // Aggiungo tutto ciò che è necessario al frame principale
        add(pannelloStato);
        add(panPrincAzi1);
        add(panPrincAzi2);
        add(wallpaper);

        /*
        ESEMPIO DI COME CAMBIARE I PANNELLI

            // Cambia al pannello "Mosse1" nel panPrincAzi1
            cambiaPannello(panPrincAzi1, "Mosse1");

            // Cambia al pannello "Cambio2" nel panPrincAzi2
            cambiaPannello(panPrincAzi2, "Cambio2");
            
        */

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     // in questo modo, quando premo x chiuderò anche la pagina      
        setVisible(true);



    }  // FINE COSTRUTTORE

    // Metodo per cambiare il pannello all'interno dell'area di scelta azione del pokeAllenatore
    // Metodo per cambiare i pannelli

    private void cambiaPannello(JPanel cardPanel, String nomePannello) {
        CardLayout cl = (CardLayout) (cardPanel.getLayout());
        cl.show(cardPanel, nomePannello);
    }

    private void resettaPannello1() {
        cardLayout1.show(panPrincAzi1, "Azioni1");
    }

    public void resettaPannello2() {
            cardLayout2.show(panPrincAzi2, "Azioni2");
    }

    // GETTERS E SETTERS DEI POKEMON IN CAMPO
    public Pokemon getPokemon1InCampo() {
        return pokemon1InCampo;
    }
    public void setPokemon1InCampo(Pokemon pokemon1InCampo) {
        this.pokemon1InCampo = pokemon1InCampo;
    }
    public Pokemon getPokemon2InCampo() {
        return pokemon2InCampo;
    }
    public void setPokemon2InCampo(Pokemon pokemon2InCampo) {
        this.pokemon2InCampo = pokemon2InCampo;
    }
     // SETTERS DELL'IMMAGINE DEL POKEMON E DELL'INFOPANEL DEL POKEMON ATTIVO IN BATTAGLIA
    public void setPoke1InfoPanel(PokeBattleInfoPanel poke1InfoPanel) {
        this.poke1InfoPanel = poke1InfoPanel;
    }
    public void setPoke2InfoPanel(PokeBattleInfoPanel poke2InfoPanel) {
        this.poke2InfoPanel = poke2InfoPanel;
    }
    public void setPokemon1Image(PokeImgLabel pokemon1Image) {
        this.pokemon1Image = pokemon1Image;
    }
    public void setPokemon2Image(PokeImgLabel pokemon2Image) {
        this.pokemon2Image = pokemon2Image;
    }




    // MAIN PROVA

    public static void main(String[] args) {
        Pokedex pokedex = new Pokedex();
        Pokedex pokedex2 = new Pokedex();
        Player hash = new Player("Hash",0,0,"Male");
        Player red = new Player("Red",0,0,"Male");


        Image img = null;
        try {
            img = ImageIO.read(new File("src/Img/maleTrainer.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Image dimg = img.getScaledInstance(60, 60,
                Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);

        hash.setImage(imageIcon);
        red.setImage(imageIcon);

        // Per adesso faccio questo ciclo solo per vedere il pannello con le mosse che deve riempire
        for(Pokemon pokem : pokedex.getPokedex()){
            pokem.addMove(new Action());
            pokem.addMove(new Frustration());
            pokem.addMove(new GigaImpact());
            pokem.addMove(new BodySlam());


            hash.addPokemon(pokem);
        }
        for(Pokemon pokem : pokedex2.getPokedex()){
            pokem.addMove(new Action());
            pokem.addMove(new Frustration());
            pokem.addMove(new GigaImpact());
            pokem.addMove(new BodySlam());


            red.addPokemon(pokem);
        }

            BattleView battle = new BattleView(red,hash);
            battle.setVisible(true);

            System.out.println(red.pokemonStringList());
    }
}