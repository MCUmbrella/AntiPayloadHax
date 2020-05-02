package com.relatev.minecraft.antipayloadhax.modules;

import com.relatev.minecraft.antipayloadhax.AntiPayloadHax;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Mekanism extends PayloadModuel {

    public Mekanism() {
        super("MEK", (byte) 20);
        AntiPayloadHax.MainPlugin.getLogger().info(this.getClass().getSimpleName() + " 防御系统已启用!");
        this.enable();
    }

    @Override
    public boolean onReceivePayload(Player player, byte[] array) {
        if (array[1] == 0 && array[2] == 0 && array[3] == 0 && array[4] == 0 && array[5] == 0 && array[6] == 0 && array[7] == 0) {
            if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType().name().contains("JETPACK") == false) {
                player.sendMessage(AntiPayloadHax.MainPlugin.configManager.DenyMessage);
                return true;
            }
        }
        return false;
    }
}
