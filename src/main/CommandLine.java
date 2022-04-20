package main;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import main.GameEnvironment.Difficulty;

public class CommandLine {

	/**
	 * Fields
	 * 
	 */
    private Scanner scanner = new Scanner(System.in);
    private GameEnvironment game;
    private MonsterInventory shopMonsters;
    private ItemInventory shopItems;
    private int selection;
    
    
    /**
     * Constructors
     * 
     */
    
    /**
     * @param game
     */
    public CommandLine(GameEnvironment game) {
    	this.game = game;
    	shopMonsters = game.getShop().getMonsters();
    	shopItems = game.getShop().getItems();
    }
    
    
	/**
	 * @return the scanner
	 */
	public Scanner getScanner() {
		return scanner;
	}
	
	
	/**
	 * @param scanner the scanner to set
	 */
	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
	

	/**
	 * Functional
	 * 
	 */
	
	/**
	 * 1. Set player name and request a different name if necessary.
	 * 2. Set number of days and request a different input if necessary.
	 * 3. Set difficulty.
	 * 4. Select starting monster and set monster name if not using the default.
	 * @throws InventoryFullException 
	 */
	public void setupGame() throws InventoryFullException {
		selectPlayerName();
		selectNumDays();
		selectDifficulty();
		selectStartingMonster();
	}
	
	
	/**
	 * Let the player pick a name.
	 * 
	 */
	public void selectPlayerName() {
		System.out.println("Select a player name (3 - 15 characters"
						+ " containing no numbers or special characters):");
		while (game.getPlayerName() == null) {
			String name = scanner.nextLine();
			try {
				game.setPlayerName(name);
			}
			catch (InvalidValueException e) {
				System.out.println(e.getMessage());
			}    		
		}
		System.out.println(String.format("Nice to meet you %s!", game.getPlayerName()));
    }
    
    
	/**
	 * Let the player pick a number of days
	 * 
	 */
    public void selectNumDays() {
		System.out.println("Select a number of days (between 5 - 15):");
		while (game.getNumDays() == 0) {
			try {
				int numDays = Integer.parseInt(scanner.nextLine().strip());
				game.setNumDays(numDays);
			}
			catch (NumberFormatException e) {
				System.out.println("Please enter a number! Try again:");
			}
			catch (InvalidValueException e) {
				System.out.println(e.getMessage());
			}    		
		}
		System.out.println(String.format("You chose: %s days.", game.getNumDays()));
    }
    
    
    /**
     * Let the player pick a difficulty
     * 
     */
    public void selectDifficulty() {
		System.out.println("Select a difficulty level (easy, normal, hard):");
		while (game.getDifficulty() == null) {
			try {
				String inputStr = scanner.nextLine().toUpperCase().strip();
				Difficulty difficulty = Difficulty.valueOf(inputStr);
				game.setDifficulty(difficulty);
			}
			catch (IllegalArgumentException e) {
				System.out.println("Invalid difficulty! Try again:");
			}
		}
		System.out.println(String.format("You chose: %s.", game.getDifficulty()));
    }


