package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.*;

class ItemTest {
	
	private GameEnvironment game;
	private ItemInventory myItems;
	
	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		myItems = game.getMyItems();
	}

	
	@Test
	public void testBuy1() throws InsufficientFundsException, InventoryFullException {
		// Blue sky
		Item testItem = new IncreaseHealth(game);
		game.setBalance(testItem.getCost());
		testItem.buy();
		ArrayList<Item> testItemList = new ArrayList<Item>();
		testItemList.add(testItem);
		assertEquals(0, game.getBalance());
		assertEquals(testItemList, myItems.getItemList());
	}
	
	
	@Test
	public void testBuy2() throws InsufficientFundsException, InventoryFullException {
		// Insufficient funds
		Item testItem = new IncreaseHealth(game);
		game.setBalance(testItem.getCost() / 2);
		try {    		
			testItem.buy();
		}
		catch (InsufficientFundsException e){
			assertEquals(e.getMessage(), "Insufficient funds!");
		}
	}
	
	
	@Test
	public void testBuy3() throws InsufficientFundsException, InventoryFullException {
		// Inventory full	
		Item testItem = new IncreaseHealth(game);
		game.setBalance(testItem.getCost() * (myItems.getInventorySize() + 1));
		for (int i = 0; i < myItems.getInventorySize(); i++) {			
			testItem.buy();
		}
		try {    		
			testItem.buy();
		}
		catch (InventoryFullException e){
			assertEquals(e.getMessage(), "Item inventory is full!");
		}
	}
	
	
	@Test
	public void testSell1() throws PurchasableNotFoundException, InventoryFullException, InsufficientFundsException {
		// Blue sky
		Item testItem = new IncreaseHealth(game);
		game.setBalance(testItem.getCost());
		testItem.buy();
		testItem.sell();
		ArrayList<Item> testItemList = new ArrayList<Item>();
		assertEquals(testItem.getCost() * testItem.getRefundAmount(), game.getBalance());
		assertEquals(testItemList, myItems.getItemList());
	}
	
	
	@Test
	public void testSell2() throws PurchasableNotFoundException, InventoryFullException, InsufficientFundsException {
		// Multiple items of the same type
		Item testItem1 = new IncreaseHealth(game);
		Item testItem2 = new IncreaseHealth(game);
		game.setBalance(testItem1.getCost() * 3);
		testItem1.buy();
		testItem2.buy();
		testItem2.buy();
		testItem2.sell();
		testItem1.sell();
		ArrayList<Item> testItemList = new ArrayList<Item>();
		testItemList.add(testItem2);
		assertEquals(testItem1.getCost(), game.getBalance());
		assertEquals(testItemList, myItems.getItemList());
	}
	
	
	@Test
	public void testSell3() throws PurchasableNotFoundException, InsufficientFundsException, InventoryFullException {
		// Purchasable not found in inventory
		Item testItem1 = new IncreaseHealth(game);
		Item testItem2 = new IncreaseHealth(game);
		game.setBalance(testItem1.getCost());
		testItem1.buy();
		try {    		
			testItem2.sell();
		}
		catch (PurchasableNotFoundException e){
			assertEquals(e.getMessage(), "Item not found in inventory!");
		}
	}

}
