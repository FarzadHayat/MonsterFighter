package main;

import java.util.Random;

public class Battle implements Storable {
	
    /** 
     * Fields
     */
    private Turn currentTurn = Turn.PLAYER;
    private Turn winner;
    private GameEnvironment game;
    private Inventory<Monster> enemyMonsters;
    private Inventory<Monster> playerMonsters;
    
    
    /** 
     * Constructors
     */
    
    /**
     * Create a new Battle object.
     * Set the value of game to the given GameEnvironment object.
     * @param game the given GameEnvironment object
     */
    public Battle (GameEnvironment game) {
    	this.game = game;
    	playerMonsters = game.getMyMonsters();
    	enemyMonsters = new Inventory<Monster>(4, game);
    };
    
    
    /**
     * Create a new Battle object.
     * Set the value of game to the given GameEnvironment object.
     * Set the value of enemyMonsters to the given Inventory object.
     * @param game the given GameEnvironment object
     * @param monsterInventory the given Monster Inventory
     */
    public Battle (GameEnvironment game, Inventory<Monster> monsterInventory) {
    	this.game = game;
    	playerMonsters = game.getMyMonsters();
    	enemyMonsters = monsterInventory;
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
     * Get the value of enemyMonsters
	 * @return the value of enemyMonsters
	 */
	public Inventory<Monster> getEnemyMonsters() {
		return enemyMonsters;
	}


	/**
	 * Set the value of enemyMonsters
	 * @param enemyMonsters the new value of enemyMonsters
	 */
	public void setEnemyMonsters(Inventory<Monster> enemyMonsters) {
		this.enemyMonsters = enemyMonsters;
	}


	/**
	 * Get the value of playerMonsters
	 * @return the value of playerMonsters
	 */
	public Inventory<Monster> getPlayerMonsters() {
		return playerMonsters;
	}


	/**
	 * Set the value of playerMonsters
	 * @param playerMonsters the new value of playerMonsters
	 */
	public void setPlayerMonsters(Inventory<Monster> playerMonsters) {
		this.playerMonsters = playerMonsters;
	}


    /** 
     * Functional
     * 
     * */
    
	/**
	 * Get a random non fainted monster from the given Monster Inventory.
	 * @param inventory the given Monster Inventory
	 * @return the randomly selected monster
	 */
	public Monster random(Inventory<Monster> inventory) {
		Random random = new Random();
    	boolean found = false;
    	Monster monster = null;
    	
    	while (!found) {
    		int index = random.nextInt(inventory.size());
    		monster = inventory.get(index);
    		if (!monster.getIsFainted()) {
    			found = true;
    		}
    	}
    	
		return monster;
	}
	
	
	/**
	 * Check that the player monster inventory contains at least one non fainted monster.
	 * @throws PurchasableNotFoundException if the player has no non fainted monsters in their team
	 */
	public void setup() throws PurchasableNotFoundException {
		if (game.getMyMonsters().isEmpty()) {
    		throw new PurchasableNotFoundException("Battle not available: Player has no monsters! Try again...");
    	}
		if (Inventory.allFainted(game.getMyMonsters())) {
    		throw new PurchasableNotFoundException("Battle not available: Player monsters are all fainted! Try again...");
    	}
	}
	
	
    /**
     * choose a random player monster to attack a random enemy monster.
	 * Turn over to the enemy.
	 * @return result the commentary of the events that occurred during the attack
     */
    public String playerAttack() {
    	Monster playerMonster = random(getPlayerMonsters());
    	Monster enemyMonster = random(getEnemyMonsters());
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
    	Monster enemyMonster = random(getEnemyMonsters());
    	Monster playerMonster = random(getPlayerMonsters());
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
     * @throws PurchasableNotFoundException if the player has no non fainted monsters in their team
     */
    public String play() throws PurchasableNotFoundException {
    	setup();
    	String result = "";
    	while (winner == null) {
    		playTurn();
    		result += checkStatus();
    	}
    	try {
    		game.getBattles().remove(this);
    	}
    	catch (PurchasableNotFoundException e) {
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
    	if (Inventory.allFainted(playerMonsters)) {
    		result = lose();
    	}
    	if (Inventory.allFainted(enemyMonsters)) {
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
    	String result = "You won!";
    	result += "\nYour monsters:";
    	result += playerMonsters;
    	game.getScoreSystem().addBattlesWon();
    	try {    		
    		game.addBalance(0);
    		game.getScoreSystem().addScore(0);
    	}
    	catch (InvalidValueException e) {
    		e.printStackTrace();
    	}
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
    	String result = "You lost!";
    	result += "\nEnemy monsters:";
    	result += enemyMonsters;
    	game.getScoreSystem().addBattlesLost();
    	return result;
    }
    
    
    /**
     * @return result the string representation of the battle object
     */
    public String toString() {
    	int index = game.getBattles().indexOf(this);
    	String result = String.format("Battle %s\n %s\n", index + 1, enemyMonsters);
    	return result;
    }
    
    
    /**
     * @return result the string representation of the battle object followed by command line options
     */
    public String view() {
    	String result = toString();
    	result += "\n1: Fight";
    	result += "\n2: Go back";
    	return result;
    }


    /**
     * Battles do not have names.
     * @return null
     */
	@Override
	public String getName() {
		return null;
	}

}
