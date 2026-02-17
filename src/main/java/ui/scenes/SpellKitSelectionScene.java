package ui.scenes;

import entities.Character;
import entities.spells.Spell;
import entities.spells.SpellFactory;
import game.BattleManager;

import java.util.List;

import app.SceneManager;
import app.SceneType;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;

public class SpellKitSelectionScene {

    private final Scene scene;
	private IntegerProperty jokerKitIndice = new SimpleIntegerProperty();
	private IntegerProperty makotoKitIndice = new SimpleIntegerProperty();
	private IntegerProperty yuKitIndice = new SimpleIntegerProperty();

	Color APColor = new Color(235.0 / 255, 137.0 / 255, 217.0 / 255, 1.0 );
	
    public Scene getScene() {
        return scene;
    }

    public SpellKitSelectionScene(SceneManager sceneManager, List<BattleManager> battleManagers, Character joker, Character makoto, Character yu) {
	    VBox globalPane = new VBox();
	    globalPane.setStyle("-fx-background-color: grey;");
	    globalPane.setAlignment(Pos.CENTER);
	    
	    jokerKitIndice.set(-1);
	    makotoKitIndice.set(-1);
	    yuKitIndice.set(-1);
	    
	    HBox topPane = getTopPane(globalPane);
	    
	    VBox bottomPane = getBottomPane(globalPane, topPane);
	    
	    Button continueButton = new Button("Continuer");
	    continueButton.setOnMouseClicked(event -> {
		    if (event.getButton() == MouseButton.PRIMARY && jokerKitIndice.get() != -1 && makotoKitIndice.get() != -1 && yuKitIndice.get() != -1) {
	    		joker.setSpellKit(SpellFactory.getISpellKit(jokerKitIndice.get()));
	    		makoto.setSpellKit(SpellFactory.getISpellKit(makotoKitIndice.get()));
	    		yu.setSpellKit(SpellFactory.getISpellKit(yuKitIndice.get()));
	    		battleManagers.forEach(battleManager -> {
	    			battleManager.setSpellActualizer();
	    		});
		    	sceneManager.switchTo(SceneType.BATTLE1);
		    }
		});
	    continueButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
	    continueButton.setFocusTraversable(false);

	    globalPane.getChildren().addAll(topPane, bottomPane, continueButton);
        scene = new Scene(globalPane);
    }

	private HBox getTopPane(VBox globalPane) {
		HBox topPane = new HBox();
	    topPane.prefHeightProperty().bind(globalPane.heightProperty().multiply(.2));
	    topPane.prefWidthProperty().bind(globalPane.widthProperty());
	    
	    topPane.setAlignment(Pos.CENTER);
	    topPane.setSpacing(40);  
	    
	    Label yuLabel = new Label("Yu");
	    yuLabel.setStyle("-fx-text-fill: white; -fx-background-color: black; -fx-border-color: yellow; -fx-border-width: 2; -fx-padding: 8 16 8 16;");
	    globalPane.widthProperty().addListener((_, _, newW) -> {
	    	yuLabel.setFont(Font.font(newW.doubleValue() * .02));
		});
	    Label jokerLabel = new Label("Joker");
	    jokerLabel.setStyle("-fx-text-fill: white; -fx-background-color: black; -fx-border-color: red; -fx-border-width: 2; -fx-padding: 8 16 8 16;");
	    globalPane.widthProperty().addListener((_, _, newW) -> {
	    	jokerLabel.setFont(Font.font(newW.doubleValue() * .02));
		});
	    Label makotoLabel = new Label("Makoto");
	    makotoLabel.setStyle("-fx-text-fill: white; -fx-background-color: black; -fx-border-color: blue; -fx-border-width: 2; -fx-padding: 8 16 8 16;");
	    globalPane.widthProperty().addListener((_, _, newW) -> {
	    	makotoLabel.setFont(Font.font(newW.doubleValue() * .02));
		});
	    
	    topPane.getChildren().addAll(yuLabel, jokerLabel, makotoLabel);
	    
		return topPane;
	}
	
	private VBox getBottomPane(VBox globalPane, HBox topPane) {
		VBox bottomPane = new VBox();
		bottomPane.prefHeightProperty().bind(globalPane.heightProperty().multiply(.7));
		bottomPane.prefWidthProperty().bind(globalPane.widthProperty());
	    
	    HBox bottomTopPane = getBottomHalfPane(bottomPane, 0);
	    HBox bottomBottomPane = getBottomHalfPane(bottomPane, 5);
	    
	    bottomPane.getChildren().addAll(bottomTopPane, bottomBottomPane);
		return bottomPane;
	}

	private HBox getBottomHalfPane(VBox bottomPane, int startingIndice) {
		HBox bottomHalfPane = new HBox();
		bottomHalfPane.prefHeightProperty().bind(bottomPane.heightProperty().multiply(.5));
		bottomHalfPane.prefWidthProperty().bind(bottomPane.widthProperty());
	    
	    HBox spellKit0 = getiSPellKit(bottomHalfPane, startingIndice);
	    HBox spellKit1 = getiSPellKit(bottomHalfPane, startingIndice+1);
	    HBox spellKit2 = getiSPellKit(bottomHalfPane, startingIndice+2);
	    HBox spellKit3 = getiSPellKit(bottomHalfPane, startingIndice+3);
	    HBox spellKit4 = getiSPellKit(bottomHalfPane, startingIndice+4);
	    
	    bottomHalfPane.getChildren().addAll(spellKit0, spellKit1, spellKit2, spellKit3, spellKit4);
		return bottomHalfPane;
	}

