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

public class Death extends OneFishEvent {

	@Override
	public void check(Fish a,int[] eventArray,String[] eventArrayDescription,int nEvent[]) {
		if(a.getDeath()>=100 &&a.getFishStatus()!=FishStatus.DEATH)
		{
			a.setFishStatus(FishStatus.DEATH);
			description(a, eventArray, eventArrayDescription, nEvent);
			System.out.println("=======4===");
		}
		else if(a.getDeath()>=80 && a.getDeath()<100)
		{
			a.setFishStatus(FishStatus.DYING);
		}
	}

	@Override
	protected void description(Fish a,int[] eventArray,String[] eventArrayDescription,int nEvent[]) {

		//將事件上傳至資料庫
		String des;
		des=FishCataLog.getFishChineseName(a)+"("+a.getFishNO()+")"+
				"那隻魚死惹\n"
				+"可能原因：(氧氣量不足、飼主沒餵食、水質過差...)";
		
		//紀錄事件發生
		eventArray[nEvent[0]]=3;
		eventArrayDescription[nEvent[0]]=des;
		nEvent[0]++;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date beginDate=new Date();
		try {
			beginDate = sdf.parse("1/1/1");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Event.event.insertTable("非自然死亡",des,(int) (TimeUnit.MILLISECONDS.toHours(Event.timer.getTimer().getTime().getTime()-beginDate.getTime()))+1);
	}

}
