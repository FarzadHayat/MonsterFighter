package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InsufficientFundsException;
import exceptions.InvalidTargetException;
import exceptions.InvalidValueException;
import exceptions.InventoryFullException;
import exceptions.StatMaxedOutException;
import main.*;
import monsters.AverageJoe;

/**
 * Unit test for Monster class
 * @author Farzad and Daniel
 */

class MonsterTest {

	/**
	 * Fields
	 */
	private GameEnvironment game = GameEnvironment.getInstance();
	private MonsterInventory myMonsters;
	private Monster monster;
	private Player player;
	private Shop shop;
	
	/**
	 * Assign values to fields used in unit test
	 * @throws Exception if any exception is caught
	 */
	@BeforeEach
	void setUp() throws Exception {
		game.populateGame();
		player = game.getPlayer();
		player.setMonsters(new MonsterInventory(4));
		shop = game.getShop();
		myMonsters = player.getMonsters();
		monster = new AverageJoe();
	}

	/**
	 * Monster takes damage
	 * @result monster takes given damage without any errors 
	 * @throws InvalidValueException if given value is invalid
	 */
	@Test
	public void testTakeDamage1() throws InvalidValueException {
		//Taking damage less than total health 
		monster.takeDamage(10);
		assertEquals(monster.getMaxHealth()-10, monster.getHealth());
	}
	
	/**
	 * Monster takes negative damage
	 * @result exception is thrown 
	 * @throws InvalidValueException if given value is invalid 
	 */
	@Test
	public void testTakeDamage2() throws InvalidValueException {
		//Taking negative damage
		try {
			monster.takeDamage(-10);
		}
		catch(InvalidValueException e) {
			assertEquals("Invalid damage value!", e.getMessage());
		}
	}
	
	/**
	 * Monster takes damage equivalent to total health
	 * @result monster health is zero and monster is fainted 
	 * @throws InvalidValueException if given value is invalid
	 */
	@Test 
	public void testTakeDamage3() throws InvalidValueException {
		//Taking damage equivalent to total health 
		monster.takeDamage(monster.getHealth());
		assertEquals(0, monster.getHealth());
		assertEquals(true, monster.getIsFainted());
	}
	
	/**
	 * Monster takes damage more than total health
	 * @result monster health is zero and monster is fainted
	 * @throws InvalidValueException if given value is invalid
	 */
	@Test 
	public void testTakeDamage4() throws InvalidValueException {
		//Taking more damage than total health 
		monster.takeDamage(monster.getHealth()+20);
		assertEquals(0, monster.getHealth());
		assertEquals(true, monster.getIsFainted());
	}
	
	/**
	 * Monster takes zero damage
	 * @result nothing happens to the monster's health 
	 * @throws InvalidValueException if given value is invalid 
	 */
	@Test
	public void testTakeDamage5() throws InvalidValueException {
		//Taking zero damage 
		monster.takeDamage(0);
		assertEquals(100, monster.getHealth());
	}
	
	/**
	 * Monster heals when health is max 
	 * @result nothing happens to monster's health
	 */
	@Test
	public void testHeal1() {
		//Healing when total health is at max 
		monster.heal();
		assertEquals(100, monster.getHealth());
	}
	
	/**
	 * Monster heals to max health 
	 * @result monster heals without any errors 
	 * @throws InvalidValueException if given value is invalid
	 */
	@Test 
	public void testHeal2() throws InvalidValueException {
		//Healing to exactly max health 
		monster.takeDamage(monster.getHealAmount());
		monster.heal();
		assertEquals(100, monster.getHealth());
	}
	
	/**
	 * Monster heals to less than max health
	 * @result monster heals without any errors
	 * @throws InvalidValueException if given value is invalid
	 */
	@Test
	public void testHeal3() throws InvalidValueException {
		//Healing to less than max health 
		monster.takeDamage(monster.getHealAmount()+10);
		monster.heal();
		assertEquals(90, monster.getHealth());
	}
	
