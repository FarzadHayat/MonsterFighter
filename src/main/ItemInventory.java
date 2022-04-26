package main;
import java.util.ArrayList;
import java.util.Random;

public class ItemInventory extends Inventory {
    
    /**
     * Constructors
     */
    
    /**
     * Create a new ItemInventory object with the given size.
     * @param maxSize the given maxSize
     * @param game the given game
     */
	public ItemInventory (int maxSize, GameEnvironment game) {
    	super(maxSize, game);
    	setList(new ArrayList<Storable>(maxSize));
    };
    
    
    /**
     * Functional 
     */
    
    /**
     * Populate the inventory by randomly selecting items from all items in the game.
     */
    public void randomise() {
    	Random random = new Random();
    	ArrayList<Storable> newList = new ArrayList<Storable>(maxSize);
    	for (int i = 0; i < maxSize; i++) {
    		int index = random.nextInt(game.getAllItems().size());
    		Item item = (Item) game.getAllItems().get(index).clone();
    		newList.add(item);
    	}
    	setList(newList);
    }

}