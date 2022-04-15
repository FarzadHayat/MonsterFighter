package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.*;

class InventoryTest {
	
	private GameEnvironment game;
	private Inventory inventory;

	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		inventory = game.getInventory();
	}
	
	
	@Test
	public void testAddMonster1() throws InventoryFullException {
		// Blue sky
		Monster testMonster = new Chunky("Chunky", "Description", 100, 10, 10, 0, 10, 0.1);
		inventory.addMonster(testMonster);
		ArrayList<Monster> testMonsterList = new ArrayList<Monster>();
		testMonsterList.add(testMonster);
		assertEquals(testMonsterList, inventory.getMyMonsters());
	}
	
	
	@Test
	public void testAddMonster2() throws InventoryFullException {
		// Inventory full
		Monster testMonster = new Chunky("Chunky", "Description", 100, 10, 10, 0, 10, 0.1);
		for (int i = 0; i < 4; i++) {			
			inventory.addMonster(testMonster);
		}
		try {    		
			inventory.addMonster(testMonster);
		}
		catch (InventoryFullException e){
			assertEquals(e.getMessage(), "Inventory full!");
		}
	}

	
	@Test
	public void testRemoveMonster1() throws PurchasableNotFoundException, InventoryFullException {
		// Blue sky
		Monster testMonster = new Chunky("Chunky", "Description", 100, 10, 10, 0, 10, 0.1);
		inventory.addMonster(testMonster);
		inventory.removeMonster(testMonster);
		ArrayList<Monster> testMonsterList = new ArrayList<Monster>();
		assertEquals(testMonsterList, game.getInventory().getMyMonsters());
	}
	
	
	@Test
	public void testRemoveMonster2() throws PurchasableNotFoundException, InventoryFullException {
		// Multiple items of the same type
		Monster testMonster1 = new Chunky("Chunky 1", "Description", 100, 10, 10, 0, 10, 0.1);
		Monster testMonster2 = new Chunky("Chunky 2", "Description", 100, 10, 10, 0, 10, 0.1);
		inventory.addMonster(testMonster1);
		inventory.addMonster(testMonster2);
		inventory.addMonster(testMonster2);
		inventory.removeMonster(testMonster2);
		inventory.removeMonster(testMonster1);
		ArrayList<Monster> testMonsterList = new ArrayList<Monster>();
		testMonsterList.add(testMonster2);
		assertEquals(testMonsterList, game.getInventory().getMyMonsters());
	}
	
	
	@Test
	public void testRemoveMonster3() throws PurchasableNotFoundException, InventoryFullException {
		// Purchasable not found in inventory
		Monster testMonster1 = new Chunky("Chunky 1", "Description", 100, 10, 10, 0, 10, 0.1);
		Monster testMonster2 = new Chunky("Chunky 2", "Description", 100, 10, 10, 0, 10, 0.1);
		inventory.addMonster(testMonster2);
		try {    		
			inventory.removeMonster(testMonster1);
		}
		catch (PurchasableNotFoundException e){
			assertEquals(e.getMessage(), "Purchasable not found in inventory!");
		}
	}

	
	@Test
	public void testAddItem1() throws InventoryFullException {
		// Blue sky
		Item testItem = new IncreaseHealth("Health Potion", "Heals the Monster upon usage", 10, game);
		inventory.addItem(testItem);
		ArrayList<Item> testItemList = new ArrayList<Item>();
		testItemList.add(testItem);
		assertEquals(testItemList, inventory.getMyItems());
	}
	
	
	@Test
	public void testAddItem2() throws InventoryFullException {
		// Inventory full
		Item testItem = new IncreaseHealth("Health Potion", "Heals the Monster upon usage", 10, game);
		for (int i = 0; i < 4; i++) {			
			inventory.addItem(testItem);
		}
		try {    		
			inventory.addItem(testItem);
		}
		catch (InventoryFullException e){
			assertEquals(e.getMessage(), "Inventory full!");
		}
	}

	
	@Test
	public void testRemoveItem1() throws PurchasableNotFoundException, InventoryFullException {
		// Blue sky
		Item testItem = new IncreaseHealth("Health Potion", "Heals the Monster upon usage", 10, game);
		inventory.addItem(testItem);
		inventory.removeItem(testItem);
		ArrayList<Item> testItemList = new ArrayList<Item>();
		assertEquals(testItemList, game.getInventory().getMyItems());
	}
	
	
	@Test
	public void testRemoveItem2() throws PurchasableNotFoundException, InventoryFullException {
		// Multiple items of the same type
		Item testItem1 = new IncreaseHealth("Health Potion 1", "Heals the Monster upon usage", 10, game);
		Item testItem2 = new IncreaseHealth("Health Potion 2", "Heals the Monster upon usage", 10, game);
		inventory.addItem(testItem1);
		inventory.addItem(testItem2);
		inventory.addItem(testItem2);
		inventory.removeItem(testItem2);
		inventory.removeItem(testItem1);
		ArrayList<Item> testItemList = new ArrayList<Item>();
		testItemList.add(testItem2);
		assertEquals(testItemList, game.getInventory().getMyItems());
	}
	
	
	@Test
	public void testRemoveItem3() throws PurchasableNotFoundException, InventoryFullException {
		// Purchasable not found in inventory
		Item testItem1 = new IncreaseHealth("Health Potion", "Heals the Monster upon usage", 10, game);
		Item testItem2 = new IncreaseHealth("Health Potion", "Heals the Monster upon usage", 10, game);
		inventory.addItem(testItem2);
		try {    		
			inventory.removeItem(testItem1);
		}
		catch (PurchasableNotFoundException e){
			assertEquals(e.getMessage(), "Purchasable not found in inventory!");
		}
	}

	
	@Test
	void testMonstersFull() throws InventoryFullException {
		Monster testMonster = new Chunky("Chunky", "Description", 100, 10, 10, 0, 10, 0.1);
		assertFalse(inventory.monstersFull());
		inventory.addMonster(testMonster);
		inventory.addMonster(testMonster);
		inventory.addMonster(testMonster);
		assertFalse(inventory.monstersFull());
		inventory.addMonster(testMonster);
		assertTrue(inventory.monstersFull());
	}

	
	@Test
	void testItemsFull() throws InventoryFullException {
		Item testItem = new IncreaseHealth("Health Potion", "Heals the Monster upon usage", 10, game);
		assertFalse(inventory.itemsFull());
		inventory.addItem(testItem);
		inventory.addItem(testItem);
		inventory.addItem(testItem);
		assertFalse(inventory.itemsFull());
		inventory.addItem(testItem);
		assertTrue(inventory.itemsFull());
	}

}
