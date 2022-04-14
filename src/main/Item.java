package main;

abstract public class Item implements Purchasable {

    private String name;
    private int cost;
    private String description;    public Item () { };
    
    //
    // Methods
    //


    /**
     * Set the value of name
     * @param newVar the new value of name
     */
    public void setName (String newVar) {
        name = newVar;
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
     * @param newVar the new value of cost
     */
    public void setCost (int newVar) {
        cost = newVar;
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
     * @param newVar the new value of description
     */
    public void setDescription (String newVar) {
        description = newVar;
    }

    /**
     * Get the value of description
     * @return the value of description
     */
    public String getDescription () {
        return description;
    }

    //
    // Other methods
    //

    /**
     * @param        item
     */
    public void buy(Item item)
    {
    }


    /**
     * @param        item
     */
    public void sell(Item item)
    {
    }


    /**
     * @param        monster
     */
    abstract public void use(Monster monster);


    /**
     * Buy a purchasable from the shop and add it to the player inventory.
     * @param        purchasable
     */
    abstract public void buy(Purchasable purchasable);


    /**
     * Sell a purchasable in the player inventory to the shop.
     * @param        purchasable
     */
    abstract public void sell(Purchasable purchasable);


}
