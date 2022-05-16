package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InventoryFullException;
import exceptions.StatMaxedOutException;
import items.IncreaseDamage;
import items.LevelUp;
import main.*;
import monsters.Chunky;

class IncreaseDamageTest {
	
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
		int damageBefore = monster.getDamage();
		Item item = new IncreaseDamage(game);
		player.getMonsters().add(monster);
		player.getItems().add(item);
		item.use(monster);
		ArrayList<Item> itemList = new ArrayList<Item>();
		assertEquals(itemList, player.getItems().getList());
		assertEquals(damageBefore + IncreaseDamage.getDamageIncrease(), monster.getDamage());
	}
	
	
	@Test
	public void testClone() {
		Item testItem = new IncreaseDamage(game);
		Item newItem = testItem.clone();
		assertTrue(IncreaseDamage.class.isInstance(newItem));
	}

}
