package fish;

import java.security.SecureRandom;

import fish.fishClass.freshwaterFish.BettaSplendens;
import fish.fishClass.freshwaterFish.DarioRerio;
import fish.fishClass.freshwaterFish.MacropodusOpercularis;
import fish.fishClass.freshwaterFish.ParacheirodonInnesi;
import fish.fishClass.freshwaterFish.PoeciliaReticulata;
import fish.fishClass.oceanFish.CentropygeFerrugata;
import fish.fishClass.oceanFish.ChaetodonKleinii;
import fish.fishClass.oceanFish.OdunusNiger;
import fish.fishClass.oceanFish.ParacanthurusHepatus;
import fish.fishClass.oceanFish.PomacanthusAsfur;

public class FishCataLog {
	private static final SecureRandom randomNumbers = new SecureRandom();
	
	
	public enum EatType {MEAT,HERBIVOROUS,OMNIVORE};//魚吃的東西(肉食，草食，雜食)
	private static final int YEAR = 8760;//一年有幾小時，計算用
	private static final int FishTypeNumber = 10;
	String[] fishName=new String[FishTypeNumber];
	double[] lifeEnd=new double[FishTypeNumber];//壽命(年)
	double[] lifeEndRange=new double[FishTypeNumber];//壽命浮動(年)
	int[] initLively=new int[FishTypeNumber];//起始活潑度
	double[] maxLength=new double[FishTypeNumber];
	double[] maxLengthRange=new double[FishTypeNumber];//長度的浮動值
	int[] initFight=new int[FishTypeNumber];//起始打架度
	//初始長度為最大長度的1/3;
	//體重等於(身長*10)g
	//最大飽食度為(體重)
	//起始飽食度為滿
	EatType[] eating=new EatType[FishTypeNumber];
	int[] suitableTemperature=new int[FishTypeNumber];//適合生存溫度，用abc0def表示ab.c度～ed.f度
	
	
	String[] statement=new String[FishTypeNumber];	//簡介，之後再補
	
	
	public FishCataLog(){
		//海水魚
		//藍倒吊
		fishName[0]="藍倒吊";
		lifeEnd[0]=5;
		lifeEndRange[0]=2;
		initLively[0]=50;
		maxLength[0]=25;
		maxLengthRange[0]=3;
		eating[0]=EatType.OMNIVORE;
		suitableTemperature[0]=2400260;
		initFight[0]=20;
		
		//藍魔鬼砲彈
		fishName[1]="藍魔鬼砲彈";
		lifeEnd[1]=5;
		lifeEndRange[1]=2;
		initLively[1]=60;
		maxLength[1]=33;
		maxLengthRange[1]=4;
		eating[1]=EatType.MEAT;
		suitableTemperature[1]=2400270;
		initFight[1]=50;
		
		//紅閃電
		fishName[2]="紅閃電";
		lifeEnd[2]=3;
		lifeEndRange[2]=2;
		initLively[2]=60;
		maxLength[2]=7;
		maxLengthRange[2]=3;
		eating[2]=EatType.OMNIVORE;
		suitableTemperature[2]=2400270;
		initFight[2]=50;
		
		//藍頭蝶
		fishName[3]="藍頭蝶";
		lifeEnd[3]=10;
		lifeEndRange[3]=2;
		initLively[3]=50;
		maxLength[3]=10;
		maxLengthRange[3]=3;
		eating[3]=EatType.OMNIVORE;
		suitableTemperature[3]=2450255;
		initFight[3]=20;
		
		//阿拉伯神仙魚
		fishName[4]="阿拉伯神仙魚";
		lifeEnd[4]=10;
		lifeEndRange[4]=2;
		initLively[4]=50;
		maxLength[4]=39;
		maxLengthRange[4]=2;
		eating[4]=EatType.MEAT;
		suitableTemperature[4]=2550265;
		initFight[4]=10;
		
		
		//淡水魚
		//蓋斑鬥魚
		fishName[5]="蓋斑鬥魚";
		lifeEnd[5]=2;	//雌性2~3年, 雄性3~6年，因目前沒分性別故合在一起
		lifeEndRange[5]=4;
		initLively[5]=60;
		maxLength[5]=5;
		maxLengthRange[5]=1;
		eating[5]=EatType.OMNIVORE;
		suitableTemperature[5]=2000270;
		initFight[5]=60;
		
		//泰國鬥魚
		fishName[6]="泰國鬥魚";
		lifeEnd[6]=1.5;
		lifeEndRange[6]=0.5;
		initLively[6]=60;
		maxLength[6]=6;
		maxLengthRange[6]=1;
		eating[6]=EatType.OMNIVORE;
		suitableTemperature[6]=2200240;
		initFight[6]=50;
		
		//日光燈
		fishName[7]="日光燈";
		lifeEnd[7]=1;
		lifeEndRange[7]=1;
		initLively[7]=40;
		maxLength[7]=3;
		maxLengthRange[7]=1;
		eating[7]=EatType.OMNIVORE;
		suitableTemperature[7]=2000260;
		initFight[7]=10;
		
		//斑馬魚
		fishName[8]="斑馬魚";
		lifeEnd[8]=2;
		lifeEndRange[8]=1;
		initLively[8]=45;
		maxLength[8]=4;
		maxLengthRange[8]=2;
		eating[8]=EatType.OMNIVORE;
		suitableTemperature[8]=2500260;
		initFight[8]=10;
		
		//孔雀魚
		fishName[9]="孔雀魚";
		lifeEnd[9]=1;
		lifeEndRange[9]=1;
		initLively[9]=55;
		maxLength[9]=2.5;
		maxLengthRange[9]=1;
		eating[9]=EatType.OMNIVORE;
		suitableTemperature[9]=1800280;
		initFight[9]=10;
		
	}
	
