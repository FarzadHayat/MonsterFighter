package main;
import java.util.ArrayList;
import java.util.Random;

import exceptions.InventoryFullException;

public class ItemInventory {
    
	/**
	 * Fields
	 */
    protected int maxSize;
    private ArrayList<Item> list;

    protected GameEnvironment game;
    protected Player player;
	
	
    /**
     * Constructors
     */
    
    /**
     * Create a new ItemInventory object with the given size.
     * @param maxSize the given maxSize
     * @param game the given game
     */
    public ItemInventory (int maxSize, GameEnvironment game) {
    	this.maxSize = maxSize;
    	this.game = game;
    	player = game.getPlayer();
    	list = new ArrayList<Item>(maxSize);
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
	public ArrayList<Item> getList() {
		return list;
	}


	/**
	 * Set the value of list
	 * @param list the new value of list
	 */
	public void setList(ArrayList<Item> list) {
		this.list = list;
	}
	
	
	/**
	 * Functional 
	 */

	/**
     * Add the given item to the inventory.
     * @param item the given item
     * @throws InventoryFullException if the inventory is already full
     */
    public void add(Item item) throws InventoryFullException
    {
    	if (!isFull()) {
    		list.add(item);
    	}
    	else {
    		throw new InventoryFullException("Item inventory is full!");
    	}
    }
    
    
    /**
     * Add the given item at the given index to the inventory.
     * @param item the given item
     * @throws InventoryFullException if the inventory is already full
     */
    public void add(int index, Item item) throws InventoryFullException
    {
    	if (!isFull()) {
    		list.add(index, item);
    	}
    	else {
    		throw new InventoryFullException("Item inventory is full!");
    	}
    }


    /**
     * Remove the given item from the inventory.
     * @param item the given item
     */
    public void remove(Item item)
    {
    	list.remove(item);
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
	 * Get a random non fainted item from the inventory.
	 * @return the randomly selected item
	 */
	public Item random() {
		Random random = new Random();
		int index = random.nextInt(list.size());
		Item item = list.get(index);
		return item;
	}
	
	
    /**
     * Populate the inventory by randomly selecting items from all items in the game.
     */
    public void randomise() {
    	Random random = new Random();
    	ArrayList<Item> newList = new ArrayList<Item>(maxSize);
    	for (int i = 0; i < maxSize; i++) {
    		int index = random.nextInt(game.getAllItems().getList().size());
    		Item item = game.getAllItems().getList().get(index).clone();
    		newList.add(item);
    	}
    	setList(newList);
    }
    
    
    /**
	 * @return result a string representation of the inventory object
	 */
	public String toString() {
		String result = "";
		for (Item item : list)
		{
			result += "\n" + item;
		}
		return result;
	}


	/**
	 * @return result a string representation of the inventory object with command line options
	 */
	public String view() {
		String result = "";
		int start = 1;
    	for (Item item : list) {
    		result += String.format("%s: %s\n", start, item);
    		start++;
    	}
    	return result;
	}

}