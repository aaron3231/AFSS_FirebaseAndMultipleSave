package deviceCatalog;

import enviroment.Enviroment;

public class ChangeWater extends Device {
	
	//水種類 1.淡水  2.海水
	private static final int FRESHWATER = 1;
	private static final int SEAWATER = 2;
	
	private int waterCategory; //水質
	
	public ChangeWater(String statement) 
	{
		super("換水", 0, statement, AUTOMATIC);
	}
	
	//set function
	public void setWaterCategory(int waterCategory) 
	{
		switch(waterCategory)
		{
			case FRESHWATER:
			{
				this.waterCategory =  FRESHWATER;
				break;
			}
			case SEAWATER:
			{
				this.waterCategory = SEAWATER;
				break;
			}
			case NONE:
			{
				this.waterCategory = NONE;
				break;
			}
			default:
				System.out.printf("水種類輸入錯誤!");
		}
	}
	
	//get function
	public int getWaterCategory() 
	{
		return this.waterCategory;
	}
	
	//換水 => 僅影響水質 quantity 使用者加入水量
	public void change(Enviroment environment, int waterCategory, double quantity) 
	{
		//換水量小於 1/4
		if(quantity <= 1/4) 
		{
			double temp = 0;
			temp = environment.getWaterQuality() * (100-quantity) + 100 * quantity;
			if(temp>100) 
			{
				temp = 100;
			}
			//設定新的水質
			environment.setWaterQuality(temp);
		}
	}
	
	@Override
	public String toString() 
	{
		super.toString();
		String str;
		str =  String.format("水種：%s %n", this.waterCategory == SEAWATER?"海水":"淡水");
		return str;
	}
}	