	/**
	 * Monster heals to greater than max health 
	 * @result monster heals to max health without any errors
	 * @throws InvalidValueException if given value is invalid 
	 */
	@Test
	public void testHeal4() throws InvalidValueException {
		//Health exceed max health after healing 
		monster.takeDamage(monster.getHealAmount()/2);
		monster.heal();
		assertEquals(100, monster.getHealth());
	}
	
	/**
	 * Fainted monster heals
	 * @result monster heals and is no longer fainted
	 * @throws InvalidValueException if given value is invalid 
	 */
	@Test
	public void testHeal5() throws InvalidValueException {
		//Healing fainted monster
		monster.takeDamage(monster.getHealth());
		assertEquals(true, monster.getIsFainted());
		monster.heal();
		assertEquals(false, monster.getIsFainted());
	}
	
	/**
	 * Monster heals for given amount 
	 * @result monster heals to exactly max health 
	 * @throws InvalidValueException if given value is invalid
	 */
	@Test
	public void testHealAmount1() throws InvalidValueException {
		//Healing to exactly monster max health
		monster.takeDamage(10);
		monster.heal(10);
		assertEquals(100, monster.getHealth());
	}
	
	/**
	 * Monster heals to greater than max health 
	 * @result monster heals to max health
	 * @throws InvalidValueException if given value is invalid 
	 */
	@Test
	public void testHealAmount2() throws InvalidValueException {
		//Healing for more than monster max health
		monster.takeDamage(10);
		monster.heal(20);
		assertEquals(100, monster.getHealth());
	}
	
	/**
	 * Healing fainted monster 
	 * @result monster heals and is no longer fainted 
	 * @throws InvalidValueException if given value is invalid 
	 */
	@Test
	public void testHealAmount3() throws InvalidValueException {
		//Healing a fainted monster
		monster.takeDamage(monster.getHealth());
		assertEquals(true, monster.getIsFainted());
		assertEquals(0, monster.getHealth());
		monster.heal(20);
		assertEquals(false, monster.getIsFainted());
		assertEquals(20, monster.getHealth());
	}
	
	/**
	 * Monster heals for negative amount 
	 * @result exception is thrown
	 */
	@Test
	public void testHealAmount4() {
		//Healing for negative amount
		try{
			monster.heal(-10);
		}
		catch(InvalidValueException e) {
			assertEquals("Invalid value!", e.getMessage());
		}
	}
	
	/**
	 * Monster attacks, no critical hit 
	 * @result monster deals base damage without critical 
	 * @throws InvalidValueException if given value is invalid 
	 * @throws InvalidTargetException if given monster is fainted 
	 */
	@Test
	public void testAttack1() throws InvalidValueException, InvalidTargetException {
		//Attacking and dealing damage to an enemy, no critical hit
		monster.setRandom(new Random(123));
		Monster enemy = new AverageJoe();
		monster.attack(enemy);
		//Taking into account whether the monster crits or not 
		assertEquals(80, enemy.getHealth());
		assertEquals(100, monster.getHealth());
	}
	
	/**
	 * Monster attacks, critical hit 
	 * @result monster deals 2 times its base damage (critical hit) 
	 * @throws InvalidValueException if given value is invalid 
	 * @throws InvalidTargetException if given monster is fainted 
	 */
	@Test
	public void testAttack2() throws InvalidValueException, InvalidTargetException {
		//Attacking and dealing damage to an enemy, critical hit
		monster.setRandom(new Random(0)); //Generates int of 1  
		Monster enemy = new AverageJoe();
		monster.attack(enemy);
		//Taking into account whether the monster crits or not 
		assertEquals(60, enemy.getHealth());
		assertEquals(100, monster.getHealth());
	}
	
	/**
	 * Monster attacking fainted enemy 
	 * @result exception is thrown
	 * @throws InvalidValueException if given value is invalid 
	 */
	@Test
	public void testAttack3() throws InvalidValueException {
		//Attacking fainted enemy
		Monster enemy = new AverageJoe();
		enemy.takeDamage(enemy.getHealth());
		try {
			monster.attack(enemy);
		}
		catch(InvalidTargetException e) {
			assertEquals("Invalid target!" , e.getMessage());
		}
	}
	
