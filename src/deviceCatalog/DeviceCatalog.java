package deviceCatalog;

import java.util.Scanner;

import cost.Cost;
import landScape.LandScape;
import database.Cost_data;
import database.Date_data;
import enviroment.Enviroment;
import timer.Timerr;

//設備目錄
public class DeviceCatalog {
	
	//-----------------------這些變數應該另外設定成一個檔案 Import---------------------------------------
		//設定失敗
		public static final int NONE = 0;
		//飼料種類
		public static final int CARNIVOROUS = 1;
		public static final int HERBIVOROUS = 2;
		//顆粒大小
		public static final int BIG = 50;
		public static final int MIDDLE = 10;
		public static final int SMALL = 5;
		public static final int SLIGHT = 1;
		//操作模式選擇
		public static final int HALFDAY = 1;
		public static final int ALLDAY = 2;
		//手動 or 自動模式常數
		public static final int MANUAL = 1;
		public static final int AUTOMATIC =2;
	//------------------------------------------------------------------
		
	//慣用丟棄變數
	int i;
	
	//列舉器具
	private static final int FEEDER = 1; //餵食器
	private static final int FILTER = 2; //過濾器
	private static final int INFLATOR = 3; //打氣汞
	private static final int FLASH_LIGHT = 4; //照明器
	private static final int HEATER = 5; //加溫器
	private static final int NET = 6; //網子
	private static final int CHANGE_WATER = 7; //換水
	
	//目錄內所有設備 各四種
	public static Feeder[] feeder = new Feeder[4]; //餵食器列表
	public static Filter[] filter = new Filter[4]; //過濾器列表
	public static Inflator[] inflator = new Inflator[4]; //打氣汞列表
	public static FlashLight [] flashLight = new FlashLight[4]; //照明器列表
	public static Heater[] heater = new Heater[4]; //加熱器列表
	//互動物件 單一個就足夠了
	public static FishNet fishNet; //撈網
	public static ChangeWater changeWater; //換水
	
	//Cost_data傳給資料庫的物件
	private Cost_data costConnect = new Cost_data();
	private Date_data dateConnect = new Date_data();
	//throw
	int k;
	
