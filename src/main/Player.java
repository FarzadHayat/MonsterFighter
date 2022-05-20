package main;

import exceptions.InsufficientFundsException;
import exceptions.InvalidValueException;

/**
 * Holds information about the player and their inventories.
 * @author Farzad and Daniel
 */
public class Player {

	/**
	 * Fields
	 */
	private int balance;
    private String name;
	
	private MonsterInventory monsters;
    private ItemInventory items;
	
    
    /**
     * Constructors
     */
    
    /**
     * Create a new Player object.
     * @param game the given game
     */
    public Player(GameEnvironment game) {
    	this.monsters = new MonsterInventory(4, game);
    	this.items = new ItemInventory(4, game);
    }
    
    
    /**
     * Getters and setters
     */
    
    /**
     * Get the value of balance
     * @return the value of balance
     */
    public int getBalance () {
        return balance;
    }
    
    
    /**
     * Set the value of balance
     * @param balance the new value of balance
     * @throws InvalidValueException if the value is negative
     */
    public void setBalance (int balance) throws InvalidValueException {
    	if (0 > balance) {
    		throw new InvalidValueException("Balance cannot be a negative value!");
    	}
    	else {
    		this.balance = balance;    		
    	}
    }

    
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
     * @throws InvalidValueException if the name is not valid
     */
    public void setName (String name) throws InvalidValueException {
    	name = name.strip();
    	String regex = "(([a-zA-Z])*(\\s)*)*([a-zA-Z])+";
    	if (3 <= name.length() && name.length() <= 15 && name.matches(regex)) {
    		this.name = name;
    	}
    	else {    		
    		throw new InvalidValueException("Invalid player name! Try again:");
    	}
    }
    
    
    /**
     * Get the value of monsters
     * @return the value of monsters
     */
    public MonsterInventory getMonsters () {
        return monsters;
    }
    
    
    /**
     * Set the value of monsters
     * @param monsters the new value of monsters
     */
    public void setMonsters (MonsterInventory monsters) {
    	this.monsters = monsters;
    }

    
    /**
     * Get the value of items
     * @return the value of items
     */
    public ItemInventory getItems () {
        return items;
    }
    
    
    /**
     * Set the value of items
     * @param items the new value of items
     */
    public void setItems (ItemInventory items) {
    	this.items = items;
    }
    
    
    /**
     * Functional
     */
    
    /**
     * Add the given amount to balance.
     * @param amount to be added to balance
     * @throws InvalidValueException if amount is a negative value
     */
    public void addBalance(int amount) throws InvalidValueException {
    	if (0 > amount) {
    		throw new InvalidValueException("Cannot be a negative value!");
    	}
    	else {    		
    		setBalance(getBalance() + amount);
    	}
    }
    
    
    /**
     * Subtract the given amount from balance.
     * @param amount to be subtracted from balance.
     * @throws InsufficientFundsException if balance is less than the amount to be subtracted
     * @throws InvalidValueException if amount is a negative value
     */
    public void minusBalance(int amount) throws InsufficientFundsException, InvalidValueException {
    	if (0 > amount) {
    		throw new InvalidValueException("Cannot be a negative value!");
    	}
		if (balanceSufficient(amount)) {
			setBalance(getBalance() - amount);
    	}
    	else {
    		throw new InsufficientFundsException("Insufficient funds!");
    	}
    }
    
    
    /**
     * Returns whether balance is sufficient to afford the given amount.
     * @param amount the given amount
     * @return whether balance is bigger or equal to amount
     * @throws InvalidValueException if amount is negative value
     */
    public boolean balanceSufficient(int amount) throws InvalidValueException {
    	if (0 > amount) {
    		throw new InvalidValueException("Cannot be a negative value!");
    	}
    	else {    		
    		return getBalance() >= amount;
    	}
    }
    
    
    /**
     * Returns a string containing a header and a numbered list of the player monsters.
     * @return result the result
     */
    public String viewMonsters() {
    	String result = "\n===== MY TEAM =====\n\n";
    	result += monsters.view();
    	result += String.format("\n%s: Go back", monsters.size() + 1);
    	return result;
    }
    
    
    /**
     * Returns a string containing a header and a numbered list of the player items.
     * @return result the result
     */
    public String viewItems() {
    	String result = "\n===== MY INVENTORY =====\n\n";
    	result += items.view();
    	result += String.format("\n%s: Go back", items.size() + 1);
    	return result;
    }
    
}
