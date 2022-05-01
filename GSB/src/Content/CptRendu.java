package Content;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CptRendu extends JFrame {
    private JButton fermerButton;
    private JButton nouveauButton;
    private JButton suivantButton;
    private JButton precedentButton;
    private JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextArea textArea1;
    private JComboBox<Object> comboBox1;
    private JTextField textField3;
    private JButton detailsButton;
    private JPanel pan;
    private JPanel comboBoxPanel;
    private Connect con;
    private String statut;

    public CptRendu() throws SQLException {
        comboBoxPanel = new JPanel();
        setVisible(true);
        setSize(640,480);
        Connect connection = new Connect();
        connection.connect();
        fillComboBox(connection,"SELECT * FROM PRATICIEN");
        add(pan);
        /*
        pan = new JPanel();
        this.setTitle("Gestion des comptes rendus");
        this.setSize(800,600);
        Container CptRd = getContentPane();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(pan);
         */
//        this.con = new Connect();
//        return this;
        fermerButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        detailsButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        precedentButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet rs = getTablePraticien("SELECT * FROM RAPPORT_VISITE");
                try {
                    getInfoPraticien(rs,"previous");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        suivantButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet rs = getTablePraticien("SELECT * FROM RAPPORT_VISITE");
                try {
                    getInfoPraticien(rs,"next");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        detailsButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Praticiens praticiens = new Praticiens();
            }
        });
    }

    public void fillComboBox(Connect connection, String query) throws SQLException{
        ResultSet res = connection.requete(query);
        String nom, prenom, fullName ;
        ArrayList <String> listName = new ArrayList <String> ();
        while (res.next()){
            nom = res.getString("PRA_NOM");
            prenom = res.getString("PRA_PRENOM");
            fullName = nom + " " + prenom;
            listName.add(fullName);
        }
        comboBox1.setModel(new DefaultComboBoxModel<>(listName.toArray()));
    }

    public void getInfoPraticien(ResultSet rs, String statut) throws SQLException {
        if (statut =="next") {
            if (rs.next()==false) {
                rs.first();
            }
        }
        if (statut == "previous") {
            if (rs.previous()==false) {
                rs.last();
            }
        }
        textField1.setText(rs.getString("RAP_NUM"));
        textField2.setText(rs.getString("RAP_DATE"));
        textField3.setText(rs.getString("RAP_MOTIF"));
        textArea1.setText(rs.getString("RAP_BILAN"));
    }

    public ResultSet getTablePraticien(String query) {
        Connect con = new Connect();
        con.connect();

        /*
        String query = "SELECT PRA_NUM, PRA_NOM, PRA_PRENOM, PRA_ADRESSE, PRA_CP, PRA_VILLE, PRA_COEFNOTORIETE, " +
                "type_praticien.TYP_CODE AS CODE, type_praticien.TYP_LIBELLE AS LIBELLE FROM praticien " +
                "INNER JOIN type_praticien ON type_praticien.TYP_CODE = praticien.TYP_CODE";

         */
        System.out.println(query);

        ResultSet resultRequest = con.requete(query);
        return resultRequest;
    }
}
