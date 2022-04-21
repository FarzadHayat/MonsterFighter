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
    private ArrayList<Monster> list;
    private GameEnvironment game;
    
    
    /**
     * Constructors
     * 
     */
    public MonsterInventory (GameEnvironment game) {
    	list = new ArrayList<Monster>(inventorySize);
    	this.game = game;
    };
    
    
    public MonsterInventory (GameEnvironment game, ArrayList<Monster> list) {
    	this.game = game;
    	this.list = list;
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
    
    
    public ArrayList<Monster> getList() {
    	return list;
    }

    
    public void setList(ArrayList<Monster> list) {
    	this.list = list;
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
    		list.add(monster);
    	}
    	else {
    		throw new InventoryFullException("Monster inventory is full!");
    	}
    }
    
    
    /**
     * Add the given monster to the inventory
     * @param monster
     * @throws InventoryFullException 
     */
    public void add(int index, Monster monster) throws InventoryFullException
    {
    	if (!isFull()) {
    		list.add(index, monster);
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
    	if (contains(monster)) {    		
    		list.remove(monster);
    	}
    	else {
    		throw new PurchasableNotFoundException("Monster not found in inventory!");
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
	public Monster get(int index) {
		return list.get(index);
	}
    
    
    /**
     * @return whether the inventory is full
     */
    public boolean isFull() {
		return list.size() >= inventorySize;
    }
    
    
    /**
     * @return whether all monsters have fainted
     */
    public boolean allFainted() {
    	boolean fainted = true;
    	for (Monster monster : list) {
    		if (!monster.getIsFainted()) {
    			fainted = false;
    		}
    	}
    	return fainted;
    }
    
    
    /**
     * Heals all monsters in the inventory once.
     */
    public void healAll() {
    	for (Monster monster : getList()) {
    		monster.heal();
    	}
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
    		int index = random.nextInt(list.size());
    		selectedMonster = list.get(index);
    		if (!selectedMonster.getIsFainted()) {
    			monsterFound = true;
    		}
    	}
    	
		return selectedMonster;
    }
    
    /**
     * Randomises the monster inventory by selecting random monsters from all monsters in the game.
     * @throws InventoryFullException
     */
    public void randomise() throws InventoryFullException {
    	Random random = new Random();
    	ArrayList<Monster> allMonstersList = game.getAllMonsters().getList();
    	ArrayList<Monster> newMonsterList = new ArrayList<Monster>(inventorySize);
    	for (int i = 0; i < inventorySize; i++) {
    		int index = random.nextInt(allMonstersList.size());
    		Class<? extends Monster> clazz = allMonstersList.get(index).getClass();
			try {
				Monster randomMonster = clazz.getConstructor(GameEnvironment.class).newInstance(game);
				newMonsterList.add(randomMonster);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
    	}
    	list = newMonsterList;
    }
    
    
    /**
     * Return a string representation of the inventory
     * @param start 
     * @return 
     */
    public String toString() {
    	String result = "";
    	for (Monster monster : list)
    	{
    		result += "\n" + monster;
    	}
    	return result;
    }
    
    
    /**
	 * @param monster
	 * @return
	 */
	public boolean contains(Monster monster) {
		return list.contains(monster);
	}
    
    
    /**
     * Return whether the inventory contains a monster with the given monsterName.
     * @param monsterName
     * @return whether the inventory contains the monster
     */
    public boolean contains(String monsterName) {
    	boolean hasMonster = false;
    	for (Monster monster : getList()) {
    		if (monster.getName().equals(monsterName)) {
    			hasMonster = true;
    		}
    	}
    	return hasMonster;
    }
    
    
    /**
     * Return the first occurence of the monster with the given monsterName.
     * @param monsterName
     * @return the monster
     */
    public Monster find(String monsterName) {
    	Monster selectedMonster = null;
    	for (Monster monster : getList()) {
    		if (monster.getName().equals(monsterName)) {
    			selectedMonster = monster;
    		}
    	}
		return selectedMonster;
    }


    /**
     * @param monster
     * @return
     */
	public int indexOf(Monster monster) {
		return list.indexOf(monster);
	}


	public String view() {
		String result = "\n===== MY MONSTERS =====\n\n";
		int start = 1;
    	for (Monster monster : list) {
    		result += String.format("%s: %s\n", start, monster);
    		start++;
    	}
    	result += String.format("\n%s: Go back", start);
    	return result;
	}
}
