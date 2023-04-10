package fr.viazel.viazessentials;

import fr.viazel.viazessentials.commands.FeedCommand;
import fr.viazel.viazessentials.commands.SetSpawnCommand;
import fr.viazel.viazessentials.commands.SpawnCommand;
import fr.viazel.viazessentials.events.MainEvent;
import fr.viazel.viazessentials.utils.ConfigFile;
import fr.viazel.viazessentials.utils.sql.DataBaseManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.sql.Connection;

public class Main extends JavaPlugin {

    private static Main instance;
    public ConfigFile configFile;
    private DataBaseManager dataBaseManager;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        configFile = new ConfigFile();
        PluginManager pm = getServer().getPluginManager();
        getServer().getLogger().info("The plugin is starting...s");
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        pm.registerEvents(new MainEvent(this), this);
        connectionSql();
    }

    public Connection getConnection() {
        return dataBaseManager.getConnection().getConnection();
    }

    private void connectionSql() {

        if(!configFile.getUseSql()) return;

        this.dataBaseManager = new DataBaseManager(configFile);

    }

    @Override
    public void onDisable() {
        getServer().getLogger().info("The plugin is shutting down...");
        if(configFile.getUseSql())
            dataBaseManager.close();
    }
}