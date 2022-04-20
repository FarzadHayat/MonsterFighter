package main;

import java.util.ArrayList;

public class BattleInventory {

    private int inventorySize = 5;
    private ArrayList<Battle> list;
    private GameEnvironment game;
    
    
    public BattleInventory(GameEnvironment game) throws InventoryFullException {
    	list = new ArrayList<Battle>(inventorySize);
    	this.game = game;
    	randomise();
    }
    
    
    public BattleInventory (GameEnvironment game, ArrayList<Battle> list) throws InventoryFullException {
    	this.game = game;
    	this.list = list;
    	randomise();
    };

    
    /**
	 * @return the inventorySize
	 */
	public int getInventorySize() {
		return inventorySize;
	}


	/**
	 * @param inventorySize the inventorySize to set
	 */
	public void setInventorySize(int numBattles) {
		this.inventorySize = numBattles;
	}


	/**
	 * @return the list
	 */
	public ArrayList<Battle> getList() {
		return list;
	}


	/**
	 * @param list the list to set
	 */
	public void setList(ArrayList<Battle> list) {
		this.list = list;
	}
	
	
	/**
	 * @param battle
	 * @throws InventoryFullException
	 */
	public void add(Battle battle) throws InventoryFullException {
		if (size() < inventorySize) {
			list.add(battle);			
		}
		else {
			throw new InventoryFullException("Battle inventory is full!");
		}
	}
	
	
	/**
	 * @param battle
	 */
	public void remove(Battle battle) {
		list.remove(battle);
	}
	
	
	/**
	 * @return
	 */
	public int size() {
		return list.size();
	}
	
	
	/**
	 * @param index
	 * @return
	 */
	public Battle get(int index) {
		return list.get(index);
	}
	
	
	/**
	 * @param battle
	 * @return
	 */
	public boolean contains(Battle battle) {
		return list.contains(battle);
	}
	
    
    /**
     * Randomise the battles in list.
     * @throws InventoryFullException 
     */
    public void randomise() throws InventoryFullException {
    	for (int i = 0; i < inventorySize; i++) {
    		MonsterInventory monsterInventory = new MonsterInventory(game);
    		monsterInventory.randomiseInventory();
    		Battle battle = new Battle(game, monsterInventory);
    		add(battle);
    	}
    }
}
