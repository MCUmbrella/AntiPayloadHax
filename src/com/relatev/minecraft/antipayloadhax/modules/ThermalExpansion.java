package com.relatev.minecraft.antipayloadhax.modules;

import com.relatev.minecraft.antipayloadhax.AntiPayloadHax;
import io.netty.buffer.ByteBuf;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class ThermalExpansion extends PayloadModuel {

    @Override
    public boolean onReceivePayload(Player player, ByteBuf buf) {
        int packetid = this.readBufByte(buf);
        int x = this.readBufInt(buf);
        int y = this.readBufInt(buf);
        int z = this.readBufInt(buf);
        if (player.getWorld().isChunkLoaded(x / 16, z / 16)) {
            Block block = player.getWorld().getBlockAt(x, y, z);
            if (block.getType().name().contains("CACHE")) {
                Bukkit.broadcastMessage(AntiPayloadHax.MainPlugin.configManager.BoardcastMessage.replaceAll("%Player%", player.getName()).replaceAll("%Mod%", this.getClass().getSimpleName()));
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public ThermalExpansion() {
        super("CoFH", (byte) 4);
        AntiPayloadHax.MainPlugin.getLogger().info(this.getClass().getSimpleName() + " 防御模块加载中!");
        if (AntiPayloadHax.MainPlugin.classNameMap != null && AntiPayloadHax.MainPlugin.classNameMap.containsKey("cofh.thermalexpansion.block.cache.TileCache")) {
            AntiPayloadHax.MainPlugin.getLogger().info("AnotherCommonBugFix已安装,此模块已被接替!");
        } else {
            AntiPayloadHax.MainPlugin.getLogger().info(this.getClass().getSimpleName() + " 防御系统已经启用!");
            this.enable();
        }
    }
}
