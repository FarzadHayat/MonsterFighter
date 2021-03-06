package main;

import java.util.*;

import javax.swing.ImageIcon;

import exceptions.InsufficientFundsException;
import exceptions.InvalidTargetException;
import exceptions.InvalidValueException;
import exceptions.InventoryFullException;
import exceptions.StatMaxedOutException;
import monsters.Raka;

/**
 * An abstract class that describes a monster.
 * @author Farzad and Daniel
 */
public abstract class Monster implements Purchasable {
	
	/**
	 * Fields
	 */
 	private String name;
	private String description;
	
	private int health;
	private int maxHealth;
	private int damage;
	private int cost;
	private int level;
	private int maxLevel = 4;
	private int healAmount;
	
	private double critRate;
	private double maxCritRate = 1;
	private double critMultiplier = 2;
	private double refundAmount = 0.5;
	
	private boolean isFainted = false;

	private ImageIcon sprite;
	
	private GameEnvironment game = GameEnvironment.getInstance();
	private Player player;
	private Shop shop;
	
	private Random rn = new Random();
	
	
	/**
	 * Constructors
	 */
	
	/**
	 * Creates a new Monster object
	 * Sets the monster's name, description, maxHealth, damage, cost, level, healAmount and critRate with the given values 
	 * Sets the monster's initial health to the maxHealth 
	 * Sets the monster's maximum level based on the difficulty of the GameEnvironment object 
	 * @param name given String for the monster's name 
	 * @param description given String for the monster's description
	 * @param maxHealth given int for the monster's maximum health
	 * @param damage given int for the monster's damage
	 * @param cost given int for the monster's cost
	 * @param level given int for the monster's current level 
	 * @param healAmount given int for the monster's heal amount overnight
	 * @param critRate given double for the monster's critical rate 
	 */
	public Monster(String name, String description, int maxHealth, int damage, int cost, int level, int healAmount, double critRate) {
		this.name = name;
		this.description = description;
		this.maxHealth = maxHealth;
		this.health = maxHealth;
		this.damage = damage;
		this.cost = cost;
		this.level = level;
		changeMaxLevel(game.getDifficulty());
		this.healAmount = healAmount;
		this.critRate = critRate;
		player = game.getPlayer();
		shop = game.getShop();
		setSprite(new ImageIcon(Monster.class.getResource(
				"/resources/%s.png".formatted(name.replaceAll(" ", "")))));
	}
	
	
	/**
	 * Getters and Setters 
	 */
	
