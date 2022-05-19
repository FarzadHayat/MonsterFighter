package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.*;

/**
 * Unit test for Shanny class
 * @author Farzad and Daniel
 */

class ShopTest {

	/**
	 * Fields
	 */
	private GameEnvironment game;
	private Shop shop;
	private Player player;
	private MonsterInventory monsters;
	private ItemInventory items;
	
	/**
	 * Assign values to fields used in unit test
	 * @throws Exception if any exception is caught
	 */
	@BeforeEach
	void setUp() throws Exception {
		game = new GameEnvironment();
		game.setupGame();
		shop = game.getShop();
		player = game.getPlayer();
		monsters = shop.getMonsters();
		items = shop.getItems();
	}

	/**
	 * Randomise monsters and items in shop
	 * @result monsters and items lists are randomised without any errors 
	 */
	@Test
	void testRandomise() {
		ArrayList<Monster> monstersList = monsters;
		ArrayList<Item> itemsList = items;
		shop.randomise();
		assertTrue(monstersList != monsters);
		assertTrue(itemsList != items);
	}

	/**
	 * Checks string representation of shop 
	 * @result correct string is returned without any errors 
	 */
	@Test
	void testToString() {
		String result = String.format("\nBalance: %s\n", player.getBalance());
    	int start = 1;
    	result += "\n===== MONSTERS =====\n\n";
    	for (Monster monster : monsters) {
    		result += String.format("%s: %s\n", start, monster);
    		start++;
    	}
    	result += "\n===== ITEMS =====\n\n";
    	for (Item item : items) {
    		result += String.format("%s: %s\n", start, item);
    		start++;
    	}
    	result += String.format("\n%s: Go back", start);
    	assertEquals(result, shop.toString());
	}

}
