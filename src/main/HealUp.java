package main;

public class HealUp extends Item {
    
	/**
	 * Fields
	 * 
	 */
	private static int healAmount = 20;
	private static String name = "Heal Up";
	private static String description = "Heal a monster for " + healAmount + " health.";
	private static int cost = 20;
	
	
	/**
	 * Getters and setters
	 * 
	 */
	
	/**
	 * @return the healAmount
	 */
	public static int getHealAmount() {
		return healAmount;
	}


	/**
	 * @param healAmount the healAmount to set
	 */
	public static void setHealAmount(int healthIncrease) {
		HealUp.healAmount = healthIncrease;
	}


	/**
	 * Constructors
	 * 
	 */
	public HealUp (GameEnvironment game) {
		super(name, description, cost, game);
	};
	

    /**
     * Heal a monster for healAmount health.
     * @param monster
     * @throws PurchasableNotFoundException 
     * @throws StatMaxedOutException 
     */
    public void use(Monster monster) throws PurchasableNotFoundException, StatMaxedOutException
    {
    	if (!game.getMyItems().contains(this)) {
    		throw new PurchasableNotFoundException("You do not own this item!");
    	}
    	
    	int health = monster.getHealth();
    	int maxHealth = monster.getMaxHealth();
		
    	if (health == maxHealth) {
    		throw new StatMaxedOutException("Health is already full!");
    	}
    	
    	int newHealth = health + healAmount;

    	if (newHealth > maxHealth) {
    		newHealth = maxHealth;
    	}
    	
    	monster.setHealth(newHealth);
    	game.getMyItems().remove(this);
    }
    
    
    /**
     * @return new HealUp instance
     */
    public Item clone() {
    	return new HealUp(game);
    }
    
}