	//建構設備目錄 每種設備有4種 None 初階 中階 高階
	public DeviceCatalog() 
	{
		//餵食器
		{	
			//Feeder(String name, int price, String statement, int OperateModel, int Favorite, int Grain, int regularTime) 
			//設備名稱	設備價格		設備敘述		操作模式		飼料種類		顆粒大小		定時模式
			feeder[0] = new Feeder("無", 0, "為魚隻提供食物，可彌補使用者無法定時餵食的問題",NONE, NONE, NONE, NONE);
			feeder[1] = new Feeder("普通餵食器", 700, "為魚隻提供食物，可彌補使用者無法定時餵食的問題。適合各種規格水族缸", AUTOMATIC, CARNIVOROUS, BIG, ALLDAY);
			feeder[2] = new Feeder("高級餵食器", 1000, "分段式調節可控制飼料餵食量", AUTOMATIC, CARNIVOROUS, BIG, ALLDAY);
			feeder[3] = new Feeder("最高級餵食器", 1300, "高度自由調整，多角度固定座設計", AUTOMATIC, CARNIVOROUS, BIG, ALLDAY);
			
		}
		//過濾器
		{
			// Filter(String name, int price, String statement, int operateModel) 
			//設備名稱	設備價格		設備敘述		操作模式
			filter[0] = new Filter("無", 0, "將髒汙或糞便過濾掉，可穩定水質，避免水質因此惡化太快而使魚隻生病或死亡", AUTOMATIC);
			filter[1] = new Filter("普通過濾器", 1000, "波型水流出水口，雙活性碳插片，培養最適合飼育幼苗的水質及長時效的水質維持，加上防止幼苗吸入裝置；靜音式設計加可調式流量調節閥，調整最適水流量及溶氧量以供生物生長，長，最適1~2尺缸的有框、無框、玻璃、壓克力、彎曲等之魚缸使用。", AUTOMATIC);
			filter[2] = new Filter("高級過濾器", 2000, "內附馬達．原廠濾材陶瓷環、滴流管、生化棉，不使用白棉，不易阻塞水流，雙接頭一體式設計。", AUTOMATIC);
			filter[3] = new Filter("最高級過濾器", 3000, "強迫式過濾模式，配有油膜處理器，啟動免加水，可調節入水及出水水量，濾材盒可自由搭配所需濾材，清潔提示鈕當濾材阻塞時會警示更換或清洗，不阻塞水流，取出濾材盒清洗時，可利用上蓋放置，不滴水，配有加溫管固定夾", AUTOMATIC);
			
		}
		//打氣汞
		{
			//Inflator(String name, int price, String statement) 
			//設備名稱 	設備價格		設備敘述
			inflator[0] = new Inflator("無", 0, "增加水的空氣量並促進空氣溶於水中，可避免水族箱因含氧量不足而有魚隻死亡");
			inflator[1] = new Inflator("普通打氣汞", 200, "單孔靜音型打氣機 ，適用60L以下魚缸，最大出氣量可達 1.2 L/min ");
			inflator[2] = new Inflator("高級打氣汞", 300, "雙孔兩段式調整靜音型打氣機，適用200L以下魚缸， 最大出氣量可達 3 L/min");
			inflator[3] = new Inflator("最高級打氣汞", 400, "雙孔多段式微調靜音型打氣機，適用400L以下魚缸，最大出氣量可達 4 L/min");
		}
		//照明器
		{
			//public FlashLight(String name, int price, String statement, int operateModel) 
			//設備名稱	設備價格		設備敘述		操作模式
			flashLight[0] = new FlashLight("無", 0, "提供水草行光合作用的光線", AUTOMATIC);
			flashLight[1] = new FlashLight("普通照明器", 200, "搭配柔光罩呈現舒適自然光，可調式蛇管方便調整角度，適合小型缸，燈具尺寸10公分", AUTOMATIC);
			flashLight[2] = new FlashLight("高級照明器", 500, "金屬軟管燈夾可隨意調整光源角度，金屬材質外殼散熱效率高，燈具組採用防潑水設計，可保護LED及零組件，適用各式魚缸，燈具尺寸17公分", AUTOMATIC);
			flashLight[3] = new FlashLight("最高級照明器", 800, "金屬軟管燈夾可隨意調整光源角度，金屬材質外殼散熱效率高，燈具組採用防潑水設計，可保護LED及零組件，適用各式魚缸，燈具尺寸32公分", AUTOMATIC);
			
		}
		//加溫器
		{
			//Heater(String name, int price, String statement, int operateModel, double maxTemperature) 
			//設備名稱	設備價格		設備敘述		操作模式		最大加熱上限
			heater[0] = new Heater("無", 0, "提升水族箱內水的溫度，可避免魚隻因溫度過低而死亡或生病，適時使用可協助維持水溫", AUTOMATIC, 28);
			heater[1] = new Heater("初階加熱器", 200, "採用耐高溫防爆石英管，純加熱，無感應器", AUTOMATIC, 28);
			heater[2] = new Heater("中階加熱器", 400, "採用耐高溫防爆石英管，內藏式雙感溫器，離水斷電", AUTOMATIC, 28);
			heater[3] = new Heater("高階加熱器", 600, "採用耐高溫防爆石英管，加溫管離水空燒，自動斷電，冷卻復歸，電子IC電路，能精確控制水中溫度", AUTOMATIC, 28);	
		}
		//撈魚網
		{
			//public FishNet(String statement) 
			fishNet = new FishNet("清除死亡的魚，避免其影響水質或是使水族箱內其他魚生病");
		}
		//換水
		{
			//public ChangeWater(String statement) 
			changeWater = new ChangeWater("將魚缸的水質變乾淨，但要注意不可以更換過多的水以免造成魚缸環境變化太大而導致魚隻死亡");
		}
	}
	//----------------------------購買需要加入時間 時間傳給cost給資料庫紀錄---------------
	//買餵食器
	public void buyFeeder(int level, Cost cost, Timerr timer) 
	{
		//將購買資料送到資料庫
		costConnect.insertTable(feeder[level].getName(), feeder[level].getPrice(), dateConnect.SelectDate(timer.toStringToDB()));
		//舊的device內的重設變數
		this.feeder[getFeederSelector()].newOne();
		//加入購買金額
		cost.addnewCost(feeder[level].getPrice());
		//新買的device內重設變數
		this.feeder[level].newOne();
		//feeder特別變數重設定
		//this.feeder[level].setFavorite(favorite); 
		//this.feeder[level].setGrain(grain);	
		//this.feeder[level].setRegularTime(regularTime);
		this.feeder[level].setCrazyFeed(); //狂餵狀態 內部會再一次隨機給

		for(k=0;k<4;k++) 
		{
			if(level != k) 
				feeder[k].setHaveBuy(0);
		}
		feeder[level].setHaveBuy(1);
	}
	//loadÁý­¹¾¹
			public void loadFeeder(String all) 
			{
			 			
				String[] value=all.split(",");
				
				int level =Integer.parseInt(value[0]);
				Boolean bool_state;
				Boolean bool_achieve;
				if(value[2].equals("true")) {
					bool_state=true;
					}
				else 
					bool_state =false;
				if(value[9].equals("true")) {
					bool_achieve=true;
					}
				else 
					bool_achieve =false;
				
				//ÂÂªºdevice¤ºªº­«³]ÅÜ¼Æ
				this.feeder[getFeederSelector()].newOne();
			
				//·s¶Rªºdevice¤º­«³]ÅÜ¼Æ
				this.feeder[level].newOne();
				//feeder¯S§OÅÜ¼Æ­«³]©w
				this.feeder[level].setCountTime(Integer.parseInt(value[1]));
				this.feeder[level].setModelState(bool_state);
				this.feeder[level].setDamageRateNow(Double.parseDouble(value[3]) );
				this.feeder[level].setOperateModel(Integer.parseInt(value[4]));
				this.feeder[level].setmyCrazyFeed(Integer.parseInt(value[5])); //¨gÁýª¬ºA ¤º³¡·|¦A¤@¦¸ÀH¾÷µ¹	
				this.feeder[level].setFavorite(Integer.parseInt(value[6]));
				this.feeder[level].setGrain(Integer.parseInt(value[7]));	 
				this.feeder[level].setRegularTime(Integer.parseInt(value[8]));	
	            this.feeder[level].setAchieve(bool_achieve);
		
				for(k=0;k<4;k++) 
				{
					if(level != k) 
						feeder[k].setHaveBuy(0);
				}
				feeder[level].setHaveBuy(1);
			}
	
