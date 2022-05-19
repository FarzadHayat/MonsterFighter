package main;

/**
 * Holds the shop monsters and items.
 * @author Farzad and Daniel
 */
public class Shop {

	/**
	 * Fields
	 */
    private MonsterInventory monsters;
    private ItemInventory items;
    private Player player;
    
    
    /**
     * Constructors
     */
    
    /**
     * Create a new Shop object.
     * @param game the given GameEnvironment object
     */
    public Shop(GameEnvironment game) {
    	monsters = new MonsterInventory (4, game);
    	items = new ItemInventory(4, game);
    	player = game.getPlayer();
    }
    
    
    /**
     * Getters and setters
     */
    
	/**
	 * get the value of monsters
	 * @return the value of monsters
	 */
	public MonsterInventory getMonsters() {
		return monsters;
	}


	/**
	 * Set the value of monsters
	 * @param monsters the new value of monsters
	 */
	public void setMonsters(MonsterInventory monsters) {
		this.monsters = monsters;
	}
	
	
	/**
	 * Get the value of items
	 * @return the value of items
	 */
	public ItemInventory getItems() {
		return items;
	}


	/**
	 * Set the value of items
	 * @param items the new value of items
	 */
	public void setItems(ItemInventory items) {
		this.items = items;
	}
	
	
	/**
	 * Functional
	 */
	
    /**
     * Randomize the monsters and items in the shop.
     */
    public void randomise() {
		monsters.randomise();
    	items.randomise();;
    }
    
    
    /**
     * @return result the string representation of the Shop object with command line options.
     */
    public String toString() {
    	String result = String.format("\nBalance: %s\n", player.getBalance());
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
