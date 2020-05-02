package com.relatev.minecraft.antipayloadhax;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {

    private final File ConfigFile;
    public final YamlConfiguration config;
    public final String BoardcastMessage;
    public final String DenyMessage;

    public ConfigManager() {
        ConfigFile = new File(AntiPayloadHax.MainPlugin.getDataFolder(), "config.yml");
        if (ConfigFile.exists() == false) {
            ConfigFile.getParentFile().mkdirs();
            try {
                ConfigFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(ConfigManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        config = YamlConfiguration.loadConfiguration(ConfigFile);
        if (config.getInt("Version") != Versioning.Version) {
            config.set("Version", Versioning.Version);
            config.set("AutoUpdate", true);
            config.set("Prefix", "&a[&bAntiPayloadHax&a]");
            config.set("BoardcastMessage", "&r玩家%Player%&c尝试使用%Mod%的漏洞作弊,已被拦截!");
            config.set("DenyMessage", "&c抱歉!由于存在Bug,此操作在服务器被禁用!");
            try {
                config.save(ConfigFile);
            } catch (IOException ex) {
                Logger.getLogger(ConfigManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        BoardcastMessage = (config.getString("Prefix") + config.getString("BoardcastMessage")).replace("&", "§");
        DenyMessage = config.getString("DenyMessage").replace("&", "§");
    }
}
