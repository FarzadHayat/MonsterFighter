package items;

import exceptions.InvalidValueException;
import exceptions.StatMaxedOutException;
import main.*;

/**
 * Item that can be used on a monster to heal them for the given heal amount.
 * @author Farzad and Daniel
 */
public class HealthPotion extends Item {
    
	/**
	 * Fields
	 */
	private static int healAmount = 20;
	private static String name = "Health Potion";
	private static String description = "Heal a monster for " + healAmount + " health.";
	private static int cost = 20;
	
	
	/**
	 * Constructors
	 */
	
	/**
     * Create a new HealthPotion object.
     */
	public HealthPotion () {
		super(name, description, cost);
	};
	
	
	/**
	 * Getters and setters
	 */
	
	/**
	 * Get the value of healAmount
	 * @return the value of healAmount
	 */
	public static int getHealAmount() {
		return healAmount;
	}
	
	
	/**
	 * Set the value of healAmount
	 * @param healthIncrease the new value of healthIncrease
	 */
	public static void setHealAmount(int healthIncrease) {
		HealthPotion.healAmount = healthIncrease;
	}

	
    /**
     * Functional
     */

    /**
     * Heal a monster for healAmount health.
     * @param monster the given monster
     * @throws StatMaxedOutException if the monster is already max health
     */
    public void use(Monster monster) throws StatMaxedOutException
    {
    	if (monster.getHealth() == monster.getMaxHealth()) {
    		throw new StatMaxedOutException("Health is already full!");
    	}
    	
    	try {
			monster.heal(healAmount);
		} catch (InvalidValueException e) {
			e.printStackTrace();
		}
    	GameEnvironment.getInstance().getPlayer().getItems().remove(this);
    }
    
    
    /**
     * Get a new instance of the HealthPotion class.
     * @return item the new HealthPotion object.
     */
    public Item clone() {
    	return new HealthPotion();
    }
    
}