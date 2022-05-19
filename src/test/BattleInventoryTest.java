package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InventoryFullException;
import main.*;

class BattleInventoryTest {
	
	private GameEnvironment game;
	private BattleInventory myBattles;

	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		game.setupGame();
		game.setBattles(new BattleInventory(6, game));
		myBattles = game.getBattles();
		
	}
	
	
	@Test
	public void testAdd1() throws InventoryFullException {
		// Blue sky
		Battle testBattle = new Battle(game);
		myBattles.add(testBattle);
		ArrayList<Battle> testBattleList = new ArrayList<Battle>();
		testBattleList.add(testBattle);
		assertEquals(testBattleList, myBattles);
	}
	
	
	@Test
	public void testAdd3() throws InventoryFullException {
		// Add at index
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
		assertEquals(testBattleList, myBattles);
	}

	
	@Test
	public void testRemove1() throws InventoryFullException {
		// Blue sky
		Battle testBattle = new Battle(game);
		myBattles.add(testBattle);
		myBattles.remove(testBattle);
		ArrayList<Battle> testBattleList = new ArrayList<Battle>();
		assertEquals(testBattleList, myBattles);
	}
	
	
	@Test
	public void testRemove2() throws InventoryFullException {
		// Multiple Battles of the same type
		Battle testBattle1 = new Battle(game);
		Battle testBattle2 = new Battle(game);
		myBattles.add(testBattle1);
		myBattles.add(testBattle2);
		myBattles.add(testBattle2);
		myBattles.remove(testBattle2);
		myBattles.remove(testBattle1);
		ArrayList<Battle> testBattleList = new ArrayList<Battle>();
		testBattleList.add(testBattle2);
		assertEquals(testBattleList, myBattles);
	}

	
	@Test
	public void testIsFull() throws InventoryFullException {
		// Blue sky
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
	
	
	@Test
	public void testIsEmpty() throws InventoryFullException {
		// Blue sky
		Battle testBattle = new Battle(game);
		assertTrue(myBattles.isEmpty());
		myBattles.add(testBattle);
		assertFalse(myBattles.isEmpty());
	}
	
	
	@Test
	public void testToString() throws InventoryFullException {
		Battle testBattle1 = new Battle(game);
		myBattles.add(testBattle1);
		Battle testBattle2 = new Battle(game);
		myBattles.add(testBattle2);
		
		String result = "";
		for (Battle battle : myBattles)
		{
			result += "\n" + battle;
		}
		
		assertEquals(result, myBattles.toString());
	}
	
	
	@Test
	public void testView() throws InventoryFullException {
		Battle testBattle1 = new Battle(game);
		myBattles.add(testBattle1);
		Battle testBattle2 = new Battle(game);
		myBattles.add(testBattle2);
		
		String result = "\n===== BATTLES =====\n\n";
		int start = 1;
    	for (Battle battle : myBattles) {
    		result += String.format("%s: %s\n", start, battle);
    		start++;
    	}
    	result += String.format("%s: Go back", start);
    	
    	assertEquals(result, myBattles.view());
	}

}
