package cost;

public class Cost {
	
	//總花費
	private int totalCost;
	private String event;
	public Cost() 
	{
		this.setTotalCost(0);
	}
	
	//set function
	public void setTotalCost(int totalCost) 
	{
		this.totalCost = totalCost;
	}
	
	//get function
	public int getTotalCost() 
	{
		return this.totalCost;
	}
	
	//加入新花費
	public void addnewCost(int newCost) 
	{
		this.totalCost += newCost;
	}
	//儲存
	public String saveCostData() 
	{
		String allCost;
		allCost = String.format("totalCost:%d,", this.totalCost);
		return allCost;
	}
	
	@Override
	public String toString() 
	{
		String str;
		str =  String.format("%d", this.totalCost);
		return str;
	}
}