	//買過濾器
	public void buyFilter(int level, Cost cost, Timerr timer) 
	{
		//將購買資料送到資料庫
		costConnect.insertTable(filter[level].getName(), filter[level].getPrice(), dateConnect.SelectDate(timer.toStringToDB()));
		//舊的device內的重設變數
		this.filter[getFilterSelector()].newOne();
		//加入購買金額
		cost.addnewCost(filter[level].getPrice());
		//device內重設變數
		this.filter[level].newOne();
		//this.filter[level].changeModel(true);
		for(k=0;k<4;k++) 
		{
			if(level != k) 
				filter[k].setHaveBuy(0);
		}
		filter[level].setHaveBuy(1);
	}
	 //loadfilter
	public void loadFilter(String all) 
	{
	 			
		String[] value=all.split(",");
		
		int level =Integer.parseInt(value[0]);
		Boolean bool_state;
		if(value[2].equals("true")) {
			bool_state=true;
			}
		else 
			bool_state =false;

		
		//ÂÂªºdevice¤ºªº­«³]ÅÜ¼Æ
		this.filter[getFeederSelector()].newOne();
	
		//·s¶Rªºdevice¤º­«³]ÅÜ¼Æ
		this.filter[level].newOne();
		//feeder¯S§OÅÜ¼Æ­«³]©w
		this.filter[level].setCountTime(Integer.parseInt(value[1]));
		this.filter[level].setModelState(bool_state);
		this.filter[level].setDamageRateNow(Double.parseDouble(value[3]) );
		this.filter[level].setOperateModel(Integer.parseInt(value[4]));


		for(k=0;k<4;k++) 
		{
			if(level != k) 
				filter[k].setHaveBuy(0);
		}
		filter[level].setHaveBuy(1);
	}	
	
	
	//買打氣汞
	public void buyInflator(int level, Cost cost, Timerr timer) 
	{
		//將購買資料送到資料庫
		costConnect.insertTable(inflator[level].getName(), inflator[level].getPrice(), dateConnect.SelectDate(timer.toStringToDB()));
		//舊的device內的重設變數
		this.inflator[getInflatorSelector()].newOne();
		//加入購買金額
		cost.addnewCost(inflator[level].getPrice());
		//device內重設變數
		this.inflator[level].newOne();
		//inflator特別變數重設
		this.inflator[level].setBestProportion(0);
		//this.inflator[level].changeModel(true);
		for(k=0;k<4;k++) 
		{
			if(level != k) 
				inflator[k].setHaveBuy(0);
		}
		inflator[level].setHaveBuy(1);
	}
	 //loadfilter
	public void loadInflator(String all) 
	{
	 			
		String[] value=all.split(",");
		
		int level =Integer.parseInt(value[0]);
		Boolean bool_state;
		if(value[2].equals("true")) {
			bool_state=true;
			}
		else 
			bool_state =false;

		
		//ÂÂªºdevice¤ºªº­«³]ÅÜ¼Æ
		this.inflator[getFeederSelector()].newOne();
	
		//·s¶Rªºdevice¤º­«³]ÅÜ¼Æ
		this.inflator[level].newOne();
		//feeder¯S§OÅÜ¼Æ­«³]©w
		this.inflator[level].setCountTime(Integer.parseInt(value[1]));
		this.inflator[level].setModelState(bool_state);
		this.inflator[level].setDamageRateNow(Double.parseDouble(value[3]) );
		this.inflator[level].setOperateModel(Integer.parseInt(value[4]));


		for(k=0;k<4;k++) 
		{
			if(level != k) 
				inflator[k].setHaveBuy(0);
		}
		inflator[level].setHaveBuy(1);
	}
	
	
	
