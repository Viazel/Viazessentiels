package fr.viazel.viazessentials;

import fr.viazel.viazessentials.events.MainEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        PluginManager pm = getServer().getPluginManager();
        getServer().getLogger().info("The plugin is starting...s");
        pm.registerEvents(new MainEvent(this), this);
        saveFiles();
    }

    @Override
    public void onDisable() {

        getServer().getLogger().info("The plugin is shutting down...");
    }

    public void saveFiles() {
        saveDefaultConfig();
    }
}