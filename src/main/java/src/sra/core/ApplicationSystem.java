package src.sra.core;

public abstract class ApplicationSystem {

    private final SystemManager systemManager;

    public ApplicationSystem(SystemManager systemManager) {
        this.systemManager = systemManager;
    }

    private boolean initialized;

    protected abstract void terminate();

    protected abstract void init();

    public final void markAsInitialized() {
        this.initialized = true;
    }
}
