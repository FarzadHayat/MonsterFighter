package main;

/**
 * Class Lanky
 * Lanky
Squishy but high damage
 */
public class Lanky extends Monster {
	
	/**
	 * Default value for monster statistics 
	 */
	private static String defaultName = "Lanky";
	private static String description = "";
	private static int defaultMaxHealth = 60;
	private static int defaultDamage = 40;
	private static int defaultCost = 70;
	private static int defaultHealAmount = (int) (0.2*defaultMaxHealth);
	private static double defaultCritRate = 0.1;
	private static int level = 1;
	
	/**
	 * Increment on statistics per level 
	 */
	private int levelUpHealth = (int)(0.1*getMaxHealth());
	private int levelUpDamage = 15;
	private int levelUpCost = 10;
	private int levelUpHealAmount = (int)(0.1*getMaxHealth());
	
	/**
	 * Constructor for Lanky class 
	 * Note: Need to change to default values later on 
	 */
	public Lanky(GameEnvironment game) {
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
     * @return new Lanky instance
     */
    public Monster clone() {
    	return new Lanky(game);
    }
    
}
