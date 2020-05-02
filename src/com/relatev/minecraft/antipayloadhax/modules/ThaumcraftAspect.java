package com.relatev.minecraft.antipayloadhax.modules;

import com.relatev.minecraft.antipayloadhax.AntiPayloadHax;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ThaumcraftAspect extends PayloadModuel {

    private Map<Player, Long> TimeMap = new HashMap();

    public ThaumcraftAspect() {
        super("thaumcraft", (byte) 14);
        AntiPayloadHax.MainPlugin.getLogger().info(this.getClass().getSimpleName() + " 防御系统启动中!");
        if (AntiPayloadHax.MainPlugin.classNameMap != null && AntiPayloadHax.MainPlugin.classNameMap.containsKey("buildcraft.core.lib.network.PacketHandler")) {
            AntiPayloadHax.MainPlugin.getLogger().info(this.getClass().getSimpleName() + " 现在由AnotherCommonBugFix接手!");
        } else {
            AntiPayloadHax.MainPlugin.getLogger().info(this.getClass().getSimpleName() + " 防御系统已启用!");
            this.enable();
        }
    }

    @Override
    public boolean onReceivePayload(Player player, byte[] array) {
        if (TimeMap.containsKey(player) && System.currentTimeMillis() - TimeMap.get(player) < 100) {
            Bukkit.broadcastMessage(AntiPayloadHax.MainPlugin.configManager.BoardcastMessage.replaceAll("%Player%", player.getName()).replaceAll("%Mod%", this.getClass().getSimpleName()));
            return true;
        }
        TimeMap.put(player, System.currentTimeMillis());
        return false;
    }
}
