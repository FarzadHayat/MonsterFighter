package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InvalidValueException;
import exceptions.InventoryFullException;
import main.*;
import monsters.Chunky;

/**
 * Unit test for MonsterInventory class
 * @author Farzad and Daniel
 */

class MonsterInventoryTest {
	
	/**
	 * Fields
	 */
	private GameEnvironment game = GameEnvironment.getInstance();
	private MonsterInventory myMonsters;

	/**
	 * Assign values to fields used in unit test
	 * @throws Exception if any exception is caught
	 */
	@BeforeEach
	void setUp() throws Exception {
		game.populateGame();
		game.getPlayer().setMonsters(new MonsterInventory(4));
		myMonsters = game.getPlayer().getMonsters();
	}
	
	/**
	 * Add monster to monster inventory
	 * @result monster is added to inventory without any errors
	 * @throws InventoryFullException
	 */
	@Test
	public void testAdd1() throws InventoryFullException {
		// Blue sky
		Monster testMonster = new Chunky();
		myMonsters.add(testMonster);
		ArrayList<Monster> testMonsterList = new ArrayList<Monster>();
		testMonsterList.add(testMonster);
		assertEquals(testMonsterList, myMonsters);
	}
	
	/**
	 * Add monsters to specific indexes
	 * @result monsters are added to specific indexes without any errors
	 * @throws InventoryFullException if inventory is already full
	 */
	@Test
	public void testAdd2() throws InventoryFullException {
		// Add at index
		Monster testMonster1 = new Chunky();
		Monster testMonster2 = new Chunky();
		Monster testMonster3 = new Chunky();
		Monster testMonster4 = new Chunky();
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

	/**
	 * Remove monster from inventory
	 * @result monster is removed without any errors
	 * @throws InventoryFullException if inventory is already full
	 */
	@Test
	public void testRemove1() throws InventoryFullException {
		// Blue sky
		Monster testMonster = new Chunky();
		myMonsters.add(testMonster);
		myMonsters.remove(testMonster);
		ArrayList<Monster> testMonsterList = new ArrayList<Monster>();
		assertEquals(testMonsterList, myMonsters);
	}
	
	/**
	 * Remove one instance of a monster from the monster inventory
	 * @result removes one instance without any errors
	 * @throws InventoryFullException if inventory is already full
	 */
	@Test
	public void testRemove2() throws InventoryFullException {
		// Multiple monsters of the same type
		Monster testMonster1 = new Chunky();
		Monster testMonster2 = new Chunky();
		myMonsters.add(testMonster1);
		myMonsters.add(testMonster2);
		myMonsters.add(testMonster2);
		myMonsters.remove(testMonster2);
		myMonsters.remove(testMonster1);
		ArrayList<Monster> testMonsterList = new ArrayList<Monster>();
		testMonsterList.add(testMonster2);
		assertEquals(testMonsterList, myMonsters);
	}

	/**
	 * Checks if monster inventory is full
	 * @result monster inventory is full when there are 4 items
	 * @throws InventoryFullException if inventory is already full
	 */
	@Test
	public void testIsFull() throws InventoryFullException {
		// Blue sky
		Monster testMonster = new Chunky();
		assertFalse(myMonsters.isFull());
		myMonsters.add(testMonster);
		myMonsters.add(testMonster);
		myMonsters.add(testMonster);
		assertFalse(myMonsters.isFull());
		myMonsters.add(testMonster);
		assertTrue(myMonsters.isFull());
	}
	
	/**
	 * Checks if monster inventory is empty
	 * @result monster inventory is empty when there are no monsters 
	 * @throws InventoryFullException if inventory is already full
	 */
	@Test
	public void testIsEmpty() throws InventoryFullException {
		// Blue sky
		Monster testMonster = new Chunky();
		assertTrue(myMonsters.isEmpty());
		myMonsters.add(testMonster);
		assertFalse(myMonsters.isEmpty());
	}
	
	/**
	 * Checks if all monsters are fainted
	 * @result true if all monsters are fainted and false otherwise
	 * @throws InventoryFullException if inventory is already full
	 */
	@Test
	public void testAllFainted() throws InventoryFullException {
		// No monsters in the team
		assertTrue(myMonsters.allFainted());
		
		Monster testMonster1 = new Chunky();
		myMonsters.add(testMonster1);
		assertFalse(myMonsters.allFainted());
		testMonster1.setHealth(0);
		testMonster1.setIsFainted(true);
		assertTrue(myMonsters.allFainted());
		
		Monster testMonster2 = new Chunky();
		myMonsters.add(testMonster2);
		assertFalse(myMonsters.allFainted());
		testMonster2.setHealth(0);
		testMonster2.setIsFainted(true);
		assertTrue(myMonsters.allFainted());
	}
	
	/**
	 * Heal all monsters in inventory
	 * @result all monsters are healed without any errors
	 * @throws InventoryFullException if inventory is already full
	 */
	@Test
	public void testHealAll() throws InventoryFullException {
		Monster testMonster1 = new Chunky();
		testMonster1.setHealth(testMonster1.getHealth() - 10);
		myMonsters.add(testMonster1);
		Monster testMonster2 = new Chunky();
		testMonster2.setHealth(testMonster2.getHealth() / 2);
		myMonsters.add(testMonster2);
		Monster testMonster3 = new Chunky();
		myMonsters.add(testMonster3);
		
		myMonsters.healAll();
		assertEquals(testMonster1.getMaxHealth(), myMonsters.get(0).getHealth());
		assertEquals((testMonster2.getMaxHealth() / 2) + testMonster2.getHealAmount(), myMonsters.get(1).getHealth());
		assertEquals(testMonster3.getMaxHealth(), myMonsters.get(2).getHealth());
	}
	
	/**
	 * Gets random monster in inventory
	 * @result random monster from inventory is returned without any errors
	 * @throws InventoryFullException if inventory is already full
	 */
	@Test
	public void testRandom() throws InventoryFullException {
		Monster testMonster1 = new Chunky();
		myMonsters.add(testMonster1);
		Monster testMonster2 = new Chunky();
		myMonsters.add(testMonster2);
		
		Monster randomMonster = myMonsters.random();
		assertTrue(myMonsters.contains(randomMonster));
	}
	
	/**
	 * Level up monsters to appropriate level based on current day 
	 * @result level of monsters are correct to the number of day
	 * @throws InventoryFullException if inventory is already full
	 * @throws InvalidValueException if number of day value is invalid
	 */
	@Test
	public void testLevelUpOnDay() throws InventoryFullException, InvalidValueException {
		game.setDay(3);
		game.setNumDays(5);
		game.setDifficulty(Difficulty.EASY);

		Monster testMonster1 = new Chunky();
		myMonsters.add(testMonster1);
		Monster testMonster2 = new Chunky();
		myMonsters.add(testMonster2);
		
		System.out.println(game.getNumDays());
		System.out.println(testMonster1.getMaxLevel());
		
		for(int day = 1; day < game.getDay(); day++) {
			myMonsters.levelUpOnDay();
		}
		
		assertEquals(3, myMonsters.get(0).getLevel());
		assertEquals(3, myMonsters.get(1).getLevel());
	}
	
	/**
	 * Checks string representation of monster inventory 
	 * @result correct string is returned without any errors 
	 * @throws InventoryFullException if inventory is already full
	 */
	@Test
	public void testToString() throws InventoryFullException {
		Monster testMonster1 = new Chunky();
		myMonsters.add(testMonster1);
		Monster testMonster2 = new Chunky();
		myMonsters.add(testMonster2);
		
		String result = "";
		for (Monster monster : myMonsters)
		{
			result += "\n" + monster;
		}
		
		assertEquals(result, myMonsters.toString());
	}
	
	/**
	 * Checks string representation of view option
	 * @result correct string is returned without any errors
	 */
	@Test
	public void testView() throws InventoryFullException {
		Monster testMonster1 = new Chunky();
		myMonsters.add(testMonster1);
		Monster testMonster2 = new Chunky();
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
