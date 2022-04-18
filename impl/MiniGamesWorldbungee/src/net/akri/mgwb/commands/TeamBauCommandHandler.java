package net.akri.mgwb.commands;

import net.akri.mgwb.Bungee;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.connection.ConnectedPlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class TeamBauCommandHandler extends Command implements TabExecutor {

    public TeamBauCommandHandler() {
        super("teambau");
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("rank.builder")) {
            if (args.length == 2) {
                if (args[0].equals("start")) {

                    if (args[1].equals("BW") || args[1].equals("BedWars") || args[1].equals("SkyWars") || args[1].equals("SW")) {
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "cl ser bau-bw-1 start");
                        sender.sendMessage(Bungee.prefix + " §7Der Bau Server §6Teambau-BedWars §7startet!");

                    } else if (args[1].equals("GunGame") || args[1].equals("GG")) {
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "cl ser bau-gungame-1 start");
                        sender.sendMessage(Bungee.prefix + " §7Der Bau Server §6Teambau-GunGame §7startet!");

                    } else if (args[1].equals("Generell") || args[1].equals("Normal")) {

                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "cl ser bau-generell-1 start");
                        sender.sendMessage(Bungee.prefix + " §7Der Bau Server §6Teambau-Normal §7startet!");

                    } else if (args[1].equals("Murder") || args[1].equals("Mörder")) {
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "cl ser bau-murder-1 start");
                        sender.sendMessage(Bungee.prefix + " §7Der Bau Server §6Teambau-Mörder §7startet!");

                    } else if (args[1].equals("WG") || args[1].equals("WarGear")) {
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "cl ser bau-wargear-1 start");
                        sender.sendMessage(Bungee.prefix + " §7Der Bau Server §6Teambau-WarGear §7startet!");

                    } else
                        sender.sendMessage(Bungee.prefix + " §cBitte setze einen Server §7(BedWars/SkyWars/GunGame/Murder/Generell/WarGear)");

                } else if (args[0].equals("join")) {
                    if (args[1].equals("BW") || args[1].equals("BedWars") || args[1].equals("SkyWars") || args[1].equals("SW")) {
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "send " + sender.getName() + " bau-bw-1");
                        sender.sendMessage(Bungee.prefix + " §7du wirst zu dem Bau: §6Teambau-BedWars §7gesendet...");

                    } else if (args[1].equals("GunGame") || args[1].equals("GG")) {
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "send " + sender.getName() + " bau-gungame-1");
                        sender.sendMessage(Bungee.prefix + " §7du wirst zu dem Bau: §6Teambau-GunGame §7gesendet...");

                    } else if (args[1].equals("Generell") || args[1].equals("Normal")) {
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "send " + sender.getName() + " bau-generell-1");
                        sender.sendMessage(Bungee.prefix + " §7du wirst zu dem Bau: §6Teambau-General §7gesendet...");
                    } else if (args[1].equals("Murder") || args[1].equals("Mörder")) {
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "send " + sender.getName() + " bau-murder-1");
                        sender.sendMessage(Bungee.prefix + " §7du wirst zu dem Bau: §6Teambau-Mörder §7gesendet...");

                    } else if (args[1].equals("WG") || args[1].equals("WarGear")) {
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "send " + sender.getName() + " bau-wargear-1");
                        sender.sendMessage(Bungee.prefix + " §7Du wirst zum dem Bau: §6Teambau-WarGear §7gesendet...!");

                    } else
                        sender.sendMessage(Bungee.prefix + " §cBitte setze einen Server §7(BedWars/SkyWars/GunGame/Murder/Generell/WarGear)");

                } else
                    sender.sendMessage(Bungee.prefix + " " + ChatColor.RED + "Error(Bungee:F0001) " + ChatColor.GRAY + "INVALID_ARGUMENT");

            } else if (args.length == 3) {
                if (args[0].equals("send")) {
                    String target = args[1];

                    if (args[2].equals("BW") || args[1].equals("BedWars") || args[1].equals("SkyWars") || args[1].equals("SW")) {
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "send " + target + " bau-bw-1");
                        sender.sendMessage(Bungee.prefix + "§7du wirst zu dem Bau: §6Teambau-BedWars gesendet...");

                    } else if (args[2].equals("GunGame") || args[1].equals("GG")) {
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "send " + target + " bau-gungame-1");
                        sender.sendMessage(Bungee.prefix + "§7du wirst zu dem Bau: §6Teambau-GunGame gesendet...");

                    } else if (args[2].equals("Generell") || args[1].equals("Normal")) {
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "send " + target + " bau-generell-1");
                        sender.sendMessage(Bungee.prefix + "§7du wirst zu dem Bau: §6Teambau-General gesendet...");

                    } else if (args[2].equals("Murder") || args[1].equals("Mörder")) {
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "send " + target + " bau-murder-1");
                        sender.sendMessage(Bungee.prefix + "§7du wirst zu dem Bau: §6Teambau-Mörder gesendet...");

                    } else if (args[1].equals("WG") || args[1].equals("WarGear")) {
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "send " + target + " bau-wargear-1");
                        sender.sendMessage(Bungee.prefix + " §7Du wirst zum dem Bau: §6Teambau-WarGear §7gesendet...!");

                    } else
                        sender.sendMessage(Bungee.prefix + " §cBitte setze einen Server §7(BedWars/SkyWars/GunGame/Murder/Generell/WarGear)");

                } else
                    sender.sendMessage(Bungee.prefix + " " + ChatColor.RED + "Error(Bungee:F0001) " + ChatColor.GRAY + "INVALID_ARGUMENT");
            } else
                sender.sendMessage(Bungee.prefix + " " + ChatColor.RED + "Error(Bungee:F0001) " + ChatColor.GRAY + "INVALID_ARGUMENT");
        } else
            sender.sendMessage(Bungee.prefix + " " + ChatColor.RED + "Du hast keine Rechte um den Command zu nutzen! \n" + ChatColor.GRAY + "Error(Bungee:F0002) " + ChatColor.GRAY + "NO_PERMISSION");
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        ArrayList<String> arg1Tab = new ArrayList<String>();
        ArrayList<String> arg2Tab = new ArrayList<String>();
        ArrayList<String> arg3Tab = new ArrayList<String>();
        ArrayList<String> playerTab = new ArrayList<String>();

        arg1Tab.add("start");
        arg1Tab.add("join");
        arg1Tab.add("send");

        arg2Tab.add("WarGear");
        arg2Tab.add("Murder");
        arg2Tab.add("Mörder");
        arg2Tab.add("Generell");
        arg2Tab.add("Normal");
        arg2Tab.add("GunGame");
        arg2Tab.add("GG");
        arg2Tab.add("SkyWars");
        arg2Tab.add("BedWars");
        arg2Tab.add("SW");
        arg2Tab.add("BW");

        for(ProxiedPlayer allPlayer: BungeeCord.getInstance().getPlayers()){
            playerTab.add(allPlayer.getName());
        }

        arg3Tab.add("WarGear");
        arg3Tab.add("Murder");
        arg3Tab.add("Mörder");
        arg3Tab.add("Generell");
        arg3Tab.add("Normal");
        arg3Tab.add("GunGame");
        arg3Tab.add("GG");
        arg3Tab.add("SkyWars");
        arg3Tab.add("BedWars");
        arg3Tab.add("SW");
        arg3Tab.add("BW");

        if (args.length == 1)
            return arg1Tab.stream().filter(s -> s.startsWith(args[0])).collect(Collectors.toList());;

        if(args.length == 2 || !args[0].equals("send"))
            return arg2Tab.stream().filter(s -> s.startsWith(args[0])).collect(Collectors.toList());;

        if(args.length == 2 || args[0].equals("send"))
            return playerTab.stream().filter(s -> s.startsWith(args[0])).collect(Collectors.toList());

        if(args.length == 3 && args[0].equals("send"))
            return arg3Tab.stream().filter(s -> s.startsWith(args[0])).collect(Collectors.toList());;

        return Collections.emptyList();
    }
}