    /**
     * Let the player pick a starting monster
     * @throws InventoryFullException 
     * 
     */
	public void selectStartingMonster() throws InventoryFullException {
		System.out.println("Select a starting monster (average joe, chunky, lanky, shanny, raka, zap):");
		while (game.getMyMonsters().size() == 0) {
			String inputStr = scanner.nextLine();
			String monsterName = properCase(inputStr);
			try {
				if (game.getAllMonsters().contains(monsterName)) {				
					Class<? extends Monster> clazz = game.getAllMonsters().find(monsterName).getClass();
					Monster monster = clazz.getConstructor(GameEnvironment.class).newInstance(game);
					selectMonsterName(monster);
					game.getMyMonsters().add(monster);
				}
				else {
					throw new PurchasableNotFoundException("Invalid starting monster!");
				}
			}
			catch (PurchasableNotFoundException | InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException | NoSuchMethodException
					| SecurityException e) {
				System.out.println(e.getMessage() + " Try again:");
			}				
		}
		System.out.println(String.format("You chose:\n%s", game.getMyMonsters()));
	}
	/**
	 * Select the name of the monster 
	 */
	public void selectMonsterName(Monster monster) {
		
		// Player can choose to rename their monster or keep the default name 
		System.out.println("Do you wish to rename your monster? Yes OR No?");
		String choice = scanner.nextLine().toLowerCase();
		while(!(choice.equals("yes") || choice.equals("no"))) {
			System.out.println("Invalid input, please try again.");
			choice = scanner.nextLine().toLowerCase();
		}
		
		// Player chooses to rename their monster 
		if(choice.equals("yes")) {
			System.out.println("Select a unique monster name (3 - 15 characters"
					+ " containing no numbers or special characters):");
			while (true) {
				String name = scanner.nextLine();
				try {
					monster.setName(name);
					break;
				}
				catch (InvalidValueException e) {
					System.out.println(e.getMessage());
				}    		
			}
			System.out.println(String.format("Welcome %s to the team!", monster.getName()));
			scanner.close();
		}
	}
    
    
    public void viewShop() {
    	outer:
			while (true) {
				System.out.println(game.getShop());
				try {
					selection = scanner.nextInt();
					switch (selection) {
						case 1, 2, 3, 4:
							viewMonster(shopMonsters.get(selection - 1));
							break;
						case 5, 6, 7, 8:
							viewItem(shopItems.get(selection - 5));
							break;
						case 9:
							break outer;
					}
				}
				catch (IllegalArgumentException | InputMismatchException | IndexOutOfBoundsException e) {
					System.out.println("Command not found! Try again:");
					scanner.next();
				}
			}
    }
    
    
    /**
     * @param monster
     */
    public void viewMonster(Monster monster) {
    	outer:
			while (true) {
				System.out.println(monster.view());
				try {
					selection = scanner.nextInt();
					switch (selection) {
						case 1:
							if (game.getMyMonsters().contains(monster)) {
								System.out.println(monster.sell());
							}
							if (shopMonsters.contains(monster)) {							
								System.out.println(monster.buy());
							}
						case 2:
							break outer;
					}
				}
				catch (IllegalArgumentException | InputMismatchException | IndexOutOfBoundsException | 
						InsufficientFundsException | InventoryFullException | PurchasableNotFoundException e) {
					System.out.println("Command not found! Try again:");
					scanner.next();
				}
			}
    }
    
    
    /**
     * @param item
     */
    public void viewItem(Item item) {
    	outer:
			while (true) {
				System.out.println(item.view());
				try {
					selection = scanner.nextInt();
					switch (selection) {
						case 1:
							if (game.getMyItems().contains(item)) {
								System.out.println(item.sell());
							}
							if (shopItems.contains(item)) {							
								System.out.println(item.buy());
							}
						case 2:
							break outer;
					}
				}
				catch (IllegalArgumentException | InputMismatchException | IndexOutOfBoundsException | 
						InsufficientFundsException | InventoryFullException | PurchasableNotFoundException e) {
					System.out.println("Command not found! Try again:");
					scanner.next();
				}
			}
    }
    
    
    public void viewBattles() {
    	outer:
			while (true) {
				System.out.println(game.getBattles());
				try {
					selection = scanner.nextInt();
					if (game.getMyItems().size() == selection - 1) {
						break;
					}
					switch (selection) {
						case 1, 2, 3, 4, 5:
							viewBattle(game.getBattles().get(selection - 1));
							break;
						case 6:
							break outer;
					}
				}
				catch (IllegalArgumentException | InputMismatchException | IndexOutOfBoundsException e) {
					System.out.println("Command not found! Try again:");
					scanner.next();
				}
			}
    }
    
    
    /**
     * 
     * @param battle
     */
    private void viewBattle(Battle battle) {
    	outer:
    		while (true) {
    			System.out.println(battle.view());
    			try {
    				selection = scanner.nextInt();
    				switch (selection) {
    					case 1:
    						battle.play();
    					case 2:
    						break outer;
    				}
    			}
    			catch (IllegalArgumentException | InputMismatchException | IndexOutOfBoundsException | 
    					InvalidValueException | InvalidTargetException e) {
    				System.out.println("Command not found! Try again:");
    				scanner.next();
    			}
    		}
	}


