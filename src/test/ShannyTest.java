package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.StatMaxedOutException;
import main.*;
import monsters.Shanny;

/**
 * Unit test for Shanny class
 * @author Farzad and Daniel
 */

class ShannyTest {

	/**
	 * Fields
	 */
	private GameEnvironment game = GameEnvironment.getInstance();
	private Shanny monster;
	
	/**
	 * Assign values to fields used in unit test
	 * @throws Exception if any exception is caught
	 */
	@BeforeEach
	public void setUp() throws Exception {
		game.populateGame();
		monster = new Shanny();
	}

	/**
	 * Levels up monster once
	 * @result monster's stats will increase without any errors
	 * @throws StatMaxedOutException if monster is already at maximum level
	 */
	@Test
	public void testLevelUp1() throws StatMaxedOutException {
		//Shanny level up once 
		monster.levelUp();
		assertEquals(110, monster.getMaxHealth());
		assertEquals(23, monster.getDamage());
		assertEquals(70, monster.getCost());
		assertEquals(70, monster.getHealAmount());
		assertEquals(0.2, monster.getCritRate());
	}
	
	/**
	 * Levels up monster twice
	 * @result monster's stats will increase without any errors
	 * @throws StatMaxedOutException if monster is already at maximum level
	 */
	@Test
	public void testLevelUp2() throws StatMaxedOutException {
		//Shanny level up twice 
		monster.levelUp();
		monster.levelUp();
		assertEquals(120, monster.getMaxHealth());
		assertEquals(31, monster.getDamage());
		assertEquals(80, monster.getCost());
		assertEquals(90, monster.getHealAmount());
		assertEquals(0.2, monster.getCritRate());
	}
	
	/**
	 * Levels up monster four times
	 * @result monster is unable to level up further and exception is thrown
	 * @throws StatMaxedOutException if monster is already at maximum level
	 */
	@Test
	public void testLevelUp3() throws StatMaxedOutException {
		//Shanny level up at max level
		for(int i = 0; i < 3; i++) {
			monster.levelUp();
		}
		try {
			monster.levelUp();
		}
		catch(StatMaxedOutException e) {
			assertEquals(e.getMessage(), "Monster is already max level!");
		}
	}
	
	/**
	 * Clones monster instance
	 * @result new monster instance is created and does not equal the original instance
	 */
	@Test
	public void testClone() {
		Monster cloneInst = monster.clone();
		assertTrue(cloneInst != monster);
		assertTrue(Shanny.class.isInstance(cloneInst));
		assertTrue(cloneInst.getLevel() == monster.getLevel());
	}

}