	/**
	 * Checks if given string passes the criteria and sets the name of the monster 
	 * @param name the new value of name 
	 * @throws InvalidValueException if the name is not valid
	 */
	public void setName(String name) throws InvalidValueException {
		name = name.strip();
    	String regex = "(([a-zA-Z])*(\\s)*)*([a-zA-Z])+";
    	if (3 <= name.length() && name.length() <= 15 && name.matches(regex)) {
    		this.name = name;
    	}
    	else {    		
    		throw new InvalidValueException("Invalid monster name! Try again:");
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
     * Set the value of health 
     * @param health the new value of health
     */
    public void setHealth (int health) {
    	if(health >= maxHealth) {
    		this.health = maxHealth;
    	}
    	else {
            this.health = health;
    	}
    }

    
    /**
     * Get the value of health
     * @return the value of health
     */
    public int getHealth () {
        return health;
    }

    
    /**
     * Set the value of maxHealth
     * @param maxHealth the new value of maxHealth
     */
    public void setMaxHealth (int maxHealth) {
        this.maxHealth = maxHealth;
    }

    
    /**
     * Get the value of maxHealth
     * @return the value of maxHealth
     */
    public int getMaxHealth () {
        return maxHealth;
    }

    
    /**
     * Set the value of healAmount
     * @param healAmount the new value of healAmount
     */
    public void setHealAmount (int healAmount) {
        this.healAmount = healAmount;
    }
    

    /**
     * Get the value of healAmount
     * @return the value of healAmount
     */
    public int getHealAmount () {
        return healAmount;
    }
    

    /**
     * Set the value of damage
     * @param damage the new value of damage
     */
    public void setDamage (int damage) {
        this.damage = damage;
    }
    

    /**
     * Get the value of damage
     * @return the value of damage
     */
    public int getDamage () {
        return damage;
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
     * Get the value of maxLevel
	 * @return the value of maxLevel
	 */
	public int getMaxLevel() {
		return maxLevel;
	}

	
	/**
	 * Set the value of maxLevel
	 * @param maxLevel the new value of maxLevel
	 */
	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}

	
	/**
     * Set the value of level
     * @param level the new value of level
     */
    public void setLevel (int level) {
        this.level = level;
    }

    
    /**
     * Get the value of level
     * @return the value of level
     */
    public int getLevel () {
        return level;
    }
    

    /**
     * Set the value of critRate
     * @param critRate the new value of critRate
     */
    public void setCritRate (double critRate) {
        this.critRate = critRate;
    }
    

    /**
     * Get the value of critRate
     * @return the value of critRate
     */
    public double getCritRate () {
        return critRate;
    }
    
    
    /**
     * Get the value of maxCritRate
	 * @return the value of maxCritRate
	 */
	public double getMaxCritRate() {
		return maxCritRate;
	}
	

	/**
	 * Set the value of maxCritRate
	 * @param maxCritRate the new value of maxCritRate
	 */
	public void setMaxCritRate(double maxCritRate) {
		this.maxCritRate = maxCritRate;
	}
	

	/**
	 * Get the value of critMultiplier
	 * @return the value of critMultiplier
	 */
	public double getCritMultiplier() {
		return critMultiplier;
	}

	
	/**
	 * Set the value of critMultiplier 
	 * @param critMultiplier the new value of critMultiplier
	 */
	public void setCritMultiplier(double critMultiplier) {
		this.critMultiplier = critMultiplier;
	}
	

	/**
     * Set the value of isFainted
     * @param isFainted the new value of isFainted
     */
    public void setIsFainted (boolean isFainted) {
        this.isFainted = isFainted;
    }
    

    /**
     * Get the value of isFainted
     * @return the value of isFainted
     */
    public boolean getIsFainted () {
        return isFainted;
    }
    

	/**
     * Set the value of refundAmount
     * @param refundAmount the new value of refundAmount
     */
    public void setRefundAmount (double refundAmount) {
        this.refundAmount = refundAmount;
    }
    

    /**
     * Get the value of refundAmount
     * @return the value of refundAmount
     */
    public double getRefundAmount () {
        return refundAmount;
    }
    
    
    /**
     * Get the value of rn
     * @return the value of rn 
     */
    public Random getRandom() {
    	return rn;
    }
    
    
    /**
     * Set the value of rn
     * @param rn the new value of rn
     */
    public void setRandom(Random rn) {
    	this.rn = rn;
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
	 * Functional 
	 */

	/**
	 * Sets the maximum level of the monster to a value based on the difficulty given 
	 * @param difficulty given Difficulty value 
	 */
	public void changeMaxLevel(Difficulty difficulty) {
		switch(difficulty) {
		case EASY:
			setMaxLevel(4);
			break;
		case NORMAL:
			setMaxLevel(6);
			break;
		case HARD:
			setMaxLevel(8);
			break;
		}
	}
	

    /**
     * If monster is fainted, set the new value of isFainted to false 
     * Increase the monster health by the healAmount value 
     */
	public void heal() {
	    if(getIsFainted()) {
		setIsFainted(false);
	    }
	    health += healAmount;
	    if(this.getHealth() > this.getMaxHealth()) {
		this.setHealth(maxHealth);
	    }
	}
	
	
    /**
     * If monster is fainted, set the new value of isFainted to false 
     * Increase the monster health by the amount value 
     * @param amount the heal amount
     * @throws InvalidValueException if given amount value is 0 or negative
     */
	public void heal(int amount) throws InvalidValueException {
		if(amount <= 0) {
			throw new InvalidValueException("Invalid value!");
		}
		else {
		    if(getIsFainted()) {
		    	setIsFainted(false);
		    }
		    health += amount;
		    if(this.getHealth() > this.getMaxHealth()) {
		    	this.setHealth(maxHealth);
		    }
		}
	}
	
	
	/**
	 * Deal damage to the given Monster object 
	 * Damage to deal is calulated in finalDamage method
     * @param other given Monster object 
	 * @throws InvalidValueException if given Monster object is fainted 
	 * @throws InvalidTargetException if the target is fainted
	 * @return damageDealt the final damage dealt to the given Monster object
     */
	public int attack(Monster other) throws InvalidValueException, InvalidTargetException {
	    int damageDealt = finalDamage();
	    if(other.getIsFainted()) {
	    	throw new InvalidTargetException("Invalid target!");
	    }
	    else {
			other.takeDamage(damageDealt);
			return damageDealt;
	    }
		
	}
	
	
	/**
	 * Reduce monster's health based on given value 
     * @param damageReceived given int value to reduce from monster's health
     * @throws InvalidValueException if given value is less than 0 
     */
	public void takeDamage(int damageReceived) throws InvalidValueException {
		if(damageReceived < 0) {
			throw new InvalidValueException("Invalid damage value!");
		}
		else {
			health -= damageReceived;
			if(this.getHealth() <= 0) {
				this.setHealth(0);
				this.setIsFainted(true);
			}
		}
	}
	
	
	/**
	 * Calculate totalDamage to deal based on the critical rate and critical multiplier
	 * @return the totalDamage after taking into account the critical rate 
	 */
	public int finalDamage() {
		double chanceValue = (double)(rn.nextInt(10)+1) / 10;
		int totalDamage = getDamage();
		if(chanceValue <= getCritRate()) {
			//Monster deals a critical hit for 2 times its original damage 
			totalDamage *= getCritMultiplier();
		}
		return totalDamage;
	}
	
	
    /**
     * Buy a monster from the shop and add it to the player inventory.
     * @throws InsufficientFundsException if cost of monster is more than player balance
     * @throws InvalidValueException if value of balance to minus is invalid
     * @return the string representing what the player bought
     * @throws InventoryFullException if the player monster inventory is full
     */
	public String buy() throws InsufficientFundsException, InvalidValueException, InventoryFullException {
		if (player.getMonsters().isFull()) {
			throw new InventoryFullException("Monster inventory is full!");
		}
		else {		
			player.minusBalance(cost);
			player.getMonsters().add(this);
			int index = shop.getMonsters().indexOf(this);		
			shop.getMonsters().remove(this);
			Monster monster = game.getAllMonsters().random().clone();
			shop.getMonsters().add(index, monster);
			if(monster instanceof Raka) {
    			((Raka) monster).setTeam(player.getMonsters());
    		}
			return "You bought: " + name;
		}
	}
	
	
    /**
     * Sell monster back to the shop for a partial refund and removes the monster from the player's inventory
     * @throws InvalidValueException if value of balance to add is invalid
     * @return the string representing what the player sold
     */
	public String sell() throws InvalidValueException {
		player.addBalance((int) (cost * refundAmount));
		player.getMonsters().remove(this);
		return "You sold: " + name;
	}
	
	
	/**
     * Level up the monster by 1 level
	 * @throws StatMaxedOutException if monster's level value is the same as maxLevel value 
     */
	public void levelUp( ) throws StatMaxedOutException {
		setIsFainted(false);
		if (level == maxLevel) {
			throw new StatMaxedOutException("Monster is already max level!");
		}
		else {
			level += 1;
		}
	};
	
	
	/**
	 * @return the string representation of the Monster object
	 */
	public String toString() {
		return String.format("%-14s    health: %-3s    max health: %-3s    damage: %-3s    cost: %-3s    "
						+ "level: %-1s    max level: %-1s    heal amount: %-3s    crit rate: %-3s%%    fainted: %-5s",
				name, health, maxHealth, damage, cost, level, maxLevel, healAmount, (int) (critRate * 100), isFainted);
	}
	
	
	/**
	 * @return result the string representation of the Monster object followed by command line options
	 */
    public String view() {
    	String result = "";
    	if (shop.getMonsters().contains(this)) {
    		result += String.format("\nBalance: %s\n\n", player.getBalance());
    	}
    	result += "Monster: " + getClass().getSimpleName() + "\n";
    	if (!name.equals(getClass().getSimpleName())){
    		result += "Name: " + name + "\n";
    	}
    	result += description + "\n";
    	result += "Health: " + health + "\n";
    	result += "Max Health: " + maxHealth + "\n";
    	result += "Damage: " + damage + "\n";
    	result += "Cost: " + cost + "\n";
    	result += "Level: " + level + "\n";
    	result += "Heal Amount: " + healAmount + "\n";
    	result += "Crit Rate: " + (int) (critRate * 100) + "%\n";
    	result += "Fainted: " + isFainted + "\n";
    	result += "Max Level: " + maxLevel + "\n";
    	if (shop.getMonsters().contains(this)) {
    		result += "\n1: Buy";
    		result += "\n2: Go back";
    	}
    	if (player.getMonsters().contains(this)) {
    		result += "\n1: Rename";
    		result += "\n2: Sell";
    		result += "\n3: Go back";
    	}
    	return result;
    }
    
    
    /**
     * @return monster
     */
    public abstract Monster clone();
    
}
