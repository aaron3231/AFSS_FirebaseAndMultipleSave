package event.enviromentEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import deviceCatalog.DeviceCatalog;
import enviroment.Enviroment;
import event.EnviromentEvent;
import event.Event;
import fish.Fish;
import fish.FishCataLog;
import fish.Fish.FishStatus;
import landScape.LandScape;
import timer.Timerr;

public class WaterQuality extends EnviromentEvent {

	@Override
	public void check(Fish[] fishs, Enviroment enviroment, Timerr timer,int nFishs,LandScape landSpace,DeviceCatalog device
			,int[] eventArray,String[] eventArrayDescription,int nEvent[]) {
		//計算死魚數量
		int nDeadFish=0;
		for(int i=0;i<nFishs;i++)
		{
			if(fishs[i].getFishStatus()==FishStatus.DEATH)
				nDeadFish++;
		}
		if(nDeadFish!=0)
		{
			enviroment.setWaterQuality(enviroment.getWaterQuality()-nDeadFish*0.1);
		}
		if(timer.getTimer().get(Calendar.HOUR_OF_DAY) %6 ==0)
		{
			enviroment.setWaterQuality(enviroment.getWaterQuality()-enviroment.getFishTankSize());
		}
		if(timer.getTimer().get(Calendar.HOUR_OF_DAY) %3 ==0 && enviroment.getStool()>0 && enviroment.getStool()<=20)
		{
			enviroment.setWaterQuality(enviroment.getWaterQuality()-0.5);
		}
		else if(timer.getTimer().get(Calendar.HOUR_OF_DAY) %3 ==0 && enviroment.getStool()>20 && enviroment.getStool()<=30)
		{
			enviroment.setWaterQuality(enviroment.getWaterQuality()-1);
		}
		else if(timer.getTimer().get(Calendar.HOUR_OF_DAY) %3 ==0 && enviroment.getStool()>30)
		{
			if(nFishs!=nDeadFish) {
				enviroment.setWaterQuality(enviroment.getWaterQuality()-((double)(enviroment.getStool())/((double)(nFishs-nDeadFish))));
			}
		}
		
		enviroment.setWaterQuality(enviroment.getWaterQuality()-nDeadFish*0.1);
		//水質太低魚死亡值增加
		if(enviroment.getWaterQuality()<=15)
		{
			for(int i=0;i<nFishs;i++) {
				if(fishs[i].getDeath()<100)
					fishs[i].setDeath(fishs[i].getDeath()+3);
			}
		}
		else if(enviroment.getWaterQuality()<=25)
		{
			for(int i=0;i<nFishs;i++) {
				if(fishs[i].getDeath()<100) {
					if(fishs[i].getDeath()<100)
						fishs[i].setDeath(fishs[i].getDeath()+2);
				}
			}
			
			description(fishs, enviroment, timer, nFishs, landSpace, device,  eventArray, eventArrayDescription, nEvent);

			//水質糟糕
		}
		else if(enviroment.getWaterQuality()<=50)
		{
			for(int i=0;i<nFishs;i++) {
				if(fishs[i].getDeath()<100)
					fishs[i].setDeath(fishs[i].getDeath()+1);
			}
			
			description(fishs, enviroment, timer, nFishs, landSpace, device, eventArray, eventArrayDescription, nEvent);

			//水質不良
		}
		/*
		//水質不良水草死亡值增加
		if(enviroment.getWaterQuality()<=30)
		{
			//死亡值+3
		}
		else if(enviroment.getWaterQuality()<=50)
		{
			//死亡值+2
		}
		else if(enviroment.getWaterQuality()<=70)
		{
			//死亡值+1
		}
		*/
		if(enviroment.getWaterQuality()<0)
			enviroment.setWaterQuality(0);
		if(enviroment.getWaterQuality()>100)
			enviroment.setWaterQuality(100);
	}

	@Override
	protected void description(Fish[] fishs, Enviroment enviroment, Timerr timer,int nFishs,LandScape landSpace,DeviceCatalog device
			,int[] eventArray,String[] eventArrayDescription,int nEvent[]) {
		//將事件上傳至資料庫
		String des="";
		if(enviroment.getWaterQuality()<=25)
		{
			des="魚缸飄出了壞味道瞜";
			//水質糟糕
			
			eventArray[nEvent[0]]=12;
			eventArrayDescription[nEvent[0]]=des;
			nEvent[0]++;
		}
		else if(enviroment.getWaterQuality()<=50)
		{
			des="魚缸裡的水質有點髒喔～";
			//水質不良
			
			eventArray[nEvent[0]]=11;
			eventArrayDescription[nEvent[0]]=des;
			nEvent[0]++;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date beginDate=new Date();
		try {
			beginDate = sdf.parse("1/1/1");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Event.event.insertTable("水質",des,(int) (TimeUnit.MILLISECONDS.toHours(Event.timer.getTimer().getTime().getTime()-beginDate.getTime()))+1);
	}
}
