package fish.fishClass.freshwaterFish;

import fish.fishClass.FreshwaterFish;

public class MacropodusOpercularis extends FreshwaterFish {

	public MacropodusOpercularis(int weight, int lively, int lifeEnd, int maxWeight,int fight) {
		super(weight, lively, lifeEnd, maxWeight,fight);
	}
	
	public int getSatiationRate()
	{
		return ((super.getSatiation()*100)/(super.getWeight()));
	}
	
	@Override
	public String toString()
	{
		String str;
		str="	»\´³°«³½ " +super.toString();
		return str;
	}
}
