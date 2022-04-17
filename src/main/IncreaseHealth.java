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
    	if (!game.getMyItems().getItemList().contains(this)) {
    		throw new PurchasableNotFoundException("You do not own this item!");
    	}
    	
    	int health = monster.getHealth();
    	int maxHealth = monster.getMaxHealth();
    	
		if (health == maxHealth) {
			throw new StatMaxedOutException("Health is already full!");
		}
		
    	int newHealth = health + healthIncrease;
    	if (newHealth > maxHealth) {
    		newHealth = maxHealth;
    	}
    	
    	monster.setHealth(newHealth);
    	game.getMyItems().remove(this);
    }
}
