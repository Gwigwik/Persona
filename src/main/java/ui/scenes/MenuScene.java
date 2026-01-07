package ui.scenes;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class MenuScene {

    private final Scene scene;


    public MenuScene() {
        BorderPane root = new BorderPane();

        scene = new Scene(root, 1400, 800);
    }

    public Scene getScene() {
        return scene;
    }
}
