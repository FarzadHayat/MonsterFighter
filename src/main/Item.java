package main;

abstract public class Item implements Storable {

	/**
	 * Fields
	 */
    private String name;
    private String description;
    private int cost;
    protected GameEnvironment game;
    private double refundAmount = 0.5;
    
    
    /**
	 * Constructors
	 */
    
    /**
     * Create a new Item object.
     * Set the value of game to the given GameEnvironment object.
     * @param game the given GameEnvironment object.
     */
    public Item (GameEnvironment game) {
    	this.game = game;
    }
    
    
    /**
     * Create a new Item object.
     * Set the value of game to the given GameEnvironment object.
     * Set the item's name, description, and cost with the given values.
     * @param name the given name
     * @param description the given description
     * @param cost the given cost
     * @param game the given GameEnvironment object.
     */
    public Item (String name, String description, int cost, GameEnvironment game) {
    	this.name = name;
    	this.description = description;
    	this.cost = cost;
    	this.game = game;
    };
    
    
    /**
	 * Getters and Setters methods 
	 */
    
    /**
     * Get the value of name
     * @return the value of name
     */
    public String getName () {
    	return name;
    }
    
    
    /**
     * Set the value of name
     * @param name the new value of name
     */
    public void setName (String name) {
        this.name = name;
    }
    
    
    /**
     * Get the value of description
     * @return the value of description
     */
    public String getDescription () {
    	return description;
    }
    
    
    /**
     * Set the value of description
     * @param description the new value of description
     */
    public void setDescription (String description) {
    	this.description = description;
    }
    
    
    /**
     * Get the value of cost
     * @return the value of cost
     */
    public int getCost () {
    	return cost;
    }

    
    /**
     * Set the value of cost
     * @param cost the new value of cost
     */
    public void setCost (int cost) {
        this.cost = cost;
    }
    
    
    /**
     * Get the value of refundAmount
     * @return the value of refundAmount
     */
    public double getRefundAmount () {
    	return refundAmount;
    }

    
	/**
     * Set the value of refundAmount
     * @param refundAmount the new value of refundAmount
     */
    public void setRefundAmount (double refundAmount) {
        this.refundAmount = refundAmount;
    }
    
    
    /**
	 * Functional methods
	 */
    
    /**
     * Buy this item from the shop and add it to the player inventory.
     * @throws InsufficientFundsException if cost of item is more than player balance
     * @throws InventoryFullException if the player inventory is full
     * @throws StorableNotFoundException if item was not found in the shop
     * @throws InvalidValueException if cost is a negative value 
     */
    public String buy() throws InsufficientFundsException, InventoryFullException, StorableNotFoundException, InvalidValueException
    {
    	game.minusBalance(cost);
		game.getMyItems().add(this);
		int index = game.getShop().getItems().indexOf(this);
		game.getShop().getItems().remove(this);
		game.getShop().getItems().add(index, game.getAllItems().random());
		return "You bought: " + name;
    }

    
    /**
     * Sell this item back to the shop for a partial refund and remove the item from the player inventory.
     * @throws StorableNotFoundException if item was not found in the player inventory
     * @throws InvalidValueException if (cost * refundAmount) is a negative value
     */
    public String sell() throws StorableNotFoundException, InvalidValueException
    {
    	game.addBalance(cost * refundAmount);
    	game.getMyItems().remove(this);
    	return "You sold: " + name;
    }


    /**
     * Use this item on the given Monster
     * @param monster the given monster
     * @throws StorableNotFoundException if the given monster was not found in the player inventory or the item was not found in the shop
     * @throws StatMaxedOutException if the monster is already maxed out in the stat that the item is upgrading
     */
    abstract public void use(Monster monster) throws StorableNotFoundException, StatMaxedOutException;

    
    /**
	 * @return the string representation of the Item object.
	 */
    public String toString() {
    	return String.format("%s (%s cost: %s)", name, description, cost);
    }
    
    
    /**
	 * @return a string representation of the Item object with full details and command line options.
	 */
    public String view() {
    	String result = "";
    	if (game.getShop().getItems().contains(this)) {
    		result += String.format("\nBalance: %s\n\n", game.getBalance());
    	}
    	result += "Item: " + name + "\n";
    	result += description + "\n";
    	result += "Cost: " + cost + "\n";
    	if (game.getMyItems().contains(this)) {
    		result += "\n1: Use";
    		result += "\n2: Sell";
    		result += "\n3: Go back";
    	}
    	if (game.getShop().getItems().contains(this)) {    		
    		result += "\n1: Buy";
    		result += "\n2: Go back";
    	}
    	return result;
    }
    
    
    /**
     * Get a new instance of the Item class.
     * @return item the new Item object.
     */
    public abstract Item clone();
    
}