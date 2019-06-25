package deviceCatalog;

import landScape.Decoration;
import landScape.LandScape;
import landScape.WaterPlant;

public class FlashLight extends Device {

	private int lightHour = 0; //剩餘照明時間
	
	//建構元
	public FlashLight(String name, int price, String statement, int operateModel) 
	{
		super(name, price, statement, operateModel);
	}
	
	//set function
	public void setLightHour(int lighthour) 
	{
		//自動模式才能Set
		if(this.getOperateModel() == AUTOMATIC) 
		{
			this.lightHour = lighthour;
		}
	}
	
	
	//get function
	public int getLightHour() 
	{
		return this.lightHour;
	}
	
	//隨時間扣除物件內 剩餘照明時間
	public void lighthourDecrease() 
	{
		//call一次function扣紀錄時間一小時(單位時間)
		if(lightHour > 0) 
		{
			this.lightHour -= 1;
		}
		//若時間到則關閉設備執行
		if(lightHour <= 0) 
		{
			this.lightHour = 0;
			this.setModelState(false);
		}
	}
	
	//照明器影響水草生命
	public void affectWaterPlant(LandScape landScape) 
	{
		int i,j;
		//尋找每一格如果是水草則做動作
		for(i=0;i<landScape.getTable().length;i++) 
		{
			for(j=0;j<landScape.getTable()[0].length;j++) 
			{
				//check如果table上是水草
				if(landScape.getTable()[i][j].getPrice()==20) 
				{
					//回復一次水草 損壞值
					landScape.getTable()[i][j].setDamageRateNow(landScape.getTable()[i][j].getDamageRateNow()-landScape.getTable()[i][j].DAMAGE_RATE);
				}
			}
		}
		
	}
	
	//override newOne function
	@Override
	public void newOne() 
	{
		this.setDamageRateNow(0);
		this.setModelState(false);
		this.setOperateModel(AUTOMATIC);
		this.setLightHour(0);
	}
	
	//儲存
	@Override
	public String saveData() 
	{
		String forwordStr = super.saveData();
		String str;
		str = String.format("lightHour:%d,", this.lightHour);
		return forwordStr + str;
	}
	@Override
	public String savetoData() 
	{
		String forwordStr = super.savetoData();
		String str;
		str = String.format("%d,", this.lightHour);
		return forwordStr + str;
	}
	
	@Override
	public String toString() 
	{
		String forward;
		forward = super.toString();
		String str;
		str =  String.format("剩餘照明時間：%d %n", this.lightHour);
		return forward + str;
	}
}
