package main;

public class GameEnvironment {

	/**
	 * Fields
	 * 
	 */
    private double balance;
    private String playerName;
    private int numDays;
    private String difficulty;
    private MonsterInventory myMonsters;
    private ItemInventory myItems;
    
    
    /**
     * Constructors
     * 
     */
    public GameEnvironment () {
    	balance = 0;
    	myMonsters = new MonsterInventory();
    	myItems = new ItemInventory();
    	setupGame();
    };

    
    /**
     * Getters and setters
     */

    
    /**
     * Get the value of balance
     * @return the value of balance
     */
    public double getBalance () {
        return balance;
    }
    
    
    /**
     * Set the value of balance
     * @param balance the new value of balance
     */
    public void setBalance (double balance) {
    	this.balance = balance;
    }

    
    /**
     * Get the value of name
     * @return the value of name
     */
    public String getName () {
        return playerName;
    }
    
    
    /**
     * Set the value of name
     * @param playerName the new value of name
     */
    public void setPlayerName (String playerName) {
    	this.playerName = playerName;
    }

    
    /**
     * Get the value of numDays
     * @return the value of numDays
     */
    public int getNumDays () {
        return numDays;
    }
    
    
    /**
     * Set the value of numDays
     * @param numDays the new value of numDays
     */
    public void setNumDays (int numDays) {
    	this.numDays = numDays;
    }

    
    /**
     * Get the value of difficulty
     * @return the value of difficulty
     */
    public String getDifficulty () {
        return difficulty;
    }
    
    
    /**
     * Set the value of difficulty
     * @param difficulty the new value of difficulty
     */
    public void setDifficulty (String difficulty) {
    	this.difficulty = difficulty;
    }

    
    /**
     * Get the value of myMonsters
     * @return the value of myMonsters
     */
    public MonsterInventory getMyMonsters () {
        return myMonsters;
    }
    
    
    /**
     * Set the value of myMonsters
     * @param myMonsters the new value of myMonsters
     */
    public void setMyMonsters (MonsterInventory myMonsters) {
    	this.myMonsters = myMonsters;
    }

    
    /**
     * Get the value of myItems
     * @return the value of myItems
     */
    public ItemInventory getMyItems () {
        return myItems;
    }
    
    
    /**
     * Set the value of myItems
     * @param myItems the new value of myItems
     */
    public void setMyItems (ItemInventory myItems) {
    	this.myItems = myItems;
    }
    
    /**
     * Functional
     * 
     */

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

    
    public void addBalance(double amount) {
		balance += amount;
    }
    
    public void minusBalance(double amount) throws InsufficientFundsException {
		if (balanceSufficient(amount)) {
			balance -= amount;
    	}
    	else {
    		throw new InsufficientFundsException("Insufficient funds!");
    	}
    }
    
    
    public boolean balanceSufficient(double amount) {
    	return balance >= amount;
    }
    
    
    /**
     * View the player inventory panel.
     */
    public void viewInventory()
    {
    }


}
