package deviceCatalog;

import java.security.SecureRandom;

public class Device {
	SecureRandom random = new SecureRandom();
	//�]�w���Ѯɥ�
	public static final int NONE = 0;
	//�ާ@�Ҧ����
	public static final int MANUAL = 1; //���
	public static final int AUTOMATIC = 2; //�۰�	
	private final double DAMAGE_RATE = 0.0005; //�]�Ưӷl�v
	//�]�����O�ܼ� => �C�ӳ]�Ƴ���
	private String name; //�]�ƦW��
	private int price; //�]�ƻ���
	private String statement; //�]��²��
	private boolean modelState = false; //�]�ƶ}�� true = �} , false = ��
	private double damageRateNow; //�]�ƥثe�l�a�v
	private int operateModel; //�Ҧ��Ѽ� 1.��� 2.�۰�
	private int countTime; 
	private int haveBuy;
	//��device�ܼƭ��s�]�w �R�F��ɨϥ�
	
	//�غc��
	public Device(String name, int price, String statement, int operateModel) 
	{
		this.setName(name);
		this.setPrice(price);
		this.setStatement(statement);
		this.setModelState(false); //�@�}�l�]�w������
		this.setDamageRateNow(0);
		this.setOperateModel(operateModel);
		this.setCountTime(0);
	}
	
	//set function
	public void setName(String name) 
	{
		this.name = name;
	}
	public void setPrice(int price) 
	{
		this.price = price;
	}
	public void setStatement(String statement) 
	{
		this.statement = statement;
	}
	public void setModelState(boolean input) 
	{
		this.modelState = input;
	}
	public void setDamageRateNow(double input) 
	{
		//�ӷl�v 100 �N�����ӷl �Y�j��100 �h�]��100
		if(this.damageRateNow >= 100)
			this.damageRateNow = 100;
		else if(this.damageRateNow < 0)
			this.damageRateNow = 0;
		//�_�h�令��J��
		else
			this.damageRateNow = input;
	}
	public void setOperateModel(int model)  //�]�w�ާ@�Ҧ�
	{
		switch(model) 
		{
			case MANUAL:
			{
				this.operateModel = MANUAL;
				break;
			}
			case AUTOMATIC:
			{
				this.operateModel = AUTOMATIC;
				break;
			}
			default:{
				System.out.printf("�]�Ƴ]�w�Ҧ����~!");
				this.operateModel = NONE;	
			}
		}
	}
	//�]�w�g�L�ɶ�
	public void setCountTime(int countTime) 
	{
		this.countTime = countTime;
	}
	
	public void setHaveBuy(int input) 
	{
		this.haveBuy = input;
	}
	//get function
	public String getName() 
	{
		return this.name;
	}
	public int getPrice() 
	{
		return this.price;
	}
	public String getStatement() 
	{
		return this.statement;
	}
	public boolean getModleState() 
	{
		return modelState;
	}
	public double getDamageRateNow() 
	{
		return this.damageRateNow;
	}
	public int getOperateModel() 
	{
		return this.operateModel;
	}
	public int getCountTime() 
	{
		return this.countTime;
	}
	public boolean getHaveBuy() 
	{
		if(this.haveBuy == 1)
			return true;
		else
			return false;
	}
	//�ܧ�}��  true�h���}�� / false �h������
	public void changeModel(boolean input) 
	{
		//�ˬd�Y�Q�}�ҳ]�ơA���O�]�Ƥw�ӷl�A���ĵ�i�T���A�åB���}��
		if(input == true && this.damageRateNow==100)
			System.out.printf("�]�Ưӷl�w�F100%! �Хt���K��!");
		//�Y�S���D�h�i�H�H�N���}��
		else
			this.modelState = input;
	}
	
	//�W�[�@���]�Ʒl�a�v(�H�ۮɶ�����) 
	public void damageDeviceByTime() 
	{
		int p = random.nextInt(10000); //p = ���v
		//�۵M�z���]�Ʒl�a ���v 1%
		if(p == 0) // 0���z�� 1~98�S��
		{
			this.setDamageRateNow(100);
		}
		//�W�[�l�a�w��
		if(this.damageRateNow < 100)
			this.damageRateNow += this.DAMAGE_RATE;
		//�Y�]�Ƨ����ӷl�h�����]��
		if(this.damageRateNow == 100)
		{
			System.out.println("�]�Ưӷl����!");
			changeModel(false);
		}
	}
	
	//�C���I�s�����W�[�@�ӳ��ɶ�
	public void countingTime() 
	{
		this.countTime++;
	}
	
	//�ʶR�s���~�ɭ��s�]�w
	public void newOne()
	{
		this.setDamageRateNow(0);
		this.setModelState(false);
		this.setOperateModel(AUTOMATIC);
	}
	//�x�s
	public String saveData() 
	{
		String str;
		str = String.format("modelState:%s,damageRateNow:%f,operateModel:%d,", this.modelState?"true":"false" , this.damageRateNow, this.operateModel);
		return str;
	}
	
	
	public String savetoData() 
	{
		String str;
		str = String.format("%d,%s,%f,%d,",this.countTime,this.modelState?"true":"false" , this.damageRateNow, this.operateModel);
		return str;
	}
	
	
	@Override
	public String toString() 
	{
		String str;
		str = String.format("�W��: %s     ����: %d     �}��: %s     �ާ@�Ҧ�:%s     �]�Ʒl�a�v:%f %n", this.name, this.price, this.modelState==true?"�}":"��", this.operateModel == AUTOMATIC?"�۰�":"���", this.damageRateNow);
		return str;
	}
	
}
