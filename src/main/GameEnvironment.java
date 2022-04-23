package main;

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
    
    private Inventory<Monster> myMonsters;
    private Inventory<Item> myItems;
    
    private Inventory<Monster> allMonsters;
    private Inventory<Item> allItems;
    
    private Shop shop;
    private Inventory<Battle> battles;
    
    private RandomEvent randomEvent;
    private Score scoreSystem;
    
    private boolean isFinished = false;
    
    
    /**
     * Constructors
     * @throws InventoryFullException 
     * @throws InvalidValueException 
     */
    public GameEnvironment () throws InventoryFullException, InvalidValueException {
    	setDay(1);
    	setBalance(100);

    	allMonsters = new Inventory<Monster>(6, this);
    	allMonsters.add(new AverageJoe(this));
    	allMonsters.add(new Chunky(this));
    	allMonsters.add(new Lanky(this));
    	allMonsters.add(new Shanny(this));
    	allMonsters.add(new Raka(this));
    	allMonsters.add(new Zap(this));
    	
    	allItems = new Inventory<Item>(4, this);
    	allItems.add(new HealUp(this));
    	allItems.add(new IncreaseDamage(this));
    	allItems.add(new IncreaseCritRate(this));
    	allItems.add(new LevelUp(this));
    	
    	myMonsters = new Inventory<Monster>(4, this);
    	myItems = new Inventory<Item>(4, this);
    	shop = new Shop(this);
    	battles = new Inventory<Battle>(5, this);
    	Inventory.randomiseBattles(battles, this);
    	randomEvent = new RandomEvent(this);
    	scoreSystem = new Score(this);
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
    public Inventory<Monster> getMyMonsters () {
        return myMonsters;
    }
    
    
    /**
     * Set the value of myMonsters
     * @param myMonsters the new value of myMonsters
     */
    public void setMyMonsters (Inventory<Monster> myMonsters) {
    	this.myMonsters = myMonsters;
    }

    
    /**
     * Get the value of myItems
     * @return the value of myItems
     */
    public Inventory<Item> getMyItems () {
        return myItems;
    }
    
    
    /**
     * Set the value of myItems
     * @param myItems the new value of myItems
     */
    public void setMyItems (Inventory<Item> myItems) {
    	this.myItems = myItems;
    }


	/**
	 * @return the allMonsters
	 */
	public Inventory<Monster> getAllMonsters() {
		return allMonsters;
	}


	/**
	 * @param allMonsters the allMonsters to set
	 */
	public void setAllMonsters(Inventory<Monster> allMonsters) {
		this.allMonsters = allMonsters;
	}


	/**
	 * @return the allItems
	 */
	public Inventory<Item> getAllItems() {
		return allItems;
	}
	
	
	/**
	 * @param allItems the allItems o set
	 */
	public void setAllItems(Inventory<Item> allItems) {
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
	public Inventory<Battle> getBattles() {
		return battles;
	}


	/**
	 * @param battles the battles to set
	 */
	public void setBattles(Inventory<Battle> battles) {
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
	 * @return the scoreSystem
	 */
	public Score getScoreSystem() {
	    return scoreSystem;
	}
	
	/**
	 * @param scoreSystem the Score class to set
	 */
	public void setScoreSystem(Score scoreSystem) {
	    this.scoreSystem = scoreSystem;
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
    		Inventory.randomiseBattles(battles, this);
			randomEvent.runAllRandom();
    		Inventory.healAll(myMonsters);
    		scoreSystem.resetDayBattles();
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
    
    /**
     * Plays a battle and adds the win/lost to the score 
     * @param battle picked battle
     * @throws InvalidValueException
     * @throws InvalidTargetException
     * @throws PurchasableNotFoundException
     */
    public void pickBattle(Battle battle) throws InvalidValueException, InvalidTargetException, PurchasableNotFoundException {
	battle.play();
	if(battle.getWinner().name().equals("PLAYER")) {
	    scoreSystem.addBattlesWon();
	}
	else if(battle.getWinner().name().equals("ENEMY")) {
	    scoreSystem.addBattlesLost();
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
