package deviceCatalog;

import enviroment.Enviroment;

public class Inflator extends Device {
	
	private static double EVERYTIME_PUMP = 5; //–Ωゴ程ㄎゑㄒ +5
	
	private double bestProportion; //程ㄎゑㄒ
	
	//篶じ
	public Inflator(String name, int price, String statement) 
	{
		//╰参笆砞﹚笆
		super(name, price, statement, AUTOMATIC);
	}
	
	//set function
	public void setBestProportion(int input) 
	{
		//砞﹚絛瞅ず
		if(input >= 0 && input <= 100) 
		{
			this.bestProportion = input;
		}
		else 
		{
			System.out.printf("程ㄎゑㄒ砞﹚岿粇!");
		}
	}
	
	//get function
	public double getBestProportion() 
	{
		return this.bestProportion;
	}
	
	//ゴ
	public void pump(Enviroment environment) 
	{
		//絋粄⊿胊
		if(this.getDamageRateNow() < 100)
		{
			//盢﹚秖秖砞
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
		str =  String.format("ヘ玡程ㄎゑㄒ%.2f –Ωゴ秖:%.2f %n", this.bestProportion, this.EVERYTIME_PUMP);
		return forward + str;
	}
}
