package com.relatev.minecraft.antipayloadhax.modules;

import com.relatev.minecraft.antipayloadhax.AntiPayloadHax;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BiblioMCBEdit extends PayloadModuel {

    public BiblioMCBEdit() {
        super("BiblioMCBEdit", (byte) -1);
        this.enable();
        AntiPayloadHax.MainPlugin.getLogger().info(this.getClass().getSimpleName() + " 防御系统已启用!");
    }

    @Override
    public boolean onReceivePayload(Player player, byte[] array) {
        player.sendMessage(AntiPayloadHax.MainPlugin.configManager.DenyMessage);
        return true;
    }
}
