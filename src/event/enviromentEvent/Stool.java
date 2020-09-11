package event.enviromentEvent;

import java.util.ArrayList;

import deviceCatalog.DeviceCatalog;
import enviroment.Enviroment;
import event.EnviromentEvent;
import fish.Fish;
import fish.Fish.FishStatus;
import landScape.LandScape;
import timer.Timerr;

public class Stool extends EnviromentEvent {
	
	//
	public void stoolDown(Enviroment enviroment)
	{
		ArrayList<int[]> temp=enviroment.getStoolXY();
		for(int[] XYZ: temp)
		{
			if(enviroment.getFishTankXYZSize()[1]>XYZ[1])
			{
				XYZ[1]+=10;
				if(enviroment.getFishTankXYZSize()[1]<XYZ[1])
					XYZ[1]=enviroment.getFishTankXYZSize()[1];
			}
		}
	}
	
	//
	@Override
	public void check(Fish[] fishs, Enviroment enviroment, Timerr timer, int nFishs,LandScape landSpace,DeviceCatalog device
			,int[] eventArray,String[] eventArrayDescription,int nEvent[]) {
		ArrayList<int[]> temp=enviroment.getStoolXY();
		for(int i=0;i<nFishs;i++)
		{
			if(fishs[i].getFishStatus()!=FishStatus.DEATH)
			{
				if(fishs[i].getLifetime()%24==0)
				{
					int [] stool=new int[3];
					stool[0]=fishs[i].getNowPosition()[0];	//X
					stool[1]=fishs[i].getNowPosition()[1];	//Y
					stool[2]=fishs[i].getNowPosition()[2];	//Z
					
					temp.add(stool);
					enviroment.setStool(enviroment.getStool()+1);
				}
			}
		}
		enviroment.setStoolXY(temp);
	}

	@Override
	protected void description(Fish[] fishs, Enviroment enviroment, Timerr timer, int nFishs,LandScape landSpace,DeviceCatalog device
			,int[] eventArray,String[] eventArrayDescription,int nEvent[]) {
		//????
	}
}
