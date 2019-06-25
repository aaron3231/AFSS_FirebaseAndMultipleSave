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
	public static Boolean tokenAdd=false;	//�p�G�o�Ӯɬq�w�g���ק�L���ܼơA�h�אּTrue�A�O�����ܼƪ�token���|�A++
	
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
		//�N�ƥ�W�Ǧܸ�Ʈw
		String des;
		des="���ߧA���G"+FishCataLog.getFishChineseName(a)+"("+a.getFishNO()+")"+
				"����̤j�F!!";
		
		//�����ƥ�o��
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
		Event.event.insertTable("�����̤ܳj",des,(int) (TimeUnit.MILLISECONDS.toHours(Event.timer.getTimer().getTime().getTime()-beginDate.getTime()))+1);

	}


}
