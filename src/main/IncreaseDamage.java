package main;

public class IncreaseDamage extends Item {
    
	/**
	 * Fields
	 * 
	 */
	private static int damageIncrease = 10;
	private static String name = "Increase Damage";
	private static String description = "Increase a monster's damage by " + damageIncrease + ".";
	private static int cost = 20;
	
	
	/**
	 * Getters and setters
	 * 
	 */
	
	/**
	 * @return the damageIncrease
	 */
	public static int getDamageIncrease() {
		return damageIncrease;
	}


	/**
	 * @param damageIncrease the damageIncrease to set
	 */
	public static void setDamageIncrease(int damageIncrease) {
		IncreaseDamage.damageIncrease = damageIncrease;
	}


	/**
	 * Constructors
	 * 
	 */
	public IncreaseDamage (GameEnvironment game) {
		super(name, description, cost, game);
	};
	

    /**
     * Increase the monster's damage by damageIncrease amount.
     * @param monster
     * @throws PurchasableNotFoundException 
     */
    public void use(Monster monster) throws PurchasableNotFoundException
    {	
    	if (!game.getMyItems().contains(this)) {
    		throw new PurchasableNotFoundException("You do not own this item!");
    	}
    	
    	monster.setDamage(monster.getDamage() + damageIncrease);
    	game.getMyItems().remove(this);
    }
    
    
    /**
     * @return new IncreaseDamage instance
     */
    public Item clone() {
    	return new IncreaseDamage(game);
    }

}
