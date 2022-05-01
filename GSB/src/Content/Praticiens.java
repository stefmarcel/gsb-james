package Content;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class Praticiens extends JFrame{
    private JButton OKButton;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JButton fermerButton;
    private JButton précédentButton;
    private JButton suivantButton;
    private JTextField textField9;
    private JComboBox comboBox1;
    private JPanel praPan;
    private ResultSet rs;
    private String statut;

    public Praticiens() {
        super("PRATICIENS");
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setContentPane(praPan);
        pack();
        setVisible(true);

        try {
            statut = "next";
            rs = getTablePraticien();
            getComboboxId(rs);
            getInfoPraticien(rs, statut);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        suivantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    statut = "next";
                    getInfoPraticien(rs, statut);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        précédentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    statut = "previous";
                    getInfoPraticien(rs, statut);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        fermerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = comboBox1.getSelectedItem().toString();
                String[] tabValues = value.split("\\s+");

                String setQuery = "SELECT PRA_NUM, PRA_NOM, PRA_PRENOM, PRA_ADRESSE, PRA_CP, PRA_VILLE, PRA_COEFNOTORIETE, " +
                        "type_praticien.TYP_CODE AS CODE, type_praticien.TYP_LIBELLE AS LIBELLE FROM praticien " +
                        "INNER JOIN type_praticien ON type_praticien.TYP_CODE = praticien.TYP_CODE WHERE PRA_NOM ='"+tabValues[0]+"' AND PRA_PRENOM = '"+tabValues[1]+"'";
                ResultSet res = getTablePraticien(setQuery);
                try {
                    res.next();
                    int row = res.getInt(1);
                    statut = "";
                    getInfoPraticien(rs, statut, row);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public ResultSet getTablePraticien() {
        Connect con = new Connect();
        con.connect();

        String query = "SELECT PRA_NUM, PRA_NOM, PRA_PRENOM, PRA_ADRESSE, PRA_CP, PRA_VILLE, PRA_COEFNOTORIETE, " +
                "type_praticien.TYP_CODE AS CODE, type_praticien.TYP_LIBELLE AS LIBELLE FROM praticien " +
                "INNER JOIN type_praticien ON type_praticien.TYP_CODE = praticien.TYP_CODE";
        System.out.println(query);

        ResultSet resultRequest = con.requete(query);
        return resultRequest;
    }
    public ResultSet getTablePraticien(String setQuery) {
        Connect con = new Connect();
        con.connect();

        String query = setQuery;
        System.out.println(query);

        ResultSet resultRequest = con.requete(query);
        return resultRequest;
    }

    private void getComboboxId(ResultSet rs) throws SQLException {
        fillComboBox(rs);
    }

    private void getInfoPraticien(ResultSet rs, String statut) throws SQLException {
        if (statut =="next") {
            System.out.println("suivant");
            if (rs.next()==false) {
                rs.first();
            }
        }
        if (statut == "previous") {
            System.out.println("précédent");
            if (rs.previous()==false) {
                rs.last();
            }
        }


        textField2.setText(rs.getString("PRA_NUM"));
        textField3.setText(rs.getString("PRA_NOM"));
        textField4.setText(rs.getString("PRA_PRENOM"));
        textField5.setText(rs.getString("PRA_ADRESSE"));
        textField6.setText(rs.getString("PRA_CP"));
        textField9.setText(rs.getString("PRA_VILLE"));
        textField7.setText(rs.getString("PRA_COEFNOTORIETE"));
        textField8.setText(rs.getString("LIBELLE"));
    }
    private void getInfoPraticien(ResultSet rs, String statut, int row) throws SQLException {
        if (statut =="next") {
            System.out.println("suivant");
            if (rs.next()==false) {
                rs.first();
            }
        }
        if (statut == "previous") {
            System.out.println("précédent");
            if (rs.previous()==false) {
                rs.last();
            }
        }
        else
            rs.absolute(row);
        System.out.println(rs.getString("PRA_NUM"));


        textField2.setText(rs.getString("PRA_NUM"));
        textField3.setText(rs.getString("PRA_NOM"));
        textField4.setText(rs.getString("PRA_PRENOM"));
        textField5.setText(rs.getString("PRA_ADRESSE"));
        textField6.setText(rs.getString("PRA_CP"));
        textField9.setText(rs.getString("PRA_VILLE"));
        textField7.setText(rs.getString("PRA_COEFNOTORIETE"));
        textField8.setText(rs.getString("LIBELLE"));
    }
    public void fillComboBox(ResultSet rs) throws SQLException{
        ArrayList<String> listName = new ArrayList<String>();

        while (rs.next()){
            String nom = rs.getString("PRA_NOM");
            String prenom = rs.getString("PRA_PRENOM");
            String fullName = nom + " " + prenom;
            listName.add(fullName);
        }
        comboBox1.setModel(new DefaultComboBoxModel<>(listName.toArray()));
    }
}
