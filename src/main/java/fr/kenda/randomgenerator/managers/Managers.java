package fr.kenda.randomgenerator.managers;

import java.util.HashMap;
import java.util.Map;

public class Managers {

    private final Map<Class<? extends IManager>, IManager> managers = new HashMap<>();

    public Managers() {
        registerManager(CommandManager.class, new CommandManager());

        managers.forEach((aClass, iManager) -> iManager.register());
    }

    <T extends IManager> void registerManager(Class<T> managerClass, T managerInstance) {
        managers.putIfAbsent(managerClass, managerInstance);
    }

}
