package main;

import java.util.Random;

/**
 * Class Battle
 * The player will have at most four monsters.
Once the player has picked a battle,
 * his monsters will fight against the enemy monsters by attacking in a random
 * order. The attacks will take turn one by the player and one by the computer
 * until one team has fainted all monsters.
 */
public class Battle implements Storable {
	
    /** 
     * Variables
     * 
     * */
    private Turn currentTurn = Turn.PLAYER;
    private Turn winner;
    private GameEnvironment game;
    private Inventory<Monster> enemyMonsters;
    private Inventory<Monster> playerMonsters;
    
    
    public enum Turn {
    	PLAYER,
    	ENEMY
    }
    
    
    /** 
     * Constructors
     * 
     * */
    public Battle (GameEnvironment game) {
    	this.game = game;
    	playerMonsters = game.getMyMonsters();
    	enemyMonsters = new Inventory<Monster>(4, game);
    };
    
    
    public Battle (GameEnvironment game, Inventory<Monster> monsterInventory) {
    	this.game = game;
    	playerMonsters = game.getMyMonsters();
    	enemyMonsters = monsterInventory;
    };
    

    /** 
     * Getters and Setters
     * 
     * */


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
	 * @return the enemyMonsters
	 */
	public Inventory<Monster> getEnemyMonsters() {
		return enemyMonsters;
	}


	/**
	 * @param enemyMonsters the enemyMonsters to set
	 */
	public void setEnemyMonsters(Inventory<Monster> enemyMonsters) {
		this.enemyMonsters = enemyMonsters;
	}


	/**
	 * @return the playerMonsters
	 */
	public Inventory<Monster> getPlayerMonsters() {
		return playerMonsters;
	}


	/**
	 * @param playerMonsters the playerMonsters to set
	 */
	public void setPlayerMonsters(Inventory<Monster> playerMonsters) {
		this.playerMonsters = playerMonsters;
	}


    /** 
     * Functional
     * 
     * */
    
	
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
	
	
	public void setup() throws PurchasableNotFoundException {
		if (game.getMyMonsters().isEmpty()) {
    		throw new PurchasableNotFoundException("Battle not available: Player has no monsters! Try again...");
    	}
		if (Inventory.allFainted(game.getMyMonsters())) {
    		throw new PurchasableNotFoundException("Battle not available: Player monsters are all fainted! Try again...");
    	}
	}
	
	
    /**
     * choose a random player monster to attack a random enemy monster
	 * and turn over to the enemy
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
     * choose a random enemy monster to attack a random player monster
	 * turn over to the player
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
     * Play the game.
     * Start with the player attacking and takes turns going to enemy and back to player.
     * Checks status after each attack.
     * Repeat until one side's team is all fainted.
     * @throws PurchasableNotFoundException 
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
     * checks the status of the two sides.
     * If the player's monsters have all fainted, then the player loses.
     * If the enemy's monsters have all fainted, then the player wins.
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
     * Player wins the game
     * 
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
     * Player loses the game
     * 
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
    
    
    public String toString() {
    	int index = game.getBattles().indexOf(this);
    	return String.format("Battle %s\n %s\n", index + 1, enemyMonsters);
    }
    
    
    public String view() {
    	String result = toString();
    	result += "\n1: Fight";
    	result += "\n2: Go back";
    	return result;
    }


	@Override
	public String getName() {
		return null;
	}

}
