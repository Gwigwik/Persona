package ui.scenes;

import app.SceneManager;
import app.SceneType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	    Label difficulté = new Label("Difficulté");
	    Label textSpeed = new Label("Vitesse du texte");
	    Button continueButton = new Button("Continuer");
	    continueButton.setOnMouseClicked(event -> {
		    if (event.getButton() == MouseButton.PRIMARY) {
		    	sceneManager.switchTo(SceneType.RULES);
		    }
		});
	    continueButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
	    continueButton.setFocusTraversable(false);
	    settingsPane.getChildren().addAll(difficulté, textSpeed, continueButton);
	    globalPane.setCenter(settingsPane);

        scene = new Scene(globalPane);
    }

    public Scene getScene() {
        return scene;
    }
}
