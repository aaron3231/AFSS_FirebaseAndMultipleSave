package event.oneFishEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import event.Event;
import event.OneFishEvent;
import fish.Fish;
import fish.FishCataLog;
import fish.Fish.FishStatus;

public class LifeTime extends OneFishEvent {
	@Override
	public void check(Fish a,int[] eventArray,String[] eventArrayDescription,int nEvent[])
	{
		if(a.getLifetime()<a.getLifeEnd())
			a.setLifeTime(a.getLifetime()+1);
		else if(a.getLifetime()==a.getLifeEnd())
		{
			a.setFishStatus(FishStatus.DEATH);
			a.setDeath(100);
			description(a, eventArray, eventArrayDescription, nEvent);
		}
	}
	
	@Override
	protected void description(Fish a,int[] eventArray,String[] eventArrayDescription,int nEvent[])
	{
		//將事件上傳至資料庫
		String des;
		des=FishCataLog.getFishChineseName(a)+"("+a.getFishNO()+")"+
				"壽終正請了QQ";

		//紀錄事件發生
		eventArray[nEvent[0]]=2;
		eventArrayDescription[nEvent[0]]=des;
		nEvent[0]++;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date beginDate=new Date();
		try {
			beginDate = sdf.parse("1/1/1");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Event.event.insertTable("自然死亡",des,(int) (TimeUnit.MILLISECONDS.toHours(Event.timer.getTimer().getTime().getTime()-beginDate.getTime()))+1);
	}
}
