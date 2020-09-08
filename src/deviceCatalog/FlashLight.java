package deviceCatalog;

import landScape.Decoration;
import landScape.LandScape;
import landScape.WaterPlant;

public class FlashLight extends Device {

	private int lightHour = 0; //�Ѿl�ө��ɶ�
	
	//�غc��
	public FlashLight(String name, int price, String statement, int operateModel) 
	{
		super(name, price, statement, operateModel);
	}
	
	//set function
	public void setLightHour(int lighthour) 
	{
		//�۰ʼҦ��~��Set
		if(this.getOperateModel() == AUTOMATIC) 
		{
			this.lightHour = lighthour;
		}
	}
	
	
	//get function
	public int getLightHour() 
	{
		return this.lightHour;
	}
	
	//�H�ɶ��������� �Ѿl�ө��ɶ�
	public void lighthourDecrease() 
	{
		//call�@��function�������ɶ��@�p��(���ɶ�)
		if(lightHour > 0) 
		{
			this.lightHour -= 1;
		}
		//�Y�ɶ���h�����]�ư���
		if(lightHour <= 0) 
		{
			this.lightHour = 0;
			this.setModelState(false);
		}
	}
	
	//�ө����v�T����ͩR
	public void affectWaterPlant(LandScape landScape) 
	{
		int i,j;
		//�M��C�@��p�G�O����h���ʧ@
		for(i=0;i<landScape.getTable().length;i++) 
		{
			for(j=0;j<landScape.getTable()[0].length;j++) 
			{
				//check�p�Gtable�W�O����
				if(landScape.getTable()[i][j].getPrice()==20) 
				{
					//�^�_�@������ �l�a��
					landScape.getTable()[i][j].setDamageRateNow(landScape.getTable()[i][j].getDamageRateNow()-landScape.getTable()[i][j].DAMAGE_RATE);
				}
			}
		}
		
	}
	
	//override newOne function
	@Override
	public void newOne() 
	{
		this.setDamageRateNow(0);
		this.setModelState(false);
		this.setOperateModel(AUTOMATIC);
		this.setLightHour(0);
	}
	
	//�x�s
	@Override
	public String saveData() 
	{
		String forwordStr = super.saveData();
		String str;
		str = String.format("lightHour:%d,", this.lightHour);
		return forwordStr + str;
	}
	@Override
	public String savetoData() 
	{
		String forwordStr = super.savetoData();
		String str;
		str = String.format("%d,", this.lightHour);
		return forwordStr + str;
	}
	
	@Override
	public String toString() 
	{
		String forward;
		forward = super.toString();
		String str;
		str =  String.format("�Ѿl�ө��ɶ��G%d %n", this.lightHour);
		return forward + str;
	}
}
