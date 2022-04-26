package main;

public class Raka extends Monster {
	
	/**
	 * Fields
	 */
	
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
	 * Constructors
	 */
	
	/**
	 * Creates Raka object by calling superclass constructor and passing in the default values
	 * @param game given GameEnvironment object
	 */
	public Raka(GameEnvironment game) {
    	super(defaultName, description, defaultMaxHealth, defaultDamage, defaultCost, level, defaultHealAmount, defaultCritRate, game);
    };
    
    
    /**
     * Getters and Setters 
     */
    
    /**
     * Get the value of damageBefore
     * @return the value of damageBefore
     */
    public int getDamageBefore() {
    	return damageBefore;
    }
    
    
    /**
     * Sets the value of damageBefore
     * @param damageBefore the new value of damageBefore
     */
    public void setDamageBefore(int damageBefore) {
    	this.damageBefore = damageBefore;
    }
    

    /**
     * Functional
     */
    
    /**
     * Get the value of healingAmount 
     * @return the value of healingAmount
     */
    public int getHealingAmount() {
    	return getHealAmount();
    }
    

    /**
     * Get the value of monster's damage to buff 
     * @return the value of damage to buff
     */
    public int getBuffAmount() {
    	return getDamage();
    }
    
    
    /**
     * Heals given Monster object
     * @param other given Monster object
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
     * Increase damage of given Monster object
     * @param other given Monster object
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
     * @return new Raka instance
     */
    public Monster clone() {
    	return new Raka(game);
    }
    
}
