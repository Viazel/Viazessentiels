package fr.viazel.viazessentials.events;

import fr.viazel.viazessentials.Main;
import fr.viazel.viazessentials.utils.ConfigFile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class MainEvent implements Listener {

    private final Main instance;

    public MainEvent(Main main) {
        instance = main;
    }

    @EventHandler
    public void event(PlayerJoinEvent e) {
        e.getPlayer().sendMessage(new ConfigFile().getMessages("welcome"));
    }
}