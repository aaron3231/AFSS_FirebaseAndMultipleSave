package landScape;
import enviroment.Enviroment;

public class WaterPlant extends Decoration {
	
	public WaterPlant() 
	{
		//�j�p 1x1
		super();
		this.setName("����");
		this.setPrice(20);
		this.setStatement("�i�H����X�@��(�ηө����ө�)���ͮ��");
		this.setSizeX(1);
		this.setSizeY(1);
	}
	
	//�˹����u����������Ҧ��v�T
	public void environmentCheck(Enviroment environment) 
	{
		//�ˬd��������v�T
		if(environment.getWaterQuality() < 30) 
		{
			//����̮t �l�a +3
			super.damageDecorationByTime();
			super.damageDecorationByTime();
			super.damageDecorationByTime();
		} 
		else if (environment.getWaterQuality() < 50) 
		{
			//���� �ܮt �l�a +2
			super.damageDecorationByTime();
			super.damageDecorationByTime();
		}
		else if(environment.getWaterQuality() < 70) 
		{
			//���� �t �l�a +1
			super.damageDecorationByTime();
		}
		
		//�ˬd���Ź����v�T
		if ((31 < environment.getWaterTemperature() && environment.getWaterTemperature() <= 33) || ((23 < environment.getWaterTemperature() && environment.getWaterTemperature() <= 25))) 
		{
			super.damageDecorationByTime();
		}
		else if( 23 >= environment.getWaterTemperature() || environment.getWaterTemperature() > 33) 
		{
			super.damageDecorationByTime();
			super.damageDecorationByTime();
		} 
		
		//����v�T���q �C�Ӫ���|�I�s�@��
		environment.setOxygen(1);
	}
	
	//�Y���ө����b�ө� ��w����l�a�t��
	public void flashLightRepair() 
	{
		this.setDamageRateNow(this.getDamageRateNow() - super.DAMAGE_RATE);
	}
}
