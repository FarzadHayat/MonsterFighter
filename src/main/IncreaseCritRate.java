package main;

public class IncreaseCritRate extends Item {
    
	/**
	 * Fields
	 */
	private static double critIncrease = 0.2;
	private static String name = "Increase Crit Rate";
	private static String description = "Increase a monster's crit rate by " + critIncrease * 100 + " percent.";
	private static int cost = 20;
	
	/**
	 * Constructors
	 * 
	 */
	public IncreaseCritRate (GameEnvironment game) {
		super(name, description, cost, game);
	};
	

    /**
     * increase the monster's crit rate by critIncrease amount.
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
		
    	double newCritRate = critRate + critIncrease;
    	if (newCritRate > maxCritRate) {
    		newCritRate = maxCritRate;
    	}
    	
    	monster.setCritRate(newCritRate);
    	game.getMyItems().remove(this);
    }

}
