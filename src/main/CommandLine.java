package main;

import java.util.*;

public class CommandLine {

	/**
	 * Fields
	 * 
	 */
    private Scanner scanner = new Scanner(System.in);
    private GameEnvironment game;
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
		while (true) {
			System.out.println("\nSelect a starting monster:\n");
	    	System.out.println(game.getAllMonsters().view());
			try {
				selection = scanner.nextInt();
				scanner.nextLine();
				if (0 < selection && selection <= game.getAllMonsters().size()) {
					Monster monster = game.getAllMonsters().get(selection - 1).clone();
					System.out.println("You chose: " + monster.getName());
					selectMonsterName(monster);
					game.getMyMonsters().add(monster);
					break;
				}
				else {
					throw new IllegalArgumentException();
				}
			}
			catch (IllegalArgumentException | InputMismatchException | IndexOutOfBoundsException e) {
				System.out.println("Command not found! Try again:");
			}
		}
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
		}
	}
    
    
    public void viewShop() {
    	outer:
			while (true) {
				System.out.println(game.getShop());
				try {
					selection = scanner.nextInt();
					scanner.nextLine();
					switch (selection) {
						case 1, 2, 3, 4:
							viewMonster(game.getShop().getMonsters().get(selection - 1));
							break;
						case 5, 6, 7, 8:
							viewItem(game.getShop().getItems().get(selection - 5));
							break;
						case 9:
							break outer;
					}
				}
				catch (IllegalArgumentException | InputMismatchException | IndexOutOfBoundsException e) {
					System.out.println("Command not found! Try again:");
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
					scanner.nextLine();
					switch (selection) {
						case 1:
							if (game.getMyMonsters().contains(monster)) {
								System.out.println(monster.sell());
							}
							if (game.getShop().getMonsters().contains(monster)) {							
								System.out.println(monster.buy());
							}
						case 2:
							break outer;
					}
				}
				catch (IllegalArgumentException | InputMismatchException | IndexOutOfBoundsException | 
						InsufficientFundsException | InventoryFullException | PurchasableNotFoundException | 
						InvalidValueException e) {
					System.out.println("Command not found! Try again:");
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
					scanner.nextLine();
					switch (selection) {
						case 1:
							if (game.getMyItems().contains(item)) {
								System.out.println(item.sell());
							}
							if (game.getShop().getItems().contains(item)) {							
								System.out.println(item.buy());
							}
						case 2:
							break outer;
					}
				}
				catch (IllegalArgumentException | InputMismatchException | IndexOutOfBoundsException | 
						InsufficientFundsException | InventoryFullException | PurchasableNotFoundException | 
						InvalidValueException e) {
					System.out.println("Command not found! Try again:");
				}
			}
    }
    
    
    public void viewBattles() {
		while (true) {
			System.out.println("\n===== BATTLES =====\n");
			System.out.println(game.getBattles().view());
	    	System.out.println(String.format("%s: Go back", game.getBattles().size() + 1));
			try {
				selection = scanner.nextInt();
				scanner.nextLine();
				if (game.getBattles().size() == selection - 1) {
					break;
				}
				if (0 < selection && selection <= game.getBattles().size()) {
					viewBattle(game.getBattles().get(selection - 1));
				}
				else {
					throw new IllegalArgumentException();
				}
			}
			catch (IllegalArgumentException | InputMismatchException | IndexOutOfBoundsException e) {
				System.out.println("Command not found! Try again:");
			}
		}
    }
    
    
    /**
     * 
     * @param battle
     */
    public void viewBattle(Battle battle) {
    	outer:
    		while (true) {
    			System.out.println(battle.view());
    			try {
    				selection = scanner.nextInt();
    				scanner.nextLine();
    				switch (selection) {
    					case 1:
    						game.pickBattle(battle);
    					case 2:
    						break outer;
    				}
    			}
    			catch (IllegalArgumentException | InputMismatchException | IndexOutOfBoundsException | 
    					InvalidValueException | InvalidTargetException | PurchasableNotFoundException e) {
    				System.out.println("Command not found! Try again:");
    			}
    		}
	}


	/**
     * View my monsters
     */
    public void viewTeam() {
		while (true) {
			System.out.println("\n===== MY TEAM =====\n");
	    	System.out.println(game.getMyMonsters().view());
	    	System.out.println(String.format("%s: Go back", game.getMyMonsters().size() + 1));
			try {
				selection = scanner.nextInt();
				scanner.nextLine();
				if (game.getMyMonsters().size() == selection - 1) {
					break;
				}
				if (0 < selection && selection <= game.getMyMonsters().size()) {
					viewMonster(game.getMyMonsters().get(selection - 1));
				}
				else {
					throw new IllegalArgumentException();
				}
			}
			catch (IllegalArgumentException | InputMismatchException | IndexOutOfBoundsException e) {
				System.out.println("Command not found! Try again:");
			}
		}
    }


	/**
     * View my items
     */
    public void viewInventory() {
		while (true) {
	    	System.out.println("\n===== MY INVENTORY =====\n");
	    	System.out.println(game.getMyItems().view());
	    	System.out.println(String.format("%s: Go back", game.getMyItems().size() + 1));
			try {
				selection = scanner.nextInt();
				scanner.nextLine();
				if (game.getMyItems().size() == selection - 1) {
					break;
				}
				if (0 < selection && selection <= game.getMyItems().size()) {
					viewItem(game.getMyItems().get(selection - 1));
				}
				else {
					throw new IllegalArgumentException();
				}
			}
			catch (IllegalArgumentException | InputMismatchException | IndexOutOfBoundsException e) {
				System.out.println("Command not found! Try again:");
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
	 * @throws PurchasableNotFoundException 
	 * @throws StatMaxedOutException 
	 */
    public void run() throws InventoryFullException, StatMaxedOutException, PurchasableNotFoundException {
    	while (!game.getIsFinished()) {
    		viewHome();
    		try {
    			selection = scanner.nextInt();
    			scanner.nextLine();
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