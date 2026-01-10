package ui.scenes;

import entities.CharacterElement;
import game.BattleManager;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BattleScene {
	
//	Pour plus tard, quand je voudrai que différent pane puissent occuper le meme mais sans cohabiter t'as capté le rectangle rose
//	pane1.setVisible(false);
//	pane1.setManaged(false);
	
	private final Scene scene;
//	private final BattleManager battleManager;

    public Scene getScene() {
        return scene;
    }

	private String redBorderStyle() {
		return "-fx-border-color: red; -fx-border-width: 2; -fx-border-style: solid;";
	}

	private String greenBorderStyle() {
		return "-fx-border-color: green; -fx-border-width: 2; -fx-border-style: solid;";
	}

	
    public BattleScene(BattleManager battleManagerParam) {
//    	this.battleManager = battleManagerParam;
    	
	    
	    HBox globalPane = new HBox();
	    globalPane.setPrefSize(1400, 800);
	    
	    //------------------------------ Attacks, persona, defend, stats, elements... ------------------------------
	    VBox leftPane = getLeftPane(globalPane);
	    
	    Region globalPaneSpacer = new Region();
        globalPaneSpacer.prefWidthProperty().bind(globalPane.widthProperty().multiply(0.10));
	    
	    //------------------------------ Characters display ------------------------------
	    VBox rightPane = getRightPane(globalPane);
	    
	    globalPane.getChildren().addAll(leftPane, globalPaneSpacer, rightPane);
	
	    scene = new Scene(globalPane);
    }

	private VBox getLeftPane(HBox globalPane) {
		VBox leftPane = new VBox();
	    leftPane.prefWidthProperty().bind(globalPane.widthProperty().multiply(0.45));
	    leftPane.setStyle(redBorderStyle());
		return leftPane;
	}

	private VBox getRightPane(HBox globalPane) {
		VBox rightPane = new VBox();
		rightPane.prefWidthProperty().bind(globalPane.widthProperty().multiply(0.45));
		rightPane.setStyle(redBorderStyle());
		
		HBox enemiesPane = getEnemiesPane(rightPane);
		
		Region rightPaneSpacer = new Region();
		rightPaneSpacer.prefHeightProperty().bind(rightPane.heightProperty().multiply(0.4));
	
		HBox alliesPane = getAlliesPane(rightPane);
		
		rightPane.getChildren().addAll(enemiesPane, rightPaneSpacer, alliesPane);
		return rightPane;
	}


	private HBox getEnemiesPane(VBox rightPane) {
		HBox enemiesPane = new HBox();
		enemiesPane.prefHeightProperty().bind(rightPane.heightProperty().multiply(.3));
		enemiesPane.setStyle(greenBorderStyle());
		
		Region enemiesPaneSpacer1 = getEnemiesPaneSpacer(enemiesPane);
		VBox enemyPane1 = getEnemyPane(enemiesPane);
		Region enemiesPaneSpacer2 = getEnemiesPaneSpacer(enemiesPane);
		VBox enemyPane2 = getEnemyPane(enemiesPane);
		Region enemiesPaneSpacer3 = getEnemiesPaneSpacer(enemiesPane);
		VBox enemyPane3 = getEnemyPane(enemiesPane);
		Region enemiesPaneSpacer4 = getEnemiesPaneSpacer(enemiesPane);
		
		
		
		enemiesPane.getChildren().addAll(enemiesPaneSpacer1, enemyPane1, enemiesPaneSpacer3, enemyPane2, enemiesPaneSpacer2, enemyPane3, enemiesPaneSpacer4);
		return enemiesPane;
	}

	private Region getEnemiesPaneSpacer(HBox enemiesPane) {
		Region enemiesPaneSpacer = new Region();
		enemiesPaneSpacer.prefWidthProperty().bind(enemiesPane.widthProperty().multiply(0.025));
		return enemiesPaneSpacer;
	}

	private VBox getEnemyPane(HBox enemiesPane) {
		VBox enemyPane = new VBox();
		enemyPane.prefWidthProperty().bind(enemiesPane.widthProperty().multiply(0.3));
		enemyPane.setStyle(redBorderStyle());
		
		Pane healthBarPane = new Pane();
		healthBarPane.prefWidthProperty().bind(enemyPane.widthProperty().multiply(.8));
		healthBarPane.setPrefHeight(30);
		
		Rectangle healthBar = new Rectangle();
		healthBar.widthProperty().bind(healthBarPane.widthProperty());
		healthBar.heightProperty().bind(healthBarPane.heightProperty());
		healthBar.setFill(Color.PURPLE);
		
		healthBarPane.getChildren().add(healthBar);
		
		Region enemyPaneSpacer = new Region();
		enemyPaneSpacer.prefHeightProperty().bind(enemyPane.heightProperty().multiply(.2));
		
		Pane enemyIconPane = new Pane();
		enemyIconPane.prefWidthProperty().bind(enemyPane.widthProperty());
		enemyIconPane.prefHeightProperty().bind(enemyPane.heightProperty().multiply(.7));
		enemyIconPane.setStyle(greenBorderStyle());
		ImageView enemyIconImageView = ui.IconProvider.getCharacterElementIcon(CharacterElement.FIRE, 1);
		enemyIconImageView.setPreserveRatio(true);
		enemyIconImageView.fitWidthProperty().bind(enemyIconPane.widthProperty());
		enemyIconImageView.fitHeightProperty().bind(enemyIconPane.heightProperty());
		enemyIconPane.getChildren().addAll(enemyIconImageView);
		
		enemyPane.getChildren().addAll(healthBarPane, enemyPaneSpacer, enemyIconPane);
		return enemyPane;
	}
	
	private HBox getAlliesPane(VBox rightPane) {
		HBox alliesPane = new HBox();
		alliesPane.prefHeightProperty().bind(rightPane.heightProperty().multiply(.3));
		alliesPane.setStyle(greenBorderStyle());
		return alliesPane;
	}
}
