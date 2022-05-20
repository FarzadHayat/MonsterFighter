package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InventoryFullException;
import exceptions.StatMaxedOutException;
import items.HealthPotion;
import items.CritPotion;
import main.*;
import monsters.Chunky;

/**
 * Unit test for HealthPotion class
 * @author Farzad and Daniel
 */

class HealUpTest {
	
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
		monster.setHealth(0);
		Item item = new HealthPotion(game);
		player.getMonsters().add(monster);
		player.getItems().add(item);
		item.use(monster);
		ArrayList<Item> itemList = new ArrayList<Item>();
		assertEquals(itemList, player.getItems());
		assertEquals(HealthPotion.getHealAmount(), monster.getHealth());
	}

	/**
	 * Use item on monster and monster heals to greater than maximum health
	 * @result monster is healed to maximum health
	 * @throws InventoryFullException if inventory is already full
	 * @throws StatMaxedOutException if monster stat is already maxed out
	 */
	@Test
	void testUse2() throws InventoryFullException, StatMaxedOutException {
		// Monster is only partially healed
		Monster monster = new Chunky(game);
		monster.setHealth(monster.getHealth() - (HealthPotion.getHealAmount() / 2));
		Item item = new HealthPotion(game);
		player.getMonsters().add(monster);
		player.getItems().add(item);
		item.use(monster);
		ArrayList<Item> itemList = new ArrayList<Item>();
		assertEquals(itemList, player.getItems());
		assertEquals(monster.getMaxHealth(), monster.getHealth());
	}
	
	/**
	 * Use item on maximum health monster
	 * @result exception is thrown as monster is already at max health 
	 * @throws InventoryFullException if inventory is already full
	 * @throws StatMaxedOutException if monster stat is already maxed out
	 */
	@Test
	void testUse3() throws InventoryFullException, StatMaxedOutException {
		// Monster is already max health
		Monster monster = new Chunky(game);
		Item item = new HealthPotion(game);
		player.getMonsters().add(monster);
		player.getItems().add(item);
		try {			
			item.use(monster);
		}
		catch (StatMaxedOutException e) {
			assertEquals("Health is already full!", e.getMessage());
		}
		
	}
	
	/**
	 * Clones item instance
	 * @result cloned instance is of the correct class
	 */
	@Test
	public void testClone() {
		Item testItem = new HealthPotion(game);
		Item newItem = testItem.clone();
		assertTrue(HealthPotion.class.isInstance(newItem));
	}

}