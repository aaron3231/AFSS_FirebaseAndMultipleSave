package event.oneFishEvent;

import event.OneFishEvent;
import fish.Fish;

public class Familiarity extends OneFishEvent {
	public static Boolean tokenAdd=false;	//如果這個時段已經有修改過此變數，則改為True，記錄此變數的token不會再++
	@Override
	public void check(Fish a,int[] eventArray,String[] eventArrayDescription,int nEvent[])
	{
		if(a.getFamiliarity()<100)
		{
			a.setFamiliarity(a.getFamiliarity()+1);
			if(Familiarity.tokenAdd!=true) {
				a.setFamiliarityAddToken(a.getFamiliarityAddToken()+1);
				Familiarity.tokenAdd=true;
			}
			
			if(a.getLively()<100)
				a.setLively(a.getLively()+1);
			if(a.getSnatch()<100)
				a.setSnatch(a.getSnatch()+1);
			
			if(a.getFamiliarityAddToken()==3)
			{
				a.setFight(a.getFight()+1);
			}
		}
		if(Familiarity.tokenAdd==false)
		{
			a.setFamiliarityAddToken(0);
		}
	}
	
	@Override
	protected void description(Fish a,int[] eventArray,String[] eventArrayDescription,int nEvent[])
	{
	}

}
