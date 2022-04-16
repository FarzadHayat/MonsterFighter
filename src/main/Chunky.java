package main;

/**
 * Class Chunky
 * Chunky
Tanky but low damage and expensive
 */
public class Chunky extends Monster {
	
	/**
	 * Constructor for Chunky class 
	 * Note: Need to change to default values later on 
	 */
    public Chunky(String name, String description, int maxHealth, int damage, int cost, int level, int healAmount, double critRate, GameEnvironment game) {
    	super(name, description, maxHealth, damage, cost, level, healAmount, critRate, game);
    };

    /**
     * Increases max health by 15 
     */
    public void levelUp()
    {
    	setMaxHealth(getMaxHealth()+15);
    }

}
