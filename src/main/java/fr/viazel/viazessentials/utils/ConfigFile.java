package fr.viazel.viazessentials.utils;

import fr.viazel.viazessentials.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ConfigFile {
    private final FileConfiguration fileConfiguration;
    private final FileConfiguration fileSpawnConfiguration;
    private final Map<String, String> messages;
    private final File spawnFile;
    public ConfigFile() {
        fileConfiguration = Main.getInstance().getConfig();
        spawnFile = new File(Main.getInstance().getDataFolder(), "spawn.yml");
        fileSpawnConfiguration = YamlConfiguration.loadConfiguration(spawnFile);
        if(!spawnFile.exists()) {
            try {
                spawnFile.createNewFile();
                setSpawnLocation(new Location(Bukkit.getWorld("world"), 0, 0, 0));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        messages = new HashMap<>();

        fileConfiguration.getConfigurationSection("messages").getValues(false).forEach((s, o) -> {
            byte b[] = o.toString().getBytes();
            String result = null;
            try {
                result = new String(b, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            messages.put(s, result.replace('&', '§'));
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
        fileSpawnConfiguration.set("spawn.x", l.getBlockX());
        fileSpawnConfiguration.set("spawn.y", l.getBlockY());
        fileSpawnConfiguration.set("spawn.z", l.getBlockZ());
        fileSpawnConfiguration.set("spawn.yaw", l.getYaw());
        fileSpawnConfiguration.set("spawn.pitch", l.getPitch());
        fileSpawnConfiguration.set("spawn.world", l.getWorld().getName());

        saveSpawn();
    }

    public Location getSpawn() {

        int x = fileSpawnConfiguration.getInt("spawn.x");
        int y = fileSpawnConfiguration.getInt("spawn.y");
        int z = fileSpawnConfiguration.getInt("spawn.z");
        float yaw = (float) fileSpawnConfiguration.getDouble("spawn.yaw");
        float pitch = (float) fileSpawnConfiguration.getDouble("spawn.pitch");
        World world = Bukkit.getWorld(fileSpawnConfiguration.getString("spawn.world"));

        return new Location(world, x, y, z, yaw, pitch);
    }

    public boolean getUseSql() {
        return fileConfiguration.getBoolean("useSQL");
    }
    public String getHost() {
        return fileConfiguration.getString("host");
    }
    public String getTableName() {
        return fileConfiguration.getString("tablename");
    }
    public String getPassword() {
        return fileConfiguration.getString("password");
    }
    public String getUser() {
        return fileConfiguration.getString("user");
    }
    public String getDbName() {
        return fileConfiguration.getString("dbname");
    }
    public int getPort() {
        return fileConfiguration.getInt("port");
    }

    private void error(String message) {
        try {
            throw new Exception(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void saveSpawn() {
        try {
            fileSpawnConfiguration.save(spawnFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}