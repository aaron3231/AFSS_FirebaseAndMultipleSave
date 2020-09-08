package event.enviromentEvent;

import java.util.ArrayList;

import deviceCatalog.DeviceCatalog;

import java.security.SecureRandom;

import enviroment.Enviroment;
import event.EnviromentEvent;
import fish.Fish;
import fish.Fish.FishMove;
import fish.Fish.FishStatus;
import landScape.LandScape;
import timer.Timerr;

public class Feed extends EnviromentEvent {
	private static final SecureRandom randomNumbers = new SecureRandom();
	@Override
	public void check(Fish[] fishs, Enviroment enviroment, Timerr timer, int nFishs,LandScape landSpace,DeviceCatalog device
			,int[] eventArray,String[] eventArrayDescription,int nEvent[]) {
		ArrayList<int[]> temp=enviroment.getFeedXY();
		ArrayList<int []> alreadyFeed=new ArrayList<int []>();

		int nFeeds=temp.size();
		/*
		//�����������X���}�ƶq
		int nFeeds=1;	//�Ȯɳ]�w�A����|������������
		for(int i=0;i<nFeeds;i++)
		{
			int [] feed=new int[3];
			feed[0]=0+randomNumbers.nextInt(6);
			feed[1]=0+randomNumbers.nextInt(2);
			feed[2]=27+randomNumbers.nextInt(7);
			
			temp.add(feed);
		}
		*/
		ArrayList<Fish> aliveFish=new ArrayList<Fish>();		//�s���B�����רS��100%����
		ArrayList<Fish> satietyFish=new ArrayList<Fish>();		//�s���B�����׶W�L100%����
		for(int i=0;i<nFishs;i++)
		{
			if(fishs[i].getFishStatus()!=FishStatus.DEATH)
			{
				if(fishs[i].getSatiationRate()<100)
					aliveFish.add(fishs[i]);
				else
					satietyFish.add(fishs[i]);
			}
		}
		for(int i=0;i<nFeeds;i++)
		{
			int [] feed;
			feed=temp.get(i);
			if(aliveFish.size()!=0)
			{
				//��X�̰��v�������A�N�}�Ƥ��t���L
				//�v�������G(100-������(%)+�m����/2) / (�Z��/10) = �v��
				//�v���j���|�u�����t���L
				Fish chooseFish=aliveFish.get(0);
				double maxWeights;	//�����̰��v���������v��
				maxWeights=(100-chooseFish.getSatiationRate()+chooseFish.getSnatch()/2) / 
						(Math.sqrt(
								((chooseFish.getNowPosition()[0]-feed[0])*(chooseFish.getNowPosition()[0]-feed[0]))
								+((chooseFish.getNowPosition()[1]-feed[1])*(chooseFish.getNowPosition()[1]-feed[1]))
								+((chooseFish.getNowPosition()[2]-feed[2])*(chooseFish.getNowPosition()[2]-feed[2])))
						/10);
				for(Fish fish:aliveFish)
				{
					double weights;
					weights=(100-fish.getSatiationRate()+fish.getSnatch()/2) / 
							(Math.sqrt(
									((fish.getNowPosition()[0]-feed[0])*(fish.getNowPosition()[0]-feed[0]))
									+((fish.getNowPosition()[1]-feed[1])*(fish.getNowPosition()[1]-feed[1]))
									+((fish.getNowPosition()[2]-feed[2])*(fish.getNowPosition()[2]-feed[2])))
							/10);
					if(maxWeights<weights)
					{
						maxWeights=weights;
						chooseFish=fish;
					}
				}
				
				//�N�}�ưt���ﭫ����
				chooseFish.setMyMove(FishMove.EATING);
				chooseFish.setGoalPosition(null);
				chooseFish.setFightTarget(null);
				ArrayList<int[]> fishFeedArray=chooseFish.getFeedArray();
				fishFeedArray.add(feed);
				chooseFish.setFeedArray(fishFeedArray);
				chooseFish.setSatiation(chooseFish.getSatiation()+device.feeder[device.getFeederSelector()].getGrain());	//����O��}�ƪ���
				alreadyFeed.add(feed);
				if(chooseFish.getSatiationRate()>=100)
				{
					aliveFish.remove(chooseFish);
					satietyFish.add(chooseFish);
				}
			}
			
			else if(satietyFish.size()!=0)
			{
				//��X�Z���̪񪺳��A�N�}�Ƥ��t���L
				//���Z���W�L5�����ɡA���|�Y�}��
				Fish chooseFish=satietyFish.get(0);
				double maxWeights;	//�Z���̪񪺳����Z��
				maxWeights=Math.sqrt(
								((chooseFish.getNowPosition()[0]-feed[0])*(chooseFish.getNowPosition()[0]-feed[0]))
								+((chooseFish.getNowPosition()[1]-feed[1])*(chooseFish.getNowPosition()[1]-feed[1]))
								+((chooseFish.getNowPosition()[2]-feed[2])*(chooseFish.getNowPosition()[2]-feed[2])));
				for(Fish fish:satietyFish)
				{
					double weights;
					weights=Math.sqrt(
									((fish.getNowPosition()[0]-feed[0])*(fish.getNowPosition()[0]-feed[0]))
									+((fish.getNowPosition()[1]-feed[1])*(fish.getNowPosition()[1]-feed[1]))
									+((fish.getNowPosition()[2]-feed[2])*(fish.getNowPosition()[2]-feed[2])));
					if(maxWeights>weights)
					{
						maxWeights=weights;
						chooseFish=fish;
					}
				}
				if(maxWeights<=5) {
					//�N�}�ưt���ﭫ����
					chooseFish.setMyMove(FishMove.EATING);
					chooseFish.setGoalPosition(null);
					chooseFish.setFightTarget(null);
					ArrayList<int[]> fishFeedArray=chooseFish.getFeedArray();
					fishFeedArray.add(feed);
					chooseFish.setFeedArray(fishFeedArray);
					chooseFish.setSatiation(chooseFish.getSatiation()+5);	//����O��}�ƪ���
					alreadyFeed.add(feed);
					if(chooseFish.getSatiationRate()>=200)
						satietyFish.remove(chooseFish);
				}
			}
		}
		for(int[] a:alreadyFeed)
			temp.remove(a);
		
		enviroment.setFeedXY(temp);
		
	}

	@Override
	protected void description(Fish[] fishs, Enviroment enviroment, Timerr timer, int nFishs,LandScape landSpace,DeviceCatalog device
			,int[] eventArray,String[] eventArrayDescription,int nEvent[]) {
	}
}
