package main;

/**
 * Class Lanky
 * Lanky
Squishy but high damage
 */
public class Lanky extends Monster {
	
	/**
	 * Constructor for Lanky class 
	 * Note: Need to change to default values later on 
	 */
	public Lanky(String name, String description, int maxHealth, int damage, int cost, int level, int healAmount, double critRate, GameEnvironment game) {
    	super(name, description, maxHealth, damage, cost, level, healAmount, critRate, game);
    };

    /**
     * Increases damage by 15 
     */
    public void levelUp()
    {
    	setDamage(getDamage()+15);
    }


}
