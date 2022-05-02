package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InsufficientFundsException;
import exceptions.InvalidTargetException;
import exceptions.InvalidValueException;
import exceptions.InventoryFullException;
import exceptions.NotFoundException;
import exceptions.StatMaxedOutException;
import main.*;
import monsters.AverageJoe;

class MonsterTest {

	private GameEnvironment game;
	private MonsterInventory myMonsters;
	private Monster monster;
	private Player player;
	private Shop shop;
	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		game.setupGame();
		player = game.getPlayer();
		shop = game.getShop();
		myMonsters = player.getMonsters();
		monster = new AverageJoe(game);
	}

	
	@Test
	public void testTakeDamage1() throws InvalidValueException {
		//Taking damage less than total health 
		monster.takeDamage(10);
		assertEquals(monster.getMaxHealth()-10, monster.getHealth());
	}
	
	@Test
	public void testTakeDamage2() throws InvalidValueException {
		//Taking negative damage
		try {
			monster.takeDamage(-10);
		}
		catch(InvalidValueException e) {
			assertEquals(e.getMessage(), "Invalid damage value!");
		}
	}
	
	@Test 
	public void testTakeDamage3() throws InvalidValueException {
		//Taking damage equivalent to total health 
		monster.takeDamage(monster.getHealth());
		assertEquals(0, monster.getHealth());
		assertEquals(true, monster.getIsFainted());
	}
	
	@Test 
	public void testTakeDamage4() throws InvalidValueException {
		//Taking more damage than total health 
		monster.takeDamage(monster.getHealth()+20);
		assertEquals(0, monster.getHealth());
		assertEquals(true, monster.getIsFainted());
	}
	
	@Test
	public void testTakeDamage5() throws InvalidValueException {
		//Taking zero damage 
		monster.takeDamage(0);
		assertEquals(100, monster.getHealth());
	}
	
	@Test
	public void testHeal1() {
		//Healing when total health is at max 
		monster.heal();
		assertEquals(100, monster.getHealth());
	}
	
	@Test 
	public void testHeal2() throws InvalidValueException {
		//Healing to exactly max health 
		monster.takeDamage(monster.getHealAmount());
		monster.heal();
		assertEquals(100, monster.getHealth());
	}
	
	@Test
	public void testHeal3() throws InvalidValueException {
		//Healing to less than max health 
		monster.takeDamage(monster.getHealAmount()+10);
		monster.heal();
		assertEquals(90, monster.getHealth());
	}
	
	@Test
	public void testHeal4() throws InvalidValueException {
		//Health exceed max health after healing 
		monster.takeDamage(monster.getHealAmount()/2);
		monster.heal();
		assertEquals(100, monster.getHealth());
	}
	
	@Test
	public void testAttack1() throws InvalidValueException, InvalidTargetException {
		//Attacking and dealing damage to an enemy 
		Monster enemy = new AverageJoe(game);
		monster.attack(enemy);
		//Taking into account whether the monster crits or not 
		assertTrue(enemy.getHealth() == 80 || enemy.getHealth() == 60);
		assertEquals(100, monster.getHealth());
	}
	
	@Test
	public void testBuy1() throws InsufficientFundsException, InventoryFullException, NotFoundException, InvalidValueException {
		//Blue sky
		player.setBalance(monster.getCost());
		shop.setMonsters(new MonsterInventory(4, game));
		shop.getMonsters().add(monster);
		monster.buy();
		ArrayList<Monster> monsterList = new ArrayList<Monster>();
		monsterList.add(monster);
		assertEquals(0, player.getBalance());
		assertEquals(monsterList, myMonsters.getList());
	}
	
	@Test
	public void testBuy2() throws InsufficientFundsException, InventoryFullException, NotFoundException, InvalidValueException {
		//Insufficient fund in player's balance 
		player.setBalance(monster.getCost()/2);
		try {
			monster.buy();
		}
		catch(InsufficientFundsException e) {
			assertEquals(e.getMessage(), "Insufficient funds!");
		}
	}
	
	@Test
	public void testBuy3() throws InsufficientFundsException, InventoryFullException, NotFoundException, InvalidValueException {
		//Inventory full
		player.setBalance(monster.getCost()*5);
		shop.setMonsters(new MonsterInventory(4, game));
		for(int i = 0; i < 4; i++) {
			shop.getMonsters().add(monster);
			monster.buy();
		}
		try {
			monster.buy();
		}
		catch(InventoryFullException e) {
			assertEquals(e.getMessage(), "Inventory full!");
		}
	}
	
	@Test
	public void testSell1() throws NotFoundException, InventoryFullException, InsufficientFundsException, InvalidValueException {
		//Blue sky
		player.setBalance(monster.getCost());
		Monster testMonster = new AverageJoe(game);
		shop.setMonsters(new MonsterInventory(4, game));
		shop.getMonsters().add(testMonster);
		testMonster.buy();
		testMonster.sell();
		ArrayList<Monster> monsterList = new ArrayList<Monster>();
		assertEquals(30, player.getBalance());
		assertEquals(monsterList, myMonsters.getList());
	}
	
	@Test
	public void testSell2() throws NotFoundException, InventoryFullException, InsufficientFundsException, InvalidValueException {
		//Multiple items of same type
		player.setBalance(monster.getCost()*3);
		Monster testMonster = new AverageJoe(game);
		shop.setMonsters(new MonsterInventory(4, game));
		shop.getMonsters().add(monster);
		shop.getMonsters().add(testMonster);
		shop.getMonsters().add(testMonster);
		monster.buy();
		testMonster.buy();
		testMonster.buy();
		testMonster.sell();
		monster.sell();
		ArrayList<Monster> monsterList = new ArrayList<Monster>();
		monsterList.add(testMonster);
		assertEquals(60, player.getBalance());
		assertEquals(monsterList, myMonsters.getList());
	}
	
	@Test
	public void testSell3() throws NotFoundException, InventoryFullException, InsufficientFundsException, InvalidValueException {
		//Purchasable not found in inventory
		player.setBalance(monster.getCost());
		try {
			monster.sell();
		}
		catch(NotFoundException e) {
			assertEquals(e.getMessage(), "Not found in inventory!");
		}
	}
	
	@Test
	public void testLevelUp() throws StatMaxedOutException {
		//Monster is maxed alredy at max level
		monster.setLevel(monster.getMaxLevel());
		try {
			monster.levelUp();
		}
		catch(StatMaxedOutException e) {
			assertEquals(e.getMessage(), "Monster is already max level!");
		}
	}

}
