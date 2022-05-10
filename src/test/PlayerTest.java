package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InsufficientFundsException;
import exceptions.InvalidValueException;
import main.*;

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
		player.setBalance(0);
		assertEquals(0, player.getBalance());
		
		player.setBalance(100);
		assertEquals(100, player.getBalance());
		
		try {
			player.setBalance(-5);
		}
		catch (InvalidValueException e) {
			assertEquals(e.getMessage(), "Balance cannot be a negative value!");
		}
	}

	
	@Test
	void testSetName() throws InvalidValueException {
		player.SetName("max");
		assertEquals("max", player.getName());
		
		player.SetName("ABC   Abc   abc");
		assertEquals("ABC   Abc   abc", player.getName());
		
		player.SetName("  whitespace               ");
		assertEquals("whitespace", player.getName());
		
		String[] testNames = {"aj", "", "Abc   Abc   AbcZ", "123", "Mikey5x", "!WoW", "oops_"};
		for (String name : testNames) {			
			try {			
				player.SetName(name);
			}
			catch (InvalidValueException e){
				assertEquals(e.getMessage(), "Invalid player name! Try again:");
			}
		}
	}


	@Test
	void testAddBalance() throws InvalidValueException {
		player.setBalance(0);
		player.addBalance(10);
		assertEquals(player.getBalance(), 10);
		player.addBalance(50);
		assertEquals(player.getBalance(), 60);
		player.addBalance(0);
		assertEquals(player.getBalance(), 60);
		
		try {
			player.addBalance(-5);
		}
		catch (InvalidValueException e) {
			assertEquals(e.getMessage(), "Cannot be a negative value!");
		}
	}
	

	@Test
	void testMinusBalance() throws InsufficientFundsException, InvalidValueException {
		player.minusBalance(10);
		assertEquals(player.getBalance(), 90);
		player.minusBalance(90);
		assertEquals(player.getBalance(), 0);
		
		try {
			player.setBalance(10);
			player.minusBalance(-5);
		}
		catch (InvalidValueException e) {
			assertEquals(e.getMessage(), "Cannot be a negative value!");
		}
		
		try {
			player.minusBalance(20);
		}
		catch (InsufficientFundsException e) {
			assertEquals(e.getMessage(), "Insufficient funds!");
		}
		
		player.setBalance(0);
		try {
			player.minusBalance(10);
		}
		catch (InsufficientFundsException e) {
			assertEquals(e.getMessage(), "Insufficient funds!");
		}
	}
	

	@Test
	void testBalanceSufficient() throws InvalidValueException {
		assertTrue(player.balanceSufficient(0));
		assertTrue(player.balanceSufficient(50));
		assertTrue(player.balanceSufficient(100));
		
		assertFalse(player.balanceSufficient(101));
		player.setBalance(0);
		assertTrue(player.balanceSufficient(0));
		assertFalse(player.balanceSufficient(1));
		
		try {
			player.balanceSufficient(-5);
		}
		catch (InvalidValueException e) {
			assertEquals(e.getMessage(), "Cannot be a negative value!");
		}
	}

}
