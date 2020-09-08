package fish.fishClass.freshwaterFish;

import fish.fishClass.FreshwaterFish;

public class PoeciliaReticulata extends FreshwaterFish {

	public PoeciliaReticulata(int weight, int lively, int lifeEnd, int maxWeight,int fight) {
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
		str="	�ճ��� " +super.toString();
		return str;
	}
}
