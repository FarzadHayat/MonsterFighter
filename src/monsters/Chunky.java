package monsters;

import exceptions.StatMaxedOutException;
import main.*;

/**
 * A monster subclass.
 * Big bones, big limbs, big shoulder, everything about this monster is big.
 * They tower over other monsters, making any incoming damage feel like a tickle.
 * Their lack of attack power would long ago have spelled their end if not for
 * their formidable size.
 * @author Farzad and Daniel
 */
public class Chunky extends Monster {
	
	/**
	 * Fields
	 */
	
	/**
	 * Default value for monster statistics 
	 */
	private static String defaultName = "Chunky";
	private static String description = "Big bones, big limbs, big shoulder, everything about this monster is big. They tower over other monsters, making any incoming damage feel like a tickle. Their lack of attack power would long ago have spelled their end if not for their formidable size.";
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
	 * Constructors
	 */
	
	/**
	 * Creates Chunky object by calling superclass constructor and passing in the default values
	 * @param game given GameEnvironment object
	 */
    public Chunky(GameEnvironment game) {
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
     * @return new Chunky instance
     */
    public Monster clone() {
    	Chunky cloneInst = new Chunky(game);
    	int correctLevel = game.getAllMonsters().random().getLevel();
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
