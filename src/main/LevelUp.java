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
     * Level up a monster by one level.
     * @param monster
     * @throws PurchasableNotFoundException
     */
    public void use(Monster monster) throws PurchasableNotFoundException
    {
    	monster.levelUp();
    	game.getMyItems().remove(this);
    }


}
