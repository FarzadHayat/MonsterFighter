package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InsufficientFundsException;
import exceptions.InvalidValueException;
import exceptions.InventoryFullException;
import items.HealthPotion;
import main.*;

/**
 * Unit test for Item class
 * @author Farzad and Daniel
 */

class ItemTest {
	
	/**
	 * Fields
	 */
	private GameEnvironment game = GameEnvironment.getInstance();
	private ItemInventory myItems;
	private Player player;
	private Shop shop;
	
	/**
	 * Assign values to fields used in unit test
	 * @throws Exception if any exception is caught
	 */
	@BeforeEach
	void setUp() throws Exception {
		game.populateGame();
		player = game.getPlayer();
		player.setItems(new ItemInventory(4));
		myItems = player.getItems();
		game.setShop(new Shop());
		shop = game.getShop();
	}

	/**
	 * Item is bought
	 * @result item is bought without any errors and player's balance is reduced 
	 * @throws InsufficientFundsException if player has insufficient funds 
	 * @throws InventoryFullException if inventory is already full
	 * @throws InvalidValueException if given balance value is negative
	 */
	@Test
	public void testBuy1() throws InsufficientFundsException, InventoryFullException, InvalidValueException {
		// Blue sky
		Item testItem = new HealthPotion();
		player.setBalance(testItem.getCost());
		shop.getItems().add(testItem);
		testItem.buy();
		ArrayList<Item> testItemList = new ArrayList<Item>();
		testItemList.add(testItem);
		assertEquals(0, player.getBalance());
		assertEquals(testItemList, myItems);
	}
	
	/**
	 * Item is bought, player has insufficient funds 
	 * @result exception is thrown 
	 * @throws InsufficientFundsException if player has insufficient funds
	 * @throws InventoryFullException if inventory is already full
	 * @throws InvalidValueException if given balance value is negative
	 */
	@Test
	public void testBuy2() throws InsufficientFundsException, InventoryFullException, InvalidValueException {
		// Insufficient funds
		Item testItem = new HealthPotion();
		player.setBalance(testItem.getCost() / 2);
		try {    		
			testItem.buy();
		}
		catch (InsufficientFundsException e){
			assertEquals("Insufficient funds!", e.getMessage());
		}
	}
	
	/**
	 * Item is bought, player has full inventory
	 * @result exception is thrown 
	 * @throws InsufficientFundsException if player has insufficient funds
	 * @throws InventoryFullException if inventory is already full
	 * @throws InvalidValueException if given balance value is negative
	 */
	@Test
	public void testBuy3() throws InsufficientFundsException, InventoryFullException, InvalidValueException {
		// Inventory full	
		Item testItem = new HealthPotion();
		player.setBalance(testItem.getCost() * (myItems.getMaxSize() + 1));
		
		for (int i = 0; i < myItems.getMaxSize(); i++) {
			shop.getItems().add(testItem);
			testItem.buy();
		}
		try {
			shop.getItems().add(testItem);
			testItem.buy();
		}
		catch (InventoryFullException e){
			assertEquals("Item inventory is full!", e.getMessage());
		}
	}
	
	/**
	 * Item is sold
	 * @result item is sold without any errors and player's balance is increased 
	 * @throws InsufficientFundsException if player has insufficient funds
	 * @throws InventoryFullException if inventory is already full
	 * @throws InvalidValueException if given balance value is negative
	 */
	@Test
	public void testSell1() throws InventoryFullException, InsufficientFundsException, InvalidValueException {
		// Blue sky
		Item testItem = new HealthPotion();
		player.setBalance(testItem.getCost());
		shop.getItems().add(testItem);
		testItem.buy();
		testItem.sell();
		ArrayList<Item> testItemList = new ArrayList<Item>();
		assertEquals(testItem.getCost() * testItem.getRefundAmount(), player.getBalance());
		assertEquals(testItemList, myItems);
	}
	
	/**
	 * Item is sold, player has multiple of the same item
	 * @result items are sold without any errors and player's balance is increased 
	 * @throws InsufficientFundsException if player has insufficient funds
	 * @throws InventoryFullException if inventory is already full
	 * @throws InvalidValueException if given balance value is negative
	 */
	@Test
	public void testSell2() throws InventoryFullException, InsufficientFundsException, InvalidValueException {
		// Multiple items of the same type
		Item testItem1 = new HealthPotion();
		Item testItem2 = new HealthPotion();
		player.setBalance(testItem1.getCost() * 3);
		shop.getItems().add(testItem1);
		shop.getItems().add(testItem2);
		shop.getItems().add(testItem2);
		testItem1.buy();
		testItem2.buy();
		testItem2.buy();
		testItem2.sell();
		testItem1.sell();
		ArrayList<Item> testItemList = new ArrayList<Item>();
		testItemList.add(testItem2);
		assertEquals(testItem1.getCost(), player.getBalance());
		assertEquals(testItemList, myItems);
	}
	
	/**
	 * Checks string representation of item
	 * @result correct string is returned without any errors 
	 */
	@Test
	public void testToString() {
		Item testItem = new HealthPotion();
		String myStr = testItem.toString();
		assertEquals("%-20s    cost: %-3s    %-50s".formatted(testItem.getName(), testItem.getCost(), testItem.getDescription()), myStr);
	}
	
	/**
	 * Checks string representation of view option
	 * @result correct string is returned without any errors
	 */
	@Test
	public void testView() {
		Item testItem = new HealthPotion();
		String myStr = testItem.view();
		
		String result = "";
    	if (game.getShop().getItems().contains(testItem)) {
    		result += String.format("\nBalance: %s\n\n", player.getBalance());
    	}
    	result += "Item: " + testItem.getName() + "\n";
    	result += testItem.getDescription() + "\n";
    	result += "Cost: " + testItem.getCost() + "\n";
    	if (player.getItems().contains(testItem)) {
    		result += "\n1: Use";
    		result += "\n2: Sell";
    		result += "\n3: Go back";
    	}
    	if (game.getShop().getItems().contains(testItem)) {    		
    		result += "\n1: Buy";
    		result += "\n2: Go back";
    	}
    	assertEquals(result, myStr);
	}

}