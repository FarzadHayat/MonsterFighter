package main;
import java.util.ArrayList;
import java.util.Random;

import exceptions.InventoryFullException;
import exceptions.StatMaxedOutException;
import monsters.Raka;

/**
 * Holds an array of monsters with additional functionality.
 * @author Farzad and Daniel
 */
public class MonsterInventory {
    
	/**
	 * Fields
	 */
    protected int maxSize;
    private ArrayList<Monster> list;

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
    	list = new ArrayList<Monster>(maxSize);
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
	 * get the value of list
	 * @return the value of list
	 */
	public ArrayList<Monster> getList() {
		return list;
	}


	/**
	 * Set the value of list
	 * @param list the new value of list
	 */
	public void setList(ArrayList<Monster> list) {
		this.list = list;
	}

	
	/**
	 * Functional
	 */

	/**
     * Add the given monster to the inventory.
     * @param monster the given monster
     * @throws InventoryFullException if the inventory is already full
     */
    public void add(Monster monster) throws InventoryFullException
    {
    	if (!isFull()) {
    		list.add(monster);
    		if(monster instanceof Raka) {
    			((Raka) monster).setTeam(this);
    		}
    	}
    	else {
    		throw new InventoryFullException("Monster inventory is full!");
    	}
    }
    
    
    /**
     * Add the given monster at the given index to the inventory.
     * @param monster the given monster
     * @throws InventoryFullException if the inventory is already full
     */
    public void add(int index, Monster monster) throws InventoryFullException
    {
    	if (!isFull()) {
    		list.add(index, monster);
    	}
    	else {
    		throw new InventoryFullException("Monster inventory is full!");
    	}
    }


    /**
     * Remove the given monster from the inventory.
     * @param monster the given monster
     */
    public void remove(Monster monster)
    {
    	list.remove(monster);
    }
	
	
	/**
	 * @return if the inventory is full or not
	 */
	public boolean isFull() {
		return list.size() >= maxSize;
	}


	/**
	 * @return whether the inventory is empty
	 */
	public boolean isEmpty() {
		return list.size() == 0;
	}
    
    
    /**
     * @return whether all monsters in the inventory have fainted
     */
    public boolean allFainted() {
    	boolean fainted = true;
    	for (Monster monster : getList()) {
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
    	for (Monster monster : getList()) {
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
    		int index = random.nextInt(list.size());
    		monster = list.get(index);
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
    	Random random = new Random();
    	ArrayList<Monster> newList = new ArrayList<Monster>(maxSize);
    	for (int i = 0; i < maxSize; i++) {
    		int index = random.nextInt(game.getAllMonsters().getList().size());
    		Monster monster = game.getAllMonsters().getList().get(index).clone();
    		if(monster instanceof Raka) {
    			((Raka) monster).setTeam(this);
    		}
    		newList.add(monster);
    	}
    	setList(newList);
    }
	
    
    /**
     * Level up monsters based on the number of day and the max level of monsters
     */
    public void levelUpOnDay() {
    	int maxLevel = random().getMaxLevel();
    	int dayToLevelUp = game.getNumDays()/maxLevel;
    	
    	if(game.getDay() % dayToLevelUp == 0){
    		for(Monster monster: list) {
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
		for (Monster monster : list)
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
    	for (Monster monster : list) {
    		result += String.format("%s: %s\n", start, monster);
    		start++;
    	}
    	return result;
	}
	
}
