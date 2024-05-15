package fr.kenda.randomgenerator.utils;

import fr.kenda.randomgenerator.RandomGenerator;
import org.bukkit.ChatColor;

public class Messages {
    private static final RandomGenerator instance = RandomGenerator.getInstance();

    /**
     * Get prefix of plugin
     *
     * @return String
     */
    public static String getPrefix() {
        return transformColor(instance.getConfig().getString("prefix"));
    }


    /**
     * Transform message with color
     *
     * @param message String
     * @return String
     */
    public static String transformColor(final String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
