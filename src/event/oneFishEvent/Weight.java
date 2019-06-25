package event.oneFishEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import event.Event;
import event.OneFishEvent;
import fish.Fish;
import fish.FishCataLog;

public class Weight extends OneFishEvent {
	public static Boolean tokenAdd=false;	//如果這個時段已經有修改過此變數，則改為True，記錄此變數的token不會再++
	
	@Override
	public void check(Fish a,int[] eventArray,String[] eventArrayDescription,int nEvent[]) 
	{
		if(a.getWeightAddToken()==2)
		{
			a.setSnatch(a.getSnatch()+1);
		}
		if(a.getWeightAddToken()==3)
		{
			a.setFight(a.getFight()+1);
			a.setWeightAddToken(0);
		}

		if(Weight.tokenAdd==false)
		{
			a.setWeightAddToken(0);
		}
		if(a.getWeight()>=a.getMaxWeight() && a.getAlreadyMaxWeight()==false)
		{
			a.setAlreadyMaxWeight(true);
			description(a, eventArray, eventArrayDescription, nEvent);
		}
	}

	@Override
	protected void description(Fish a,int[] eventArray,String[] eventArrayDescription,int nEvent[])
	{
		//將事件上傳至資料庫
		String des;
		des="恭喜你的："+FishCataLog.getFishChineseName(a)+"("+a.getFishNO()+")"+
				"長到最大了!!";
		
		//紀錄事件發生
		eventArray[nEvent[0]]=5;
		eventArrayDescription[nEvent[0]]=des;
		nEvent[0]++;
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date beginDate=new Date();
		try {
			beginDate = sdf.parse("1/1/1");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Event.event.insertTable("魚長至最大",des,(int) (TimeUnit.MILLISECONDS.toHours(Event.timer.getTimer().getTime().getTime()-beginDate.getTime()))+1);

	}


}
