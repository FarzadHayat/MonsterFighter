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
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public int getCost() {
		return cost;
	}
	
	public int getLevel() {
		return level;
	}
	
	public double getCritRate() {
		return critRate;
	}
	
	/**
	 * Functional methods
	 * 
	 */
	public void heal() {
		health += healAmount;
	}
	
	public void attack(Monster other) {
		other.takeDamage(this.damage);
	}
	
	public void takeDamage(int damageReceived) {
		health -= damageReceived;
	}
	
	public void buy(Monster monster) {
		//GameEnvironment.minusBalance(cost)
	}
	
	public void sell(Monster monster) {
		/**
		 * Refund 50% of the original cost of the 
		 * monster to the player 
		 */
		//GameEnvironment.addBalance(cost*0.5)
	}
	
	public void levelUp() {
		level += 1; 
	}
	
}
