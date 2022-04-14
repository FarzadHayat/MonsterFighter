package main;
import java.util.Scanner;

public class GameEnvironment {

    private int balance;
    private String playerName;
    private int numDays;
    private String difficulty;
    private Inventory inventory;
    
    public GameEnvironment () {
    	balance = 0;
    	inventory = new Inventory();
    	setupGame();
    };


    /**
     * Set the value of balance
     * @param balance the new value of balance
     */
    public void setBalance (int balance) {
        this.balance = balance;
    }

    /**
     * Get the value of balance
     * @return the value of balance
     */
    public int getBalance () {
        return balance;
    }

    /**
     * Set the value of name
     * @param playerName the new value of name
     */
    public void setPlayerName (String playerName) {
    	this.playerName = playerName;
    }

    /**
     * Get the value of name
     * @return the value of name
     */
    public String getName () {
        return playerName;
    }

    /**
     * Set the value of numDays
     * @param numDays the new value of numDays
     */
    public void setNumDays (int numDays) {
        this.numDays = numDays;
    }

    /**
     * Get the value of numDays
     * @return the value of numDays
     */
    public int getNumDays () {
        return numDays;
    }

    /**
     * Set the value of difficulty
     * @param difficulty the new value of difficulty
     */
    public void setDifficulty (String difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Get the value of difficulty
     * @return the value of difficulty
     */
    public String getDifficulty () {
        return difficulty;
    }

    //
    // Other methods
    //

    /**
     */
    public void sleep()
    {
    }


    /**
     * View the shop page.
     */
    public void visitShop()
    {
    }


    /**
     * View the possible battles panel.
     */
    public void viewBattles()
    {
    }


    /**
     * Select a battle to fight.
     */
    public void selectBattle()
    {
    }


    /**
     * View the team properties panel.
     */
    public void viewTeam()
    {
    }


    /**
     * View the game statistics panel.
     */
    public void viewStats()
    {
    }


    /**
     * 1. Set player name and request a different name if necessary.
	 * 2. Set number of days and request a different input if necessary.
	 * 3. Set difficulty.
	 * 4. Select starting monster and set monster name if not using the default.
     */
    public void setupGame()
    {
    	selectPlayerName();
    	selectNumDays();
    	SelectDifficulty();
    	selectStartingMonster();
    }

    
    public void selectPlayerName()
    {
    	// let the player pick a name
    }
    
    
    public void selectNumDays()
    {
    	// let the player pick a number of days
    }
    
    
    public void SelectDifficulty()
    {
    	// let the player pick a difficulty
    }
    
    
    public void selectStartingMonster()
    {
    	// let the player pick a monster
    }
    
    
    public boolean balanceSufficient(int cost) {
		return false;
    	
    }
    
    
    /**
     * View the player inventory panel.
     */
    public void viewInventory()
    {
    }


}
