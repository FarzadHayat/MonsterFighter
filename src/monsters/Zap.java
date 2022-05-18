package monsters;

import exceptions.StatMaxedOutException;
import main.*;

public class Zap extends Monster implements Cloneable {
	
	/**
	 * Fields
	 */
	
	/**
	 * Default value for monster statistics 
	 */
	private static String defaultName = "Zap";
	private static String description = "Being charged is in its nature, some say its attacks are so powerful you won't even know you're dead. Specialising in critical rate, this monster has the potential to strike as hard as a thunderstorm. However, when it doesn't land a critical hit, its attacks are only mediocre.";
	private static int defaultMaxHealth = 80;
	private static int defaultDamage = 20;
	private static int defaultCost = 50;
	private static int defaultHealAmount = (int) (0.2*defaultMaxHealth);
	private static double defaultCritRate = 0.5;
	private static int level = 1;
	
	/**
	 * Increment on statistics per level 
	 */
	private int levelUpHealth = (int)(0.1*getMaxHealth());
	private int levelUpDamage = 10;
	private int levelUpCost = 10;
	private int levelUpHealAmount = (int)(0.1*getMaxHealth());
	private double levelUpCritRate = 0.2;
	
	
	/**
	 * Constructors
	 */
	
	/**
	 * Creates Zap object by calling superclass constructor and passing in the default values
	 * @param game given GameEnvironment object
	 */
	public Zap(GameEnvironment game) {
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
    	double newCritRate = Math.round((getCritRate()+levelUpCritRate)*10.0)/ 10.0;
    	setCritRate(newCritRate);
    	setHealth(getHealth()+levelUpHealth);
    }
    
    
    /**
     * @return new Zap instance
     */
    public Monster clone() {
    	Zap cloneInst = new Zap(game);
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
