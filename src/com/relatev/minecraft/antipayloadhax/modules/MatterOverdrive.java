package com.relatev.minecraft.antipayloadhax.modules;

import com.relatev.minecraft.antipayloadhax.AntiPayloadHax;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MatterOverdrive extends PayloadModuel {

    @Override
    public boolean onReceivePayload(Player player, byte[] array) {
        Bukkit.broadcastMessage(AntiPayloadHax.MainPlugin.configManager.BoardcastMessage.replaceAll("%Player%", player.getName()).replaceAll("%Mod%", this.getClass().getSimpleName()));
        return true;
    }

    public MatterOverdrive() {
        super("mo_channel", (byte) 24);
        AntiPayloadHax.MainPlugin.getLogger().info(this.getClass().getSimpleName() + " 防御系统加载中!");
        if (AntiPayloadHax.MainPlugin.classNameMap != null && AntiPayloadHax.MainPlugin.classNameMap.containsKey("matteroverdrive.network.packet.server.PacketSendMachineNBT$BiHandler")) {
            AntiPayloadHax.MainPlugin.getLogger().info("AnotherCommonBugFix已安装,此模块已被接替!");
        } else {
            this.enable();
            AntiPayloadHax.MainPlugin.getLogger().info(this.getClass().getSimpleName() + " 防御系统已经启用!");
        }
    }
}
