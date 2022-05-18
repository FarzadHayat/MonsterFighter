package monsters;

import exceptions.StatMaxedOutException;
import main.*;

public class Shanny extends Monster {
	
	/**
	 * Fields
	 */
	
	/**
	 * Default value for monster statistics 
	 */
	private static String defaultName = "Shanny";
	private static String description = "Its offensive ability is not the best but this monster can definitely hold its ground when it comes to self healing. It specialises in overnight healing, making it perfect for everyday battles. It could end up being your last hope in winning your battles on the last day";
	private static int defaultMaxHealth = 100;
	private static int defaultDamage = 15;
	private static int defaultCost = 60;
	private static int defaultHealAmount = (int) (0.5*defaultMaxHealth);
	private static double defaultCritRate = 0.2;
	private static int level = 1;
	
	/**
	 * Increment on statistics per level 
	 */
	private int levelUpHealth = (int)(0.1*getMaxHealth());
	private int levelUpDamage = 8;
	private int levelUpCost = 10;
	private int levelUpHealAmount = (int)(0.2*getMaxHealth());
	
	/**
	 * Constructors
	 */
	
	/**
	 * Creates Shanny object by calling superclass constructor and passing in the default values
	 * @param game given GameEnvironment object
	 */
	public Shanny(GameEnvironment game) {
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
     * @return new Shanny instance
     */
    public Monster clone() {
    	Shanny cloneInst = new Shanny(game);
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
