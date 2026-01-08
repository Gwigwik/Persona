package app;

import javafx.application.Application;
import javafx.stage.Stage;
import ui.scenes.BattleScene;
import ui.scenes.MenuScene;

import java.util.ArrayList;
import java.util.List;

import entities.Character;
import entities.ResistanceFactory;
import entities.StatFactory;
import game.BattleManager;

public class Main extends Application {
    
    public void start(Stage stage) {
    	
    	SceneManager sceneManager = new SceneManager(stage);
        
        stage.setTitle("Persona");
        stage.setMinWidth(700);
        stage.setMinHeight(400);
        
        Character hero = new Character("Héros", 100, 200, true, ResistanceFactory.characterResistances(), ResistanceFactory.initialDiscoveredResistances(), StatFactory.CharacterStats(), "/images/characters/hero.png");
        Character enemy = new Character("Slime", 80, 150, true, ResistanceFactory.characterResistances(), ResistanceFactory.initialDiscoveredResistances(), StatFactory.CharacterStats(), "/images/characters/slime.png");

        List<Character> charactersInBattle = new ArrayList<>();
        charactersInBattle.add(hero);
        charactersInBattle.add(enemy);

        BattleManager battleManager = new BattleManager(charactersInBattle);

	     sceneManager.addScene(SceneType.MENU, new MenuScene().getScene());
	     sceneManager.addScene(SceneType.BATTLE, new BattleScene(battleManager).getScene());
	
	     sceneManager.switchTo(SceneType.BATTLE);
    }

    public static void main(String[] args) {
        launch();
    }
}
