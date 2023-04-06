package fr.viazel.viazessentials.utils;

import fr.viazel.viazessentials.Main;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerServer {

    private Player p;
    private Connection connection;
    private ConfigFile configFile;

    public PlayerServer(Player p) {
        this.configFile = new ConfigFile();
        if(!configFile.getUseSql())
            throw new Error("You must turn true useSQL !!");
        this.p = p;
        this.connection = Main.getInstance().getConnection();
        if(!isOnDb())
            createAccount();
    }

    private void createAccount() {
        try {
            connection.createStatement().executeUpdate("INSERT INTO " + configFile.getTableName() + " VALUES('" + p.getUniqueId() + "', '" + p.getName() + "')");
            p.sendMessage("Account created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isOnDb() {
        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT nom FROM " + configFile.getTableName() + " WHERE ID = '" + p.getUniqueId() + "'");
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
