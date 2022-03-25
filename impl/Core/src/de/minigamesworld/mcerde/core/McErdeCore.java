package de.minigamesworld.mcerde.core;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import de.minigamesworld.mcerde.core.listener.SpawnLocManagement;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class McErdeCore extends JavaPlugin {

    private static McErdeCore instance;

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {
        instance = this;

        this.getLogger().info("Loaded Core!");

        PluginManager pluginManager = Bukkit.getPluginManager();
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

        //TODO Make better way for loc saving because ist messy
        if(this.getConfig().get("PlayerManagement.DefaultSpawn.x") == null){
            this.getConfig().set("PlayerManagement.DefaultSpawn.x", 1);
        }

        if(this.getConfig().get("PlayerManagement.DefaultSpawn.y") == null){
            this.getConfig().set("PlayerManagement.DefaultSpawn.y", 1);
        }

        if(this.getConfig().get("PlayerManagement.DefaultSpawn.z") == null){
            this.getConfig().set("PlayerManagement.DefaultSpawn.z", 1);
        }
        this.saveConfig();

        pluginManager.registerEvents(new SpawnLocManagement(), this);
    }

    @Override
    public void onDisable() {

    }

    public static McErdeCore getInstance(){
        return instance;
    }

}
