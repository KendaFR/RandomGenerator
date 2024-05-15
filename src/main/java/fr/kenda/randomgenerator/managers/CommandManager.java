package fr.kenda.randomgenerator.managers;

import fr.kenda.randomgenerator.RandomGenerator;
import fr.kenda.randomgenerator.commands.RandomGenerateCommand;

public class CommandManager implements IManager {
    @Override
    public void register() {

        final RandomGenerator instance = RandomGenerator.getInstance();
        instance.getCommand("random").setExecutor(new RandomGenerateCommand());
    }
}
