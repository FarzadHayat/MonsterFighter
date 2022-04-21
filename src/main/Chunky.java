package main;

/**
 * Class Chunky
 * Chunky
Tanky but low damage and expensive
 */
public class Chunky extends Monster {
	
	/**
	 * Default value for monster statistics 
	 */
	private static String defaultName = "Chunky";
	private static String description = "";
	private static int defaultMaxHealth = 200;
	private static int defaultDamage = 10;
	private static int defaultCost = 80;
	private static int defaultHealAmount = (int) (0.2*defaultMaxHealth);
	private static double defaultCritRate = 0.1;
	private static int level = 1;
	
	/**
	 * Increment on statistics per level 
	 */
	private int levelUpHealth = (int)(0.2*getMaxHealth());
	private int levelUpDamage = 5;
	private int levelUpCost = 10;
	private int levelUpHealAmount = (int)(0.1*getMaxHealth());
	
	
	/**
	 * Constructor for Chunky class 
	 * Note: Need to change to default values later on 
	 */
    public Chunky(GameEnvironment game) {
    	super(defaultName, description, defaultMaxHealth, defaultDamage, defaultCost, level, defaultHealAmount, defaultCritRate, game);
    };

    /**
     * Level up monster statistics relevant to the monster 
     * @throws StatMaxedOutException 
     */
    public void levelUp() throws StatMaxedOutException
    {
    	super.levelUp();
    	setMaxHealth(getMaxHealth()+levelUpHealth);
    	setDamage(getDamage()+levelUpDamage);
    	setCost(getCost()+levelUpCost);
    	setHealAmount(getHealAmount()+levelUpHealAmount);
    	setHealth(getHealth()+levelUpHealth);
    }
    
    
    /**
     * @return new Chunky instance
     */
    public Monster clone() {
    	return new Chunky(game);
    }

}
