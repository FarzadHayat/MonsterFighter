<<<<<<< HEAD:src/test/HealUpTest.java
package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.*;

class HealUpTest {
	
	private GameEnvironment game;

	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
	}

	
	@Test
	void testUse1() throws InventoryFullException, PurchasableNotFoundException, StatMaxedOutException {
		// Blue sky
		Monster monster = new Chunky(game);
		monster.setHealth(0);
		Item item = new HealUp(game);
		game.getMyMonsters().add(monster);
		game.getMyItems().add(item);
		item.use(monster);
		ArrayList<Item> itemList = new ArrayList<Item>();
		assertEquals(itemList, game.getMyItems().getList());
		assertEquals(HealUp.getHealAmount(), monster.getHealth());
	}

	
	@Test
	void testUse2() throws InventoryFullException, PurchasableNotFoundException, StatMaxedOutException {
		// Monster is only partially healed
		Monster monster = new Chunky(game);
		monster.setHealth(monster.getHealth() - (HealUp.getHealAmount() / 2));
		Item item = new HealUp(game);
		game.getMyMonsters().add(monster);
		game.getMyItems().add(item);
		item.use(monster);
		ArrayList<Item> itemList = new ArrayList<Item>();
		assertEquals(itemList, game.getMyItems().getList());
		assertEquals(monster.getMaxHealth(), monster.getHealth());
	}
	
	
	@Test
	void testUse3() throws InventoryFullException, PurchasableNotFoundException, StatMaxedOutException {
		// Monster is already max health
		Monster monster = new Chunky(game);
		Item item = new HealUp(game);
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
		Monster monster = new Chunky(game);
		Item item = new HealUp(game);
		game.getMyMonsters().add(monster);
		try {			
			item.use(monster);
		}
		catch (PurchasableNotFoundException e) {
			assertEquals(e.getMessage(), "You do not own this item!");
		}
	}

}
=======
package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.*;

class HealUpTest {
	
	private GameEnvironment game;

	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
	}

	
	@Test
	void testUse1() throws InventoryFullException, PurchasableNotFoundException, StatMaxedOutException {
		// Blue sky
		Monster monster = new Chunky(game);
		monster.setHealth(0);
		Item item = new HealUp(game);
		game.getMyMonsters().add(monster);
		game.getMyItems().add(item);
		item.use(monster);
		ArrayList<Item> itemList = new ArrayList<Item>();
		assertEquals(itemList, game.getMyItems().getList());
		assertEquals(HealUp.getHealAmount(), monster.getHealth());
	}

	
	@Test
	void testUse2() throws InventoryFullException, PurchasableNotFoundException, StatMaxedOutException {
		// Monster is only partially healed
		Monster monster = new Chunky(game);
		monster.setHealth(monster.getHealth() - (HealUp.getHealAmount() / 2));
		Item item = new HealUp(game);
		game.getMyMonsters().add(monster);
		game.getMyItems().add(item);
		item.use(monster);
		ArrayList<Item> itemList = new ArrayList<Item>();
		assertEquals(itemList, game.getMyItems().getList());
		assertEquals(monster.getMaxHealth(), monster.getHealth());
	}
	
	
	@Test
	void testUse3() throws InventoryFullException, PurchasableNotFoundException, StatMaxedOutException {
		// Monster is already max health
		Monster monster = new Chunky(game);
		Item item = new HealUp(game);
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
		Monster monster = new Chunky(game);
		Item item = new HealUp(game);
		game.getMyMonsters().add(monster);
		try {			
			item.use(monster);
		}
		catch (PurchasableNotFoundException e) {
			assertEquals(e.getMessage(), "You do not own this item!");
		}
	}

}
>>>>>>> Fixed line endings:src/test/IncreaseHealthTest.java