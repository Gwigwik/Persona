package ui.scenes;

import java.util.List;

import app.SceneManager;
import app.SceneType;
import entities.Character;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;

public class InitialSettingsScene {

    private final Scene scene;

    public InitialSettingsScene(SceneManager sceneManager, List<Character> characters) {
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
	    easyButton.prefWidthProperty().bind(settingsPane.widthProperty().multiply(.1));
	    easyButton.prefHeightProperty().bind(settingsPane.heightProperty().multiply(.1));
	    settingsPane.widthProperty().addListener((_, _, newW) -> {
			easyButton.setFont(Font.font("Arial", FontWeight.BOLD, newW.doubleValue() * .02));
		});
		BorderPane easyPane = new BorderPane();
		easyPane.setStyle("-fx-background-color: white;");
		Label easyLabel = new Label(" [RECOMMANDÉ] Les résistances des ennemis sont dévoilées dès le début du combat ");
		easyLabel.setFont(Font.font(16));
		easyLabel.setTextFill(Color.BLACK);
		easyPane.setCenter(easyLabel);
		Popup easyPopup = new Popup();
		easyPopup.getContent().add(easyPane);
		easyPopup.setAutoHide(false);
		easyButton.addEventHandler(MouseEvent.MOUSE_ENTERED, _ -> {
			easyPopup.show(easyButton.getScene().getWindow());
		});
		easyButton.addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
			easyPopup.setX(e.getScreenX() + 20);
			easyPopup.setY(e.getScreenY());
		});
		easyButton.addEventHandler(MouseEvent.MOUSE_EXITED, _ -> {
			easyPopup.hide();
		});
		
		Region spacer = new Region();
		spacer.prefHeightProperty().bind(settingsPane.heightProperty().multiply(.1));
		
	    Button hardButton = new Button("Difficile");
	    hardButton.setOnMouseClicked(event -> {
		    if (event.getButton() == MouseButton.PRIMARY) {
		    	characters.forEach((character) -> {
		    		character.hideResistances();
		    	});
		    	sceneManager.switchTo(SceneType.SPELLKITSELECTION);
		    }
		});
	    hardButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
	    hardButton.setFocusTraversable(false);
	    hardButton.prefWidthProperty().bind(settingsPane.widthProperty().multiply(.1));
	    hardButton.prefHeightProperty().bind(settingsPane.heightProperty().multiply(.1));
	    settingsPane.widthProperty().addListener((_, _, newW) -> {
	    	hardButton.setFont(Font.font("Arial", FontWeight.BOLD, newW.doubleValue() * .02));
		});
	    BorderPane hardPane = new BorderPane();
	    hardPane.setStyle("-fx-background-color: white;");
		Label hardLabel = new Label(" Les résistances des ennemis sont inconnues jusqu'à ce que vous les frappiez dans un élément ");
		hardLabel.setFont(Font.font(16));
		hardLabel.setTextFill(Color.BLACK);
		hardPane.setCenter(hardLabel);
		Popup hardPopoup = new Popup();
		hardPopoup.getContent().add(hardPane);
		hardPopoup.setAutoHide(false);
		hardButton.addEventHandler(MouseEvent.MOUSE_ENTERED, _ -> {
			hardPopoup.show(hardButton.getScene().getWindow());
		});
		hardButton.addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
			hardPopoup.setX(e.getScreenX() + 20);
			hardPopoup.setY(e.getScreenY());
		});
		hardButton.addEventHandler(MouseEvent.MOUSE_EXITED, _ -> {
			hardPopoup.hide();
		});
	    settingsPane.getChildren().addAll(easyButton, spacer, hardButton);
	    globalPane.setCenter(settingsPane);

        scene = new Scene(globalPane);
    }

    public Scene getScene() {
        return scene;
    }
}
