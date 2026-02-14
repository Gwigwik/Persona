package ui.scenes;

import app.SceneManager;
import app.SceneType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class RulesScene {

    private final Scene scene;

    public RulesScene(SceneManager sceneManager) {
	    BorderPane globalPane = new BorderPane();
	    globalPane.setPrefSize(1400, 800);
	    globalPane.setStyle("-fx-background-color: grey;");
	    
	    VBox rulesPane = new VBox();
	    rulesPane.setAlignment(Pos.CENTER);
	    Label title = new Label("Bienvenue dans Persona 6/7 !\n\n"
	    		+ "Chaque perso a des resistances par élément, des stats et un kit de sorts\n"
	    		+ "Si tu touches la faiblesse d'un ennemi, il est étourdi\n"
	    		+ "Etourdir un ennemi permet de rejouer\n"
	    		+ "Survole un ennemi pour connaître ses statistiques et résistances\n"
	    		+ "En mode difficile, tu ne connais pas les résistances d'un ennemi avant de l'avoir touché avec un sort de cet élément\n"
	    		+ "Choisissez les kits de sorts pour chacun de vos personnages\n"
	    		+ "Eliminer tous les ennemis pour gagner !");

	    title.setTextFill(Color.WHITE);
	    title.setTextAlignment(TextAlignment.CENTER);
	    title.setWrapText(true);
	    title.setStyle("-fx-background-color: black;");
	    title.setPadding(new Insets(5, 10, 5, 10));
	    globalPane.widthProperty().addListener((_, _, newW) -> {
	    	title.setFont(Font.font(newW.doubleValue() * 0.017));
		});
	    Button continueButton = new Button("Continuer");
	    continueButton.setOnMouseClicked(event -> {
		    if (event.getButton() == MouseButton.PRIMARY) {
		    	sceneManager.switchTo(SceneType.SPELLKITSELECTION);
		    }
		});
	    rulesPane.getChildren().addAll(title, continueButton);
	    globalPane.setCenter(rulesPane);

        scene = new Scene(globalPane);
    }

    public Scene getScene() {
        return scene;
    }
}
