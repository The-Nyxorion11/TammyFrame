package utils;

import org.bukkit.ChatColor;

public class TammyUtils {
    public static String messageColor(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
