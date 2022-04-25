package main;
import java.util.ArrayList;
import java.util.Random;

public class Inventory<T extends Storable> {
	
	/**
	 * Fields
	 */
	private int maxSize;
    private ArrayList<T> list;
    
    
    /**
     * Constructors
     */
    
    /**
     * Create a new Inventory object with the given size.
     * Set the value of maxSize to the given maxSize.
     * @param maxSize the given maxSize
     */
    public Inventory (int maxSize) {
    	this.maxSize = maxSize;
    	list = new ArrayList<T>(maxSize);
    };
    
    
    /**
     * Getters and setters
     */
    
    /**
     * get the value of maxSize
     * @return the value of maxSize
     */
    public int getMaxSize() {
    	return maxSize;
    }
    
    
    /**
     * Set the value of maxSize
     * @param maxSize the new value of maxSize
     */
    public void setMaxSize(int maxSize) {
    	this.maxSize = maxSize;
    }
    
    
    /**
     * get the value of list
     * @return the value of list
     */
    public ArrayList<T> getList() {
    	return list;
    }
    
    
    /**
     * Set the value of list
     * @param list the new value of list
     */
    public void setList(ArrayList<T> list) {
    	this.list = list;
    }
    
    
    /**
     * Functional 
     */
    
    /**
     * Add the given t to the inventory.
     * @param t the given t
     * @throws InventoryFullException if the inventory is already full
     */
    public void add(T t) throws InventoryFullException
    {
    	if (!isFull()) {
    		list.add(t);
    	}
    	else {
    		throw new InventoryFullException("Inventory full!");
    	}
    }
    
    
    /**
     * Add the given t at the given index to the inventory.
     * @param t the given t
     * @throws InventoryFullException if the inventory is already full
     */
    public void add(int index, T t) throws InventoryFullException
    {
    	if (!isFull()) {
    		list.add(index, t);
    	}
    	else {
    		throw new InventoryFullException("Inventory full!");
    	}
    }


    /**
     * Remove the given t from the inventory.
     * @param t the given t
     * @throws PurchasableNotFoundException if the t was not found in the inventory
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
	 * @return size the size of the inventory
	 */
	public int size() {
		return list.size();
	}
	
	
	/**
	 * @param index the index of t
	 * @return t the t at the given index in the inventory
	 */
	public T get(int index) {
		return list.get(index);
	}
    
    
	/**
	 * @return if the inventory is full or not
	 */
    public boolean isFull() {
		return list.size() >= maxSize;
    }
    
    
    /**
     * @param inventory the given monster inventory
     * @return whether all monsters in the given inventory have fainted
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
     * @param inventory the given monster inventory
     * Heal all monsters in the given inventory.
     */
    public static void healAll(Inventory<Monster> inventory) {
    	for (Monster monster : inventory.getList()) {
    		monster.heal();
    	}
    }
    
    
    /**
     * Returns a random t from the inventory
     * @return t the randomly selected t
     */
    public T random() {
    	Random random = new Random();
    	int index = random.nextInt(list.size());
    	T t = list.get(index);
		return t;
    }
    
    
    /**
     * Populate the given monster inventory by randomly selecting monsters from the given all monsters.
     * @param inventory the given monster inventory
     * @param allItems all monsters in the game
     */
    public static void randomiseMonsters(Inventory<Monster> inventory, Inventory<Monster> allMonsters) {
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
     * Populate the given item inventory by randomly selecting monsters from the given all items.
     * @param inventory the given item inventory
     * @param allItems all items in the game
     */
    public static void randomiseItems(Inventory<Item> inventory, Inventory<Item> allItems) {
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
     * populate the given battle inventory by randomly generating battles.
     * Each randomly generated battle is a new battle populated with randomly selected monsters from all monsters in the game.
     * @param inventory the given battle inventory
     * @param game the given GameEnviroment object
     */
    public static void randomiseBattles(Inventory<Battle> inventory, GameEnvironment game) {
    	ArrayList<Battle> battleList = new ArrayList<Battle>(inventory.getMaxSize());
    	for (int i = 0; i < inventory.getMaxSize(); i++) {
    		Inventory<Monster> monsterInventory = new Inventory<Monster>(4);
    		Inventory.randomiseMonsters(monsterInventory, game.getAllMonsters());
    		Battle battle = new Battle(game, monsterInventory);
    		battleList.add(battle);
    	}
    	inventory.setList(battleList);
    }
    
    
    /**
     * @return result a string representation of the inventory object
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
	 * @param t the given t
	 * @return whether the inventory contains the given t
	 */
	public boolean contains(T t) {
		return list.contains(t);
	}
	
    
    /**
     * @param name the given name
     * @return whether the inventory contains a t with the given name. Not case sensitive.
     */
    public boolean contains(String name) {
    	name = name.toLowerCase();
    	boolean hasT = false;
    	for (T t: getList()) {
    		if (t.getName().toLowerCase().equals(name)) {
    			hasT = true;
    		}
    	}
    	return hasT;
    }
    
    
    /**
     * Get the index of the first occurrence of the t with the given name in the inventory or null if not found.
     * @param name the given name
     * @return the index of selectedT
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
     * Get the index of the given t in the inventory or -1 if not found.
     * @param t the given t
     * @return the index of t
     */
	public int indexOf(T t) {
		return list.indexOf(t);
	}
	
	
	/**
	 * @return result a string representation of the inventory object with command line options
	 */
	public String view() {
		String result = "";
		int start = 1;
    	for (T t : list) {
    		result += String.format("%s: %s\n", start, t);
    		start++;
    	}
    	return result;
	}

	
	/**
	 * @return whether the inventory is empty
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
}
