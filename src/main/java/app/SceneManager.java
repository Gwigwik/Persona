package app;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.EnumMap;
import java.util.Map;

public class SceneManager {

    private final Stage stage;
    private final Map<SceneType, Scene> scenes = new EnumMap<>(SceneType.class);

    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    public void addScene(SceneType type, Scene scene) {
        scenes.put(type, scene);
    }

    public void switchTo(SceneType type) {
        Scene scene = scenes.get(type);
        if (scene != null) {
            double width = stage.getWidth();
            double height = stage.getHeight();

            stage.setScene(scene);

            stage.setWidth(width);
            stage.setHeight(height);

            stage.show();
        } else {
            System.err.println("Scene '" + type + "' not found");
        }
    }
}
