package main;

/**
 * Class Battle
 * The player will have at most four monsters.
Once the player has picked a battle,
 * his monsters will fight against the enemy monsters by attacking in a random
 * order. The attacks will take turn one by the player and one by the computer
 * until one team has fainted all monsters.
 */
public class Battle {
	
    /** 
     * Variables
     * 
     * */
    private Turn currentTurn;
    private Turn winner;
    private GameEnvironment game;
    private MonsterInventory enemyMonsters;
    private MonsterInventory playerMonsters;
    
    
    private enum Turn {
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
    	enemyMonsters = new MonsterInventory(game);
    };
    
    
    public Battle (GameEnvironment game, MonsterInventory monsterInventory) {
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
	public MonsterInventory getEnemyMonsters() {
		return enemyMonsters;
	}


	/**
	 * @param enemyMonsters the enemyMonsters to set
	 */
	public void setEnemyMonsters(MonsterInventory enemyMonsters) {
		this.enemyMonsters = enemyMonsters;
	}


	/**
	 * @return the playerMonsters
	 */
	public MonsterInventory getPlayerMonsters() {
		return playerMonsters;
	}


	/**
	 * @param playerMonsters the playerMonsters to set
	 */
	public void setPlayerMonsters(MonsterInventory playerMonsters) {
		this.playerMonsters = playerMonsters;
	}


    /** 
     * Functional
     * 
     * */
    
    /**
     * choose a random player monster to attack a random enemy monster
	 * and turn over to the enemy
     * @throws InvalidValueException 
     * @throws InvalidTargetException 
     */
    public void playerAttack() throws InvalidValueException, InvalidTargetException
    {
    	Monster playerMonster = getPlayerMonsters().random();
    	Monster enemyMonster = getEnemyMonsters().random();
    	playerMonster.attack(enemyMonster);
    	currentTurn = Turn.ENEMY;
    	
    	System.out.println(String.format("\nPlayer %s attacked enemy %s for %s damage.", 
    			playerMonster.getName(), enemyMonster.getName(), 999));
    	if (enemyMonster.getIsFainted()) {
    		System.out.println(String.format("Enemy %s has fainted!", enemyMonster.getName()));
    	}
    	else {    		
    		System.out.println(String.format("Enemy %s now has %s health.", 
    				enemyMonster.getName(), enemyMonster.getHealth()));
    	}
    }


    /**
     * choose a random enemy monster to attack a random player monster
	 * turn over to the player
     * @throws InvalidValueException 
     * @throws InvalidTargetException 
     */
    public void enemyAttack() throws InvalidValueException, InvalidTargetException
    {
    	Monster enemyMonster = getEnemyMonsters().random();
    	Monster playerMonster = getPlayerMonsters().random();
    	enemyMonster.attack(playerMonster);
    	currentTurn = Turn.PLAYER;
    	
    	System.out.println(String.format("\nEnemy %s attacked player %s for %s damage.", 
    			enemyMonster.getName(), playerMonster.getName(), 999));
    	if (playerMonster.getIsFainted()) {
    		System.out.println(String.format("Player %s has fainted!", playerMonster.getName()));
    	}
    	else {    		
    		System.out.println(String.format("Player %s now has %s health.", 
    				playerMonster.getName(), playerMonster.getHealth()));
    	}
    }


    /**
     * Play the game.
     * Start with the player attacking and takes turns going to enemy and back to player.
     * Checks status after each attack.
     * Repeat until one side's team is all fainted.
     * @throws InvalidValueException 
     * @throws InvalidTargetException 
     */
    public void play() throws InvalidValueException, InvalidTargetException
    {
    	currentTurn = Turn.PLAYER;
    	
    	while (winner == null) {
    		switch (currentTurn) {
	    		case PLAYER:
	    			playerAttack();
	    			break;
	    		case ENEMY:
	    			enemyAttack();
	    			break;
    		}
    		checkStatus();
    	}
    	game.getBattleList().remove(this);
    }
    
    
    /**
     * checks the status of the two sides.
     * If the player's monsters have all fainted, then the player loses.
     * If the enemy's monsters have all fainted, then the player wins.
     */
    public void checkStatus() {
    	if (playerMonsters.allFainted()) {
    		lose();
    	}
    	if (enemyMonsters.allFainted()) {
    		win();
    	}
    }

    
    /**
     * Player wins the game
     * 
     */
    public void win()
    {
    	winner = Turn.PLAYER;
    	System.out.println("You won!");
    	System.out.println(playerMonsters);
    	// add to player balance
    	// add to player score
    }


    /**
     * Player loses the game
     * 
     */
    public void lose()
    {
    	winner = Turn.ENEMY;
    	System.out.println("You lost!");
    	System.out.println(enemyMonsters);
    }
    
    
    public String toString() {
    	String result = "";
    	for (int i = 0; i < enemyMonsters.getMonsterList().size(); i++) {
    		Monster monster = enemyMonsters.getMonsterList().get(i);
    		result += String.format("\n%s. %s", i + 1, monster);
    	}
    	return result;
    }

}
