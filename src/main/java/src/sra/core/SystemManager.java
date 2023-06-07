package src.sra.core;

import src.sra.logging.Log;
import src.sra.utils.types.TypeUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SystemManager {

    private ApplicationSystem[] systems;
    private Log log;

    public SystemManager() {
        systems = new ApplicationSystem[]{
                this.log = createSystem(Log.class)
        };
    }

    private <T extends ApplicationSystem> T createSystem(Class<T> clazz) {
        T system = TypeUtils.newInstance(clazz, this);
        TypeUtils.initSingleton(clazz, system);
        return system;
    }

    public void init() throws Throwable {
        // here we will initialize all systems
        Throwable error;
        for (ApplicationSystem system : systems) {
            error = initializeSystem(system);
            if (error != null) {
                throw error;
            }
        }
    }

    private Throwable initializeSystem(ApplicationSystem system) {
        try {
            system.init();
            system.markAsInitialized();
        } catch (Throwable error) {
            Logger.getLogger(SystemManager.class.getSimpleName())
                    .log(Level.SEVERE, "Failed to initialize system" + system.getClass().getSimpleName(), error);
            return error;
        }
        return null;
    }

    public void terminate() {
        for (ApplicationSystem system : systems) {
            terminate(system);
        }
    }

    private void terminate(ApplicationSystem system) {
        // need to check later if the system is not null and initialized
        system.terminate();
    }
}
