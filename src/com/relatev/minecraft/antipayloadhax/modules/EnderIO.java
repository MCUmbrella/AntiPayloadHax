package com.relatev.minecraft.antipayloadhax.modules;

import com.relatev.minecraft.antipayloadhax.AntiPayloadHax;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class EnderIO extends PayloadModuel {

    public EnderIO() {
        super("enderio", (byte) 56);
        if (AntiPayloadHax.MainPlugin.classNameMap != null && AntiPayloadHax.MainPlugin.classNameMap.containsKey("crazypants.enderio.teleport.packet.PacketTravelEvent")) {
            AntiPayloadHax.MainPlugin.getLogger().info("AnotherCommonBugFix已安装,本插件此模块已被接替!");
        } else {
            AntiPayloadHax.MainPlugin.getLogger().info(this.getClass().getSimpleName() + " 防御系统已经启用!");
            this.enable();
        }
    }

    @Override
    public boolean onReceivePayload(Player player, byte[] array) {
        player.sendMessage(AntiPayloadHax.MainPlugin.configManager.DenyMessage);
        return true;
    }
}
