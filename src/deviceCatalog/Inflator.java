package deviceCatalog;

import enviroment.Enviroment;

public class Inflator extends Device {
	
	private static double EVERYTIME_PUMP = 5; //每次打氣氧氣最佳比例 +5
	
	private double bestProportion; //最佳比例
	
	//建構元
	public Inflator(String name, int price, String statement) 
	{
		//系統自動設定為自動
		super(name, price, statement, AUTOMATIC);
	}
	
	//set function
	public void setBestProportion(int input) 
	{
		//設定值再範圍內
		if(input >= 0 && input <= 100) 
		{
			this.bestProportion = input;
		}
		else 
		{
			System.out.printf("氧氣最佳比例設定錯誤!");
		}
	}
	
	//get function
	public double getBestProportion() 
	{
		return this.bestProportion;
	}
	
	//打氣
	public void pump(Enviroment environment) 
	{
		//確認沒壞
		if(this.getDamageRateNow() < 100)
		{
			//將定量氧氣量設入
			environment.setOxygen(environment.getOxygen()+EVERYTIME_PUMP);
		}
	}
	//override newOne function
	@Override
	public void newOne() 
	{
		this.setDamageRateNow(0);
		this.setModelState(false);
		this.setOperateModel(AUTOMATIC);
		this.setBestProportion(0);
	}
	
	@Override
	public String toString() 
	{
		String forward;
		forward = super.toString();
		String str;
		str =  String.format("目前最佳比例：%.2f 每次打氣量:%.2f %n", this.bestProportion, this.EVERYTIME_PUMP);
		return forward + str;
	}
}
