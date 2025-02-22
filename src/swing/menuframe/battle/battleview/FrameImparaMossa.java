package swing.menuframe.battle.battleview;

import moves.Move;
import moves.fire.FireFang;
import pokemon.Pokemon;
import pokemon.pokemonClass.Charmender;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Classe per il pannello di sfondo
class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String filePath) {
        try {
            backgroundImage = new ImageIcon(filePath).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

public class FrameImparaMossa extends JDialog {

    private Pokemon pokemonInAttacco;
    private Move mossaDaImparare;
    private Move mossaDaCambiare;
    MoveButton bottoneMossa;

    public FrameImparaMossa(JFrame parent, Pokemon pokemonImparaMossa, Move mosaDaImparare) {
        super(parent, true); // Imposta la modalità del dialogo
        this.pokemonInAttacco = pokemonImparaMossa;
        this.mossaDaImparare = mosaDaImparare;

        // Dimensioni Frame Battaglia
        setSize(450, 350); // larghezza e altezza
        setLayout(null); // using no layout managers
        setLocationRelativeTo(null); // centro dello schermo
        setResizable(false);

        // Aggiungo il pannello di sfondo
        BackgroundPanel backgroundPanel = new BackgroundPanel("src/Img/sfondo_cambio_mossa.jpg");
        backgroundPanel.setLayout(null);
        backgroundPanel.setBounds(0, 0, 450, 350);
        add(backgroundPanel);

        // Parte superiore: scritta del nome della mossa da imparare con bordo
        JPanel pannelloNuovaMossa = new JPanel();
        pannelloNuovaMossa.setBounds(10, 10, 430, 50);
        Border bordoNuovaMossa = BorderFactory.createTitledBorder("New Move");
        pannelloNuovaMossa.setBorder(bordoNuovaMossa);
        pannelloNuovaMossa.setOpaque(false); // Imposta il pannello come trasparente
        JLabel nomeMossaLabel = new JLabel("You want to make " + pokemonInAttacco.getName() + " learn the move: " + mossaDaImparare.getName(), SwingConstants.CENTER);
        nomeMossaLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Imposta la dimensione del testo
        nomeMossaLabel.setForeground(Color.WHITE); // Imposta il colore del testo in bianco
        pannelloNuovaMossa.add(nomeMossaLabel);
        backgroundPanel.add(pannelloNuovaMossa);

        // Parte centrale-bassa: GridLayout con bordo
        JPanel pannelloBottoniMossa = new JPanel(new GridLayout(2, 2, 2, 2));
        pannelloBottoniMossa.setBounds(25, 75, 225, 200);
        Border bordo = BorderFactory.createTitledBorder("Choose the move to replace");
        ((javax.swing.border.TitledBorder) bordo).setTitleColor(Color.WHITE); // Imposta il colore del titolo del bordo in bianco
        pannelloBottoniMossa.setBorder(bordo);
        pannelloBottoniMossa.setOpaque(false); // Imposta il pannello come trasparente

        for (Move mossa : pokemonInAttacco.getMoves()) {
            MoveButton bottoneMossa = new MoveButton(mossa); // Crea un nuovo bottone per ogni mossa
            bottoneMossa.setForeground(Color.BLACK);

            // Crea un ActionListener specifico per ogni bottone
            bottoneMossa.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Immagine da inserire come dialogo di conferma
                    ImageIcon returnBackCustomIcon = new ImageIcon("src/Img/backToMenu.png");
                    // Inserisco il dialogo di conferma
                    int risposta = JOptionPane.showConfirmDialog(null, "Want to change this move?", "Yes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, returnBackCustomIcon);
                    if (risposta == JOptionPane.YES_OPTION) {
                        // Imposta la mossa da cambiare e chiudi il dialogo
                        setMossaDaCambiare(bottoneMossa.getMove());
                        System.out.println("The move I decided to change is: " + mossaDaCambiare);
                        dispose();  // Chiudi il frame dopo la conferma di risposta
                    } else {
                        System.out.println("I haven't decided yet..."); // Azione se l'utente sceglie "No"
                    }
                }
            });

            // Aggiungi il bottone mossa al pannello
            pannelloBottoniMossa.add(bottoneMossa);
        }


        // Aggiungo il pannello al frame
        backgroundPanel.add(pannelloBottoniMossa);

        // Parte destra: immagine e nome del Pokémon
        ImageIcon icon = new ImageIcon(pokemonInAttacco.getImagePath());
        Image image = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel immaginePokemonLabel = new JLabel(new ImageIcon(image));

        immaginePokemonLabel.setBounds(265, 100, 150, 150); // Posizione e dimensioni dell'immagine
        JLabel nomePokemonLabel = new JLabel(pokemonInAttacco.getName(), SwingConstants.CENTER);
        nomePokemonLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Aumenta la dimensione del testo
        nomePokemonLabel.setBounds(215, 200, 250, 130);
        nomePokemonLabel.setForeground(Color.WHITE); // Imposta il colore del testo in bianco
        backgroundPanel.add(immaginePokemonLabel);
        backgroundPanel.add(nomePokemonLabel);


        // Imposto il comportamento di chiusura della finestra --> ritorno null quando chiudo la finestra senza cambiare la mossa
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setMossaDaCambiare(null);
                dispose();
            }
        });

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // in questo modo, quando premo x chiuderò anche la pagina
        setVisible(true);
    }

    public Move returnMossaDaCambiare() {
        return this.mossaDaCambiare;
    }

    public void setMossaDaCambiare(Move mossaDaCambiare) {
        this.mossaDaCambiare = mossaDaCambiare;
    }



}           // FINE CLASSE FRAME
