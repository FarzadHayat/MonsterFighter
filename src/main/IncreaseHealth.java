package main;

public class IncreaseHealth extends Item {
	
    public IncreaseHealth (String name, String description, int cost, GameEnvironment game) {
    	super(name, description, cost, game);
    };
    
    //
    // Methods
    //


    //
    // Other methods
    //

    /**
     * add x amount of health to the monster's health
     * @param        monster
     */
    public void use(Monster monster)
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
