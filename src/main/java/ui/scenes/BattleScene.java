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
import javafx.beans.value.ChangeListener;
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
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import ui.AnimatedSprite;
import javafx.scene.input.MouseEvent;

public class BattleScene {
	
	private final Scene scene;
	private final BattleManager battleManager;

    Text ennemyName = new Text();
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
    Text allyName = new Text();
     
	Pane actionPane = new Pane();
    VBox firstChoicePane = new VBox();
    BorderPane attackSelectionPane = new BorderPane();
    VBox personaSpellSelectionPane = new VBox();

	Label messageLabel = new Label();
	
	Color healthColor = new Color(1.0 / 255, 221.0 / 255, 183.0 / 255, 1.0);
	Color APColor = new Color(235.0 / 255, 137.0 / 255, 217.0 / 255, 1.0 );
    
    public Scene getScene() {
        return scene;
    }

    boolean debug = false;
    
    private String buttonStyle() {
    	return "-fx-background-color: black; -fx-text-fill: white;";
    }
    
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
	    leftPane.setAlignment(Pos.CENTER);
	    
		double fontSize = .04;
		ennemyName.setFill(Color.WHITE);
		ennemyName.setStroke(Color.BLACK);
		ennemyName.setStrokeWidth(2);
		ennemyName.setTextAlignment(TextAlignment.CENTER);
		ChangeListener<Number> resizeListener = (_, _, newVal) -> {
			ennemyName.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, newVal.doubleValue() * fontSize));
			allyName.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, newVal.doubleValue() * fontSize));
        };
        leftPane.widthProperty().addListener(resizeListener);
        leftPane.heightProperty().addListener(resizeListener);

		setEnnemyStatsPane(leftPane);
		ennemyStatsPane.prefHeightProperty().bind(leftPane.heightProperty().multiply(0.1));
		
		setEnnemyResPane(leftPane);
		ennemyResPane.prefHeightProperty().bind(leftPane.heightProperty().multiply(0.15));
		
		actionPane.setStyle(greenBorderStyle());
		actionPane.prefHeightProperty().bind(leftPane.heightProperty().multiply(0.55));
		actionPane.getChildren().addAll(firstChoicePane, attackSelectionPane, personaSpellSelectionPane);

		setAllyStatsPane(leftPane);
		allyStatsPane.prefHeightProperty().bind(leftPane.heightProperty().multiply(0.1));
		
		allyName.setFill(Color.WHITE);
		allyName.setStroke(Color.BLACK);
		allyName.setStrokeWidth(2);
		allyName.setTextAlignment(TextAlignment.CENTER);

	    leftPane.getChildren().addAll(ennemyName, ennemyStatsPane, ennemyResPane, actionPane, allyStatsPane, allyName);
		return leftPane;
	}
	
	private void setEnnemyStatsPane(VBox leftPane) {
		ennemyStatsPane.setStyle(greenBorderStyle());
		ennemyStatsPane.setAlignment(Pos.CENTER);
		ennemyStatsPane.setVisible(false);
		int marginRight = 40;
	    
		Text ennemyStatsAttackLabel = getEnnemyStatsText("ATK");
	    
	    ennemyStatsAttackPane.prefWidthProperty().bind(ennemyStatsPane.widthProperty().multiply(.125));
	    HBox.setMargin(ennemyStatsAttackPane, new Insets(0, marginRight, 0, 0));
	    
	    Text ennemyStatsDefenseLabel = getEnnemyStatsText("DEF");

	    ennemyStatsDefensePane.prefWidthProperty().bind(ennemyStatsPane.widthProperty().multiply(.125));
	    HBox.setMargin(ennemyStatsDefensePane, new Insets(0, marginRight, 0, 0));
	    
	    Text ennemyStatsAccuEvaLabel = getEnnemyStatsText("PREC/ESQ");

	    ennemyStatsAccuEvaPane.prefWidthProperty().bind(ennemyStatsPane.widthProperty().multiply(.125));
	    HBox.setMargin(ennemyStatsAccuEvaPane, new Insets(0, marginRight, 0, 0));
	    
	    Text ennemyStatsCriticalLabel = getEnnemyStatsText("CRIT");

	    ennemyStatsCriticalPane.prefWidthProperty().bind(ennemyStatsPane.widthProperty().multiply(.125));
	    
	    ennemyStatsPane.getChildren().addAll(ennemyStatsAttackLabel, ennemyStatsAttackPane, ennemyStatsDefenseLabel, ennemyStatsDefensePane, ennemyStatsAccuEvaLabel, ennemyStatsAccuEvaPane, ennemyStatsCriticalLabel, ennemyStatsCriticalPane);
	}

	private Text getEnnemyStatsText(String text) {
		double fontSize = .32;
		Text ennemyStatsText = new Text(text);
		ennemyStatsText.setFill(Color.WHITE);
		ennemyStatsText.setStroke(Color.BLACK);
		ennemyStatsText.setStrokeWidth(2);
		ennemyStatsText.setTextAlignment(TextAlignment.CENTER);
		ChangeListener<Number> resizeListener = (_, _, newVal) -> {
            ennemyStatsText.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, newVal.doubleValue() * fontSize));
        };
        ennemyStatsPane.heightProperty().addListener(resizeListener);
		return ennemyStatsText;
	}

	private void setEnnemyResPane(VBox leftPane) {
		ennemyResPane.setStyle(greenBorderStyle());
		ennemyResPane.setAlignment(Pos.CENTER);
		ennemyResPane.setVisible(false);
		
		BorderPane ennemyResPhysicalBox = getEnnemyResUnitPane(leftPane, SpellElement.PHYSICAL, ennemyResPhysicalValuePane, ennemyResPhysicalValueImage);
		BorderPane ennemyResGunBox = getEnnemyResUnitPane(leftPane, SpellElement.GUN, ennemyResGunValuePane, ennemyResGunValueImage);
		BorderPane ennemyResFireBox = getEnnemyResUnitPane(leftPane, SpellElement.FIRE, ennemyResFireValuePane, ennemyResFireValueImage);
		BorderPane ennemyResIceBox = getEnnemyResUnitPane(leftPane, SpellElement.ICE, ennemyResIceValuePane, ennemyResIceValueImage);
		BorderPane ennemyResElectricBox = getEnnemyResUnitPane(leftPane, SpellElement.ELECTRIC, ennemyResElectricValuePane, ennemyResElectricValueImage);
		BorderPane ennemyResWindBox = getEnnemyResUnitPane(leftPane, SpellElement.WIND, ennemyResWindValuePane, ennemyResWindValueImage);
		BorderPane ennemyResPsyBox = getEnnemyResUnitPane(leftPane, SpellElement.PSY, ennemyResPsyValuePane, ennemyResPsyValueImage);
		BorderPane ennemyResNuclearBox = getEnnemyResUnitPane(leftPane, SpellElement.NUCLEAR, ennemyResNuclearValuePane, ennemyResNuclearValueImage);
		BorderPane ennemyResDivineBox = getEnnemyResUnitPane(leftPane, SpellElement.DIVINE, ennemyResDivineValuePane, ennemyResDivineValueImage);
		BorderPane ennemyResCursedBox = getEnnemyResUnitPane(leftPane, SpellElement.CURSED, ennemyResCursedValuePane, ennemyResCursedValueImage);
		
		ennemyResPane.getChildren().addAll(ennemyResPhysicalBox, ennemyResGunBox, ennemyResFireBox, ennemyResIceBox, ennemyResElectricBox, ennemyResWindBox, ennemyResPsyBox, ennemyResNuclearBox, ennemyResDivineBox, ennemyResCursedBox);
	}

	private BorderPane getEnnemyResUnitPane(VBox leftPane, SpellElement characterElement, BorderPane valuePane, ImageView valueImage) {
		BorderPane ennemyResBox = new BorderPane();
		
		ImageView ennemyResImage = new ImageView();
		ennemyResImage = ui.IconProvider.getCharacterElementIcon(characterElement, 60);
		valuePane.prefWidthProperty().bind(leftPane.widthProperty());
		valuePane.prefHeightProperty().bind(leftPane.heightProperty());
		ennemyResBox.setCenter(new StackPane(ennemyResImage, valuePane));
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
		Button attackButton = new Button("ATTAQUER");
		attackButton.setFocusTraversable(false);
		attackButton.prefWidthProperty().bind(attackPane.widthProperty().multiply(.9));
		attackButton.setStyle(buttonStyle());
		attackButton.setOnAction(_ -> battleManager.firstChoiceAttack());
		attackPane.widthProperty().addListener((_, _, newW) -> {
			attackButton.setFont(Font.font("Arial", FontWeight.BOLD, newW.doubleValue() * .1));
		});
		attackPane.getChildren().addAll(attackButton);

		VBox personaPane = new VBox();
		personaPane.setStyle(redBorderStyle());
		personaPane.prefWidthProperty().bind(attackPersonaPane.widthProperty().multiply(.5));
		personaPane.setAlignment(Pos.CENTER);
		Button personaButton = new Button("PERSONA");
		personaButton.setFocusTraversable(false);
		personaButton.prefWidthProperty().bind(personaPane.widthProperty().multiply(.9));
		personaButton.setStyle(buttonStyle());
		personaButton.setOnAction(_ -> battleManager.firstChoicePersona());
		personaPane.widthProperty().addListener((_, _, newW) -> {
			personaButton.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, newW.doubleValue() * .1));
		});
		personaPane.getChildren().addAll(personaButton);

		attackPersonaPane.getChildren().addAll(attackPane, personaPane);
		
		VBox parryPane = new VBox();
		parryPane.setStyle(redBorderStyle());
		parryPane.prefHeightProperty().bind(firstChoicePane.heightProperty().multiply(.25));
		parryPane.setAlignment(Pos.CENTER);
		Button parryButton = new Button("PARER");
		parryButton.setFocusTraversable(false);
		parryButton.prefWidthProperty().bind(attackPane.widthProperty().multiply(.9));
		parryButton.setStyle(buttonStyle());
		parryButton.setOnAction(_ -> battleManager.firstChoiceParry());
		parryPane.widthProperty().addListener((_, _, newW) -> {
			parryButton.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, newW.doubleValue() * .05));
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
		
		Button cancelAttackButton = new Button("RETOUR");
		cancelAttackButton.setFocusTraversable(false);
		cancelAttackButton.prefWidthProperty().bind(attackSelectionPane.widthProperty().multiply(.4));
		cancelAttackButton.setStyle(buttonStyle());
		cancelAttackButton.setOnAction(_ -> battleManager.cancelChoice());
		attackSelectionPane.widthProperty().addListener((_, _, newW) -> {
			cancelAttackButton.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, newW.doubleValue() * .05));
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
		
		Button cancelAttackButton = new Button("RETOUR");
		cancelAttackButton.setFocusTraversable(false);
		cancelAttackButton.prefWidthProperty().bind(personaSpellSelectionPane.widthProperty().multiply(.4));
		cancelAttackButton.prefHeightProperty().bind(personaSpellSelectionPane.heightProperty().multiply(.18));
		cancelAttackButton.setStyle(buttonStyle());
		cancelAttackButton.setOnAction(_ -> battleManager.cancelChoice());
		attackSelectionPane.widthProperty().addListener((_, _, newW) -> {
			cancelAttackButton.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, newW.doubleValue() * .05));
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
			Label spellCost = new Label();
			spellCost.setTextFill(APColor);
			spellCost.setText("" + spell.getAPCost());
			spellCost.setAlignment(Pos.BOTTOM_CENTER);
			spellCost.prefWidthProperty().bind(ennemyStatsPane.widthProperty().multiply(.3));
			spellCost.widthProperty().addListener((_, _, newW) -> {
				spellCost.setFont(Font.font(newW.doubleValue() * .1));
			});
			spellElementPane.setTop(new StackPane(spellElementImage, spellCost));
			
			Label spellName = new Label();
			spellName.setText(spell.getName());
			spellName.setAlignment(Pos.CENTER);
			spellName.prefWidthProperty().bind(ennemyStatsPane.widthProperty().multiply(.4));
			spellName.widthProperty().addListener((_, _, newW) -> {
				spellName.setFont(Font.font(newW.doubleValue() * .1));
			});
			
			
			spellPane.getChildren().addAll(spellElementPane,spellName);
		}
		
		return spellPane;
	}
	
	private void setAllyStatsPane(VBox leftPane) {
		allyStatsPane.setStyle(greenBorderStyle());
		allyStatsPane.setAlignment(Pos.CENTER);
		allyStatsPane.setVisible(false);
		int marginRight = 40;
	    
	    Text allyStatsAttackLabel = getAllyStatsText("ATK");
	    
	    allyStatsAttackPane.prefWidthProperty().bind(allyStatsPane.widthProperty().multiply(.125));
	    HBox.setMargin(allyStatsAttackPane, new Insets(0, marginRight, 0, 0));
	    
	    Text allyStatsDefenseLabel = getAllyStatsText("DEF");

	    allyStatsDefensePane.prefWidthProperty().bind(allyStatsPane.widthProperty().multiply(.125));
	    HBox.setMargin(allyStatsDefensePane, new Insets(0, marginRight, 0, 0));
	    
	    Text allyStatsAccuEvaLabel = getAllyStatsText("PREC/ESQ");

	    allyStatsAccuEvaPane.prefWidthProperty().bind(allyStatsPane.widthProperty().multiply(.125));
	    HBox.setMargin(allyStatsAccuEvaPane, new Insets(0, marginRight, 0, 0));
	    
	    Text allyStatsCriticalLabel = getAllyStatsText("CRIT");

	    allyStatsCriticalPane.prefWidthProperty().bind(allyStatsPane.widthProperty().multiply(.125));
	    
	    allyStatsPane.getChildren().addAll(allyStatsAttackLabel, allyStatsAttackPane, allyStatsDefenseLabel, allyStatsDefensePane, allyStatsAccuEvaLabel, allyStatsAccuEvaPane, allyStatsCriticalLabel, allyStatsCriticalPane);
	}

	private Text getAllyStatsText(String text) {
		double fontSize = .32;
		Text allyStatsText = new Text(text);
		allyStatsText.setFill(Color.WHITE);
		allyStatsText.setStroke(Color.BLACK);
		allyStatsText.setStrokeWidth(2);
		allyStatsText.setTextAlignment(TextAlignment.CENTER);
		ChangeListener<Number> resizeListener = (_, _, newVal) -> {
            allyStatsText.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, newVal.doubleValue() * fontSize));
        };
        allyStatsPane.heightProperty().addListener(resizeListener);
		return allyStatsText;
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
		ennemyPane.visibleProperty().bind(ennemy.isAliveProperty());
		ennemyPane.styleProperty().bind(
		    Bindings.when(ennemy.isPlayingProperty())
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
		healthBar.setStroke(Color.BLACK);
		healthBar.setStrokeWidth(2);
		Rectangle currentHealthBar = new Rectangle();
		currentHealthBar.widthProperty().bind(ennemy.currentHPProperty().multiply(ennemyPane.widthProperty().divide(ennemy.getMaxHP())).subtract(4));
		currentHealthBar.heightProperty().bind(healthBarPane.heightProperty().subtract(4)); 
		currentHealthBar.setFill(healthColor);
		currentHealthBar.setLayoutX(2);
		currentHealthBar.setLayoutY(2);
		healthBarPane.getChildren().addAll(healthBar, currentHealthBar);
		
		Region enemyPaneSpacer2 = new Region();
		enemyPaneSpacer2.prefHeightProperty().bind(ennemyPane.heightProperty().multiply(.1));
		
		BorderPane ennemyIconPane = new BorderPane();
		ennemyIconPane.prefWidthProperty().bind(ennemyPane.widthProperty());
		ennemyIconPane.prefHeightProperty().bind(ennemyPane.heightProperty().multiply(.7));
		ennemyIconPane.setStyle(greenBorderStyle());
		AnimatedSprite ennemySprite = ui.IconProvider.getAnimatedCharacterIcon(ennemy, 2, 150, 150, 150, 500);
		ImageView effectImage = new ImageView();
		effectImage.setFitWidth(75);
		effectImage.setFitHeight(75);
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
		allyPane.styleProperty().bind(
		    Bindings.when(ally.isPlayingProperty())
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
		
		AnimatedSprite allySprite = ui.IconProvider.getAnimatedCharacterIcon(ally, 2, 150, 150, 150, 500);
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
		healthBar.setStroke(Color.BLACK);
		healthBar.setStrokeWidth(2);
		Rectangle currentHealthBar = new Rectangle();
		currentHealthBar.widthProperty().bind(ally.currentHPProperty().multiply(healthBarBox.widthProperty().multiply(.8).divide(ally.getMaxHP())).subtract(4));
		currentHealthBar.heightProperty().bind(healthBarPane.heightProperty().subtract(4)); 
		currentHealthBar.setFill(healthColor);
		currentHealthBar.setLayoutX(2);
		currentHealthBar.setLayoutY(2);
		healthBarPane.getChildren().addAll(healthBar, currentHealthBar);
		
		Label healthPoints = new Label();
		healthPoints.textProperty().bind(ally.currentHPProperty().asString());
		healthPoints.setAlignment(Pos.CENTER);
		healthPoints.setTextFill(healthColor);
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
		APBar.setStroke(Color.BLACK);
		APBar.setStrokeWidth(2);
		APBar.setStrokeWidth(2);
		Rectangle currentAPBar = new Rectangle();
		currentAPBar.widthProperty().bind(ally.currentAPProperty().multiply(APBarBox.widthProperty().multiply(.8).divide(ally.getMaxAP())).subtract(4));
		currentAPBar.heightProperty().bind(APBarPane.heightProperty().subtract(4)); 
		currentAPBar.setFill(APColor);
		currentAPBar.setLayoutX(2);
		currentAPBar.setLayoutY(2);
		APBarPane.getChildren().addAll(APBar, currentAPBar);
		
		Label APPoints = new Label();
		APPoints.textProperty().bind(ally.currentAPProperty().asString());
		APPoints.setAlignment(Pos.CENTER);
		APPoints.setTextFill(APColor);
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
