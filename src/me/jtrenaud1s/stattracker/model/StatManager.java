package me.jtrenaud1s.stattracker.model;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class StatManager {
    private List<PlayerStat> players;

    public StatManager() {
        players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        if(!contains(player))
            players.add(new PlayerStat(player));
    }

    public boolean contains(Player player) {
        return getPlayerStats(player) != null ? true : false;
    }

    public void removePlayer(Player player){
        if(contains(player))
            players.remove(player);
    }

    public PlayerStat getPlayerStats(Player player) {
        for(PlayerStat ps : players) {
            if(ps.getUUID() == player.getUniqueId())
                return ps;
        }
        return null;
    }
}
