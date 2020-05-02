package com.relatev.minecraft.antipayloadhax.modules;

import com.google.gson.Gson;
import com.relatev.minecraft.antipayloadhax.AntiPayloadHax;
import io.netty.buffer.ByteBuf;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class ExtraCells extends PayloadModuel {

    public ExtraCells() {
        super("extracells", (byte) 8);
        AntiPayloadHax.MainPlugin.getLogger().info(this.getClass().getSimpleName() + " 防御系统加载中!");
        if (AntiPayloadHax.MainPlugin.classNameMap != null && AntiPayloadHax.MainPlugin.classNameMap.containsKey("extracells.network.packet.other.PacketFluidContainerSlot")) {
            AntiPayloadHax.MainPlugin.getLogger().info(this.getClass().getSimpleName() + " 此模块现在被AnotherCommonBuyFix接手!");
        } else {
            AntiPayloadHax.MainPlugin.getLogger().info(this.getClass().getSimpleName() + " 防御系统已启用!");
            this.enable();
        }
    }

    @Override
    public boolean onReceivePayload(Player player, ByteBuf buf) {
        Gson gson = new Gson();
        buf.readByte();
        buf.readByte();
        buf.readBoolean();
        buf.readInt();
        buf.readInt();
        buf.readBytes(player.getName().getBytes());
        buf.readInt();
        int x = buf.readInt();
        int y = buf.readInt();
        int z = buf.readInt();
        Object itemstack = this.readBufItemStack(buf);
        try {
            Class clazz = Class.forName("extracells.util.FluidUtil");
            if ((boolean) clazz.getMethod("isEmpty", Class.forName("net.minecraft.item.ItemStack")).invoke(null, itemstack) == false) {
                Bukkit.broadcastMessage(AntiPayloadHax.MainPlugin.configManager.BoardcastMessage.replaceAll("%Player%", player.getName()).replaceAll("%Mod%", this.getClass().getSimpleName()));
                return true;
            }
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(ExtraCells.class.getName()).log(Level.SEVERE, null, ex);
        }
        Bukkit.broadcastMessage("item:" + itemstack);
        return false;
    }
}
