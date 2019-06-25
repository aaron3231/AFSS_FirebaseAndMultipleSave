package fish.fishClass.oceanFish;

import java.security.SecureRandom;

import fish.fishClass.OceanFish;

public class CentropygeFerrugata extends OceanFish {

	public CentropygeFerrugata(int weight, int lively, int lifeEnd, int maxWeight,int fight) {
		super(weight, lively, lifeEnd, maxWeight,fight);
		int []nowPosition=new int[3];
		nowPosition[0] =0;
		nowPosition[1] =20;
		nowPosition[2] =0;
		super.setNowPosition(nowPosition);
	}
	
	public int getSatiationRate()
	{
		return ((super.getSatiation()*100)/(super.getWeight()));
	}
	
	public void naturalMove(int[] fishTankXYZSize)
	{
		SecureRandom randomNumbers = new SecureRandom();
		int []goalPosition=new int[3];
		goalPosition[0]=randomNumbers.nextInt(fishTankXYZSize[0]);
		goalPosition[1]=(fishTankXYZSize[1]/3)+randomNumbers.nextInt((fishTankXYZSize[1]*2)/3);
		goalPosition[2]=randomNumbers.nextInt(fishTankXYZSize[2]);
		super.setGoalPosition(goalPosition);
	}
	
	
	@Override
	public String toString()
	{
		String str;
		str="	¬õ°{¹q " +super.toString();
		return str;
	}

}
