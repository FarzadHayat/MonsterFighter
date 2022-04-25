package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.*;

class ItemInventoryTest {
	
	private GameEnvironment game;
	private Inventory<Item> myItems;

	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		myItems = game.getMyItems();
	}

	
	@Test
	public void testAddItem1() throws InventoryFullException {
		// Blue sky
		Item testItem = new HealUp(game);
		myItems.add(testItem);
		ArrayList<Item> testItemList = new ArrayList<Item>();
		testItemList.add(testItem);
		assertEquals(testItemList, myItems.getList());
	}
	
	
	@Test
	public void testAddItem2() throws InventoryFullException {
		// Inventory full
		Item testItem = new HealUp(game);
		for (int i = 0; i < myItems.getMaxSize(); i++) {			
			myItems.add(testItem);
		}
		try {    		
			myItems.add(testItem);
		}
		catch (InventoryFullException e){
			assertEquals(e.getMessage(), "Item inventory is full!");
		}
	}

	
	@Test
	public void testRemoveItem1() throws StorableNotFoundException, InventoryFullException {
		// Blue sky
		Item testItem = new HealUp(game);
		myItems.add(testItem);
		myItems.remove(testItem);
		ArrayList<Item> testItemList = new ArrayList<Item>();
		assertEquals(testItemList, myItems.getList());
	}
	
	
	@Test
	public void testRemoveItem2() throws StorableNotFoundException, InventoryFullException {
		// Multiple items of the same type
		Item testItem1 = new HealUp(game);
		Item testItem2 = new HealUp(game);
		myItems.add(testItem1);
		myItems.add(testItem2);
		myItems.add(testItem2);
		myItems.remove(testItem2);
		myItems.remove(testItem1);
		ArrayList<Item> testItemList = new ArrayList<Item>();
		testItemList.add(testItem2);
		assertEquals(testItemList, myItems.getList());
	}
	
	
	@Test
	public void testRemoveItem3() throws StorableNotFoundException, InventoryFullException {
		// Storable not found in inventory
		Item testItem1 = new HealUp(game);
		Item testItem2 = new HealUp(game);
		myItems.add(testItem2);
		try {    		
			myItems.remove(testItem1);
		}
		catch (StorableNotFoundException e){
			assertEquals(e.getMessage(), "Item not found in inventory!");
		}
	}

	
	@Test
	void testItemsFull() throws InventoryFullException {
		Item testItem = new HealUp(game);
		assertFalse(myItems.isFull());
		myItems.add(testItem);
		myItems.add(testItem);
		myItems.add(testItem);
		assertFalse(myItems.isFull());
		myItems.add(testItem);
		assertTrue(myItems.isFull());
	}

}
