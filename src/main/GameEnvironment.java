package main;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class GameEnvironment {

	/**
	 * Fields
	 * 
	 */
    private double balance;
    private String playerName;
    private int numDays;
    private Difficulty difficulty;
    
    private enum Difficulty {
    	EASY,
    	NORMAL,
    	HARD
    }
    
    private int numBattles = 5;
    private ArrayList<Battle> battleList;
    
    private MonsterInventory myMonsters;
    private ItemInventory myItems;
    
    private MonsterInventory allMonsters;
    private ItemInventory allItems;
    
    private MonsterInventory shopMonsters;
    private ItemInventory shopItems;
    
    private enum MonsterClass {
    	AVERAGEJOE,
    	CHUNKY,
    	LANKY,
    	SHANNY,
    	RAKA,
    	ZAP
    };
    
    private enum Command {
    	VIEW,
    	BUY,
    	SELL,
    	SELECT
    }
    
    private enum View {
    	SHOP,
    	INVENTORY,
    	BATTLES,
    	STATS,
    	TEAM
    }
    
    private enum Select {
    	NAME,
    	NUMDAYS,
    	DIFFICULTY,
    	BATTLE,
    	MONSTER
    }
    
    
    /**
     * Constructors
     * @throws InventoryFullException 
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */
    public GameEnvironment () throws InventoryFullException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
    	balance = 0;
    	myMonsters = new MonsterInventory(this);
    	myItems = new ItemInventory(this);

    	allMonsters = new MonsterInventory(this);
    	ArrayList<Monster> allMonstersList = new ArrayList<Monster>();
    	allMonstersList.add(new AverageJoe(this));
    	allMonstersList.add(new Chunky(this));
    	allMonstersList.add(new Lanky(this));
    	allMonstersList.add(new Shanny(this));
    	allMonstersList.add(new Raka(this));
    	allMonstersList.add(new Zap(this));
    	allMonsters.setMonsterList(allMonstersList);
    	
    	allItems = new ItemInventory(this);
    	ArrayList<Item> allItemsList = new ArrayList<Item>();
    	allItemsList.add(new IncreaseHealth(this));
    	allItemsList.add(new IncreaseDamage(this));
    	allItemsList.add(new IncreaseCritRate(this));
    	allItemsList.add(new LevelUp(this));
    	allItems.setItemList(allItemsList);
    	
//    	monsterClasses = new HashMap<String, Class<? extends Monster>>();
//    	monsterClasses.put("averagejoe", AverageJoe.class);
//    	monsterClasses.put("chunky", Chunky.class);
//    	monsterClasses.put("lanky", Lanky.class);
//    	monsterClasses.put("shanny", Shanny.class);
//    	monsterClasses.put("raka", Raka.class);
//    	monsterClasses.put("zap", Zap.class);
    	
    	battleList = new ArrayList<Battle>(numBattles);
    	randomiseBattles();
    	
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
    	String regex = "(([a-z]|[A-Z])*(\s)*)*([a-z]|[A-Z])+";
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
    public void viewShop()
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
	 * @throws InventoryFullException 
	 */
	public void setupGame() throws InventoryFullException
	{
		selectPlayerName();
		selectNumDays();
		selectDifficulty();
		selectStartingMonster();
	}


	/**
	 * Let the player pick a name.
	 * 
	 */
	public void selectPlayerName()
    {
    	Scanner input = new Scanner(System.in);
		System.out.println("Select a player name (between 3 - 15 characters"
						+ " containing no numbers or special characters):");
		while (getPlayerName() == null) {
			String name = input.nextLine();
			try {
				setPlayerName(name);
			}
			catch (InvalidValueException e) {
				System.out.println(e.getMessage());
			}    		
		}
		System.out.println(String.format("Nice to meet you %s!", getPlayerName()));
    }
    
    
	/**
	 * Let the player pick a number of days
	 * 
	 */
    public void selectNumDays()
    {
    	Scanner input = new Scanner(System.in);
		System.out.println("Select a number of days (between 5 - 15):");
		while (getNumDays() == 0) {
			try {
				int numDays = Integer.parseInt(input.nextLine().strip());
				setNumDays(numDays);
			}
			catch (NumberFormatException e) {
				System.out.println("Please enter a number! Try again:");
			}
			catch (InvalidValueException e) {
				System.out.println(e.getMessage());
			}    		
		}
		System.out.println(String.format("You chose: %s days.", getNumDays()));
    }
    
    
    /**
     * Let the player pick a difficulty
     * 
     */
    public void selectDifficulty()
    {
    	Scanner input = new Scanner(System.in);
		System.out.println("Select a difficulty level (easy, normal, hard):");
		while (difficulty == null) {
			try {
				String inputStr = input.nextLine().toUpperCase().strip();
				Difficulty difficulty = Difficulty.valueOf(inputStr);
				setDifficulty(difficulty);
			}
			catch (IllegalArgumentException e) {
				System.out.println("Invalid difficulty! Try again:");
			}
		}
		System.out.println(String.format("You chose: %s.", getDifficulty()));
    }


    /**
     * Let the player pick a starting monster
     * @throws InventoryFullException 
     * 
     */
	public void selectStartingMonster() throws InventoryFullException
    {
		Scanner input = new Scanner(System.in);
		System.out.println("Select a starting monster (average joe, chunky, lanky, shanny, raka, zap):");
		while (getMyMonsters().getMonsterList().size() == 0) {
			String inputStr = input.nextLine().toUpperCase().strip().replaceAll("\\s+", "");
			try {
				MonsterClass monsterClass = MonsterClass.valueOf(inputStr);
				Monster monster = null;
				switch (monsterClass) {
					case AVERAGEJOE:
						monster = new AverageJoe(this);
						break;
					case CHUNKY:
						monster = new Chunky(this);
						break;
					case LANKY:
						monster = new Lanky(this);
						break;
					case SHANNY:
						monster = new Shanny(this);
						break;
					case RAKA:
						monster = new Raka(this);
						break;
					case ZAP:
						monster = new Zap(this);
						break;
				}
				getMyMonsters().add(monster);
			}
			catch (IllegalArgumentException e) {
				System.out.println("Invalid starting monster! Try again:");
			}				
		}
		System.out.println(String.format("You chose:\n%s", getMyMonsters()));
    }
	
	
	/**
	 * Select a battle to fight.
	 */
	public void selectBattle()
	{
		
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
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * 
     */
    public void randomiseBattles() throws InventoryFullException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
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
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * 
     */
    public void randomiseShop() throws InventoryFullException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
    {
    	shopMonsters.randomiseInventory();
    	shopItems.randomiseInventory();
    }
    
    
    public void run() throws InventoryFullException {
    	Scanner input = new Scanner(System.in);
    	while (true) {    		
    		String[] commands = input.nextLine().toUpperCase().strip().split("\\s+");
    		if (commands[0] == "QUIT") {
    			break;
    		}
    		try {    		
    			if (commands.length != 2) {
    				throw new IllegalArgumentException();
    			}
    			Command command = Command.valueOf(commands[0]);
    			System.out.println(command);
    			switch (command) {
	    			case VIEW:
	    				View view = View.valueOf(commands[1]);
	    				System.out.println(view);
	    				switch (view) {
	    					case SHOP:
	    						viewShop();
	    						break;
	    					case INVENTORY:
	    						viewInventory();
	    						break;
	    					case BATTLES:
	    						viewBattles();
	    						break;
	    					case STATS:
	    						viewStats();
	    						break;
	    					case TEAM:
	    						viewTeam();
	    						break;
	    				}
	    				break;
	    			case BUY:
	    				// do some stuff here...
	    				break;
	    			case SELL:
	    				// do some stuff here...
	    				break;
	    			case SELECT:
	    				Select select = Select.valueOf(commands[1]);
	    				System.out.println(select);
	    				switch (select) {
	    					case NAME:
	    						selectPlayerName();
	    						break;
	    					case NUMDAYS:
	    						selectNumDays();
	    						break;
	    					case DIFFICULTY:
	    						selectDifficulty();
	    						break;
	    					case BATTLE:
	    						selectBattle();
	    						break;
	    					case MONSTER:
	    						selectStartingMonster();
	    						break;
	    				}
	    				break;
    			}
    		}
    		catch (IllegalArgumentException e) {
    			System.out.println("Command not found! Try again:");
    		}
    	}
    	input.close();
    }
    
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InventoryFullException {
    	GameEnvironment game = new GameEnvironment();
    	
//    	for (int i = 0; i < game.battleList.size(); i++)
//    	{
//    		Battle battle = game.battleList.get(i);
//    		System.out.println(String.format("=== Battle %s ===", i + 1));
//    		System.out.println(battle);
//    	}
//    	
//    	System.out.println("-----------------------------");
//    	System.out.println(game.shopItems);
//    	
//    	System.out.println("-----------------------------");
//    	System.out.println(game.shopMonsters);
    	
    	//game.run();
    }

}
