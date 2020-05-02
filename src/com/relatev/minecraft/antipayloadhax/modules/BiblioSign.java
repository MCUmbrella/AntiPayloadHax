package com.relatev.minecraft.antipayloadhax.modules;

import com.relatev.minecraft.antipayloadhax.AntiPayloadHax;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BiblioSign extends PayloadModuel {

    public BiblioSign() {
        super("BiblioSign", (byte) 0);
        this.enable();
        AntiPayloadHax.MainPlugin.getLogger().info(this.getClass().getSimpleName() + " 防御系统已启用!");
    }

    @Override
    public boolean onReceivePayload(Player player, byte[] array) {
        if (array[0] == 0 && array[1] == 0 && array[2] == 0 && array[3] == 0 && array[4] == 1 && player.isOp() == false) {
            player.sendMessage(AntiPayloadHax.MainPlugin.configManager.DenyMessage);
            return true;
        }
        return false;
    }
}
