package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.*;

class ItemTest {
	
	private GameEnvironment game;
	private ItemInventory myItems;
	private Player player;

	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		player = game.getPlayer();
		myItems = player.getItems();
	}

	
	@Test
	public void testBuy1() throws InsufficientFundsException, InventoryFullException, NotFoundException, InvalidValueException {
		// Blue sky
		Item testItem = new HealUp(game);
		player.setBalance(testItem.getCost());
		testItem.buy();
		ArrayList<Item> testItemList = new ArrayList<Item>();
		testItemList.add(testItem);
		assertEquals(0, player.getBalance());
		assertEquals(testItemList, myItems.getList());
	}
	
	
	@Test
	public void testBuy2() throws InsufficientFundsException, InventoryFullException, NotFoundException, InvalidValueException {
		// Insufficient funds
		Item testItem = new HealUp(game);
		player.setBalance(testItem.getCost() / 2);
		try {    		
			testItem.buy();
		}
		catch (InsufficientFundsException e){
			assertEquals(e.getMessage(), "Insufficient funds!");
		}
	}
	
	
	@Test
	public void testBuy3() throws InsufficientFundsException, InventoryFullException, NotFoundException, InvalidValueException {
		// Inventory full	
		Item testItem = new HealUp(game);
		player.setBalance(testItem.getCost() * (myItems.getMaxSize() + 1));
		for (int i = 0; i < myItems.getMaxSize(); i++) {			
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
	public void testSell1() throws NotFoundException, InventoryFullException, InsufficientFundsException, InvalidValueException {
		// Blue sky
		Item testItem = new HealUp(game);
		player.setBalance(testItem.getCost());
		testItem.buy();
		testItem.sell();
		ArrayList<Item> testItemList = new ArrayList<Item>();
		assertEquals(testItem.getCost() * testItem.getRefundAmount(), player.getBalance());
		assertEquals(testItemList, myItems.getList());
	}
	
	
	@Test
	public void testSell2() throws NotFoundException, InventoryFullException, InsufficientFundsException, InvalidValueException {
		// Multiple items of the same type
		Item testItem1 = new HealUp(game);
		Item testItem2 = new HealUp(game);
		player.setBalance(testItem1.getCost() * 3);
		testItem1.buy();
		testItem2.buy();
		testItem2.buy();
		testItem2.sell();
		testItem1.sell();
		ArrayList<Item> testItemList = new ArrayList<Item>();
		testItemList.add(testItem2);
		assertEquals(testItem1.getCost(), player.getBalance());
		assertEquals(testItemList, myItems.getList());
	}
	
	
	@Test
	public void testSell3() throws NotFoundException, InsufficientFundsException, InventoryFullException, InvalidValueException {
		// Purchasable not found in inventory
		Item testItem1 = new HealUp(game);
		Item testItem2 = new HealUp(game);
		player.setBalance(testItem1.getCost());
		testItem1.buy();
		try {    		
			testItem2.sell();
		}
		catch (NotFoundException e){
			assertEquals(e.getMessage(), "Item not found in inventory!");
		}
	}

}