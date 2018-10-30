package me.jtrenaud1s.stattracker.commands;

import me.jtrenaud1s.stattracker.StatTracker;
import me.jtrenaud1s.stattracker.Utils;
import me.jtrenaud1s.stattracker.model.PlayerStat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandStats implements CommandExecutor {
    private StatTracker plugin;

    public CommandStats(StatTracker plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(command.getName().equalsIgnoreCase("stats")) {
            if(commandSender instanceof Player) {
                Player sender = (Player) commandSender;
                if(strings.length == 0){
                PlayerStat stats = plugin.getStatManager().getPlayerStats(sender);

                String message = ChatColor.YELLOW + "-------------------- [Your Stats] --------------------";
                message += ChatColor.BLUE + "Kills: " + ChatColor.GREEN + stats.getKills() + "\n";
                message += ChatColor.BLUE + "Deaths: " + ChatColor.GREEN + stats.getDeaths() + "\n";
                message += ChatColor.BLUE + "K/D: " + ChatColor.GREEN + (stats.getDeaths() > 0 ? (((double)stats.getKills())/((double)stats.getDeaths())) : stats.getKills()) + "\n";
                message += ChatColor.BLUE + "Streak: " + ChatColor.GREEN + stats.getStreak() + "\n";
                message += ChatColor.BLUE + "Damage Dealt: " + ChatColor.GREEN + stats.getDamageDealt() + "\n";

                sender.sendMessage(message);
                return true;
                } else if(strings.length == 1) {
                    if(strings[0].equalsIgnoreCase("spawn")) {
                        Utils.createNPC(sender.getLocation());
                        return true;
                    }
                }
                return true;
            }
        }
        return false;
    }
}
