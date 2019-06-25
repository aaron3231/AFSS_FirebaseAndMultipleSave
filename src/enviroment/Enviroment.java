package enviroment;

import java.util.ArrayList;


public class Enviroment {
	//�����O�|�̷Ӿ�J���Ѽƭק����ҰѼơA�è̻ݨD�^�ǻݭn���Ѽƭ�
	public static final int LARGE=3;
	public static final int MEDIUM=2;
	public static final int SMALL=1;
	
	public enum Water{OCEAN,FRESHWATER};
	
	private int fishTankSize;	//�����j�p
	private int[] fishTankXYZSize=new int[3];	//���������e��
	

	private double waterQuality;	//����
	private double waterTemperature;	//����
	private int stool;	//�T�K�q
	private ArrayList<int[]> stoolXY=new ArrayList<int[]>();
	private ArrayList<int[]> feedXY=new ArrayList<int[]>();
	private Water water;	//�����٬O�H��
	//private int light;	//���G��
	private double oxygen;	//�t��q
	
	public Enviroment(int fishTankSize,double waterQuality,double waterTemperature,int stool,int light,double oxygen,Water water)
	{
		this.fishTankSize=fishTankSize;
		if(fishTankSize==Enviroment.LARGE)
		{
			fishTankXYZSize[0]=120;	//X
			fishTankXYZSize[1]=45;	//Y
			fishTankXYZSize[2]=60;	//Z
		}
		else if(fishTankSize==Enviroment.MEDIUM)
		{
			fishTankXYZSize[0]=90;	//X
			fishTankXYZSize[1]=45;	//Y
			fishTankXYZSize[2]=60;	//Z
		}
		else if(fishTankSize==Enviroment.SMALL)
		{
			fishTankXYZSize[0]=60;	//X
			fishTankXYZSize[1]=30;	//Y
			fishTankXYZSize[2]=30;	//Z
		}
		this.waterQuality=waterQuality;
		this.waterTemperature=waterTemperature;
		this.stool=stool;
		//this.light=light;
		this.oxygen=oxygen;
		this.setWater(water);
	}
	
	public void setWaterQuality(double waterQuality)
	{
		this.waterQuality=waterQuality;
	}
	
	public void setWaterTemperature(double waterTemperature)
	{
		this.waterTemperature=waterTemperature;
	}
	
	public void setStool(int stool)
	{
		this.stool=stool;
	}
	/*
	public void setLight(int light)
	{
		this.light=light;
	}
	*/
	public void setStoolXY(ArrayList<int[]>  stoolXY)
	{
		this.stoolXY=stoolXY;
	}
	
	public void setFeedXY(ArrayList<int[]>  feedXY)
	{
		this.feedXY=feedXY;
	}
	
	
	public void setOxygen(double oxygen)
	{
		this.oxygen=oxygen;
	}
	
	public void setFishTankSize(int fishTankSize) {
		this.fishTankSize = fishTankSize;
		if(fishTankSize==Enviroment.LARGE)
		{
			fishTankXYZSize[0]=120;	//X
			fishTankXYZSize[1]=45;	//Y
			fishTankXYZSize[2]=60;	//Z
		}
		else if(fishTankSize==Enviroment.MEDIUM)
		{
			fishTankXYZSize[0]=90;	//X
			fishTankXYZSize[1]=45;	//Y
			fishTankXYZSize[2]=60;	//Z
		}
		else if(fishTankSize==Enviroment.SMALL)
		{
			fishTankXYZSize[0]=60;	//X
			fishTankXYZSize[1]=30;	//Y
			fishTankXYZSize[2]=30;	//Z
		}
	}
	
	
	public int getFishTankSize()
	{
		return fishTankSize;
	}
	
	public double getWaterQuality()
	{
		return waterQuality;
	}
	
	public double getWaterTemperature()
	{
		return waterTemperature;
	}
	
	public int getStool()
	{
		return stool;
	}
	/*
	public int getLight()
	{
		return light;
	}
	*/
	public double getOxygen()
	{
		return oxygen;
	}
	
	public ArrayList<int[]> getStoolXY()
	{
		return stoolXY;
	}
	
	public ArrayList<int[]> getFeedXY()
	{
		return feedXY;
	}
	
	public int[] getFishTankXYZSize()
	{
		return fishTankXYZSize;
	}
	
	public Water getWater() {
		return water;
	}

	public void setWater(Water water) {
		this.water = water;
	}

	@Override
	public String toString()
	{
		String str="";
		str=String.format("fishTankSize:%d waterQuality:%.2f waterTemperature:%.2f stool:%d oxygen:%.2f", fishTankSize, waterQuality, waterTemperature, stool,  oxygen);
		return str;
	}
	
	public String toSaveString()
	{
		String str="";
		str=String.format("%d,%.2f,%.2f,%d,%.2f", fishTankSize, waterQuality, waterTemperature, stool,  oxygen);
		return str;
	}
	
}
