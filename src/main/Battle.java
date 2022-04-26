package main;

import java.util.Random;

public class Battle {
	
    /** 
     * Fields
     */
    private Turn currentTurn = Turn.PLAYER;
    private Turn winner;
    
    private GameEnvironment game;
    private Player player;
    private MonsterInventory monsters;
    
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
    	size = inventorySize();
    	monsters = new MonsterInventory(size, game);
    	monsters.randomise();
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
	public MonsterInventory getEnemyMonsters() {
		return monsters;
	}


	/**
	 * Set the value of monsters
	 * @param monsters the new value of monsters
	 */
	public void setEnemyMonsters(MonsterInventory enemyMonsters) {
		this.monsters = enemyMonsters;
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
    	result += "\nYour monsters:";
    	result += player.getMonsters();
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
    	result += "\nEnemy monsters:";
    	result += monsters;
    	game.getScoreSystem().addBattlesLost();
    	return result;
    }
    
    
    public int inventorySize() {
    	int minSize = 1;
    	int maxsize = player.getMonsters().getMaxSize();
    	Random random = new Random();
    	int size = random.nextInt(minSize, maxsize + 1);
    	return size;
    }
    
    
    /**
     * @return result the string representation of the battle object
     */
    public String toString() {
    	int index = game.getBattles().getList().indexOf(this);
    	String result = String.format("Battle %s\n %s\n", index + 1, monsters);
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
