package com.relatev.minecraft.antipayloadhax.modules;

import com.relatev.minecraft.antipayloadhax.AntiPayloadHax;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MineFactoryReloaded extends PayloadModuel {

    public MineFactoryReloaded() {
        super("MFReloaded", (byte) -1);
        AntiPayloadHax.MainPlugin.getLogger().info(this.getClass().getSimpleName() + " 防御系统加载中!");
        if (AntiPayloadHax.MainPlugin.classNameMap != null && AntiPayloadHax.MainPlugin.classNameMap.containsKey("powercrystals.minefactoryreloaded.net.ServerPacketHandler")) {
            AntiPayloadHax.MainPlugin.getLogger().info(this.getClass().getSimpleName() + " 此模块现在被AnotherCommonBuyFix接手!");
        } else {
            AntiPayloadHax.MainPlugin.getLogger().info(this.getClass().getSimpleName() + " 防御系统已启用!");
            this.enable();
        }
    }

    @Override
    public boolean onReceivePayload(Player player, byte[] array) {
        if (array[6] == 20) {
            Bukkit.broadcastMessage(AntiPayloadHax.MainPlugin.configManager.BoardcastMessage.replaceAll("%Player%", player.getName()).replaceAll("%Mod%", this.getClass().getSimpleName()));
            return true;
        }
        if (array[6] == 11) {
            Bukkit.broadcastMessage(AntiPayloadHax.MainPlugin.configManager.BoardcastMessage.replaceAll("%Player%", player.getName()).replaceAll("%Mod%", this.getClass().getSimpleName()));
            return true;
        }
        return false;
    }
}