	//買加溫器
	public void buyheater(int level, Cost cost, Timerr timer) 
	{
		//將購買資料送到資料庫
		costConnect.insertTable(heater[level].getName(), heater[level].getPrice(), dateConnect.SelectDate(timer.toStringToDB()));
		//舊的device內的重設變數
		this.heater[getHeaterSelector()].newOne();
		//加入購買金額
		cost.addnewCost(heater[level].getPrice());
		//device內重設變數
		this.heater[level].newOne();
		//heater內重設變數
		//this.heater[level].setMaxTemperature(maxTemperature);
		//this.heater[level].changeModel(true);
		for(k=0;k<4;k++) 
		{
			if(level != k) 
				heater[k].setHaveBuy(0);
		}
		heater[level].setHaveBuy(1);
	}
	
	
	//loadheater
		public void loadheater(String all) 
		{
		 			
			String[] value=all.split(",");
			
			int level =Integer.parseInt(value[0]);
			Boolean bool_state;
			if(value[2].equals("true")) {
				bool_state=true;
				}
			else 
				bool_state =false;

			
			//ÂÂªºdevice¤ºªº­«³]ÅÜ¼Æ
			this.heater[getFeederSelector()].newOne();
		
			//·s¶Rªºdevice¤º­«³]ÅÜ¼Æ
			this.heater[level].newOne();
			//feeder¯S§OÅÜ¼Æ­«³]©w
			this.heater[level].setCountTime(Integer.parseInt(value[1]));
			this.heater[level].setModelState(bool_state);
			this.heater[level].setDamageRateNow(Double.parseDouble(value[3]) );
			this.heater[level].setOperateModel(Integer.parseInt(value[4]));
			this.heater[level].setMaxTemperature(Double.parseDouble(value[5]) );

			for(k=0;k<4;k++) 
			{
				if(level != k) 
					heater[k].setHaveBuy(0);
			}
			heater[level].setHaveBuy(1);
		}
	
	
	//買照明器
	public void buyflashLight(int level, Cost cost, Timerr timer) 
	{
		//將購買資料送到資料庫
		costConnect.insertTable(flashLight[level].getName(),flashLight[level].getPrice(), dateConnect.SelectDate(timer.toStringToDB()));
		//舊的device內的重設變數
		this.flashLight[getFlashLightSelector()].newOne();
		//加入購買金額
		cost.addnewCost(flashLight[level].getPrice());
		//device內重設變數
		this.flashLight[level].newOne();
		//flashLight內設變數
		//this.flashLight[level].setLightHour(lighthour);
		//this.flashLight[level].changeModel(true);
		for(k=0;k<4;k++) 
		{
			if(level != k) 
				flashLight[k].setHaveBuy(0);
		}
		flashLight[level].setHaveBuy(1);
	}
	
	 //loadflashlight
	public void loadflashLight(String all) 
	{
	 			
		String[] value=all.split(",");
		
		int level =Integer.parseInt(value[0]);
		Boolean bool_state;
		if(value[2].equals("true")) {
			bool_state=true;
			}
		else 
			bool_state =false;

		
		//ÂÂªºdevice¤ºªº­«³]ÅÜ¼Æ
		this.flashLight[getFeederSelector()].newOne();
	
		//·s¶Rªºdevice¤º­«³]ÅÜ¼Æ
		this.flashLight[level].newOne();
		//feeder¯S§OÅÜ¼Æ­«³]©w
		this.flashLight[level].setCountTime(Integer.parseInt(value[1]));
		this.flashLight[level].setModelState(bool_state);
		this.flashLight[level].setDamageRateNow(Double.parseDouble(value[3]) );
		this.flashLight[level].setOperateModel(Integer.parseInt(value[4]));
		this.flashLight[level].setLightHour(Integer.parseInt(value[5]));

		for(k=0;k<4;k++) 
		{
			if(level != k) 
				flashLight[k].setHaveBuy(0);
		}
		flashLight[level].setHaveBuy(1);
	}
	