	private HBox getiSPellKit(HBox bottomHalfPane, int indice) {
		HBox spellKit = new HBox();
		IntegerProperty spellKitIndice = new SimpleIntegerProperty();
		HBox.setMargin(spellKit, new Insets(10, 10, 10, 10));
		spellKitIndice.set(indice);
	    spellKit.prefHeightProperty().bind(bottomHalfPane.heightProperty());
	    spellKit.prefWidthProperty().bind(bottomHalfPane.widthProperty().multiply(.2));
	    spellKit.setOnMouseClicked(event -> {
		    if (event.getButton() == MouseButton.PRIMARY) {
		    	iSpellKitClicked(indice);
		    }
		});
	    spellKit.styleProperty().bind(
		    Bindings.when(jokerKitIndice.isEqualTo(spellKitIndice))
		        .then("-fx-border-color: red; -fx-border-width: 2; -fx-border-style: solid;")
		        .otherwise(Bindings.when(makotoKitIndice.isEqualTo(spellKitIndice))
			        .then("-fx-border-color: blue; -fx-border-width: 2; -fx-border-style: solid;")
			        .otherwise(Bindings.when(yuKitIndice.isEqualTo(spellKitIndice))
				        .then("-fx-border-color: yellow; -fx-border-width: 2; -fx-border-style: solid;")
				        .otherwise("-fx-border-color: black; -fx-border-width: 2; -fx-border-style: solid;")))
		);
	    List<Spell> spellsList = SpellFactory.getISpellKit(indice);
	    VBox spellsListPane = new VBox();
	    spellsListPane.prefWidthProperty().bind(spellKit.widthProperty());
	    HBox spell0 = getISpellPane(spellsListPane, spellsList.get(0));
	    HBox spell1 = getISpellPane(spellsListPane, spellsList.get(1));
	    HBox spell2 = getISpellPane(spellsListPane, spellsList.get(2));
	    HBox spell3 = getISpellPane(spellsListPane, spellsList.get(3));
	    HBox spell4 = getISpellPane(spellsListPane, spellsList.get(4));
	    spellsListPane.getChildren().addAll(spell0, spell1, spell2, spell3, spell4);
	    
	    spellKit.getChildren().addAll(spellsListPane);
	    return spellKit;
	}
	
	private HBox getISpellPane(VBox spellsListPane, Spell spell) {
		HBox spellPane = new HBox();
		spellPane.prefWidthProperty().bind(spellsListPane.widthProperty());
		spellPane.prefHeightProperty().bind(spellsListPane.heightProperty().multiply(.2));
		spellPane.setAlignment(Pos.CENTER);
		spellPane.setStyle("-fx-background-color: black;");
		
		BorderPane descriptionPane = new BorderPane();
		descriptionPane.setStyle("-fx-background-color: white;");
		Label descriptionLabel = new Label(" " + spell.getDescription() + " ");
		descriptionLabel.setFont(Font.font(16));
		descriptionLabel.setTextFill(Color.BLACK);
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

		VBox spellElementPane = new VBox();
		spellElementPane.prefWidthProperty().bind(spellPane.widthProperty().multiply(.2));
		spellElementPane.prefHeightProperty().bind(spellPane.heightProperty());
		spellElementPane.setAlignment(Pos.CENTER);
		ImageView spellElementImage = new ImageView();
		spellElementImage = ui.IconProvider.getCharacterElementSpellIcon(spell.getElement(), 50);
		spellElementImage.setPreserveRatio(true);
		spellElementPane.getChildren().addAll(spellElementImage);
		
		Label spellName = new Label();
		spellName.setTextFill(Color.WHITE);
		spellName.setText(spell.getName());
		spellName.setAlignment(Pos.CENTER);
		spellName.prefWidthProperty().bind(spellPane.widthProperty().multiply(.6));
		spellName.widthProperty().addListener((_, _, newW) -> {
			spellName.setFont(Font.font(newW.doubleValue() * .11));
		});
		
		Label spellCost = new Label();
		spellCost.setTextFill(APColor);
		spellCost.setText("" + spell.getAPCost());
		spellCost.setAlignment(Pos.CENTER);
		spellCost.prefWidthProperty().bind(spellPane.widthProperty().multiply(.2));
		spellCost.widthProperty().addListener((_, _, newW) -> {
			spellCost.setFont(Font.font(newW.doubleValue() * .35));
		});
		
		spellPane.getChildren().addAll(spellElementPane, spellName, spellCost);
		
		return spellPane;
	}
	
	private void iSpellKitClicked(int i) {
		if (jokerKitIndice.get() == i)
			jokerKitIndice.set(-1);
		else if (makotoKitIndice.get() == i)
			makotoKitIndice.set(-1);
		else if (yuKitIndice.get() == i)
			yuKitIndice.set(-1);
		else if (jokerKitIndice.get() == -1)
			jokerKitIndice.set(i);
		else if (makotoKitIndice.get() == -1)
			makotoKitIndice.set(i);
		else if (yuKitIndice.get() == -1)
			yuKitIndice.set(i);
	}
}
