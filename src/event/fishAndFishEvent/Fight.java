package event.fishAndFishEvent;
import fish.Fish;
import fish.Fish.FishMove;
import fish.FishCataLog;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import event.Event;
import event.FishAndFishEvent;
import java.util.concurrent.TimeUnit;
public class Fight extends FishAndFishEvent{
	public static ArrayList<String> fightingFishs=new ArrayList<String>();	//�����ثe�����ǳ����b���[�A���A�����ܴN���|�C�J�P�_
	private static final SecureRandom randomNumbers = new SecureRandom();
	
	public static void clearFightFish(Fish[] fishs,int nFishs)	//�M�ť��[������
	{
		fightingFishs.clear();
		//�N���b���[�B�S�������ܦ^���`����
		for(int i=0;i<nFishs;i++)
		{
			if(fishs[i].getMyMove()==Fish.FishMove.FIGHTING)
				fishs[i].setMyMove(Fish.FishMove.NATURAL);
		}
	}
	
	
	@Override
	public void check(Fish a,Fish b,int[] eventArray,String[] eventArrayDescription,int nEvent[])
	{
		Boolean alreadyFighting=false;
		for(String fightingFish :fightingFishs)
		{
			if(a.getFishNO()==fightingFish || b.getFishNO()==fightingFish) {
				alreadyFighting=true;
				break;
			}
		}
		if(alreadyFighting==false)
		{
			double leanght=Math.sqrt(
					((a.getNowPosition()[0]-b.getNowPosition()[0])*(a.getNowPosition()[0]-b.getNowPosition()[0]))
					+((a.getNowPosition()[1]-b.getNowPosition()[1])*(a.getNowPosition()[1]-b.getNowPosition()[1]))
					+((a.getNowPosition()[2]-b.getNowPosition()[2])*(a.getNowPosition()[2]-b.getNowPosition()[2])));
			if(leanght<=5)		//����n�gmeet(�Ⱖ���O�_�b���񪺧P�_)
			{
				int N =randomNumbers.nextInt(100); //�����դ�K�]�w�{5�A����Ȭ�100
				if(N < a.getFight() && N < b.getFight()) {
					fightingFishs.add(a.getFishNO());
					fightingFishs.add(b.getFishNO());
					description(a,b, eventArray, eventArrayDescription, nEvent);
				}
				else
				{
					a.setNoFight(a.getNoFight()+1);
					b.setNoFight(b.getNoFight()+1);
					if(a.getNoFight()==4)
					{
						a.setFight(a.getFight()-1);
						a.setNoFight(0);
					}
					if(b.getNoFight()==4)
					{
						b.setFight(b.getFight()-1);
						b.setNoFight(0);
					}
					if(a.getFight()<10)
						a.setFight(10);
					if(b.getFight()<10)
						b.setFight(10);
				}
			}
		}
	}
	
	@Override
	protected void description(Fish a,Fish b,int[] eventArray,String[] eventArrayDescription,int nEvent[])
	{
		int ahurt,bhurt;	//�Ⱖ�������˭�
		ahurt=a.getHurt();
		bhurt=b.getHurt();
		int fightDamage;
		if(a.getFight()>b.getFight()) {
			fightDamage=a.getFight()-b.getFight();
		}
		else
		{
			fightDamage=b.getFight()-a.getFight();
		}
		String str;	//�ƥ�y�z
		if(a.getWeight()>b.getWeight())
		{
			bhurt +=fightDamage;
			ahurt +=fightDamage/2;
		}
		else if(a.getWeight()<b.getWeight())
		{
			bhurt +=fightDamage/2;
			ahurt +=fightDamage;
		}
		else
		{
			bhurt +=fightDamage;
			ahurt +=fightDamage;
		}
		//str=String.format("%s �M %s ���[�G%n%d    %d%n", a.getFishNO(), b.getFishNO(), a.getHurt(), b.getHurt());
		//System.out.println(str);
		
	
		
		a.setHurt(ahurt);
		b.setHurt(bhurt);
		str=String.format("%d    %d", a.getHurt(), b.getHurt());
		//System.out.println(str);
		
		
		a.setMyMove(FishMove.FIGHTING);
		b.setMyMove(FishMove.FIGHTING);
		a.setFightTarget(b);
		b.setFightTarget(a);
		a.setGoalPosition(null);
		b.setGoalPosition(null);
		a.setNoFight(0);
		b.setNoFight(0);
		
		
		//�N�ƥ�W�Ǧܸ�Ʈw
		String des;
		des=FishCataLog.getFishChineseName(a)+"("+a.getFishNO()+")"+"�M"+
				FishCataLog.getFishChineseName(b)+"("+b.getFishNO()+")" +
				"���_�ӤF!!!";
		
		//�����o�ͪ��ƥ�
		eventArray[nEvent[0]]=1;
		eventArrayDescription[nEvent[0]]=des;
		nEvent[0]++;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date beginDate=new Date();
		try {
			beginDate = sdf.parse("1/1/1");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Event.event.insertTable("���[",des,(int) (TimeUnit.MILLISECONDS.toHours(Event.timer.getTimer().getTime().getTime()-beginDate.getTime()))+1);
	}

}
