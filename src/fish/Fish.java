package fish;

import java.util.ArrayList;
import java.security.SecureRandom;

public abstract class Fish {
	private static final SecureRandom randomNumbers = new SecureRandom();
	
	public enum FishStatus {DEATH,DYING,ALIFE};//生存狀態(死亡、瀕死、存活)
	public enum FishHealthly {HEALTH, SICKNESS,HURT, BOTH};//健康狀況(健康，生病中，受傷中，生病又受傷中)
	public enum FishMove {NATURAL, FIGHTING, EATING};//移動情況(隨便動、打架中、要去吃飼料)
	public static int totalFish=0;	//放入魚缸的總魚數(含已死亡的)
	
	private String fishNO;												////
	private int lifeTime=0;	//生命週期(年齡)										////
	private final int lifeEnd;	//此魚的最大生命週期
	private int familiarity=0;//對環境熟悉度
	private final int maxWeight;	//此魚的最大體重(身長)
	private int maxSatiation;		//最大飽食度
	private int weight;		//體重												////
	private int lively;		//活潑度(起始預設50%)								////
	private int sick=0;		//生病值
	private int satiation;	//飽食度(起始預設100%)	最大飽食度=體重				////
	private int hurt=0;		//受傷度
	private int death=0;		//死亡度
	private int[] nowPosition=new int[3];
	private int[] goalPosition=new int[3];
	private ArrayList<int[]> feedArray=new ArrayList<int[]>();	//分配給此魚的飼料存放處(尚未被吃)
	
	private int fight;		//打架慾望
	
	private int snatch;	//搶食度
	
	
	
	//紀錄數值是否連續上升的token
	
	private int familiarityAddToken=0;	//familiarity值連續增加時+1，一沒有連續增加時歸零
	private int weightAddToken=0;	//weight值連續增加時+1，一沒有連續增加時歸零
	private int noFight=0;		//沒有發生打架多久?0為正在打
	private Fish fightTarget=null;	//打架目標
	private Boolean alreadyMaxWeight=false;
	
	//==================
	
	private FishStatus myStatus=FishStatus.ALIFE;					////
	private FishHealthly myHealthly=FishHealthly.HEALTH;			////
	private FishMove myMove=FishMove.NATURAL;	//移動狀態
	public Fish(int weight,int lively,int lifeEnd,int maxWeight,int fight)
	{
		
		Fish.totalFish++;
		fishNO=String.format("%05d", Fish.totalFish);
		this.lifeEnd=lifeEnd;
		this.maxWeight=maxWeight;
		this.weight=weight;
		this.maxSatiation=this.weight;
		this.lively=lively;
		this.snatch=this.lively;
		this.fight=fight;
		this.satiation=weight;
	}
	
	public int getLifetime()
	{
		return lifeTime;
	}
	public void setLifeTime(int lifeTime)
	{
		this.lifeTime=lifeTime;
	}
	
	
	public int getLifeEnd()
	{
		return lifeEnd;
	}
	
	public FishStatus getFishStatus()
	{
		return myStatus;
	}
	public void setFishStatus(FishStatus fishStatus)
	{
		myStatus=fishStatus;
	}
	
	public FishHealthly getFishHealthly()
	{
		return myHealthly;
	}
	public void setFishHealthly(FishHealthly fishHealthly)
	{
		myHealthly=fishHealthly;
	}
	
	
	public int getFamiliarity()
	{
		return familiarity;
	}
	public void setFamiliarity(int familiarity)
	{
		this.familiarity=familiarity;
	}
	
	
	public int getWeight()
	{
		return weight;
	}
	public void setWeight(int weight)
	{
		this.weight=weight;
	}
	
	public int getMaxWeight() {
		return maxWeight;
	}

	public int getLively()
	{
		return lively;
	}
	public void setLively(int lively)
	{
		this.lively=lively;
	}
	