	/**
     * View my monsters
     */
    public void viewTeam() {
    	outer:
			while (true) {
		    	System.out.println(game.getMyMonsters().view());
				try {
					selection = scanner.nextInt();
					if (game.getMyMonsters().size() == selection - 1) {
						break;
					}
					switch (selection) {
						case 1, 2, 3, 4:
							viewMonster(game.getMyMonsters().get(selection - 1));
							break;
						case 5:
							break outer;
					}
				}
				catch (IllegalArgumentException | InputMismatchException | IndexOutOfBoundsException e) {
					System.out.println("Command not found! Try again:");
					scanner.next();
				}
			}
    }


	/**
     * View my items
     */
    public void viewInventory() {
    	outer:
			while (true) {
		    	System.out.println(game.getMyItems().view());
				try {
					selection = scanner.nextInt();
					if (game.getMyItems().size() == selection - 1) {
						break;
					}
					switch (selection) {
						case 1, 2, 3, 4:							
							viewItem(game.getMyItems().get(selection - 1));
							break;
						case 5:
							break outer;
					}
				}
				catch (IllegalArgumentException | InputMismatchException | IndexOutOfBoundsException e) {
					System.out.println("Command not found! Try again:");
					scanner.next();
				}
			}
    }
    
    
    /**
     * View the game statistics
     */
    public void viewStats() {
    	System.out.println("\n===== PLAYER STATS =====");
    	System.out.println("Balance: " + game.getBalance());
    	System.out.println("Player name: " + game.getPlayerName());
    	System.out.println(String.format("Day %s out of %s", game.getDay(), game.getNumDays()));
    	System.out.println("Difficulty: " + game.getDifficulty());
    	// print current score and other stats about past battles
    }
    
	
	/**
	 * 
	 */
	public void viewHome() {
		System.out.println("\nHome (select a number):");
		System.out.println("1: Shop");
		System.out.println("2: Team");
		System.out.println("3: Inventory");
		System.out.println("4: Battles");
		System.out.println("5: Stats");
		System.out.println("6: Sleep");
	}
	
    
	/**
	 * 
	 * @throws InventoryFullException
	 */
    public void run() throws InventoryFullException {
    	while (!game.isFinished()) {
    		viewHome();
    		try {
    			selection = scanner.nextInt();
    			switch (selection) {
    				case 1:
    					viewShop();
    					break;
    				case 2:
    					viewTeam();
    					break;
    				case 3:
    					viewInventory();
    					break;
    				case 4:
    					viewBattles();
    					break;
    				case 5:
    					viewStats();
    					break;
    				case 6:
    					game.sleep();
    					break;
				}
    		}
    		catch (IllegalArgumentException | InputMismatchException | IndexOutOfBoundsException
    				| InvalidValueException e) {
    			System.out.println("Command not found! Try again:");
    			scanner.next();
    		}
    	}
    	System.out.println("<<<<< Game over! >>>>>");
    }
    
    
    /**
     * Returns a proper case version of the given phrase with all redundant whitespace removed.
     * @param phrase to be formatted
     * @return the formatted phrase
     */
    public String properCase(String phrase) {
    	String[] words = phrase.toLowerCase().strip().split("\\s+");
    	if (String.join("", words).equals("")) {
    		return "";
    	}
    	for (int i = 0; i < words.length; i++) {
    		String word = words[i];
    		String first = word.substring(0, 1);
    		if (first.matches("[a-zA-Z]")) {
    			first = first.toUpperCase();
    		}
    		String rest = word.substring(1, word.length());   
    		words[i] = first + rest;
    	}
    	String result = String.join(" ", words);
		return result;
    }
    
}
