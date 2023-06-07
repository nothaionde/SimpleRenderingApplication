import src.sra.core.Application;
import src.sra.core.EntryPoint;
import src.sra.logging.Log;
import src.sra.scene.Scene;

public class Main extends Application {

    public static void main(String[] args) {
        EntryPoint.launch(new Main());
    }

    @Override
    protected void onStart(Scene scene) {
        Log.info("Application started");
    }
}
