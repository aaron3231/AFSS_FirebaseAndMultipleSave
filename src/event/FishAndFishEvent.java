package event;

import fish.Fish;

public abstract class FishAndFishEvent extends Event{
	public abstract void check(Fish a,Fish b,int[] eventArray,String[] eventArrayDescription,int nEvent[]);
	protected abstract void description(Fish a,Fish b,int[] eventArray,String[] eventArrayDescription,int nEvent[]);
}
