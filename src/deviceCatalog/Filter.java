package deviceCatalog;

import enviroment.Enviroment;
import event.enviromentEvent.Stool;

public class Filter extends Device {
	
	//建構元
	public Filter(String name, int price, String statement, int operateModel) 
	{
		super(name, price, statement,operateModel);
	}
	
	//自動模式清除所有糞便
	public void filterAll(Enviroment environment)
	{
		//確認是自動模式
		if(getOperateModel() == AUTOMATIC)
		{
			if(this.getDamageRateNow() < 100) 
			{
				//清理所有大便
				environment.getStoolXY().clear();
				//增加水質
				environment.setWaterQuality(environment.getWaterQuality()+1);
				environment.setStool(0);
			}
		}
	}

	
	//手動模式清除單一糞便 section 1左邊 2中間 3右邊
	public void filterOne(Enviroment environment, int section) 
	{
		int i;
		if(getOperateModel() ==  MANUAL)
		{
			int[] fishTank = environment.getFishTankXYZSize();
			int point1, point2;
		
			point1 = fishTank[0]/3 * 1;
			point2 = fishTank[0]/3 * 2;
			switch(section) 
			{
				//左邊	
				case 1:
					for(int[] k:environment.getStoolXY()) 
					{
						if(k[0] <= point1+2) 
						{
							environment.getStoolXY().remove(k);
							//增加水質
							environment.setWaterQuality(environment.getWaterQuality()+1);
						}
					}
					break;
				//中間
				case 2:
					for(int[] k:environment.getStoolXY()) 
					{
						if(k[0] >= point1-2 && k[0] <= point2+2) 
						{
							environment.getStoolXY().remove(k);
							//增加水質
							environment.setWaterQuality(environment.getWaterQuality()+1);
						}
					}
					break;
				//右邊
				case 3:
					for(int[] k:environment.getStoolXY()) 
					{
						if(k[0] >= point2-2) 
						{
							environment.getStoolXY().remove(k);
							//增加水質
							environment.setWaterQuality(environment.getWaterQuality()+1);
						}
					}
					break;
				default:
					break;
			}
		}
	}
	
}
