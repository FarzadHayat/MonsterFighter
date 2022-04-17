package main;

public class IncreaseHealth extends Item {
    
	/**
	 * Fields
	 * 
	 */
	private static int healthIncrease = 20;
	private static String name = "Increase Health";
	private static String description = "Increase a monster's health by " + getHealthIncrease() + ".";
	private static int cost = 20;
	
	
	/**
	 * Getters and setters
	 * 
	 */
	
	/**
	 * @return the healthIncrease
	 */
	public static int getHealthIncrease() {
		return healthIncrease;
	}


	/**
	 * @param healthIncrease the healthIncrease to set
	 */
	public static void setHealthIncrease(int healthIncrease) {
		IncreaseHealth.healthIncrease = healthIncrease;
	}


	/**
	 * Constructors
	 * 
	 */
	public IncreaseHealth (GameEnvironment game) {
		super(name, description, cost, game);
	};
	

    /**
     * Increase the monster's health by healthIncrease amount.
     * @param monster
     * @throws PurchasableNotFoundException 
     * @throws StatMaxedOutException 
     */
    public void use(Monster monster) throws PurchasableNotFoundException, StatMaxedOutException
    {
    	int health = monster.getHealth();
    	int maxHealth = monster.getHealth();
    	
		if (health == maxHealth) {
			throw new StatMaxedOutException("Health is already maxed out!");
		}
		
    	int newHealth = health + getHealthIncrease();
    	if (newHealth > maxHealth) {
    		newHealth = maxHealth;
    	}
    	
    	monster.setHealth(newHealth);
    	game.getMyItems().remove(this);
    }
}
