package main;

import java.util.*;

public class GameEnvironment {

	/**
	 * Fields
	 * 
	 */
    private double balance;
    private String playerName;
    private int numDays;
    private int day;
    private Difficulty difficulty;
    
    private MonsterInventory myMonsters;
    private ItemInventory myItems;
    
    private MonsterInventory allMonsters;
    private ItemInventory allItems;
    
    private Shop shop;
    private BattleInventory battles;
    
    private RandomEvent randomEvent;
    
    private boolean isFinished = false;
    
    
    /**
     * Constructors
     * @throws InventoryFullException 
     * @throws InvalidValueException 
     */
    public GameEnvironment () throws InventoryFullException, InvalidValueException {
    	setDay(1);
    	setBalance(100);

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
    	allItemsList.add(new IncreaseDamage(this));
    	allItemsList.add(new IncreaseCritRate(this));
    	allItemsList.add(new LevelUp(this));
    	allItems = new ItemInventory(this, allItemsList);
    	
    	myMonsters = new MonsterInventory(this);
    	myItems = new ItemInventory(this);
    	shop = new Shop(this);
    	battles = new BattleInventory(this);
    	randomEvent = new RandomEvent(this);
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
     * @throws InvalidValueException 
     */
    public void setBalance (double balance) throws InvalidValueException {
    	if (0 > balance) {
    		throw new InvalidValueException("Balance cannot be a negative value!");
    	}
    	else {
    		this.balance = balance;    		
    	}
    }

    
    /**
     * Get the value of playerName
     * @return the value of playerName
     */
    public String getPlayerName () {
        return playerName;
    }
    
    
    /**
     * Set the value of playerName
     * @param playerName the new value of playerName
     * @throws InvalidValueException 
     */
    public void setPlayerName (String playerName) throws InvalidValueException {
    	playerName = playerName.strip();
    	String regex = "(([a-zA-Z])*(\\s)*)*([a-zA-Z])+";
    	if (3 <= playerName.length() && playerName.length() <= 15 && playerName.matches(regex)) {
    		this.playerName = playerName;
    	}
    	else {    		
    		throw new InvalidValueException("Invalid player name! Try again:");
    	}
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
     * @throws InvalidValueException 
     */
    public void setNumDays (int numDays) throws InvalidValueException {
    	if (5 <= numDays && numDays <= 15) {
    		this.numDays = numDays;
    	}
    	else {    		
    		throw new InvalidValueException("Invalid number of days! Try again:");
    	}
    }

    
    /**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}


	/**
	 * @param day the day to set
	 */
	public void setDay(int day) {
		this.day = day;
	}


	/**
     * Get the value of difficulty
     * @return the value of difficulty
     */
    public Difficulty getDifficulty () {
        return difficulty;
    }
    
    
    /**
     * Set the value of difficulty
     * @param difficulty the new value of difficulty
     */
    public void setDifficulty (Difficulty difficulty) {
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
	 * @return the shop
	 */
	public Shop getShop() {
		return shop;
	}


	/**
	 * @param shop the shop to set
	 */
	public void setShop(Shop shop) {
		this.shop = shop;
	}


	/**
	 * @return the battles
	 */
	public BattleInventory getBattles() {
		return battles;
	}


	/**
	 * @param battles the battles to set
	 */
	public void setBattles(BattleInventory battles) {
		this.battles = battles;
	}
	
	/**
	 * @return the randomEvent
	 */
	public RandomEvent getRandomEvent() {
		return randomEvent;
	}


	/**
	 * @param randomEvent the randomEvent to set
	 */
	public void setRandomEvent(RandomEvent randomEvent) {
		this.randomEvent = randomEvent;
	}

	/**
	 * @return the isFinished
	 */
	public boolean getIsFinished() {
		return isFinished;
	}


	/**
	 * @param isFinished the isFinished to set
	 */
	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	
	/**
	 * Functional
	 * 
	 */

	/**
	 * Sleep through the night. Randomises shop, randomises battles, and heals all player monsters once.
	 * @throws InventoryFullException 
	 * @throws InvalidValueException 
	 * @throws PurchasableNotFoundException 
	 * @throws StatMaxedOutException 
	 * 
     */
    public void sleep() throws InventoryFullException, InvalidValueException, StatMaxedOutException, PurchasableNotFoundException {
    	checkStatus();
    	if (!getIsFinished()) {
    		setDay(getDay() + 1);
    		getShop().randomise();
    		battles.randomise();
    		randomEvent.runAllRandom();
    		myMonsters.healAll();
    		// Random events
    	}
    }
    
    
    /**
     * Checks if number of days has finished or there is a stalemate
     * where the player has no monsters and can't buy anything.
     * If yes, then set isFinished to true.
     */
    public void checkStatus() {
    	boolean stalemate = true;
    	if (myMonsters.size() == 0) {
    		for (Monster monster : getShop().getMonsters().getList()) {
    			if (balance >= monster.getCost()) {
    				stalemate = false;
    			}
    		}
    	}
    	else {
    		stalemate = false;
    	}
    	setFinished(day >= numDays || stalemate);
    }
		

    /**
     * Add to the player balance
     * @param amount
     * @throws InvalidValueException 
     */
    public void addBalance(double amount) throws InvalidValueException {
    	if (0 > amount) {
    		throw new InvalidValueException("Cannot be a negative value!");
    	}
    	else {    		
    		setBalance(getBalance() + amount);
    	}
    }
    
    
    /**
     * Remove from the player balance
     * @param amount
     * @throws InsufficientFundsException
     * @throws InvalidValueException 
     */
    public void minusBalance(double amount) throws InsufficientFundsException, InvalidValueException {
    	if (0 > amount) {
    		throw new InvalidValueException("Cannot be a negative value!");
    	}
		if (balanceSufficient(amount)) {
			setBalance(getBalance() - amount);
    	}
    	else {
    		throw new InsufficientFundsException("Insufficient funds!");
    	}
    }
    
    
    /**
     * Returns whether balance is sufficient for the given cost
     * @param amount the cost
     * @return whether balance is sufficient
     * @throws InvalidValueException 
     */
    public boolean balanceSufficient(double amount) throws InvalidValueException {
    	if (0 > amount) {
    		throw new InvalidValueException("Cannot be a negative value!");
    	}
    	else {    		
    		return getBalance() >= amount;
    	}
    }
    
    
    public static void main(String[] args) throws InventoryFullException, InvalidValueException, StatMaxedOutException, PurchasableNotFoundException {
    	GameEnvironment game = new GameEnvironment();
    	CommandLine commandLine = new CommandLine(game);
    	// The setup
    	//commandLine.setupGame();
    	// The main command line
    	commandLine.run();
    	// Game over
    	commandLine.viewStats();
    }

}
