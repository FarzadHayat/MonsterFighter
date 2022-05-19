package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InventoryFullException;
import exceptions.NotFoundException;
import exceptions.StatMaxedOutException;
import main.*;
import monsters.Chunky;
import monsters.Zap;

/**
 * Unit test for RandomEvent class
 * @author Farzad and Daniel
 */

class RandomEventTest {

	/**
	 * Fields
	 */
	private GameEnvironment game;
	private RandomEvent randEvent;
	private Monster monster;
	private Player player;
	
	/**
	 * Assign values to fields used in unit test
	 * @throws Exception if any exception is caught
	 */
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		game.setupGame();
		player = game.getPlayer();
		randEvent = new RandomEvent(game);
		monster = new Chunky(game);
	}

	/**
	 * Random monster levels up, chance dependent 
	 * @result monster levels up without any errors
	 * @throws StatMaxedOutException if monster stat is already maxed out 
	 */
	@Test
	public void testRandomMonsterLevelUp1() throws StatMaxedOutException {
		//Monster levels up
		randEvent.setRandom(new Random(1010101010)); //Generates double of 0.04... 
		randEvent.randomMonsterLevelUp(monster);
		assertEquals(2, monster.getLevel());
	}
	
	/**
	 * Random monster levels up, chance dependent (chance increased to 100% based on number of wins) 
	 * @result monster levels up without any errors 
	 * @throws StatMaxedOutException if monster stat is already maxed out 
	 */
	@Test 
	public void testRandomMonsterLevelUp2() throws StatMaxedOutException {
		//Monster levels up
		//Level up chance is increased due to number of battles won
		game.getScoreSystem().setDayBattlesWon(9);
		randEvent.randomMonsterLevelUp(monster);
		assertEquals(2, monster.getLevel());
		assertEquals(0.1, randEvent.getLevelUpChance());
	}
	
	/**
	 * Random monster levels up, chance dependent 
	 * @result monster does not level up
	 * @throws StatMaxedOutException if monster stat is already maxed out 
	 */
	@Test
	public void testRandomMonsterLevelUp3() throws StatMaxedOutException {
		//Monster does not level up
		randEvent.setRandom(new Random(123));
		randEvent.randomMonsterLevelUp(monster);
		assertEquals(1, monster.getLevel());
	}
	
	/**
	 * Random monster levels up, chance dependent (monster is already max level)
	 * @result exception is thrown 
	 * @throws StatMaxedOutException if monster stat is already maxed out 
	 */
	@Test
	public void testRandomMonsterLevelUp4() throws StatMaxedOutException {
		//Monster is already at max level 
		//Level up chance is increased due to number of battles won 
		game.getScoreSystem().setDayBattlesWon(9);
		monster.setLevel(4);
		randEvent.randomMonsterLevelUp(monster);
		assertEquals(4, monster.getLevel());
	}
	
	/**
	 * Random monster leaves, chance dependent 
	 * @result monster leaves without any errors
	 * @throws InventoryFullException if inventory is already full 
	 * @throws NotFoundException if monster is not found 
	 */
	@Test
	public void testRandomMonsterLeave1() throws InventoryFullException, NotFoundException {
		//Monster leaves
		randEvent.setRandom(new Random(1010101010)); //Generates double of 0.04...
		player.getMonsters().add(monster);
		randEvent.randomMonsterLeave(monster);
		assertEquals(0, player.getMonsters().getList().size());
	}
	
	/**
	 * Random monster leaves, chance dependent 
	 * @result monster does not leave
	 * @throws InventoryFullException if inventory is already full 
	 * @throws NotFoundException if monster is not found 
	 */
	@Test
	public void testRandomMonsterLeave2() throws InventoryFullException, NotFoundException {
		//Monster does not leave
		randEvent.setRandom(new Random(123));
		player.getMonsters().add(monster);
		randEvent.randomMonsterLeave(monster);
		assertEquals(1, player.getMonsters().getList().size());
	}
	
	/**
	 * Random monster leaves, chance dependent (fainted monster)
	 * @result monster leaves without any errors
	 * @throws InventoryFullException if inventory is already full 
	 * @throws NotFoundException if monster is not found 
	 */
	@Test
	public void testRandomMonsterLeave3() throws InventoryFullException {
		//Fainted monster, monster leaves 
		randEvent.setRandom(new Random(1010101010)); //Generates double of 0.04...
		player.getMonsters().add(monster);
		monster.setIsFainted(true);
		randEvent.randomMonsterLeave(monster);
		assertEquals(0.05, randEvent.getLeaveChance());
		assertEquals(0, player.getMonsters().getList().size());
	}
	
	/**
	 * Random monster leaves, chance dependent (fainted monster)
	 * @result monster does not leave
	 * @throws InventoryFullException if inventory is already full 
	 * @throws NotFoundException if monster is not found 
	 */
	@Test
	public void testRandomMonsterLeave4() throws InventoryFullException {
		//Fainted monster, monster does not leave
		randEvent.setRandom(new Random(123));
		player.getMonsters().add(monster);
		monster.setIsFainted(true);
		randEvent.randomMonsterLeave(monster);
		assertEquals(0.05, randEvent.getLeaveChance());
		assertEquals(1, player.getMonsters().getList().size());
	}
	
	/**
	 * Random monster joins, chance dependent 
	 * @result monster joins without any errors 
	 * @throws InventoryFullException if inventory is already full
	 */
	@Test
	public void testRandomMonsterJoin1() throws InventoryFullException {
		//Monster joins
		randEvent.setRandom(new Random(1010101010)); //Generates double of 0.04...
		player.getMonsters().add(monster);
		randEvent.randomMonsterJoin();
		assertEquals(2, player.getMonsters().getList().size());
	}
	
	/**
	 * Random monster joins, chance dependent 
	 * @result monster does not join
	 * @throws InventoryFullException if inventory is already full
	 */
	@Test
	public void testRandomMonsterJoin2() throws InventoryFullException {
		//Monster doesn't join
		randEvent.setRandom(new Random(123)); //Generates double of 0.04...
		player.getMonsters().add(monster);
		randEvent.randomMonsterJoin();
		assertEquals(1, player.getMonsters().getList().size());
	}
	
	/**
	 * Random monster joins, chance dependent (inventory is full)
	 * @result exception is thrown
	 * @throws InventoryFullException if inventory is already full
	 */
	@Test
	public void testRandomMonsterJoin3() throws InventoryFullException {
		//Inventory full 
		randEvent.setRandom(new Random(1010101010)); //Generates double of 0.04...
		for(int i = 0; i < 4; i++) {
			player.getMonsters().add(monster);
		}
		randEvent.randomMonsterJoin();
		assertEquals(4, player.getMonsters().getList().size());
	}
	
	/**
	 * 4 random monster joins, chance dependent 
	 * @result 4 monsters join without any errors 
	 * @throws InventoryFullException if inventory is already full
	 */
	@Test
	public void testRandomMonsterJoin4() {
		//4 monsters join 
		for(int i = 0; i < 4; i++) {
			randEvent.setRandom(new Random(1010101010)); //Generates double of 0.04...
			randEvent.randomMonsterJoin();
		}
		assertEquals(4, player.getMonsters().getList().size());
	}
	
	/**
	 * Run all chance dependent functions 
	 * @result monster levels up, monster does not leave, random monster joins 
	 * @throws InventoryFullException if inventory is already full
	 * @throws StatMaxedOutException if monster stat is maxed out 
	 * @throws NotFoundException if monster is not found 
	 */
	@Test
	public void testRunAllRandom1() throws InventoryFullException, StatMaxedOutException, NotFoundException {
		//Monster levels up
		//No monster leaves
		//A monster joins
		randEvent.setRandom(new Random(1010101010)); //Generates double of 0.04...
		player.getMonsters().add(monster);
		randEvent.runAllRandom();
		assertEquals(2, monster.getLevel());
		assertEquals(2, player.getMonsters().getList().size());
	}
	
	/**
	 * Run all chance dependent functions 
	 * @result monster levels up, monster leaves, random monster joins 
	 * @throws InventoryFullException if inventory is already full
	 * @throws StatMaxedOutException if monster stat is maxed out 
	 * @throws NotFoundException if monster is not found 
	 */
	@Test
	public void testRunAllRandom2() throws InventoryFullException, StatMaxedOutException, NotFoundException {
		//Monster levels up
		//Monster leaves 
		//A monster joins 
		randEvent.setRandom(new Random(1010101010)); //Generates double of 0.04...
		Monster zap = new Zap(game);
		player.getMonsters().add(monster);
		player.getMonsters().add(zap);
		assertEquals(2, player.getMonsters().getList().size());
		randEvent.runAllRandom();
		assertEquals(2, monster.getLevel());
		assertEquals(2, player.getMonsters().getList().size());
	}
	
	/**
	 * Run all chance dependent functions 
	 * @result monster does not level up, monster does not leave, no random monster join
	 * @throws InventoryFullException if inventory is already full
	 * @throws StatMaxedOutException if monster stat is maxed out 
	 * @throws NotFoundException if monster is not found 
	 */
	@Test
	public void testRunAllRandom3() throws InventoryFullException, StatMaxedOutException, NotFoundException {
		//Monster does not level up
		//No monster leaves 
		//No monster joins 
		randEvent.setRandom(new Random(123));
		player.getMonsters().add(monster);
		randEvent.runAllRandom();
		assertEquals(1, monster.getLevel());
		assertEquals(1, player.getMonsters().getList().size());
	}

}
