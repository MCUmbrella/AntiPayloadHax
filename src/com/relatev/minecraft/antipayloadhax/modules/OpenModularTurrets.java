package com.relatev.minecraft.antipayloadhax.modules;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.relatev.minecraft.antipayloadhax.AntiPayloadHax;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class OpenModularTurrets extends PayloadModuel {

    @Override
    public boolean onReceivePayload(Player player, byte[] array) {
        Bukkit.broadcastMessage(AntiPayloadHax.MainPlugin.configManager.BoardcastMessage.replaceAll("%Player%", player.getName()).replaceAll("%Mod%", this.getClass().getSimpleName()));
        return true;
    }

    public OpenModularTurrets() {
        super("openmodularturrets", (byte) 9);
        AntiPayloadHax.MainPlugin.getLogger().info(this.getClass().getSimpleName() + " 防御系统加载中!");
        if (AntiPayloadHax.MainPlugin.classNameMap != null && AntiPayloadHax.MainPlugin.classNameMap.containsKey("openmodularturrets.client.gui.containers.TurretBaseContainer") && AntiPayloadHax.MainPlugin.classNameMap.containsKey("openmodularturrets.network.messages.MessageDropBase$MessageHandlerDropBase")) {
            AntiPayloadHax.MainPlugin.getLogger().info("AnotherCommonBugFix已安装,此模块已被接替!");
        } else {
            AntiPayloadHax.MainPlugin.getLogger().info(this.getClass().getSimpleName() + " 防御系统已启用!");
            this.enable();
        }
    }
}
