package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.StatMaxedOutException;
import main.*;
import monsters.AverageJoe;

/**
 * Unit test for AverageJoe class
 * @author Farzad and Daniel
 */

class AverageJoeTest {
	
	/**
	 * Fields
	 */
	private GameEnvironment game = GameEnvironment.getInstance();
	private AverageJoe monster;
	
	/**
	 * Assign values to fields used in unit test
	 * @throws Exception if any exception is caught
	 */
	@BeforeEach
	public void setUp() throws Exception {
		game.populateGame();
		monster = new AverageJoe();
	}

	/**
	 * Levels up monster once
	 * @result monster's stats will increase without any errors
	 * @throws StatMaxedOutException if monster is already at maximum level
	 */
	@Test
	public void testLevelUp1() throws StatMaxedOutException {
		monster.levelUp();
		assertEquals(110, monster.getMaxHealth());
		assertEquals(30, monster.getDamage());
		assertEquals(70, monster.getCost());
		assertEquals(30, monster.getHealAmount());
		assertEquals(0.2, monster.getCritRate());
	}
	
	/**
	 * Levels up monster twice
	 * @result monster's stats will increase without any errors
	 * @throws StatMaxedOutException if monster is already at maximum level
	 */
	@Test
	public void testLevelUp2() throws StatMaxedOutException {
		monster.levelUp();
		monster.levelUp();
		assertEquals(120, monster.getMaxHealth());
		assertEquals(40, monster.getDamage());
		assertEquals(80, monster.getCost());
		assertEquals(40, monster.getHealAmount());
		assertEquals(0.2, monster.getCritRate());
	}
	
	/**
	 * Levels up monster four times
	 * @result monster is unable to level up further and exception is thrown
	 * @throws StatMaxedOutException if monster is already at maximum level
	 */
	@Test
	public void testLevelUp3() throws StatMaxedOutException {
		//Average Joe level up at max level
		for(int i = 0; i < 3; i++) {
			monster.levelUp();
		}
		try {
			monster.levelUp();
		}
		catch(StatMaxedOutException e) {
			assertEquals("Monster is already max level!", e.getMessage());
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
		assertTrue(AverageJoe.class.isInstance(cloneInst));
		assertTrue(cloneInst.getLevel() == monster.getLevel());
	}

}
