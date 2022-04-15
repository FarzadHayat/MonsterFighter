package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.*;

class ItemTest {
	
	private GameEnvironment game;
	
	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
	}

	
	@Test
	public void buyTest1() throws InsufficientFundsException, InventoryFullException {
		// Blue sky
		game.setBalance(10);
		Item testItem = new IncreaseHealth("Health Potion", "Heals the Monster upon usage", 10, game);
		testItem.buy();
		ArrayList<Item> testItemList = new ArrayList<Item>();
		testItemList.add(testItem);
		assertEquals(0, game.getBalance());
		assertEquals(testItemList, game.getInventory().getMyItems());
	}
	
	
	@Test
	public void buyTest2() throws InsufficientFundsException, InventoryFullException {
		// Insufficient funds
		game.setBalance(5);
		Item testItem = new IncreaseHealth("Health Potion", "Heals the Monster upon usage", 10, game);
		try {    		
			testItem.buy();
		}
		catch (InsufficientFundsException e){
			assertEquals(e.getMessage(), "Insufficient funds!");
		}
	}
	
	
	@Test
	public void buyTest3() throws InsufficientFundsException, InventoryFullException {
		// Inventory full	
		game.setBalance(50);
		Item testItem = new IncreaseHealth("Health Potion", "Heals the Monster upon usage", 10, game);
		for (int i = 0; i < 4; i++) {			
			testItem.buy();
		}
		try {    		
			testItem.buy();
		}
		catch (InventoryFullException e){
			assertEquals(e.getMessage(), "Inventory full!");
		}
	}
	
	
	@Test
	public void sellTest1() throws PurchasableNotFoundException, InventoryFullException, InsufficientFundsException {
		// Blue sky
		game.setBalance(5);
		Item testItem = new IncreaseHealth("Health Potion", "Heals the Monster upon usage", 5, game);
		testItem.buy();
		testItem.sell();
		ArrayList<Item> testItemList = new ArrayList<Item>();
		assertEquals(2.5, game.getBalance());
		assertEquals(testItemList, game.getInventory().getMyItems());
	}
	
	
	@Test
	public void sellTest2() throws PurchasableNotFoundException, InventoryFullException, InsufficientFundsException {
		// Multiple items of the same type
		game.setBalance(15);
		Item testItem1 = new IncreaseHealth("Health Potion 1", "Heals the Monster upon usage", 5, game);
		Item testItem2 = new IncreaseHealth("Health Potion 2", "Heals the Monster upon usage", 5, game);
		testItem1.buy();
		testItem2.buy();
		testItem2.buy();
		testItem2.sell();
		testItem1.sell();
		ArrayList<Item> testItemList = new ArrayList<Item>();
		testItemList.add(testItem2);
		assertEquals(5, game.getBalance());
		assertEquals(testItemList, game.getInventory().getMyItems());
	}
	
	
	@Test
	public void sellTest3() throws PurchasableNotFoundException {
		// Purchasable not found in inventory
		game.setBalance(50);
		Item testItem = new IncreaseHealth("Health Potion", "Heals the Monster upon usage", 10, game);
		try {    		
			testItem.sell();
		}
		catch (PurchasableNotFoundException e){
			assertEquals(e.getMessage(), "Purchasable not found in inventory!");
		}
	}

}
