package ui.scenes;

import app.SceneManager;
import app.SceneType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class InitialSettingsScene {

    private final Scene scene;

    public InitialSettingsScene(SceneManager sceneManager) {
	    BorderPane globalPane = new BorderPane();
	    globalPane.setStyle("-fx-background-color: grey;");
	    
	    VBox settingsPane = new VBox();
	    settingsPane.setAlignment(Pos.CENTER);
	    Button easyButton = new Button("Facile");
	    easyButton.setOnMouseClicked(event -> {
		    if (event.getButton() == MouseButton.PRIMARY) {
		    	sceneManager.switchTo(SceneType.SPELLKITSELECTION);
		    }
		});
	    easyButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
	    easyButton.setFocusTraversable(false);
	    Button hardButton = new Button("Difficile");
	    hardButton.setOnMouseClicked(event -> {
		    if (event.getButton() == MouseButton.PRIMARY) {
		    	sceneManager.switchTo(SceneType.SPELLKITSELECTION);
		    }
		});
	    hardButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
	    hardButton.setFocusTraversable(false);
	    settingsPane.getChildren().addAll(easyButton, hardButton);
	    globalPane.setCenter(settingsPane);

        scene = new Scene(globalPane);
    }

    public Scene getScene() {
        return scene;
    }
}
