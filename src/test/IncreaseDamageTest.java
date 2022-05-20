package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InventoryFullException;
import exceptions.StatMaxedOutException;
import items.DamagePotion;
import items.LevelPotion;
import main.*;
import monsters.Chunky;

/**
 * Unit test for DamagePotion class
 * @author Farzad and Daniel
 */

class IncreaseDamageTest {
	
	/**
	 * Fields
	 */
	private GameEnvironment game;
	private Player player;
	
	/**
	 * Assign values to fields used in unit test
	 * @throws Exception if any exception is caught
	 */
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		game.setupGame();
		player = game.getPlayer();
	}

	/**
	 * Use item on monster
	 * @result item is used on monster without any errors
	 * @throws InventoryFullException if inventory is already full
	 * @throws StatMaxedOutException if monster stat is already maxed out
	 */
	@Test
	void testUse1() throws InventoryFullException, StatMaxedOutException {
		// Blue sky
		Monster monster = new Chunky(game);
		int damageBefore = monster.getDamage();
		Item item = new DamagePotion(game);
		player.getMonsters().add(monster);
		player.getItems().add(item);
		item.use(monster);
		ArrayList<Item> itemList = new ArrayList<Item>();
		assertEquals(itemList, player.getItems());
		assertEquals(damageBefore + DamagePotion.getDamageIncrease(), monster.getDamage());
	}
	
	/**
	 * Clones item instance
	 * @result cloned instance is of the correct class
	 */
	@Test
	public void testClone() {
		Item testItem = new DamagePotion(game);
		Item newItem = testItem.clone();
		assertTrue(DamagePotion.class.isInstance(newItem));
	}

}
