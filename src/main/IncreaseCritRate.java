package main;

public class IncreaseCritRate extends Item {
    
	/**
	 * Fields
	 */
	private static double critIncrease = 0.2;
	private static String name = "Increase Crit Rate";
	private static String description = "Increase a monster's crit rate by " + (int) (critIncrease * 100) + " percent.";
	private static int cost = 20;
	
	
	/**
	 * Constructors
	 */
	
	/**
     * Create a new IncreaseCritRate object.
     * Set the value of game to the given GameEnvironment object.
     * @param game the given GameEnvironment object.
     */
	public IncreaseCritRate (GameEnvironment game) {
		super(name, description, cost, game);
	};
	

	/**
	 * Getters and setters
	 */
	
	/**
	 * Get the value of critIncrease
	 * @return the value of critIncrease
	 */
	public static double getCritIncrease() {
		return critIncrease;
	}
	
	
	/**
	 * set the value of critIncrease
	 * @param critIncrease the new value of critIncrease
	 */
	public static void setCritIncrease(double critIncrease) {
		IncreaseCritRate.critIncrease = critIncrease;
	}
	
	
    /**
     * Functional
     */

    /**
     * Increase the monster's crit rate by critIncrease amount.
     * @param monster the given monster
     * @throws NotFoundException if the given monster was not found in the player inventory or the item was not found in the shop
     * @throws StatMaxedOutException if the monster is already max crit rate
     */
    public void use(Monster monster) throws NotFoundException, StatMaxedOutException
    {
    	if (!player.getItems().getList().contains(this)) {
    		throw new NotFoundException("You do not own this item!");
    	}
    	
    	double critRate = monster.getCritRate();
    	double maxCritRate = monster.getMaxCritRate();
    	
		if (critRate == maxCritRate) {
			throw new StatMaxedOutException("Crit Rate is already maxed out!");
		}
		
    	double newCritRate = ((double) Math.round((critRate + critIncrease) * 100)) / 100;
    	if (newCritRate > maxCritRate) {
    		newCritRate = maxCritRate;
    	}
    	
    	monster.setCritRate(newCritRate);
    	player.getItems().remove(this);
    }
    
    
    /**
     * Get a new instance of the IncreaseCritRate class.
     * @return item the new IncreaseCritRate object.
     */
    public Item clone() {
    	return new IncreaseCritRate(game);
    }
    
}