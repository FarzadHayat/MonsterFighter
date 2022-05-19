package main;
import java.util.ArrayList;
import java.util.Random;

import exceptions.StatMaxedOutException;
import monsters.Raka;

/**
 * Holds an array of monsters with additional functionality.
 * @author Farzad and Daniel
 */
@SuppressWarnings("serial")
public class MonsterInventory extends ArrayList<Monster> {
    
	/**
	 * Fields
	 */
    protected int maxSize;

    protected GameEnvironment game;
    protected Player player;
    
    
    /**
     * Constructors
     */
    
    /**
     * Create a new MonsterInventory object with the given size.
     * @param maxSize the given maxSize
     * @param game the given game
     */
    public MonsterInventory (int maxSize, GameEnvironment game) {
    	this.maxSize = maxSize;
    	this.game = game;
    	player = game.getPlayer();
    };
    
    
    /**
     * Getters and setters
     */
    
    /**
     * get the value of maxSize
     * @return the value of maxSize
     */
    public int getMaxSize() {
    	return maxSize;
    }
    
    
    /**
     * Set the value of maxSize
     * @param maxSize the new value of maxSize
     */
    public void setMaxSize(int maxSize) {
    	this.maxSize = maxSize;
    }

	
	/**
	 * Functional
	 */
	
	/**
	 * @return if the inventory is full or not
	 */
	public boolean isFull() {
		return size() >= maxSize;
	}


	/**
	 * @return whether the inventory is empty
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
    
    
    /**
     * @return whether all monsters in the inventory have fainted
     */
    public boolean allFainted() {
    	boolean fainted = true;
    	for (Monster monster : this) {
    		if (!monster.getIsFainted()) {
    			fainted = false;
    		}
    	}
    	return fainted;
    }
    
    
    /**
     * Heal all monsters in the inventory.
     */
    public void healAll() {
    	for (Monster monster : this) {
    		monster.heal();
    	}
    }
    
    
    /**
	 * Get a random non fainted monster from the inventory.
	 * @return the randomly selected monster
	 */
	public Monster random() {
		Random random = new Random();
    	boolean found = false;
    	Monster monster = null;
    	
    	while (!found) {
    		int index = random.nextInt(size());
    		monster = get(index);
    		if (!monster.getIsFainted()) {
    			found = true;
    		}
    	}
    	
		return monster;
	}
    
    
    /**
     * Populate the inventory by randomly selecting monsters from all monsters in the game.
     */
    public void randomise() {
    	while (!this.isEmpty()) {
    		remove(this.get(0));
    	}
    	Random random = new Random();
    	for (int i = 0; i < maxSize; i++) {
    		int index = random.nextInt(game.getAllMonsters().size());
    		Monster monster = game.getAllMonsters().get(index).clone();
    		if(monster instanceof Raka) {
    			((Raka) monster).setTeam(this);
    		}
    		add(monster);
    	}
    }
	
    
    /**
     * Level up monsters based on the number of day and the max level of monsters
     */
    public void levelUpOnDay() {
    	int maxLevel = random().getMaxLevel();
    	int dayToLevelUp = game.getNumDays()/maxLevel;
    	
    	if(game.getDay() % dayToLevelUp == 0){
    		for(Monster monster: this) {
				try {
					monster.levelUp();
				}
				catch(StatMaxedOutException e) {}
    			
    		}
    	}
    }
    
		
	/**
	 * @return result a string representation of the inventory object
	 */
	public String toString() {
		String result = "";
		for (Monster monster : this)
		{
			result += "\n" + monster;
		}
		return result;
	}


	/**
	 * @return result a string representation of the inventory object with command line options
	 */
	public String view() {
		String result = "";
		int start = 1;
    	for (Monster monster : this) {
    		result += String.format("%s: %s\n", start, monster);
    		start++;
    	}
    	return result;
	}
	
}
