package com.relatev.minecraft.antipayloadhax.modules;

import com.relatev.minecraft.antipayloadhax.AntiPayloadHax;
import org.bukkit.entity.Player;

public class RailCraft extends PayloadModuel {

    @Override
    public boolean onReceivePayload(Player player, byte[] array) {
        if (array[1] == 16 && array[2] == 60 && array[3] == 64) {
            player.sendMessage(AntiPayloadHax.MainPlugin.configManager.DenyMessage);
            return true;
        }
        return false;
    }

    public RailCraft() {
        super("RC", (byte) 14);
        AntiPayloadHax.MainPlugin.getLogger().info(this.getClass().getSimpleName() + " 防御系统已启用!");
        this.enable();
    }
}
