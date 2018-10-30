package me.jtrenaud1s.stattracker.events;

import me.jtrenaud1s.stattracker.StatTracker;
import me.jtrenaud1s.stattracker.model.PlayerStat;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerEventListener implements Listener {

    private StatTracker plugin;

    public PlayerEventListener(StatTracker plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoinServer(PlayerJoinEvent event) {
        plugin.getStatManager().addPlayer(event.getPlayer());
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            plugin.getStatManager().getPlayerStats((Player)event.getDamager()).addDamage(event.getDamage());
        }
    }

    @EventHandler
    public void onPlayerKillPlayerEvent(PlayerDeathEvent event) {
        plugin.getStatManager().getPlayerStats(event.getEntity()).addDeath();
        PlayerStat killer = plugin.getStatManager().getPlayerStats(event.getEntity().getKiller());
        killer.addKill();

        if(killer.getStreak() % 5 == 0) {
            for(Player p : plugin.getServer().getOnlinePlayers()) {
                p.sendMessage(ChatColor.GREEN + "" + event.getEntity().getKiller().getName() + " is on a " + killer.getStreak() + " kill streak!");
            }
        }
    }
}
