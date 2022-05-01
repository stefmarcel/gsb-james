package Content;

import java.sql.*;

public class Connect {

    private String driver, db_url, user, pwd, query;
    private Connection conn;

    public Connect() {
        this.driver = "com.mysql.cj.jdbc.Driver";
        this.db_url = "jdbc:mysql://localhost/GSB";
        this.user = "root";
        this.pwd = "root";
    }

    public void setConnect(String driver, String db_url, String user, String pwd) {
        this.driver = driver;
        this.db_url = db_url;
        this.user = user;
        this.pwd = pwd;
        System.out.println(this.driver);
        System.out.println(this.db_url);
        System.out.println(this.user);
        System.out.println(this.pwd);
    }

    public void setQuery(String query) {
        this.query = query;
        System.out.println(this.query);
    }

    public Connection connect() {
        if (conn == null) {
            try {
                Class.forName(this.driver);
                this.conn = DriverManager.getConnection(this.db_url, this.user, this.pwd);
                System.out.println("Connexion base de données : OK");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public ResultSet requete(String query) {
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}