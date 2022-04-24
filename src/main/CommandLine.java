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
    
    private boolean forceStop = false;
        
    /**
     * Constructors
     * 
     */
    
    /**
     * @param game
     */
    public CommandLine() {
    	start();
    	if(forceStop != true) {
        	run();
    	}
    }
    
    public void start() {
    	System.out.println("Would you like to play the game?\n1. Yes\n2. No");
    	outer:
	    	while (true) {    		
	    		try {
	    			int choice = scanner.nextInt();
	    			scanner.nextLine();
	    			switch (choice) {
	    			case 1:
	    				setupCmdGame();
	    				break outer;
	    			case 2:
	    				System.out.println("Bye!");
	    				forceStop = true;
	    				break outer;
	    			default:
	    				throw new IllegalArgumentException("Command not found! Try again:");
	    			}
	    		}
	    		catch (IllegalArgumentException e) {
	    			System.out.println(e.getMessage());
	    		}
	    		catch (InputMismatchException e) {
	    			System.out.println("Command not found! Try again:");
	    			scanner.nextLine();
	    		}
	    	}
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
	 * 4. Instantiate new GamEnvironment with the chosen difficulty, player name and number of days
	 * 5. Select starting monster and set monster name if not using the default.
	 */
	public void setupCmdGame() {
		game = new GameEnvironment();
		selectPlayerName();
		selectNumDays();
		selectDifficulty();
		game.setupGame();
		selectStartingMonster();
	}
	
	/**
	 * Let the player pick a name.
	 * 
	 */
	public void selectPlayerName() {
		System.out.println("Select a player name (3 - 15 characters"
						+ " containing no numbers or special characters):");
		while (true) {
			String name = scanner.nextLine();
			try {
				game.setPlayerName(name);
				break;
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
		outer:
			while (true) {
				try {
					int numDays = scanner.nextInt();
					scanner.nextLine();
					game.setNumDays(numDays);
					break outer;
				}
				catch (InputMismatchException e) {
	    			System.out.println("Command not found! Try again:");
	    			scanner.nextLine();
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
		System.out.println("Select a difficulty level\n1. Easy\n2. Normal\n3. Hard");
		outer:
			while(true) {
	        	try {
	        		int choice = scanner.nextInt();
	        		scanner.nextLine();
	        		switch (choice) {
	        		case 1:
	        			game.setDifficulty(Difficulty.EASY);
	        			break outer;
	        		case 2:
	        			game.setDifficulty(Difficulty.NORMAL);
	        			break outer;
	        		case 3:
	        			game.setDifficulty(Difficulty.HARD);
	        			break outer;
	        		default:
	        			throw new IllegalArgumentException("Command not found! Try again:");
	        		}
	        	}
	        	catch (IllegalArgumentException e) {
	    			System.out.println(e.getMessage());
	    		}
	        	catch (InputMismatchException e) {
	    			System.out.println("Command not found! Try again:");
	    			scanner.nextLine();
	    		}
	    	}
		System.out.println(String.format("You chose: %s.", game.getDifficulty()));
    }


    /**
     * Let the player pick a starting monster
     */
	public void selectStartingMonster() {
		System.out.println("\nSelect a starting monster:\n");
		System.out.println(game.getAllMonsters().view());
		while (true) {
			try {
				selection = scanner.nextInt();
				scanner.nextLine();
				if (0 < selection && selection <= game.getAllMonsters().size()) {
					Monster monster = game.getAllMonsters().get(selection - 1).clone();
					System.out.println("You chose: " + monster.getName());
					game.getMyMonsters().add(monster);
					break;
				}
				else {
					throw new IllegalArgumentException("Command not found! Try again:");
				}
			}
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
			catch (InventoryFullException e) {
				System.out.println(e.getMessage());
			}
			catch (InputMismatchException e) {
    			System.out.println("Command not found! Try again:");
    			scanner.nextLine();
    		}
		}
	}
	/**
	 * Select the name of the monster 
	 */
	public void selectMonsterName(Monster monster) {
		System.out.println("Select a unique monster name (3 - 15 characters"
				+ " containing no numbers or special characters):");
		while (true) {
			String name = scanner.nextLine();
			try {
				String nameBefore = monster.getName();
				monster.setName(name);
				System.out.println(String.format("%s has been renamed to %s.", nameBefore, monster.getName()));
				goBack();
				break;
			}
			catch (InvalidValueException e) {
				System.out.println(e.getMessage());
			}
		}
	}
    
    
    public void viewShop() {
    	System.out.println(game.getShop());
    	outer:
			while (true) {
				try {
					selection = scanner.nextInt();
					scanner.nextLine();
					switch (selection) {
					case 1, 2, 3, 4:
						Monster monster = game.getShop().getMonsters().get(selection - 1);
						viewMonster(monster);
			    		System.out.println(game.getShop());
						break;
					case 5, 6, 7, 8:
						Item item = game.getShop().getItems().get(selection - 5);
						viewItem(item);
			    		System.out.println(game.getShop());
						break;
					case 9:
						break outer;
					default:
						throw new IllegalArgumentException("Command not found! Try again:");
					}
				}
				catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
				catch (InputMismatchException e) {
	    			System.out.println("Command not found! Try again:");
	    			scanner.nextLine();
	    		}
			}
    }
    
    
    /**
     * @param monster
     */
    public void viewMonster(Monster monster) {
    	System.out.println(monster.view());
    	outer:
			while (true) {
				try {
					selection = scanner.nextInt();
					scanner.nextLine();
					if (game.getMyMonsters().contains(monster)) {
						switch (selection) {
						case 1:
							selectMonsterName(monster);
							System.out.println(monster.view());
							break;
						case 2:
							System.out.println(monster.sell());
							goBack();
							break outer;
						case 3:
							break outer;
						default:
							throw new IllegalArgumentException("Command not found! Try again:");
						}
					}
					if (game.getShop().getMonsters().contains(monster)) {	
						switch (selection) {
						case 1:
							System.out.println(monster.buy());
							goBack();
							break outer;
						case 2:
							break outer;
						default:
							throw new IllegalArgumentException("Command not found! Try again:");
						}
					}
				}
				catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
				catch (PurchasableNotFoundException | InvalidValueException | 
						   InsufficientFundsException | InventoryFullException e) {
					System.out.println(e.getMessage());
				}
				catch (InputMismatchException e) {
	    			System.out.println("Command not found! Try again:");
	    			scanner.nextLine();
	    		}
			}
    }
    
    
    /**
     * @param item
     */
    public void viewItem(Item item) {
    	System.out.println(item.view());
    	outer:
			while (true) {
				try {
					selection = scanner.nextInt();
					scanner.nextLine();
					if (game.getMyItems().contains(item)) {
						switch (selection) {
						case 1:
							useItem(item);
							break outer;
						case 2:
							System.out.println(item.sell());
							goBack();
							break outer;
						case 3:
							break outer;
						default:
							throw new IllegalArgumentException("Command not found! Try again:");
						}
					}
					if (game.getShop().getItems().contains(item)) {
						switch (selection) {
						case 1:
							System.out.println(item.buy());
							goBack();
							break outer;
						case 2:
							break outer;
						default:
							throw new IllegalArgumentException("Command not found! Try again:");
						}
					}
				}
				catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
				catch (PurchasableNotFoundException | InvalidValueException | 
						   InsufficientFundsException | InventoryFullException e) {
					System.out.println(e.getMessage());
				}
				catch (InputMismatchException e) {
	    			System.out.println("Command not found! Try again:");
	    			scanner.nextLine();
	    		}
			}
    }
    
    
    public void useItem(Item item) {
    	System.out.println("Choose a monster to use it on:");
    	System.out.println("\n===== MY TEAM =====\n");
    	System.out.println(game.getMyMonsters().view());
    	System.out.println(String.format("%s: Go back", game.getMyMonsters().size() + 1));
    	while (true) {
			try {
				selection = scanner.nextInt();
				scanner.nextLine();
				if (game.getMyMonsters().size() == selection - 1) {
					break;
				}
				if (0 < selection && selection <= game.getMyMonsters().size()) {
					Monster monster = game.getMyMonsters().get(selection - 1);
					System.out.println(String.format("You used %s on %s:", item.getName(), monster.getName()));
					item.use(monster);
					System.out.println(monster);
					goBack();
					break;
				}
				else {
					throw new IllegalArgumentException("Command not found! Try again:");
				}
			}
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
			catch (PurchasableNotFoundException | StatMaxedOutException e) {
				System.out.println(e.getMessage());
			}
			catch (InputMismatchException e) {
    			System.out.println("Command not found! Try again:");
    			scanner.nextLine();
    		}
		}
    }
    
    
    public void viewBattles() {
    	System.out.println("\n===== BATTLES =====\n");
    	System.out.println(game.getBattles().view());
    	System.out.println(String.format("%s: Go back", game.getBattles().size() + 1));
		while (true) {
			try {
				selection = scanner.nextInt();
				scanner.nextLine();
				if (game.getBattles().size() == selection - 1) {
					break;
				}
				if (0 < selection && selection <= game.getBattles().size()) {
					Battle battle = game.getBattles().get(selection - 1); 
					viewBattle(battle);
					System.out.println("\n===== BATTLES =====\n");
			    	System.out.println(game.getBattles().view());
			    	System.out.println(String.format("%s: Go back", game.getBattles().size() + 1));
				}
				else {
					throw new IllegalArgumentException("Command not found! Try again:");
				}
			}
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
			catch (InputMismatchException e) {
    			System.out.println("Command not found! Try again:");
    			scanner.nextLine();
    		}
		}
    }
    
    
    /**
     * 
     * @param battle
     */
    public void viewBattle(Battle battle) {
    	System.out.println(battle.view());
    	outer:
    		while (true) {
    			try {
    				selection = scanner.nextInt();
    				scanner.nextLine();
    				switch (selection) {
					case 1:
						playBattle(battle);
						break outer;
					case 2:
						break outer;
					default:
						throw new IllegalArgumentException("Command not found! Try again:");
    				}
    			}
    			catch (IllegalArgumentException e) {
    				System.out.println(e.getMessage());
    			}
    			catch (InputMismatchException e) {
	    			System.out.println("Command not found! Try again:");
	    			scanner.nextLine();
	    		} catch (PurchasableNotFoundException e) {
					System.out.println(e.getMessage());
				}
    		}
	}
    
    
    /**
     * 
     * @param battle
     * @throws PurchasableNotFoundException
     */
    public void playBattle(Battle battle) throws PurchasableNotFoundException {
    	battle.setup();
    	System.out.println("Press Enter to play next turn...");
    	while (battle.getWinner() == null) {
    		scanner.nextLine();
    		System.out.println(battle.playTurn());
    		System.out.println(battle.checkStatus());
    	}
    	try {
    		game.getBattles().remove(battle);
    	}
    	catch (PurchasableNotFoundException e) {
    		e.printStackTrace();
    	}
    	goBack();
    }


	/**
     * View my monsters
     */
    public void viewTeam() {
    	System.out.println("\n===== MY TEAM =====\n");
    	System.out.println(game.getMyMonsters().view());
    	System.out.println(String.format("%s: Go back", game.getMyMonsters().size() + 1));
		while (true) {
			try {
				selection = scanner.nextInt();
				scanner.nextLine();
				if (game.getMyMonsters().size() == selection - 1) {
					break;
				}
				if (0 < selection && selection <= game.getMyMonsters().size()) {
					Monster monster = game.getMyMonsters().get(selection - 1);
					viewMonster(monster);
					System.out.println("\n===== MY TEAM =====\n");
			    	System.out.println(game.getMyMonsters().view());
			    	System.out.println(String.format("%s: Go back", game.getMyMonsters().size() + 1));
				}
				else {
					throw new IllegalArgumentException("Command not found! Try again:");
				}
			}
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
			catch (InputMismatchException e) {
    			System.out.println("Command not found! Try again:");
    			scanner.nextLine();
    		}
		}
    }


	/**
     * View my items
     */
    public void viewInventory() {
    	System.out.println("\n===== MY INVENTORY =====\n");
    	System.out.println(game.getMyItems().view());
    	System.out.println(String.format("%s: Go back", game.getMyItems().size() + 1));
		while (true) {
			try {
				selection = scanner.nextInt();
				scanner.nextLine();
				if (game.getMyItems().size() == selection - 1) {
					break;
				}
				if (0 < selection && selection <= game.getMyItems().size()) {
					Item item = game.getMyItems().get(selection - 1);
					viewItem(item);
			    	System.out.println("\n===== MY INVENTORY =====\n");
			    	System.out.println(game.getMyItems().view());
			    	System.out.println(String.format("%s: Go back", game.getMyItems().size() + 1));
				}
				else {
					throw new IllegalArgumentException("Command not found! Try again:");
				}
			}
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
			catch (InputMismatchException e) {
    			System.out.println("Command not found! Try again:");
    			scanner.nextLine();
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
    	System.out.println("Today score: " + game.getScoreSystem().getScore());
    	System.out.println("Total score: " + game.getScoreSystem().getFinalScore());
    	System.out.println(String.format("Today: %s battles won out of %s", game.getScoreSystem().getDayBattlesWon(), 
    			game.getScoreSystem().getDayBattlesWon() + game.getScoreSystem().getDayBattlesLost()));
    	System.out.println(String.format("Total: %s battles won out of %s", game.getScoreSystem().getTotalBattlesWon(), 
    			game.getScoreSystem().getTotalBattlesWon() + game.getScoreSystem().getTotalBattlesLost()));
    	goBack();
    }
    
    
    public void goBack() {
    	System.out.println("\n1. Go back");
    	outer:
	    	while (true) {
	    		try {
	    			int selection = scanner.nextInt();
	    			scanner.nextLine();
	    			switch (selection) {
	    			case 1:
	    				break outer;
	    			default:
	    				throw new IllegalArgumentException("Command not found! Try again:");
	    			}
	    		}
	    		catch (IllegalArgumentException e) {
	    			System.out.println(e.getMessage());
	    		}
	    		catch (InputMismatchException e) {
	    			System.out.println("Command not found! Try again:");
	    			scanner.nextLine();
	    		}
	    	}
    }
    
	
	/**
	 * 
	 */
	public void viewHome() {
		System.out.println("\nHome:");
		System.out.println("1: Shop");
		System.out.println("2: Team");
		System.out.println("3: Inventory");
		System.out.println("4: Battles");
		System.out.println("5: Stats");
		System.out.println("6: Sleep");
	}
	
    
	/**
	 * 
	 */
    public void run() {
    	viewHome();
    	while (!game.getIsFinished()) {
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
					default:
						throw new IllegalArgumentException("Command not found! Try again:");
				}
    			viewHome();
    		}
    		catch (IllegalArgumentException e) {
    			System.out.println(e.getMessage());
    		} catch (InputMismatchException e) {
    			System.out.println("Command not found! Try again:");
    			scanner.nextLine();
    		}
    	}
    	viewStats();
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