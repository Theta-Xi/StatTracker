package me.jtrenaud1s.stattracker;

import com.mojang.authlib.GameProfile;
import me.jtrenaud1s.stattracker.api.NMS;
import me.jtrenaud1s.stattracker.api.v1_13_R2.CNMS;
import me.jtrenaud1s.stattracker.api.v1_13_R2.EntityNPC;
import net.minecraft.server.v1_13_R2.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_13_R2.CraftServer;
import org.bukkit.craftbukkit.v1_13_R2.CraftWorld;

import java.util.UUID;

public class Utils {

    public static NMS nms;
    private static boolean isInit = false;

    public static void init() {
        if(!isInit){
        String version;

            try {
                version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
            } catch (ArrayIndexOutOfBoundsException whatVersionAreYouUsingException) {
                return;
            }

            if (version.equals("v1_13_R2")) {
                nms = new CNMS();
                isInit = true;
            }
        }
    }

    public static void createNPC(Location loc) {
        init();
        nms.spawnNPC(loc);
    }
}
