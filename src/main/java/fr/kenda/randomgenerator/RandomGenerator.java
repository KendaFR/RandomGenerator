package fr.kenda.randomgenerator;

import fr.kenda.randomgenerator.managers.Managers;
import fr.kenda.randomgenerator.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class RandomGenerator extends JavaPlugin {

    private static RandomGenerator instance;
    private Managers managers;
    @Override
    public void onEnable()
    {
        instance = this;

        saveDefaultConfig();

        managers = new Managers();

        Bukkit.getConsoleSender().sendMessage(Messages.transformColor(
                "\n§a########################## " + "\n" +
                        "§a# " + Messages.getPrefix() + " §a#" + "\n" +
                        "§a# Merci de m'avoir fait confiance :D #" + "\n" +
                        "§a# Kenda #" + "\n" +
                        "§a##########################"));

    }

    @Override
    public void onDisable()
    {

    }

    public static RandomGenerator getInstance() {
        return instance;
    }
}
