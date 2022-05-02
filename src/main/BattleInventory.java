package main;
import java.util.ArrayList;

import exceptions.InventoryFullException;
import exceptions.NotFoundException;

public class BattleInventory {
    
	/**
	 * Fields
	 */
    protected int maxSize;
    private ArrayList<Battle> list;

    protected GameEnvironment game;
    protected Player player;
	
	
    /**
     * Constructors
     */
    
    /**
     * Create a new BattleInventory object with the given size.
     * @param maxSize the given maxSize
     * @param game the given game
     */
    public BattleInventory (int maxSize, GameEnvironment game) {
    	this.maxSize = maxSize;
    	this.game = game;
    	player = game.getPlayer();
    	list = new ArrayList<Battle>(maxSize);
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
	public ArrayList<Battle> getList() {
		return list;
	}


	/**
	 * Set the value of list
	 * @param list the new value of list
	 */
	public void setList(ArrayList<Battle> list) {
		this.list = list;
	}
    
	
	/**
	 * Functional 
	 */

	/**
     * Add the given battle to the inventory.
     * @param battle the given battle
     * @throws InventoryFullException if the inventory is already full
     */
    public void add(Battle battle) throws InventoryFullException
    {
    	if (!isFull()) {
    		list.add(battle);
    	}
    	else {
    		throw new InventoryFullException("Inventory full!");
    	}
    }
    
    
    /**
     * Add the given battle at the given index to the inventory.
     * @param battle the given battle
     * @throws InventoryFullException if the inventory is already full
     */
    public void add(int index, Battle battle) throws InventoryFullException
    {
    	if (!isFull()) {
    		list.add(index, battle);
    	}
    	else {
    		throw new InventoryFullException("Inventory full!");
    	}
    }


    /**
     * Remove the given battle from the inventory.
     * @param battle the given battle
     * @throws NotFoundException if the battle was not found in the inventory
     */
    public void remove(Battle battle) throws NotFoundException
    {
    	if (list.contains(battle)) {    		
    		list.remove(battle);
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
     * Populate the inventory by randomly generating battles.
     * Each randomly generated battle is a new battle populated with randomly selected monsters from all monsters in the game.
     */
    public void randomise() {
    	ArrayList<Battle> newList = new ArrayList<Battle>(maxSize);
    	for (int i = 0; i < maxSize; i++) {
    		Battle battle = new Battle(game);
    		newList.add(battle);
    	}
    	setList(newList);
    }
    
    
    /**
	 * @return result a string representation of the inventory object
	 */
	public String toString() {
		String result = "";
		for (Battle battle : list)
		{
			result += "\n" + battle;
		}
		return result;
	}


	/**
	 * @return result a string representation of the inventory object with command line options
	 */
	public String view() {
		String result = "\n===== BATTLES =====\n\n";
		int start = 1;
    	for (Battle battle : list) {
    		result += String.format("%s: %s\n", start, battle);
    		start++;
    	}
    	result += String.format("%s: Go back", start);
    	return result;
	}

}
