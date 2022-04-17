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
    	monster.setDamage(monster.getDamage() + damageIncrease);
    	game.getMyItems().remove(this);
    }

}
