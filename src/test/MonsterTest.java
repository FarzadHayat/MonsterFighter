package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Chunky;
import main.GameEnvironment;
import main.InsufficientFundsException;
import main.InventoryFullException;
import main.Monster;
import main.PurchasableNotFoundException;

class MonsterTest {

	private GameEnvironment game;
	private Monster monster;
	
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		monster = new Chunky("Test", "Testing description", 100, 20, 20, 1, 10, 0.1, game);
	}

	@Test
	public void testTakeDamage1() {
		monster.takeDamage(10);
		assertEquals(90, monster.getHealth());
	}
	
	@Test
	public void testTakeDamage2() {
		monster.takeDamage(0);
		assertEquals(100, monster.getHealth());
	}
	
	@Test 
	public void testTakeDamage3() {
		monster.takeDamage(100);
		assertEquals(0, monster.getHealth());
		assertEquals(true, monster.getIsFainted());
	}
	
	@Test 
	public void testTakeDamage4() {
		monster.takeDamage(120);
		assertEquals(0, monster.getHealth());
		assertEquals(true, monster.getIsFainted());
	}
	
	@Test
	public void testHeal1() {
		monster.heal();
		assertEquals(100, monster.getHealth());
	}
	
	@Test 
	public void testHeal2() {
		monster.takeDamage(10);
		monster.heal();
		assertEquals(100, monster.getHealth());
	}
	
	@Test
	public void testHeal3() {
		monster.takeDamage(20);
		monster.heal();
		assertEquals(90, monster.getHealth());
	}
	
	@Test
	public void testHeal4() {
		monster.takeDamage(5);
		monster.heal();
		assertEquals(100, monster.getHealth());
	}
	
	@Test
	public void testAttack1() {
		Monster enemy = new Chunky("Enemy", "Enemy description", 100, 20, 20, 1, 10, 0.1, game);
		monster.attack(enemy);
		assertEquals(80, enemy.getHealth());
		assertEquals(100, monster.getHealth());
	}
	
	@Test
	public void testBuy1() throws InsufficientFundsException, InventoryFullException {
		game.setBalance(20);
		monster.buy();
		ArrayList<Monster> monsterList = new ArrayList<Monster>();
		monsterList.add(monster);
		assertEquals(0, game.getBalance());
		assertEquals(monsterList, game.getInventory().getMyMonsters());
	}
	
	@Test
	public void testBuy2() throws InsufficientFundsException, InventoryFullException {
		game.setBalance(10);
		try {
			monster.buy();
		}
		catch(InsufficientFundsException e) {
			assertEquals(e.getMessage(), "Insufficient funds!");
		}
	}
	
	@Test
	public void testBuy3() throws InsufficientFundsException, InventoryFullException {
		game.setBalance(100);
		for(int i = 0; i < 4; i++) {
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
	public void testSell1() throws PurchasableNotFoundException, InventoryFullException, InsufficientFundsException {
		game.setBalance(20);
		Monster testMonster = new Chunky("Test1", "Testing description1", 100, 20, 20, 1, 10, 0.1, game);
		testMonster.buy();
		testMonster.sell();
		ArrayList<Monster> monsterList = new ArrayList<Monster>();
		assertEquals(10, game.getBalance());
		assertEquals(monsterList, game.getInventory().getMyMonsters());
	}
	
	@Test
	public void testSell2() throws PurchasableNotFoundException, InventoryFullException, InsufficientFundsException {
		game.setBalance(60);
		Monster testMonster = new Chunky("Test1", "Testing description1", 100, 20, 20, 1, 10, 0.1, game);
		monster.buy();
		testMonster.buy();
		testMonster.buy();
		testMonster.sell();
		monster.sell();
		ArrayList<Monster> monsterList = new ArrayList<Monster>();
		monsterList.add(testMonster);
		assertEquals(20, game.getBalance());
		assertEquals(monsterList, game.getInventory().getMyMonsters());
	}
	
	@Test
	public void testSell3() throws PurchasableNotFoundException, InventoryFullException, InsufficientFundsException {
		game.setBalance(20);
		try {
			monster.sell();
		}
		catch(PurchasableNotFoundException e) {
			assertEquals(e.getMessage(), "Purchasable not found in inventory!");
		}
	}

}