	public Fish addFish(String fishName)
	{
		Fish temp;
		int weight;
		int lively;
		int lifeEnd;
		int maxWeight;
		int fight;
		switch (fishName)
		{
			case "藍倒吊":
				lifeEnd=(int)(this.lifeEnd[0]*YEAR)+randomNumbers.nextInt((int)(this.lifeEndRange[0]*10));
				maxWeight=(int)(this.maxLength[0]*10)+randomNumbers.nextInt((int)(this.maxLengthRange[0]*10));
				weight=maxWeight/3+randomNumbers.nextInt(5)-2;
				lively=this.initLively[0]+randomNumbers.nextInt(7)-3;
				fight=this.initFight[0]+randomNumbers.nextInt(7)-3;
				temp=new ParacanthurusHepatus(weight,lively,lifeEnd,maxWeight,fight);
				break;
				
			case "藍魔鬼砲彈":
				lifeEnd=(int)(this.lifeEnd[1]*YEAR)+randomNumbers.nextInt((int)(this.lifeEndRange[1]*10));
				maxWeight=(int)(this.maxLength[1]*10)+randomNumbers.nextInt((int)(this.maxLengthRange[1]*10));
				weight=maxWeight/3+randomNumbers.nextInt(5)-2;
				lively=this.initLively[1]+randomNumbers.nextInt(7)-3;
				fight=this.initFight[1]+randomNumbers.nextInt(7)-3;
				temp=new OdunusNiger(weight,lively,lifeEnd,maxWeight,fight);
				break;
				
			case "紅閃電":
				lifeEnd=(int)(this.lifeEnd[2]*YEAR)+randomNumbers.nextInt((int)(this.lifeEndRange[2]*10));
				maxWeight=(int)(this.maxLength[2]*10)+randomNumbers.nextInt((int)(this.maxLengthRange[2]*10));
				weight=maxWeight/3+randomNumbers.nextInt(5)-2;
				lively=this.initLively[2]+randomNumbers.nextInt(7)-3;
				fight=this.initFight[2]+randomNumbers.nextInt(7)-3;
				temp=new CentropygeFerrugata(weight,lively,lifeEnd,maxWeight,fight);
				break;
				
			case "藍頭蝶":
				lifeEnd=(int)(this.lifeEnd[3]*YEAR)+randomNumbers.nextInt((int)(this.lifeEndRange[3]*10));
				maxWeight=(int)(this.maxLength[3]*10)+randomNumbers.nextInt((int)(this.maxLengthRange[3]*10));
				weight=maxWeight/3+randomNumbers.nextInt(5)-2;
				lively=this.initLively[3]+randomNumbers.nextInt(7)-3;
				fight=this.initFight[3]+randomNumbers.nextInt(7)-3;
				temp=new ChaetodonKleinii(weight,lively,lifeEnd,maxWeight,fight);
				break;
				
			case "阿拉伯神仙魚":
				lifeEnd=(int)(this.lifeEnd[4]*YEAR)+randomNumbers.nextInt((int)(this.lifeEndRange[4]*10));
				maxWeight=(int)(this.maxLength[4]*10)+randomNumbers.nextInt((int)(this.maxLengthRange[4]*10));
				weight=maxWeight/3+randomNumbers.nextInt(5)-2;
				lively=this.initLively[4]+randomNumbers.nextInt(7)-3;
				fight=this.initFight[4]+randomNumbers.nextInt(7)-3;
				temp=new PomacanthusAsfur(weight,lively,lifeEnd,maxWeight,fight);
				break;
				
			case "蓋斑鬥魚":
				lifeEnd=(int)(this.lifeEnd[5]*YEAR)+randomNumbers.nextInt((int)(this.lifeEndRange[5]*10));
				maxWeight=(int)(this.maxLength[5]*10)+randomNumbers.nextInt((int)(this.maxLengthRange[5]*10));
				weight=maxWeight/3+randomNumbers.nextInt(5)-2;
				lively=this.initLively[5]+randomNumbers.nextInt(7)-3;
				fight=this.initFight[5]+randomNumbers.nextInt(7)-3;
				temp=new MacropodusOpercularis(weight,lively,lifeEnd,maxWeight,fight);
				break;
				
			case "泰國鬥魚":
				lifeEnd=(int)(this.lifeEnd[6]*YEAR)+randomNumbers.nextInt((int)(this.lifeEndRange[6]*10));
				maxWeight=(int)(this.maxLength[6]*10)+randomNumbers.nextInt((int)(this.maxLengthRange[6]*10));
				weight=maxWeight/3+randomNumbers.nextInt(5)-2;
				lively=this.initLively[6]+randomNumbers.nextInt(7)-3;
				fight=this.initFight[6]+randomNumbers.nextInt(7)-3;
				temp=new BettaSplendens(weight,lively,lifeEnd,maxWeight,fight);
				break;
				
			case "日光燈":
				lifeEnd=(int)(this.lifeEnd[7]*YEAR)+randomNumbers.nextInt((int)(this.lifeEndRange[7]*10));
				maxWeight=(int)(this.maxLength[7]*10)+randomNumbers.nextInt((int)(this.maxLengthRange[7]*10));
				weight=maxWeight/3+randomNumbers.nextInt(5)-2;
				lively=this.initLively[7]+randomNumbers.nextInt(7)-3;
				fight=this.initFight[7]+randomNumbers.nextInt(7)-3;
				temp=new ParacheirodonInnesi(weight,lively,lifeEnd,maxWeight,fight);
				break;
				
			case "斑馬魚":
				lifeEnd=(int)(this.lifeEnd[8]*YEAR)+randomNumbers.nextInt((int)(this.lifeEndRange[8]*10));
				maxWeight=(int)(this.maxLength[8]*10)+randomNumbers.nextInt((int)(this.maxLengthRange[8]*10));
				weight=maxWeight/3+randomNumbers.nextInt(5)-2;
				lively=this.initLively[8]+randomNumbers.nextInt(7)-3;
				fight=this.initFight[8]+randomNumbers.nextInt(7)-3;
				temp=new DarioRerio(weight,lively,lifeEnd,maxWeight,fight);
				break;
				
			case "孔雀魚":
				lifeEnd=(int)(this.lifeEnd[9]*YEAR)+randomNumbers.nextInt((int)(this.lifeEndRange[9]*10));
				maxWeight=(int)(this.maxLength[9]*10)+randomNumbers.nextInt((int)(this.maxLengthRange[9]*10));
				weight=maxWeight/3+randomNumbers.nextInt(5)-2;
				lively=this.initLively[9]+randomNumbers.nextInt(7)-3;
				fight=this.initFight[9]+randomNumbers.nextInt(7)-3;
				temp=new PoeciliaReticulata(weight,lively,lifeEnd,maxWeight,fight);
				break;
				
			default:
				temp=null;
		}
		return temp;
		
	}
	
