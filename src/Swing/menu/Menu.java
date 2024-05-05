package Swing.menu;

import Swing.ChooseTeam;
import players.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu extends JFrame {

        private JPanel gameSelect;

        public Menu() {
            //creating instance of JFrame
            setSize(600,650);//400 width and 500 height
            gameSelect = new JPanel();
            gameSelect.setLayout(null);
//            gameSelect.setLayout(new BorderLayout(BorderLayout.CENTER));

//            setLayout();

            //using no layout managers
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File("src/Img/wallpaper.jpg"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Image dimg = img.getScaledInstance(getWidth(), getHeight(),
                    Image.SCALE_SMOOTH);

            ImageIcon imageIcon = new ImageIcon(dimg);

            JLabel wallpaper= new JLabel("",imageIcon,JLabel.CENTER);
            wallpaper.setBounds(0,0,600,650);


            JButton startButton=new JButton("Nuova Partita");//creating instance of JButton
            startButton.setBounds(115,350,350,85);//x axis, y axis, width, height
            startButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.setRootFrame(new ChooseTeam(new Player("Hash",0,0,"Male")));
                    setVisible(false);
                }
            });


            JButton continueButton=new JButton("Continua Partita");//creating instance of JButton
            continueButton.setBounds(115,250,350,85);//x axis, y axis, width, height
            continueButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.setRootFrame(new ChooseTeam(new Player("Hash",0,0,"Male")));
                    setVisible(false);
                }
            });



            //Setting up the container ready for the components to be added.
            Container pane = getContentPane();
            setContentPane(pane);
            //Adding the JPanel to the container
            pane.add(gameSelect);

            // Set color background of the buttons
            startButton.setBackground(Color.GREEN);
            startButton.setBorder(BorderFactory.createLineBorder(Color.blue)); // Simple Line Border
            continueButton.setBackground(Color.BLUE);
            continueButton.setBorder(BorderFactory.createLineBorder(Color.yellow)); // Simple Line Border

            // Add the button on the Frame
            gameSelect.add(startButton); //adding button in JFrame
            gameSelect.add(continueButton); //adding button in JFrame

            setVisible(true);//making the frame visible
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     // in questo modo, quando premo x chiuderò anche la pagina
        }


    }
