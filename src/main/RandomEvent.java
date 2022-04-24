package main;

import java.util.*;

public class RandomEvent {
    
    private double levelUpChance = 0.1;
    private double leaveChance = 0.05;
    private double joinChance = 0.1;
    private GameEnvironment game;
    private double leaveIncrement = 2.0;
    private double levelUpIncrement = 0.1;
    Random rn = new Random();
    
    public RandomEvent(GameEnvironment game) {
    	this.game = game;
    }
    
    public double getLevelUpChance() {
    	return levelUpChance;
    }
    
    public void setLevelUpChance(double levelUpChance) {
    	this.levelUpChance = levelUpChance;
    }

    public double getLeaveChance() {
    	return leaveChance;
    }
    
    public void setLeaveChance(double leaveChance) {
    	this.leaveChance = leaveChance;
    }
    
    public double getJoinChance() {
    	return joinChance;
    }
    
    public void setJoinChance(double joinChance) {
    	this.joinChance = joinChance;
    }
    
    public void randomMonsterLevelUp(Monster monster) {
		double resetValue = levelUpChance;
		//Possible mechanism: levelUpChance increases with the number of battles won in a day
		levelUpChance += levelUpIncrement*game.getScoreSystem().getDayBattlesWon();
		double randomValue = rn.nextDouble(1);
		if(randomValue <= levelUpChance) {
			try {
				monster.levelUp();			
			}
			catch (StatMaxedOutException e) {}
		}
		setLevelUpChance(resetValue);
    }
    
    public void randomMonsterLeave(Monster monster) throws PurchasableNotFoundException {
		double resetValue = leaveChance;
		//If monster fainted during any battle in the day, leaveChance is doubled 
		if(monster.getIsFainted()) {
		    leaveChance *= leaveIncrement;
		}
		double randomValue = rn.nextDouble(1);
		if(randomValue <= leaveChance) {
		    game.getMyMonsters().remove(monster);
		}
		setLeaveChance(resetValue);
    }

    public void randomMonsterJoin() {
		Monster randomMonster = game.getAllMonsters().random();
		double randomValue = rn.nextDouble(1);
		
		if(randomValue <= joinChance) {
			try {				
				game.getMyMonsters().add(randomMonster);
			}
			catch (InventoryFullException e) {}
		}	
    }
    
    public void runAllRandom() {
		for(Monster monster: game.getMyMonsters().getList()) {
		    randomMonsterLevelUp(monster);
		}
		for(Monster monster: game.getMyMonsters().getList()) {
		    if(game.getMyMonsters().size() <= 1) {
		    	break;
		    }
		    try {		    	
		    	randomMonsterLeave(monster);
		    }
		    catch (PurchasableNotFoundException e) {
		    	e.printStackTrace();
		    }
		}
		for(int i = 0; i < game.getMyMonsters().getMaxSize() - game.getMyMonsters().size(); i++) {
		    randomMonsterJoin();
		}
    }
}
