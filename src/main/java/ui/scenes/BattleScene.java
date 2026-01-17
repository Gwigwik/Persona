package ui.scenes;

import entities.CharacterElement;
import game.BattleManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class BattleScene {
	
//	Pour plus tard, quand je voudrai que différent pane puissent occuper le meme mais sans cohabiter t'as capté le rectangle rose
//	pane1.setVisible(false);
//	pane1.setManaged(false);
	
	private final Scene scene;
	private final BattleManager battleManager;

    Label ennemyName = new Label();
    Label allyName = new Label();
	
    public Scene getScene() {
        return scene;
    }

	private String redBorderStyle() {
		return "-fx-border-color: red; -fx-border-width: 2; -fx-border-style: solid;";
//		return "";
	}

	private String greenBorderStyle() {
		return "-fx-border-color: green; -fx-border-width: 2; -fx-border-style: solid;";
//		return "";
	}

	
    public BattleScene(BattleManager battleManagerParam) {
    	this.battleManager = battleManagerParam;
    	
	    HBox globalPane = new HBox();
	    globalPane.setPrefSize(1400, 800);
	    globalPane.setStyle("-fx-background-color: white;");
	    
	    //------------------------------ Attacks, persona, defend, stats, elements... ------------------------------
	    VBox leftPane = getLeftPane(globalPane);
	    
	    Region globalPaneSpacer = new Region();
        globalPaneSpacer.prefWidthProperty().bind(globalPane.widthProperty().multiply(0.10));
	    
	    //------------------------------ Characters display ------------------------------
	    VBox rightPane = getRightPane(globalPane);
	    HBox.setMargin(rightPane, new Insets(10, 0, 10, 0));
	    
	    globalPane.getChildren().addAll(leftPane, globalPaneSpacer, rightPane);
	
	    scene = new Scene(globalPane);
    }

	private VBox getLeftPane(HBox globalPane) {
		VBox leftPane = new VBox();
	    leftPane.prefWidthProperty().bind(globalPane.widthProperty().multiply(0.45));
	    leftPane.setStyle(redBorderStyle());
	    
		ennemyName.setAlignment(Pos.CENTER);
		ennemyName.prefWidthProperty().bind(leftPane.widthProperty());
		ennemyName.widthProperty().addListener((_, _, newW) -> {
		    ennemyName.setFont(Font.font(newW.doubleValue() * 0.03));
		}); 

		allyName.setAlignment(Pos.CENTER);
		allyName.prefWidthProperty().bind(leftPane.widthProperty());
		allyName.widthProperty().addListener((_, _, newW) -> {
			allyName.setFont(Font.font(newW.doubleValue() * 0.03));
		}); 

	    
	    leftPane.getChildren().addAll(ennemyName, allyName);
		return leftPane;
	}

	private VBox getRightPane(HBox globalPane) {
		VBox rightPane = new VBox();
		rightPane.prefWidthProperty().bind(globalPane.widthProperty().multiply(0.45));
		rightPane.setStyle(redBorderStyle());
		
		HBox enemiesPane = getEnemiesPane(rightPane);
		
		Region rightPaneSpacer = new Region();
		rightPaneSpacer.prefHeightProperty().bind(rightPane.heightProperty().multiply(0.3));
	
		HBox alliesPane = getAlliesPane(rightPane);
		
		rightPane.getChildren().addAll(enemiesPane, rightPaneSpacer, alliesPane);
		return rightPane;
	}

	private HBox getEnemiesPane(VBox rightPane) {
		HBox enemiesPane = new HBox();
		enemiesPane.prefHeightProperty().bind(rightPane.heightProperty().multiply(.35));
		enemiesPane.setStyle(greenBorderStyle());
		
		Region enemiesPaneSpacer1 = getEnemiesPaneSpacer(enemiesPane);
		VBox enemyPane1 = getEnemyPane(enemiesPane, 5);
		enemyPane1.visibleProperty().bind(battleManager.getICharacter(5).isAliveProperty());
		Region enemiesPaneSpacer2 = getEnemiesPaneSpacer(enemiesPane);
		VBox enemyPane2 = getEnemyPane(enemiesPane, 1);
		enemyPane2.visibleProperty().bind(battleManager.getICharacter(1).isAliveProperty());
		Region enemiesPaneSpacer3 = getEnemiesPaneSpacer(enemiesPane);
		VBox enemyPane3 = getEnemyPane(enemiesPane, 3);
		enemyPane3.visibleProperty().bind(battleManager.getICharacter(3).isAliveProperty());
		Region enemiesPaneSpacer4 = getEnemiesPaneSpacer(enemiesPane);
		
		enemiesPane.getChildren().addAll(enemiesPaneSpacer1, enemyPane1, enemiesPaneSpacer3, enemyPane2, enemiesPaneSpacer2, enemyPane3, enemiesPaneSpacer4);
		return enemiesPane;
	}

	private Region getEnemiesPaneSpacer(HBox enemiesPane) {
		Region enemiesPaneSpacer = new Region();
		enemiesPaneSpacer.prefWidthProperty().bind(enemiesPane.widthProperty().multiply(0.025));
		return enemiesPaneSpacer;
	}

	private VBox getEnemyPane(HBox enemiesPane, int ennemyIndex) {
		VBox enemyPane = new VBox();
		enemyPane.prefWidthProperty().bind(enemiesPane.widthProperty().multiply(0.3));
		enemyPane.setStyle(redBorderStyle());
		
		enemyPane.hoverProperty().addListener((_, _, isHover) -> {
	    	if (isHover)
	    		ennemyName.setText(battleManager.getICharacter(ennemyIndex).getName());
	    	else
	    		ennemyName.setText("prout");
	    });

		Region enemyPaneSpacer1 = new Region();
		enemyPaneSpacer1.prefHeightProperty().bind(enemyPane.heightProperty().multiply(.1));
		
		Pane healthBarPane = new Pane();
		healthBarPane.prefWidthProperty().bind(enemyPane.widthProperty().multiply(.8));
		healthBarPane.setPrefHeight(30);
		Rectangle healthBar = new Rectangle();
		healthBar.widthProperty().bind(healthBarPane.widthProperty());
		healthBar.heightProperty().bind(healthBarPane.heightProperty());
		healthBar.setFill(Color.PURPLE);
		healthBarPane.getChildren().add(healthBar);
		
		Region enemyPaneSpacer2 = new Region();
		enemyPaneSpacer2.prefHeightProperty().bind(enemyPane.heightProperty().multiply(.1));
		
		Pane enemyIconPane = new Pane();
		enemyIconPane.prefWidthProperty().bind(enemyPane.widthProperty());
		enemyIconPane.prefHeightProperty().bind(enemyPane.heightProperty().multiply(.7));
		enemyIconPane.setStyle(greenBorderStyle());
		ImageView enemyIconImageView = ui.IconProvider.getCharacterElementIcon(CharacterElement.FIRE, 1);
		enemyIconImageView.setPreserveRatio(true);
		enemyIconImageView.fitWidthProperty().bind(enemyIconPane.widthProperty());
		enemyIconImageView.fitHeightProperty().bind(enemyIconPane.heightProperty());
		enemyIconPane.getChildren().addAll(enemyIconImageView);
		
		enemyPane.getChildren().addAll(enemyPaneSpacer1, healthBarPane, enemyPaneSpacer2, enemyIconPane);
		return enemyPane;
	}
	
	private HBox getAlliesPane(VBox rightPane) {
		HBox alliesPane = new HBox();
		alliesPane.prefHeightProperty().bind(rightPane.heightProperty().multiply(.35));
		alliesPane.setStyle(greenBorderStyle());
		
		Region alliesPaneSPacer1 = getAlliesPaneSpacer(alliesPane);
		VBox allyPane1 = getAllyPane(alliesPane, 4);
		allyPane1.visibleProperty().bind(battleManager.getICharacter(4).isAliveProperty());
		Region alliesPaneSPacer2 = getAlliesPaneSpacer(alliesPane);
		VBox allyPane2 = getAllyPane(alliesPane, 0);
		allyPane2.visibleProperty().bind(battleManager.getICharacter(0).isAliveProperty());
		Region alliesPaneSPacer3 = getAlliesPaneSpacer(alliesPane);
		VBox allyPane3 = getAllyPane(alliesPane, 2);
		allyPane3.visibleProperty().bind(battleManager.getICharacter(2).isAliveProperty());
		Region alliesPaneSPacer4 = getAlliesPaneSpacer(alliesPane);
		
		
		alliesPane.getChildren().addAll(alliesPaneSPacer1, allyPane1, alliesPaneSPacer2, allyPane2, alliesPaneSPacer3, allyPane3, alliesPaneSPacer4);
		return alliesPane;
	}
	
	private Region getAlliesPaneSpacer(HBox alliesPane) {
		Region enemiesPaneSpacer = new Region();
		enemiesPaneSpacer.prefWidthProperty().bind(alliesPane.widthProperty().multiply(0.025));
		return enemiesPaneSpacer;
	}
	
	private VBox getAllyPane(HBox alliesPane, int allyIndex) {
		VBox allyPane = new VBox();
		allyPane.prefWidthProperty().bind(alliesPane.widthProperty().multiply(0.3));
		allyPane.setStyle(redBorderStyle());
		
		allyPane.hoverProperty().addListener((_, _, isHover) -> {
	    	if (isHover)
	    		allyName.setText(battleManager.getICharacter(allyIndex).getName());
	    	else
	    		allyName.setText("prout");
	    });
		
		Pane allyIconPane = new Pane();
		allyIconPane.prefWidthProperty().bind(allyPane.widthProperty());
		allyIconPane.prefHeightProperty().bind(allyPane.heightProperty().multiply(.7));
		allyIconPane.setStyle(greenBorderStyle());
		ImageView allyIconImageView = ui.IconProvider.getCharacterElementIcon(CharacterElement.FIRE, 1);
		allyIconImageView.setPreserveRatio(true);
		allyIconImageView.fitWidthProperty().bind(allyIconPane.widthProperty());
		allyIconImageView.fitHeightProperty().bind(allyIconPane.heightProperty());
		allyIconPane.getChildren().addAll(allyIconImageView);

		Region allyPaneSpacer1 = new Region();
		allyPaneSpacer1.prefHeightProperty().bind(allyPane.heightProperty().multiply(.1));
		
		HBox healthBarBox = new HBox();
		Pane healthBarPane = new Pane();
		healthBarPane.prefWidthProperty().bind(healthBarBox.widthProperty().multiply(.8));
		healthBarPane.setPrefHeight(30);
		Rectangle healthBar = new Rectangle();
		healthBar.widthProperty().bind(healthBarPane.widthProperty());
		healthBar.heightProperty().bind(healthBarPane.heightProperty());
		healthBar.setFill(Color.PURPLE);
		healthBarPane.getChildren().add(healthBar);
		Label healthPoints = new Label();
		healthPoints.textProperty().bind(battleManager.getCurrentCharacter().currentHPProperty().asString());
		healthPoints.setAlignment(Pos.CENTER);
		healthPoints.setTextFill(Color.PURPLE);
		healthPoints.prefWidthProperty().bind(healthBarBox.widthProperty().multiply(.2));
		healthPoints.widthProperty().addListener((_, _, newW) -> {
		    double fontSize = Math.max(10, newW.doubleValue() * 0.5);
		    healthPoints.setFont(Font.font(fontSize));
		});
		healthBarBox.getChildren().addAll(healthBarPane, healthPoints);

		Region allyPaneSpacer2 = new Region();
		allyPaneSpacer2.prefHeightProperty().bind(allyPane.heightProperty().multiply(.1));
		
		HBox APBarBox = new HBox();
		Pane APBarPane = new Pane();
		APBarPane.prefWidthProperty().bind(APBarBox.widthProperty().multiply(.8));
		APBarPane.setPrefHeight(30);
		Rectangle APBar = new Rectangle();
		APBar.widthProperty().bind(APBarPane.widthProperty());
		APBar.heightProperty().bind(APBarPane.heightProperty());
		APBar.setFill(Color.BLUE);
		APBarPane.getChildren().add(APBar);
		Label APPoints = new Label();
		APPoints.textProperty().bind(battleManager.getCurrentCharacter().currentAPProperty().asString());
		APPoints.setAlignment(Pos.CENTER);
		APPoints.setTextFill(Color.BLUE);
		APPoints.prefWidthProperty().bind(healthBarBox.widthProperty().multiply(.2));
		APPoints.widthProperty().addListener((_, _, newW) -> {
		    double fontSize = Math.max(10, newW.doubleValue() * 0.5);
		    APPoints.setFont(Font.font(fontSize));
		});
		APBarBox.getChildren().addAll(APBarPane, APPoints);
		
		allyPane.getChildren().addAll(allyIconPane, allyPaneSpacer1, healthBarBox, allyPaneSpacer2, APBarBox);
		return allyPane;
	}
}
