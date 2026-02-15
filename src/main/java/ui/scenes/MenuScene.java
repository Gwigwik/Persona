package ui.scenes;

import app.SceneManager;
import app.SceneType;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class MenuScene {

    private final Scene scene;

    public MenuScene(SceneManager sceneManager) {
	    BorderPane globalPane = new BorderPane();
	    globalPane.setPrefSize(1400, 800);
	    globalPane.setStyle("-fx-background-color: grey;");
	    globalPane.setOnMouseClicked(event -> {
		    if (event.getButton() == MouseButton.PRIMARY) {
		    	sceneManager.switchTo(SceneType.INITIALSETTINGS);
		    }
		});
	    
	    Label title = new Label("Persona 6/7");
	    title.setStyle("-fx-text-fill: white; -fx-background-color: black; -fx-border-color: white; -fx-border-width: 2; -fx-padding: 16 32 16 32;");
	    globalPane.widthProperty().addListener((_, _, newW) -> {
	    	title.setFont(Font.font(newW.doubleValue() * .1));
		});
	    globalPane.setCenter(title);

        scene = new Scene(globalPane);
    }

    public Scene getScene() {
        return scene;
    }
}
