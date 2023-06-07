package src.sra.core;

import src.sra.logging.Log;
import src.sra.scene.Scene;
import src.sra.utils.types.Singleton;

public abstract class Application {

    @Singleton
    private static Application instance;

    protected abstract void onStart(Scene scene);

    public void start(Scene scene) {
        onStart(scene);
    }

    protected void onTerminate() {

    }

    protected void onError(Throwable error) {
        Log.error("Application error: " + errorMessage(error), error);
    }

    private String errorMessage(Throwable error) {
        return error.getMessage() == null ? "" : error.getMessage();
    }
}
