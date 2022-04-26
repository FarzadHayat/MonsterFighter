package main;

public interface Purchasable {
	
	public String getName();
	
	public int getCost();
	
	public double getRefundAmount();
	
	public Purchasable clone();
}
