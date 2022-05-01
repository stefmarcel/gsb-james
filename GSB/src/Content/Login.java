package Content;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

// La class Login est enfant de JFrame
public class Login extends JFrame{
    private JButton okButton;
    private JTextField idField;
    private JPasswordField mdpField;
    private JButton quitterButton;
    public JPanel loginPan;
    private JLabel errorMessage;
    private ResultSet data;
    private String id;
    private String mdp;

    public Login() {
        super("Login"); // Le nom de la fenêtre
        setSize(640,480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // DISPOSE_ON_CLOSE : Cette méthode est utilisée pour fermer la trame courante.

        setContentPane(loginPan);
        pack(); // agencer  vue.
        setVisible(true); // fait en sorte que la fenêtre login soit toujours ouverte à moins qu'on la ferme.

        quitterButton.addActionListener(new ActionListener() { //  Bouton quitter
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        okButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mdp = String.valueOf(mdpField.getPassword());
                    id = idField.getText();

                    if (isValidUser(id, mdp)) { // Verifier connexion
                        new Menu(); // génèrer menu
                        remove(); // fermer
                    }
                    else{
                        errorMessage.setVisible(true); // rend visible msg erreur
                        errorMessage.setText("identifiant ou mot de passe invalide"); //msg erreur
                    }
                }
                catch (SQLException | ParseException ex) { // attrape exception
                    ex.printStackTrace();
                    errorMessage.setVisible(true); // rend visible msg erreur
                    errorMessage.setText("identifiant ou mot de passe invalide"); //msg erreur
                }
            }

            private static boolean isValidUser(String id, String mdp)
                    throws ParseException, SQLException {
                java.util.Date date = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH).parse(mdp); //mdp rentré pour se coonecter
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd"); // mdp dans la bdd
                mdp = formater.format(date); // transforme le mdp
                System.out.println("Date d'embauche du visiteur : "+ mdp);
                Connect con = new Connect();
                con.connect(); // permet connexion à la bdd

                String query = "SELECT count(*) FROM visiteur WHERE VIS_NOM='" + id + "' AND VIS_DATEEMBAUCHE='" + mdp + "'";
                //System.out.println(query); // verif si on a un acces

                ResultSet ResultRequest = con.requete(query); // resultat de la query
                ResultRequest.next(); // initiaisation des  données
                boolean isValidUser = ResultRequest.getInt(1) > 0;
                System.out.println("result = "+ isValidUser);
                System.out.println(); // sur le terminal
                return isValidUser;
            }
        });
    }
    public void remove() {
        this.dispose();
    }

}
