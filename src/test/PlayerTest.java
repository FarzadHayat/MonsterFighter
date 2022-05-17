package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InsufficientFundsException;
import exceptions.InvalidValueException;
import exceptions.InventoryFullException;
import items.HealUp;
import main.*;
import monsters.Chunky;

class PlayerTest {

	private GameEnvironment game;
	private Player player;

	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		player = game.getPlayer();
	}


	@Test
	void testSetBalance() throws InvalidValueException {
		// Blue sky
		player.setBalance(0);
		assertEquals(0, player.getBalance());
		
		player.setBalance(100);
		assertEquals(100, player.getBalance());
		
		// Negative value error
		try {
			player.setBalance(-5);
		}
		catch (InvalidValueException e) {
			assertEquals("Balance cannot be a negative value!", e.getMessage());
		}
	}

	
	@Test
	void testSetName() throws InvalidValueException {
		// Blue sky
		player.SetName("john");
		assertEquals("john", player.getName());
		
		player.SetName("ABC   Abc   abc");
		assertEquals("ABC   Abc   abc", player.getName());
		
		player.SetName("  whitespace               ");
		assertEquals("whitespace", player.getName());
		
		// Invalid name error
		String[] testNames = {"aj", "", "Abc   Abc   AbcZ", "123", "Mikey5x", "!WoW", "oops_"};
		for (String name : testNames) {			
			try {			
				player.SetName(name);
			}
			catch (InvalidValueException e){
				assertEquals("Invalid player name! Try again:", e.getMessage());
			}
		}
	}


	@Test
	void testAddBalance() throws InvalidValueException {
		// Blue sky
		player.setBalance(0);
		player.addBalance(10);
		assertEquals(player.getBalance(), 10);
		player.addBalance(50);
		assertEquals(player.getBalance(), 60);
		player.addBalance(0);
		assertEquals(player.getBalance(), 60);
		
		// Negative value error
		try {
			player.addBalance(-5);
		}
		catch (InvalidValueException e) {
			assertEquals("Cannot be a negative value!", e.getMessage());
		}
	}
	

	@Test
	void testMinusBalance() throws InsufficientFundsException, InvalidValueException {
		// Blue sky
		player.minusBalance(10);
		assertEquals(player.getBalance(), 90);
		player.minusBalance(90);
		assertEquals(player.getBalance(), 0);
		
		// Negative value error
		try {
			player.setBalance(10);
			player.minusBalance(-5);
		}
		catch (InvalidValueException e) {
			assertEquals("Cannot be a negative value!", e.getMessage());
		}
		
		// Insufficient funds error
		try {
			player.minusBalance(20);
		}
		catch (InsufficientFundsException e) {
			assertEquals("Insufficient funds!", e.getMessage());
		}
		
		player.setBalance(0);
		try {
			player.minusBalance(10);
		}
		catch (InsufficientFundsException e) {
			assertEquals("Insufficient funds!", e.getMessage());
		}
	}
	

	@Test
	void testBalanceSufficient() throws InvalidValueException {
		// Blue sky
		assertTrue(player.balanceSufficient(0));
		assertTrue(player.balanceSufficient(50));
		assertTrue(player.balanceSufficient(100));
		
		assertFalse(player.balanceSufficient(101));
		player.setBalance(0);
		assertTrue(player.balanceSufficient(0));
		assertFalse(player.balanceSufficient(1));
		
		// Negative value error
		try {
			player.balanceSufficient(-5);
		}
		catch (InvalidValueException e) {
			assertEquals("Cannot be a negative value!", e.getMessage());
		}
	}
	
	
	@Test
	public void testViewMonsters() throws InventoryFullException {
		// Blue sky
		Monster testMonster1 = new Chunky(game);
		player.getMonsters().add(testMonster1);
		Monster testMonster2 = new Chunky(game);
		player.getMonsters().add(testMonster2);
		
    	String result = "\n===== MY TEAM =====\n\n";
    	result += player.getMonsters().view();
    	result += String.format("\n%s: Go back", player.getMonsters().getList().size() + 1);
    	
    	assertEquals(result, player.viewMonsters());
    }
    
    
	@Test
    public void testViewItems() throws InventoryFullException {
		// Blue sky
		Item testItem1 = new HealUp(game);
		player.getItems().add(testItem1);
		Item testItem2 = new HealUp(game);
		player.getItems().add(testItem2);
		
    	String result = "\n===== MY INVENTORY =====\n\n";
    	result += player.getItems().view();
    	result += String.format("\n%s: Go back", player.getItems().getList().size() + 1);

    	assertEquals(result, player.viewItems());
    }

}
