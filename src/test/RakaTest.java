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

class RakaTest {

	private GameEnvironment game;
	private Raka raka;
	private Monster target;
	
	@BeforeEach
	public void setUp() throws Exception {
		game = new GameEnvironment();
		game.setupGame();
		raka = new Raka(game);
		target = new AverageJoe(game);
	}

	@Test
	public void testHeal1() throws InvalidTargetException, InvalidValueException {
		//Raka heals target to max health  
		target.takeDamage(raka.getDamage());
		raka.healAllies(target);
		assertEquals(target.getMaxHealth(), target.getHealth());
	}
	
	@Test 
	public void testHeal2() throws InvalidTargetException, InvalidValueException {
		//Raka heals target to less than max health 
		target.takeDamage(target.getDamage());
		raka.healAllies(target);
		assertEquals(target.getMaxHealth()-4, target.getHealth());
	}
	
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
	
	@Test
	public void testAttack1() throws InvalidTargetException, InvalidValueException, InventoryFullException {
		//Raka heals
		game.getPlayer().getMonsters().add(raka);
		raka.setRandom(new Random(0));
		assertEquals(raka.getHealAmount(), raka.attack(target));
	}
	
	@Test
	public void testAttack2() throws InventoryFullException, InvalidTargetException, InvalidValueException {
		//Raka attacks
		game.getPlayer().getMonsters().add(raka);
		raka.setRandom(new Random(1234));
		assertTrue(raka.getDamage() == raka.attack(target) || raka.getDamage()*2 == raka.attack(target));
	}
	
	@Test
	public void testClone() {
		Monster cloneInst = raka.clone();
		assertTrue(cloneInst != raka);
		assertTrue(Raka.class.isInstance(cloneInst));
		assertTrue(cloneInst.getLevel() == raka.getLevel());
	}

}
