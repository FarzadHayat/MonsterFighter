package items;

import exceptions.NotFoundException;
import exceptions.StatMaxedOutException;
import main.*;

public class LevelUp extends Item {
	
	/**
	 * Fields
	 */
	private static String name = "Level Up";
	private static String description = "Level up a monster by one level.";
	private static int cost = 50;
	
	
	/**
	 * Constructors
	 */
	
	/**
     * Create a new LevelUp object.
     * Set the value of game to the given GameEnvironment object.
     * @param game the given GameEnvironment object.
     */
    public LevelUp (GameEnvironment game) {
    	super(name, description, cost, game);
    };
    
    
    /**
     * Functional
     */

    /**
     * Level up the monster by one level.
     * @param monster the given monster
     * @throws NotFoundException if the given monster was not found in the player inventory or the item was not found in the shop
     * @throws StatMaxedOutException if the monster is already max level
     */
    public void use(Monster monster) throws NotFoundException, StatMaxedOutException
    {
    	if (!player.getItems().getList().contains(this)) {
    		throw new NotFoundException("You do not own this item!");
    	}
    	
    	monster.levelUp();
    	player.getItems().remove(this);
    }
    
    
    /**
     * Get a new instance of the LevelUp class.
     * @return item the new LevelUp object.
     */
    public Item clone() {
    	return new LevelUp(game);
    }

}
