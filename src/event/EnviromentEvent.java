package event;

import deviceCatalog.DeviceCatalog;
import enviroment.Enviroment;
import fish.Fish;
import landScape.LandScape;
import timer.Timerr;

public abstract class EnviromentEvent {
	public abstract void check(Fish[] fishs,Enviroment enviroment,Timerr timer,int nFishs,LandScape landSpace,DeviceCatalog device
			,int[] eventArray,String[] eventArrayDescription,int[] nEvent);
	protected abstract void description(Fish[] fishs,Enviroment enviroment,Timerr timer,int nFishs,LandScape landSpace,DeviceCatalog device
			,int[] eventArray,String[] eventArrayDescription,int[] nEvent);
}
