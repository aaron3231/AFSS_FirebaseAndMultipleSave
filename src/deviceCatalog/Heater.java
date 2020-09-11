package deviceCatalog;

import enviroment.Enviroment;

//加熱器
public class Heater extends Device
{
	private static final double HEATERRATE = 0.5; //加熱效率(C)
	private static final double ENVIRONMENT_MAX_TEMPERATURE = 38; //環境內最大溫度
	//heater變數
	private double maxTemperature; //加熱溫度最大上限
	
	//建構元
	public Heater(String name, int price, String statement, int operateModel, double maxTemperature) 
	{
		super(name,price,statement, operateModel);
		this.setMaxTemperature(maxTemperature);
	}
	
	//set function
	public void setMaxTemperature(double maxTemperature) 
	{
		if(maxTemperature < ENVIRONMENT_MAX_TEMPERATURE)
		{
			System.out.printf("加溫器最大上限為 %f 度 自動幫您設定成%f度 %n",ENVIRONMENT_MAX_TEMPERATURE,ENVIRONMENT_MAX_TEMPERATURE);
			this.maxTemperature = maxTemperature;
		}
		else
			this.maxTemperature = ENVIRONMENT_MAX_TEMPERATURE;
	}
	
	//get function
	public double getMaxTemperature() 
	{
		return this.maxTemperature;
	}
	
	//對環境進行加熱
	public void heat(Enviroment environment) 
	{
		//若加熱後小於最大上限，則做加熱
		if( (environment.getWaterTemperature() + HEATERRATE) < this.maxTemperature ) 
		{
			environment.setWaterTemperature(environment.getWaterTemperature()+HEATERRATE);
		}
	}
	//儲存
	@Override
	public String saveData() 
	{
		String forwordStr = super.saveData();
		String str;
		str = String.format("maxTemperature:%f,", this.maxTemperature);
		return forwordStr + str;
	}
	
	@Override
	public String savetoData() 
	{
		String forwordStr = super.savetoData();
		String str;
		str = String.format("%f,", this.maxTemperature);
		return forwordStr + str;
	}
	
	@Override
	public String toString() 
	{
		String forward;
		forward = super.toString();
		String str;
		str =  String.format("加熱最大上限：%.2f    加熱速率：%.2f %n", this.maxTemperature, HEATERRATE);
		return forward + str;
	}
}
