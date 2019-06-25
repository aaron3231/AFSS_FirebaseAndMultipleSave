package deviceCatalog;

import enviroment.Enviroment;

//�[����
public class Heater extends Device
{
	private static final double HEATERRATE = 0.5; //�[���Ĳv(C)
	private static final double ENVIRONMENT_MAX_TEMPERATURE = 38; //���Ҥ��̤j�ū�
	//heater�ܼ�
	private double maxTemperature; //�[���ū׳̤j�W��
	
	//�غc��
	public Heater(String name, int price, String statement, int operateModel, double maxTemperature) 
	{
		super(name,price,statement, operateModel);
		this.setMaxTemperature(maxTemperature);
	}
	
	//set function
	public void setMaxTemperature(double maxTemperature) 
	{
		if(maxTemperature < ENVIRONMENT_MAX_TEMPERATURE)
		{
			System.out.printf("�[�ž��̤j�W���� %f �� �۰����z�]�w��%f�� %n",ENVIRONMENT_MAX_TEMPERATURE,ENVIRONMENT_MAX_TEMPERATURE);
			this.maxTemperature = maxTemperature;
		}
		else
			this.maxTemperature = ENVIRONMENT_MAX_TEMPERATURE;
	}
	
	//get function
	public double getMaxTemperature() 
	{
		return this.maxTemperature;
	}
	
	//�����Ҷi��[��
	public void heat(Enviroment environment) 
	{
		//�Y�[����p��̤j�W���A�h���[��
		if( (environment.getWaterTemperature() + HEATERRATE) < this.maxTemperature ) 
		{
			environment.setWaterTemperature(environment.getWaterTemperature()+HEATERRATE);
		}
	}
	//�x�s
	@Override
	public String saveData() 
	{
		String forwordStr = super.saveData();
		String str;
		str = String.format("maxTemperature:%f,", this.maxTemperature);
		return forwordStr + str;
	}
	
	@Override
	public String savetoData() 
	{
		String forwordStr = super.savetoData();
		String str;
		str = String.format("%f,", this.maxTemperature);
		return forwordStr + str;
	}
	
	@Override
	public String toString() 
	{
		String forward;
		forward = super.toString();
		String str;
		str =  String.format("�[���̤j�W���G%.2f    �[���t�v�G%.2f %n", this.maxTemperature, HEATERRATE);
		return forward + str;
	}
}
