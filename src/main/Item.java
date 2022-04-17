package main;

abstract public class Item implements Purchasable {

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
	 * Functional methods
	 * 
	 */

    
    /**
     * buy this item from the shop and add it to the player inventory
     * @throws InsufficientFundsException cost of item is more than player balance error
     * @throws InventoryFullException inventory is full error
     */
    public void buy() throws InsufficientFundsException, InventoryFullException
    {
    	game.minusBalance(cost);
		game.getMyItems().add(this);
    }


    /**
     * sell this item back to the shop for a partial refund and remove it from the player inventory
     * @throws PurchasableNotFoundException item was not found in the player inventory error
     */
    public void sell() throws PurchasableNotFoundException
    {
    	game.addBalance(cost * refundAmount);
    	game.getMyItems().remove(this);
    }


    /**
     * use this item on the given Monster
     * @param monster
     * @throws PurchasableNotFoundException 
     * @throws StatMaxedOutException 
     */
    abstract public void use(Monster monster) throws PurchasableNotFoundException, StatMaxedOutException;


}
