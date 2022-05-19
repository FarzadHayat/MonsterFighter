package main;
import java.util.ArrayList;
import java.util.Random;

/**
 * Holds an array of items with additional functionality.
 * @author Farzad and Daniel
 */
@SuppressWarnings("serial")
public class ItemInventory extends ArrayList<Item> {
    
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
     * Create a new ItemInventory object with the given size.
     * @param maxSize the given maxSize
     * @param game the given game
     */
    public ItemInventory (int maxSize, GameEnvironment game) {
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
	 * Get a random non fainted item from the inventory.
	 * @return the randomly selected item
	 */
	public Item random() {
		Random random = new Random();
		int index = random.nextInt(size());
		Item item = get(index);
		return item;
	}
	
	
    /**
     * Populate the inventory by randomly selecting items from all items in the game.
     */
    public void randomise() {
    	while (!this.isEmpty()) {
    		remove(this.get(0));
    	}
    	Random random = new Random();
    	for (int i = 0; i < maxSize; i++) {
    		int index = random.nextInt(game.getAllItems().size());
    		Item item = game.getAllItems().get(index).clone();
    		add(item);
    	}
    }
    
    
    /**
	 * @return result a string representation of the inventory object
	 */
	public String toString() {
		String result = "";
		for (Item item : this)
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
    	for (Item item : this) {
    		result += String.format("%s: %s\n", start, item);
    		start++;
    	}
    	return result;
	}

}