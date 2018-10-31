package me.jtrenaud1s.stattracker.api.v1_13_R2;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_13_R2.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class EntityNPC extends EntityPlayer {
    public EntityNPC(MinecraftServer minecraftserver, WorldServer worldserver, GameProfile gameprofile, PlayerInteractManager playerinteractmanager) {
        super(minecraftserver, worldserver, gameprofile, playerinteractmanager);
        this.playerConnection = new CustomPlayerConnection(server, new NetworkManager(EnumProtocolDirection.SERVERBOUND), this);
    }

    public void spawn() {
        world.addEntity(this);

        for(Player target : Bukkit.getOnlinePlayers()) {
            PlayerConnection connection = ((CraftPlayer) target).getHandle().playerConnection;
            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, this));
            connection.sendPacket(new PacketPlayOutNamedEntitySpawn(this));
        }
    }
}
