package fr.viazel.viazessentials.utils.sql;

import fr.viazel.viazessentials.Main;
import fr.viazel.viazessentials.utils.ConfigFile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseManager {
    private DbConnection connection;
    private ConfigFile configFile;

    public DataBaseManager(ConfigFile configFile) {
        this.configFile = configFile;
        this.connection = new DbConnection(new DbCredentials(configFile.getHost(), configFile.getUser(), configFile.getPassword(), configFile.getDbName(), configFile.getPort()));
        main(connection.getConnection());
    }

    private void info(String message) {
        Main.getInstance().getLogger().info(message);
    }

    public void main(Connection connection) {

        try {
            ResultSet rs = connection.getMetaData().getTables(null, null, configFile.getTableName(), null);
            if(!rs.next()) {
                connection.createStatement().executeUpdate("CREATE TABLE " + configFile.getTableName() + "( id VARCHAR(255) PRIMARY KEY NOT NULL, nom VARCHAR(100) )");
                info("The table " + configFile.getTableName() + " has created");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void close(){
        connection.close();
    }

    public DbConnection getConnection() {
        return connection;
    }
}