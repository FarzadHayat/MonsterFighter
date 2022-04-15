package main;

public abstract class Monster implements Purchasable {
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
	private int healAmount;
	private double critRate;
	private boolean isFainted = false;
	private GameEnvironment game;
	private double refundAmount = 0.5;
	
	/**
	 * Constructor method 
	 * FOR SUBCLASS: super(name, description, maxHealth, damage, cost, level, healAmount, critRate)
	 */
	public Monster(String name, String description, int maxHealth, int damage, int cost, int level, int healAmount, double critRate) {
		this.name = name;
		this.description = description;
		this.maxHealth = maxHealth;
		this.health = maxHealth;
		this.damage = damage;
		this.cost = cost;
		this.level = level;
		this.healAmount = healAmount;
		this.critRate = critRate;
	}
	
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
     * Set the value of health
     * @param health the new value of health
     */
    public void setHealth (int health) {
        this.health = health;
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
	 * Functional methods
	 * 
	 */

    /**
     * Heals the monster for the heal amount.
     */
	public void heal() {
		health += healAmount;
		if(this.getHealth() > this.getMaxHealth()) {
			this.setHealth(maxHealth);
		}
	}
	
	/**
     * @param other the monster that should take damage
     */
	public void attack(Monster other) {
		other.takeDamage(this.damage);
	}
	
	/**
     * @param damageReceived the amount of damage this monster took
     */
	public void takeDamage(int damageReceived) {
		health -= damageReceived;
		if(this.getHealth() <= 0) {
			this.setIsFainted(true);
		}
	}
	
    /**
     * Buy a monster from the shop and add it to the player inventory.
     * @throws InsufficientFundsException cost of item is more than player balance error
     * @throws InventoryFullException inventory is full error
     */
	public void buy() throws InsufficientFundsException, InventoryFullException{
		game.minusBalance(cost);
		game.getInventory().addMonster(this);
	}
	
    /**
     * Sell monster back to the shop for a partial refund and removes the monster from the player's inventory
     * @throws PurchasableNotFoundException monster was not found in the player inventory error
     */
	public void sell() throws PurchasableNotFoundException {
		game.addBalance(cost * refundAmount);
		game.getInventory().removeMonster(this);
	}
	
	/**
     * Level up the monster's statistics.
     */
	public abstract void levelUp();
	
	
}
