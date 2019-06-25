package event.oneFishEvent;

import event.OneFishEvent;
import fish.Fish;

public class Lively extends OneFishEvent {
	public static int livelyEditN=0;	//這一輪判斷中lively上升多少
	@Override
	public void check(Fish a,int[] eventArray,String[] eventArrayDescription,int nEvent[]) {
		a.setFight(a.getFight()+Lively.livelyEditN);	//當lively上升時，此值也會跟著上升
		a.setSnatch(a.getSnatch()+Lively.livelyEditN);//當lively上升時，此值也會跟著上升
		
		
	}

	@Override
	protected void description(Fish a,int[] eventArray,String[] eventArrayDescription,int nEvent[]) {
		

	}

}
