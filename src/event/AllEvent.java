package event;

import deviceCatalog.DeviceCatalog;
import enviroment.Enviroment;
import event.enviromentEvent.Oxygen;
import event.enviromentEvent.Stool;
import event.enviromentEvent.WaterQuality;
import event.enviromentEvent.WaterTemperature;
import event.fishAndFishEvent.Fight;
import event.oneFishEvent.Death;
import event.oneFishEvent.Familiarity;
import event.oneFishEvent.Hurt;
import event.oneFishEvent.LifeTime;
import event.oneFishEvent.Lively;
import event.oneFishEvent.Satiation;
import event.oneFishEvent.Sick;
import event.oneFishEvent.Weight;
import fish.Fish;
import fish.Fish.FishStatus;
import landScape.LandScape;
import timer.Timerr;

public class AllEvent {
		//各類事件的事件數(要新增事件時記得修改這邊)
		static final private int oneFishEventCount=8;
		static final private int fishAndFishEventCount=1;
		static final private int environmentEventCount=4;
	
		private OneFishEvent[] oneFishEvent;
		private FishAndFishEvent[] fishAndFishEvent;
		private EnviromentEvent[] environmentEvent;
		
		
		private int[] eventArray;		//記錄此次模擬發生了哪些事件
		private String[] eventArrayDescription;	//發生事件的描述
		private int[] nEvent=new int[1];
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
		public AllEvent()
		{
			oneFishEvent=new OneFishEvent[oneFishEventCount];
			oneFishEvent[0]=new LifeTime();
			oneFishEvent[1]=new Familiarity();
			oneFishEvent[2]=new Weight();
			oneFishEvent[3]=new Lively();
			oneFishEvent[4]=new Sick();
			oneFishEvent[5]=new Satiation();
			oneFishEvent[6]=new Hurt();
			oneFishEvent[7]=new Death();
			
			fishAndFishEvent=new FishAndFishEvent[fishAndFishEventCount];
			fishAndFishEvent[0]=new Fight();
			
			environmentEvent=new EnviromentEvent[environmentEventCount];
			environmentEvent[0]=new Stool();
			environmentEvent[1]=new Oxygen();
			environmentEvent[2]=new WaterTemperature();
			environmentEvent[3]=new WaterQuality();
		}
		
		public void allEventDealWith(Fish[] fishs, Enviroment enviroment, Timerr timer, int nFishs,LandScape landSpace,DeviceCatalog device,
				int[] eventArray,String[] eventArrayDescription,int nEvent[])
		{
			this.eventArray=eventArray;
			this.eventArrayDescription=eventArrayDescription;
			this.nEvent=nEvent;
			
			System.out.println("gogo");	
			Fight.clearFightFish(fishs, nFishs);//清空前一時段在打架的魚
			oneFishEventDealwith(fishs,nFishs);
			fishAndFishEventDealwith(fishs,nFishs);
			environmentEventDealwith(fishs, enviroment, timer, nFishs,landSpace,device);
			for(int i=0;i<nFishs;i++)
				oneFishEvent[oneFishEventCount-1].check(fishs[i], this.eventArray, this.eventArrayDescription, this.nEvent);
		}
		
		public void oneFishEventDealwith(Fish[] fishs,int nFishs)
		{
			for(int i=0;i<nFishs;i++)
			{
				for(int n=0;n<oneFishEventCount;n++)
				{
					if(fishs[i].getFishStatus()!=FishStatus.DEATH)
						oneFishEvent[n].check(fishs[i], eventArray, eventArrayDescription, nEvent);
				}
				Familiarity.tokenAdd=false;
				Lively.livelyEditN=0;
				Satiation.satiationAddN=0;
				Weight.tokenAdd=false;
			}
		}
		
		private void fishAndFishEventDealwith(Fish[] fishs,int nFishs)
		{
			for(int n=0;n<fishAndFishEventCount;n++)
			{
				for(int i=0;i<nFishs-1;i++)
				{
					for(int y=i+1;y<nFishs;y++)
					{
						if(fishs[i].getFishStatus()!=FishStatus.DEATH && fishs[y].getFishStatus()!=FishStatus.DEATH)
							fishAndFishEvent[n].check(fishs[i], fishs[y], eventArray, eventArrayDescription, nEvent);
					}
				}
			}
		}
		
		private void environmentEventDealwith(Fish[] fishs, Enviroment enviroment, Timerr timer, int nFishs,LandScape landSpace,DeviceCatalog device)
		{
			for(int n=0;n<environmentEventCount;n++)
			{
				environmentEvent[n].check(fishs, enviroment, timer, nFishs,landSpace,device, eventArray, eventArrayDescription,  nEvent);
			}
		}
}
