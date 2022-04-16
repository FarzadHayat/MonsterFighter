package main;
import java.util.ArrayList;

public class MonsterInventory {
	
	private int inventorySize = 4;
    private ArrayList<Monster> monsterList;
    
    
    public MonsterInventory () {
    	monsterList = new ArrayList<Monster>(inventorySize);
    };
    
    
    public ArrayList<Monster> getMonsterList() {
    	return monsterList;
    }

    
    public void setMonsterList(ArrayList<Monster> monsterList) {
    	this.monsterList = monsterList;
    }
    
    
    /**
     * @param monster
     * @throws InventoryFullException 
     */
    public void add(Monster monster) throws InventoryFullException
    {
    	if (!isFull()) {
    		monsterList.add(monster);
    	}
    	else {
    		throw new InventoryFullException("Monster inventory is full!");
    	}
    }


    /**
     * @param monster
     * @throws PurchasableNotFoundException 
     */
    public void remove(Monster monster) throws PurchasableNotFoundException
    {
    	if (monsterList.contains(monster)) {    		
    		monsterList.remove(monster);
    	}
    	else {
    		throw new PurchasableNotFoundException("Monster not found in inventory!");
    	}
    }
    
    
    public boolean isFull() {
		return monsterList.size() >= inventorySize;
    }
    
    
    /**
     * checks whether all monsters have fainted.
     * @return whether the monsters are all fainted
     */
    public boolean allFainted() {
    	boolean fainted = true;
    	for (Monster monster : monsterList) {
    		if (!monster.getIsFainted()) {
    			fainted = false;
    		}
    	}
    	return fainted;
    }

}
