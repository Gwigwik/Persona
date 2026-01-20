package ui.scenes;

import entities.Character;
import entities.CharacterElement;
import entities.Stat;
import game.BattleManager;
import game.BattleState;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class BattleScene {
	
	private final Scene scene;
	private final BattleManager battleManager;

    Label ennemyName = new Label();
    HBox ennemyStatsPane = new HBox();
    BorderPane ennemyStatsAttackPane = new BorderPane();
    BorderPane ennemyStatsDefensePane = new BorderPane();
    BorderPane ennemyStatsAccuEvaPane = new BorderPane();
    BorderPane ennemyStatsCriticalPane = new BorderPane();
    ImageView ennemyStatsAttackImage = new ImageView();
    ImageView ennemyStatsDefenseImage = new ImageView();
    ImageView ennemyStatsAccuEvaImage = new ImageView();
    ImageView ennemyStatsCriticalImage = new ImageView();
    HBox ennemyResPane = new HBox();
	BorderPane ennemyResPhysicalValuePane = new BorderPane();
	BorderPane ennemyResGunValuePane = new BorderPane();
	BorderPane ennemyResFireValuePane = new BorderPane();
	BorderPane ennemyResIceValuePane = new BorderPane();
	BorderPane ennemyResElectricValuePane = new BorderPane();
	BorderPane ennemyResWindValuePane = new BorderPane();
	BorderPane ennemyResPsyValuePane = new BorderPane();
	BorderPane ennemyResNuclearValuePane = new BorderPane();
	BorderPane ennemyResDivineValuePane = new BorderPane();
	BorderPane ennemyResCursedValuePane = new BorderPane();
	ImageView ennemyResPhysicalValueImage = new ImageView();
	ImageView ennemyResGunValueImage = new ImageView();
	ImageView ennemyResFireValueImage = new ImageView();
	ImageView ennemyResIceValueImage = new ImageView();
	ImageView ennemyResElectricValueImage = new ImageView();
	ImageView ennemyResWindValueImage = new ImageView();
	ImageView ennemyResPsyValueImage = new ImageView();
	ImageView ennemyResNuclearValueImage = new ImageView();
	ImageView ennemyResDivineValueImage = new ImageView();
	ImageView ennemyResCursedValueImage = new ImageView();
    HBox allyStatsPane = new HBox();
    BorderPane allyStatsAttackPane = new BorderPane();
    BorderPane allyStatsDefensePane = new BorderPane();
    BorderPane allyStatsAccuEvaPane = new BorderPane();
    BorderPane allyStatsCriticalPane = new BorderPane();
    ImageView allyStatsAttackImage = new ImageView();
    ImageView allyStatsDefenseImage = new ImageView();
    ImageView allyStatsAccuEvaImage = new ImageView();
    ImageView allyStatsCriticalImage = new ImageView();
    Label allyName = new Label();
	Pane actionPane = new Pane();
    VBox firstChoicePane = new VBox();
    
    public Scene getScene() {
        return scene;
    }

    boolean debug = false;
    
	private String redBorderStyle() {
		return debug?"-fx-border-color: red; -fx-border-width: 2; -fx-border-style: solid;":"";
	}

	private String greenBorderStyle() {
		return debug?"-fx-border-color: green; -fx-border-width: 2; -fx-border-style: solid;":"";
	}

	private String purpleBorderStyle() {
		return debug?"-fx-border-color: purple; -fx-border-width: 2; -fx-border-style: solid;":"";
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
		ennemyName.prefHeightProperty().bind(leftPane.heightProperty().multiply(0.05));
		ennemyName.widthProperty().addListener((_, _, newW) -> {
		    ennemyName.setFont(Font.font(newW.doubleValue() * 0.03));
		});

		setEnnemyStatsPane(leftPane);
		ennemyStatsPane.prefHeightProperty().bind(leftPane.heightProperty().multiply(0.1));
		
		setEnnemyResPane(leftPane);
		ennemyResPane.prefHeightProperty().bind(leftPane.heightProperty().multiply(0.15));
		
		actionPane.setStyle(greenBorderStyle());
		actionPane.prefHeightProperty().bind(leftPane.heightProperty().multiply(0.55));
		setFirstChoicePane();
		actionPane.getChildren().addAll(firstChoicePane);

		setAllyStatsPane(leftPane);
		allyStatsPane.prefHeightProperty().bind(leftPane.heightProperty().multiply(0.1));
		
		allyName.setAlignment(Pos.CENTER);
		allyName.prefWidthProperty().bind(leftPane.widthProperty());
		allyName.prefHeightProperty().bind(leftPane.heightProperty().multiply(0.05));
		allyName.widthProperty().addListener((_, _, newW) -> {
			allyName.setFont(Font.font(newW.doubleValue() * 0.03));
		});

	    
	    leftPane.getChildren().addAll(ennemyName, ennemyStatsPane, ennemyResPane, actionPane, allyStatsPane, allyName);
		return leftPane;
	}
	
	private void setEnnemyStatsPane(VBox leftPane) {
		ennemyStatsPane.setStyle(greenBorderStyle());
		ennemyStatsPane.setAlignment(Pos.CENTER);
		ennemyStatsPane.setVisible(false);
		int marginRight = 40;
	    
	    Label ennemyStatsAttackLabel = getEnnemyStatsLabel("Atk");
	    
	    ennemyStatsAttackPane.prefWidthProperty().bind(ennemyStatsPane.widthProperty().multiply(.125));
	    HBox.setMargin(ennemyStatsAttackPane, new Insets(0, marginRight, 0, 0));
	    
	    Label ennemyStatsDefenseLabel = getEnnemyStatsLabel("Def");

	    ennemyStatsDefensePane.prefWidthProperty().bind(ennemyStatsPane.widthProperty().multiply(.125));
	    HBox.setMargin(ennemyStatsDefensePane, new Insets(0, marginRight, 0, 0));
	    
	    Label ennemyStatsAccuEvaLabel = getEnnemyStatsLabel("Prec/Esq");

	    ennemyStatsAccuEvaPane.prefWidthProperty().bind(ennemyStatsPane.widthProperty().multiply(.125));
	    HBox.setMargin(ennemyStatsAccuEvaPane, new Insets(0, marginRight, 0, 0));
	    
	    Label ennemyStatsCriticalLabel = getEnnemyStatsLabel("Crit");

	    ennemyStatsCriticalPane.prefWidthProperty().bind(ennemyStatsPane.widthProperty().multiply(.125));
	    
	    ennemyStatsPane.getChildren().addAll(ennemyStatsAttackLabel, ennemyStatsAttackPane, ennemyStatsDefenseLabel, ennemyStatsDefensePane, ennemyStatsAccuEvaLabel, ennemyStatsAccuEvaPane, ennemyStatsCriticalLabel, ennemyStatsCriticalPane);
	}

	private Label getEnnemyStatsLabel(String text) {
		Label ennemyStatsLabel = new Label();
		double fontSize = .26;
	    ennemyStatsLabel.setText(text);
	    ennemyStatsLabel.setAlignment(Pos.CENTER);
	    ennemyStatsLabel.prefWidthProperty().bind(ennemyStatsPane.widthProperty().multiply(.125));
	    ennemyStatsLabel.widthProperty().addListener((_, _, newW) -> {
	    	ennemyStatsLabel.setFont(Font.font(newW.doubleValue() * fontSize));
		});
		return ennemyStatsLabel;
	}

	private void setEnnemyResPane(VBox leftPane) {
		ennemyResPane.setStyle(greenBorderStyle());
		ennemyResPane.setAlignment(Pos.CENTER);
		ennemyResPane.setVisible(false);
		
		VBox ennemyResPhysicalBox = getEnnemyResUnitPane(leftPane, CharacterElement.PHYSICAL, ennemyResPhysicalValuePane, ennemyResPhysicalValueImage);
		VBox ennemyResGunBox = getEnnemyResUnitPane(leftPane, CharacterElement.GUN, ennemyResGunValuePane, ennemyResGunValueImage);
		VBox ennemyResFireBox = getEnnemyResUnitPane(leftPane, CharacterElement.FIRE, ennemyResFireValuePane, ennemyResFireValueImage);
		VBox ennemyResIceBox = getEnnemyResUnitPane(leftPane, CharacterElement.ICE, ennemyResIceValuePane, ennemyResIceValueImage);
		VBox ennemyResElectricBox = getEnnemyResUnitPane(leftPane, CharacterElement.ELECTRIC, ennemyResElectricValuePane, ennemyResElectricValueImage);
		VBox ennemyResWindBox = getEnnemyResUnitPane(leftPane, CharacterElement.WIND, ennemyResWindValuePane, ennemyResWindValueImage);
		VBox ennemyResPsyBox = getEnnemyResUnitPane(leftPane, CharacterElement.PSY, ennemyResPsyValuePane, ennemyResPsyValueImage);
		VBox ennemyResNuclearBox = getEnnemyResUnitPane(leftPane, CharacterElement.NUCLEAR, ennemyResNuclearValuePane, ennemyResNuclearValueImage);
		VBox ennemyResDivineBox = getEnnemyResUnitPane(leftPane, CharacterElement.DIVINE, ennemyResDivineValuePane, ennemyResDivineValueImage);
		VBox ennemyResCursedBox = getEnnemyResUnitPane(leftPane, CharacterElement.CURSED, ennemyResCursedValuePane, ennemyResCursedValueImage);
		
		ennemyResPane.getChildren().addAll(ennemyResPhysicalBox, ennemyResGunBox, ennemyResFireBox, ennemyResIceBox, ennemyResElectricBox, ennemyResWindBox, ennemyResPsyBox, ennemyResNuclearBox, ennemyResDivineBox, ennemyResCursedBox);
	}

	private VBox getEnnemyResUnitPane(VBox leftPane, CharacterElement characterElement, BorderPane valuePane, ImageView valueImage) {
		VBox ennemyResPhysicalBox = new VBox();
		ennemyResPhysicalBox.setStyle(redBorderStyle());
		ennemyResPhysicalBox.prefWidthProperty().bind(ennemyResPane.widthProperty().multiply(.1));
		BorderPane ennemyResPhysicalPane = new BorderPane();
		ennemyResPhysicalPane.prefWidthProperty().bind(leftPane.widthProperty());
		ennemyResPhysicalPane.prefHeightProperty().bind(leftPane.heightProperty().multiply(.5));
		ImageView ennemyResPhysicalImage = new ImageView();
		ennemyResPhysicalImage = ui.IconProvider.getCharacterElementIcon(characterElement, 50);
		ennemyResPhysicalPane.setCenter(ennemyResPhysicalImage);
		valuePane.prefWidthProperty().bind(leftPane.widthProperty());
		valuePane.prefHeightProperty().bind(leftPane.heightProperty().multiply(.5));
		ennemyResPhysicalBox.getChildren().addAll(ennemyResPhysicalPane, valuePane);
		return ennemyResPhysicalBox;
	}
	
	private void setFirstChoicePane() {
		firstChoicePane.visibleProperty().bind(battleManager.getState().isEqualTo(BattleState.FIRSTCHOICE));
		firstChoicePane.prefWidthProperty().bind(actionPane.widthProperty());
		firstChoicePane.prefHeightProperty().bind(actionPane.heightProperty());
		firstChoicePane.setStyle(purpleBorderStyle());
		
		Region firstChoicePaneSpacer1 = new Region();
		firstChoicePaneSpacer1.prefHeightProperty().bind(firstChoicePane.heightProperty().multiply(.25));
		
		HBox attackPersonaPane = new HBox();
		attackPersonaPane.setStyle(greenBorderStyle());
		attackPersonaPane.prefHeightProperty().bind(firstChoicePane.heightProperty().multiply(.25));
		
		VBox attackPane = new VBox();
		attackPane.setStyle(redBorderStyle());
		attackPane.prefWidthProperty().bind(attackPersonaPane.widthProperty().multiply(.5));
		attackPane.setAlignment(Pos.CENTER);
		Button attackButton = new Button("Attaquer");
		attackButton.setFocusTraversable(false);
		attackButton.prefWidthProperty().bind(attackPane.widthProperty().multiply(.9));
		attackButton.setStyle("-fx-background-radius: 100; -fx-border-radius: 100;");
		attackButton.setOnAction(_ -> battleManager.firstChoiceAttack());
		attackPane.widthProperty().addListener((_, _, newW) -> {
			attackButton.setFont(Font.font(newW.doubleValue() * .1));
		});
		attackPane.getChildren().addAll(attackButton);

		VBox personaPane = new VBox();
		personaPane.setStyle(redBorderStyle());
		personaPane.prefWidthProperty().bind(attackPersonaPane.widthProperty().multiply(.5));
		personaPane.setAlignment(Pos.CENTER);
		Button personaButton = new Button("Persona");
		personaButton.setFocusTraversable(false);
		personaButton.prefWidthProperty().bind(personaPane.widthProperty().multiply(.9));
		personaButton.setStyle("-fx-background-radius: 100; -fx-border-radius: 100;");
		personaPane.widthProperty().addListener((_, _, newW) -> {
			personaButton.setFont(Font.font(newW.doubleValue() * .1));
		});
		personaPane.getChildren().addAll(personaButton);

		attackPersonaPane.getChildren().addAll(attackPane, personaPane);
		
		VBox parryPane = new VBox();
		parryPane.setStyle(redBorderStyle());
		parryPane.prefHeightProperty().bind(firstChoicePane.heightProperty().multiply(.25));
		parryPane.setAlignment(Pos.CENTER);
		Button parryButton = new Button("Parer");
		parryButton.setFocusTraversable(false);
		parryButton.prefWidthProperty().bind(attackPane.widthProperty().multiply(.9));
		parryButton.setStyle("-fx-background-radius: 100; -fx-border-radius: 100;");
		parryPane.widthProperty().addListener((_, _, newW) -> {
			parryButton.setFont(Font.font(newW.doubleValue() * .05));
		});
		parryPane.getChildren().addAll(parryButton);

		Region firstChoicePaneSpacer2 = new Region();
		firstChoicePaneSpacer2.prefHeightProperty().bind(firstChoicePane.heightProperty().multiply(.25));
		
		firstChoicePane.getChildren().addAll(firstChoicePaneSpacer1,attackPersonaPane, parryPane, firstChoicePaneSpacer2);
	}
	
	

	private void setAllyStatsPane(VBox leftPane) {
		allyStatsPane.setStyle(greenBorderStyle());
		allyStatsPane.setAlignment(Pos.CENTER);
		allyStatsPane.setVisible(false);
		int marginRight = 40;
	    
	    Label allyStatsAttackLabel = getAllyStatsLabel("Atk");
	    
	    allyStatsAttackPane.prefWidthProperty().bind(allyStatsPane.widthProperty().multiply(.125));
	    HBox.setMargin(allyStatsAttackPane, new Insets(0, marginRight, 0, 0));
	    
	    Label allyStatsDefenseLabel = getAllyStatsLabel("Def");

	    allyStatsDefensePane.prefWidthProperty().bind(allyStatsPane.widthProperty().multiply(.125));
	    HBox.setMargin(allyStatsDefensePane, new Insets(0, marginRight, 0, 0));
	    
	    Label allyStatsAccuEvaLabel = getAllyStatsLabel("Prec/Esq");

	    allyStatsAccuEvaPane.prefWidthProperty().bind(allyStatsPane.widthProperty().multiply(.125));
	    HBox.setMargin(allyStatsAccuEvaPane, new Insets(0, marginRight, 0, 0));
	    
	    Label allyStatsCriticalLabel = getAllyStatsLabel("Crit");

	    allyStatsCriticalPane.prefWidthProperty().bind(allyStatsPane.widthProperty().multiply(.125));
	    
	    allyStatsPane.getChildren().addAll(allyStatsAttackLabel, allyStatsAttackPane, allyStatsDefenseLabel, allyStatsDefensePane, allyStatsAccuEvaLabel, allyStatsAccuEvaPane, allyStatsCriticalLabel, allyStatsCriticalPane);
	}

	private Label getAllyStatsLabel(String text) {
		Label allyStatsLabel = new Label();
		double fontSize = .26;
		allyStatsLabel.setText(text);
		allyStatsLabel.setAlignment(Pos.CENTER);
		allyStatsLabel.prefWidthProperty().bind(allyStatsPane.widthProperty().multiply(.125));
		allyStatsLabel.widthProperty().addListener((_, _, newW) -> {
			allyStatsLabel.setFont(Font.font(newW.doubleValue() * fontSize));
		});
		return allyStatsLabel;
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
    		Character ennemy = battleManager.getICharacter(ennemyIndex);
	    	if (isHover) {
				ennemyName.setText(ennemy.getName());
	    		ennemyStatsAttackImage = ui.IconProvider.getCharacterStatStatusIcon(ennemy.getStatStatus(Stat.ATTACK), 50);
	    		ennemyStatsAttackPane.setCenter(ennemyStatsAttackImage);
	    		ennemyStatsDefenseImage = ui.IconProvider.getCharacterStatStatusIcon(ennemy.getStatStatus(Stat.DEFENSE), 50);
	    		ennemyStatsDefensePane.setCenter(ennemyStatsDefenseImage);
			    ennemyStatsAccuEvaImage = ui.IconProvider.getCharacterStatStatusIcon(ennemy.getStatStatus(Stat.ACCURACY), 50);
				ennemyStatsAccuEvaPane.setCenter(ennemyStatsAccuEvaImage);
			    ennemyStatsCriticalImage = ui.IconProvider.getCharacterStatStatusIcon(ennemy.getStatStatus(Stat.CRITICAL), 50);
				ennemyStatsCriticalPane.setCenter(ennemyStatsCriticalImage);
				ennemyResPane.setVisible(true);
				ennemyResPhysicalValueImage = ui.IconProvider.getCharacterResIcon(ennemy.getDiscoveredResistance(CharacterElement.PHYSICAL), 50);
				ennemyResPhysicalValuePane.setCenter(ennemyResPhysicalValueImage);
				ennemyResGunValueImage = ui.IconProvider.getCharacterResIcon(ennemy.getDiscoveredResistance(CharacterElement.GUN), 50);
				ennemyResGunValuePane.setCenter(ennemyResGunValueImage);
				ennemyResFireValueImage = ui.IconProvider.getCharacterResIcon(ennemy.getDiscoveredResistance(CharacterElement.FIRE), 50);
				ennemyResFireValuePane.setCenter(ennemyResFireValueImage);
				ennemyResIceValueImage = ui.IconProvider.getCharacterResIcon(ennemy.getDiscoveredResistance(CharacterElement.ICE), 50);
				ennemyResIceValuePane.setCenter(ennemyResIceValueImage);
				ennemyResElectricValueImage = ui.IconProvider.getCharacterResIcon(ennemy.getDiscoveredResistance(CharacterElement.ELECTRIC), 50);
				ennemyResElectricValuePane.setCenter(ennemyResElectricValueImage);
				ennemyResWindValueImage = ui.IconProvider.getCharacterResIcon(ennemy.getDiscoveredResistance(CharacterElement.WIND), 50);
				ennemyResWindValuePane.setCenter(ennemyResWindValueImage);
				ennemyResPsyValueImage = ui.IconProvider.getCharacterResIcon(ennemy.getDiscoveredResistance(CharacterElement.PSY), 50);
				ennemyResPsyValuePane.setCenter(ennemyResPsyValueImage);
				ennemyResNuclearValueImage = ui.IconProvider.getCharacterResIcon(ennemy.getDiscoveredResistance(CharacterElement.NUCLEAR), 50);
				ennemyResNuclearValuePane.setCenter(ennemyResNuclearValueImage);
				ennemyResDivineValueImage = ui.IconProvider.getCharacterResIcon(ennemy.getDiscoveredResistance(CharacterElement.DIVINE), 50);
				ennemyResDivineValuePane.setCenter(ennemyResDivineValueImage);
				ennemyResCursedValueImage = ui.IconProvider.getCharacterResIcon(ennemy.getDiscoveredResistance(CharacterElement.CURSED), 50);
				ennemyResCursedValuePane.setCenter(ennemyResCursedValueImage);
	    		ennemyStatsPane.setVisible(true);
	    	}
	    	else {
	    		ennemyName.setText("");
				ennemyResPane.setVisible(false);
	    		ennemyStatsPane.setVisible(false);
	    	}
	    });

		Region enemyPaneSpacer1 = new Region();
		enemyPaneSpacer1.prefHeightProperty().bind(enemyPane.heightProperty().multiply(.1));
		
		Pane healthBarPane = new Pane();
		healthBarPane.prefWidthProperty().bind(enemyPane.widthProperty().multiply(.8));
		healthBarPane.setPrefHeight(30);
		Rectangle healthBar = new Rectangle();
		healthBar.widthProperty().bind(healthBarPane.widthProperty());
		healthBar.heightProperty().bind(healthBarPane.heightProperty());
		healthBar.setFill(Color.RED);
		healthBarPane.getChildren().add(healthBar);
		
		Region enemyPaneSpacer2 = new Region();
		enemyPaneSpacer2.prefHeightProperty().bind(enemyPane.heightProperty().multiply(.1));
		
		BorderPane enemyIconPane = new BorderPane();
		enemyIconPane.prefWidthProperty().bind(enemyPane.widthProperty());
		enemyIconPane.prefHeightProperty().bind(enemyPane.heightProperty().multiply(.7));
		enemyIconPane.setStyle(greenBorderStyle());
		ImageView enemyIconImageView = ui.IconProvider.getCharacterIcon(battleManager.getICharacter(ennemyIndex), 150);
		enemyIconPane.setCenter(enemyIconImageView);
		
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
    		Character ally = battleManager.getICharacter(allyIndex);
	    	if (isHover) {
	    		allyName.setText(battleManager.getICharacter(allyIndex).getName());
	    		allyStatsAttackImage = ui.IconProvider.getCharacterStatStatusIcon(ally.getStatStatus(Stat.ATTACK), 50);
	    		allyStatsAttackPane.setCenter(allyStatsAttackImage);
	    		allyStatsDefenseImage = ui.IconProvider.getCharacterStatStatusIcon(ally.getStatStatus(Stat.DEFENSE), 50);
	    		allyStatsDefensePane.setCenter(allyStatsDefenseImage);
	    		allyStatsAccuEvaImage = ui.IconProvider.getCharacterStatStatusIcon(ally.getStatStatus(Stat.ACCURACY), 50);
	    		allyStatsAccuEvaPane.setCenter(allyStatsAccuEvaImage);
	    		allyStatsCriticalImage = ui.IconProvider.getCharacterStatStatusIcon(ally.getStatStatus(Stat.CRITICAL), 50);
	    		allyStatsCriticalPane.setCenter(allyStatsCriticalImage);
	    		allyStatsPane.setVisible(true);
	    	} else {
	    		allyName.setText("");
	    		allyStatsPane.setVisible(false);
	    	}
	    });
		
		BorderPane allyIconPane = new BorderPane();
		allyIconPane.prefWidthProperty().bind(allyPane.widthProperty());
		allyIconPane.prefHeightProperty().bind(allyPane.heightProperty().multiply(.7));
		allyIconPane.setStyle(greenBorderStyle());
		ImageView allyIconImageView = ui.IconProvider.getCharacterIcon(battleManager.getICharacter(allyIndex), 150);
		allyIconPane.setCenter(allyIconImageView);
		
		Region allyPaneSpacer1 = new Region();
		allyPaneSpacer1.prefHeightProperty().bind(allyPane.heightProperty().multiply(.1));
		
		HBox healthBarBox = new HBox();
		Pane healthBarPane = new Pane();
		healthBarPane.prefWidthProperty().bind(healthBarBox.widthProperty().multiply(.8));
		healthBarPane.setPrefHeight(30);
		Rectangle healthBar = new Rectangle();
		healthBar.widthProperty().bind(healthBarPane.widthProperty());
		healthBar.heightProperty().bind(healthBarPane.heightProperty());
		healthBar.setFill(Color.RED);
		healthBarPane.getChildren().add(healthBar);
		Label healthPoints = new Label();
		healthPoints.textProperty().bind(battleManager.getCurrentCharacter().currentHPProperty().asString());
		healthPoints.setAlignment(Pos.CENTER);
		healthPoints.setTextFill(Color.RED);
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
