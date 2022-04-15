package main;
import java.util.ArrayList;
import java.util.Random;

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
    private ArrayList<Monster> enemyMonsters;
    private GameEnvironment game;
    private Inventory inventory;
    
    
    private enum Turn {
    	PLAYER,
    	ENEMY
    }
    
    
    /** 
     * Constructors
     * 
     * */
    public Battle (GameEnvironment game, ArrayList<Monster> monsterList) {
    	this.game = game;
    	this.inventory = game.getInventory();
    	enemyMonsters = monsterList;
    };
    

    /** 
     * Getters and Setters
     * 
     * */


    /**
     * Set the value of currentTurn
     * @param currentTurn the new value of currentTurn
     */
    public void setCurrentTurn (Turn currentTurn) {
        this.currentTurn = currentTurn;
    }

    /**
     * Get the value of currentTurn
     * @return the value of currentTurn
     */
    public Turn getCurrentTurn () {
        return currentTurn;
    }


    /** 
     * Functional
     * 
     * */
    
    
    /**
     * Returns a random non fainted monsters from the list.
     * @param monsterList
     * @return a random non fainted monster from the given list of monsters
     */
    public Monster randomMonster(ArrayList<Monster> monsterList) {
    	
    	Random random = new Random();
    	boolean monsterFound = false;
    	Monster selectedMonster = null;
    	
    	while (!monsterFound) {
    		int index = random.nextInt(monsterList.size());
    		selectedMonster = monsterList.get(index);
    		if (!selectedMonster.getIsFainted()) {
    			monsterFound = true;
    		}
    	}
    	
		return selectedMonster;
    }
    
    /**
     * choose a random player monster to attack a random enemy monster
	 * and turn over to the enemy
     */
    public void playerAttack()
    {
    	Monster attackingMonster = randomMonster(inventory.getMyMonsters());
    	Monster defendingMonster = randomMonster(enemyMonsters);
    	attackingMonster.attack(defendingMonster);
    	currentTurn = Turn.ENEMY;
    }


    /**
     * choose a random enemy monster to attack a random player monster
	 * turn over to the player
     */
    public void enemyAttack()
    {
    	Monster attackingMonster = randomMonster(enemyMonsters);
    	Monster defendingMonster = randomMonster(inventory.getMyMonsters());
    	attackingMonster.attack(defendingMonster);
    	currentTurn = Turn.PLAYER;
    }


    /**
     * Play the game.
     * Start with the player attacking and takes turns going to enemy and back to player.
     * Checks status after each attack.
     * Repeat until one side's team is all fainted.
     */
    public void play()
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
    }
    
    
    /**
     * checks the status of the two sides.
     * If the player's monsters have all fainted, then the player loses.
     * If the enemy's monsters have all fainted, then the player wins.
     */
    public void checkStatus() {
    	if (allMonstersFainted(inventory.getMyMonsters())) {
    		lose();
    	}
    	if (allMonstersFainted(enemyMonsters)) {
    		win();
    	}
    }
    
    
    /**
     * checks whether a list of monsters have all fainted.
     * @param monsterList
     * @return whether the monsters are all fainted
     */
    public boolean allMonstersFainted(ArrayList<Monster> monsterList) {
    	boolean dead = true;
    	for (Monster monster : monsterList) {
    		if (!monster.getIsFainted()) {
    			dead = false;
    		}
    	}
    	return dead;
    }
    

    /**
     */
    public void win()
    {
    	winner = Turn.PLAYER;
    	// do some stuff here
    }


    /**
     */
    public void lose()
    {
    	winner = Turn.ENEMY;
    	// do some stuff here
    }


}
