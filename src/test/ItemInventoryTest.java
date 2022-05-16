package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InventoryFullException;
import main.*;
import items.HealUp;

class ItemInventoryTest {
	
	private GameEnvironment game;
	private ItemInventory myItems;

	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		game.setupGame();
		myItems = game.getPlayer().getItems();
	}
	
	
	@Test
	public void testAdd1() throws InventoryFullException {
		// Blue sky
		Item testItem = new HealUp(game);
		myItems.add(testItem);
		ArrayList<Item> testItemList = new ArrayList<Item>();
		testItemList.add(testItem);
		assertEquals(testItemList, myItems.getList());
	}
	
	
	@Test
	public void testAdd2() throws InventoryFullException {
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
	public void testAdd3() throws InventoryFullException {
		// Add at index
		Item testItem1 = new HealUp(game);
		Item testItem2 = new HealUp(game);
		Item testItem3 = new HealUp(game);
		Item testItem4 = new HealUp(game);
		myItems.add(testItem1);
		myItems.add(0, testItem2);
		myItems.add(1, testItem3);
		myItems.add(3, testItem4);
		ArrayList<Item> testItemList = new ArrayList<Item>();
		testItemList.add(testItem2);
		testItemList.add(testItem3);
		testItemList.add(testItem1);
		testItemList.add(testItem4);
		assertEquals(testItemList, myItems.getList());
	}

	
	@Test
	public void testRemove1() throws InventoryFullException {
		// Blue sky
		Item testItem = new HealUp(game);
		myItems.add(testItem);
		myItems.remove(testItem);
		ArrayList<Item> testItemList = new ArrayList<Item>();
		assertEquals(testItemList, myItems.getList());
	}
	
	
	@Test
	public void testRemove2() throws InventoryFullException {
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
	public void testIsFull() throws InventoryFullException {
		// Blue sky
		Item testItem = new HealUp(game);
		assertFalse(myItems.isFull());
		myItems.add(testItem);
		myItems.add(testItem);
		myItems.add(testItem);
		assertFalse(myItems.isFull());
		myItems.add(testItem);
		assertTrue(myItems.isFull());
	}
	
	
	@Test
	public void testIsEmpty() throws InventoryFullException {
		// Blue sky
		Item testItem = new HealUp(game);
		assertTrue(myItems.isEmpty());
		myItems.add(testItem);
		assertFalse(myItems.isEmpty());
	}
	
	
	@Test
	public void testRandom() throws InventoryFullException {
		Item testItem1 = new HealUp(game);
		myItems.add(testItem1);
		Item testItem2 = new HealUp(game);
		myItems.add(testItem2);
		
		Item randomItem = myItems.random();
		assertTrue(myItems.getList().contains(randomItem));
	}
	
	
	@Test
	public void testToString() throws InventoryFullException {
		Item testItem1 = new HealUp(game);
		myItems.add(testItem1);
		Item testItem2 = new HealUp(game);
		myItems.add(testItem2);
		
		String result = "";
		for (Item item : myItems.getList())
		{
			result += "\n" + item;
		}
		
		assertEquals(myItems.toString(), result);
	}
	
	
	@Test
	public void testView() throws InventoryFullException {
		Item testItem1 = new HealUp(game);
		myItems.add(testItem1);
		Item testItem2 = new HealUp(game);
		myItems.add(testItem2);
		
		String result = "";
		int start = 1;
    	for (Item item : myItems.getList()) {
    		result += String.format("%s: %s\n", start, item);
    		start++;
    	}
    	
    	assertEquals(myItems.view(), result);
	}

}
