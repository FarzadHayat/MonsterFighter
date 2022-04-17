package main;

public class IncreaseCritRate extends Item {
    
	/**
	 * Fields
	 * 
	 */
	private static double critIncrease = 0.2;
	private static String name = "Increase Crit Rate";
	private static String description = "Increase a monster's crit rate by " + getCritIncrease() * 100 + " percent.";
	private static int cost = 20;
	
	
	/**
	 * Getters and setters
	 * 
	 */
	
	/**
	 * @return the critIncrease
	 */
	public static double getCritIncrease() {
		return critIncrease;
	}


	/**
	 * @param critIncrease the critIncrease to set
	 */
	public static void setCritIncrease(double critIncrease) {
		IncreaseCritRate.critIncrease = critIncrease;
	}


	/**
	 * Constructors
	 * 
	 */
	public IncreaseCritRate (GameEnvironment game) {
		super(name, description, cost, game);
	};
	

    /**
     * Increase the monster's crit rate by critIncrease amount.
     * @param monster
     * @throws PurchasableNotFoundException 
     * @throws StatMaxedOutException 
     */
    public void use(Monster monster) throws PurchasableNotFoundException, StatMaxedOutException
    {
    	double critRate = monster.getCritRate();
    	double maxCritRate = monster.getMaxCritRate();
    	
		if (critRate == maxCritRate) {
			throw new StatMaxedOutException("Crit Rate is already maxed out!");
		}
		
    	double newCritRate = critRate + getCritIncrease();
    	if (newCritRate > maxCritRate) {
    		newCritRate = maxCritRate;
    	}
    	
    	monster.setCritRate(newCritRate);
    	game.getMyItems().remove(this);
    }

}
