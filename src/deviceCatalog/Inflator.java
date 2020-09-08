package deviceCatalog;

import enviroment.Enviroment;

public class Inflator extends Device {
	
	private static double EVERYTIME_PUMP = 5; //�C��������̨Τ�� +5
	
	private double bestProportion; //�̨Τ��
	
	//�غc��
	public Inflator(String name, int price, String statement) 
	{
		//�t�Φ۰ʳ]�w���۰�
		super(name, price, statement, AUTOMATIC);
	}
	
	//set function
	public void setBestProportion(int input) 
	{
		//�]�w�ȦA�d��
		if(input >= 0 && input <= 100) 
		{
			this.bestProportion = input;
		}
		else 
		{
			System.out.printf("���̨Τ�ҳ]�w���~!");
		}
	}
	
	//get function
	public double getBestProportion() 
	{
		return this.bestProportion;
	}
	
	//����
	public void pump(Enviroment environment) 
	{
		//�T�{�S�a
		if(this.getDamageRateNow() < 100)
		{
			//�N�w�q���q�]�J
			environment.setOxygen(environment.getOxygen()+EVERYTIME_PUMP);
		}
	}
	//override newOne function
	@Override
	public void newOne() 
	{
		this.setDamageRateNow(0);
		this.setModelState(false);
		this.setOperateModel(AUTOMATIC);
		this.setBestProportion(0);
	}
	
	@Override
	public String toString() 
	{
		String forward;
		forward = super.toString();
		String str;
		str =  String.format("�ثe�̨Τ�ҡG%.2f �C������q:%.2f %n", this.bestProportion, this.EVERYTIME_PUMP);
		return forward + str;
	}
}
