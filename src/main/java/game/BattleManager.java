package game;

import java.util.List;
import entities.characters.Character;

public class BattleManager {
	private final List<Character> turnOrder;

    public BattleManager(List<Character> charactersInBattle) {
        this.turnOrder = charactersInBattle;
    }

    public List<Character> getTurnOrder() {
        return turnOrder;
    }

    public Character getCurrentCharacter() {
        return turnOrder.get(0);
    }

    public void nextTurn() {
        Character c = turnOrder.remove(0);
        turnOrder.add(c);
    }
}