	/**
	 * Calculate final damage, critical hit 
	 * @result correct damage value is returned without any errors
	 */
	@Test
	public void testFinalDamage1() {
		//Critical hit 
		monster.setRandom(new Random(0));
		assertEquals(2*monster.getDamage(), monster.finalDamage());
	}
	
	/**
	 * Calculate final damage, no critical hit 
	 * @result correct damage value is returned without any errors
	 */
	@Test
	public void testFinalDamage2() {
		//No critical hit
		monster.setRandom(new Random(123));
		assertEquals(monster.getDamage(), monster.finalDamage());
	}
	
	/**
	 * Monster is bought 
	 * @result monster is bought and amount is reduced from player's balance 
	 * @throws InsufficientFundsException if player has insufficient funds 
	 * @throws InventoryFullException if inventory is already full 
	 * @throws InvalidValueException if given value is invalid 
	 */
	@Test
	public void testBuy1() throws InsufficientFundsException, InventoryFullException, InvalidValueException {
		//Blue sky
		player.setBalance(monster.getCost());
		shop.setMonsters(new MonsterInventory(4));
		shop.getMonsters().add(monster);
		monster.buy();
		ArrayList<Monster> monsterList = new ArrayList<Monster>();
		monsterList.add(monster);
		assertEquals(0, player.getBalance());
		assertEquals(monsterList, myMonsters);
	}
	
	/**
	 * Monster is not bought, player has insufficient funds 
	 * @result exception is thrown 
	 * @throws InsufficientFundsException if player has insufficient funds 
	 * @throws InventoryFullException if inventory is already full
	 * @throws InvalidValueException if given value is invalid 
	 */
	@Test
	public void testBuy2() throws InsufficientFundsException, InventoryFullException, InvalidValueException {
		//Insufficient fund in player's balance 
		player.setBalance(monster.getCost()/2);
		try {
			monster.buy();
		}
		catch(InsufficientFundsException e) {
			assertEquals("Insufficient funds!", e.getMessage());
		}
	}
	
	/**
	 * Monster is not bought, inventory is full
	 * @result exception is thrown
	 * @throws InsufficientFundsException if player has insufficient funds
	 * @throws InventoryFullException if inventory is already full
	 * @throws InvalidValueException if given value is invalid 
	 */
	@Test
	public void testBuy3() throws InsufficientFundsException, InventoryFullException, InvalidValueException {
		//Inventory full
		player.setBalance(monster.getCost()*5);
		shop.setMonsters(new MonsterInventory(4));
		for(int i = 0; i < 4; i++) {
			shop.getMonsters().add(monster);
			monster.buy();
		}
		try {
			monster.buy();
		}
		catch(InventoryFullException e) {
			assertEquals("Monster inventory is full!", e.getMessage());
		}
	}
	
	/**
	 * Monster is sold 
	 * @result monster is sold and refund amount is added to player's balance 
	 * @throws InventoryFullException if inventory is already full
	 * @throws InsufficientFundsException if player has insufficient funds 
	 * @throws InvalidValueException if given value is invalid 
	 */
	@Test
	public void testSell1() throws InventoryFullException, InsufficientFundsException, InvalidValueException {
		//Blue sky
		player.setBalance(monster.getCost());
		Monster testMonster = new AverageJoe();
		shop.setMonsters(new MonsterInventory(4));
		shop.getMonsters().add(testMonster);
		testMonster.buy();
		testMonster.sell();
		ArrayList<Monster> monsterList = new ArrayList<Monster>();
		assertEquals(30, player.getBalance());
		assertEquals(monsterList, myMonsters);
	}
	
	/**
	 * Monster is sold, multiple instance of same item 
	 * @result one instance of item is sold, other is untouched 
	 * @throws InventoryFullException if inventory is full
	 * @throws InsufficientFundsException if player has insufficient funds 
	 * @throws InvalidValueException if given value is invalid
	 */
	@Test
	public void testSell2() throws InventoryFullException, InsufficientFundsException, InvalidValueException {
		//Multiple items of same type
		player.setBalance(monster.getCost()*3);
		Monster testMonster = new AverageJoe();
		shop.setMonsters(new MonsterInventory(4));
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
		assertEquals(monsterList, myMonsters);
	}

