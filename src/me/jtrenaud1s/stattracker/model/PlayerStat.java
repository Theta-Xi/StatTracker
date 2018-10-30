package me.jtrenaud1s.stattracker.model;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerStat {
    private UUID uuid;
    private int kills;
    private int streak;
    private int deaths;
    private double damageDealt;

    public PlayerStat(UUID uuid) {
        this.uuid = uuid;
        kills = deaths = streak = 0;
        damageDealt = 0D;
    }

    public PlayerStat(Player player) {
        this(player.getUniqueId());
    }

    public UUID getUUID() {
        return uuid;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public double getDamageDealt() {
        return damageDealt;
    }

    public void setDamageDealt(double damageDealt) {
        this.damageDealt = damageDealt;
    }

    public void addKill() {
        streak++;
        kills++;
    }

    public void addDeath(){
        streak = 0;
        deaths++;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public void addDamage(double damage) {
        this.damageDealt += damage;
    }
}
