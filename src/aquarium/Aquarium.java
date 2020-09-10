package aquarium;

import java.util.ArrayList;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import cost.Cost;
import deviceCatalog.DeviceCatalog;
import landScape.LandScape;
import database.Date_data;
import database.Event_data;
import database.Fish_data;

import java.security.SecureRandom;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import fish.Fish;
import fish.Fish.FishStatus;
import fish.FishCataLog;
import event.AllEvent;
import event.Event;
import event.enviromentEvent.Feed;
import event.enviromentEvent.Move;
import enviroment.Enviroment;
import enviroment.Enviroment.Water;
import timer.Timerr;
import timer.Timerr.speedUpTime;
import userName.UserName;


public class Aquarium {
	static final public int maxFishsCount=20;
	static final private FishCataLog fishCataLog=new FishCataLog();

	//存入資料庫時用的參數
	private Fish_data  dbFish=new Fish_data();
	private Date_data  dbDate=new Date_data();
	
	//需get.set===========================================
	private int nFishs=0;	//魚缸中的魚數量
	private Timerr timer;


	private Fish[] fishs;
	private Enviroment enviroment;	//參數全部先預設為50


	//設備物件
		//設備、擺設、cost
	private DeviceCatalog device;
	private LandScape landSpace;
	private Cost cost;
	//=======================================================
	private AllEvent allEvent;
	private Move moveEvent;
	private Feed feedEvent;
	//********進階功能 成就系統
	
	//事件提示功能
	private int[] eventArray =new int[800];		//記錄此次模擬發生了哪些事件
	private String[] eventArrayDescription=new String[800];	//發生事件的描述
	private int nEvent=0;
	private String[] eventString = {"魚打架", "自然死亡", "非自然死亡", "魚生病", "魚生長至最大", "餵食器壞掉", "過濾器壞掉", "氧氣泵壞掉", "照明器壞掉", "加溫器壞掉",
			 "水質不良", "水質糟糕", "溫度偏高", "溫度過高", "溫度偏低", "溫度過低", "含氧量偏低", "含氧量過低", "含氧量極低",};
	/*
	 事件種類
	1. 魚打架   
	2. 自然死亡
	3. 非自然死亡 
	4. 魚生病   
	5. 魚生長至最大
	6. 餵食器壞掉  
	7. 過濾器壞掉 
	8. 氧氣泵壞掉 
	9. 照明器壞掉  
	10. 加溫器壞掉 
	11.水質不良  
	12.水質糟糕 
	13.溫度偏高  
	14.溫度過高  
	15.溫度偏低 
	16.溫度過低  
	17.含氧量偏低 
	18.含氧量過低
	19.含氧量極低
	 
	 */
	
	
	/*
	
	前端所有的呼叫都會在這面寫出對應的method
	
	*/
	public Aquarium()
	{
		
		fishs=new Fish[maxFishsCount];
		allEvent=new AllEvent();
		moveEvent=new Move();
		feedEvent=new Feed();
		enviroment=new Enviroment(2,100,28,0,50,100,Water.OCEAN);
		timer=new Timerr();
		device=new DeviceCatalog();
		landSpace=new LandScape();
		cost=new Cost();
		this.landSpace.setTableSize(enviroment.getFishTankSize()+1);
		device.feeder[0].changeModel(true);
		device.filter[0].changeModel(true);
		device.inflator[0].changeModel(true);
		device.heater[0].changeModel(true);
		device.printAll();
		Event.event = new Event_data();
		Event.setTime(timer);

		//將第1/1/1 00:00的時間放入資料庫中
		dbDate.insertTable(timer.toStringToDB());
	}
	
