package main;

public class Shop {

    private MonsterInventory monsters;
    private ItemInventory items;
    private GameEnvironment game;
    
    
    public Shop(GameEnvironment game) throws InventoryFullException {
    	monsters = new MonsterInventory(game);
    	items = new ItemInventory(game);
    	this.game = game;
    	randomise();
    }
    
	/**
	 * @return the monsters
	 */
	public MonsterInventory getMonsters() {
		return monsters;
	}


	/**
	 * @param monsters the monsters to set
	 */
	public void setMonsters(MonsterInventory monsters) {
		this.monsters = monsters;
	}
	
	
	/**
	 * @return the items
	 */
	public ItemInventory getItems() {
		return items;
	}


	/**
	 * @param items the items to set
	 */
	public void setItems(ItemInventory items) {
		this.items = items;
	}
	
	
    /**
     * Randomise the purchasables in the shop.
     * @throws InventoryFullException 
     */
    public void randomise() throws InventoryFullException {
		monsters.randomiseInventory();
    	items.randomiseInventory();
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
