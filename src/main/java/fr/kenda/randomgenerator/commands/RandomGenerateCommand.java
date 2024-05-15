package fr.kenda.randomgenerator.commands;

import fr.kenda.randomgenerator.RandomGenerator;
import fr.kenda.randomgenerator.utils.Config;
import fr.kenda.randomgenerator.utils.ItemBuilder;
import fr.kenda.randomgenerator.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RandomGenerateCommand implements CommandExecutor {

    private final FileConfiguration config = RandomGenerator.getInstance().getConfig();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {


        if (args.length != 3) {
            sendHelp(commandSender);
            return false;
        }

        String category = args[0];
        String playerName = args[2];
        if (!isCategoryExist(category)) {
            commandSender.sendMessage("La catégorie \"" + category + "\" n'existe pas.");
            return false;
        }

        int amount;
        try {
            amount = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            commandSender.sendMessage(Messages.transformColor("&cVous devez spécifiquer un chiffre/nombre."));
            return false;
        }

        Player target = Bukkit.getPlayer(playerName);
        if(target == null)
        {
            commandSender.sendMessage(Messages.transformColor("&cLe joueur " + playerName + " n'est pas connectée"));
            return false;
        }
        if (getParentCategory(category).equalsIgnoreCase("items")) {
            Material mat = Config.getRandomMaterialFromCategory(category);
            if (mat == null) {
                commandSender.sendMessage(Messages.transformColor("&cLe materiau n'as pas été trouvé."));
                return false;
            }
            //player.getWorld().dropItemNaturally(player.getLocation(), new ItemBuilder(mat, amount).toItemStack());
            target.getInventory().addItem(new ItemBuilder(mat, amount).toItemStack());
            return true;
        }
        else if (getParentCategory(category).equalsIgnoreCase("summon")) {
            EntityType entity = Config.getRandomEntityFrom(category);
            if (entity == null) {
                commandSender.sendMessage(Messages.transformColor("&cLe type d'entité n'as pas été trouvé."));
                return false;
            }
            for (int i = 0; i < amount; i++)
                target.getWorld().spawnEntity(target.getLocation(), entity);
            return true;
        }
        return false;
    }

    private void sendHelp(CommandSender player) {
        List<String> keys = getKeys("items");
        List<String> keysSummon = getKeys("summon");

        keys.addAll(keysSummon);

        String categories = String.join("/", keys);
        /*StringBuilder sb = new StringBuilder();
        int size = keys.size();

        for (int i = 0; i < size; i++)
        {
            sb.append(Messages.transformColor("&7")).append(keys.get(i));
            if (i != size - 1) sb.append("/");
        }*/

        player.sendMessage(Messages.transformColor("&c/rd <category> <amount> <target>: &7Permet de faire spawn/give sur une quantité."));
        player.sendMessage(Messages.transformColor("&c&nCatégorie(s):&r &7" + categories));
    }

    private boolean isCategoryExist(String category) {
        List<String> keys = getKeys("items");
        List<String> keysMobs = getKeys("summon");

        return keys.contains(category) || keysMobs.contains(category);
    }

    private String getParentCategory(String category) {
        List<String> keys = getKeys("items");
        List<String> keysMobs = getKeys("summon");

        if (keys.contains(category))
            return "items";
        else if (keysMobs.contains(category))
            return "summon";
        else return "";
    }

    private List<String> getKeys(String category) {
        return new ArrayList<>(config.getConfigurationSection("category." + category).getKeys(true));
    }
}
