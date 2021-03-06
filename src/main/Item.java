package main;

import javax.swing.ImageIcon;

import exceptions.InsufficientFundsException;
import exceptions.InvalidValueException;
import exceptions.InventoryFullException;
import exceptions.StatMaxedOutException;

/**
 * An abstract class that describes an item that can be used on a monster.
 * @author Farzad and Daniel
 */
abstract public class Item implements Purchasable {

	/**
	 * Fields
	 */
    private String name;
    private String description;
    private int cost;
    private GameEnvironment game = GameEnvironment.getInstance();
    private Player player;
    private Shop shop;
    private double refundAmount = 0.5;
    private ImageIcon sprite;
    
    
    /**
	 * Constructors
	 */
    
    /**
     * Create a new Item object.
     * Set the item's name, description, and cost with the given values.
     * @param name the given name
     * @param description the given description
     * @param cost the given cost
     */
    public Item (String name, String description, int cost) {
    	this.name = name;
    	this.description = description;
    	this.cost = cost;
    	player = game.getPlayer();
    	shop = game.getShop();
    	setSprite(new ImageIcon(Item.class.getResource(
				"/resources/%s.png".formatted(name.replaceAll(" ", "")))));
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
     * Get the value of sprite
     * @return the value of sprite
     */
    public ImageIcon getSprite() {
    	return sprite;
    }
    
    
    /**
     * Set the value of sprite
     * @param sprite the new value of sprite
     */
    public void setSprite(ImageIcon sprite) {
    	this.sprite = sprite;
    }
    
    
    /**
	 * Functional methods
	 */
    
    /**
     * Buy this item from the shop and add it to the player inventory.
     * @throws InsufficientFundsException if cost of item is more than player balance
     * @throws InvalidValueException if cost is a negative value 
     * @return the string representing what the player bought
     * @throws InventoryFullException if the player item inventory is full
     */
    public String buy() throws InsufficientFundsException, InvalidValueException, InventoryFullException {
    	if (player.getItems().isFull()) {
    		throw new InventoryFullException("Item inventory is full!");
    	}
    	else {    		
    		player.minusBalance(cost);
    		player.getItems().add(this);
    		int index = shop.getItems().indexOf(this);
    		shop.getItems().remove(this);
    		shop.getItems().add(index, game.getAllItems().random().clone());
    		return "You bought: " + name;
    	}
    }

    
    /**
     * Sell this item back to the shop for a partial refund and remove the item from the player inventory.
     * @throws InvalidValueException if (cost * refundAmount) is a negative value
     * @return the string representing what the player sold
     */
    public String sell() throws InvalidValueException {
    	player.addBalance((int) (cost * refundAmount));
    	player.getItems().remove(this);
    	return "You sold: " + name;
    }


    /**
     * Use this item on the given Monster
     * @param monster the given monster
     * @throws StatMaxedOutException if the monster is already maxed out in the stat that the item is upgrading
     */
    abstract public void use(Monster monster) throws StatMaxedOutException;

    
    /**
	 * @return the string representation of the Item object.
	 */
    public String toString() {
    	return String.format("%-20s    cost: %-3s    %-50s", name, cost, description);
    }
    
    
    /**
	 * @return a string representation of the Item object with full details and command line options.
	 */
    public String view() {
    	String result = "";
    	if (shop.getItems().contains(this)) {
    		result += String.format("\nBalance: %s\n\n", player.getBalance());
    	}
    	result += "Item: " + name + "\n";
    	result += description + "\n";
    	result += "Cost: " + cost + "\n";
    	if (player.getItems().contains(this)) {
    		result += "\n1: Use";
    		result += "\n2: Sell";
    		result += "\n3: Go back";
    	}
    	if (shop.getItems().contains(this)) {    		
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