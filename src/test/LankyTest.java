package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.StatMaxedOutException;
import main.*;
import monsters.Lanky;

/**
 * Unit test for Lanky class
 * @author Farzad and Daniel
 */

class LankyTest {

	/**
	 * Fields
	 */
	private GameEnvironment game = GameEnvironment.getInstance();
	private Lanky monster;
	
	/**
	 * Assign values to fields used in unit test
	 * @throws Exception if any exception is caught
	 */
	@BeforeEach
	void setUp() throws Exception {
		game.populateGame();
		monster = new Lanky();
	}

	/**
	 * Levels up monster once
	 * @result monster's stats will increase without any errors
	 * @throws StatMaxedOutException if monster is already at maximum level
	 */
	@Test
	void testLevelUp1() throws StatMaxedOutException {
		monster.levelUp();
		assertEquals(66, monster.getMaxHealth());
		assertEquals(55, monster.getDamage());
		assertEquals(80, monster.getCost());
		assertEquals(18, monster.getHealAmount());
		assertEquals(0.1, monster.getCritRate());
	}
	
	/**
	 * Levels up monster twice
	 * @result monster's stats will increase without any errors
	 * @throws StatMaxedOutException if monster is already at maximum level
	 */
	@Test
	void testLevelUp2() throws StatMaxedOutException {
		monster.levelUp();
		monster.levelUp();
		assertEquals(72, monster.getMaxHealth());
		assertEquals(70, monster.getDamage());
		assertEquals(90, monster.getCost());
		assertEquals(24, monster.getHealAmount());
		assertEquals(0.1, monster.getCritRate());
	}
	
	/**
	 * Levels up monster four times
	 * @result monster is unable to level up further and exception is thrown
	 * @throws StatMaxedOutException if monster is already at maximum level
	 */
	@Test
	void testLevelUp3() throws StatMaxedOutException {
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
		assertTrue(Lanky.class.isInstance(cloneInst));
		assertTrue(cloneInst.getLevel() == monster.getLevel());
	}

}
