package main;

public class IncreaseCritRate extends Item {
    
	/**
	 * Fields
	 */
	private double critIncrease = 0.1;
	
	/**
	 * Constructors
	 * 
	 */
	public IncreaseCritRate (GameEnvironment game) {
		super(game);
		super.setName("Increase Crit Rate");
		super.setDescription("Increase a monster's crit rate by " + critIncrease * 100 + " percent.");
		super.setCost(10);
	};
	

    /**
     * increase the monster's crit rate by critIncrease amount.
     * @param monster
     * @throws PurchasableNotFoundException 
     */
    public void use(Monster monster) throws PurchasableNotFoundException
    {
    	double newCritRate = monster.getCritRate() + critIncrease;
    	if (newCritRate > monster.getMaxCritRate()) {
    		newCritRate = monster.getMaxCritRate();
    	}
    	monster.setCritRate(newCritRate);
    	game.getMyItems().remove(this);
    }

}
