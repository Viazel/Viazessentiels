package fr.viazel.viazessentials;

import fr.viazel.viazessentials.events.MainEvent;
import fr.viazel.viazessentials.utils.ConfigFile;
import fr.viazel.viazessentials.utils.sql.DataBaseManager;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;
    private ConfigFile configFile;
    private DataBaseManager dataBaseManager;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        configFile = new ConfigFile();
        PluginManager pm = getServer().getPluginManager();
        getServer().getLogger().info("The plugin is starting...s");
        pm.registerEvents(new MainEvent(this), this);
        saveFiles();
        connectionSql(configFile);
    }

    private void connectionSql(ConfigFile configFile) {

        if(!configFile.getUseSql()) return;

        this.dataBaseManager = new DataBaseManager(this.configFile);

    }

    @Override
    public void onDisable() {

        getServer().getLogger().info("The plugin is shutting down...");
    }

    public void saveFiles() {
        saveDefaultConfig();
    }
}