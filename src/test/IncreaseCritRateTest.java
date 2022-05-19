package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InventoryFullException;
import exceptions.StatMaxedOutException;
import items.IncreaseCritRate;
import items.LevelUp;
import main.*;
import monsters.Chunky;

class IncreaseCritRateTest {
	
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
		monster.setCritRate(0);
		Item item = new IncreaseCritRate(game);
		player.getMonsters().add(monster);
		player.getItems().add(item);
		item.use(monster);
		ArrayList<Item> itemList = new ArrayList<Item>();
		assertEquals(itemList, player.getItems());
		assertEquals(IncreaseCritRate.getCritIncrease(), monster.getCritRate());
	}

	
	@Test
	void testUse2() throws InventoryFullException, StatMaxedOutException {
		// Monster crit rate is only partially increased
		Monster monster = new Chunky(game);
		monster.setCritRate(monster.getMaxCritRate() - (IncreaseCritRate.getCritIncrease() / 2));
		Item item = new IncreaseCritRate(game);
		player.getMonsters().add(monster);
		player.getItems().add(item);
		item.use(monster);
		ArrayList<Item> itemList = new ArrayList<Item>();
		assertEquals(itemList, player.getItems());
		assertEquals(monster.getMaxCritRate(), monster.getCritRate());
	}
	
	
	@Test
	void testUse3() throws InventoryFullException, StatMaxedOutException {
		// Monster is already at max crit rate
		Monster monster = new Chunky(game);
		monster.setCritRate(1);
		Item item = new IncreaseCritRate(game);
		player.getMonsters().add(monster);
		player.getItems().add(item);
		try {			
			item.use(monster);
		}
		catch (StatMaxedOutException e) {
			assertEquals("Crit Rate is already maxed out!", e.getMessage());
		}
		
	}
	
	
	@Test
	public void testClone() {
		Item testItem = new IncreaseCritRate(game);
		Item newItem = testItem.clone();
		assertTrue(IncreaseCritRate.class.isInstance(newItem));
	}

}
