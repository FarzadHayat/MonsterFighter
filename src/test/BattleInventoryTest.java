package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InventoryFullException;
import main.*;

/**
 * Unit test for BattleInventory class
 * @author Farzad and Daniel
 */

class BattleInventoryTest {
	
	/**
	 * Fields
	 */
	private GameEnvironment game;
	private BattleInventory myBattles;

	/**
	 * Assign values to fields used in unit test
	 * @throws Exception if any exception is caught
	 */
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		game.setupGame();
		game.setBattles(new BattleInventory(6, game));
		myBattles = game.getBattles();
		
	}
	
	/**
	 * Adds a battle to the battle inventory
	 * @result battle is added without any errors
	 * @throws InventoryFullException if battle inventory is already full
	 */
	@Test
	public void testAdd1() throws InventoryFullException {
		Battle testBattle = new Battle(game);
		myBattles.add(testBattle);
		ArrayList<Battle> testBattleList = new ArrayList<Battle>();
		testBattleList.add(testBattle);
		assertEquals(testBattleList, myBattles.getList());
	}
	
	/**
	 * Adds battle to full battle inventory
	 * @result battle is not added and exception is thrown
	 * @throws InventoryFullException if battle inventory is already full
	 */
	@Test
	public void testAdd2() throws InventoryFullException {
		Battle testBattle = new Battle(game);
		for (int i = 0; i < myBattles.getMaxSize(); i++) {			
			myBattles.add(testBattle);
		}
		try {    		
			myBattles.add(testBattle);
		}
		catch (InventoryFullException e){
			assertEquals("Battle inventory is full!", e.getMessage());
		}
	}
	
	/**
	 * Add battles to specific indexes
	 * @result battles are added to specific indexes without any errors
	 * @throws InventoryFullException if battle inventory is already full
	 */
	@Test
	public void testAdd3() throws InventoryFullException {
		
		Battle testBattle1 = new Battle(game);
		Battle testBattle2 = new Battle(game);
		Battle testBattle3 = new Battle(game);
		Battle testBattle4 = new Battle(game);
		
		myBattles.add(testBattle1);
		myBattles.add(0, testBattle2);
		myBattles.add(1, testBattle3);
		myBattles.add(3, testBattle4);
		
		ArrayList<Battle> testBattleList = new ArrayList<Battle>();
		
		testBattleList.add(testBattle2);
		testBattleList.add(testBattle3);
		testBattleList.add(testBattle1);
		testBattleList.add(testBattle4);
		
		assertEquals(testBattleList, myBattles.getList());
	}

	/**
	 * Remove battle from battle inventory
	 * @result battle is removed without any errors
	 * @throws InventoryFullException if battle inventory is already full
	 */
	@Test
	public void testRemove1() throws InventoryFullException {
		Battle testBattle = new Battle(game);
		myBattles.add(testBattle);
		myBattles.remove(testBattle);
		ArrayList<Battle> testBattleList = new ArrayList<Battle>();
		assertEquals(testBattleList, myBattles.getList());
	}
	
	/**
	 * Remove one instance of a battle from the battle inventory
	 * @result removes one instance without any errors
	 * @throws InventoryFullException if battle inventory is already full
	 */
	@Test
	public void testRemove2() throws InventoryFullException {
		Battle testBattle1 = new Battle(game);
		Battle testBattle2 = new Battle(game);
		myBattles.add(testBattle1);
		myBattles.add(testBattle2);
		myBattles.add(testBattle2);
		myBattles.remove(testBattle2);
		myBattles.remove(testBattle1);
		ArrayList<Battle> testBattleList = new ArrayList<Battle>();
		testBattleList.add(testBattle2);
		assertEquals(testBattleList, myBattles.getList());
	}

	/**
	 * Checks if battle inventory is full
	 * @result battle inventory is full when there are 6 battles
	 * @throws InventoryFullException if battle inventory is already full
	 */
	@Test
	public void testIsFull() throws InventoryFullException {
		Battle testBattle = new Battle(game);
		assertFalse(myBattles.isFull());
		myBattles.add(testBattle);
		myBattles.add(testBattle);
		myBattles.add(testBattle);
		myBattles.add(testBattle);
		myBattles.add(testBattle);
		assertFalse(myBattles.isFull());
		myBattles.add(testBattle);
		assertTrue(myBattles.isFull());
	}
	
	/**
	 * Checks if battle inventory is empty
	 * @result battle inventory is empty when there are no battles 
	 * @throws InventoryFullException if battle inventory is already full
	 */
	@Test
	public void testIsEmpty() throws InventoryFullException {
		Battle testBattle = new Battle(game);
		assertTrue(myBattles.isEmpty());
		myBattles.add(testBattle);
		assertFalse(myBattles.isEmpty());
	}
	
	
	/**
	 * Checks string representation of battle inventory
	 * @result correct string is returned without any errors 
	 * @throws InventoryFullException if battle inventory is already full
	 */
	@Test
	public void testToString() throws InventoryFullException {
		Battle testBattle1 = new Battle(game);
		myBattles.add(testBattle1);
		Battle testBattle2 = new Battle(game);
		myBattles.add(testBattle2);
		
		String result = "";
		for (Battle battle : myBattles.getList())
		{
			result += "\n" + battle;
		}
		
		assertEquals(result, myBattles.toString());
	}
	
	/**
	 * Checks string representation of view option
	 * @result correct string is returned without any errors
	 * @throws InventoryFullException if battle inventory is already full
	 */
	@Test
	public void testView() throws InventoryFullException {
		Battle testBattle1 = new Battle(game);
		myBattles.add(testBattle1);
		Battle testBattle2 = new Battle(game);
		myBattles.add(testBattle2);
		
		String result = "\n===== BATTLES =====\n\n";
		int start = 1;
    	for (Battle battle : myBattles.getList()) {
    		result += String.format("%s: %s\n", start, battle);
    		start++;
    	}
    	result += String.format("%s: Go back", start);
    	
    	assertEquals(result, myBattles.view());
	}

}
