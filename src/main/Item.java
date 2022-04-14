package main;

abstract public class Item implements Purchasable {

    private String name;
    private int cost;
    private String description;
    private GameEnvironment game;
    private double refundAmount = 0.5;
    
    public Item (String name, int cost, String description, GameEnvironment game) {
    	this.name = name;
    	this.cost = cost;
    	this.description = description;
    	this.game = game;
    };
    
    /**
	 * Getters and Setters methods 
	 * 
	 */
    
    /**
     * Set the value of name
     * @param name the new value of name
     */
    public void setName (String name) {
        this.name = name;
    }

    /**
     * Get the value of name
     * @return the value of name
     */
    public String getName () {
        return name;
    }

    /**
     * Set the value of cost
     * @param cost the new value of cost
     */
    public void setCost (int cost) {
        this.cost = cost;
    }

    /**
     * Get the value of cost
     * @return the value of cost
     */
    public int getCost () {
        return cost;
    }

    /**
     * Set the value of description
     * @param description the new value of description
     */
    public void setDescription (String description) {
        this.description = description;
    }

    /**
     * Get the value of description
     * @return the value of description
     */
    public String getDescription () {
        return description;
    }

    /**
	 * Functional methods
	 * 
	 */

    /**
     * @throws InsufficientFundsException 
     * @throws InventoryFullException 
     */
    public void buy() throws InsufficientFundsException, InventoryFullException
    {
    	game.minusBalance(cost);
		game.getInventory().addItem(this);
    }


    /**
     * @param item
     * @throws PurchasableNotFoundException 
     */
    public void sell(Item item) throws PurchasableNotFoundException
    {
    	game.addBalance(cost * refundAmount);
    	game.getInventory().removeItem(this);
    }


    /**
     * @param monster
     */
    abstract public void use(Monster monster);


    /**
     * Buy a purchasable from the shop and add it to the player inventory.
     * @param purchasable
     */
    abstract public void buy(Purchasable purchasable);


    /**
     * Sell a purchasable in the player inventory to the shop.
     * @param purchasable
     */
    abstract public void sell(Purchasable purchasable);


}
