package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InventoryFullException;
import exceptions.StatMaxedOutException;
import items.HealUp;
import items.LevelUp;
import main.*;
import monsters.Chunky;

class LevelUpTest {
	
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
		int levelBefore = monster.getLevel();
		Item item = new LevelUp(game);
		player.getMonsters().add(monster);
		player.getItems().add(item);
		item.use(monster);
		ArrayList<Item> itemList = new ArrayList<Item>();
		assertEquals(itemList, player.getItems());
		assertEquals(levelBefore + 1, monster.getLevel());
	}
	
	
	@Test
	void testUse2() throws InventoryFullException, StatMaxedOutException {
		// Monster is already max level
		Monster monster = new Chunky(game);
		monster.setLevel(monster.getMaxLevel());
		Item item = new LevelUp(game);
		player.getMonsters().add(monster);
		player.getItems().add(item);
		try {			
			item.use(monster);
		}
		catch (StatMaxedOutException e) {
			assertEquals("Monster is already max level!", e.getMessage());
		}
	}
	
	
	@Test
	public void testClone() {
		Item testItem = new LevelUp(game);
		Item newItem = testItem.clone();
		assertTrue(LevelUp.class.isInstance(newItem));
	}

}
