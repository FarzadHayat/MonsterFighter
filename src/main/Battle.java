package main;

import java.util.ArrayList;
import java.util.Random;

import exceptions.InvalidTargetException;
import exceptions.InvalidValueException;
import exceptions.NotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Battle {
	
    /** 
     * Fields
     */
    private Turn currentTurn = Turn.PLAYER;
    private Turn winner;
    
    private GameEnvironment game;
    private Player player;
    private MonsterInventory monsters;
    
    private String name;
    private int size;
    private int balanceMultiplier = 25;
    private int scoreMultiplier = 50;
    
    
    /** 
     * Constructors
     */
    
    /**
     * Create a new Battle object.
     * Set the value of game to the given GameEnvironment object.
     * Randomize the enemy monsters inventory.
     * @param game the given GameEnvironment object
     */
    public Battle (GameEnvironment game) {
    	this.game = game;
    	player = game.getPlayer();
    	size = randomSize();
    	monsters = new MonsterInventory(size, game);
    	monsters.randomise();
    	name = randomName();
    };
    

    /** 
     * Getters and Setters
     */

	/**
     * Get the value of currentTurn
     * @return the value of currentTurn
     */
    public Turn getCurrentTurn () {
        return currentTurn;
    }
    
    
    /**
     * Set the value of currentTurn
     * @param currentTurn the new value of currentTurn
     */
    public void setCurrentTurn (Turn currentTurn) {
    	this.currentTurn = currentTurn;
    }

    
    /**
     * Get the value of winner
     * @return the value of winner
     */
    public Turn getWinner () {
        return winner;
    }
    
    
    /**
     * Set the value of winner
     * @param winner the new value of winner
     */
    public void setWinner (Turn winner) {
    	this.winner = winner;
    }

    
    /**
     * Get the value of monsters
	 * @return the value of monsters
	 */
	public MonsterInventory getMonsters() {
		return monsters;
	}


	/**
	 * Set the value of monsters
	 * @param monsters the new value of monsters
	 */
	public void setMonsters(MonsterInventory monsters) {
		this.monsters = monsters;
	}
	
	
	/**
     * Get the value of name
	 * @return the value of name
	 */
	public String getName() {
		return name;
	}
	
	
	/**
	 * Set the value of name
	 * @param name the new value of name
	 */
	public void setName(String name) {
		this.name = name;
	}

    /** 
     * Functional
     */


	/**
	 * Check that the player monster inventory contains at least one non fainted monster.
	 * @throws NotFoundException if the player has no non fainted monsters in their team
	 */
	public void setup() throws NotFoundException {
		if (player.getMonsters().isEmpty()) {
    		throw new NotFoundException("Battle not available: Player has no monsters! Try again...");
    	}
		if (player.getMonsters().allFainted()) {
    		throw new NotFoundException("Battle not available: Player monsters are all fainted! Try again...");
    	}
	}
	
	
    /**
     * choose a random player monster to attack a random enemy monster.
	 * Turn over to the enemy.
	 * @return result the commentary of the events that occurred during the attack
     */
    public String playerAttack() {
    	Monster playerMonster = player.getMonsters().random();
    	Monster enemyMonster = monsters.random();
    	int damageDealt = 0;
    	try {
    		damageDealt = playerMonster.attack(enemyMonster);
    	}
    	catch (InvalidValueException | InvalidTargetException e) {
    		e.printStackTrace();
    	}
    	currentTurn = Turn.ENEMY;
    	
    	String result = String.format("Player %s attacked enemy %s for %s damage.\n", 
    			playerMonster.getName(), enemyMonster.getName(), damageDealt);
    	if (enemyMonster.getIsFainted()) {
    		result += String.format("Enemy %s has fainted!", enemyMonster.getName());
    	}
    	else {    		
    		result += String.format("Enemy %s now has %s health.", 
    				enemyMonster.getName(), enemyMonster.getHealth());
    	}
		return result;
    }


    /**
     * choose a random enemy monster to attack a random player monster.
	 * Turn over to the player.
	 * @return result the commentary of the events that occurred during the attack
     */
    public String enemyAttack() {
    	Monster enemyMonster = monsters.random();
    	Monster playerMonster = player.getMonsters().random();
    	int damageDealt = 0;
    	try {    		
    		damageDealt = enemyMonster.attack(playerMonster);
    	}
    	catch (InvalidValueException | InvalidTargetException e) {
    		e.printStackTrace();
    	}
    	currentTurn = Turn.PLAYER;
    	
    	String result = String.format("Enemy %s attacked player %s for %s damage.\n", 
    			enemyMonster.getName(), playerMonster.getName(), damageDealt);
    	if (playerMonster.getIsFainted()) {
    		result += String.format("Player %s has fainted!", playerMonster.getName());
    	}
    	else {    		
    		result += String.format("Player %s now has %s health.", 
    				playerMonster.getName(), playerMonster.getHealth());
    	}
		return result;
    }

    
    /**
     * Play the next turn in the battle.
     * @return result the commentary of the events that happened during the turn
     */
    public String playTurn() {
    	String result = "";
    	switch (currentTurn) {
		case PLAYER:
			result += playerAttack();
			break;
		case ENEMY:
			result += enemyAttack();
			break;
		}
    	return result;
    }
    

    /**
     * Play the battle.
     * Start with the player attacking and take turns alternating between enemy and player.
     * Checks the player team before the game to make sure they have at least one non fainted monster.
     * Checks the status of the battle after each turn.
     * Stop when one team's monsters have all fainted.
     * @param result the commentary of the battle
     * @throws NotFoundException if the player has no non fainted monsters in their team
     */
    public String play() throws NotFoundException {
    	setup();
    	String result = "";
    	while (winner == null) {
    		playTurn();
    		result += checkStatus();
    	}
    	try {
    		game.getBattles().remove(this);
    	}
    	catch (NotFoundException e) {
    		e.printStackTrace();
    	}
    	return result;
    }
    
    
    /**
     * checks the status of the battle.
     * If the player's monsters have all fainted, then the player loses.
     * If the enemy's monsters have all fainted, then the player wins.
     * @result result the commentary of the battle's outcome
     */
    public String checkStatus() {
    	String result = "";
    	if (player.getMonsters().allFainted()) {
    		result = lose();
    	}
    	if (monsters.allFainted()) {
    		result = win();
    	}
    	return result;
    }

    
    /**
     * Player wins the game.
     * Add to the battles won.
     * Add rewards to the player balance and score.
     * @return result the commentary of the player winning the battle
     */
    public String win()
    {
    	winner = Turn.PLAYER;
    	String result = "\nYou won!";
    	game.getScoreSystem().addBattlesWon();
    	
    	int balanceReward = balanceMultiplier * size;
    	int scoreReward = scoreMultiplier * size;
    	
    	try {    		
    		player.addBalance(balanceReward);
    		game.getScoreSystem().addScore(scoreReward);
    	}
    	catch (InvalidValueException e) {
    		e.printStackTrace();
    	}
    	
    	result += String.format("\nYou have gained %s gold!", balanceReward);
    	result += String.format("\nYou have gained %s score!", scoreReward);
    	
    	return result;
    }


    /**
     * Player loses the game.
     * Add to the battles lost.
     * @return result the commentary of the player losing the battle
     */
    public String lose()
    {
    	winner = Turn.ENEMY;
    	String result = "\nYou lost!";
    	game.getScoreSystem().addBattlesLost();
    	return result;
    }
    
    
    /**
     * get a random number between 1 and the maximum numbers of monsters that the player can have
     * @return the inventory size
     */
    public int randomSize() {
    	int minSize = 1;
    	int maxsize = player.getMonsters().getMaxSize();
    	Random random = new Random();
    	int size = random.nextInt(minSize, maxsize + 1);
    	return size;
    }
    
    
    /**
     * get a random battle name from the given file
     * @return the selected name
     */
    public String randomName() {
    	ArrayList<String> battleNames = new ArrayList<String>();
    	try {
    		File myFile = new File(System.getProperty("user.dir") + "/src/resources/battle-names.txt");
    		Scanner scanner = new Scanner(myFile);
    		while (scanner.hasNextLine()) {
    			String name = scanner.nextLine();
    			battleNames.add(name);
    		}
    		scanner.close();
    	}
    	catch (FileNotFoundException e) {
    		e.printStackTrace();
    	}
    	Random random = new Random();
    	int i = random.nextInt(0, battleNames.size());
    	String selectedName = battleNames.get(i);
		return selectedName;
    }
    
    /**
     * @return result the string representation of the battle object
     */
    public String toString() {
    	String result = String.format("Battle %s\n %s\n", name, monsters);
    	return result;
    }
    
    
    /**
     * @return result the string representation of the battle object with by command line options
     */
    public String view() {
    	String result = toString();
    	result += "\n1: Fight";
    	result += "\n2: Go back";
    	return result;
    }

}