	//回傳購買狀態
	public int getFeederbuyer() 
	{
		int level = 0;
		for(i=0;i<feeder.length;i++) 
			//確認器材以購買
			if(feeder[i].getHaveBuy())
				level = i;
		return level;
	}
	public int getFilterbuyer() 
	{
		int level = 0;
		for(i=0;i<filter.length;i++) 
			//確認器材以購買
			if(filter[i].getHaveBuy())
				level = i;
		return level;
	}
	public int getInflatorbuyer() 
	{
		int level = 0;
		for(i=0;i<inflator.length;i++) 
			//確認器材以購買
			if(inflator[i].getHaveBuy())
				level = i;
		return level;
	}
	public int getHeaterbuyer() 
	{
		int level = 0;
		for(i=0;i<heater.length;i++) 
			//確認器材以購買
			if(heater[i].getHaveBuy())
				level = i;
		return level;
	}
	public int getFlashLightbuyer() 
	{
		int level = 0;
		for(i=0;i<flashLight.length;i++) 
			//確認器材以購買
			if(flashLight[i].getHaveBuy())
				level = i;
		return level;
	}
	//確認餵食器開啟狀態
	public int getFeederSelector() 
	{
		int level = 0;
		for(i=0;i<feeder.length;i++) 
			//確認器材打開
			if(feeder[i].getModleState())
				level = i;
		return level;
	}
	//確認過濾器開啟狀態
	public int getFilterSelector() 
	{
		int level = 0;
		for(i=0;i<filter.length;i++) 
			//確認器材打開
			if(filter[i].getModleState())
				level = i;
		return level;
	}
	//確認打氣汞開啟狀態
	public int getInflatorSelector() 
	{
		int level = 0;
		for(i=0;i<inflator.length;i++) 
			//確認器材打開
			if(inflator[i].getModleState())
				level = i;
		return level;
	}
	//確認加溫器開啟狀態
	public int getHeaterSelector() 
	{
		int level = 0;
		for(i=0;i<heater.length;i++) 
			//確認器材打開
			if(heater[i].getModleState())
				level = i;
		return level;
	}
	//確認照明器開啟狀態
	public int getFlashLightSelector() 
	{
		int level = 0;
		for(i=0;i<flashLight.length;i++) 
			//確認器材打開
			if(flashLight[i].getModleState())
				level = i;
		return level;
	}
	
	//確認餵食器有沒有壞掉
	public int getHaveDamagedFeeder() 
	{
		int level = 0;
		for(i=0;i<feeder.length;i++) 
			//確認器材打開
			if(feeder[i].getDamageRateNow()>=100)
				level = i;
		return level;
	}
	//確認過濾器有沒有壞掉
	public int getHaveDamagedFilter() 
	{
		int level = 0;
		for(i=0;i<filter.length;i++) 
			//確認器材打開
			if(filter[i].getDamageRateNow()>=100)
				level = i;
		return level;
	}
	//確認打氣汞有沒有壞掉
	public int getHaveDamagedInflator() 
	{
		int level = 0;
		for(i=0;i<inflator.length;i++) 
			//確認器材打開
			if(inflator[i].getDamageRateNow()>=100)
				level = i;
		return level;
	}
	//確認加溫器有沒有壞掉
	public int getHaveDamagedHeater() 
	{
		int level = 0;
		for(i=0;i<heater.length;i++) 
			//確認器材打開
			if(heater[i].getDamageRateNow()>=100)
				level = i;
		return level;
	}
	//確認照明氣有沒有壞掉
	public int getHaveDamagedFlashLight() 
	{
		int level = 0;
		for(i=0;i<flashLight.length;i++) 
			//確認器材打開
			if(flashLight[i].getDamageRateNow()>=100)
				level = i;
		return level;
	}

	
	//每單位時間呼叫檢測發生事件
	public void aTime(Enviroment environment, LandScape landScape) 
	{
		//加熱器做事
		i = getHeaterSelector(); //確認是哪一個器材有打開
		if(i!=0)
		{
			//每兩個週期發動一次
			if(heater[i].getCountTime()%2==0)
			{
				heater[i].heat(environment);
				heater[i].damageDeviceByTime();
			}
			heater[i].countingTime();
		}
		//餵食器做事
		i = getFeederSelector(); //確認是哪一個器材有打開
		if(i!=0)
		{
			//內部才做Check半天全天
			feeder[i].feed(environment);
			feeder[i].damageDeviceByTime();
			feeder[i].countingTime();
		}
		
		//照明器做事
		i = getFlashLightSelector(); //確認是哪一個器材有打開
		if(i!=0)
		{
			//減少剩餘照明時間
			flashLight[i].lighthourDecrease();
			if(flashLight[i].getCountTime()%3==0)
			{
				flashLight[i].affectWaterPlant(landScape);
				flashLight[i].damageDeviceByTime();
			}
			flashLight[i].countingTime();
		}
		
		//打氣汞做事
		i = getInflatorSelector(); //確認是哪一個器材有打開
		if(i!=0)
		{
			if(inflator[i].getCountTime()%2==0)
			{
				inflator[i].pump(environment);
				inflator[i].damageDeviceByTime();
			}
			inflator[i].countingTime();
		}
		
		//過濾器做事
		i = getFilterSelector(); //確認是哪一個器材有打開
		if(i!=0)
		{
			if(filter[i].getCountTime()%2==0)
			{
				filter[i].filterAll(environment);
				filter[i].damageDeviceByTime();
			}
			filter[i].countingTime();
		}
	}
	
