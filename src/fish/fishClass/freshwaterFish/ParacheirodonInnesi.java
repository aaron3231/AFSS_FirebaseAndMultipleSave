package fish.fishClass.freshwaterFish;

import fish.fishClass.FreshwaterFish;

public class ParacheirodonInnesi extends FreshwaterFish {

	public ParacheirodonInnesi(int weight, int lively, int lifeEnd, int maxWeight,int fight) {
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
		str="	¤é¥ú¿O " +super.toString();
		return str;
	}
}
