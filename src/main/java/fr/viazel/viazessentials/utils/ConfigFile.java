package fr.viazel.viazessentials.utils;

import fr.viazel.viazessentials.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigFile {
    private final FileConfiguration fileConfiguration;
    private final Map<String, String> messages;

    public ConfigFile() {
        fileConfiguration = Main.getInstance().getConfig();
        messages = new HashMap<>();

        fileConfiguration.getConfigurationSection("messages").getValues(false).forEach((s, o) -> {
            messages.put(s, o.toString());
        });
    }

    public String getMessages(String message) {
        if(messages.get(message) == null) {
            error("The message " + message + " not exist");
            return "Error";
        }
        return messages.get(message);
    }

    public void setSpawnLocation(Location l) {
        fileConfiguration.set("spawn.x", l.getBlockX());
        fileConfiguration.set("spawn.y", l.getBlockY());
        fileConfiguration.set("spawn.z", l.getBlockZ());
        fileConfiguration.set("spawn.yaw", l.getYaw());
        fileConfiguration.set("spawn.pitch", l.getPitch());
        fileConfiguration.set("spawn.world", l.getWorld().getName());

        save();
    }

    public Location getSpawn() {
        int x = fileConfiguration.getInt("spawn.x");
        int y = fileConfiguration.getInt("spawn.y");
        int z = fileConfiguration.getInt("spawn.z");
        float yaw = (float) fileConfiguration.getDouble("spawn.yaw");
        float pitch = (float) fileConfiguration.getDouble("spawn.pitch");
        World world = Bukkit.getWorld(fileConfiguration.getString("spawn.world"));

        return new Location(world, x, y, z, yaw, pitch);
    }

    public boolean getUseSql() {
        return fileConfiguration.getBoolean("sql.useSQL");
    }
    public String getHost() {
        return fileConfiguration.getString("sql.host");
    }
    public String getTableName() {
        return fileConfiguration.getString("sql.tablename");
    }
    public String getPassword() {
        return fileConfiguration.getString("sql.password");
    }
    public String getUser() {
        return fileConfiguration.getString("sql.user");
    }
    public String getDbName() {
        return fileConfiguration.getString("sql.dbname");
    }
    public int getPort() {
        return fileConfiguration.getInt("sql.port");
    }

    private void error(String message) {
        try {
            throw new Exception(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void save() {
        try {
            fileConfiguration.save(new File(Main.getInstance().getDataFolder(), "config.yml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}