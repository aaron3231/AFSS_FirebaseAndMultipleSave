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

	//�s�J��Ʈw�ɥΪ��Ѽ�
	private Fish_data  dbFish=new Fish_data();
	private Date_data  dbDate=new Date_data();
	
	//��get.set===========================================
	private int nFishs=0;	//�����������ƶq
	private Timerr timer;


	private Fish[] fishs;
	private Enviroment enviroment;	//�Ѽƥ������w�]��50


	//�]�ƪ���
		//�]�ơB�\�]�Bcost
	private DeviceCatalog device;
	private LandScape landSpace;
	private Cost cost;
	//=======================================================
	private AllEvent allEvent;
	private Move moveEvent;
	private Feed feedEvent;
	//********�i���\�� ���N�t��
	
	//�ƥ󴣥ܥ\��
	private int[] eventArray =new int[800];		//�O�����������o�ͤF���Ǩƥ�
	private String[] eventArrayDescription=new String[800];	//�o�ͨƥ󪺴y�z
	private int nEvent=0;
	private String[] eventString = {"�����[", "�۵M���`", "�D�۵M���`", "���ͯf", "���ͪ��̤ܳj", "�������a��", "�L�o���a��", "����a��", "�ө����a��", "�[�ž��a��",
			 "���褣�}", "�����V�|", "�ūװ���", "�ū׹L��", "�ūװ��C", "�ū׹L�C", "�t��q���C", "�t��q�L�C", "�t��q���C",};
	/*
	 �ƥ����
	1. �����[   
	2. �۵M���`
	3. �D�۵M���` 
	4. ���ͯf   
	5. ���ͪ��̤ܳj
	6. �������a��  
	7. �L�o���a�� 
	8. ����a�� 
	9. �ө����a��  
	10. �[�ž��a�� 
	11.���褣�}  
	12.�����V�| 
	13.�ūװ���  
	14.�ū׹L��  
	15.�ūװ��C 
	16.�ū׹L�C  
	17.�t��q���C 
	18.�t��q�L�C
	19.�t��q���C
	 
	 */
	
	
	/*
	
	�e�ݩҦ����I�s���|�b�o���g�X������method
	
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

		//�N��1/1/1 00:00���ɶ���J��Ʈw��
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
		
		//�N��1/1/1 00:00���ɶ���J��Ʈw��
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
		
		//�N��1/1/1 00:00���ɶ���J��Ʈw��
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

	public void fishParameter()			//�C�����Ѽƪ��վ�A�ݦA�ɥR		���ʡB�i������
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
	
	//===================================�令�����M��
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
		 speedInformation�Ĥ@�欰�n�[�t�@§���B�@���٬O�@�p��
		 ����|�����L���ƥ�(1���n���L)
		 */
		
		//�k�s�ƥ����
		int stopEvent = -1;
		for(int i=0;i<nEvent;i++)
		{
			eventArray[i]=0;
			eventArrayDescription[i]="";
		}
		nEvent=0;
		
		
		
		int n=0;
		//��ܦ~���Ӷi��
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
		//������Ʈw�P�B��s(���N��T��Jbuffer�b��s�����A�i�[�ֳt��)
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
			//�N����ɶ���J��Ʈw
			dbDate.insertTable(timer.toStringToDB());
			
			ArrayList<int[]> temp=enviroment.getFeedXY();
			//���M�Ť��e���}��(�ܦ��j�K)
			ArrayList<int[]> stools=enviroment.getStoolXY();
			for(int[] feed:temp) {
				stools.add(feed);
				enviroment.setStool(enviroment.getStool()+1);
			}
			temp.clear();
			
			device.aTime(enviroment, landSpace);
			//�T�{�]�ƬO�_���a��
			if(device.getHaveDamagedFeeder()!=0)
			{
				eventArray[nEvent]=6;
				eventArrayDescription[nEvent]="�������a���I�I";
				nEvent++;
			}
			if(device.getHaveDamagedFilter()!=0)
			{
				eventArray[nEvent]=7;
				eventArrayDescription[nEvent]="�L�o���a���I�I";
				nEvent++;
			}
			if(device.getHaveDamagedInflator()!=0)
			{
				eventArray[nEvent]=8;
				eventArrayDescription[nEvent]="����a���I�I";
				nEvent++;
			}
			if(device.getHaveDamagedFlashLight()!=0)
			{
				eventArray[nEvent]=9;
				eventArrayDescription[nEvent]="�ө����a���I�I";
				nEvent++;
			}
			if(device.getHaveDamagedHeater()!=0)
			{
				eventArray[nEvent]=10;
				eventArrayDescription[nEvent]="�[�ž��a���I�I";
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
			 
			 
			 //�P�_�O�_���ݸ��L���ƥ�o��
			 for(int eventSkip=1;eventSkip<20;eventSkip++)
			 {
				 if(speedInformation[eventSkip]==1) {
					 for(int i=0;i<nEvent;i++)
					 {
						 if(eventArray[i]==eventSkip) {
							 a=n;
							 stopEvent=i;
							 //�^�Ǹ��L���ƥ�ĵ�y
						 }
					 }
				 }
			 }
		///////******************************************
			//��X��ƨ��Ʈw
			
			for(int i=0;i<nFishs;i++)
			{
				fishDataToDB(fishs[i]);
			}
			//����令�^���J�Ҧ���
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
	
	
	public void run() {			//�Ҧ��޿�ƥ󪺧P�_�P�վ�
		
		fishParameter();
		if(timer.afterASecond())
		{
			//�N����ɶ���J��Ʈw
			dbDate.insertTable(timer.toStringToDB());
			
			//�]�ƹB�@
			//�C�@�p�ɭn������
			ArrayList<int[]> temp=enviroment.getFeedXY();
			//���M�Ť��e���}��(�ܦ��j�K)
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
			
			
			
			//��X��ƨ��Ʈw
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
