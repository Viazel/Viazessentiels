package fr.viazel.viazessentials.commands;

import fr.viazel.viazessentials.Main;
import fr.viazel.viazessentials.utils.ConfigFile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    private ConfigFile configFile;

    public SetSpawnCommand(ConfigFile configFile) {
        this.configFile = configFile;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) {
            Main.getInstance().getLogger().severe("You have to be in the game to execute this command !");
            return false;
        }

        Player p = (Player) sender;

        configFile.setSpawnLocation(p.getLocation());
        p.sendMessage("Vous avez set le spawn location !");

        return false;
    }
}
