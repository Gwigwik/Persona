package ui.scenes;

import game.BattleManager;
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
    private Label enemyHpLabel;

    public BattleScene(BattleManager battleManager) {
    	this.battleManager = battleManager;
    	
    	BorderPane root = new BorderPane();
	    root.setPrefSize(1400, 800);
	    
	    HBox topBar = new HBox(20);
	    topBar.setAlignment(Pos.CENTER);
	    playerHpLabel = new Label("Player HP: " + this.battleManager.getCurrentCharacter().getCurrentHP() + "/" + this.battleManager.getCurrentCharacter().getMaxHP());
	    enemyHpLabel = new Label("Enemy HP: 80");
	    playerHpLabel.setFont(Font.font(18));
	    enemyHpLabel.setFont(Font.font(18));
	    topBar.getChildren().addAll(playerHpLabel, enemyHpLabel);
	    root.setBottom(topBar);
	
	    scene = new Scene(root);
    }

    public Scene getScene() {
        return scene;
    }
}
