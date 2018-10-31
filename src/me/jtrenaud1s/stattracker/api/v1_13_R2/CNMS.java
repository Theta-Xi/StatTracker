package me.jtrenaud1s.stattracker.api.v1_13_R2;

import com.mojang.authlib.GameProfile;
import me.jtrenaud1s.stattracker.api.NMS;
import net.minecraft.server.v1_13_R2.MinecraftServer;
import net.minecraft.server.v1_13_R2.PlayerInteractManager;
import net.minecraft.server.v1_13_R2.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_13_R2.CraftServer;
import org.bukkit.craftbukkit.v1_13_R2.CraftWorld;

import java.util.UUID;

public class CNMS implements NMS {
    @Override
    public void spawnNPC(Location loc) {
        MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer nmsWorld = ((CraftWorld) loc.getWorld()).getHandle();

        EntityNPC entity = new EntityNPC(nmsServer, nmsWorld,
                new GameProfile(UUID.randomUUID(), "NPC"),
                new PlayerInteractManager(nmsWorld));

        entity.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        entity.setHealth(20F);

        entity.spawn();
    }
}
