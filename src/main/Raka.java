package main;

/**
 * Class Raka
 * low damage and health but heals allies slightly or increases their damage
 */
public class Raka extends Monster {
	
	/**
	 * Default value for monster statistics 
	 */
	private static String defaultName = "Raka";
	private static String description = "";
	private static int defaultMaxHealth = 80;
	private static int defaultDamage = 10;
	private static int defaultCost = 70;
	private static int defaultHealAmount = (int) (0.2*defaultMaxHealth);
	private static double defaultCritRate = 0.1;
	private static int level = 1;
	
	/**
	 * Increment on statistics per level 
	 */
	private int levelUpHealth = (int)(0.1*getMaxHealth());
	private int levelUpDamage = 5;
	private int levelUpCost = 10;
	private int levelUpHealAmount = (int)(0.1*getMaxHealth());
	
	/**
	 * Damage before Raka buffs the other monster
	 */
	private int damageBefore;
	
	/**
	 * Constructor for Raka class 
	 * Note: Need to change to default values later on 
	 */
	public Raka(GameEnvironment game) {
    	super(defaultName, description, defaultMaxHealth, defaultDamage, defaultCost, level, defaultHealAmount, defaultCritRate, game);
    };
    
    /**
     * Getters and Setters methods 
     */
    public int getDamageBefore() {
    	return damageBefore;
    }
    
    public int getHealingAmount() {
    	return getHealAmount();
    }

    public int getBuffAmount() {
    	return getDamage();
    }
    
    /**
     * Heals one monster for the healingAmount 
     * @param other monster to heal 
     */
    public void healAllies(Monster other) throws InvalidTargetException {
    	if(!other.getIsFainted()) {
    		other.setHealth(other.getHealth()+getHealAmount());
    	}
    	else {
    		throw new InvalidTargetException("Invalid target!");
    	}
    	
    }
    
    /**
     * Increase damage of ally monster by buffAmount 
     * @param other monster to buff 
     */
    public void increaseDamage(Monster other) throws InvalidTargetException {
    	if(!other.getIsFainted() && !other.getIsBuffed()) {
    		//damage before 
        	damageBefore = other.getDamage();
        	other.setDamage(damageBefore+getDamage());
        	other.setIsBuffed(true);
        	//After turn set other damage back to damageBefore 
    	}
    	else {
    		throw new InvalidTargetException("Invalid target!");
    	}
    }

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


}
