package fr.viazel.viazessentials.commands;

import fr.viazel.viazessentials.Main;
import fr.viazel.viazessentials.utils.ConfigFile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) {
            Main.getInstance().getLogger().severe("You have to be in the game to execute this command !");
            return false;
        }

        Player p = (Player) sender;
        ConfigFile configFile = new ConfigFile();

        p.teleport(configFile.getSpawn());
        p.sendMessage("Vous avez été téléporté au spawn du serveur !");

        return false;
    }
}
