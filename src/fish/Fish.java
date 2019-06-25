package fish;

import java.util.ArrayList;
import java.security.SecureRandom;

public abstract class Fish {
	private static final SecureRandom randomNumbers = new SecureRandom();
	
	public enum FishStatus {DEATH,DYING,ALIFE};//�ͦs���A(���`�B�x���B�s��)
	public enum FishHealthly {HEALTH, SICKNESS,HURT, BOTH};//���d���p(���d�A�ͯf���A���ˤ��A�ͯf�S���ˤ�)
	public enum FishMove {NATURAL, FIGHTING, EATING};//���ʱ��p(�H�K�ʡB���[���B�n�h�Y�}��)
	public static int totalFish=0;	//��J�������`����(�t�w���`��)
	
	private String fishNO;												////
	private int lifeTime=0;	//�ͩR�g��(�~��)										////
	private final int lifeEnd;	//�������̤j�ͩR�g��
	private int familiarity=0;//�����Ҽ��x��
	private final int maxWeight;	//�������̤j�魫(����)
	private int maxSatiation;		//�̤j������
	private int weight;		//�魫												////
	private int lively;		//�����(�_�l�w�]50%)								////
	private int sick=0;		//�ͯf��
	private int satiation;	//������(�_�l�w�]100%)	�̤j������=�魫				////
	private int hurt=0;		//���˫�
	private int death=0;		//���`��
	private int[] nowPosition=new int[3];
	private int[] goalPosition=new int[3];
	private ArrayList<int[]> feedArray=new ArrayList<int[]>();	//���t���������}�Ʀs��B(�|���Q�Y)
	
	private int fight;		//���[����
	
	private int snatch;	//�m����
	
	
	
	//�����ƭȬO�_�s��W�ɪ�token
	
	private int familiarityAddToken=0;	//familiarity�ȳs��W�[��+1�A�@�S���s��W�[���k�s
	private int weightAddToken=0;	//weight�ȳs��W�[��+1�A�@�S���s��W�[���k�s
	private int noFight=0;		//�S���o�ͥ��[�h�[?0�����b��
	private Fish fightTarget=null;	//���[�ؼ�
	private Boolean alreadyMaxWeight=false;
	
	//==================
	
	private FishStatus myStatus=FishStatus.ALIFE;					////
	private FishHealthly myHealthly=FishHealthly.HEALTH;			////
	private FishMove myMove=FishMove.NATURAL;	//���ʪ��A
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
	
	//token��get�Mset
	
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
