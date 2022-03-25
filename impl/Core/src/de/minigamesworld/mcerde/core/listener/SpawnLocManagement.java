package de.minigamesworld.mcerde.core.listener;

import de.minigamesworld.mcerde.core.McErdeCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class SpawnLocManagement implements Listener {

    McErdeCore main = McErdeCore.getInstance();
    Configuration config = main.getConfig();

    @EventHandler @Deprecated
    public void handleJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        if(config.get("PlayerManagement.LastLogout." + p.getUniqueId() + ".x") != null && config.get("PlayerManagement.LastLogout." + p.getUniqueId() + ".y") != null && config.get("PlayerManagement.LastLogout." + p.getUniqueId() + ".z") != null && config.get("PlayerManagement.LastLogout." + p.getUniqueId() + ".world") != null){
            Location loc = new Location(Bukkit.getWorld(Objects.requireNonNull(config.getString("PlayerManagement.LastLogout.world"))), config.getDouble("PlayerManagement.LastLogout.x"), config.getDouble("PlayerManagement.LastLogout.y"), config.getDouble("PlayerManagement.LastLogout.z"));

            p.teleport(loc);
            p.sendTitle("§6§lWillkommen zu:", "§a§lMinecraft-Erde");
        }else {

            Location loc = new Location(Bukkit.getWorld("world"), config.getDouble("PlayerManagement.DefaultSpawn.x"), config.getDouble("PlayerManagement.DefaultSpawn.y"), config.getDouble("PlayerManagement.DefaultSpawn.z"));

            p.teleport(loc);
            p.sendTitle("§6§lWillkommen zu:", "§a§lMinecraft-Erde");
        }


    }

    @EventHandler
    public void handelQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();

        config.set("PlayerManagement.LastLogout." + p.getUniqueId() + ".x", p.getLocation().getX());
        config.set("PlayerManagement.LastLogout." + p.getUniqueId() + ".y", p.getLocation().getY());
        config.set("PlayerManagement.LastLogout." + p.getUniqueId() + ".z", p.getLocation().getZ());
        config.set("PlayerManagement.LastLogout." + p.getUniqueId() + ".world", Objects.requireNonNull(p.getLocation().getWorld()).getName());

        main.saveConfig();
        main.reloadConfig();
    }
}
