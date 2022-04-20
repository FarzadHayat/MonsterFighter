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
     * Find the given purchasable name in the shop and buy it
     * @param rest the name of the purchasable
     * @throws PurchasableNotFoundException 
     * @throws InventoryFullException 
     * @throws InsufficientFundsException 
     */
    public void buy(String rest) throws PurchasableNotFoundException, InsufficientFundsException, InventoryFullException {
		if (monsters.contains(rest)) {
			Monster monster = monsters.find(rest);
			monster.buy();
			monsters.remove(monster);
			System.out.println("You bought: " + monster.getName());
		}
		else {
			if (items.contains(rest)) {
				Item item = items.find(rest);
				item.buy();
				items.remove(item);
				System.out.println("You bought: " + item.getName());
			}
			else {
				throw new PurchasableNotFoundException("Purchasable not found in shop!");
			}
		}
    }
    
	/**
	 * Find the given purchasable name in the player inventory and sell it
	 * @param rest
	 * @throws PurchasableNotFoundException 
	 */
    public void sell(String rest) throws PurchasableNotFoundException {  		
		if (game.getMyMonsters().contains(rest)) {
			Monster monster = game.getMyMonsters().find(rest); 
			monster.sell();
			System.out.println("You sold: " + monster.getName());
		}
		else {
			if (game.getMyItems().contains(rest)) {
				Item item = game.getMyItems().find(rest); 
				item.sell();
				System.out.println("You sold: " + item.getName());
			}
			else {
				throw new PurchasableNotFoundException("Purchasable not found in inventory!");
			}
		}
    }
	
	
    /**
     * Randomise the purchasables in the shop.
     * @throws InventoryFullException 
     */
    public void randomise() throws InventoryFullException {
		monsters.randomiseInventory();
    	items.randomiseInventory();
    }
}
