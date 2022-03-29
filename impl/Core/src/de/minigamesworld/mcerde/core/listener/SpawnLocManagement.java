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

        if(config.get("PlayerManagement." + p.getUniqueId() + ".LastLogOut") != null){
            Location loc = config.getLocation("PlayerManagement." + p.getUniqueId() + ".LastLogOut");

            p.teleport(loc);
        }else {
            Location loc = config.getLocation("PlayerManagement.DefaultSpawnLoc");

            p.teleport(loc);
        }

        p.sendTitle("§6§lWillkommen zu:", "§a§lMinecraft-Erde");
    }

    @EventHandler
    public void handelQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();

        config.set("PlayerManagement." + p.getUniqueId() + ".LastLogOut", p.getLocation());

        main.saveConfig();
        main.reloadConfig();
    }
}
