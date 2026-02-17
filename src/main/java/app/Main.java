package app;

import javafx.application.Application;
import javafx.stage.Stage;
import ui.scenes.BattleScene;
import ui.scenes.InitialSettingsScene;
import ui.scenes.MenuScene;
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

        Character joker = new Character("Joker", 150, 200, true, SpellElement.PHYSICAL, SpellFactory.emptySpellKit(), ResistanceFactory.characterResistances(), ResistanceFactory.initialDiscoveredResistancesTrue(), StatFactory.CharacterStats(), "joker");
        Character makoto = new Character("Makoto", 150, 200, true, SpellElement.GUN, SpellFactory.emptySpellKit(), ResistanceFactory.characterResistances(), ResistanceFactory.initialDiscoveredResistancesTrue(), StatFactory.CharacterStats(), "makoto");
        Character yu = new Character("Yu", 150, 200, true, SpellElement.PHYSICAL, SpellFactory.emptySpellKit(), ResistanceFactory.characterResistances(), ResistanceFactory.initialDiscoveredResistancesTrue(), StatFactory.CharacterStats(), "yu");
        
        sceneManager.addScene(SceneType.MENU, new MenuScene(sceneManager).getScene());
        sceneManager.addScene(SceneType.INITIALSETTINGS, new InitialSettingsScene(sceneManager).getScene());
        
        List<BattleManager> battleManagers;
        
//      Battle 1
        Character matt = new Character("Matt", 300, 999999, true, SpellElement.PHYSICAL, SpellFactory.getMattSpellKit(), ResistanceFactory.getMattResistances(), ResistanceFactory.initialDiscoveredResistancesTrue(), StatFactory.CharacterStats(), "matt");
        Character manon = new Character("Manon", 300, 999999, true, SpellElement.PHYSICAL, SpellFactory.getManonSpellKit(), ResistanceFactory.getSophieManonResistances(), ResistanceFactory.initialDiscoveredResistancesTrue(), StatFactory.CharacterStats(), "manon");
        Character sophie = new Character("Sophie", 300, 999999, true, SpellElement.PHYSICAL, SpellFactory.getSophieSpellKit(), ResistanceFactory.getSophieManonResistances(), ResistanceFactory.initialDiscoveredResistancesTrue(), StatFactory.CharacterStats(), "sophie");     
        List<Character> charactersInBattle1 = new ArrayList<>();
        charactersInBattle1.add(joker);
        charactersInBattle1.add(matt);
        charactersInBattle1.add(makoto);
        charactersInBattle1.add(manon);
        charactersInBattle1.add(yu);
        charactersInBattle1.add(sophie);
        BattleManager battleManager1 = new BattleManager(charactersInBattle1, sceneManager, SceneType.BATTLE2);
        sceneManager.addScene(SceneType.BATTLE1, new BattleScene(battleManager1).getScene());
        
//      Battle 2
        Character flo = new Character("Flo", 300, 999999, true, SpellElement.PHYSICAL, SpellFactory.getFloSpellKit(), ResistanceFactory.getFloResistances(), ResistanceFactory.initialDiscoveredResistancesTrue(), StatFactory.CharacterStats(), "flo");
        Character hugo = new Character("Hugo", 300, 999999, true, SpellElement.PHYSICAL, SpellFactory.getHugoSpellKit(), ResistanceFactory.getHugoResistances(), ResistanceFactory.initialDiscoveredResistancesTrue(), StatFactory.CharacterStats(), "hugo");
        Character mara = new Character("Mara", 300, 999999, true, SpellElement.PHYSICAL, SpellFactory.getMaraSpellKit(), ResistanceFactory.getMaraResistances(), ResistanceFactory.initialDiscoveredResistancesTrue(), StatFactory.CharacterStats(), "mara");
        List<Character> charactersInBattle2 = new ArrayList<>();
        charactersInBattle2.add(joker);
        charactersInBattle2.add(flo);
        charactersInBattle2.add(makoto);
        charactersInBattle2.add(mara);
        charactersInBattle2.add(yu);
        charactersInBattle2.add(hugo);
        BattleManager battleManager2 = new BattleManager(charactersInBattle2, sceneManager, SceneType.BATTLE3);
        sceneManager.addScene(SceneType.BATTLE2, new BattleScene(battleManager2).getScene());

//      Battle 3
        Character adrien = new Character("Adrien", 300, 999999, true, SpellElement.PHYSICAL, SpellFactory.getAdrienSpellKit(), ResistanceFactory.getAdrienNeoliBriceResistances(), ResistanceFactory.initialDiscoveredResistancesTrue(), StatFactory.CharacterStats(), "adrien");
        Character neoli = new Character("Neoli", 300, 999999, true, SpellElement.PHYSICAL, SpellFactory.getNeoliSpellKit(), ResistanceFactory.getAdrienNeoliBriceResistances(), ResistanceFactory.initialDiscoveredResistancesTrue(), StatFactory.CharacterStats(), "neoli");
        Character brice = new Character("Brice", 300, 999999, true, SpellElement.PHYSICAL, SpellFactory.getBriceSpellKit(), ResistanceFactory.getAdrienNeoliBriceResistances(), ResistanceFactory.initialDiscoveredResistancesTrue(), StatFactory.CharacterStats(), "brice");
        List<Character> charactersInBattle3 = new ArrayList<>();
        charactersInBattle3.add(joker);
        charactersInBattle3.add(adrien);
        charactersInBattle3.add(makoto);
        charactersInBattle3.add(neoli);
        charactersInBattle3.add(yu);
        charactersInBattle3.add(brice);
        BattleManager battleManager3 = new BattleManager(charactersInBattle3, sceneManager, SceneType.BATTLE4);
        sceneManager.addScene(SceneType.BATTLE3, new BattleScene(battleManager3).getScene());

//      Battle 4
        Character leo = new Character("Leo", 300, 999999, true, SpellElement.PHYSICAL, SpellFactory.getAdrienSpellKit(), ResistanceFactory.getAdrienNeoliBriceResistances(), ResistanceFactory.initialDiscoveredResistancesTrue(), StatFactory.CharacterStats(), "leo");
        Character lisa = new Character("Lisa", 300, 999999, true, SpellElement.PHYSICAL, SpellFactory.getNeoliSpellKit(), ResistanceFactory.getAdrienNeoliBriceResistances(), ResistanceFactory.initialDiscoveredResistancesTrue(), StatFactory.CharacterStats(), "lisa");
        List<Character> charactersInBattle4 = new ArrayList<>();
        charactersInBattle4.add(joker);
        charactersInBattle4.add(noCharacter);
        charactersInBattle4.add(makoto);
        charactersInBattle4.add(leo);
        charactersInBattle4.add(yu);
        charactersInBattle4.add(lisa);
        BattleManager battleManager4 = new BattleManager(charactersInBattle4, sceneManager, SceneType.BATTLE5);
        sceneManager.addScene(SceneType.BATTLE4, new BattleScene(battleManager4).getScene());
        
        battleManagers = new ArrayList<>(List.of(battleManager1, battleManager2, battleManager3, battleManager4));
        sceneManager.addScene(SceneType.SPELLKITSELECTION, new SpellKitSelectionScene(sceneManager, battleManagers, joker, makoto, yu).getScene());
        sceneManager.switchTo(SceneType.MENU);
    }

    public static void main(String[] args) {
        launch();
    }
}
