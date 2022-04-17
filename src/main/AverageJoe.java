package main;

/**
 * Class AverageJoe
 * Medium damage and health
 */
public class AverageJoe extends Monster{
	
	/**
	 * Default value for monster statistics 
	 */
	private static String defaultName = "AverageJoe";
	private static String description = "";
	private static int defaultMaxHealth = 100;
	private static int defaultDamage = 20;
	private static int defaultCost = 60;
	private static int defaultHealAmount = (int) (0.2*defaultMaxHealth);
	private static double defaultCritRate = 0.2;
	private static int level = 1;
	
	/**
	 * Increment on statistics per level 
	 */
	private int levelUpHealth = (int)(0.1*getMaxHealth());
	private int levelUpDamage = 10;
	private int levelUpCost = 10;
	private int levelUpHealAmount = (int)(0.1*getMaxHealth());
	
	/**
	 * Constructor for AverageJoe class 
	 * Note: Need to change to default values later on 
	 */
	public AverageJoe(GameEnvironment game) {
    	super(defaultName, description, defaultMaxHealth, defaultDamage, defaultCost, level, defaultHealAmount, defaultCritRate, game);
    };
    

    /**
     * Level up monster statistics relevant to the monster 
     */
    public void levelUp()
    {
    	setMaxHealth(getMaxHealth()+levelUpHealth);
    	setDamage(getDamage()+levelUpDamage);
    	setCost(getCost()+levelUpCost);
    	setHealAmount(getHealAmount()+levelUpHealAmount);
    }
}
