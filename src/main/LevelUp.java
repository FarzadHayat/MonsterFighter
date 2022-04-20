package main;

public class LevelUp extends Item {
	
	/**
	 * Fields
	 * 
	 */
	private static String name = "Level Up";
	private static String description = "Level up a monster by one level.";
	private static int cost = 50;
	
	/**
	 * Constructors
	 * 
	 */
    public LevelUp (GameEnvironment game) {
    	super(name, description, cost, game);
    };
    
    
    /**
     * Functional
     * 
     */

    /**
     * Level up the monster by one level.
     * @param monster
     * @throws PurchasableNotFoundException
     * @throws StatMaxedOutException 
     */
    public void use(Monster monster) throws PurchasableNotFoundException, StatMaxedOutException
    {
    	if (!game.getMyItems().contains(this)) {
    		throw new PurchasableNotFoundException("You do not own this item!");
    	}
    	
    	monster.levelUp();
    	game.getMyItems().remove(this);
    }


}
