package Content;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Menu extends JFrame{
    public JPanel pany;
    private JButton comptesRendusButton;
    private JButton visiteursButton;
    private JButton praticiensButton;
    private JButton medicamentsButton;
    private JButton quitterButton;

    public Menu(){
        super("Menu");
        setSize(640,480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(pany);
        pack();
        setVisible(true);


        quitterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        comptesRendusButton.addActionListener(new ActionListener() {
            /**
             *
             * La methode actionPerformed décrit l'action se déroulant lors du clic sur le bouton.
             * Lors du clic sur le bouton "Comptes Rendus", la vue compte rendu va s'ouvrir dans une nouvelle fenêtre.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new CptRendu();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        visiteursButton.addActionListener(new ActionListener() {
            /**
             *
             * La methode actionPerformed décrit l'action se déroulant lors du clic sur le bouton.
             * Lors du clic sur le bouton "Visiteurs", la vue Visiteurs va s'ouvrir dans une nouvelle fenêtre.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new Visiteurs();
            }
        });
        praticiensButton.addActionListener(new ActionListener() {
            /**
             *
             * La methode actionPerformed décrit l'action se déroulant lors du clic sur le bouton.
             * Lors du clic sur le bouton "Visiteurs", la vue Visiteurs va s'ouvrir dans une nouvelle fenêtre.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new Praticiens();
            }
        });
        medicamentsButton.addActionListener(new ActionListener() {
            /**
             *
             * La methode actionPerformed décrit l'action se déroulant lors du clic sur le bouton.
             * Lors du clic sur le bouton "Visiteurs", la vue Visiteurs va s'ouvrir dans une nouvelle fenêtre.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new Medicaments();
            }
        });
    }

}
