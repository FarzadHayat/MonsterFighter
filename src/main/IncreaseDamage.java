package main;

public class IncreaseDamage extends Item {
    
	/**
	 * Fields
	 */
	private static int damageIncrease = 10;
	private static String name = "Increase Damage";
	private static String description = "Increase a monster's damage by " + damageIncrease + ".";
	private static int cost = 20;

	
	/**
	 * Constructors
	 */
	
	/**
	 * Create a new IncreaseDamage object.
	 * Set the value of game to the given GameEnvironment object.
	 * @param game the given GameEnvironment object.
	 */
	public IncreaseDamage (GameEnvironment game) {
		super(name, description, cost, game);
	};
	
	
	/**
	 * Getters and setters
	 */
	
	/**
	 * Get the value of damageIncrease
	 * @return the value of damageIncrease
	 */
	public static int getDamageIncrease() {
		return damageIncrease;
	}


	/**
	 * Set the value of damageIncrease
	 * @param damageIncrease the new value of damageIncrease
	 */
	public static void setDamageIncrease(int damageIncrease) {
		IncreaseDamage.damageIncrease = damageIncrease;
	}

	
	/**
     * Functional
     */

    /**
     * Increase the monster's damage by damageIncrease amount.
     * @param monster the given monster
     * @throws NotFoundException if the given monster was not found in the player inventory or the item was not found in the shop
     */
    public void use(Monster monster) throws NotFoundException
    {	
    	if (!player.getItems().getList().contains(this)) {
    		throw new NotFoundException("You do not own this item!");
    	}
    	
    	monster.setDamage(monster.getDamage() + damageIncrease);
    	player.getItems().remove(this);
    }
    
    
    /**
     * Get a new instance of the IncreaseDamage class.
     * @return item the new IncreaseDamge object.
     */
    public Item clone() {
    	return new IncreaseDamage(game);
    }

}
