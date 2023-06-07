package src.sra.core;

import src.sra.graphics.rendering.RenderAPI;
import src.sra.scene.Scene;
import src.sra.utils.types.TypeUtils;

public final class EntryPoint {

    private RenderAPI renderAPI;
    private SystemManager systemManager;
    private final Application application;

    public EntryPoint(Application application) {
        this.application = application;
        TypeUtils.initSingleton(EntryPoint.class, application);
        systemManager = new SystemManager();
    }

    public static synchronized void launch(Application application) {
        EntryPoint entryPoint = new EntryPoint(application);
        try {
            entryPoint.init();
            entryPoint.run();
            entryPoint.terminate();
        } catch (Throwable error) {
            entryPoint.error(error);
        }
    }

    private void error(Throwable error) {
        application.onError(error);
    }

    private void terminate() {
        application.onTerminate();
        systemManager.terminate();
    }

    private void run() {
        application.start(getStartingScene());
    }


    private void init() throws Throwable {
        systemManager.init();
    }

    private Scene getStartingScene() {
        // here we will set the starting scene later
        return new Scene();
    }
}
