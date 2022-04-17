package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.*;

class IncreaseDamageTest {
	
	private GameEnvironment game;

	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
	}

	
	@Test
	void testUse1() throws InventoryFullException, PurchasableNotFoundException, StatMaxedOutException {
		// Blue sky
		Monster monster = new Chunky("Test", "Test", 1, 0, 1, 1, 1, 1, game);
		Item item = new IncreaseDamage(game);
		game.getMyMonsters().add(monster);
		game.getMyItems().add(item);
		item.use(monster);
		ArrayList<Item> itemList = new ArrayList<Item>();
		assertEquals(itemList, game.getMyItems().getItemList());
		assertEquals(IncreaseDamage.getDamageIncrease(), monster.getDamage());
	}
	
	
	@Test
	void testUse2() throws InventoryFullException, PurchasableNotFoundException, StatMaxedOutException {
		// Item not owned
		Monster monster = new Chunky("Test", "Test", 1, 1, 1, 1, 1, 1, game);
		Item item = new IncreaseDamage(game);
		game.getMyMonsters().add(monster);
		try {			
			item.use(monster);
		}
		catch (PurchasableNotFoundException e) {
			assertEquals(e.getMessage(), "You do not own this item!");
		}
	}

}
