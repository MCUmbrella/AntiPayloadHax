package com.relatev.minecraft.antipayloadhax.modules;

import com.relatev.minecraft.antipayloadhax.AntiPayloadHax;
import org.bukkit.entity.Player;

public class BuildCraft extends PayloadModuel {

    public BuildCraft() {
        super("BC-CORE", (byte) 0);
        AntiPayloadHax.MainPlugin.getLogger().info(this.getClass().getSimpleName() + " 防御系统加载中!");
        if (AntiPayloadHax.MainPlugin.classNameMap != null && AntiPayloadHax.MainPlugin.classNameMap.containsKey("buildcraft.core.lib.network.PacketHandler")) {
            AntiPayloadHax.MainPlugin.getLogger().info(this.getClass().getSimpleName() + " 现在由AnotherCommonBugFix接手修复!");
        } else {
            this.enable();
        }
    }

    @Override
    public boolean onReceivePayload(Player player, byte[] array) {
        player.sendMessage(AntiPayloadHax.MainPlugin.configManager.DenyMessage);
        return true;
    }
}
