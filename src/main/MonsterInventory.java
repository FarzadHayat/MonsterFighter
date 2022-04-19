package main;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

public class MonsterInventory {
	
	/**
	 * Fields
	 * 
	 */
	private int inventorySize = 4;
    private ArrayList<Monster> monsterList;
    private GameEnvironment game;
    
    
    /**
     * Constructors
     * 
     */
    public MonsterInventory (GameEnvironment game) {
    	monsterList = new ArrayList<Monster>(inventorySize);
    	this.game = game;
    };
    
    
    public MonsterInventory (GameEnvironment game, ArrayList<Monster> monsterList) {
    	this.game = game;
    	this.monsterList = monsterList;
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
    
    
    public ArrayList<Monster> getMonsterList() {
    	return monsterList;
    }

    
    public void setMonsterList(ArrayList<Monster> monsterList) {
    	this.monsterList = monsterList;
    }
    
    
    /**
     * Functional
     * 
     */
    
    /**
     * Add the given monster to the inventory
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
     * Remove the given monster from the inventory
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
    
    
    /**
     * @return whether the inventory is full
     */
    public boolean isFull() {
		return monsterList.size() >= inventorySize;
    }
    
    
    /**
     * @return whether all monsters have fainted
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
    
    
    /**
     * Returns a random non-fainted monster from the inventory
     * @return the randomly selected non-fainted monster
     */
    public Monster random() {
    	Random random = new Random();
    	boolean monsterFound = false;
    	Monster selectedMonster = null;
    	
    	while (!monsterFound) {
    		int index = random.nextInt(inventorySize);
    		selectedMonster = monsterList.get(index);
    		if (!selectedMonster.getIsFainted()) {
    			monsterFound = true;
    		}
    	}
    	
		return selectedMonster;
    }
    
    /**
     * Randomises the monster inventory by selecting random monsters from all monsters in the game.
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
    	ArrayList<Monster> allMonstersList = game.getAllMonsters().getMonsterList();
    	ArrayList<Monster> newMonsterList = new ArrayList<Monster>(inventorySize);
    	for (int i = 0; i < inventorySize; i++) {
    		int index = random.nextInt(allMonstersList.size());
    		Class<? extends Monster> clazz = allMonstersList.get(index).getClass();
    		Monster randomMonster = clazz.getConstructor(GameEnvironment.class).newInstance(game);
    		newMonsterList.add(randomMonster);
    	}
    	monsterList = newMonsterList;
    }
    
    
    /**
     * Return a string representation of the inventory
     */
    public String toString() {
    	String result = "";
    	for (Monster monster : monsterList)
    	{
    		result += "\n" + monster;
    	}
    	return result;
    }

}
