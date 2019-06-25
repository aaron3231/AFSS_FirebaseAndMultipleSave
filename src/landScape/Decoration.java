package landScape;

import java.security.SecureRandom;

public class Decoration {
	SecureRandom random = new SecureRandom();
	public static final double DAMAGE_RATE = 0.0005; //裝飾物損壞常數
	
	//岩石種類
	public static final int COBBLE = 1; //鵝卵石
	public static final int PRISM = 2; //稜角石 
	
	//裝飾物類別必須參數
	private String name; //裝飾物名稱
	private int price; //裝飾物價格
	private String statement; //裝飾物簡介
	private int sizex = 0; //物件的寬
	private int sizey = 0; //物件的長
	private double damageRateNow; //目前損壞率
	
	//建構元
	public Decoration() 
	{
		this.setName("???");
		this.setPrice(0);
		this.setStatement("無東西");
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
	
	//增加裝飾物損壞率(隨著時間推動呼叫)
	public void damageDecorationByTime() 
	{
		int p = random.nextInt(100); // p = 機率
		//自然爆炸裝飾物損壞 機率 1%
		if(p == 0) // 0 爆炸 1~98沒事
		{
			this.setDamageRateNow(100);
		}
		//增加損壞定植
		if(this.damageRateNow < 100)
			this.damageRateNow += this.DAMAGE_RATE;
	}
	

	
	//儲存
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
		str =  String.format("名稱：%s     價格：%d     裝飾物寬：%d     裝飾物長：%d    目前損壞率：%f %n", this.name, this.price, this.sizex, this.sizey, this.damageRateNow);
		return str;
	}
}
