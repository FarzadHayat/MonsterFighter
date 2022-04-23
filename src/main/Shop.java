package main;

public class Shop {

    private Inventory<Monster> monsters;
    private Inventory<Item> items;
    private GameEnvironment game;
    
    
    public Shop(GameEnvironment game) throws InventoryFullException {
    	monsters = new Inventory<Monster> (4, game);
    	items = new Inventory<Item>(4, game);
    	this.game = game;
    	randomise();
    }
    
	/**
	 * @return the monsters
	 */
	public Inventory<Monster> getMonsters() {
		return monsters;
	}


	/**
	 * @param monsters the monsters to set
	 */
	public void setMonsters(Inventory<Monster> monsters) {
		this.monsters = monsters;
	}
	
	
	/**
	 * @return the items
	 */
	public Inventory<Item> getItems() {
		return items;
	}


	/**
	 * @param items the items to set
	 */
	public void setItems(Inventory<Item> items) {
		this.items = items;
	}
	
	
    /**
     * Randomise the purchasables in the shop.
     * @throws InventoryFullException 
     */
    public void randomise() throws InventoryFullException {
		Inventory.randomiseMonsters(monsters, game.getAllMonsters());
    	Inventory.randomiseItems(items, game.getAllItems());
    }
    
    
    /**
     * 
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
