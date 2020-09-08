package event;

import fish.Fish;

public abstract class OneFishEvent extends Event{
	public abstract void check(Fish a,int[] eventArray,String[] eventArrayDescription,int nEvent[]);
	protected abstract void description(Fish a,int[] eventArray,String[] eventArrayDescription,int nEvent[]);
}
