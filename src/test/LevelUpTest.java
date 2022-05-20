package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InventoryFullException;
import exceptions.StatMaxedOutException;
import items.LevelPotion;
import main.*;
import monsters.Chunky;

/**
 * Unit test for LevelPotion class
 * @author Farzad and Daniel
 */

class LevelUpTest {
	
	/**
	 * Fields
	 */
	private GameEnvironment game = GameEnvironment.getInstance();
	private Player player;
	
	/**
	 * Assign values to fields used in unit test
	 * @throws Exception if any exception is caught
	 */
	@BeforeEach
	void setUp() throws Exception {
		game.populateGame();
		player = game.getPlayer();
	}

	/** Use item on monster
	 * @result item is used on monster without any errors
	 * @throws InventoryFullException if inventory is already full
	 * @throws StatMaxedOutException if monster stat is already maxed out
	 */
	@Test
	void testUse1() throws InventoryFullException, StatMaxedOutException {
		// Blue sky
		Monster monster = new Chunky();
		int levelBefore = monster.getLevel();
		Item item = new LevelPotion();
		player.getMonsters().add(monster);
		player.getItems().add(item);
		item.use(monster);
		ArrayList<Item> itemList = new ArrayList<Item>();
		assertEquals(itemList, player.getItems());
		assertEquals(levelBefore + 1, monster.getLevel());
	}
	
	/**
	 * Use item on maximum level monster
	 * @result exception is thrown as monster is already at max level
	 * @throws InventoryFullException if inventory is already full
	 * @throws StatMaxedOutException if monster stat is already maxed out
	 */
	@Test
	void testUse2() throws InventoryFullException, StatMaxedOutException {
		// Monster is already max level
		Monster monster = new Chunky();
		monster.setLevel(monster.getMaxLevel());
		Item item = new LevelPotion();
		player.getMonsters().add(monster);
		player.getItems().add(item);
		try {			
			item.use(monster);
		}
		catch (StatMaxedOutException e) {
			assertEquals("Monster is already max level!", e.getMessage());
		}
	}
	
	/**
	 * Clones item instance
	 * @result cloned instance is of the correct class
	 */
	@Test
	public void testClone() {
		Item testItem = new LevelPotion();
		Item newItem = testItem.clone();
		assertTrue(LevelPotion.class.isInstance(newItem));
	}

}
