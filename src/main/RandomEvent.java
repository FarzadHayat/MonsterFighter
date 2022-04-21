package main;

import java.util.*;

public class RandomEvent {
    
    private double levelUpChance;
    private double leaveChance;
    private double joinChance;
    private GameEnvironment game;
    private double leaveIncrement = 2.0;
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
    
    public void randomMonsterLevelUp(Monster monster) throws StatMaxedOutException {
	//Possible mechanism: levelUpChance increases with the number of battles won in a day
	double randomValue = rn.nextDouble(1);
	if(randomValue <= levelUpChance) {
	    monster.levelUp();
	}
    }
    
    public void randomMonsterLeave(Monster monster) throws PurchasableNotFoundException {
	//If monster fainted during any battle in the day, leaveChance is doubled 
	if(monster.getIsFainted()) {
	    leaveChance *= leaveIncrement;
	}
	double randomValue = rn.nextDouble(1);
	if(randomValue <= leaveChance) {
	    game.getMyMonsters().remove(monster);
	}
    }

    public void randomMonsterJoin() throws InventoryFullException {
	Monster randomMonster = game.getAllMonsters().random();
	double randomValue = rn.nextDouble(1);
	
	if(randomValue <= joinChance && !game.getMyMonsters().full()) {
	    game.getMyMonsters().add(randomMonster);
	}	
    }
    
    public void runAllRandom() throws StatMaxedOutException, PurchasableNotFoundException, InventoryFullException {
	for(Monster monster: game.getMyMonsters().getList()) {
	    randomMonsterLevelUp(monster);
	}
	for(Monster monster: game.getMyMonsters().getList()) {
	    if(game.getMyMonsters().size() <= 1) {
		break;
	    }
	    randomMonsterLeave(monster);
	}
	for(int i = 0; i < game.getMyMonsters().getMaxSize() - game.getMyMonsters().size(); i++) {
	    randomMonsterJoin();
	}
    }
}
