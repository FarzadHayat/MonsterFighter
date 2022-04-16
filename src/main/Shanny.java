package main;

/**
 * Class Shanny
 * Low damage and average health but high healing
 */
public class Shanny extends Monster {
	
	/**
	 * Constructor for Shanny class 
	 * Note: Need to change to default values later on 
	 */
	public Shanny(String name, String description, int maxHealth, int damage, int cost, int level, int healAmount, double critRate, GameEnvironment game) {
    	super(name, description, maxHealth, damage, cost, level, healAmount, critRate, game);
    };
    
    /**
     * Increases healAmount by 15
     */
    public void levelUp()
    {
    	setHealAmount(getHealAmount()+15);
    }


}