	public int getSick()
	{
		return sick;
	}
	public void setSick(int sick)
	{
		this.sick=sick;
	}
	
	public int getSatiation()
	{
		return satiation;
	}
	public void setSatiation(int satiation)
	{
		this.satiation=satiation;
	}
	
	public int getHurt()
	{
		return hurt;
	}
	public void setHurt(int hurt)
	{
		this.hurt=hurt;
	}
	
	public int getDeath()
	{
		return death;
	}
	public void setDeath(int death)
	{
		this.death=death;
	}
	
	public int getFight()
	{
		return fight;
	}
	public void setFight(int fight)
	{
		this.fight=fight;
	}
	
	
	public int getSnatch()
	{
		return snatch;
	}
	public void setSnatch(int snatch)
	{
		this.snatch=snatch;
	}
	
	public int[] getNowPosition()
	{
		return nowPosition;
	}
	public void setNowPosition(int[] nowPosition)
	{
		this.nowPosition=nowPosition;
	}
	
	
	public int getMaxSatiation()
	{
		return maxSatiation;
	}
	public void setMaxSatiation(int maxSatiation)
	{
		this.maxSatiation=maxSatiation;
	}
	
	public FishMove getMyMove() {
		return myMove;
	}

	public void setMyMove(FishMove myMove) {
		this.myMove = myMove;
	}
	
	public int[] getGoalPosition() {
		return goalPosition;
	}

	public void setGoalPosition(int[] goalPosition) {
		this.goalPosition = goalPosition;
	}

	public ArrayList<int[]> getFeedArray() {
		return feedArray;
	}

	public void setFeedArray(ArrayList<int[]> feedArray) {
		this.feedArray = feedArray;
	}

	public String getFishNO()
	{
		return fishNO;
	}
	
	//token的get和set
	
	public void setFishNO(String fishNO) {
		this.fishNO = fishNO;
	}

	public int getFamiliarityAddToken()
	{
		return familiarityAddToken;
	}
	public void setFamiliarityAddToken(int familiarityAddToken)
	{
		this.familiarityAddToken=familiarityAddToken;
	}
	
	public int getWeightAddToken()
	{
		return weightAddToken;
	}
	public void setWeightAddToken(int weightAddToken)
	{
		this.weightAddToken=weightAddToken;
	}
	
	public int getNoFight()
	{
		return noFight;
	}
	public void setNoFight(int noFight)
	{
		this.noFight=noFight;
	}
	
	
	//========================
	
	
	

	public Fish getFightTarget() {
		return fightTarget;
	}

	public void setFightTarget(Fish fightTarget) {
		this.fightTarget = fightTarget;
	}

	public abstract int getSatiationRate();
	
	public void naturalMove(int[] fishTankXYZSize)
	{
		goalPosition=new int[3];
		goalPosition[0]=randomNumbers.nextInt(fishTankXYZSize[0]);
		goalPosition[1]=randomNumbers.nextInt(fishTankXYZSize[1]);
		goalPosition[2]=randomNumbers.nextInt(fishTankXYZSize[2]);
	}
	
	@Override
	public String toString()
	{
		String str;
		str=String.format("NO.%s lifeTime:%d weight:%d lively:%d sick:%d stiation:%d hurt:%d death:%d fight:%d%n", fishNO, lifeTime, weight, lively, sick, this.getSatiationRate(), hurt, death,fight);
		return str;
	}
	
	//@Override
		public String toSaveString()
		{
			String str;
			str=String.format("%s,%d,%d,%d,%d,%d,%d,%d,%d", fishNO, lifeTime, weight, lively, sick, this.getSatiation(), hurt, death,fight);
			return str;
		}

	public Boolean getAlreadyMaxWeight() {
		return alreadyMaxWeight;
	}

	public void setAlreadyMaxWeight(Boolean alreadyMaxWeight) {
		this.alreadyMaxWeight = alreadyMaxWeight;
	}
	
}
