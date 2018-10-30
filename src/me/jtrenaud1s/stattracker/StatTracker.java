package me.jtrenaud1s.stattracker;

import me.jtrenaud1s.stattracker.commands.CommandStats;
import me.jtrenaud1s.stattracker.events.PlayerEventListener;
import me.jtrenaud1s.stattracker.model.StatManager;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class StatTracker extends JavaPlugin {
    private StatManager statManager;

    @Override
    public void onEnable() {
        statManager = new StatManager();
        getCommand("stats").setExecutor(new CommandStats(this));
        getServer().getPluginManager().registerEvents(new PlayerEventListener(this), this);
    }

    @Override
    public void onDisable() {
        EntityDamageByEntityEvent.getHandlerList().unregister(this);
        PlayerJoinEvent.getHandlerList().unregister(this);
        PlayerDeathEvent.getHandlerList().unregister(this);
    }

    public StatManager getStatManager() {
        return this.statManager;
    }

}
