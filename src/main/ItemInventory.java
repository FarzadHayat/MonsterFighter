package main;
import java.util.ArrayList;

public class ItemInventory {
	
	private int inventorySize = 4;
    private ArrayList<Item> itemList;
    
    
    public ItemInventory () {
    	itemList = new ArrayList<Item>(inventorySize);
    };
    
    
    public ArrayList<Item> getItemList() {
    	return itemList;
    }
    
    
    public void setItemList(ArrayList<Item> itemList) {
    	this.itemList = itemList;
    }
    
    
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

}
