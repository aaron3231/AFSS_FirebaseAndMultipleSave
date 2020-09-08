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

		//�N�ƥ�W�Ǧܸ�Ʈw
		String des;
		des=FishCataLog.getFishChineseName(a)+"("+a.getFishNO()+")"+
				"���������S\n"
				+"�i���]�G(���q�����B�}�D�S�����B����L�t...)";
		
		//�����ƥ�o��
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
		Event.event.insertTable("�D�۵M���`",des,(int) (TimeUnit.MILLISECONDS.toHours(Event.timer.getTimer().getTime().getTime()-beginDate.getTime()))+1);
	}

}
