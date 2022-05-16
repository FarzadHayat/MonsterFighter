package monsters;

import exceptions.StatMaxedOutException;
import main.*;

public class Lanky extends Monster {
	
	/**
	 * Fields
	 */
	
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
	 * Constructors
	 */
	
	/**
	 * Creates Lanky object by calling superclass constructor and passing in the default values
	 * @param game given GameEnvironment object
	 */
	public Lanky(GameEnvironment game) {
    	super(defaultName, description, defaultMaxHealth, defaultDamage, defaultCost, level, defaultHealAmount, defaultCritRate, game);
    };
    
    
    /**
     * Functional
     */
    
    /**
     * Level up monster statistics relevant to the monster 
     * @throws StatMaxedOutException if monster is already at maximum level
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
    	Lanky cloneInst = new Lanky(game);
    	int correctLevel = game.getAllMonsters().random().getLevel();
    	for(int i = 0; i < correctLevel - 1; i++) {
    		try {
				cloneInst.levelUp();
			} catch (StatMaxedOutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return cloneInst;
    }
    
}
