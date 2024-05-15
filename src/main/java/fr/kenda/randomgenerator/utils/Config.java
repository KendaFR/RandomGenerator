package fr.kenda.randomgenerator.utils;

import fr.kenda.randomgenerator.RandomGenerator;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("all")
public class Config {
    private static final RandomGenerator INSTANCE = RandomGenerator.getInstance();
    private static final FileConfiguration CONFIG = INSTANCE.getConfig();

    /**
     * get an int from config
     *
     * @param path path in file
     * @return Integer
     */
    public static int getInt(String path) {
        return INSTANCE.getConfig().getInt(path);
    }


    /**
     * Return a material from config file
     *
     * @param path String
     * @return Material
     */
    public static Material getMaterial(String path) {
        String str = CONFIG.getString(path);
        Material mat = Material.getMaterial(str);
        if (mat != null) return mat;
        return Material.BARRIER;
    }

    /**
     * Return a list of String from config file
     *
     * @param path String
     * @return List<String>
     */
    public static List<String> getList(String path) {
        List<String> lores = CONFIG.getStringList(path);
        List<String> colorLores = new ArrayList<>();
        lores.forEach(s -> colorLores.add(Messages.transformColor(s)));
        return colorLores;
    }

    /**
     * Return list and replace args by value
     *
     * @param path String
     * @param args String...
     * @return List<String>
     */
    public static List<String> getList(String path, String... args) {
        List<String> lores = CONFIG.getStringList(path);
        List<String> colorLores = new ArrayList<>();


        for (int i = 0; i < lores.size(); i++) {
            String line = lores.get(i);
            for (int y = 0; y < args.length; y += 2) {
                line = line.replace(args[y], args[y + 1]);
            }
            colorLores.add(Messages.transformColor(line));
        }
        return colorLores;
    }

    /**
     * Return a name from config file
     *
     * @param path String
     * @return String
     */
    public static String getString(String path, String... args) {
        String configStr = CONFIG.getString(path);
        if (configStr == null) return "";
        for (int i = 0; i < args.length; i += 2)
            configStr = configStr.replace(args[i], args[i + 1]);
        return Messages.transformColor(configStr);
    }

    /**
     * Get a boolean from config file
     *
     * @param path path in file
     * @return Boolean
     */
    public static boolean getBoolean(String path) {
        return CONFIG.getBoolean(path);
    }

    /**
     * Get a random material from category
     * @param category String
     * @return Material (or null)
     */
    public static Material getRandomMaterialFromCategory(String category)
    {
        List<String> list = CONFIG.getStringList("category.items." + category);
        Random random = new Random();
        int rand = random.nextInt(list.size());
        Material mat = Material.valueOf(list.get(rand));
        if(mat == null) return null;
        return mat;
    }

    /**
     * Get a random Entity from category
     * @param category String
     * @return EntityType
     */
    public static EntityType getRandomEntityFrom(String category) {
        List<String> list = CONFIG.getStringList("category.summon." + category);
        Random random = new Random();
        int rand = random.nextInt(list.size());
        System.out.println(list.get(rand));
        EntityType entityType = EntityType.valueOf(list.get(rand));
        if (entityType == null) return null;
        return entityType;
    }
}