	//Àx¦sÁý­¹¾¹
		public String saveFeederData() 
		{
			String feederStr;
			feederStr = String.format("feederbuyer:%d,%s", this.getFeederbuyer(), this.feeder[this.getFeederbuyer()].savetoData());
			return feederStr;
		}
		public String uploadFeederData()
		{
			String feederStr;
			feederStr = String.format("%d,%s", this.getFeederbuyer(), this.feeder[this.getFeederbuyer()].savetoData());
			return feederStr;
		}
	//Àx¦s¹LÂo¾¹
		public String saveFilterData() 
		{
			String filterStr;
			filterStr = String.format("filterbuyer:%d,%s",  this.getFilterbuyer(), this.filter[this.getFilterbuyer()].savetoData());
			return filterStr;
		}
		public String uploadFilterData() 
		{
			String filterStr;
			filterStr = String.format("%d,%s",  this.getFilterbuyer(), this.filter[this.getFilterbuyer()].savetoData());
			return filterStr;
		}
		//Àx¦s¥´®ð¨E
		public String saveInflatorData() 
		{
			String inflatorStr;
			inflatorStr = String.format("inflatorbuyer:%d,%s",  this.getInflatorbuyer(), this.inflator[this.getInflatorbuyer()].savetoData());
			return inflatorStr;
		}
		public String uploadInflatorData() 
		{
			String inflatorStr;
			inflatorStr = String.format("%d,%s",  this.getInflatorbuyer(), this.inflator[this.getInflatorbuyer()].savetoData());
			return inflatorStr;
		}
		//Àx¦s·Ó©ú¾¹
		public String saveFlashLightData() 
		{
			String flashLightStr;
			flashLightStr = String.format("flashLightbuyer:%d,%s",   this.getFlashLightbuyer(), this.flashLight[this.getFlashLightbuyer()].savetoData());
			return flashLightStr;
		}
		public String uploadFlashLightData() 
		{
			String flashLightStr;
			flashLightStr = String.format("%d,%s",   this.getFlashLightbuyer(), this.flashLight[this.getFlashLightbuyer()].savetoData());
			return flashLightStr;
		}
		//Àx¦s¥[¼ö¾¹
		public String saveHeaterData() 
		{
			String heaterStr;
			heaterStr = String.format("heaterbuyer:%d,%s",    this.getHeaterbuyer(), this.heater[this.getHeaterbuyer()].savetoData());
			return heaterStr;
		}
		public String uploadHeaterData() 
		{
			String heaterStr;
			heaterStr = String.format("%d,%s",    this.getHeaterbuyer(), this.heater[this.getHeaterbuyer()].savetoData());
			return heaterStr;
		}
		//Àx¦s¿ï¾Üª¬ºA
		public String saveSelectorData() 
		{
			String selectStr;
			selectStr = String.format("feed:%d,filter:%d,inflator:%d,flashlight:%d,heater:%d", this.getFeederSelector(), this.getFilterSelector(), this.getInflatorSelector(), this.getFlashLightSelector(), this.getHeaterSelector());
			return selectStr;
		}
		public String uploadSelectorData() 
		{
			String selectStr;
			selectStr = String.format("%d,filter:%d,inflator:%d,flashlight:%d,heater:%d", this.getFeederSelector(), this.getFilterSelector(), this.getInflatorSelector(), this.getFlashLightSelector(), this.getHeaterSelector());
			return selectStr;
		}
		//Â²³æª©Àx¦s¿ï¾Üª¬ºA
		public String savetoSelectorData() 
		{
			String selectStr;
			selectStr = String.format("feed:%d,%d,%d,%d,%d", this.getFeederSelector(), this.getFilterSelector(), this.getInflatorSelector(), this.getFlashLightSelector(), this.getHeaterSelector());
			return selectStr;
		}
		public String uploadtoSelectorData() 
		{
			String selectStr;
			selectStr = String.format("%d,%d,%d,%d,%d", this.getFeederSelector(), this.getFilterSelector(), this.getInflatorSelector(), this.getFlashLightSelector(), this.getHeaterSelector());
			return selectStr;
		}
		//Â²³æª©Àx¦s¿ï¾Üª¬ºA
			public void loadtoSelectorData(int feed_buy,int filter_buy,int inflator_buy,int flashlight_buy,int heater_buy) 
			{
				feeder[feed_buy].setHaveBuy(1);
				filter[filter_buy].setHaveBuy(1);
				inflator[inflator_buy].setHaveBuy(1);
				flashLight[flashlight_buy].setHaveBuy(1);
				heater[heater_buy].setHaveBuy(1);
		
			}
	
