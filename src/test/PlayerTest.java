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

/**
 * Unit test for Player class
 * @author Farzad and Daniel
 */

class PlayerTest {

	/**
	 * Fields
	 */
	private GameEnvironment game;
	private Player player;

	/**
	 * Assign values to fields used in unit test
	 * @throws Exception if any exception is caught
	 */
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		player = game.getPlayer();
	}
	
	/**
	 * Set player's balance 
	 * @result player's balance is set to given value without any errors, 
	 * exception is thrown if given value is negative 
	 * @throws InvalidValueException if given value is negative 
	 */
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

	/**
	 * Set name of player with various types of names 
	 * @result sets name without any errors unless name does not meet criteria 
	 * @throws InvalidValueException if given name is invalid
	 */
	@Test
	void testSetName() throws InvalidValueException {
		// Blue sky
		player.setName("john");
		assertEquals("john", player.getName());
		
		player.setName("ABC   Abc   abc");
		assertEquals("ABC   Abc   abc", player.getName());
		
		player.setName("  whitespace               ");
		assertEquals("whitespace", player.getName());
		
		// Invalid name error
		String[] testNames = {"aj", "", "Abc   Abc   AbcZ", "123", "Mikey5x", "!WoW", "oops_"};
		for (String name : testNames) {			
			try {			
				player.setName(name);
			}
			catch (InvalidValueException e){
				assertEquals("Invalid player name! Try again:", e.getMessage());
			}
		}
	}

	/**
	 * Add amount to player's balance
	 * @result player's balance is set to given value, exception is thrown if value is negative 
	 * @throws InvalidValueException if given value is negative 
	 */
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
	
	/**
	 * Reduce amount from player's balance 
	 * @result amount is reduced from balance, exception is thrown when given value is negative
	 * or player has insufficient funds 
	 * @throws InsufficientFundsException if player has insufficient funds 
	 * @throws InvalidValueException if given value is negative 
	 */
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
	
	/**
	 * Checks if player's balance is sufficient 
	 * @result player's balance is sufficient, exception is thrown if player's balance is negative 
	 * @throws InvalidValueException if given value is negative 
	 */
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
	
	/**
	 * Checks string representation when viewing monsters
	 * @result correct string is returned without any errors 
	 * @throws InventoryFullException if inventory is already full
	 */
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
    
	/**
	 * Checks string representation when viewing items 
	 * @result correct string is returned without any errors 
	 * @throws InventoryFullException if inventory is already full
	 */
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
