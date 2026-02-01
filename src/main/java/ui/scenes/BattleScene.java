package ui.scenes;

import entities.Character;
import entities.resistances.Resistance;
import entities.spells.Spell;
import entities.spells.SpellElement;
import entities.stats.Stat;
import game.BattleManager;
import game.BattleState;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import ui.AnimatedSprite;
import javafx.scene.input.MouseEvent;

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
    BorderPane attackSelectionPane = new BorderPane();
    VBox personaSpellSelectionPane = new VBox();

	Label messageLabel = new Label();
    
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

	private String blackBorderStyle() {
		return "-fx-border-color: black; -fx-border-width: 2; -fx-border-style: solid;";
	}
	
    public BattleScene(BattleManager battleManagerParam) {
    	this.battleManager = battleManagerParam;
    	
	    HBox globalPane = new HBox();
	    globalPane.setPrefSize(1400, 800);
	    globalPane.setStyle("-fx-background-color: grey;");
	    
	    //------------------------------ Attacks, persona, defend, stats, elements... ------------------------------
	    VBox leftPane = getLeftPane(globalPane);
	    HBox.setMargin(leftPane, new Insets(0, 0, 0, 10));
		setFirstChoicePane();
		setAttackSelectionPane();
		setPersonnaSpellSelectionPane();
	    
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
		actionPane.getChildren().addAll(firstChoicePane, attackSelectionPane, personaSpellSelectionPane);

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
		
		VBox ennemyResPhysicalBox = getEnnemyResUnitPane(leftPane, SpellElement.PHYSICAL, ennemyResPhysicalValuePane, ennemyResPhysicalValueImage);
		VBox ennemyResGunBox = getEnnemyResUnitPane(leftPane, SpellElement.GUN, ennemyResGunValuePane, ennemyResGunValueImage);
		VBox ennemyResFireBox = getEnnemyResUnitPane(leftPane, SpellElement.FIRE, ennemyResFireValuePane, ennemyResFireValueImage);
		VBox ennemyResIceBox = getEnnemyResUnitPane(leftPane, SpellElement.ICE, ennemyResIceValuePane, ennemyResIceValueImage);
		VBox ennemyResElectricBox = getEnnemyResUnitPane(leftPane, SpellElement.ELECTRIC, ennemyResElectricValuePane, ennemyResElectricValueImage);
		VBox ennemyResWindBox = getEnnemyResUnitPane(leftPane, SpellElement.WIND, ennemyResWindValuePane, ennemyResWindValueImage);
		VBox ennemyResPsyBox = getEnnemyResUnitPane(leftPane, SpellElement.PSY, ennemyResPsyValuePane, ennemyResPsyValueImage);
		VBox ennemyResNuclearBox = getEnnemyResUnitPane(leftPane, SpellElement.NUCLEAR, ennemyResNuclearValuePane, ennemyResNuclearValueImage);
		VBox ennemyResDivineBox = getEnnemyResUnitPane(leftPane, SpellElement.DIVINE, ennemyResDivineValuePane, ennemyResDivineValueImage);
		VBox ennemyResCursedBox = getEnnemyResUnitPane(leftPane, SpellElement.CURSED, ennemyResCursedValuePane, ennemyResCursedValueImage);
		
		ennemyResPane.getChildren().addAll(ennemyResPhysicalBox, ennemyResGunBox, ennemyResFireBox, ennemyResIceBox, ennemyResElectricBox, ennemyResWindBox, ennemyResPsyBox, ennemyResNuclearBox, ennemyResDivineBox, ennemyResCursedBox);
	}

	private VBox getEnnemyResUnitPane(VBox leftPane, SpellElement characterElement, BorderPane valuePane, ImageView valueImage) {
		VBox ennemyResBox = new VBox();
		ennemyResBox.setStyle(redBorderStyle());
		ennemyResBox.prefWidthProperty().bind(ennemyResPane.widthProperty().multiply(.1));
		BorderPane ennemyResPane = new BorderPane();
		ennemyResPane.prefWidthProperty().bind(leftPane.widthProperty());
		ennemyResPane.prefHeightProperty().bind(leftPane.heightProperty().multiply(.5));
		ImageView ennemyResImage = new ImageView();
		ennemyResImage = ui.IconProvider.getCharacterElementIcon(characterElement, 50);
		ennemyResPane.setCenter(ennemyResImage);
		valuePane.prefWidthProperty().bind(leftPane.widthProperty());
		valuePane.prefHeightProperty().bind(leftPane.heightProperty().multiply(.5));
		ennemyResBox.getChildren().addAll(ennemyResPane, valuePane);
		return ennemyResBox;
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
		personaButton.setOnAction(_ -> battleManager.firstChoicePersona());
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
		parryButton.setOnAction(_ -> battleManager.firstChoiceParry());
		parryPane.widthProperty().addListener((_, _, newW) -> {
			parryButton.setFont(Font.font(newW.doubleValue() * .05));
		});
		parryPane.getChildren().addAll(parryButton);

		Region firstChoicePaneSpacer2 = new Region();
		firstChoicePaneSpacer2.prefHeightProperty().bind(firstChoicePane.heightProperty().multiply(.25));
		
		firstChoicePane.getChildren().addAll(firstChoicePaneSpacer1,attackPersonaPane, parryPane, firstChoicePaneSpacer2);
	}
	
	private void setAttackSelectionPane() {
		attackSelectionPane.visibleProperty().bind(battleManager.getState().isEqualTo(BattleState.ATTACKSELECTION).or(battleManager.getState().isEqualTo(BattleState.PERSONAATTACKSELECTION)));
		attackSelectionPane.prefWidthProperty().bind(actionPane.widthProperty());
		attackSelectionPane.prefHeightProperty().bind(actionPane.heightProperty());
		attackSelectionPane.setStyle(purpleBorderStyle());
		
		Button cancelAttackButton = new Button("Retour");
		cancelAttackButton.setFocusTraversable(false);
		cancelAttackButton.prefWidthProperty().bind(attackSelectionPane.widthProperty().multiply(.4));
		cancelAttackButton.setStyle("-fx-background-radius: 100; -fx-border-radius: 100;");
		cancelAttackButton.setOnAction(_ -> battleManager.cancelChoice());
		attackSelectionPane.widthProperty().addListener((_, _, newW) -> {
			cancelAttackButton.setFont(Font.font(newW.doubleValue() * .05));
		});
		
		attackSelectionPane.setCenter(cancelAttackButton);
	}
	
	private void setPersonnaSpellSelectionPane() {
		personaSpellSelectionPane.visibleProperty().bind(battleManager.getState().isEqualTo(BattleState.PERSONASPELLSELECTION));
		personaSpellSelectionPane.prefWidthProperty().bind(actionPane.widthProperty());
		personaSpellSelectionPane.prefHeightProperty().bind(actionPane.heightProperty());
		personaSpellSelectionPane.setAlignment(Pos.CENTER);
		personaSpellSelectionPane.setStyle(redBorderStyle());

		for (int i = 0; i < 5; i++) {
			Spell spell = battleManager.getCurrentCharacter().getISpell(i);
			HBox spellPane = getISpellPane(spell, i);
			personaSpellSelectionPane.getChildren().add(spellPane);
		}
		
		Button cancelAttackButton = new Button("Retour");
		cancelAttackButton.setFocusTraversable(false);
		cancelAttackButton.prefWidthProperty().bind(personaSpellSelectionPane.widthProperty().multiply(.4));
		cancelAttackButton.prefHeightProperty().bind(personaSpellSelectionPane.heightProperty().multiply(.18));
		cancelAttackButton.setStyle("-fx-background-radius: 100; -fx-border-radius: 100;");
		cancelAttackButton.setOnAction(_ -> battleManager.cancelChoice());
		attackSelectionPane.widthProperty().addListener((_, _, newW) -> {
			cancelAttackButton.setFont(Font.font(newW.doubleValue() * .05));
		});

		personaSpellSelectionPane.getChildren().add(cancelAttackButton);
	}

	private HBox getISpellPane(Spell spell, int i) {
		HBox spellPane = new HBox();
		spellPane.prefWidthProperty().bind(personaSpellSelectionPane.widthProperty());
		spellPane.prefHeightProperty().bind(personaSpellSelectionPane.heightProperty().multiply(.2));
		spellPane.setStyle(greenBorderStyle());
		spellPane.setAlignment(Pos.CENTER);
		
		

		if (spell != null) {
			spellPane.setOnMouseClicked(event -> {
			    if (event.getButton() == MouseButton.PRIMARY) {
			    	battleManager.spellPersonaClicked(spell);
			    }
			});
			spellPane.hoverProperty().addListener((_, _, isHover) -> {
		    	if (isHover) {
					spellPane.setStyle(blackBorderStyle());
		    	}
		    	else {
		    		spellPane.setStyle(greenBorderStyle());
		    	}
		    });
			
			BorderPane descriptionPane = new BorderPane();
			descriptionPane.setStyle("-fx-background-color: white;");
			Label descriptionLabel = new Label(" " + spell.getDescription() + " ");
			descriptionLabel.setFont(Font.font(16));
			descriptionPane.setCenter(descriptionLabel);
			
			Popup spellDescriptionPopup = new Popup();
			spellDescriptionPopup.getContent().add(descriptionPane);
			spellDescriptionPopup.setAutoHide(false);
			spellPane.addEventHandler(MouseEvent.MOUSE_ENTERED, _ -> {
			    spellDescriptionPopup.show(spellPane.getScene().getWindow());
			});
			spellPane.addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
			    spellDescriptionPopup.setX(e.getScreenX() + 20);
			    spellDescriptionPopup.setY(e.getScreenY());
			});
			spellPane.addEventHandler(MouseEvent.MOUSE_EXITED, _ -> {
			    spellDescriptionPopup.hide();
			});

			BorderPane spellElementPane = new BorderPane();
			spellElementPane.prefWidthProperty().bind(spellPane.widthProperty().multiply(.3));
			spellElementPane.prefHeightProperty().bind(spellPane.heightProperty());
			ImageView spellElementImage = new ImageView();
			spellElementImage = ui.IconProvider.getCharacterElementIcon(spell.getElement(), 50);
			spellElementPane.setCenter(spellElementImage);
			
			Label spellName = new Label();
			spellName.setText(spell.getName());
			spellName.setAlignment(Pos.CENTER);
			spellName.prefWidthProperty().bind(ennemyStatsPane.widthProperty().multiply(.4));
			spellName.widthProperty().addListener((_, _, newW) -> {
				spellName.setFont(Font.font(newW.doubleValue() * .1));
			});
			
			Label spellCost = new Label();
			spellCost.setText("" + spell.getAPCost());
			spellCost.setAlignment(Pos.CENTER);
			spellCost.prefWidthProperty().bind(ennemyStatsPane.widthProperty().multiply(.3));
			spellCost.widthProperty().addListener((_, _, newW) -> {
				spellCost.setFont(Font.font(newW.doubleValue() * .15));
			});
			
			spellPane.getChildren().addAll(spellElementPane,spellName, spellCost);
		}
		
		return spellPane;
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
		
		BorderPane messagePane = new BorderPane();
		messagePane.prefHeightProperty().bind(rightPane.heightProperty().multiply(0.3));
		messageLabel.setAlignment(Pos.CENTER);
		messageLabel.setTextAlignment(TextAlignment.CENTER);

		messageLabel.setWrapText(true);
		messageLabel.textProperty().bind(battleManager.getMessage());
		messageLabel.prefWidthProperty().bind(messagePane.widthProperty());
		messageLabel.widthProperty().addListener((_, _, newW) -> {
			messageLabel.setFont(Font.font(newW.doubleValue() * 0.05));
		});
		messagePane.setCenter(messageLabel);
	
		HBox alliesPane = getAlliesPane(rightPane);
		
		rightPane.getChildren().addAll(enemiesPane, messagePane, alliesPane);
		return rightPane;
	}

	private HBox getEnemiesPane(VBox rightPane) {
		HBox enemiesPane = new HBox();
		enemiesPane.prefHeightProperty().bind(rightPane.heightProperty().multiply(.35));
		enemiesPane.setStyle(greenBorderStyle());
		
		Region enemiesPaneSpacer1 = getEnemiesPaneSpacer(enemiesPane);
		VBox enemyPane1 = getEnemyPane(enemiesPane, 5);
		Region enemiesPaneSpacer2 = getEnemiesPaneSpacer(enemiesPane);
		VBox enemyPane2 = getEnemyPane(enemiesPane, 1);
		Region enemiesPaneSpacer3 = getEnemiesPaneSpacer(enemiesPane);
		VBox enemyPane3 = getEnemyPane(enemiesPane, 3);
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
		Character ennemy = battleManager.getICharacter(ennemyIndex);
		VBox ennemyPane = new VBox();
		ennemyPane.prefWidthProperty().bind(enemiesPane.widthProperty().multiply(0.3));
		ennemyPane.setStyle(redBorderStyle());
		ennemyPane.visibleProperty().bind(battleManager.getICharacter(ennemyIndex).isAliveProperty());
		ennemyPane.styleProperty().bind(
		    Bindings.when(battleManager.getICharacter(ennemyIndex).isPlayingProperty())
		        .then(blackBorderStyle())
		        .otherwise("")
		);
		ennemyPane.setOnMouseClicked(event -> {
		    if (event.getButton() == MouseButton.PRIMARY) {
		    	battleManager.characterClicked(ennemyIndex);
				refreshEnnemyHover(ennemy);
		    }
		});
		ennemyPane.hoverProperty().addListener((_, _, isHover) -> {
	    	if (isHover) {
				refreshEnnemyHover(ennemy);
	    	}
	    	else {
	    		ennemyName.setText("");
				ennemyResPane.setVisible(false);
	    		ennemyStatsPane.setVisible(false);
	    	}
	    });

		Region enemyPaneSpacer1 = new Region();
		enemyPaneSpacer1.prefHeightProperty().bind(ennemyPane.heightProperty().multiply(.1));
		
		Pane healthBarPane = new Pane();
		healthBarPane.prefWidthProperty().bind(ennemyPane.widthProperty().multiply(.8));
		healthBarPane.setPrefHeight(30);
		Rectangle healthBar = new Rectangle();
		healthBar.widthProperty().bind(healthBarPane.widthProperty());
		healthBar.heightProperty().bind(healthBarPane.heightProperty());
		healthBar.setFill(Color.TRANSPARENT);
		healthBar.setStroke(Color.RED);
		healthBar.setStrokeWidth(2);
		Rectangle currentHealthBar = new Rectangle();
		currentHealthBar.widthProperty().bind(battleManager.getICharacter(ennemyIndex).currentHPProperty().multiply(ennemyPane.widthProperty().divide(battleManager.getICharacter(ennemyIndex).getMaxHP())));
		currentHealthBar.heightProperty().bind(healthBarPane.heightProperty()); 
		currentHealthBar.setFill(Color.RED);
		healthBarPane.getChildren().addAll(healthBar, currentHealthBar);
		
		Region enemyPaneSpacer2 = new Region();
		enemyPaneSpacer2.prefHeightProperty().bind(ennemyPane.heightProperty().multiply(.1));
		
		BorderPane ennemyIconPane = new BorderPane();
		ennemyIconPane.prefWidthProperty().bind(ennemyPane.widthProperty());
		ennemyIconPane.prefHeightProperty().bind(ennemyPane.heightProperty().multiply(.7));
		ennemyIconPane.setStyle(greenBorderStyle());
		AnimatedSprite ennemySprite = ui.IconProvider.getAnimatedCharacterIcon(battleManager.getICharacter(ennemyIndex), 2, 150, 150, 150, 500);
		ImageView effectImage = new ImageView();
		effectImage.setFitWidth(50);
		effectImage.setFitHeight(50);
		effectImage.imageProperty().bind(new ObjectBinding<>() {
            { super.bind(battleManager.getICharacter(ennemyIndex).getAttackEffect()); }
            protected javafx.scene.image.Image computeValue() {
            	Resistance attackEffect = battleManager.getICharacter(ennemyIndex).getAttackEffect().get();
				switch (attackEffect) {
            		case NEUTRAL, ABSORB, NULL, RETURN, STRONG, WEAK:
            			return ui.IconProvider.getCharacterResIcon(attackEffect, 100).getImage();
            		default:
            			return null;
            	}
            }
        });
		ennemyIconPane.setCenter(new StackPane(ennemySprite.getView(), effectImage));
		ennemySprite.play();
		
		ennemyPane.getChildren().addAll(enemyPaneSpacer1, healthBarPane, enemyPaneSpacer2, ennemyIconPane);
		return ennemyPane;
	}

	private void refreshEnnemyHover(Character ennemy) {
		ennemyName.setText(ennemy.getName());
		ennemyStatsAttackImage = ui.IconProvider.getCharacterStatStatusIcon(ennemy.getStatStatus(Stat.ATTACK), 50);
		ennemyStatsAttackPane.setCenter(ennemyStatsAttackImage);
		ennemyStatsDefenseImage = ui.IconProvider.getCharacterStatStatusIcon(ennemy.getStatStatus(Stat.DEFENSE), 50);
		ennemyStatsDefensePane.setCenter(ennemyStatsDefenseImage);
		ennemyStatsAccuEvaImage = ui.IconProvider.getCharacterStatStatusIcon(ennemy.getStatStatus(Stat.ACCURACY), 50);
		ennemyStatsAccuEvaPane.setCenter(ennemyStatsAccuEvaImage);
		ennemyStatsCriticalImage = ui.IconProvider.getCharacterStatStatusIcon(ennemy.getStatStatus(Stat.CRITICAL), 50);
		ennemyStatsCriticalPane.setCenter(ennemyStatsCriticalImage);
		ennemyStatsPane.setVisible(true);
		ennemyResPhysicalValueImage = ui.IconProvider.getCharacterResIcon(ennemy.getDiscoveredResistance(SpellElement.PHYSICAL), 50);
		ennemyResPhysicalValuePane.setCenter(ennemyResPhysicalValueImage);
		ennemyResGunValueImage = ui.IconProvider.getCharacterResIcon(ennemy.getDiscoveredResistance(SpellElement.GUN), 50);
		ennemyResGunValuePane.setCenter(ennemyResGunValueImage);
		ennemyResFireValueImage = ui.IconProvider.getCharacterResIcon(ennemy.getDiscoveredResistance(SpellElement.FIRE), 50);
		ennemyResFireValuePane.setCenter(ennemyResFireValueImage);
		ennemyResIceValueImage = ui.IconProvider.getCharacterResIcon(ennemy.getDiscoveredResistance(SpellElement.ICE), 50);
		ennemyResIceValuePane.setCenter(ennemyResIceValueImage);
		ennemyResElectricValueImage = ui.IconProvider.getCharacterResIcon(ennemy.getDiscoveredResistance(SpellElement.ELECTRIC), 50);
		ennemyResElectricValuePane.setCenter(ennemyResElectricValueImage);
		ennemyResWindValueImage = ui.IconProvider.getCharacterResIcon(ennemy.getDiscoveredResistance(SpellElement.WIND), 50);
		ennemyResWindValuePane.setCenter(ennemyResWindValueImage);
		ennemyResPsyValueImage = ui.IconProvider.getCharacterResIcon(ennemy.getDiscoveredResistance(SpellElement.PSY), 50);
		ennemyResPsyValuePane.setCenter(ennemyResPsyValueImage);
		ennemyResNuclearValueImage = ui.IconProvider.getCharacterResIcon(ennemy.getDiscoveredResistance(SpellElement.NUCLEAR), 50);
		ennemyResNuclearValuePane.setCenter(ennemyResNuclearValueImage);
		ennemyResDivineValueImage = ui.IconProvider.getCharacterResIcon(ennemy.getDiscoveredResistance(SpellElement.DIVINE), 50);
		ennemyResDivineValuePane.setCenter(ennemyResDivineValueImage);
		ennemyResCursedValueImage = ui.IconProvider.getCharacterResIcon(ennemy.getDiscoveredResistance(SpellElement.CURSED), 50);
		ennemyResCursedValuePane.setCenter(ennemyResCursedValueImage);
		ennemyResPane.setVisible(true);
	}
	
	private HBox getAlliesPane(VBox rightPane) {
		HBox alliesPane = new HBox();
		alliesPane.prefHeightProperty().bind(rightPane.heightProperty().multiply(.35));
		alliesPane.setStyle(greenBorderStyle());
		
		Region alliesPaneSPacer1 = getAlliesPaneSpacer(alliesPane);
		VBox allyPane1 = getAllyPane(alliesPane, 4);
		Region alliesPaneSPacer2 = getAlliesPaneSpacer(alliesPane);
		VBox allyPane2 = getAllyPane(alliesPane, 0);
		Region alliesPaneSPacer3 = getAlliesPaneSpacer(alliesPane);
		VBox allyPane3 = getAllyPane(alliesPane, 2);
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
		Character ally = battleManager.getICharacter(allyIndex);
		VBox allyPane = new VBox();
		allyPane.prefWidthProperty().bind(alliesPane.widthProperty().multiply(0.3));
		allyPane.setStyle(redBorderStyle());
//		allyPane.visibleProperty().bind(battleManager.getICharacter(allyIndex).isAliveProperty());
		allyPane.styleProperty().bind(
		    Bindings.when(battleManager.getICharacter(allyIndex).isPlayingProperty())
		        .then(blackBorderStyle())
		        .otherwise("")
		);
		allyPane.setOnMouseClicked(event -> {
		    if (event.getButton() == MouseButton.PRIMARY) {
		    	battleManager.characterClicked(allyIndex);
				refreshAllyHover(ally);
		    }
		});
		allyPane.hoverProperty().addListener((_, _, isHover) -> {
	    	if (isHover) {
	    		refreshAllyHover(ally);
	    	} else {
	    		allyName.setText("");
	    		allyStatsPane.setVisible(false);
	    	}
	    });
		
		BorderPane allyIconPane = new BorderPane();
		allyIconPane.prefWidthProperty().bind(allyPane.widthProperty());
		allyIconPane.prefHeightProperty().bind(allyPane.heightProperty().multiply(.7));
		allyIconPane.setStyle(greenBorderStyle());
		
		AnimatedSprite allySprite = ui.IconProvider.getAnimatedCharacterIcon(battleManager.getICharacter(allyIndex), 2, 150, 150, 150, 500);
		ImageView effectImage = new ImageView();
		effectImage.setFitWidth(50);
		effectImage.setFitHeight(50);
		effectImage.imageProperty().bind(new ObjectBinding<>() {
            { super.bind(battleManager.getICharacter(allyIndex).getAttackEffect()); }
            protected javafx.scene.image.Image computeValue() {
            	Resistance attackEffect = battleManager.getICharacter(allyIndex).getAttackEffect().get();
				switch (attackEffect) {
            		case UNKNOWN:
            			return null;
            		case NEUTRAL, ABSORB, NULL, RETURN, STRONG, WEAK:
            			return ui.IconProvider.getCharacterResIcon(attackEffect, 100).getImage();
            		default:
            			return null;
            	}
            }
        });
		StackPane spriteStack = new StackPane(allySprite.getView(), effectImage);
		allyIconPane.setCenter(spriteStack);
		allySprite.play();
		
		ally.isAliveProperty().addListener(
	            (_, _, isAlive) -> {
	                if (isAlive) {
	            		allySprite.play();
	                } else {
	            		allySprite.stop();
	                }
	            }
	        );
		
		
		Region allyPaneSpacer1 = new Region();
		allyPaneSpacer1.prefHeightProperty().bind(allyPane.heightProperty().multiply(.1));
		
		HBox healthBarBox = new HBox();
		Pane healthBarPane = new Pane();
		healthBarPane.prefWidthProperty().bind(healthBarBox.widthProperty().multiply(.8));
		healthBarPane.setPrefHeight(30);
		Rectangle healthBar = new Rectangle();
		healthBar.widthProperty().bind(healthBarPane.widthProperty());
		healthBar.heightProperty().bind(healthBarPane.heightProperty());
		healthBar.setFill(Color.TRANSPARENT);
		healthBar.setStroke(Color.RED);
		healthBar.setStrokeWidth(2);
		Rectangle currentHealthBar = new Rectangle();
		currentHealthBar.widthProperty().bind(battleManager.getICharacter(allyIndex).currentHPProperty().multiply(healthBarBox.widthProperty().multiply(.8).divide(battleManager.getICharacter(allyIndex).getMaxHP())));
		currentHealthBar.heightProperty().bind(healthBarPane.heightProperty()); 
		currentHealthBar.setFill(Color.RED);
		healthBarPane.getChildren().addAll(healthBar, currentHealthBar);
		
		Label healthPoints = new Label();
		healthPoints.textProperty().bind(battleManager.getICharacter(allyIndex).currentHPProperty().asString());
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
		APBar.setFill(Color.TRANSPARENT);
		APBar.setStroke(Color.BLUE);
		APBar.setStrokeWidth(2);
		Rectangle currentAPBar = new Rectangle();
		currentAPBar.widthProperty().bind(battleManager.getICharacter(allyIndex).currentAPProperty().multiply(APBarBox.widthProperty().multiply(.8).divide(battleManager.getICharacter(allyIndex).getMaxAP())));
		currentAPBar.heightProperty().bind(APBarPane.heightProperty()); 
		currentAPBar.setFill(Color.BLUE);
		APBarPane.getChildren().addAll(APBar, currentAPBar);
		
		Label APPoints = new Label();
		APPoints.textProperty().bind(battleManager.getICharacter(allyIndex).currentAPProperty().asString());
		APPoints.setAlignment(Pos.CENTER);
		APPoints.setTextFill(Color.BLUE);
		APPoints.prefWidthProperty().bind(APBarBox.widthProperty().multiply(.2));
		APPoints.widthProperty().addListener((_, _, newW) -> {
		    double fontSize = Math.max(10, newW.doubleValue() * 0.5);
		    APPoints.setFont(Font.font(fontSize));
		});
		APBarBox.getChildren().addAll(APBarPane, APPoints);
		
		allyPane.getChildren().addAll(allyIconPane, allyPaneSpacer1, healthBarBox, allyPaneSpacer2, APBarBox);
		return allyPane;
	}
	
	private void refreshAllyHover(Character ally) {
		allyName.setText(ally.getName());
		allyStatsAttackImage = ui.IconProvider.getCharacterStatStatusIcon(ally.getStatStatus(Stat.ATTACK), 50);
		allyStatsAttackPane.setCenter(allyStatsAttackImage);
		allyStatsDefenseImage = ui.IconProvider.getCharacterStatStatusIcon(ally.getStatStatus(Stat.DEFENSE), 50);
		allyStatsDefensePane.setCenter(allyStatsDefenseImage);
		allyStatsAccuEvaImage = ui.IconProvider.getCharacterStatStatusIcon(ally.getStatStatus(Stat.ACCURACY), 50);
		allyStatsAccuEvaPane.setCenter(allyStatsAccuEvaImage);
		allyStatsCriticalImage = ui.IconProvider.getCharacterStatStatusIcon(ally.getStatStatus(Stat.CRITICAL), 50);
		allyStatsCriticalPane.setCenter(allyStatsCriticalImage);
		allyStatsPane.setVisible(true);
	}
}