	//印出所有東西的狀態 for test -----這邊不用重構-----
	public void printAll() 
	{
		for(i=0;i<heater.length;i++) 
		{
			System.out.printf("%s",heater[i].toString());
		}
		System.out.println("");
		for(i=0;i<feeder.length;i++) 
		{
			System.out.printf("%s",feeder[i].toString());
		}
		System.out.println("");
		for(i=0;i<flashLight.length;i++) 
		{
			System.out.printf("%s",flashLight[i].toString());
		}
		System.out.println("");
		for(i=0;i<inflator.length;i++) 
		{
			System.out.printf("%s",inflator[i].toString());
		}
		System.out.println("");
		for(i=0;i<filter.length;i++) 
		{
			System.out.printf("%s",filter[i].toString());
		}
		System.out.println("");
	} 
	
	
	
	//器材種類 器材編號[0-4] for test -----這邊不用重構-----
	public void set(int tool, int number) 
	{
		Scanner scanner = new Scanner(System.in);
		int selector;
		double input;
		switch(tool) 
		{
			case FEEDER:
			{
				System.out.printf("%n想對餵食器弄啥?%n");
				System.out.println("1.設定食性");
				System.out.println("2.設定顆粒");
				System.out.println("3.設定時間");
				System.out.println("4.開/關");
				System.out.println("5.操作模式");
				selector = scanner.nextInt();
				switch(selector) 
				{
					case 1:
					{
						System.out.printf("%s 設定成什麼食性?%n 1.肉食性 2.草食性%n",feeder[number].getName());
						selector = scanner.nextInt();
						feeder[number].setFavorite(selector);
						break;
					}
					case 2:
					{
						System.out.printf("%s 設定成什麼顆粒?%n 1.細 5.小 10.中 50.大%n",feeder[number].getName());
						selector = scanner.nextInt();
						feeder[number].setGrain(selector);
						break;
					}
					case 3:
					{
						System.out.printf("%s 設定成什麼時間?%n 1.半天 2.全天 %n",feeder[number].getName());
						selector = scanner.nextInt();
						feeder[number].setRegularTime(selector);
						break;
					}
					case 4:
					{
						System.out.printf("%s 設定成開關?目前狀態：%s %n 1.開 2.關 %n",feeder[number].getName(), feeder[number].getModleState()== true?"開":"關");
						selector = scanner.nextInt();
						if(selector == 1)
						{
							for(i=0;i<feeder.length && i != number;i++)
							{
								feeder[i].setModelState(false);
							}
							feeder[number].setModelState(true);
						}
						else
							feeder[number].setModelState(false);
						break;
					}
					case 5:
					{
						System.out.printf("%s 設定成什麼操作模式?%n 1.手動 2.自動 %n",feeder[number].getName());
						selector = scanner.nextInt();
						feeder[number].setOperateModel(selector);
						break;
					}
				}
				break;
			}
			
			case HEATER:
			{
				System.out.printf("%n想對加溫器弄啥?%n");
				System.out.println("1.設定最大溫度(<28)");
				System.out.println("4.開/關");
				System.out.println("5.操作模式");
				selector = scanner.nextInt();
				
				switch(selector) 
				{
					case 1:
					{
						System.out.println("輸入想要設定的最大溫度(<28)");
						input = scanner.nextDouble();
						heater[number].setMaxTemperature(input);
						break;
					}
					case 4:
					{
						System.out.printf("%s 設定成開關?目前狀態：%s %n 1.開 2.關 %n",heater[number].getName(), heater[number].getModleState()== true?"開":"關");
						selector = scanner.nextInt();
						if(selector == 1)
						{
							for(i=0;i<heater.length && i != number;i++)
							{
								heater[i].setModelState(false);
							}
							heater[number].setModelState(true);
						}
						else
							heater[number].setModelState(false);
						break;
					}
					case 5:
					{
						System.out.printf("%s 設定成什麼操作模式?%n 1.手動 2.自動 %n",heater[number].getName());
						selector = scanner.nextInt();
						heater[number].setOperateModel(selector);
						break;
					}
				}
				break;
			}
			
			case FILTER:
			{
				System.out.printf("%n想對過濾器弄啥?%n");
				System.out.println("4.開/關");
				System.out.println("5.操作模式");
				selector = scanner.nextInt();
				switch(selector)
				{
					case 4:
					{
						System.out.printf("%s 設定成開關?目前狀態：%s %n 1.開 2.關 %n",filter[number].getName(), filter[number].getModleState()== true?"開":"關");
						selector = scanner.nextInt();
						if(selector == 1)
						{
							for(i=0;i<filter.length && i != number;i++)
							{
								filter[i].setModelState(false);
							}
							filter[number].setModelState(true);
						}
						else
							filter[number].setModelState(false);
						break;
					}
					case 5:
					{
						System.out.printf("%s 設定成什麼操作模式?%n 1.手動 2.自動 %n",filter[number].getName());
						selector = scanner.nextInt();
						filter[number].setOperateModel(selector);
						break;
					}
				} 
				break;
			}
			
			case INFLATOR:
			{
				System.out.printf("%n想對打氣汞弄啥?%n");
				System.out.println("4.開/關");
				System.out.println("5.操作模式");
				selector = scanner.nextInt();
				switch(selector)
				{
					case 4:
					{
						System.out.printf("%s 設定成開關?目前狀態：%s %n 1.開 2.關 %n",inflator[number].getName(), inflator[number].getModleState()== true?"開":"關");
						selector = scanner.nextInt();
						if(selector == 1)
						{
							for(i=0;i<inflator.length && i != number;i++)
							{
								inflator[i].setModelState(false);
							}
							inflator[number].setModelState(true);
						}
						else
							inflator[number].setModelState(false);
						break;
					}
					case 5:
					{
						System.out.printf("%s 設定成什麼操作模式?%n 1.手動 2.自動 %n",inflator[number].getName());
						selector = scanner.nextInt();
						inflator[number].setOperateModel(selector);
						break;
					}
				} 
				break;
			}
			
			case FLASH_LIGHT:
			{
				System.out.printf("%n想對照明器弄啥?%n");
				System.out.println("1.設定照明時間");
				System.out.println("4.開/關");
				System.out.println("5.操作模式");
				selector = scanner.nextInt();
				switch(selector)
				{
					case 1:
					{
						System.out.printf("要照射多少時間?%n %n",flashLight[number].getName());
						selector = scanner.nextInt();
						flashLight[number].setLightHour(selector);
						break;
					}
					case 4:
					{
						System.out.printf("%s 設定成開關?目前狀態：%s %n 1.開 2.關 %n",flashLight[number].getName(), flashLight[number].getModleState()== true?"開":"關");
						selector = scanner.nextInt();
						if(selector == 1)
						{
							for(i=0;i<flashLight.length && i != number;i++)
							{
								flashLight[i].setModelState(false);
							}
							flashLight[number].setModelState(true);
						}
						else
							flashLight[number].setModelState(false);
						break;
					}
					case 5:
					{
						System.out.printf("%s 設定成什麼操作模式?%n 1.手動 2.自動 %n",flashLight[number].getName());
						selector = scanner.nextInt();
						flashLight[number].setOperateModel(selector);
						break;
					}
				} 
				break;
			}

			case CHANGE_WATER:
			{
				System.out.printf("%n想對換水弄啥?%n");
				System.out.println("1.設定水種");
				System.out.println("4.開/關");
				System.out.println("5.操作模式");
				selector = scanner.nextInt();
				switch(selector)
				{
					case 1:
					{
						System.out.printf("要換哪種水?%n 1.淡水 2.海水%n",changeWater.getName());
						selector = scanner.nextInt();
						changeWater.setWaterCategory(selector);
						break;
					}
					case 4:
					{
						System.out.printf("%s 設定成開關?目前狀態：%s %n 1.開 2.關 %n",changeWater.getName(), changeWater.getModleState()== true?"開":"關");
						selector = scanner.nextInt();
						if(selector == 1)
							changeWater.setModelState(true);
						else
							changeWater.setModelState(false);
						break;
					}
					case 5:
					{
						System.out.printf("%s 設定成什麼操作模式?%n 1.手動 2.自動 %n",changeWater.getName());
						selector = scanner.nextInt();
						changeWater.setOperateModel(selector);
						break;
					}
				} 
				break;	
			}
			
			case NET:
			{
				break;
			}
		}
	}

}