	public Aquarium(int size,int water)
	{
		fishs=new Fish[maxFishsCount];
		allEvent=new AllEvent();
		moveEvent=new Move();
		feedEvent=new Feed();
		if(water==1)
			enviroment=new Enviroment(size,100,28,0,50,100,Water.FRESHWATER);
		else
			enviroment=new Enviroment(size,100,28,0,50,100,Water.OCEAN);
		timer=new Timerr();
		device=new DeviceCatalog();
		landSpace=new LandScape();
		cost=new Cost();
		this.landSpace.setTableSize(enviroment.getFishTankSize()+1);
		device.feeder[0].changeModel(true);
		device.filter[0].changeModel(true);
		device.inflator[0].changeModel(true);
		device.heater[0].changeModel(true);
		device.printAll();
		Event.setTime(timer);
		
		//將第1/1/1 00:00的時間放入資料庫中
		dbDate.insertTable(timer.toStringToDB());
	}
	
	
	
	public Aquarium(Aquarium a)
	{
		this.fishs=a.fishs;
		this.allEvent=a.allEvent;
		this.moveEvent=a.moveEvent;
		this.feedEvent=a.feedEvent;
		this.enviroment=new Enviroment(a.getEnviroment().getFishTankSize()
				,a.getEnviroment().getWaterQuality(),a.getEnviroment().getWaterTemperature()
				,a.getEnviroment().getStool(),50,a.getEnviroment().getOxygen(),a.getEnviroment().getWater());
		this.timer=a.timer;
		this.device=a.device;
		this.landSpace=a.landSpace;
		this.nFishs=a.nFishs;
		this.cost=a.cost;
		Event.setTime(timer);
		device.printAll();
	}
	
	public void aquariumReset(int size,int water)
	{
		nFishs=0;
		fishs=new Fish[maxFishsCount];
		allEvent=new AllEvent();
		moveEvent=new Move();
		feedEvent=new Feed();
		if(water==1)
			enviroment=new Enviroment(size,100,28,0,50,100,Water.FRESHWATER);
		else
			enviroment=new Enviroment(size,100,28,0,50,100,Water.OCEAN);
		timer=new Timerr();
		device=new DeviceCatalog();
		landSpace=new LandScape();
		cost=new Cost();
		this.landSpace.setTableSize(enviroment.getFishTankSize()+1);
		device.feeder[0].changeModel(true);
		device.filter[0].changeModel(true);
		device.inflator[0].changeModel(true);
		device.heater[0].changeModel(true);
		device.printAll();
		Event.setTime(timer);
		
		//將第1/1/1 00:00的時間放入資料庫中
		dbDate.insertTable(timer.toStringToDB());
	}
	public int getnFishs() {
		return nFishs;
	}

	public void setnFishs(int nFishs) {
		this.nFishs = nFishs;
	}

	public Fish[] getFishs() {
		return fishs;
	}

	public void setFishs(Fish[] fishs) {
		this.fishs = fishs;
	}

	public DeviceCatalog getDevice() {
		return device;
	}

	public void setDevice(DeviceCatalog device) {
		this.device = device;
	}

	public LandScape getLandSpace() {
		return landSpace;
	}

