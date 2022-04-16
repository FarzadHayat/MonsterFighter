package main;

/**
 * Class Raka
 * low damage and health but heals allies slightly or increases their damage
 */
public class Raka extends Monster {
	
	/**
	 * Default variables
	 * Note: Need to give value to these variables later on 
	 */
	private int healingAmount;
	private int buffAmount;
	private int damageBefore;
	
	/**
	 * Constructor for Raka class 
	 * Note: Need to change to default values later on 
	 */
	public Raka(String name, String description, int maxHealth, int damage, int cost, int level, int healAmount, double critRate, GameEnvironment game) {
    	super(name, description, maxHealth, damage, cost, level, healAmount, critRate, game);
    };
    
    /**
     * Getters and Setters methods 
     */
    public int getDamageBefore() {
    	return damageBefore;
    }
    
    public int getHealingAmount() {
    	return healingAmount;
    }
    
    public void setHealingAmount(int amount) {
    	this.healingAmount = amount;
    }
    
    public int getBuffAmount() {
    	return buffAmount;
    }
    
    public void setBuffAmount(int amount) {
    	this.buffAmount = amount;
    }
    
    /**
     * Heals one monster for the healingAmount 
     * @param other monster to heal 
     */
    public void healAllies(Monster other) {
    	other.setHealth(other.getHealth()+healingAmount);
    }
    
    /**
     * Increase damage of ally monster by buffAmount 
     * @param other monster to buff 
     */
    public void increaseDamage(Monster other) {
    	//damage before 
    	damageBefore = other.getDamage();
    	other.setDamage(damageBefore+buffAmount);
    	//After battle set other damage back to damageBefore 
    }

    /**
     * Increases healing amount and buff amount equally by 5 
     */
    public void levelUp()
    {
    	setHealingAmount(getHealingAmount()+5);
    	setBuffAmount(getBuffAmount()+5);
    }


}
