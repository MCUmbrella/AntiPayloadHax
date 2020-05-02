package com.relatev.minecraft.antipayloadhax;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Networker
        implements Runnable, Listener {
    
    private static Networker Instance;
    public static boolean donotupdate = false;

    public static void init() {
        Instance = new Networker();
        if (donotupdate == false) {
            Bukkit.getScheduler().runTaskAsynchronously(AntiPayloadHax.MainPlugin, Instance);
        }
        Bukkit.getPluginManager().registerEvents(Instance, AntiPayloadHax.MainPlugin);
    }

    @Override
    public void run() {
        try {
            //整体获取
            File URLLogFile = new File(AntiPayloadHax.MainPlugin.getDataFolder(), "URLLog");
            Networker.DowloadFile("http://urllog.relatev.com/files/AntiPayloadHax/NetWorker.yml", URLLogFile);
            YamlConfiguration URLLog = YamlConfiguration.loadConfiguration(URLLogFile);
            //检查插件并下载新版本
            if (AntiPayloadHax.MainPlugin.configManager.config.getBoolean("AutoUpdate") == true) {
                AntiPayloadHax.MainPlugin.getLogger().info("正在检查新版本插件，请稍等...");
                int NewVersion = URLLog.getInt("UpdateVersion");
                int NowVersion = Versioning.Version;
                if (NewVersion > NowVersion) {
                    AntiPayloadHax.MainPlugin.getLogger().info("插件检测到新版本 " + NewVersion + " 正在下载更新...");
                    Networker.DowloadFile("http://urllog.relatev.com/files/AntiPayloadHax/AntiPayloadHax.jar", AntiPayloadHax.MainPlugin.PluginFile);
                    AntiPayloadHax.MainPlugin.getLogger().info("插件更新版本下载完成！正在重启服务器！");
                    Bukkit.shutdown();
                } else {
                    AntiPayloadHax.MainPlugin.getLogger().info("APH插件工作良好，暂无新版本检测更新。");
                }
            }
            //完成提示
            AntiPayloadHax.MainPlugin.getLogger().info("全部网络工作都读取完毕了...");
        } catch (IOException ex) {
            AntiPayloadHax.MainPlugin.getLogger().info("插件在更新时出现错误!");
            Logger.getLogger(Networker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void DowloadFile(String urlStr, File savefile) throws IOException {
        if (savefile.exists() == true) {
            savefile.delete();
        }
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置超时间为3秒  
        conn.setConnectTimeout(3 * 1000);
        //防止屏蔽程序抓取而返回403错误  
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //得到输入流  
        InputStream inputStream = conn.getInputStream();
        //获取自己数组  
        byte[] getData = readInputStream(inputStream);

        //文件保存位置  
        File file = savefile;
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if (fos != null) {
            fos.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }
    }

    private static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    @EventHandler
    public void CheckUpdateOn(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.isOp()) {
            if (AntiPayloadHax.MainPlugin.configManager.config.getBoolean("AutoUpdate") == false) {
                player.sendMessage("§a[AntiPayloadHax]§b提示：§cAntiPayloadHax 插件的自动更新没有打开，这会影响您的服务器安全。");
                player.sendMessage("§c请在配置文件中打开自动更新，否则新漏洞出现你难以及时跟上修复！");
            }
        }
    }
}
