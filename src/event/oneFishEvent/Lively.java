package event.oneFishEvent;

import event.OneFishEvent;
import fish.Fish;

public class Lively extends OneFishEvent {
	public static int livelyEditN=0;	//�o�@���P�_��lively�W�ɦh��
	@Override
	public void check(Fish a,int[] eventArray,String[] eventArrayDescription,int nEvent[]) {
		a.setFight(a.getFight()+Lively.livelyEditN);	//��lively�W�ɮɡA���Ȥ]�|��ۤW��
		a.setSnatch(a.getSnatch()+Lively.livelyEditN);//��lively�W�ɮɡA���Ȥ]�|��ۤW��
		
		
	}

	@Override
	protected void description(Fish a,int[] eventArray,String[] eventArrayDescription,int nEvent[]) {
		

	}

}
