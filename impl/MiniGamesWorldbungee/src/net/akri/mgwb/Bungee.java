package net.akri.mgwb;

import net.akri.mgwb.commands.CommandHandler;
import net.akri.mgwb.commands.TeamBauCommandHandler;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;

public class Bungee extends Plugin {

    //Strings
    public static final String prefix = ChatColor.GRAY + "[" + ChatColor.GREEN + "System" + ChatColor.GRAY + "]";

    @Override
    public void onEnable() {
        getLogger().info("BungeePluginSystem starts...");

        getProxy().getPluginManager().registerCommand( this, new TeamBauCommandHandler());
        getProxy().getPluginManager().registerCommand(this, new CommandHandler());
    }
}