	public void setLandSpace(LandScape landSpace) {
		this.landSpace = landSpace;
	}

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}
	
	public Timerr getTimer() {
		return timer;
	}

	public void setTimer(Timerr timer) {
		this.timer = timer;
	}
	
	public Enviroment getEnviroment() {
		return enviroment;
	}

	public void setEnviroment(Enviroment enviroment) {
		this.enviroment = enviroment;
	}

	public void fishParameter()			//每秒鐘參數的調整，需再補充		移動、進食等等
	{
		int[] nEventTemp=new int[1];
		nEventTemp[0]=nEvent;
		//feed
		feedEvent.check(fishs, enviroment, timer, nFishs,landSpace,device, eventArray, eventArrayDescription, nEventTemp);
		//move
		 moveEvent.check(fishs, enviroment, timer, nFishs,landSpace,device, eventArray, eventArrayDescription, nEventTemp);
		 
		 nEvent=nEventTemp[0];
		// System.out.println(fishs[0].getNowPosition());
		 //System.out.println(fishs[0].getGoalPosition());
		 
	}
	
	public void addAFish(String fishName)
	{
		Fish temp;
		temp=Aquarium.fishCataLog.addFish(fishName);
		if(temp==null) {
			System.out.printf("add fail\n");
			return;
		}
		temp.naturalMove(enviroment.getFishTankXYZSize());
		fishs[nFishs]=temp;
		nFishs++;
		System.out.println(fishs[nFishs-1].toString());
	}
	
	//===================================改成全部清掉
	public ArrayList<Fish> removeAFish()
	{
		int i;
		ArrayList<Fish> removeFish=new ArrayList<Fish>();
		for(i=0;i<nFishs;i++)
		{
			if(fishs[i].getFishStatus()==FishStatus.DEATH)
			{
				nFishs--;
				removeFish.add(fishs[i]);
				for(int y=i;y<nFishs;y++)
				{
					fishs[y]=fishs[y+1];
				}
				i--;
			}
		}
		return removeFish;
	}
	
	public void fishDataToDB(Fish fish)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date beginDate=new Date();
		try {
			beginDate = sdf.parse("1/1/1");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dbFish.SetInsert(fish.getLifetime(), ((double)fish.getWeight()/10),fish.getSatiationRate(), fish.getFishStatus().toString(), fish.getFishHealthly().toString());
		System.out.println(fish.getFishNO() + "///" + FishCataLog.getFishChineseName(fish) + "///" + fish.getLively() + "///" + (int) (TimeUnit.MILLISECONDS.toHours(timer.getTimer().getTime().getTime()-beginDate.getTime()))+1);
	    dbFish.insertTable(fish.getFishNO(),FishCataLog.getFishChineseName(fish),fish.getLively(),(int) (TimeUnit.MILLISECONDS.toHours(timer.getTimer().getTime().getTime()-beginDate.getTime()))+1);
	}
	
	public void speedUP(int[] speedInformation)
	{
		/*
		 speedInformation第一格為要加速一禮拜、一天還是一小時
		 之後會須跳過的事件(1為要跳過)
		 */
		
		//歸零事件紀錄
		int stopEvent = -1;
		for(int i=0;i<nEvent;i++)
		{
			eventArray[i]=0;
			eventArrayDescription[i]="";
		}
		nEvent=0;
		
		
		
		int n=0;
		//選擇年月日來進行
		if(speedInformation[0]==1)
		{
			try {
				n=(int) timer.speedUp(speedUpTime.HOUR);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		else if(speedInformation[0]==2)
		{
			try {
				n=(int) timer.speedUp(speedUpTime.DAY);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		else if(speedInformation[0]==3)
		{
			try {
				n=(int) timer.speedUp(speedUpTime.WEEK);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
				
	
		dbFish=new Fish_data();
		dbDate=new Date_data();
		//關閉資料庫同步更新(先將資訊放入buffer在刷新網頁，可加快速度)
		try {
			dbFish.setClose();
		} catch (SQLException e) {
			System.out.println("Aquarium 366!");
		}
		for(int a=0;a<n;a++)
		{
			//System.out.println(timer.toString());
			timer.addOneHour();
			Event.setTime(timer);
			Boolean allGoalPosIsNull=false;
			while(!allGoalPosIsNull) {
				for(int i=0;i<nFishs;i++)
				{
					if(fishs[i].getGoalPosition()!=null) 
						fishs[i].setNowPosition(fishs[i].getGoalPosition());
					if(fishs[i].getMyMove()==Fish.FishMove.EATING)
						allGoalPosIsNull=true;
				}
				if(allGoalPosIsNull==true)
					allGoalPosIsNull=false;
				else
					allGoalPosIsNull=true;		
				fishParameter();
			}
			//將日期時間放入資料庫
			dbDate.insertTable(timer.toStringToDB());
			
			ArrayList<int[]> temp=enviroment.getFeedXY();
			//先清空之前的飼料(變成大便)
			ArrayList<int[]> stools=enviroment.getStoolXY();
			for(int[] feed:temp) {
				stools.add(feed);
				enviroment.setStool(enviroment.getStool()+1);
			}
			temp.clear();
			
			device.aTime(enviroment, landSpace);
			//確認設備是否有壞掉
			if(device.getHaveDamagedFeeder()!=0)
			{
				eventArray[nEvent]=6;
				eventArrayDescription[nEvent]="餵食器壞掉！！";
				nEvent++;
			}
			if(device.getHaveDamagedFilter()!=0)
			{
				eventArray[nEvent]=7;
				eventArrayDescription[nEvent]="過濾器壞掉！！";
				nEvent++;
			}
			if(device.getHaveDamagedInflator()!=0)
			{
				eventArray[nEvent]=8;
				eventArrayDescription[nEvent]="氧氣泵壞掉！！";
				nEvent++;
			}
			if(device.getHaveDamagedFlashLight()!=0)
			{
				eventArray[nEvent]=9;
				eventArrayDescription[nEvent]="照明器壞掉！！";
				nEvent++;
			}
			if(device.getHaveDamagedHeater()!=0)
			{
				eventArray[nEvent]=10;
				eventArrayDescription[nEvent]="加溫器壞掉！！";
				nEvent++;
			}
			int[] nEventTemp=new int[1];
			nEventTemp[0]=nEvent;
			
			
			feedEvent.check(fishs, enviroment, timer, nFishs,landSpace,device, eventArray, eventArrayDescription, nEventTemp);
			allEvent.allEventDealWith(fishs, enviroment, timer, nFishs,landSpace,device, eventArray, eventArrayDescription, nEventTemp);
			
			 nEvent=nEventTemp[0];
			 /*
			 for(int i=0;i<nEvent;i++)
				 System.out.printf(" %d", eventArray[i]);*/
			 
			 
			 //判斷是否有需跳過的事件發生
			 for(int eventSkip=1;eventSkip<20;eventSkip++)
			 {
				 if(speedInformation[eventSkip]==1) {
					 for(int i=0;i<nEvent;i++)
					 {
						 if(eventArray[i]==eventSkip) {
							 a=n;
							 stopEvent=i;
							 //回傳跳過的事件警語
						 }
					 }
				 }
			 }
		///////******************************************
			//輸出資料到資料庫
			
			for(int i=0;i<nFishs;i++)
			{
				fishDataToDB(fishs[i]);
			}
			//之後改成回圈輸入所有魚
			//fishDataToDB(fishs[0]);
			
			//
			/*
			System.out.println(enviroment.toString());
			for(int i=0;i<nFishs;i++)
			{
				System.out.println(fishs[i].toString());
				
			}*/
		}
		
		try {
			dbFish.setOpen();
		} catch (SQLException e) {
			System.out.println("Aquarium 482");
		}
		
		if(stopEvent!=-1)
		{
			JOptionPane.showMessageDialog(null,eventArrayDescription[stopEvent],
					eventString[eventArray[stopEvent]], JOptionPane.INFORMATION_MESSAGE);
		}
		//System.out.println(n);
		
	}
	
	
	public void run() {			//所有邏輯事件的判斷與調整
		
		fishParameter();
		if(timer.afterASecond())
		{
			//將日期時間放入資料庫
			dbDate.insertTable(timer.toStringToDB());
			
			//設備運作
			//每一小時要做的事
			ArrayList<int[]> temp=enviroment.getFeedXY();
			//先清空之前的飼料(變成大便)
			ArrayList<int[]> stools=enviroment.getStoolXY();
			for(int[] feed:temp) {
				stools.add(feed);
				enviroment.setStool(enviroment.getStool()+1);
			}
			temp.clear();
			
			device.aTime(enviroment, landSpace);
			
			int[] nEventTemp=new int[1];
			nEventTemp[0]=nEvent;
			
			feedEvent.check(fishs, enviroment, timer, nFishs,landSpace,device, eventArray, eventArrayDescription, nEventTemp);
			allEvent.allEventDealWith(fishs, enviroment, timer, nFishs,landSpace,device, eventArray, eventArrayDescription, nEventTemp);
			
			 nEvent=nEventTemp[0];
			///////******************************************
			
			
			
			//輸出資料到資料庫
			System.out.println("//////////////////////////////////");
			fishDataToDB(fishs[0]);
		}
		
		System.out.println(enviroment.toString());
		for(int i=0;i<nFishs;i++)
		{
			System.out.println(fishs[i].toString());
			//System.out.println(UserName.userName);
		}
	}
}
