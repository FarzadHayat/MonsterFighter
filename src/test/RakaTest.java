package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InvalidTargetException;
import exceptions.InvalidValueException;
import exceptions.InventoryFullException;
import exceptions.StatMaxedOutException;
import main.*;
import monsters.AverageJoe;
import monsters.Raka;

/**
 * Unit test for Raka class
 * @author Farzad and Daniel
 */

class RakaTest {

	/**
	 * Fields
	 */
	private GameEnvironment game = GameEnvironment.getInstance();
	private Raka raka;
	private Monster target;
	
	/**
	 * Assign values to fields used in unit test
	 * @throws Exception if any exception is caught
	 */
	@BeforeEach
	public void setUp() throws Exception {
		game.populateGame();
		raka = new Raka();
		target = new AverageJoe();
	}
	
	/**
	 * Heal target to max health 
	 * @result heals target without any errors 
	 * @throws InvalidTargetException if given monster is fainted 
	 * @throws InvalidValueException if given value is invalid 
	 */
	@Test
	public void testHeal1() throws InvalidTargetException, InvalidValueException {
		//Raka heals target to max health  
		target.takeDamage(raka.getDamage());
		raka.healAllies(target);
		assertEquals(target.getMaxHealth(), target.getHealth());
	}
	
	/**
	 * Heal target to less than max health 
	 * @result heals target without any errors 
	 * @throws InvalidTargetException if given monster is fainted 
	 * @throws InvalidValueException if given value is invalid 
	 */
	@Test 
	public void testHeal2() throws InvalidTargetException, InvalidValueException {
		//Raka heals target to less than max health 
		target.takeDamage(target.getDamage());
		raka.healAllies(target);
		assertEquals(target.getMaxHealth()-4, target.getHealth());
	}
	
	/**
	 * Heal fainted target
	 * @result exception is thrown and target is not healed 
	 * @throws InvalidTargetException if given monster is fainted 
	 * @throws InvalidValueException if given value is invalid 
	 */
	@Test
	public void testHeal3() throws InvalidTargetException, InvalidValueException {
		//Raka tries to heal fainted ally 
		try {
			target.takeDamage(target.getHealth());
			raka.healAllies(target);
		}
		catch(InvalidTargetException e) {
			assertEquals(0, target.getHealth());
			assertEquals("Invalid target!", e.getMessage());
		}
	}
	
	/**
	 * Levels up monster once
	 * @result monster's stats will increase without any errors
	 * @throws StatMaxedOutException if monster is already at maximum level
	 */
	@Test
	public void testLevelUp1() throws StatMaxedOutException {
		//Raka level up once 
		raka.levelUp();
		assertEquals(88, raka.getMaxHealth());
		assertEquals(15, raka.getDamage());
		assertEquals(80, raka.getCost());
		assertEquals(24, raka.getHealAmount());
		assertEquals(0.1, raka.getCritRate());
	}
	
	/**
	 * Levels up monster twice
	 * @result monster's stats will increase without any errors
	 * @throws StatMaxedOutException if monster is already at maximum level
	 */
	@Test
	public void testLevelUp2() throws StatMaxedOutException {
		//Raka level up twice 
		raka.levelUp();
		raka.levelUp();
		assertEquals(96, raka.getMaxHealth());
		assertEquals(20, raka.getDamage());
		assertEquals(90, raka.getCost());
		assertEquals(32, raka.getHealAmount());
		assertEquals(0.1, raka.getCritRate());
	}
	
	/**
	 * Levels up monster four times
	 * @result monster is unable to level up further and exception is thrown
	 * @throws StatMaxedOutException if monster is already at maximum level
	 */
	@Test
	public void testLevelUp3() throws StatMaxedOutException {
		//Raka level up at max level
		for(int i = 0; i < 3; i++) {
			raka.levelUp();
		}
		try {
			raka.levelUp();
		}
		catch(StatMaxedOutException e) {
			assertEquals(e.getMessage(), "Monster is already max level!");
		}
	}
	
	/**
	 * Raka attack
	 * @result raka heals instead of attacking 
	 * @throws InvalidTargetException if given monster is fainted 
	 * @throws InvalidValueException if given value is invalid 
	 * @throws InventoryFullException if inventory is already full
	 */
	@Test
	public void testAttack1() throws InvalidTargetException, InvalidValueException, InventoryFullException {
		//Raka heals
		game.getPlayer().getMonsters().add(raka);
		raka.setRandom(new Random(0));
		assertEquals(raka.getHealAmount(), raka.attack(target));
	}
	
	/**
	 * Raka attack
	 * @result raka attacks without any errors 
	 * @throws InvalidTargetException if given monster is fainted 
	 * @throws InvalidValueException if given value is invalid 
	 * @throws InventoryFullException if inventory is already full
	 */
	@Test
	public void testAttack2() throws InventoryFullException, InvalidTargetException, InvalidValueException {
		//Raka attacks
		game.getPlayer().getMonsters().add(raka);
		raka.setRandom(new Random(1234));
		assertTrue(raka.getDamage() == raka.attack(target) || raka.getDamage()*2 == raka.attack(target));
	}
	
	/**
	 * Clones monster instance
	 * @result new monster instance is created and does not equal the original instance
	 */
	@Test
	public void testClone() {
		Monster cloneInst = raka.clone();
		assertTrue(cloneInst != raka);
		assertTrue(Raka.class.isInstance(cloneInst));
		assertTrue(cloneInst.getLevel() == raka.getLevel());
	}

}
