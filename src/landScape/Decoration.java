package landScape;

import java.security.SecureRandom;

public class Decoration {
	SecureRandom random = new SecureRandom();
	public static final double DAMAGE_RATE = 0.0005; //�˹����l�a�`��
	
	//���ۺ���
	public static final int COBBLE = 1; //�Z�Z��
	public static final int PRISM = 2; //�W���� 
	
	//�˹������O�����Ѽ�
	private String name; //�˹����W��
	private int price; //�˹�������
	private String statement; //�˹���²��
	private int sizex = 0; //���󪺼e
	private int sizey = 0; //���󪺪�
	private double damageRateNow; //�ثe�l�a�v
	
	//�غc��
	public Decoration() 
	{
		this.setName("???");
		this.setPrice(0);
		this.setStatement("�L�F��");
		this.setSizeX(0);
		this.setSizeY(0);
		this.setDamageRateNow(0);
	}
	
	// set function
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
	
	public void setSizeX(int sizex) 
	{
		this.sizex = sizex;
	}
	
	public void setSizeY(int sizey) 
	{
		this.sizey = sizey;
	}
	
	public void setDamageRateNow(double damageRate) 
	{
		this.damageRateNow = damageRate;
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
	
	public int getSizeX() 
	{
		return this.sizex;
	}
	
	public int getSizeY() 
	{
		return this.sizey;
	}
	
	public double getDamageRateNow() 
	{
		return this.damageRateNow;
	}
	
	//�W�[�˹����l�a�v(�H�ۮɶ����ʩI�s)
	public void damageDecorationByTime() 
	{
		int p = random.nextInt(100); // p = ���v
		//�۵M�z���˹����l�a ���v 1%
		if(p == 0) // 0 �z�� 1~98�S��
		{
			this.setDamageRateNow(100);
		}
		//�W�[�l�a�w��
		if(this.damageRateNow < 100)
			this.damageRateNow += this.DAMAGE_RATE;
	}
	

	
	//�x�s
	public String savaData() 
	{
		String str;
		str = String.format("%s,%d,%s,%d,%d,%f", this.name, this.price, this.statement, this.sizex, this.sizey, this.damageRateNow);
		return str;
	}
	
	@Override
	public String toString() 
	{
		String str;
		str =  String.format("�W�١G%s     ����G%d     �˹����e�G%d     �˹������G%d    �ثe�l�a�v�G%f %n", this.name, this.price, this.sizex, this.sizey, this.damageRateNow);
		return str;
	}
}
