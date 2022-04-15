package main;
import java.util.ArrayList;

public class Inventory {
	
	private int myMonstersSize = 4;
	private int myItemsSize = 4;
    private ArrayList<Monster> myMonsters;
    private ArrayList<Item> myItems;
    
    
    public Inventory () {
    	myMonsters = new ArrayList<Monster>(myMonstersSize);
    	myItems = new ArrayList<Item>(myItemsSize);
    };

    
    public void setMyMonsters(ArrayList<Monster> monsterList) {
    	myMonsters = monsterList;
    }
    
    
    public void setMyItem(ArrayList<Item> itemList) {
    	myItems = itemList;
    }
    
    
    public ArrayList<Monster> getMyMonsters() {
    	return myMonsters;
    }
    
    
    public ArrayList<Item> getMyItems() {
    	return myItems;
    }
    
    
    /**
     * @param monster
     * @throws InventoryFullException 
     */
    public void addMonster(Monster monster) throws InventoryFullException
    {
    	if (!monstersFull()) {
    		myMonsters.add(monster);
    	}
    	else {
    		throw new InventoryFullException("Inventory full!");
    	}
    }


    /**
     * @param monster
     * @throws PurchasableNotFoundException 
     */
    public void removeMonster(Monster monster) throws PurchasableNotFoundException
    {
    	if (myMonsters.contains(monster)) {    		
    		myMonsters.remove(monster);
    	}
    	else {
    		throw new PurchasableNotFoundException("Purchasable not found in inventory!");
    	}
    }


    /**
     * @param item
     * @throws InventoryFullException 
     */
    public void addItem(Item item) throws InventoryFullException
    {
    	if (!itemsFull()) {
    		myItems.add(item);
    	}
    	else {
    		throw new InventoryFullException("Inventory full!");
    	}
    }


    /**
     * @param item
     * @throws PurchasableNotFoundException 
     */
    public void removeItem(Item item) throws PurchasableNotFoundException
    {
    	if (myItems.contains(item)) {    		
    		myItems.remove(item);
    	}
    	else {
    		throw new PurchasableNotFoundException("Purchasable not found in inventory!");
    	}
    }
    
    
    public boolean monstersFull() {
		return myMonsters.size() >= myMonstersSize;
    }
    
    
    public boolean itemsFull() {
		return myItems.size() >= myItemsSize;
    }

}
