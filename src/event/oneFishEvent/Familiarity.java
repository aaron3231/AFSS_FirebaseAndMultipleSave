package event.oneFishEvent;

import event.OneFishEvent;
import fish.Fish;

public class Familiarity extends OneFishEvent {
	public static Boolean tokenAdd=false;	//�p�G�o�Ӯɬq�w�g���ק�L���ܼơA�h�אּTrue�A�O�����ܼƪ�token���|�A++
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
