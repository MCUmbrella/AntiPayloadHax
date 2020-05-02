package com.relatev.minecraft.antipayloadhax;

import com.anotherera.core.ACBFClassTransformer;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.relatev.minecraft.antipayloadhax.modules.PayloadModuel;
import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiPayloadHax extends JavaPlugin {

    public ProtocolManager PLManager;
    public static AntiPayloadHax MainPlugin;
    public ConfigManager configManager;
    public File PluginFile;
    public HashMap<String, String> classNameMap;

    @Override
    public void onEnable() {
        MainPlugin = this;
        PluginFile = this.getFile();
        getLogger().info("|作者缇亚祢——Q群664015345|");
        configManager = new ConfigManager();
        hookToPL();
        hookToAnother();
        PayloadModuel.init();

        if (Bukkit.getVersion().contains("1.7.10")) {
            getLogger().info("你的服务器版本被设定为 1.7.10,现在开始加载1.7.10的防御模块");
            V1_7_10Engine.init();
        } else {
            getLogger().info("你的服务器版本被设定为 其他,现在开始加载其他版本的防御模块");
            MoreEngine.init();
        }

        Networker.init();
    }

    private void hookToAnother() {
        try {
            Field field = ACBFClassTransformer.class.getDeclaredField("classNameMap");
            field.setAccessible(true);
            classNameMap = (HashMap<String, String>) field.get(ACBFClassTransformer.class);
            System.out.println("挂钩到ACBF的Map: " + classNameMap);
        } catch (Throwable ex) {
            getLogger().info("§a§l[" + this.getClass().getSimpleName() + "]错误！AnotherCommonBugFix Mod不存在？");
            getLogger().info("§a§l[" + this.getClass().getSimpleName() + "]强烈建议安装此Mod!!可以修复一大批Bug!");
            getLogger().info("§a§l[" + this.getClass().getSimpleName() + "]§c§l你的服务器十分危险！！！");
            getLogger().info("§4请下载一个AnotherCommonBugFix Mod或者询问作者QQ：1207223090");
        }
    }

    private void hookToPL() {
        Plugin protocolLibplugin = Bukkit.getPluginManager().getPlugin("ProtocolLib");
        if (protocolLibplugin == null) {
            Bukkit.getScheduler().runTaskTimer(MainPlugin, () -> {
                Bukkit.broadcastMessage("§a§l[" + this.getClass().getSimpleName() + "]错误！ProtocolLib前置插件不存在？");
                Bukkit.broadcastMessage("§a§l[" + this.getClass().getSimpleName() + "]错误！该插件不会正常工作！请修复！");
                Bukkit.broadcastMessage("§4请下载一个ProtocolLib插件或者询问作者QQ：1207223090");
            }, 0, 5 * 20);
        } else {
            PLManager = ProtocolLibrary.getProtocolManager();
        }
    }
}