	/**
	 * Monster levels up at maximum level
	 * @result exception is thrown
	 * @throws StatMaxedOutException if monster stat is already maxed out
	 */
	@Test
	public void testLevelUp1() throws StatMaxedOutException {
		//Monster is maxed alredy at max level
		monster.setLevel(monster.getMaxLevel());
		try {
			monster.levelUp();
		}
		catch(StatMaxedOutException e) {
			assertEquals("Monster is already max level!", e.getMessage());
		}
	}
	
	/**
	 * Monster levels up once 
	 * @result monster levels up without any errors
	 * @throws StatMaxedOutException if monster stat is already maxed out 
	 */
	@Test
	public void testLevelUp2() throws StatMaxedOutException {
		//Monster levels up 
		monster.levelUp();
		assertEquals(2, monster.getLevel());
	}
	
	/**
	 * Set name of monster with various types of names 
	 * @result sets name without any errors unless name does not meet criteria 
	 * @throws InvalidValueException if given name is invalid
	 */
	@Test
	public void testSetName() throws InvalidValueException {
		// Blue sky
		monster.setName("john");
		assertEquals("john", monster.getName());
		
		monster.setName("ABC   Abc   abc");
		assertEquals("ABC   Abc   abc", monster.getName());
		
		monster.setName("  whitespace               ");
		assertEquals("whitespace", monster.getName());
		
		// Invalid name error
		String[] testNames = {"aj", "", "Abc   Abc   AbcZ", "123", "Mikey5x", "!WoW", "oops_"};
		for (String name : testNames) {			
			try {			
				monster.setName(name);
			}
			catch (InvalidValueException e){
				assertEquals("Invalid monster name! Try again:", e.getMessage());
			}
		}
	}
	
	/**
	 * Change max level of monster based on difficulty 
	 * @result max level of monster is changed without any errors 
	 */
	@Test
	public void testChangeMaxLevel() {
		//Default, Easy
		monster.changeMaxLevel(Difficulty.EASY);
		assertEquals(4, monster.getMaxLevel());
		
		//Normal
		monster.changeMaxLevel(Difficulty.NORMAL);
		assertEquals(6, monster.getMaxLevel());
		
		//Hard
		monster.changeMaxLevel(Difficulty.HARD);
		assertEquals(8, monster.getMaxLevel());
	}
	
	/**
	 * Checks string representation of monster 
	 * @result correct string is returned without any errors 
	 */
	@Test
	public void testToString() {
		assertEquals(String.format("%-14s    health: %-3s    max health: %-3s    damage: %-3s    cost: %-3s    "
						+ "level: %-1s    max level: %-1s    heal amount: %-3s    crit rate: %-3s%%    fainted: %-5s",
				monster.getName(), monster.getHealth(), monster.getMaxHealth(), monster.getDamage(), monster.getCost(), monster.getLevel(), monster.getMaxLevel(), monster.getHealAmount(), (int) (monster.getCritRate() * 100), monster.getIsFainted()), monster.toString());
	}
	
	/**
	 * Checks string representation of view option, monster stats
	 * @result correct string is returned without any errors
	 */
	@Test
	public void testView1() {
		//Viewing monster stats
		String result = "";
    	if (shop.getMonsters().contains(monster)) {
    		result += String.format("\nBalance: %s\n\n", player.getBalance());
    	}
    	result += "Monster: " + monster.getClass().getSimpleName() + "\n";
    	if (!monster.getName().equals(getClass().getSimpleName())){
    		result += "Name: " + monster.getName() + "\n";
    	}
    	result += monster.getDescription() + "\n";
    	result += "Health: " + monster.getHealth() + "\n";
    	result += "Max Health: " + monster.getMaxHealth() + "\n";
    	result += "Damage: " + monster.getDamage() + "\n";
    	result += "Cost: " + monster.getCost() + "\n";
    	result += "Level: " + monster.getLevel() + "\n";
    	result += "Heal Amount: " + monster.getHealAmount() + "\n";
    	result += "Crit Rate: " + (int) (monster.getCritRate() * 100) + "%\n";
    	result += "Fainted: " + monster.getIsFainted() + "\n";
    	result += "Max Level: " + monster.getMaxLevel() + "\n";
    	if (shop.getMonsters().contains(monster)) {
    		result += "\n1: Buy";
    		result += "\n2: Go back";
    	}
    	if (player.getMonsters().contains(monster)) {
    		result += "\n1: Rename";
    		result += "\n2: Sell";
    		result += "\n3: Go back";
    	}
    	
    	assertEquals(result, monster.view());
	}
	
