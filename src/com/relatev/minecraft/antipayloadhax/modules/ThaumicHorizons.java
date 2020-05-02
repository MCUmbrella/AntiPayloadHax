package com.relatev.minecraft.antipayloadhax.modules;

import com.relatev.minecraft.antipayloadhax.AntiPayloadHax;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ThaumicHorizons extends PayloadModuel {

    public ThaumicHorizons() {
        super("thaumichorizons", (byte) 13);
        AntiPayloadHax.MainPlugin.getLogger().info(this.getClass().getSimpleName() + " 防御系统已启用!");
        this.enable();
    }

    @Override
    public boolean onReceivePayload(Player player, byte[] array) {
        Bukkit.broadcastMessage(AntiPayloadHax.MainPlugin.configManager.BoardcastMessage.replaceAll("%Player%", player.getName()).replaceAll("%Mod%", this.getClass().getSimpleName()));
        return true;
    }
}
