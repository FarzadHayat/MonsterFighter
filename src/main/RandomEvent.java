package main;

import java.util.*;

public class RandomEvent {
    
    private double levelUpChance;
    private double leaveChance;
    private double joinChance;
    private GameEnvironment game;
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
    
    public void randomMonsterLevelUp() throws StatMaxedOutException {
	Monster randomMyMonster = game.getMyMonsters().random();
	double randomValue = rn.nextDouble(1);
	if(randomValue <= levelUpChance) {
	    randomMyMonster.levelUp();
	}
    }
    
    public void randomMonsterLeave(Monster monster) throws PurchasableNotFoundException {
	double randomValue = rn.nextDouble(1);
	if(randomValue <= leaveChance) {
	    game.getMyMonsters().remove(monster);
	}
    }

    public void randomMonsterJoin(Monster monster) throws InventoryFullException {
	Monster randomMonster = game.getAllMonsters().random();
	
	int currentTeamSize = game.getMyMonsters().size();
	double resetValue = joinChance;
	
	//The chance of a monster joining the team increases with less monsters being in the team 
	joinChance /= (currentTeamSize/game.getMyMonsters().getInventorySize());
	double randomValue = rn.nextDouble(1);
	
	if(randomValue <= joinChance && !game.getMyMonsters().isFull()) {
	    game.getMyMonsters().add(randomMonster);
	}
	
	setJoinChance(resetValue);
    }
    
    public static void main(String[]args) {
	
    }
}
