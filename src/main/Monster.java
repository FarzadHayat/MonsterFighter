package main;

import java.util.*;

public abstract class Monster implements Storable {
	/**
	 *	Monster statistic variables 
	 *
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
	private boolean isFainted = false;
	protected GameEnvironment game;
	private double refundAmount = 0.5;
	private boolean isBuffed = false;
	
	/**
	 * Constructor method 
	 * FOR SUBCLASS: super(name, description, maxHealth, damage, cost, level, healAmount, critRate)
	 */
	public Monster(String name, String description, int maxHealth, int damage, int cost, int level, int healAmount, double critRate, GameEnvironment game) {
		this.name = name;
		this.description = description;
		this.maxHealth = maxHealth;
		this.health = maxHealth;
		this.damage = damage;
		this.cost = cost;
		this.level = level;
		this.healAmount = healAmount;
		this.critRate = critRate;
		this.game = game;
	}
	
	/**
	 * Getters and Setters methods 
	 * 
	 */
	
	/**
	 * Set the name of the monster
	 * @param name of the monster
	 * @throws InvalidValueException 
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
	 * @return the maxLevel
	 */
	public int getMaxLevel() {
		return maxLevel;
	}

	/**
	 * @param maxLevel the maxLevel to set
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
	 * @return the maxCritRate
	 */
	public double getMaxCritRate() {
		return maxCritRate;
	}

	/**
	 * @param maxCritRate the maxCritRate to set
	 */
	public void setMaxCritRate(double maxCritRate) {
		this.maxCritRate = maxCritRate;
	}

	/**
	 * @return the critMultiplier
	 */
	public double getCritMultiplier() {
		return critMultiplier;
	}

	/**
	 * @param critMultiplier the critMultiplier to set
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
     * Set the value of isFainted
     * @param isFainted the new value of isFainted
     */
    public void setIsBuffed (boolean buffed) {
        this.isBuffed = buffed;
    }

    /**
     * Get the value of isFainted
     * @return the value of isFainted
     */
    public boolean getIsBuffed () {
        return isBuffed;
    }
	
	/**
	 * Functional methods
	 * 
	 */

    /**
     * Heals the monster for the heal amount.
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
     * @param other the monster that should take damage
	 * @throws InvalidValueException 
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
     * @param damageReceived the amount of damage this monster took
     * @throws InvalidValueException
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
	 * Return the damage the monster deals based on whether it was a critical hit
	 * @return the final damage after calculations 
	 */
	public int finalDamage() {
		Random rn = new Random();
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
     * @throws InsufficientFundsException cost of item is more than player balance error
     * @throws InventoryFullException inventory is full error
     * @throws PurchasableNotFoundException 
     * @throws InvalidValueException 
     */
	public String buy() throws InsufficientFundsException, InventoryFullException, PurchasableNotFoundException, InvalidValueException{
		game.minusBalance(cost);
		game.getMyMonsters().add(this);
		int index = game.getShop().getMonsters().indexOf(this);
		game.getShop().getMonsters().remove(this);
		game.getShop().getMonsters().add(index, game.getAllMonsters().random());
		return "You bought: " + name;
	}
	
    /**
     * Sell monster back to the shop for a partial refund and removes the monster from the player's inventory
     * @throws PurchasableNotFoundException monster was not found in the player inventory error
     * @throws InvalidValueException 
     */
	public String sell() throws PurchasableNotFoundException, InvalidValueException {
		game.addBalance(cost * refundAmount);
		game.getMyMonsters().remove(this);
		return "You sold: " + name;
	}
	
	/**
     * Level up the monster's statistics.
	 * @throws StatMaxedOutException 
     */
	public void levelUp( ) throws StatMaxedOutException {
		if (level == maxLevel) {
			throw new StatMaxedOutException("Monster is already max level!");
		}
		else {
			level += 1;
		}
	};
	
	
	/**
	 * @return
	 */
	public String toString() {
		return String.format("%s (%s health: %s, max health: %s, damage: %s, cost: %s, "
						+ "level: %s, heal amount: %s, crit rate: %s, fainted: %s)",
				name, description, health, maxHealth, damage, cost, level, healAmount, critRate, isFainted);
	}
	
	
	/**
	 * @return
	 */
    public String view() {
    	String result = "";
    	if (game.getShop().getMonsters().contains(this)) {
    		result += String.format("\nBalance: %s\n", game.getBalance());
    	}
    	result = "Monster: " + getClass().getSimpleName() + "\n";
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
    	result += "Crit Rate: " + critRate + "\n";
    	result += "Fainted: " + isFainted + "\n";
    	if (game.getShop().getMonsters().contains(this)) {    		
    		result += "\n1: Buy";
    	}
    	if (game.getMyMonsters().contains(this)) {    		
    		result += "\n1: Sell";
    	}
    	result += "\n2: Go back";
    	return result;
    }
    
    
    /**
     * @return monster
     */
    public abstract Monster clone();
    
}
