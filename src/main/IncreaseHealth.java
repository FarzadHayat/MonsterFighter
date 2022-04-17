package main;

public class IncreaseHealth extends Item {
    
	/**
	 * Fields
	 * 
	 */
	private static int healthIncrease = 20;
	private static String name = "Increase Health";
	private static String description = "Increase a monster's health by " + healthIncrease + ".";
	private static int cost = 20;
	
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
		
    	int newHealth = health + healthIncrease;
    	if (newHealth > maxHealth) {
    		newHealth = maxHealth;
    	}
    	
    	monster.setHealth(newHealth);
    	game.getMyItems().remove(this);
    }
}
