package ui.scenes;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class CreditsScene {

    private final Scene scene;

    public CreditsScene() {
	    BorderPane globalPane = new BorderPane();
	    globalPane.setPrefSize(1400, 800);
	    globalPane.setStyle("-fx-background-color: grey;");
	    globalPane.setOnMouseClicked(event -> {
		    if (event.getButton() == MouseButton.PRIMARY) {
		    	Platform.exit();
		    }
		});
	    
	    Label title = new Label("Persona 6/7");
	    title.setStyle("-fx-text-fill: white; -fx-background-color: black; -fx-border-color: white; -fx-border-width: 2; -fx-padding: 16 32 16 32;");
	    globalPane.widthProperty().addListener((_, _, newW) -> {
	    	title.setFont(Font.font(newW.doubleValue() * .1));
		});
	    
	    Label merci = new Label("Merci aux Beta testers : Neoli");
	    merci.setStyle("-fx-text-fill: white; -fx-background-color: black; -fx-border-color: white; -fx-border-width: 2; -fx-padding: 16 32 16 32;");
	    
	    globalPane.setCenter(title);
	    globalPane.setBottom(merci);

        scene = new Scene(globalPane);
    }

    public Scene getScene() {
        return scene;
    }
}
