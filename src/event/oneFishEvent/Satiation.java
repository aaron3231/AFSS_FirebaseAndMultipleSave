package event.oneFishEvent;

import event.OneFishEvent;
import fish.Fish;

public class Satiation extends OneFishEvent {
	public static int satiationAddN=0;	//這一輪判斷中satiation上升多少
	@Override
	public void check(Fish a,int[] eventArray,String[] eventArrayDescription,int nEvent[]) {
		a.setSatiation(a.getSatiation()-1);
		if(a.getSatiationRate()==100)
		{
			//吃飽了，有要顯示狀態再說
		}
		
		if(a.getSatiationRate()>100)
		{
			a.setDeath(a.getDeath()+1);
		}
		else if(a.getSatiationRate()>=200)
		{
			a.setDeath(100);
		}
		
		if(a.getSatiationRate()<=100 && a.getFeedArray().size()!=0)
		{
			if(a.getLively()<100)
				a.setLively(a.getLively()+Satiation.satiationAddN);
		}
		else if(a.getSatiationRate()>100)
		{
			a.setLively(a.getLively()-1);
		}
		
		if(a.getSatiationRate()>=94 && a.getWeight()<a.getMaxWeight())
		{
			a.setWeight(a.getWeight()+1);
			if(Weight.tokenAdd!=true)
			{
				a.setWeightAddToken(a.getWeightAddToken()+1);
				Weight.tokenAdd=true;
			}
			a.setMaxSatiation(a.getMaxSatiation()+1);
		}
		if(a.getSatiationRate()<0)
			a.setSatiation(0);
	}

	@Override
	protected void description(Fish a,int[] eventArray,String[] eventArrayDescription,int nEvent[]) {
		

	}

}