	/**
	 * Checks string representation of view option (in player's inventory)
	 * @result correct string is returned without any errors
	 */
	@Test
	public void testView2() throws InventoryFullException {
		//Viewing monster in player's inventory
		String result = "";
		player.getMonsters().add(monster);
    	if (shop.getMonsters().contains(monster)) {
    		result += String.format("\nBalance: %s\n\n", player.getBalance());
    	}
    	result += "Monster: " + monster.getClass().getSimpleName() + "\n";
    	if (!monster.getName().equals(getClass().getSimpleName())){
    		result += "Name: " + monster.getName() + "\n";
    	}
    	result += monster.getDescription() + "\n";
    	result += "Health: " + monster.getHealth() + "\n";
    	result += "Max Health: " + monster.getMaxHealth() + "\n";
    	result += "Damage: " + monster.getDamage() + "\n";
    	result += "Cost: " + monster.getCost() + "\n";
    	result += "Level: " + monster.getLevel() + "\n";
    	result += "Heal Amount: " + monster.getHealAmount() + "\n";
    	result += "Crit Rate: " + (int) (monster.getCritRate() * 100) + "%\n";
    	result += "Fainted: " + monster.getIsFainted() + "\n";
    	result += "Max Level: " + monster.getMaxLevel() + "\n";
    	if (shop.getMonsters().contains(monster)) {
    		result += "\n1: Buy";
    		result += "\n2: Go back";
    	}
    	if (player.getMonsters().contains(monster)) {
    		result += "\n1: Rename";
    		result += "\n2: Sell";
    		result += "\n3: Go back";
    	}
    	
    	assertEquals(result, monster.view());
	}
	
	/**
	 * Checks string representation of view option (in shop)
	 * @result correct string is returned without any errors
	 */
	@Test
	public void testView3() throws InventoryFullException {
		//Viewing monster in shop
		String result = "";
		shop.getMonsters().remove(0);
		shop.getMonsters().add(monster);
    	if (shop.getMonsters().contains(monster)) {
    		result += String.format("\nBalance: %s\n\n", player.getBalance());
    	}
    	result += "Monster: " + monster.getClass().getSimpleName() + "\n";
    	if (!monster.getName().equals(getClass().getSimpleName())){
    		result += "Name: " + monster.getName() + "\n";
    	}
    	result += monster.getDescription() + "\n";
    	result += "Health: " + monster.getHealth() + "\n";
    	result += "Max Health: " + monster.getMaxHealth() + "\n";
    	result += "Damage: " + monster.getDamage() + "\n";
    	result += "Cost: " + monster.getCost() + "\n";
    	result += "Level: " + monster.getLevel() + "\n";
    	result += "Heal Amount: " + monster.getHealAmount() + "\n";
    	result += "Crit Rate: " + (int) (monster.getCritRate() * 100) + "%\n";
    	result += "Fainted: " + monster.getIsFainted() + "\n";
    	result += "Max Level: " + monster.getMaxLevel() + "\n";
    	if (shop.getMonsters().contains(monster)) {
    		result += "\n1: Buy";
    		result += "\n2: Go back";
    	}
    	if (player.getMonsters().contains(monster)) {
    		result += "\n1: Rename";
    		result += "\n2: Sell";
    		result += "\n3: Go back";
    	}
    	
    	assertEquals(result, monster.view());
	}

}
