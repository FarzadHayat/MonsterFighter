package monsters;

import exceptions.StatMaxedOutException;
import main.*;

/**
 * A monster subclass.
 * As the name suggests, this is a regular monster with mediocre stats
 * all across the board. They not specialise in anything in particular
 * but always gets the job done. If you're looking for a safe route,
 * this is the monster to go.
 * @author Farzad and Daniel
 */
public class AverageJoe extends Monster{
	
	/**
	 * Fields
	 */
	
	/**
	 * Default value for monster statistics 
	 */
	private static String defaultName = "Average Joe";
	private static String description = "As the name suggests, this is a regular monster with mediocre stats all across the board. They not specialise in anything in particular but always gets the job done. If you're looking for a safe route, this is the monster to go.";
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
	 * Constructors
	 */
	
	/**
	 * Creates AverageJoe object by calling superclass constructor and passing in the default values
	 */
	public AverageJoe() {
    	super(defaultName, description, defaultMaxHealth, defaultDamage, defaultCost, level, defaultHealAmount, defaultCritRate);
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
     * @return new AverageJoe instance
     */
    public Monster clone() {
    	AverageJoe cloneInst = new AverageJoe();
    	int correctLevel = GameEnvironment.getInstance().getAllMonsters().random().getLevel();
    	for(int i = 0; i < correctLevel - 1; i++) {
    		try {
				cloneInst.levelUp();
			} catch (StatMaxedOutException e) {
				// Error within code
				e.printStackTrace();
			}
    	}
    	return cloneInst;
    }
    
}
