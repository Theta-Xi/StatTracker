package me.jtrenaud1s.stattracker.api.v1_13_R2;

import net.minecraft.server.v1_13_R2.*;

public class CustomPlayerConnection extends PlayerConnection {
    public CustomPlayerConnection(MinecraftServer minecraftserver, NetworkManager networkmanager, EntityPlayer entityplayer) {
        super(minecraftserver, networkmanager, entityplayer);
    }

    @Override
    public void sendPacket(Packet packet) {
        // Don't do anything because it's a NPC.
    }
}