package items;

import exceptions.StatMaxedOutException;
import main.*;

/**
 * Item that can be used on a monster to increase their crit rate by the given crit amount.
 * @author Farzad and Daniel
 */
public class CritPotion extends Item {
    
	/**
	 * Fields
	 */
	private static double critIncrease = 0.2;
	private static String name = "Crit Potion";
	private static String description = "Increase a monster's crit rate by " + (int) (critIncrease * 100) + " percent.";
	private static int cost = 20;
	
	
	/**
	 * Constructors
	 */
	
	/**
     * Create a new CritPotion object.
     */
	public CritPotion () {
		super(name, description, cost);
	};
	

	/**
	 * Getters and setters
	 */
	
	/**
	 * Get the value of critIncrease
	 * @return the value of critIncrease
	 */
	public static double getCritIncrease() {
		return critIncrease;
	}
	
	
	/**
	 * set the value of critIncrease
	 * @param critIncrease the new value of critIncrease
	 */
	public static void setCritIncrease(double critIncrease) {
		CritPotion.critIncrease = critIncrease;
	}
	
	
    /**
     * Functional
     */

    /**
     * Increase the monster's crit rate by critIncrease amount.
     * @param monster the given monster
     * @throws StatMaxedOutException if the monster is already max crit rate
     */
    public void use(Monster monster) throws StatMaxedOutException
    {
    	double critRate = monster.getCritRate();
    	double maxCritRate = monster.getMaxCritRate();
    	
		if (critRate == maxCritRate) {
			throw new StatMaxedOutException("Crit Rate is already maxed out!");
		}
		
    	double newCritRate = ((double) Math.round((critRate + critIncrease) * 100)) / 100;
    	if (newCritRate > maxCritRate) {
    		newCritRate = maxCritRate;
    	}
    	
    	monster.setCritRate(newCritRate);
    	GameEnvironment.getInstance().getPlayer().getItems().remove(this);
    }
    
    
    /**
     * Get a new instance of the CritPotion class.
     * @return item the new CritPotion object.
     */
    public Item clone() {
    	return new CritPotion();
    }
    
}