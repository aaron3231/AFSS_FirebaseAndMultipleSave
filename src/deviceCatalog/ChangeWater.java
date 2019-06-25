package deviceCatalog;

import enviroment.Enviroment;

public class ChangeWater extends Device {
	
	//������ 1.�H��  2.����
	private static final int FRESHWATER = 1;
	private static final int SEAWATER = 2;
	
	private int waterCategory; //����
	
	public ChangeWater(String statement) 
	{
		super("����", 0, statement, AUTOMATIC);
	}
	
	//set function
	public void setWaterCategory(int waterCategory) 
	{
		switch(waterCategory)
		{
			case FRESHWATER:
			{
				this.waterCategory =  FRESHWATER;
				break;
			}
			case SEAWATER:
			{
				this.waterCategory = SEAWATER;
				break;
			}
			case NONE:
			{
				this.waterCategory = NONE;
				break;
			}
			default:
				System.out.printf("��������J���~!");
		}
	}
	
	//get function
	public int getWaterCategory() 
	{
		return this.waterCategory;
	}
	
	//���� => �ȼv�T���� quantity �ϥΪ̥[�J���q
	public void change(Enviroment environment, int waterCategory, double quantity) 
	{
		//�����q�p�� 1/4
		if(quantity <= 1/4) 
		{
			double temp = 0;
			temp = environment.getWaterQuality() * (100-quantity) + 100 * quantity;
			if(temp>100) 
			{
				temp = 100;
			}
			//�]�w�s������
			environment.setWaterQuality(temp);
		}
	}
	
	@Override
	public String toString() 
	{
		super.toString();
		String str;
		str =  String.format("���ءG%s %n", this.waterCategory == SEAWATER?"����":"�H��");
		return str;
	}
}	
