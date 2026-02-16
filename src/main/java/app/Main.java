package app;

import javafx.application.Application;
import javafx.stage.Stage;
import ui.scenes.BattleScene;
import ui.scenes.InitialSettingsScene;
import ui.scenes.MenuScene;
import ui.scenes.RulesScene;
import ui.scenes.SpellKitSelectionScene;

import java.util.ArrayList;
import java.util.List;

import entities.Character;
import entities.resistances.ResistanceFactory;
import entities.spells.SpellElement;
import entities.spells.SpellFactory;
import entities.stats.StatFactory;
import game.BattleManager;

public class Main extends Application {
    
    public void start(Stage stage) {
      Character noCharacter = new Character("No character", 0, 0, false, SpellElement.PHYSICAL, SpellFactory.emptySpellKit(), ResistanceFactory.characterResistances(), ResistanceFactory.initialDiscoveredResistancesTrue(), StatFactory.CharacterStats(), "pompon");
    	
    	SceneManager sceneManager = new SceneManager(stage);
        
        stage.setTitle("Persona");
        stage.setMinWidth(1050);
        stage.setMinHeight(600);

        Character joker = new Character("Joker", 150, 200, true, SpellElement.PHYSICAL, SpellFactory.emptySpellKit(), ResistanceFactory.characterResistances(), ResistanceFactory.initialDiscoveredResistancesTrue(), StatFactory.CharacterStats(), "pompon");
        Character makoto = new Character("Makoto", 150, 200, true, SpellElement.GUN, SpellFactory.emptySpellKit(), ResistanceFactory.characterResistances(), ResistanceFactory.initialDiscoveredResistancesTrue(), StatFactory.CharacterStats(), "pompon");
        Character yu = new Character("Yu", 150, 200, true, SpellElement.PHYSICAL, SpellFactory.emptySpellKit(), ResistanceFactory.characterResistances(), ResistanceFactory.initialDiscoveredResistancesTrue(), StatFactory.CharacterStats(), "pompon");
        
        sceneManager.addScene(SceneType.MENU, new MenuScene(sceneManager).getScene());
        sceneManager.addScene(SceneType.INITIALSETTINGS, new InitialSettingsScene(sceneManager).getScene());
        sceneManager.addScene(SceneType.RULES, new RulesScene(sceneManager).getScene());
        
        
//      Chat 1
        
//      Battle 1
        Character matt = new Character("Matt", 1, 999999, true, SpellElement.PHYSICAL, SpellFactory.getMattSpellKit(), ResistanceFactory.getMattResistances(), ResistanceFactory.initialDiscoveredResistancesTrue(), StatFactory.CharacterStats(), "pompon");
        Character manon = new Character("Manon", 1, 999999, true, SpellElement.PHYSICAL, SpellFactory.getManonSpellKit(), ResistanceFactory.getSophieManonResistances(), ResistanceFactory.initialDiscoveredResistancesTrue(), StatFactory.CharacterStats(), "pompon");
        Character sophie = new Character("Sophie", 1, 999999, true, SpellElement.PHYSICAL, SpellFactory.getSophieSpellKit(), ResistanceFactory.getSophieManonResistances(), ResistanceFactory.initialDiscoveredResistancesTrue(), StatFactory.CharacterStats(), "pompon");     
        List<Character> charactersInBattle1 = new ArrayList<>();
        charactersInBattle1.add(joker);
        charactersInBattle1.add(matt);
        charactersInBattle1.add(makoto);
        charactersInBattle1.add(manon);
        charactersInBattle1.add(yu);
        charactersInBattle1.add(sophie);
        BattleManager battleManager1 = new BattleManager(charactersInBattle1, sceneManager);
        

        sceneManager.addScene(SceneType.SPELLKITSELECTION, new SpellKitSelectionScene(sceneManager, battleManager1, joker, makoto, yu).getScene());
        sceneManager.addScene(SceneType.BATTLE1, new BattleScene(battleManager1).getScene());
        
//      Chat 2
        
//      Battle 2
        Character flo = new Character("Flo", 300, 999999, true, SpellElement.PHYSICAL, SpellFactory.getFloSpellKit(), ResistanceFactory.getFloResistances(), ResistanceFactory.initialDiscoveredResistancesTrue(), StatFactory.CharacterStats(), "pompon");
        Character hugo = new Character("Hugo", 300, 999999, true, SpellElement.PHYSICAL, SpellFactory.getHugoSpellKit(), ResistanceFactory.getHugoResistances(), ResistanceFactory.initialDiscoveredResistancesTrue(), StatFactory.CharacterStats(), "pompon");
        List<Character> charactersInBattle2 = new ArrayList<>();
        charactersInBattle2.add(joker);
        charactersInBattle2.add(noCharacter);
        charactersInBattle2.add(makoto);
        charactersInBattle2.add(flo);
        charactersInBattle2.add(yu);
        charactersInBattle2.add(hugo);
        BattleManager battleManager2 = new BattleManager(charactersInBattle2, sceneManager);
        sceneManager.addScene(SceneType.BATTLE2, new BattleScene(battleManager2).getScene());
        
        sceneManager.switchTo(SceneType.SPELLKITSELECTION);
    }

    public static void main(String[] args) {
        launch();
    }
}
