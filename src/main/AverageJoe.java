package main;

/**
 * Class AverageJoe
 * Medium damage and health
 */
public class AverageJoe extends Monster{
	
	/**
	 * Constructor for AverageJoe class 
	 * Note: Need to change to default values later on 
	 */
	public AverageJoe(String name, String description, int maxHealth, int damage, int cost, int level, int healAmount, double critRate, GameEnvironment game) {
    	super(name, description, maxHealth, damage, cost, level, healAmount, critRate, game);
    };
    

    /**
     * Levels up statistics equally across all statistics
     */
    public void levelUp()
    {
    	setMaxHealth(getMaxHealth()+4);
    	setDamage(getDamage()+4);
    	setHealAmount(getHealAmount()+2);
    }
}
