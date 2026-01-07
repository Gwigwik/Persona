package ui.scenes;

import game.BattleManager;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class BattleScene {
	
	private final Scene scene;
	private final BattleManager battleManager;

    private Label playerHpLabel;
    public BattleScene(BattleManager battleManagerParam) {
    	this.battleManager = battleManagerParam;
    	
    	BorderPane root = new BorderPane();
	    root.setPrefSize(1400, 800);
	    
	    HBox topBar = new HBox(20);
	    topBar.setAlignment(Pos.CENTER);
	    
	    playerHpLabel = new Label();
	    playerHpLabel.textProperty().bind(
    	    Bindings.createStringBinding(
    	            () -> String.format(
    	                "Player HP: %d/%d",
    	                battleManager.getCurrentCharacter().getCurrentHP(),
    	                battleManager.getCurrentCharacter().getMaxHP()
    	            ),
    	            battleManager.getCurrentCharacter().currentHPProperty()
    	    )
    	);
	    
	    playerHpLabel.setFont(Font.font(18));
	    topBar.getChildren().addAll(playerHpLabel);
	    root.setCenter(topBar);
	
	    scene = new Scene(root);
    }

    public Scene getScene() {
        return scene;
    }
}
