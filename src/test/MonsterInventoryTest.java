package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InvalidValueException;
import exceptions.InventoryFullException;
import main.*;
import monsters.Chunky;

class MonsterInventoryTest {
	
	private GameEnvironment game;
	private MonsterInventory myMonsters;

	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		game.setupGame();
		myMonsters = game.getPlayer().getMonsters();
	}
	
	
	@Test
	public void testAdd1() throws InventoryFullException {
		// Blue sky
		Monster testMonster = new Chunky(game);
		myMonsters.add(testMonster);
		ArrayList<Monster> testMonsterList = new ArrayList<Monster>();
		testMonsterList.add(testMonster);
		assertEquals(testMonsterList, myMonsters);
	}
	
	
	@Test
	public void testAdd3() throws InventoryFullException {
		// Add at index
		Monster testMonster1 = new Chunky(game);
		Monster testMonster2 = new Chunky(game);
		Monster testMonster3 = new Chunky(game);
		Monster testMonster4 = new Chunky(game);
		myMonsters.add(testMonster1);
		myMonsters.add(0, testMonster2);
		myMonsters.add(1, testMonster3);
		myMonsters.add(3, testMonster4);
		ArrayList<Monster> testMonsterList = new ArrayList<Monster>();
		testMonsterList.add(testMonster2);
		testMonsterList.add(testMonster3);
		testMonsterList.add(testMonster1);
		testMonsterList.add(testMonster4);
		assertEquals(testMonsterList, myMonsters);
	}

	
	@Test
	public void testRemove1() throws InventoryFullException {
		// Blue sky
		Monster testMonster = new Chunky(game);
		myMonsters.add(testMonster);
		myMonsters.remove(testMonster);
		ArrayList<Monster> testMonsterList = new ArrayList<Monster>();
		assertEquals(testMonsterList, myMonsters);
	}
	
	
	@Test
	public void testRemove2() throws InventoryFullException {
		// Multiple monsters of the same type
		Monster testMonster1 = new Chunky(game);
		Monster testMonster2 = new Chunky(game);
		myMonsters.add(testMonster1);
		myMonsters.add(testMonster2);
		myMonsters.add(testMonster2);
		myMonsters.remove(testMonster2);
		myMonsters.remove(testMonster1);
		ArrayList<Monster> testMonsterList = new ArrayList<Monster>();
		testMonsterList.add(testMonster2);
		assertEquals(testMonsterList, myMonsters);
	}

	
	@Test
	public void testIsFull() throws InventoryFullException {
		// Blue sky
		Monster testMonster = new Chunky(game);
		assertFalse(myMonsters.isFull());
		myMonsters.add(testMonster);
		myMonsters.add(testMonster);
		myMonsters.add(testMonster);
		assertFalse(myMonsters.isFull());
		myMonsters.add(testMonster);
		assertTrue(myMonsters.isFull());
	}
	
	
	@Test
	public void testIsEmpty() throws InventoryFullException {
		// Blue sky
		Monster testMonster = new Chunky(game);
		assertTrue(myMonsters.isEmpty());
		myMonsters.add(testMonster);
		assertFalse(myMonsters.isEmpty());
	}
	
	
	@Test
	public void testAllFainted() throws InventoryFullException {
		// No monsters in the team
		assertTrue(myMonsters.allFainted());
		
		Monster testMonster1 = new Chunky(game);
		myMonsters.add(testMonster1);
		assertFalse(myMonsters.allFainted());
		testMonster1.setHealth(0);
		testMonster1.setIsFainted(true);
		assertTrue(myMonsters.allFainted());
		
		Monster testMonster2 = new Chunky(game);
		myMonsters.add(testMonster2);
		assertFalse(myMonsters.allFainted());
		testMonster2.setHealth(0);
		testMonster2.setIsFainted(true);
		assertTrue(myMonsters.allFainted());
	}
	
	
	@Test
	public void testHealAll() throws InventoryFullException {
		Monster testMonster1 = new Chunky(game);
		testMonster1.setHealth(testMonster1.getHealth() - 10);
		myMonsters.add(testMonster1);
		Monster testMonster2 = new Chunky(game);
		testMonster2.setHealth(testMonster2.getHealth() / 2);
		myMonsters.add(testMonster2);
		Monster testMonster3 = new Chunky(game);
		myMonsters.add(testMonster3);
		
		myMonsters.healAll();
		assertEquals(testMonster1.getMaxHealth(), myMonsters.get(0).getHealth());
		assertEquals((testMonster2.getMaxHealth() / 2) + testMonster2.getHealAmount(), myMonsters.get(1).getHealth());
		assertEquals(testMonster3.getMaxHealth(), myMonsters.get(2).getHealth());
	}
	
	
	@Test
	public void testRandom() throws InventoryFullException {
		Monster testMonster1 = new Chunky(game);
		myMonsters.add(testMonster1);
		Monster testMonster2 = new Chunky(game);
		myMonsters.add(testMonster2);
		
		Monster randomMonster = myMonsters.random();
		assertTrue(myMonsters.contains(randomMonster));
	}
	
	
	@Test
	public void testLevelUpOnDay() throws InventoryFullException, InvalidValueException {
		game.setDay(3);
		game.setDifficulty(Difficulty.EASY);

		Monster testMonster1 = new Chunky(game);
		myMonsters.add(testMonster1);
		Monster testMonster2 = new Chunky(game);
		myMonsters.add(testMonster2);
		
		myMonsters.levelUpOnDay();
		
		assertEquals(3, myMonsters.get(0).getLevel());
		assertEquals(3, myMonsters.get(1).getLevel());
	}
	
	
	@Test
	public void testToString() throws InventoryFullException {
		Monster testMonster1 = new Chunky(game);
		myMonsters.add(testMonster1);
		Monster testMonster2 = new Chunky(game);
		myMonsters.add(testMonster2);
		
		String result = "";
		for (Monster monster : myMonsters)
		{
			result += "\n" + monster;
		}
		
		assertEquals(result, myMonsters.toString());
	}
	
	
	@Test
	public void testView() throws InventoryFullException {
		Monster testMonster1 = new Chunky(game);
		myMonsters.add(testMonster1);
		Monster testMonster2 = new Chunky(game);
		myMonsters.add(testMonster2);
		
		String result = "";
		int start = 1;
    	for (Monster monster : myMonsters) {
    		result += String.format("%s: %s\n", start, monster);
    		start++;
    	}
    	
    	assertEquals(result, myMonsters.view());
	}

}
