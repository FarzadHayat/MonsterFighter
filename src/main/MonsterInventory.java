package main;
import java.util.ArrayList;
import java.util.Random;

import exceptions.InventoryFullException;
import exceptions.NotFoundException;
import exceptions.StatMaxedOutException;

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
    	}
    	else {
    		throw new InventoryFullException("Inventory full!");
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
    		throw new InventoryFullException("Inventory full!");
    	}
    }


    /**
     * Remove the given monster from the inventory.
     * @param monster the given monster
     * @throws NotFoundException if the monster was not found in the inventory
     */
    public void remove(Monster monster) throws NotFoundException
    {
    	if (list.contains(monster)) {    		
    		list.remove(monster);
    	}
    	else {
    		throw new NotFoundException("Not found in inventory!");
    	}
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
    		newList.add(monster);
    	}
    	setList(newList);
    	levelUpOnDay();
    }
	
    
    /**
     * Level up monsters based on the number of day and the max level of monsters
     */
    public void levelUpOnDay() {
    	int maxLevel = random().getMaxLevel();
    	int dayToLevelUp = game.getNumDays()/maxLevel;
    	int timesToLevelUp = 0;
    	
    	//If monster needs to level up every day, timesToLevelUp is reduced by one to ensure that monsters
    	//are not leveling at on day 1 
    	if(dayToLevelUp == 1) {
    		timesToLevelUp -= 1;
    	}
    	//If max level is greater than number of days, monster will level up as if dayToLevelUp is 1 
    	//Sets timesToLevelUp like the above if statement would 
    	else if(dayToLevelUp == 0) {
    		dayToLevelUp += 1;
    		timesToLevelUp -= 1;
    	}
    	
    	//Calculate and adds to the final timesToLevelUp 
    	timesToLevelUp += game.getDay()/dayToLevelUp;
    	
    	if(game.getDay() % dayToLevelUp == 0){
    		for(Monster monster: list) {
    			for(int i = 0; i < timesToLevelUp; i++) {
    				try {
    					monster.levelUp();
    				}
    				catch(StatMaxedOutException e) {}
    			}
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

	
    /**
    * @param name the given name
    * @return whether the inventory contains a monster with the given name. Not case sensitive.
    */
    public boolean contains(String name) {
	   	name = name.toLowerCase();
	   	boolean hasMonster = false;
	   	for (Monster monster: getList()) {
	   		if (monster.getName().toLowerCase().equals(name)) {
	   			hasMonster = true;
	   		}
	   	}
	   	return hasMonster;
    }
 
 
   /**
    * Get the index of the first occurrence of the monster with the given name in the inventory or null if not found.
    * @param name the given name
    * @return the index of selectedMonster
    */
    public Monster find(String name) {
	   	Monster selectedMonster = null;
	   	for (Monster monster : getList()) {
	   		if (monster.getName().equals(name)) {
	   			selectedMonster = monster;
	   		}
	   	}
  		return selectedMonster;
    }
	
}
