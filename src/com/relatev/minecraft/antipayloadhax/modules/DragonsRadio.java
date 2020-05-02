package com.relatev.minecraft.antipayloadhax.modules;

import com.relatev.minecraft.antipayloadhax.AntiPayloadHax;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class DragonsRadio extends PayloadModuel {

    public DragonsRadio() {
        super("DragonsRadioMod", (byte) 0);
        AntiPayloadHax.MainPlugin.getLogger().info(this.getClass().getSimpleName() + " 防御系统已启用!");
        this.enable();
    }

    private Map<Player, Long> TimeMap = new HashMap();

    @Override
    public boolean onReceivePayload(Player player, byte[] array) {
        if (TimeMap.containsKey(player) && System.currentTimeMillis() - TimeMap.get(player) < 1000) {
            Bukkit.broadcastMessage(AntiPayloadHax.MainPlugin.configManager.BoardcastMessage.replaceAll("%Player%", player.getName()).replaceAll("%Mod%", this.getClass().getSimpleName()));
            return true;
        }
        TimeMap.put(player, System.currentTimeMillis());
        return false;
    }
}
