package main;
import java.util.ArrayList;
import java.util.Random;

public class Inventory<T extends Storable> {
	
	/**
	 * Fields
	 * 
	 */
	private int maxSize;
    private ArrayList<T> list;
    /**
     * Constructors
     * 
     */
    
    
    public Inventory (int maxSize, GameEnvironment game) {
    	this.maxSize = maxSize;
    	list = new ArrayList<T>(maxSize);
    };
    
    
    /**
     * Getters and setters
     * 
     */
    public int getMaxSize() {
    	return maxSize;
    }
    
    
    public void setMaxSize(int maxSize) {
    	this.maxSize = maxSize;
    }
    
    
    public ArrayList<T> getList() {
    	return list;
    }
    
    
    public void setList(ArrayList<T> list) {
    	this.list = list;
    }
    
    
    /**
     * Functional
     * 
     */
    
    /**
     * @param t
     * @throws InventoryFullException 
     */
    public void add(T t) throws InventoryFullException
    {
    	if (!full()) {
    		list.add(t);
    	}
    	else {
    		throw new InventoryFullException("Inventory full!");
    	}
    }
    
    
    /**
     * @param t
     * @throws InventoryFullException 
     */
    public void add(int index, T t) throws InventoryFullException
    {
    	if (!full()) {
    		list.add(index, t);
    	}
    	else {
    		throw new InventoryFullException("Inventory full!");
    	}
    }


    /**
     * @param t
     * @throws PurchasableNotFoundException 
     */
    public void remove(T t) throws PurchasableNotFoundException
    {
    	if (contains(t)) {    		
    		list.remove(t);
    	}
    	else {
    		throw new PurchasableNotFoundException("Not found in inventory!");
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
	public T get(int index) {
		return list.get(index);
	}
    
    
	/**
	 * @return
	 */
    public boolean full() {
		return list.size() >= maxSize;
    }
    
    
    /**
     * @return whether all monsters in an inventory have fainted
     */
    public static boolean allFainted(Inventory<Monster> inventory) {
    	boolean fainted = true;
    	for (Monster monster : inventory.getList()) {
    		if (!monster.getIsFainted()) {
    			fainted = false;
    		}
    	}
    	return fainted;
    }
    
    
    /**
     * Heal all monsters in the inventory.
     */
    public static void healAll(Inventory<Monster> inventory) {
    	for (Monster monster : inventory.getList()) {
    		monster.heal();
    	}
    }
    
    
    /**
     * Returns a random t from the inventory
     * @return the randomly selected t
     */
    public T random() {
    	Random random = new Random();
    	int index = random.nextInt(list.size());
    	T t = list.get(index);
		return t;
    }
    
    
    /**
     * Randomises the monster inventory by selecting random monsters from all monster in the game.
     * @throws InventoryFullException
     */
    public static void randomiseMonsters(Inventory<Monster> inventory, Inventory<Monster> allMonsters) throws InventoryFullException {
    	Random random = new Random();
    	ArrayList<Monster> newList = new ArrayList<Monster>(inventory.getMaxSize());
    	for (int i = 0; i < inventory.getMaxSize(); i++) {
    		int index = random.nextInt(allMonsters.size());
    		Monster monster = allMonsters.get(index).clone();
    		newList.add(monster);
    	}
    	inventory.setList(newList);
    }
    
    
    /**
     * Randomises the monster inventory by selecting random monsters from all monster in the game.
     * @throws InventoryFullException
     */
    public static void randomiseItems(Inventory<Item> inventory, Inventory<Item> allItems) throws InventoryFullException {
    	Random random = new Random();
    	ArrayList<Item> newList = new ArrayList<Item>(inventory.getMaxSize());
    	for (int i = 0; i < inventory.getMaxSize(); i++) {
    		int index = random.nextInt(allItems.size());
    		Item item = allItems.get(index).clone();
    		newList.add(item);
    	}
    	inventory.setList(newList);
    }
    
	
    /**
     * Randomise the battles in list.
     * @throws InventoryFullException 
     */
    public static void randomiseBattles(Inventory<Battle> inventory, GameEnvironment game) throws InventoryFullException {
    	ArrayList<Battle> battleList = new ArrayList<Battle>(inventory.getMaxSize());
    	for (int i = 0; i < inventory.getMaxSize(); i++) {
    		Inventory<Monster> monsterInventory = new Inventory<Monster>(4, game);
    		Inventory.randomiseMonsters(monsterInventory, game.getAllMonsters());
    		Battle battle = new Battle(game, monsterInventory);
    		battleList.add(battle);
    	}
    	inventory.setList(battleList);
    }
    
    
    /**
     * Return a string representation of the inventory
     * @return
     */
    public String toString() {
    	String result = "";
    	for (T t : list)
    	{
    		result += "\n" + t;
    	}
    	return result;
    }
    
    
    /**
	 * @param t
	 * @return
	 */
	public boolean contains(T t) {
		return list.contains(t);
	}
	
    
    /**
     * Return whether the inventory contains an t with the given tName.
     * @param tName
     * @return whether the inventory contains the t
     */
    public boolean contains(String tName) {
    	boolean hasT = false;
    	for (T t: getList()) {
    		if (t.getName().equals(tName)) {
    			hasT = true;
    		}
    	}
    	return hasT;
    }
    
    
    /**
     * Return the first occurence of the t with the given tName.
     * @param name
     * @return the t
     */
    public T find(String name) {
    	T selectedT = null;
    	for (T t : getList()) {
    		if (t.getName().equals(name)) {
    			selectedT = t;
    		}
    	}
		return selectedT;
    }
    
    
    /**
     * @param t
     * @return
     */
	public int indexOf(T t) {
		return list.indexOf(t);
	}
	
	
	public String view() {
		String result = "";
		int start = 1;
    	for (T t : list) {
    		result += String.format("%s: %s\n", start, t);
    		start++;
    	}
    	return result;
	}

}
