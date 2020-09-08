package deviceCatalog;

import java.security.SecureRandom;

import enviroment.Enviroment;

public class Feeder extends Device {
	SecureRandom random = new SecureRandom();
	
	//食性種類
	private static final int CARNIVOROUS = 1; // 肉食性
	private static final int HERBIVOROUS = 2; // 草食性
	//顆粒大小
	private static final int BIG = 50; //大
	private static final int MIDDLE = 10; //中
	private static final int SMALL = 5; //小
	private static final int SLIGHT = 1; //細
	//定時模式
	private static final int HALFDAY = 1; //半天
	private static final int ALLDAY = 2; //整天
	//狂餵倍率
	private static final int CRAZY_FEED_RATE = 3;
	
	//feeder物件變數
	private int crazyStatus; //狂餵狀態 0.不餵 1.狂餵
	private int dietFavorite; //食性
	private int grain; //顆粒大小 
	private int regularTime; //定時模式
	private boolean achieve;
	
	
	//飼料顆粒暫存，手動餵食時會暫存原本自動餵食的飼料顆粒
	private int grainTmp;
	//建構元
	public Feeder(String name, int price, String statement, int OperateModel, int Favorite, int Grain, int regularTime) 
	{
		super(name, price, statement, OperateModel);
		setFavorite(Favorite);
		setGrain(Grain);
		grainTmp=this.grain;
		setRegularTime(regularTime);
		//建構時先決定壞掉的狀態
		this.setCrazyFeed();
	}
	
	//set function
	//設定食性
	public void setFavorite(int selectorFavorite) 
	{
		switch(selectorFavorite) 
		{
			case CARNIVOROUS: //肉食性
			{
				this.dietFavorite = CARNIVOROUS;
				break;
			}
			case HERBIVOROUS: //草食性
			{
				this.dietFavorite = HERBIVOROUS;
				break;
			}
			default://設定錯誤
			{
				System.out.println("食性設定錯誤!");
				this.dietFavorite = NONE;
			}
		}
	}
	
	//設定顆粒大小
	public void setGrain(int selectorGrain) 
	{
		switch(selectorGrain) 
		{
			case BIG:
			{
				this.grain = BIG;
				break;
			}
			case MIDDLE:
			{
				this.grain = MIDDLE;
				break;
			}
			case SMALL:
			{
				this.grain = SMALL;
				break;
			}
			case SLIGHT:
			{
				this.grain = SLIGHT;
				break;
			}
			default:
			{
				System.out.println("顆粒大小設定錯誤!");
				this.grain = NONE;
			}
		}
	}
	
	//設定定時
	public void setRegularTime(int regularTime) 
	{
		switch(regularTime) 
		{
			case HALFDAY: //半天
			{
				this.regularTime = HALFDAY;
				break;
			}
			case ALLDAY: //全天
			{
				this.regularTime = ALLDAY;
				break;
			}
			
			default: 
			{
				System.out.printf("定時設定失敗!");
				this.regularTime = NONE;
			}
		}
	}
	
	//設定壞掉狀態 
	public void setCrazyFeed() 
	{
		this.crazyStatus = random.nextInt(2);
	}
	
	//³]©wÃa±¼ª¬ºA _2 
		public void setmyCrazyFeed(int crazyStatus) 
		   {
				this.crazyStatus = crazyStatus;
			}
			
	
	public void setAchieve(boolean input) 
	{
		this.achieve = input;
	}
	//get function
	public int getDietFavorite() 
	{
		return this.dietFavorite;
	}
	
	public int getGrain() 
	{
		return this.grain;
	}
	
	public int getRegularTime()
	{
		return this.regularTime;
	}
	
	public boolean getAchieve() 
	{
		return this.achieve;
	}

	
	//自動餵食，餵食最大上限 200 最大顆樹10顆 ==> 要時間有到才能吃 12/24hr
	public void feed(Enviroment environment) 
	{
		SecureRandom random = new SecureRandom();
		//設定餵食上限
		int times;
		if(this.grainTmp==50) 
			times=4;
		else
			times=10;
		if(( this.regularTime == HALFDAY && this.getCountTime() % 12 == 0 ) || ( this.regularTime == ALLDAY && this.getCountTime() % 24 == 0 ))
		{
			this.grain = this.grainTmp;
			//若設備壞損 進入壞掉的狀態
			if(this.getDamageRateNow()>=100)
				 crazyFeed(times*CRAZY_FEED_RATE, environment);
			else
			{
				//將飼料放入arraylist
				for(int i=0;i<times;i++)
				{
					int[] xyz= new int[3];
					xyz[0]=0+random.nextInt(6);
					xyz[1]=0+random.nextInt(2);
					xyz[2]=27+random.nextInt(7);
					environment.getFeedXY().add(xyz);
				}
			}
		}
		//每單位call function 都會++一次 代表開啟後的第x小時
	}
	
	//手動餵食
	public void manualFeed(Enviroment environment) 
	{
		SecureRandom random = new SecureRandom();
		//設定餵食上限
		int times;
		times=5;
		//暫時將飼料設定為中顆粒
		
		if(this.grain!=SMALL) {
			grainTmp = this.grain;
			this.grain = SMALL;
		}
		//將飼料放入arraylist
		for(int i=0;i<times;i++)
		{
			int[] xyz= new int[3];
			xyz[0]=0+random.nextInt(environment.getFishTankXYZSize()[0]-1);
			xyz[1]=0+random.nextInt(environment.getFishTankXYZSize()[1]/2);
			xyz[2]=0+random.nextInt(environment.getFishTankXYZSize()[2]/2);
			environment.getFeedXY().add(xyz);
		}
	}
	
	//設備損壞狀態
	private void crazyFeed(int times, Enviroment environment) 
	{
		//不餵
		if(this.crazyStatus == 0) ;
		//狂餵
		else 
		{
			for(int i=0;i<times;i++)
			{
				int[] xyz= new int[3];
				xyz[0]=0+random.nextInt(6);
				xyz[1]=0+random.nextInt(2);
				xyz[2]=27+random.nextInt(7);
				environment.getFeedXY().add(xyz);
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
		this.setCountTime(0);
		this.setCrazyFeed();
	}
	//儲存
	@Override
	public String saveData() 
	{
		String forwordStr = super.saveData();
		String str;
		str = String.format("crazyStatus:%d,dietFavorite:%d,grain:%d,regularTime:%d,achieve:%s,", this.crazyStatus, this.dietFavorite, this.grain, this.regularTime, this.achieve);
		return forwordStr + str;
	}
	@Override
	public String savetoData() 
	{
		String forwordStr = super.savetoData();
		String str;
		str = String.format("%d,%d,%d,%d,%s,", this.crazyStatus, this.dietFavorite, this.grain, this.regularTime, this.achieve);
		return forwordStr + str;
	}
	@Override
	public String toString() 
	{
		String forward;
		forward = super.toString();
		String str;
		str =  String.format("飼料種類：%s    顆粒大小：%d    定時:%s  狂餵狀態：%s %n", this.dietFavorite == CARNIVOROUS?"肉食性":"草食性", this.grain,this.regularTime==HALFDAY?"半天":"整天", this.crazyStatus == 0?"不餵":"狂餵");
		return forward + str;
	}
}
