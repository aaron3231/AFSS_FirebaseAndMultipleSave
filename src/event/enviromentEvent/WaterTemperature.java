package event.enviromentEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import deviceCatalog.DeviceCatalog;
import enviroment.Enviroment;
import enviroment.Enviroment.Water;
import event.EnviromentEvent;
import event.Event;
import fish.Fish;
import landScape.LandScape;
import timer.Timerr;

public class WaterTemperature extends EnviromentEvent {

	@Override
	public void check(Fish[] fishs, Enviroment enviroment, Timerr timer, int nFishs,LandScape landSpace,DeviceCatalog device
			,int[] eventArray,String[] eventArrayDescription,int nEvent[]) {
		if(timer.getTimer().get(Calendar.HOUR_OF_DAY) %24 ==0 && enviroment.getWaterTemperature()>19)
		{
			enviroment.setWaterTemperature(enviroment.getWaterTemperature()-1);
		}
		else if(timer.getTimer().get(Calendar.HOUR_OF_DAY) %24 ==0 && enviroment.getWaterTemperature()>18)	//18<溫度<19
		{
			enviroment.setWaterTemperature(18);
		}
		
		if(enviroment.getWater()==Water.FRESHWATER)
		{
			//水溫太低魚死亡值增加
			if((enviroment.getWaterTemperature()>28 && enviroment.getWaterTemperature()<=33) ||
					(enviroment.getWaterTemperature()>=18 && enviroment.getWaterTemperature()<=22))
			{
				for(int i=0;i<nFishs;i++)
					fishs[i].setDeath(fishs[i].getDeath()+1);
			}
			else if(enviroment.getWaterTemperature()>33)
			{
				for(int i=0;i<nFishs;i++)
					fishs[i].setDeath(fishs[i].getDeath()+2);
			}
		}
		else if(enviroment.getWater()==Water.OCEAN)
		{
			//水溫太低魚死亡值增加
			if((enviroment.getWaterTemperature()>28 && enviroment.getWaterTemperature()<=32) ||
					(enviroment.getWaterTemperature()>=21 && enviroment.getWaterTemperature()<=24))
			{
				for(int i=0;i<nFishs;i++)
					fishs[i].setDeath(fishs[i].getDeath()+1);
			}
			else if(enviroment.getWaterTemperature()>32 || enviroment.getWaterTemperature()<=21)
			{
				for(int i=0;i<nFishs;i++)
					fishs[i].setDeath(fishs[i].getDeath()+2);
			}
		}
		description(fishs, enviroment, timer, nFishs, landSpace, device, eventArray, eventArrayDescription, nEvent);
	}

	@Override
	protected void description(Fish[] fishs, Enviroment enviroment, Timerr timer, int nFishs,LandScape landSpace,DeviceCatalog device
			,int[] eventArray,String[] eventArrayDescription,int nEvent[]) {
		
		//將事件上傳至資料庫
			String des="";
			if(enviroment.getWater()==Water.FRESHWATER)
			{
				if((enviroment.getWaterTemperature()>28 && enviroment.getWaterTemperature()<=33))
				{
					//水溫偏高
					des="魚兒們覺得有點熱喔";

					eventArray[nEvent[0]]=13;
					eventArrayDescription[nEvent[0]]=des;
					nEvent[0]++;
				}
				else if((enviroment.getWaterTemperature()>=18 && enviroment.getWaterTemperature()<=22))
				{
					//水溫偏低
					des="你的魚不太想動喔";

					eventArray[nEvent[0]]=15;
					eventArrayDescription[nEvent[0]]=des;
					nEvent[0]++;
				}
				else if(enviroment.getWaterTemperature()>33)
				{
					//水溫過高
					des="你的魚快煮熟瞜";

					eventArray[nEvent[0]]=14;
					eventArrayDescription[nEvent[0]]=des;
					nEvent[0]++;
				}
			}
			else if(enviroment.getWater()==Water.OCEAN)
			{
				if((enviroment.getWaterTemperature()>28 && enviroment.getWaterTemperature()<=32)) 
				{
					//水溫偏高
					des="魚兒們覺得有點熱喔";

					eventArray[nEvent[0]]=13;
					eventArrayDescription[nEvent[0]]=des;
					nEvent[0]++;
				}		
				else if(enviroment.getWaterTemperature()>=21 && enviroment.getWaterTemperature()<=24)
				{
					//水溫偏低
					des="你的魚不太想動喔";

					eventArray[nEvent[0]]=15;
					eventArrayDescription[nEvent[0]]=des;
					nEvent[0]++;
				}
				else if(enviroment.getWaterTemperature()>32)
				{
					//水溫過高
					des="你的魚快煮熟瞜";

					eventArray[nEvent[0]]=14;
					eventArrayDescription[nEvent[0]]=des;
					nEvent[0]++;
				}	
				else if(enviroment.getWaterTemperature()<=21)
				{
					//水溫過高
					des="魚都想穿外套囉";

					eventArray[nEvent[0]]=16;
					eventArrayDescription[nEvent[0]]=des;
					nEvent[0]++;
				}
			}

			if(des!="")
			{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				Date beginDate=new Date();
				try {
					beginDate = sdf.parse("1/1/1");
				} catch (ParseException e) {
					e.printStackTrace();
				}
				Event.event.insertTable("水溫",des,(int) (TimeUnit.MILLISECONDS.toHours(Event.timer.getTimer().getTime().getTime()-beginDate.getTime()))+1);
				}
	}
}
