package me.jtrenaud1s.stattracker;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_13_R2.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_13_R2.CraftServer;
import org.bukkit.craftbukkit.v1_13_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Utils {
    public static EntityPlayer createNPC(Location loc) {
        MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer nmsWorld = ((CraftWorld) loc.getWorld()).getHandle();

        EntityPlayer entity = new EntityPlayer(nmsServer, nmsWorld,
                new GameProfile(UUID.randomUUID(), "NPC"),
                new PlayerInteractManager(nmsWorld));

        entity.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        entity.setHealth(20F);

        for(Player target : Bukkit.getOnlinePlayers()) {
            PlayerConnection connection = ((CraftPlayer) target).getHandle().playerConnection;
            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, entity));
            connection.sendPacket(new PacketPlayOutNamedEntitySpawn(entity));
        }

        return entity;
    }
}
