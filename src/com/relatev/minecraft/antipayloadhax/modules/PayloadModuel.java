package com.relatev.minecraft.antipayloadhax.modules;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.google.gson.Gson;
import com.relatev.minecraft.antipayloadhax.AntiPayloadHax;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PayloadModuel {

    private static boolean bypassReceiving = false;
    private static List<PayloadModuel> loadedModuels = new ArrayList();
    public final String Channel;
    public final byte PacketID;
    private static boolean trackMode = false;
    private static Gson gson = new Gson();

    public PayloadModuel(String channel, byte packetid) {
        Channel = channel;
        PacketID = packetid;
    }

    public static void init() {
        if (Bukkit.getVersion().contains("1.7.10")) {
            PacketAdapter PacketListener = new PacketAdapter(AntiPayloadHax.MainPlugin, ListenerPriority.NORMAL, PacketType.Play.Client.CUSTOM_PAYLOAD) {
                @Override
                public void onPacketReceiving(PacketEvent event) {
                    if (bypassReceiving) {
                        return;
                    }
                    PacketContainer pc = event.getPacket();
                    Player player = event.getPlayer();
                    String channel = pc.getStrings().getValues().get(0);
                    List<byte[]> arrays = pc.getByteArrays().getValues();
                    for (byte[] array : arrays) {
                        if (array != null) {
                            if (trackMode) {
                                player.sendMessage("PacketTrack: [" + channel + "]" + gson.toJson(array));
                            }
                            try {
                                ByteBuf bbuf = Unpooled.copiedBuffer(array);
                                Constructor con = Class.forName("net.minecraft.network.PacketBuffer").getConstructor(Class.forName("io.netty.buffer.ByteBuf"));
                                Object pbuf = con.newInstance(bbuf);
                                List<PayloadModuel> willrunmoduel = new ArrayList();
                                for (PayloadModuel moduel : loadedModuels) {
                                    if (moduel.Channel.equals(channel) && (moduel.PacketID == array[0] || moduel.PacketID == -1)) {
                                        willrunmoduel.add(moduel);
                                    }
                                }
                                if (willrunmoduel.isEmpty() == false) {
                                    for (PayloadModuel moduel : willrunmoduel) {
                                        if (moduel.onReceivePayload(player, (ByteBuf) pbuf) || moduel.onReceivePayload(player, array)) {
                                            event.setCancelled(true);
                                            break;
                                        }
                                    }
                                }
                            } catch (SecurityException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException ex) {
                                Logger.getLogger(PayloadModuel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            };
            AntiPayloadHax.MainPlugin.PLManager.addPacketListener(PacketListener);
        } else {
            PacketAdapter PacketListener = new PacketAdapter(AntiPayloadHax.MainPlugin, ListenerPriority.NORMAL, PacketType.Play.Client.CUSTOM_PAYLOAD) {

                @Override
                public void onPacketReceiving(PacketEvent event) {
                    if (bypassReceiving) {
                        return;
                    }
                    PacketContainer pc = event.getPacket();
                    List<Object> list = pc.getModifier().getValues();
                    String channel = pc.getStrings().getValues().get(0);
                    Player player = event.getPlayer();
                    byte[] array = null;
                    for (Object obj : list) {
                        if (obj.toString().contains("ByteBuf")) {
                            ByteBuf cbb = ((ByteBuf) obj).copy();
                            array = new byte[cbb.readableBytes()];
                            cbb.readBytes(array);
                        }
                    }
                    if (array != null) {
                        if (trackMode) {
                            player.sendMessage("PacketTrack: [" + channel + "]" + gson.toJson(array));
                        }
                        try {
                            ByteBuf bbuf = Unpooled.copiedBuffer(array);
                            Constructor con = Class.forName("net.minecraft.network.PacketBuffer").getConstructor(Class.forName("io.netty.buffer.ByteBuf"));
                            Object pbuf = con.newInstance(bbuf);
                            List<PayloadModuel> willrunmoduel = new ArrayList();
                            for (PayloadModuel moduel : loadedModuels) {
                                if (moduel.Channel.equals(channel) && (moduel.PacketID == array[0] || moduel.PacketID == -1)) {
                                    willrunmoduel.add(moduel);
                                }
                            }
                            if (willrunmoduel.isEmpty() == false) {
                                for (PayloadModuel moduel : willrunmoduel) {
                                    if (moduel.onReceivePayload(player, (ByteBuf) pbuf) || moduel.onReceivePayload(player, array)) {
                                        event.setCancelled(true);
                                        break;
                                    }
                                }
                            }
                        } catch (SecurityException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException ex) {
                            Logger.getLogger(PayloadModuel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            };
            AntiPayloadHax.MainPlugin.PLManager.addPacketListener(PacketListener);
        }
    }

    public void enable() {
        loadedModuels.add(this);
    }

    public boolean onReceivePayload(Player player, byte[] array) {
        return false;
    }

    public boolean onReceivePayload(Player player, ByteBuf buf) {
        return false;
    }

    public Object readBufItemStack(Object buf) {

        try {
            for (Method method : buf.getClass().getMethods()) {
                if (method.getName().equals("func_150791_c")) {
                    return method.invoke(buf);
                }
                if (method.getName().equals("readItemStack")) {
                    return method.invoke(buf);
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException ex) {
            Logger.getLogger(PayloadModuel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String readBufString(Object buf) {
        try {
            return (String) buf.getClass().getMethod("readString").invoke(buf);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(PayloadModuel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean readBufBoolean(Object buf) {
        try {
            return (boolean) buf.getClass().getMethod("readBoolean").invoke(buf);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(PayloadModuel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public byte readBufByte(Object buf) {
        try {
            return (byte) buf.getClass().getMethod("readByte").invoke(buf);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(PayloadModuel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int readBufInt(Object buf) {
        try {
            return (int) buf.getClass().getMethod("readInt").invoke(buf);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(PayloadModuel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
