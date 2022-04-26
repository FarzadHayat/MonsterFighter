package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.*;

class InventoryTest {

	private GameEnvironment game;
	private MonsterInventory monsters;
	private ItemInventory items;
	private BattleInventory battles;
	
	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		game.setupGame();
		monsters = new MonsterInventory(4, game);
		items = new ItemInventory(4, game);
		battles = new BattleInventory(5, game);
	}
	

	@Test
	void testAddT1() throws InventoryFullException {
		// Blue sky
		Monster monster = new Chunky(game);
		monsters.add(monster);
		
		// Blue sky
		Item item = new LevelUp(game);
		items.add(item);
		
		// Blue sky
		Battle battle = new Battle(game);
		battles.add(battle);
	}
	
	
	@Test
	void testAddT2() throws InventoryFullException {
		// Inventory full
		Monster monster = new Chunky(game);
		for (int i = 0; i < monsters.getMaxSize(); i++) {
			monsters.add(monster);
		}
		try {
			monsters.add(monster);
		}
		catch (InventoryFullException e) {
			assertEquals(e.getMessage(), "Inventory full!");
		}
		
		// Inventory full
		Item item = new LevelUp(game);
		for (int i = 0; i < items.getMaxSize(); i++) {
			items.add(item);
		}
		try {
			items.add(item);
		}
		catch (InventoryFullException e) {
			assertEquals(e.getMessage(), "Inventory full!");
		}
		
		// Inventory full
		Battle battle = new Battle(game);
		for (int i = 0; i < battles.getMaxSize(); i++) {
			battles.add(battle);
		}
		try {
			battles.add(battle);
		}
		catch (InventoryFullException e) {
			assertEquals(e.getMessage(), "Inventory full!");
		}
	}

	
	@Test
	void testAddIntT1() throws InventoryFullException {
		// Blue sky (empty inventory)
		Monster monster = new Chunky(game);
		monsters.add(0, monster);
		
		// Blue sky (adding at the start)
		monsters.add(0, monster);
		// continue here...
	}

	
	@Test
	void testRemove() {
		fail("Not yet implemented");
	}

	
	@Test
	void testSize() {
		fail("Not yet implemented");
	}

	
	@Test
	void testGet() {
		fail("Not yet implemented");
	}

	
	@Test
	void testIsFull() {
		fail("Not yet implemented");
	}

	
	@Test
	void testAllFainted() {
		fail("Not yet implemented");
	}

	
	@Test
	void testHealAll() {
		fail("Not yet implemented");
	}

	
	@Test
	void testRandom() {
		fail("Not yet implemented");
	}

	
	@Test
	void testRandomiseMonsters() {
		fail("Not yet implemented");
	}

	
	@Test
	void testRandomiseItems() {
		fail("Not yet implemented");
	}

	
	@Test
	void testRandomiseBattles() {
		fail("Not yet implemented");
	}

	
	@Test
	void testToString() {
		fail("Not yet implemented");
	}

	
	@Test
	void testContainsT() {
		fail("Not yet implemented");
	}

	
	@Test
	void testContainsString() {
		fail("Not yet implemented");
	}

	
	@Test
	void testFind() {
		fail("Not yet implemented");
	}

	
	@Test
	void testIndexOf() {
		fail("Not yet implemented");
	}

	
	@Test
	void testView() {
		fail("Not yet implemented");
	}

	
	@Test
	void testIsEmpty() {
		fail("Not yet implemented");
	}

}
