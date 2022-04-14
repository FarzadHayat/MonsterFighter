package main;

/**
 * Class Chunky
 * Chunky
Tanky but low damage and expensive
 */
public class Chunky extends Monster {
	
    public Chunky(String name, String description, int maxHealth, int damage, int cost, int level, int healAmount, double critRate) {
    	super(name, description, maxHealth, damage, cost, level, healAmount, critRate);
    };
    
    //
    // Methods
    //


    //
    // Other methods
    //

    /**
     */
    public void levelUp()
    {
    }

	@Override
	public void buy(Purchasable purchasable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sell(Purchasable purchasable) {
		// TODO Auto-generated method stub
		
	}


}
