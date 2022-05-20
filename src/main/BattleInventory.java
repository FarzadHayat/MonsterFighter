package main;
import java.util.ArrayList;

/**
 * Holds an array of battles with additional functionality.
 * @author Farzad and Daniel
 */
@SuppressWarnings("serial")
public class BattleInventory extends ArrayList<Battle> {
    
	/**
	 * Fields
	 */
    protected int maxSize;
	
    /**
     * Constructors
     */
    
    /**
     * Create a new BattleInventory object with the given size.
     * @param maxSize the given maxSize
     */
    public BattleInventory (int maxSize) {
    	this.maxSize = maxSize;
    	GameEnvironment.getInstance();
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
     * Populate the inventory by randomly generating battles.
     * Each randomly generated battle is a new battle populated with randomly selected monsters from all monsters in the game.
     */
    public void randomise() {
    	while (!this.isEmpty()) {
    		remove(this.get(0));
    	}
    	for (int i = 0; i < maxSize; i++) {
    		Battle battle = new Battle();
    		add(battle);
    	}
    }
    
    
    /**
	 * @return result a string representation of the inventory object
	 */
	public String toString() {
		String result = "";
		for (Battle battle : this)
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
    	for (Battle battle : this) {
    		result += String.format("%s: %s\n", start, battle);
    		start++;
    	}
    	result += String.format("%s: Go back", start);
    	return result;
	}

}
