package event.oneFishEvent;

import event.OneFishEvent;
import fish.Fish;
import fish.Fish.FishHealthly;

public class Hurt extends OneFishEvent {
	
	
	@Override
	public void check(Fish a,int[] eventArray,String[] eventArrayDescription,int nEvent[]) {
		if(a.getNoFight()>0 && a.getHurt()>=1)	//¨S¦³¥´¬[
		{
			a.setHurt(a.getHurt()-1);
		}
			
		if (a.getHurt()/a.getWeight() >= 60)
		{
			a.setDeath(a.getDeath()+1);
		}
		if (a.getHurt()/a.getWeight() >= 50)
		{
			a.setSick(a.getSick()+1);
		}
		
		if(a.getHurt()/a.getWeight()>=50)
		{
			if(a.getFishHealthly()==FishHealthly.HEALTH)
			{
				a.setFishHealthly(FishHealthly.HURT);
			}
			else if(a.getFishHealthly()==FishHealthly.SICKNESS)
			{
				a.setFishHealthly(FishHealthly.BOTH);
			}
		}
		else
		{
			if(a.getFishHealthly()==FishHealthly.HURT)
			{
				a.setFishHealthly(FishHealthly.HEALTH);
			}
			else if(a.getFishHealthly()==FishHealthly.BOTH)
			{
				a.setFishHealthly(FishHealthly.SICKNESS);
			}
		}
	}

	@Override
	protected void description(Fish a,int[] eventArray,String[] eventArrayDescription,int nEvent[]) {

	}

}
