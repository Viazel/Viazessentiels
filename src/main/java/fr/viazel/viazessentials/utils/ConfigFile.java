package fr.viazel.viazessentials.utils;

import fr.viazel.viazessentials.Main;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigFile {
    private final FileConfiguration fileConfiguration;
    private final File file;
    private final Map<String, String> messages;

    public ConfigFile() {
        file = new File(Main.getInstance().getDataFolder(),"config.yml");
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

    private void error(String message) {
        try {
            throw new Exception(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void save() {
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}