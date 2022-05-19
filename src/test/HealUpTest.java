package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InventoryFullException;
import exceptions.StatMaxedOutException;
import items.HealUp;
import items.IncreaseCritRate;
import main.*;
import monsters.Chunky;

class HealUpTest {
	
	private GameEnvironment game;
	private Player player;
	
	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		game.setupGame();
		player = game.getPlayer();
	}

	
	@Test
	void testUse1() throws InventoryFullException, StatMaxedOutException {
		// Blue sky
		Monster monster = new Chunky(game);
		monster.setHealth(0);
		Item item = new HealUp(game);
		player.getMonsters().add(monster);
		player.getItems().add(item);
		item.use(monster);
		ArrayList<Item> itemList = new ArrayList<Item>();
		assertEquals(itemList, player.getItems());
		assertEquals(HealUp.getHealAmount(), monster.getHealth());
	}

	
	@Test
	void testUse2() throws InventoryFullException, StatMaxedOutException {
		// Monster is only partially healed
		Monster monster = new Chunky(game);
		monster.setHealth(monster.getHealth() - (HealUp.getHealAmount() / 2));
		Item item = new HealUp(game);
		player.getMonsters().add(monster);
		player.getItems().add(item);
		item.use(monster);
		ArrayList<Item> itemList = new ArrayList<Item>();
		assertEquals(itemList, player.getItems());
		assertEquals(monster.getMaxHealth(), monster.getHealth());
	}
	
	
	@Test
	void testUse3() throws InventoryFullException, StatMaxedOutException {
		// Monster is already max health
		Monster monster = new Chunky(game);
		Item item = new HealUp(game);
		player.getMonsters().add(monster);
		player.getItems().add(item);
		try {			
			item.use(monster);
		}
		catch (StatMaxedOutException e) {
			assertEquals("Health is already full!", e.getMessage());
		}
		
	}
	
	
	@Test
	public void testClone() {
		Item testItem = new HealUp(game);
		Item newItem = testItem.clone();
		assertTrue(HealUp.class.isInstance(newItem));
	}

}