package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.*;

class IncreaseHealthTest {
	
	private GameEnvironment game;

	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
	}

	
	@Test
	void testUse1() throws InventoryFullException, PurchasableNotFoundException, StatMaxedOutException {
		// Blue sky
		Monster monster = new Chunky("Test", "Test", 100, 1, 1, 1, 1, 1, game);
		monster.setHealth(0);
		Item item = new IncreaseHealth(game);
		game.getMyMonsters().add(monster);
		game.getMyItems().add(item);
		item.use(monster);
		ArrayList<Item> itemList = new ArrayList<Item>();
		assertEquals(itemList, game.getMyItems().getItemList());
		assertEquals(IncreaseHealth.getHealthIncrease(), monster.getHealth());
	}

	
	@Test
	void testUse2() throws InventoryFullException, PurchasableNotFoundException, StatMaxedOutException {
		// Monster is only partially healed
		Monster monster = new Chunky("Test", "Test", 100, 1, 1, 1, 1, 1, game);
		Item item = new IncreaseHealth(game);
		monster.setHealth(95);
		game.getMyMonsters().add(monster);
		game.getMyItems().add(item);
		item.use(monster);
		ArrayList<Item> itemList = new ArrayList<Item>();
		assertEquals(itemList, game.getMyItems().getItemList());
		assertEquals(monster.getMaxHealth(), monster.getHealth());
	}
	
	
	@Test
	void testUse3() throws InventoryFullException, PurchasableNotFoundException, StatMaxedOutException {
		// Monster is already max health
		Monster monster = new Chunky("Test", "Test", 100, 1, 1, 1, 1, 1, game);
		monster.setHealth(100);
		Item item = new IncreaseHealth(game);
		game.getMyMonsters().add(monster);
		game.getMyItems().add(item);
		try {			
			item.use(monster);
		}
		catch (StatMaxedOutException e) {
			assertEquals(e.getMessage(), "Health is already full!");
		}
		
	}
	
	
	@Test
	void testUse4() throws InventoryFullException, PurchasableNotFoundException, StatMaxedOutException {
		// Item not owned
		Monster monster = new Chunky("Test", "Test", 1, 1, 1, 1, 1, 1, game);
		Item item = new IncreaseHealth(game);
		game.getMyMonsters().add(monster);
		try {			
			item.use(monster);
		}
		catch (PurchasableNotFoundException e) {
			assertEquals(e.getMessage(), "You do not own this item!");
		}
	}

}
