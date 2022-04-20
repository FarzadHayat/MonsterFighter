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
    private ArrayList<Item> list;
    private GameEnvironment game;
    
    
    /**
     * Constructors
     * 
     */
    public ItemInventory (GameEnvironment game) {
    	list = new ArrayList<Item>(inventorySize);
    	this.game = game;
    };
    

    
    public ItemInventory (GameEnvironment game, ArrayList<Item> itemList) {
    	this.game = game;
    	this.list = itemList;
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
    
    
    public ArrayList<Item> getList() {
    	return list;
    }
    
    
    public void setList(ArrayList<Item> list) {
    	this.list = list;
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
    		list.add(item);
    	}
    	else {
    		throw new InventoryFullException("Item inventory is full!");
    	}
    }
    
    
    /**
     * @param item
     * @throws InventoryFullException 
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
     * @param item
     * @throws PurchasableNotFoundException 
     */
    public void remove(Item item) throws PurchasableNotFoundException
    {
    	if (contains(item)) {    		
    		list.remove(item);
    	}
    	else {
    		throw new PurchasableNotFoundException("Item not found in inventory!");
    	}
    }
    
    
	/**
	 * @return
	 */
	public int size() {
		return list.size();
	}
	
	
	/**
	 * @param index
	 * @return
	 */
	public Item get(int index) {
		return list.get(index);
	}
    
    
	/**
	 * @return
	 */
    public boolean isFull() {
		return list.size() >= inventorySize;
    }
    
    
    /**
     * Returns a random item from the inventory
     * @return the randomly selected item
     */
    public Item random() {
    	Random random = new Random();
    	int index = random.nextInt(list.size());
    	Item selectedItem = list.get(index);
		return selectedItem;
    }
    
    /**
     * Randomises the item inventory by selecting random items from the all items in the game.
     * @throws InventoryFullException
     */
    public void randomiseInventory() throws InventoryFullException {
    	Random random = new Random();
    	ArrayList<Item> allItemsList = game.getAllItems().getList();
    	ArrayList<Item> newItemList = new ArrayList<Item>(inventorySize);
    	for (int i = 0; i < inventorySize; i++) {
    		int index = random.nextInt(allItemsList.size());
    		Class<? extends Item> clazz = allItemsList.get(index).getClass();
			try {
				Item randomItem = clazz.getConstructor(GameEnvironment.class).newInstance(game);
				newItemList.add(randomItem);
			}
			catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
    	}
    	list = newItemList;
    }
    
    
    /**
     * Return a string representation of the inventory
     * @return
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
	 * @param item
	 * @return
	 */
	public boolean contains(Item item) {
		return list.contains(item);
	}
	
    
    /**
     * Return whether the inventory contains an item with the given itemName.
     * @param itemName
     * @return whether the inventory contains the item
     */
    public boolean contains(String itemName) {
    	boolean hasItem = false;
    	for (Item item: getList()) {
    		if (item.getName().equals(itemName)) {
    			hasItem = true;
    		}
    	}
    	return hasItem;
    }
    
    
    /**
     * Return the first occurence of the item with the given itemName.
     * @param itemName
     * @return the item
     */
    public Item find(String itemName) {
    	Item selectedItem = null;
    	for (Item item : getList()) {
    		if (item.getName().equals(itemName)) {
    			selectedItem = item;
    		}
    	}
		return selectedItem;
    }
    
    
    /**
     * @param item
     * @return
     */
	public int indexOf(Item item) {
		return list.indexOf(item);
	}
	
	
	public String view() {
		String result = "\n===== MY ITEMS =====\n\n";
		int start = 1;
    	for (Item item : list) {
    		result += String.format("%s: %s\n", start, item);
    		start++;
    	}
    	result += String.format("\n%s: Go back", start);
    	return result;
	}

}
