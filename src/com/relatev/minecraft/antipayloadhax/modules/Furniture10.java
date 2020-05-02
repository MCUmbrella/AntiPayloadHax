package com.relatev.minecraft.antipayloadhax.modules;

import com.relatev.minecraft.antipayloadhax.AntiPayloadHax;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Furniture10 extends PayloadModuel {

    public Furniture10() {
        super("cfm", (byte) 10);
        AntiPayloadHax.MainPlugin.getLogger().info(this.getClass().getSimpleName() + " 防御系统已启用!");
        this.enable();
    }

    @Override
    public boolean onReceivePayload(Player player, byte[] array) {
        player.sendMessage(AntiPayloadHax.MainPlugin.configManager.DenyMessage);
        return true;
    }
}
