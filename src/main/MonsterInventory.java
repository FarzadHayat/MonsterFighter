package main;
import java.util.ArrayList;
import java.util.Random;

public class MonsterInventory extends Inventory {
    
    /**
     * Constructors
     */
    
    /**
     * Create a new MonsterInventory object with the given size.
     * @param maxSize the given maxSize
     * @param game the given game
     */
    public MonsterInventory (int maxSize, GameEnvironment game) {
    	super(maxSize, game);
    	setList(new ArrayList<Storable>(maxSize));
    };
    
    
//    public MonsterInventory (int maxSize, GameEnvironment game) {
//    	super(maxSize, game, new ArrayList<Monster>(maxSize));
//    };


	/**
     * @return whether all monsters in the inventory have fainted
     */
    public boolean allFainted() {
    	boolean fainted = true;
    	for (Storable monster : getList()) {
    		if (!((Monster) monster).getIsFainted()) {
    			fainted = false;
    		}
    	}
    	return fainted;
    }
    
    
    /**
     * Heal all monsters in the inventory.
     */
    public void healAll() {
    	for (Storable monster : getList()) {
    		((Monster) monster).heal();
    	}
    }
    
    
    /**
     * Populate the inventory by randomly selecting monsters from all monsters in the game.
     */
    public void randomise() {
    	Random random = new Random();
    	ArrayList<Storable> newList = new ArrayList<Storable>(maxSize);
    	for (int i = 0; i < maxSize; i++) {
    		int index = random.nextInt(game.getAllMonsters().size());
    		Monster monster = (Monster) game.getAllMonsters().get(index).clone();
    		newList.add(monster);
    	}
    	setList(newList);
    }

}
