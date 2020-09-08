package event.oneFishEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import event.Event;
import event.OneFishEvent;
import fish.Fish;
import fish.FishCataLog;
import fish.Fish.FishHealthly;

public class Sick extends OneFishEvent {
	@Override
	public void check(Fish a,int[] eventArray,String[] eventArrayDescription,int nEvent[]) {
		
		if(a.getSick()>=25)
		{
			a.setLively(a.getLively()-1);
			 Lively.livelyEditN++;
		}
		
		if(a.getSick()>=30)
		{
			a.setMaxSatiation(a.getMaxSatiation()-1);
		}
		else if(a.getSick()<30 && a.getWeight()>a.getMaxSatiation())	//生病值不嚴重時慢慢回復飽食度
		{
			a.setMaxSatiation(a.getMaxSatiation()+1);
		}
		
		if(a.getSick()>=45)
		{
			a.setFight(a.getFight()-1);
		}
		
		if(a.getSick()>=60)
		{
			a.setDeath(a.getDeath()+1);
			if(a.getFishHealthly()==FishHealthly.HEALTH)
			{
				a.setFishHealthly(FishHealthly.SICKNESS);
				description(a, eventArray, eventArrayDescription, nEvent);
			}
			else if(a.getFishHealthly()==FishHealthly.HURT)
			{
				a.setFishHealthly(FishHealthly.BOTH);
				description(a, eventArray, eventArrayDescription, nEvent);
			}
		}
		else
		{
			if(a.getFishHealthly()==FishHealthly.SICKNESS)
			{
				a.setFishHealthly(FishHealthly.HEALTH);
			}
			else if(a.getFishHealthly()==FishHealthly.BOTH)
			{
				a.setFishHealthly(FishHealthly.HURT);
			}
		}
	}

	@Override
	protected void description(Fish a,int[] eventArray,String[] eventArrayDescription,int nEvent[]) {
		//將事件上傳至資料庫
		String des;
		des=FishCataLog.getFishChineseName(a)+"("+a.getFishNO()+")"+
				"生病惹%n請飼主多用點心照顧魚<3";
		
		//紀錄事件發生
		eventArray[nEvent[0]]=4;
		eventArrayDescription[nEvent[0]]=des;
		nEvent[0]++;	
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date beginDate=new Date();
		try {
			beginDate = sdf.parse("1/1/1");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Event.event.insertTable("生病",des,(int) (TimeUnit.MILLISECONDS.toHours(Event.timer.getTimer().getTime().getTime()-beginDate.getTime()))+1);

	}

}
