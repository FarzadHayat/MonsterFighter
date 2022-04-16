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
    
    
    public ArrayList<Monster> getMyMonsters() {
    	return myMonsters;
    }

    
    public void setMyMonsters(ArrayList<Monster> monsterList) {
    	myMonsters = monsterList;
    }
    
    
    public ArrayList<Item> getMyItems() {
    	return myItems;
    }
    
    
    public void setMyItem(ArrayList<Item> itemList) {
    	myItems = itemList;
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
    		throw new InventoryFullException("Monster inventory is full!");
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
    		throw new PurchasableNotFoundException("Monster not found in inventory!");
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
    		throw new InventoryFullException("Item inventory is full!");
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
    		throw new PurchasableNotFoundException("Item not found in inventory!");
    	}
    }
    
    
    public boolean monstersFull() {
		return myMonsters.size() >= myMonstersSize;
    }
    
    
    public boolean itemsFull() {
		return myItems.size() >= myItemsSize;
    }
    
    
    /**
     * checks whether all monsters have fainted.
     * @return whether the monsters are all fainted
     */
    public boolean allMonstersFainted() {
    	boolean fainted = true;
    	for (Monster monster : myMonsters) {
    		if (!monster.getIsFainted()) {
    			fainted = false;
    		}
    	}
    	return fainted;
    }

}
