package landScape;
import enviroment.Enviroment;

public class WaterPlant extends Decoration {
	
	public WaterPlant() 
	{
		//大小 1x1
		super();
		this.setName("水草");
		this.setPrice(20);
		this.setStatement("可以行光合作用(用照明器照明)產生氧氣");
		this.setSizeX(1);
		this.setSizeY(1);
	}
	
	//裝飾物只有水草對環境有影響
	public void environmentCheck(Enviroment environment) 
	{
		//檢查水質對水草影響
		if(environment.getWaterQuality() < 30) 
		{
			//水質最差 損壞 +3
			super.damageDecorationByTime();
			super.damageDecorationByTime();
			super.damageDecorationByTime();
		} 
		else if (environment.getWaterQuality() < 50) 
		{
			//水質 很差 損壞 +2
			super.damageDecorationByTime();
			super.damageDecorationByTime();
		}
		else if(environment.getWaterQuality() < 70) 
		{
			//水質 差 損壞 +1
			super.damageDecorationByTime();
		}
		
		//檢查水溫對水草影響
		if ((31 < environment.getWaterTemperature() && environment.getWaterTemperature() <= 33) || ((23 < environment.getWaterTemperature() && environment.getWaterTemperature() <= 25))) 
		{
			super.damageDecorationByTime();
		}
		else if( 23 >= environment.getWaterTemperature() || environment.getWaterTemperature() > 33) 
		{
			super.damageDecorationByTime();
			super.damageDecorationByTime();
		} 
		
		//水草影響氧氣量 每個物件會呼叫一次
		environment.setOxygen(1);
	}
	
	//若有照明器在照明 減緩水草損壞速度
	public void flashLightRepair() 
	{
		this.setDamageRateNow(this.getDamageRateNow() - super.DAMAGE_RATE);
	}
}
