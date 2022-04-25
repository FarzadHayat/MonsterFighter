package main;

public class Shop {

	/**
	 * Fields
	 */
    private Inventory<Monster> monsters;
    private Inventory<Item> items;
    private GameEnvironment game;
    
    
    /**
     * Constructors
     */
    
    /**
     * Create a new Shop object.
     * Set the value of game to the given GameEnvironment object.
     * @param game the given GameEnvironment object
     */
    public Shop(GameEnvironment game) {
    	monsters = new Inventory<Monster> (4);
    	items = new Inventory<Item>(4);
    	this.game = game;
    }
    
    /**
     * Getters and setters
     */
    
	/**
	 * get the value of monsters
	 * @return the value of monsters
	 */
	public Inventory<Monster> getMonsters() {
		return monsters;
	}


	/**
	 * Set the value of monsters
	 * @param monsters the new value of monsters
	 */
	public void setMonsters(Inventory<Monster> monsters) {
		this.monsters = monsters;
	}
	
	
	/**
	 * Get the value of items
	 * @return the value of items
	 */
	public Inventory<Item> getItems() {
		return items;
	}


	/**
	 * Set the value of items
	 * @param items the new value of items
	 */
	public void setItems(Inventory<Item> items) {
		this.items = items;
	}
	
	/**
	 * Functional
	 */
	
    /**
     * Randomize the monsters and items in the shop.
     */
    public void randomise() {
		Inventory.randomiseMonsters(monsters, game.getAllMonsters());
    	Inventory.randomiseItems(items, game.getAllItems());
    }
    
    
    /**
     * @return result the string representation of the Shop object with by command line options
     */
    public String toString() {
    	String result = String.format("\nBalance: %s\n", game.getBalance());
    	int start = 1;
    	result += "\n===== MONSTERS =====\n\n";
    	for (Monster monster : monsters.getList()) {
    		result += String.format("%s: %s\n", start, monster);
    		start++;
    	}
    	result += "\n===== ITEMS =====\n\n";
    	for (Item item : items.getList()) {
    		result += String.format("%s: %s\n", start, item);
    		start++;
    	}
    	result += String.format("\n%s: Go back", start);
    	return result;
    }
    
}
