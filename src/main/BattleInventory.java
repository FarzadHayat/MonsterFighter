package main;
import java.util.ArrayList;

public class BattleInventory extends Inventory {
    
    /**
     * Constructors
     */
    
    /**
     * Create a new BattleInventory object with the given size.
     * @param maxSize the given maxSize
     * @param game the given game
     */
	public BattleInventory (int maxSize, GameEnvironment game) {
    	super(maxSize, game);
    	setList(new ArrayList<Storable>(maxSize));
    };
    
    
    /**
     * Functional 
     */
	
    /**
     * Populate the inventory by randomly generating battles.
     * Each randomly generated battle is a new battle populated with randomly selected monsters from all monsters in the game.
     */
    public void randomise() {
    	ArrayList<Storable> battleList = new ArrayList<Storable>(maxSize);
    	for (int i = 0; i < maxSize; i++) {
    		Battle battle = new Battle(game);
    		battle.getEnemyMonsters().randomise();
    		battleList.add(battle);
    	}
    	setList(battleList);
    }

}
