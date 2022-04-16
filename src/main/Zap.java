package main;

/**
 * Class Zap
 * cheap, high crit rate, low health
 */
public class Zap extends Monster {
	
	/**
	 * Constructor for Zap class 
	 * Note: Need to change to default values later on 
	 */
	public Zap(String name, String description, int maxHealth, int damage, int cost, int level, int healAmount, double critRate, GameEnvironment game) {
    	super(name, description, maxHealth, damage, cost, level, healAmount, critRate, game);
    };

    /**
     * Increases critical rate by 0.2
     */
    public void levelUp()
    {
    	setCritRate(getCritRate()+0.2);
    }


}