	public static String getFishChineseName(Fish fish)
	{
		if(fish.getClass()==(new ParacanthurusHepatus(0,0,0,0,0).getClass()))
		{
			Fish.totalFish--;
			return "藍倒吊";
		}
		else if(fish.getClass()==(new OdunusNiger(0,0,0,0,0).getClass()))
		{
			Fish.totalFish-=2;
			return "藍魔鬼砲彈";
		}
		else if(fish.getClass()==(new CentropygeFerrugata(0,0,0,0,0).getClass()))
		{
			Fish.totalFish-=3;
			return "紅閃電";
		}
		else if(fish.getClass()==(new ChaetodonKleinii(0,0,0,0,0).getClass()))
		{
			Fish.totalFish-=4;
			return "藍頭蝶";
		}
		else if(fish.getClass()==(new PomacanthusAsfur(0,0,0,0,0).getClass()))
		{
			Fish.totalFish-=5;
			return "阿拉伯神仙魚";
		}
		else if(fish.getClass()==(new MacropodusOpercularis(0,0,0,0,0).getClass()))
		{
			Fish.totalFish-=6;
			return "蓋斑鬥魚";
		}
		else if(fish.getClass()==(new BettaSplendens(0,0,0,0,0).getClass()))
		{
			Fish.totalFish-=7;
			return "泰國鬥魚";
		}
		else if(fish.getClass()==(new ParacheirodonInnesi(0,0,0,0,0).getClass()))
		{
			Fish.totalFish-=8;
			return "日光燈";
		}
		else if(fish.getClass()==(new DarioRerio(0,0,0,0,0).getClass()))
		{
			Fish.totalFish-=9;
			return "斑馬魚";
		}
		else if(fish.getClass()==(new PoeciliaReticulata(0,0,0,0,0).getClass()))
		{
			Fish.totalFish-=10;
			return "孔雀魚";
		}
		
		return "zzz";
	}
}
