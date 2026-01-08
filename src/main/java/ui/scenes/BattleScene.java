package ui.scenes;

import game.BattleManager;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class BattleScene {
	
//	Pour plus tard, quand je voudrai que différent pane puissent occuper le meme mais sans cohabiter t'as capté le rectangle rose
//	pane1.setVisible(false);
//	pane1.setManaged(false);
	
	private final Scene scene;
	private final BattleManager battleManager;

    public Scene getScene() {
        return scene;
    }
    
//    private Label playerHpLabel;
    public BattleScene(BattleManager battleManagerParam) {
    	this.battleManager = battleManagerParam;
    	
//    	BorderPane root = new BorderPane();
//	    root.setPrefSize(1400, 800);
//	    HBox topBar = new HBox(20);
//	    topBar.setAlignment(Pos.CENTER);
//	    playerHpLabel = new Label();
//	    playerHpLabel.textProperty().bind(
//    	    Bindings.createStringBinding(
//    	            () -> String.format(
//    	                "Player HP: %d/%d",
//    	                battleManager.getCurrentCharacter().getCurrentHP(),
//    	                battleManager.getCurrentCharacter().getMaxHP()
//    	            ),
//    	            battleManager.getCurrentCharacter().currentHPProperty()
//    	    )
//    	);
//	    playerHpLabel.setFont(Font.font(18));
//	    topBar.getChildren().addAll(playerHpLabel);
//	    root.setCenter(topBar);
	    
	    HBox globalPane = new HBox();
	    globalPane.setPrefSize(1400, 800);
	    
	    //------------------------------ Attacks, persona, defend, stats, elements... ------------------------------
	    VBox leftPane = new VBox();
	    leftPane.prefWidthProperty().bind(globalPane.widthProperty().multiply(0.45));
	    leftPane.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-border-style: solid;");
	    
	    Region globalPaneSpacer = new Region();
        globalPaneSpacer.prefWidthProperty().bind(globalPane.widthProperty().multiply(0.10));
	    
	    //------------------------------ Characters display... ------------------------------
	    VBox rightPane = new VBox();
	    rightPane.prefWidthProperty().bind(globalPane.widthProperty().multiply(0.45));
	    rightPane.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-border-style: solid;");
	    
	    globalPane.getChildren().addAll(leftPane, globalPaneSpacer, rightPane);
	
	    scene = new Scene(globalPane);
    }

}
