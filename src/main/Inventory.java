package main;
import java.util.ArrayList;
import java.util.Random;

public abstract class Inventory {
	
	/**
	 * Fields
	 */
	protected int maxSize;
    protected ArrayList<Storable> list;
    protected GameEnvironment game;
    
    
    /**
     * Constructors
     */
    
    /**
     * Create a new Inventory object with the given size.
     * Set the value of maxSize to the given maxSize.
     * Set the value of game to the given game.
     * @param maxSize the given maxSize
     * @param game the given game
     */
    public Inventory (int maxSize, GameEnvironment game) {
    	this.maxSize = maxSize;
    	this.game = game;
    };
    
    
//    public Inventory (int maxSize, GameEnvironment game, ArrayList<Storable> list) {
//    	this.maxSize = maxSize;
//    	this.game = game;
//    	this.list = list;
//    };
    
    
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
    public ArrayList<Storable> getList() {
    	return list;
    }
    
    
    /**
     * Set the value of list
     * @param list the new value of list
     */
	public void setList(ArrayList<Storable> list) {
    	this.list = (ArrayList<Storable>) list;
    }
    
    
    /**
     * Functional 
     */
    
    /**
     * Add the given storable to the inventory.
     * @param storable the given storable
     * @throws InventoryFullException if the inventory is already full
     */
    public void add(Storable storable) throws InventoryFullException
    {
    	if (!isFull()) {
    		list.add(storable);
    	}
    	else {
    		throw new InventoryFullException("Inventory full!");
    	}
    }
    
    
    /**
     * Add the given storable at the given index to the inventory.
     * @param storable the given storable
     * @throws InventoryFullException if the inventory is already full
     */
    public void add(int index, Storable storable) throws InventoryFullException
    {
    	if (!isFull()) {
    		list.add(index, storable);
    	}
    	else {
    		throw new InventoryFullException("Inventory full!");
    	}
    }


    /**
     * Remove the given storable from the inventory.
     * @param storable the given storable
     * @throws StorableNotFoundException if the storable was not found in the inventory
     */
    public void remove(Storable storable) throws StorableNotFoundException
    {
    	if (contains(storable)) {    		
    		list.remove(storable);
    	}
    	else {
    		throw new StorableNotFoundException("Not found in inventory!");
    	}
    }
    
    
	/**
	 * @return size the size of the inventory
	 */
	public int size() {
		return list.size();
	}
	
	
	/**
	 * @param index the index of storable
	 * @return storable the storable at the given index in the inventory
	 */
	public Storable get(int index) {
		return list.get(index);
	}
    
    
	/**
	 * @return if the inventory is full or not
	 */
    public boolean isFull() {
		return list.size() >= maxSize;
    }
    
    
    /**
     * Returns a random storable from the inventory
     * @return storable the randomly selected storable
     */
    public Storable random() {
    	Random random = new Random();
    	int index = random.nextInt(list.size());
    	Storable storable = list.get(index);
		return storable;
    }
    
    
    /**
     * @return result a string representation of the inventory object
     */
    public String toString() {
    	String result = "";
    	for (Storable storable : list)
    	{
    		result += "\n" + storable;
    	}
    	return result;
    }
    
    
    /**
	 * @param storable the given storable
	 * @return whether the inventory contains the given storable
	 */
	public boolean contains(Storable storable) {
		return list.contains(storable);
	}
	
    
    /**
     * @param name the given name
     * @return whether the inventory contains a storable with the given name. Not case sensitive.
     */
    public boolean contains(String name) {
    	name = name.toLowerCase();
    	boolean hasStorable = false;
    	for (Storable storable: getList()) {
    		if (storable.getName().toLowerCase().equals(name)) {
    			hasStorable = true;
    		}
    	}
    	return hasStorable;
    }
    
    
    /**
     * Get the index of the first occurrence of the storable with the given name in the inventory or null if not found.
     * @param name the given name
     * @return the index of selectedStorable
     */
    public Storable find(String name) {
    	Storable selectedStorable = null;
    	for (Storable storable : getList()) {
    		if (storable.getName().equals(name)) {
    			selectedStorable = storable;
    		}
    	}
		return selectedStorable;
    }
    
    
    /**
     * Get the index of the given storable in the inventory or -1 if not found.
     * @param storable the given storable
     * @return the index of storable
     */
	public int indexOf(Storable storable) {
		return list.indexOf(storable);
	}
	
	
	/**
	 * @return result a string representation of the inventory object with command line options
	 */
	public String view() {
		String result = "";
		int start = 1;
    	for (Storable storable : list) {
    		result += String.format("%s: %s\n", start, storable);
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
