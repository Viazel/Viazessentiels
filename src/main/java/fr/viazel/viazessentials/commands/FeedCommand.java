package fr.viazel.viazessentials.commands;

import fr.viazel.viazessentials.Main;
import fr.viazel.viazessentials.utils.ConfigFile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) {
            Main.getInstance().getLogger().severe("You have to be in the game to execute this command !");
            return false;
        }

        Player p = (Player) sender;

        if(!p.hasPermission("viazessentials.feed")) {
            p.sendMessage(new ConfigFile().getMessages("permission-denied").replace("{command}", "feed"));
            return false;
        }

        p.setFoodLevel(20);
        p.setSaturation(20);

        p.sendMessage(new ConfigFile().getMessages("feed"));

        return false;
    }
}
