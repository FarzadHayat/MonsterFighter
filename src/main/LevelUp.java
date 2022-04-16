package main;

public class LevelUp extends Item {
	
	/**
	 * Constructors
	 * 
	 */
    public LevelUp (GameEnvironment game) {
    	super(game);
    	super.setName("Level Up");
		super.setDescription("Level up a monster by one level.");
		super.setCost(10);
    };
    
    
    /**
     * Functional
     * 
     */

    /**
     * Levels up the monster by one level.
     * @param monster
     * @throws PurchasableNotFoundException
     */
    public void use(Monster monster) throws PurchasableNotFoundException
    {
    	monster.levelUp();
    	game.getMyItems().remove(this);
    }


}
