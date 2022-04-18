package net.akri.mgwb.commands;

import net.akri.mgwb.Bungee;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CommandHandler extends Command implements TabExecutor {


    public CommandHandler() {
        super("system");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("rank.admin")) {
            if (args.length == 2) {
                String serverName = args[1];

                switch (args[0]) {

                    case "start": {
                        sender.sendMessage(Bungee.prefix + " " + ChatColor.GRAY + "Der Server: " + ChatColor.GOLD + serverName + ChatColor.GRAY + " wird gestartet...");
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(sender, "cl ser " + serverName + " start");

                        break;
                    }

                    case "stop": {
                        sender.sendMessage(Bungee.prefix + " " + ChatColor.GRAY + "Der Server: " + ChatColor.GOLD + serverName + ChatColor.GRAY + " wird gestopt!");
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(sender, "cl ser " + serverName + " stop");

                        break;
                    }

                    case "restart": {
                        sender.sendMessage(Bungee.prefix + " " + ChatColor.GRAY + "Der Server: " + ChatColor.GOLD + serverName + ChatColor.GRAY + " wird neu gestartet...!");
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(sender, "cl ser " + serverName + " restart");

                        break;
                    }

                    case "console": {
                        sender.sendMessage(Bungee.prefix + " " + ChatColor.GRAY + "Der Server: " + ChatColor.GOLD + serverName + ChatColor.GRAY + " überträgt nun seinen Consolen output zu dir...");
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(sender, "cl screen toggle " + serverName);

                        break;
                    }

                    default:
                        sender.sendMessage(Bungee.prefix + " " + ChatColor.RED + "Error(Bungee:F0001) " + ChatColor.GRAY + "INVALID_ARGUMENT");
                        break;
                }

            } else if (args.length == 4) {
                if (args[0].equals("rank")) {
                    String rank_name = args[3];
                    String player_name = args[2];

                    switch (args[1]) {
                        case "add": {
                            sender.sendMessage(Bungee.prefix + " " + " " + ChatColor.GRAY + "Dem Spieler: " + ChatColor.GOLD + player_name + ChatColor.GRAY + " wurde der Rang: " + ChatColor.GOLD + rank_name + ChatColor.GRAY + " hinzugefügt.");
                            ProxyServer.getInstance().getPluginManager().dispatchCommand(sender, "cl perms user " + player_name + " add group " + rank_name);

                            break;
                        }

                        case "remove": {
                            sender.sendMessage(Bungee.prefix + " " + " " + ChatColor.GRAY + "Dem Spieler: " + ChatColor.GOLD + player_name + ChatColor.GRAY + " wurde der Rang: " + ChatColor.GOLD + rank_name + ChatColor.GRAY + " entzogen.");
                            ProxyServer.getInstance().getPluginManager().dispatchCommand(sender, "cl perms user " + player_name + " remove group " + rank_name);

                            break;
                        }

                        default:
                            sender.sendMessage(Bungee.prefix + " " + ChatColor.RED + "Error(Bungee:F0001) " + ChatColor.GRAY + "INVALID_ARGUMENT");
                            break;

                    }

                } else
                    sender.sendMessage(Bungee.prefix + " " + ChatColor.RED + "Error(Bungee:F0001) " + ChatColor.GRAY + "INVALID_ARGUMENT");
            } else
                sender.sendMessage(Bungee.prefix + " " + ChatColor.RED + "Error(Bungee:F0001) " + ChatColor.GRAY + "INVALID_ARGUMENT");

        } else
            sender.sendMessage(Bungee.prefix + " " + ChatColor.RED + "Dazu hast du keine Rechte!");
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        ArrayList<String> args1Tab = new ArrayList<>();
        ArrayList<String> args2TabRank = new ArrayList<>();
        ArrayList<String> args3Tab = new ArrayList<>();

        args1Tab.add("stop");
        args1Tab.add("start");
        args1Tab.add("restart");
        args1Tab.add("rank");
        args1Tab.add("console");

        args2TabRank.add("remove");
        args2TabRank.add("add");

        for(ProxiedPlayer allPlayer: BungeeCord.getInstance().getPlayers()){
            args3Tab.add(allPlayer.getName());
        }

        if(args.length == 1)
            return args1Tab.stream().filter(s -> s.startsWith(args[0])).collect(Collectors.toList());

        if(args.length == 2 && args[0].equals("rank"))
            return args2TabRank.stream().filter(s -> s.startsWith(args[0])).collect(Collectors.toList());;

        if(args.length == 2)
            return ProxyServer.getInstance().getServers().values().stream().map(ServerInfo::getName).filter(s -> s.startsWith(args[1])).collect(Collectors.toList());

        if(args.length == 3)
            return args3Tab.stream().filter(s -> s.startsWith(args[0])).collect(Collectors.toList());;

        return Collections.emptyList();
    }
}
