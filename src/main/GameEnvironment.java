package main;

import java.util.ArrayList;

public class GameEnvironment {

	/**
	 * Fields
	 * 
	 */
    private double balance;
    private String playerName;
    private int numDays;
    private String difficulty;
    
    private int numBattles = 5;
    private ArrayList<Battle> battleList;
    
    private MonsterInventory myMonsters;
    private ItemInventory myItems;
    
    private MonsterInventory allMonsters;
    private ItemInventory allItems;
    
    private MonsterInventory shopMonsters;
    private ItemInventory shopItems;
    
    
    /**
     * Constructors
     * @throws InventoryFullException 
     */
    public GameEnvironment () throws InventoryFullException {
    	balance = 0;
    	myMonsters = new MonsterInventory(this);
    	myItems = new ItemInventory(this);
    	
    	ArrayList<Monster> allMonstersList = new ArrayList<Monster>();
    	allMonstersList.add(new AverageJoe(this));
    	allMonstersList.add(new Chunky(this));
    	allMonstersList.add(new Lanky(this));
    	allMonstersList.add(new Shanny(this));
    	allMonstersList.add(new Raka(this));
    	allMonstersList.add(new Zap(this));
    	allMonsters = new MonsterInventory(this, allMonstersList);
    	
    	ArrayList<Item> allItemsList = new ArrayList<Item>();
    	allItemsList.add(new IncreaseHealth(this));
    	allItemsList.add(new IncreaseCritRate(this));
    	allItemsList.add(new IncreaseDamage(this));
    	allItemsList.add(new LevelUp(this));
    	allItems = new ItemInventory(this, allItemsList);
    	
    	battleList = new ArrayList<Battle>(numBattles);
    	randomiseBattles();
    	System.out.println(battleList);
    	
    	shopMonsters = new MonsterInventory(this);
    	shopItems = new ItemInventory(this);
    	randomiseShop();
    	
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
	 * @return the numBattles
	 */
	public int getNumBattles() {
		return numBattles;
	}


	/**
	 * @param numBattles the numBattles to set
	 */
	public void setNumBattles(int numBattles) {
		this.numBattles = numBattles;
	}


	/**
	 * @return the battleList
	 */
	public ArrayList<Battle> getBattleList() {
		return battleList;
	}


	/**
	 * @param battleList the battleList to set
	 */
	public void setBattleList(ArrayList<Battle> battleList) {
		this.battleList = battleList;
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
	 * @return the allMonsters
	 */
	public MonsterInventory getAllMonsters() {
		return allMonsters;
	}


	/**
	 * @param allMonsters the allMonsters to set
	 */
	public void setAllMonsters(MonsterInventory allMonsters) {
		this.allMonsters = allMonsters;
	}


	/**
	 * @return the allItems
	 */
	public ItemInventory getAllItems() {
		return allItems;
	}
	
	
	/**
	 * @param allItems the allItems o set
	 */
	public void setAllItems(ItemInventory allItems) {
		this.allItems = allItems;
	}


	/**
	 * @return the shopMonsters
	 */
	public MonsterInventory getShopMonsters() {
		return shopMonsters;
	}


	/**
	 * @param shopMonsters the shopMonsters to set
	 */
	public void setShopMonsters(MonsterInventory shopMonsters) {
		this.shopMonsters = shopMonsters;
	}
	
	
	/**
	 * @return the shopItems
	 */
	public ItemInventory getShopItems() {
		return shopItems;
	}


	/**
	 * @param shopItems the shopItems to set
	 */
	public void setShopItems(ItemInventory shopItems) {
		this.shopItems = shopItems;
	}
	
    
    /**
     * Functional
     * 
     */

	/**
	 * Sleep
	 * 
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
	 * View the player inventory panel.
	 */
	public void viewInventory()
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
    
    
    /**
	 * Select a battle to fight.
	 */
	public void selectBattle()
	{
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
     * Randomise the battles in battleList.
     * @throws InventoryFullException 
     * 
     */
    public void randomiseBattles() throws InventoryFullException
    {
    	for (int i = 0; i < numBattles; i++) {
    		MonsterInventory monsterInventory = new MonsterInventory(this);
    		monsterInventory.randomiseInventory();
    		Battle battle = new Battle(this, monsterInventory);
    		battleList.add(battle);
    	}
    }


    /**
     * Randomise the purchasables in the shop.
     * @throws InventoryFullException 
     * 
     */
    public void randomiseShop() throws InventoryFullException
    {
    	shopMonsters.randomiseInventory();
    	shopItems.randomiseInventory();
    }


}
