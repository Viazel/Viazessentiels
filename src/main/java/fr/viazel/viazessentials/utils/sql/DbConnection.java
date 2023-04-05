package fr.viazel.viazessentials.utils.sql;

import fr.viazel.viazessentials.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private DbCredentials dbCredentials;
    private Connection connection;

    public DbConnection(DbCredentials dbCredentials) {
        this.dbCredentials = dbCredentials;
        connect();
    }

    private void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(dbCredentials.toURI(), dbCredentials.getUser(), dbCredentials.getPass());
            Main.getInstance().getLogger().info("Connexion réussie ! (" + dbCredentials.getDbname() + ")");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        if (this.connection != null){
            try {
                if (!this.connection.isClosed()){
                    this.connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection(){
        if (this.connection != null){
            try {
                if (!this.connection.isClosed()){
                    return this.connection;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        connect();
        return this.connection;
    }
}
