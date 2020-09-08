package deviceCatalog;

import java.security.SecureRandom;

public class Device {
	SecureRandom random = new SecureRandom();
	//設定失敗時用
	public static final int NONE = 0;
	//操作模式選擇
	public static final int MANUAL = 1; //手動
	public static final int AUTOMATIC = 2; //自動	
	private final double DAMAGE_RATE = 0.0005; //設備耗損率
	//設備類別變數 => 每個設備都有
	private String name; //設備名稱
	private int price; //設備價格
	private String statement; //設備簡介
	private boolean modelState = false; //設備開關 true = 開 , false = 關
	private double damageRateNow; //設備目前損壞率
	private int operateModel; //模式參數 1.手動 2.自動
	private int countTime; 
	private int haveBuy;
	//把device變數重新設定 買東西時使用
	
	//建構元
	public Device(String name, int price, String statement, int operateModel) 
	{
		this.setName(name);
		this.setPrice(price);
		this.setStatement(statement);
		this.setModelState(false); //一開始設定成關閉
		this.setDamageRateNow(0);
		this.setOperateModel(operateModel);
		this.setCountTime(0);
	}
	
	//set function
	public void setName(String name) 
	{
		this.name = name;
	}
	public void setPrice(int price) 
	{
		this.price = price;
	}
	public void setStatement(String statement) 
	{
		this.statement = statement;
	}
	public void setModelState(boolean input) 
	{
		this.modelState = input;
	}
	public void setDamageRateNow(double input) 
	{
		//耗損率 100 代表完全耗損 若大於100 則設為100
		if(this.damageRateNow >= 100)
			this.damageRateNow = 100;
		else if(this.damageRateNow < 0)
			this.damageRateNow = 0;
		//否則改成輸入值
		else
			this.damageRateNow = input;
	}
	public void setOperateModel(int model)  //設定操作模式
	{
		switch(model) 
		{
			case MANUAL:
			{
				this.operateModel = MANUAL;
				break;
			}
			case AUTOMATIC:
			{
				this.operateModel = AUTOMATIC;
				break;
			}
			default:{
				System.out.printf("設備設定模式錯誤!");
				this.operateModel = NONE;	
			}
		}
	}
	//設定經過時間
	public void setCountTime(int countTime) 
	{
		this.countTime = countTime;
	}
	
	public void setHaveBuy(int input) 
	{
		this.haveBuy = input;
	}
	//get function
	public String getName() 
	{
		return this.name;
	}
	public int getPrice() 
	{
		return this.price;
	}
	public String getStatement() 
	{
		return this.statement;
	}
	public boolean getModleState() 
	{
		return modelState;
	}
	public double getDamageRateNow() 
	{
		return this.damageRateNow;
	}
	public int getOperateModel() 
	{
		return this.operateModel;
	}
	public int getCountTime() 
	{
		return this.countTime;
	}
	public boolean getHaveBuy() 
	{
		if(this.haveBuy == 1)
			return true;
		else
			return false;
	}
	//變更開關  true則為開啟 / false 則為關閉
	public void changeModel(boolean input) 
	{
		//檢查若想開啟設備，但是設備已耗損，顯示警告訊息，並且不開啟
		if(input == true && this.damageRateNow==100)
			System.out.printf("設備耗損已達100%! 請另做添購!");
		//若沒問題則可以隨意做開關
		else
			this.modelState = input;
	}
	
	//增加一次設備損壞率(隨著時間推動) 
	public void damageDeviceByTime() 
	{
		int p = random.nextInt(10000); //p = 機率
		//自然爆炸設備損壞 機率 1%
		if(p == 0) // 0為爆炸 1~98沒事
		{
			this.setDamageRateNow(100);
		}
		//增加損壞定植
		if(this.damageRateNow < 100)
			this.damageRateNow += this.DAMAGE_RATE;
		//若設備完全耗損則關閉設備
		if(this.damageRateNow == 100)
		{
			System.out.println("設備耗損完畢!");
			changeModel(false);
		}
	}
	
	//每次呼叫紀錄增加一個單位時間
	public void countingTime() 
	{
		this.countTime++;
	}
	
	//購買新物品時重新設定
	public void newOne()
	{
		this.setDamageRateNow(0);
		this.setModelState(false);
		this.setOperateModel(AUTOMATIC);
	}
	//儲存
	public String saveData() 
	{
		String str;
		str = String.format("modelState:%s,damageRateNow:%f,operateModel:%d,", this.modelState?"true":"false" , this.damageRateNow, this.operateModel);
		return str;
	}
	
	
	public String savetoData() 
	{
		String str;
		str = String.format("%d,%s,%f,%d,",this.countTime,this.modelState?"true":"false" , this.damageRateNow, this.operateModel);
		return str;
	}
	
	
	@Override
	public String toString() 
	{
		String str;
		str = String.format("名稱: %s     價格: %d     開關: %s     操作模式:%s     設備損壞率:%f %n", this.name, this.price, this.modelState==true?"開":"關", this.operateModel == AUTOMATIC?"自動":"手動", this.damageRateNow);
		return str;
	}
	
}
