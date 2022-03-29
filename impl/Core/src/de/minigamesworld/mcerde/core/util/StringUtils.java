package de.minigamesworld.mcerde.core.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Random;

public class StringUtils {

    private final static char[] RANDOM_STRING_CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789-_".toCharArray();
    private final static Random random = new Random();

    public static String randomString(int length) {
        StringBuilder stringBuilder = new StringBuilder(length);

        for(int i = 0; i < length; i++) {
            stringBuilder.append(RANDOM_STRING_CHARACTERS[random.nextInt(RANDOM_STRING_CHARACTERS.length)]);
        }

        return stringBuilder.toString();
    }

    public static String color(String msg){
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static void msgPlayer(Player player, String... strings){
        for(String msg : strings){
            player.sendMessage(color(msg));
        }
    }

}
