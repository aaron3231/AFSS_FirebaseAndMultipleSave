package deviceCatalog;

import enviroment.Enviroment;
import event.enviromentEvent.Stool;

public class Filter extends Device {
	
	//�غc��
	public Filter(String name, int price, String statement, int operateModel) 
	{
		super(name, price, statement,operateModel);
	}
	
	//�۰ʼҦ��M���Ҧ��T�K
	public void filterAll(Enviroment environment)
	{
		//�T�{�O�۰ʼҦ�
		if(getOperateModel() == AUTOMATIC)
		{
			if(this.getDamageRateNow() < 100) 
			{
				//�M�z�Ҧ��j�K
				environment.getStoolXY().clear();
				//�W�[����
				environment.setWaterQuality(environment.getWaterQuality()+1);
				environment.setStool(0);
			}
		}
	}

	
	//��ʼҦ��M����@�T�K section 1���� 2���� 3�k��
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
				//����	
				case 1:
					for(int[] k:environment.getStoolXY()) 
					{
						if(k[0] <= point1+2) 
						{
							environment.getStoolXY().remove(k);
							//�W�[����
							environment.setWaterQuality(environment.getWaterQuality()+1);
						}
					}
					break;
				//����
				case 2:
					for(int[] k:environment.getStoolXY()) 
					{
						if(k[0] >= point1-2 && k[0] <= point2+2) 
						{
							environment.getStoolXY().remove(k);
							//�W�[����
							environment.setWaterQuality(environment.getWaterQuality()+1);
						}
					}
					break;
				//�k��
				case 3:
					for(int[] k:environment.getStoolXY()) 
					{
						if(k[0] >= point2-2) 
						{
							environment.getStoolXY().remove(k);
							//�W�[����
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
