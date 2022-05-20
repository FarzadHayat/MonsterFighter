package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InventoryFullException;
import main.*;
import items.HealthPotion;

/**
 * Unit test for ItemInventory class
 * @author Farzad and Daniel
 */

class ItemInventoryTest {
	
	/**
	 * Fields
	 */
	private GameEnvironment game;
	private ItemInventory myItems;

	/**
	 * Assign values to fields used in unit test
	 * @throws Exception if any exception is caught
	 */
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		game.setupGame();
		myItems = game.getPlayer().getItems();
	}
	
	/**
	 * Add item to item inventory
	 * @result item is added to inventory without any errors
	 * @throws InventoryFullException if inventory is already full
	 */
	@Test
	public void testAdd1() throws InventoryFullException {
		// Blue sky
		Item testItem = new HealthPotion(game);
		myItems.add(testItem);
		ArrayList<Item> testItemList = new ArrayList<Item>();
		testItemList.add(testItem);
		assertEquals(testItemList, myItems);
	}

	
	/**
	 * Add items to specific indexes
	 * @result items are added to specific indexes without any errors
	 * @throws InventoryFullException if inventory is already full
	 */
	@Test
	public void testAdd2() throws InventoryFullException {
		// Add at index
		Item testItem1 = new HealthPotion(game);
		Item testItem2 = new HealthPotion(game);
		Item testItem3 = new HealthPotion(game);
		Item testItem4 = new HealthPotion(game);
		myItems.add(testItem1);
		myItems.add(0, testItem2);
		myItems.add(1, testItem3);
		myItems.add(3, testItem4);
		ArrayList<Item> testItemList = new ArrayList<Item>();
		testItemList.add(testItem2);
		testItemList.add(testItem3);
		testItemList.add(testItem1);
		testItemList.add(testItem4);
		assertEquals(testItemList, myItems);
	}

	/**
	 * Remove item from inventory
	 * @result item is removed without any errors
	 * @throws InventoryFullException if inventory is already full
	 */
	@Test
	public void testRemove1() throws InventoryFullException {
		// Blue sky
		Item testItem = new HealthPotion(game);
		myItems.add(testItem);
		myItems.remove(testItem);
		ArrayList<Item> testItemList = new ArrayList<Item>();
		assertEquals(testItemList, myItems);
	}
	
	/**
	 * Remove one instance of a item from the item inventory
	 * @result removes one instance without any errors
	 * @throws InventoryFullException if inventory is already full
	 */
	@Test
	public void testRemove2() throws InventoryFullException {
		// Multiple items of the same type
		Item testItem1 = new HealthPotion(game);
		Item testItem2 = new HealthPotion(game);
		myItems.add(testItem1);
		myItems.add(testItem2);
		myItems.add(testItem2);
		myItems.remove(testItem2);
		myItems.remove(testItem1);
		ArrayList<Item> testItemList = new ArrayList<Item>();
		testItemList.add(testItem2);
		assertEquals(testItemList, myItems);
	}

	/**
	 * Checks if item inventory is full
	 * @result item inventory is full when there are 4 items
	 * @throws InventoryFullException if inventory is already full
	 */
	@Test
	public void testIsFull() throws InventoryFullException {
		// Blue sky
		Item testItem = new HealthPotion(game);
		assertFalse(myItems.isFull());
		myItems.add(testItem);
		myItems.add(testItem);
		myItems.add(testItem);
		assertFalse(myItems.isFull());
		myItems.add(testItem);
		assertTrue(myItems.isFull());
	}
	
	/**
	 * Checks if item inventory is empty
	 * @result item inventory is empty when there are no items 
	 * @throws InventoryFullException if inventory is already full
	 */
	@Test
	public void testIsEmpty() throws InventoryFullException {
		// Blue sky
		Item testItem = new HealthPotion(game);
		assertTrue(myItems.isEmpty());
		myItems.add(testItem);
		assertFalse(myItems.isEmpty());
	}
	
	/**
	 * Get random item from item inventory
	 * @result random item from inventory is returned
	 * @throws InventoryFullException if inventory is already full
	 */
	@Test
	public void testRandom() throws InventoryFullException {
		Item testItem1 = new HealthPotion(game);
		myItems.add(testItem1);
		Item testItem2 = new HealthPotion(game);
		myItems.add(testItem2);
		
		Item randomItem = myItems.random();
		assertTrue(myItems.contains(randomItem));
	}
	
	/**
	 * Checks string representation of item inventory
	 * @result correct string is returned without any errors 
	 * @throws InventoryFullException if inventory is already full
	 */
	@Test
	public void testToString() throws InventoryFullException {
		Item testItem1 = new HealthPotion(game);
		myItems.add(testItem1);
		Item testItem2 = new HealthPotion(game);
		myItems.add(testItem2);
		
		String result = "";
		for (Item item : myItems)
		{
			result += "\n" + item;
		}
		
		assertEquals(result, myItems.toString());
	}
	
	/**
	 * Checks string representation of view option
	 * @result correct string is returned without any errors
	 */
	@Test
	public void testView() throws InventoryFullException {
		Item testItem1 = new HealthPotion(game);
		myItems.add(testItem1);
		Item testItem2 = new HealthPotion(game);
		myItems.add(testItem2);
		
		String result = "";
		int start = 1;
    	for (Item item : myItems) {
    		result += String.format("%s: %s\n", start, item);
    		start++;
    	}
    	
    	assertEquals(result, myItems.view());
	}

}
