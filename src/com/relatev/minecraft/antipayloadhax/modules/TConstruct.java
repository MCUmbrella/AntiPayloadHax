package com.relatev.minecraft.antipayloadhax.modules;

import com.relatev.minecraft.antipayloadhax.AntiPayloadHax;
import io.netty.buffer.ByteBuf;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class TConstruct extends PayloadModuel {

    public TConstruct() {
        super("TConstruct", (byte) 8);
        AntiPayloadHax.MainPlugin.getLogger().info(this.getClass().getSimpleName() + " 防御系统启动中!");
        if (AntiPayloadHax.MainPlugin.classNameMap != null && AntiPayloadHax.MainPlugin.classNameMap.containsKey("tconstruct.util.network.PatternTablePacket")) {
            AntiPayloadHax.MainPlugin.getLogger().info("AnotherCommonBugFix已安装,本插件此模块已被接替!");
        } else {
            AntiPayloadHax.MainPlugin.getLogger().info(this.getClass().getSimpleName() + " 防御系统已经启用!");
            this.enable();
        }
    }

    @Override
    public boolean onReceivePayload(Player player, ByteBuf buf) {
        int packetid = this.readBufByte(buf);
        int x = this.readBufInt(buf);
        int y = this.readBufInt(buf);
        int z = this.readBufInt(buf);
        if (player.getWorld().isChunkLoaded(x / 16, z / 16)) {
            Block block = player.getWorld().getBlockAt(x, y, z);
            if (block.getType().name().equals("TCONSTRUCT_TOOLSTATIONBLOCK") && (block.getData() == 10 || block.getData() == 11 || block.getData() == 12 || block.getData() == 13)) {
                return false;
            } else {
                Bukkit.broadcastMessage(AntiPayloadHax.MainPlugin.configManager.BoardcastMessage.replaceAll("%Player%", player.getName()).replaceAll("%Mod%", this.getClass().getSimpleName()));
                return true;
            }
        } else {
            return true;
        }
    }
}
