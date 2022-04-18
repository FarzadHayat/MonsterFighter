package main;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

public class ItemInventory {
	
	/**
	 * Fields
	 * 
	 */
	private int inventorySize = 4;
    private ArrayList<Item> itemList;
    private GameEnvironment game;
    
    
    /**
     * Constructors
     * 
     */
    public ItemInventory (GameEnvironment game) {
    	itemList = new ArrayList<Item>(inventorySize);
    	this.game = game;
    };
    

    
    public ItemInventory (GameEnvironment game, ArrayList<Item> itemList) {
    	this.game = game;
    	this.itemList = itemList;
    };
    
    
    /**
     * Getters and setters
     * 
     */
    public int getInventorySize() {
    	return inventorySize;
    }
    
    
    public void setInventorySize(int inventorySize) {
    	this.inventorySize = inventorySize;
    }
    
    
    public ArrayList<Item> getItemList() {
    	return itemList;
    }
    
    
    public void setItemList(ArrayList<Item> itemList) {
    	this.itemList = itemList;
    }
    
    
    /**
     * Functional
     * 
     */
    
    /**
     * @param item
     * @throws InventoryFullException 
     */
    public void add(Item item) throws InventoryFullException
    {
    	if (!isFull()) {
    		itemList.add(item);
    	}
    	else {
    		throw new InventoryFullException("Item inventory is full!");
    	}
    }


    /**
     * @param item
     * @throws PurchasableNotFoundException 
     */
    public void remove(Item item) throws PurchasableNotFoundException
    {
    	if (itemList.contains(item)) {    		
    		itemList.remove(item);
    	}
    	else {
    		throw new PurchasableNotFoundException("Item not found in inventory!");
    	}
    }
    
    
    public boolean isFull() {
		return itemList.size() >= inventorySize;
    }
    
    
    /**
     * Returns a random item from the inventory
     * @return the randomly selected item
     */
    public Item random() {
    	Random random = new Random();
    	int index = random.nextInt(inventorySize);
    	Item selectedItem = itemList.get(index);
		return selectedItem;
    }
    
    /**
     * Randomises the item inventory by selecting random items from the all items in the game.
     * @throws InventoryFullException
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */
    public void randomiseInventory() throws InventoryFullException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
    	Random random = new Random();
    	ArrayList<Item> newItemList = new ArrayList<Item>(inventorySize);
    	for (int i = 0; i < inventorySize; i++) {
    		int index = random.nextInt(game.getItemClasses().size());
    		Class<? extends Item> clazz = game.getItemClasses().get(index);
    		Item randomItem = clazz.getConstructor(GameEnvironment.class).newInstance(game);
    		newItemList.add(randomItem);
    	}
    	itemList = newItemList;
    }
    
    
    public String toString() {
    	String result = "";
    	for (Item item : itemList)
    	{
    		result += "\n" + item;
    	}
    	return result;
    }

}
