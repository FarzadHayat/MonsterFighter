package main;

abstract public class Item implements Storable {

	/**
	 * Fields
	 * 
	 */
    private String name;
    private String description;
    private int cost;
    protected GameEnvironment game;
    private double refundAmount = 0.5;
    
    
    /**
	 * Constructors
	 * 
	 */
    
    public Item (GameEnvironment game) {
    	this.game = game;
    }
    
    
    public Item (String name, String description, int cost, GameEnvironment game) {
    	this.name = name;
    	this.description = description;
    	this.cost = cost;
    	this.game = game;
    };
    
    
    /**
	 * Getters and Setters methods 
	 * 
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
	 * 
	 */

    
    /**
     * buy this item from the shop and add it to the player inventory
     * @throws InsufficientFundsException cost of item is more than player balance error
     * @throws InventoryFullException inventory is full error
     * @throws PurchasableNotFoundException 
     * @throws InvalidValueException 
     */
    public String buy() throws InsufficientFundsException, InventoryFullException, PurchasableNotFoundException, InvalidValueException
    {
    	game.minusBalance(cost);
		game.getMyItems().add(this);
		int index = game.getShop().getItems().indexOf(this);
		game.getShop().getItems().remove(this);
		game.getShop().getItems().add(index, game.getAllItems().random());
		return "You bought: " + name;
    }


    /**
     * sell this item back to the shop for a partial refund and remove it from the player inventory
     * @throws PurchasableNotFoundException item was not found in the player inventory error
     * @throws InvalidValueException 
     */
    public String sell() throws PurchasableNotFoundException, InvalidValueException
    {
    	game.addBalance(cost * refundAmount);
    	game.getMyItems().remove(this);
    	return "You sold: " + name;
    }


    /**
     * use this item on the given Monster
     * @param monster
     * @throws PurchasableNotFoundException 
     * @throws StatMaxedOutException 
     */
    abstract public void use(Monster monster) throws PurchasableNotFoundException, StatMaxedOutException;

    
    /**
	 * @return
	 */
    public String toString() {
    	return String.format("%s (%s cost: %s)", name, description, cost);
    }
    
    
    /**
	 * @return
	 */
    public String view() {
    	String result = "";
    	if (game.getShop().getItems().contains(this)) {
    		result += String.format("\nBalance: %s\n", game.getBalance());
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
     * @return item
     */
    public abstract Item clone();
    
}
