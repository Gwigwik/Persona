package app;

import javafx.application.Application;
import javafx.stage.Stage;
import ui.scenes.BattleScene;
import ui.scenes.MenuScene;

import java.util.ArrayList;
import java.util.List;

import entities.Character;
import entities.resistances.ResistanceFactory;
import entities.spells.SpellElement;
import entities.stats.StatFactory;
import game.BattleManager;

public class Main extends Application {
    
    public void start(Stage stage) {
    	
    	SceneManager sceneManager = new SceneManager(stage);
        
        stage.setTitle("Persona");
        stage.setMinWidth(1050);
        stage.setMinHeight(600);
        
        Character hero = new Character("Héros1", 400, 200, true, SpellElement.PHYSICAL, ResistanceFactory.characterResistances(), ResistanceFactory.initialDiscoveredResistancesTrue(), StatFactory.CharacterStats(), "pompon");
        Character hero2 = new Character("Héros2", 400, 200, true, SpellElement.GUN, ResistanceFactory.characterResistances(), ResistanceFactory.initialDiscoveredResistancesTrue(), StatFactory.CharacterStats(), "pompon");
        Character hero3 = new Character("Héros3", 400, 200, true, SpellElement.PHYSICAL, ResistanceFactory.characterResistances(), ResistanceFactory.initialDiscoveredResistancesTrue(), StatFactory.CharacterStats(), "pompon");
        Character enemy = new Character("Slime1", 400, 150, true, SpellElement.PHYSICAL, ResistanceFactory.characterResistances(), ResistanceFactory.initialDiscoveredResistancesTrue(), StatFactory.CharacterStats(), "pompon");
        Character enemy2 = new Character("Slime2", 400, 150, true, SpellElement.PHYSICAL, ResistanceFactory.characterResistances(), ResistanceFactory.initialDiscoveredResistancesFalse(), StatFactory.CharacterStats(), "pompon");
        Character enemy3 = new Character("Slime3", 400, 150, true, SpellElement.PHYSICAL, ResistanceFactory.characterResistances(), ResistanceFactory.initialDiscoveredResistancesFalse(), StatFactory.CharacterStats(), "pompon");
//        Character noCharacter = new Character("No character", 0, 0, false, true, null, null, null, null);
        
        List<Character> charactersInBattle = new ArrayList<>();
        charactersInBattle.add(hero);
        charactersInBattle.add(enemy);
        charactersInBattle.add(hero2);
        charactersInBattle.add(enemy2);
        charactersInBattle.add(hero3);
        charactersInBattle.add(enemy3);

        BattleManager battleManager = new BattleManager(charactersInBattle);

	     sceneManager.addScene(SceneType.MENU, new MenuScene().getScene());
	     sceneManager.addScene(SceneType.BATTLE, new BattleScene(battleManager).getScene());
	
	     sceneManager.switchTo(SceneType.BATTLE);
    }

    public static void main(String[] args) {
        launch();
    }
}
