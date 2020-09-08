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
		//抓取餵食器丟出的飼料量
		int nFeeds=1;	//暫時設定，之後會抓餵食器的值
		for(int i=0;i<nFeeds;i++)
		{
			int [] feed=new int[3];
			feed[0]=0+randomNumbers.nextInt(6);
			feed[1]=0+randomNumbers.nextInt(2);
			feed[2]=27+randomNumbers.nextInt(7);
			
			temp.add(feed);
		}
		*/
		ArrayList<Fish> aliveFish=new ArrayList<Fish>();		//存活且飽食度沒滿100%的魚
		ArrayList<Fish> satietyFish=new ArrayList<Fish>();		//存活且飽食度超過100%的魚
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
				//選出最高權重的魚，將飼料分配給他
				//權重公式：(100-飽食度(%)+搶食度/2) / (距離/10) = 權重
				//權重大的會優先分配給他
				Fish chooseFish=aliveFish.get(0);
				double maxWeights;	//紀錄最高權重的魚的權重
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
				
				//將飼料配給選重的魚
				chooseFish.setMyMove(FishMove.EATING);
				chooseFish.setGoalPosition(null);
				chooseFish.setFightTarget(null);
				ArrayList<int[]> fishFeedArray=chooseFish.getFeedArray();
				fishFeedArray.add(feed);
				chooseFish.setFeedArray(fishFeedArray);
				chooseFish.setSatiation(chooseFish.getSatiation()+device.feeder[device.getFeederSelector()].getGrain());	//之後是抓飼料的值
				alreadyFeed.add(feed);
				if(chooseFish.getSatiationRate()>=100)
				{
					aliveFish.remove(chooseFish);
					satietyFish.add(chooseFish);
				}
			}
			
			else if(satietyFish.size()!=0)
			{
				//選出距離最近的魚，將飼料分配給他
				//當魚距離超過5公分時，不會吃飼料
				Fish chooseFish=satietyFish.get(0);
				double maxWeights;	//距離最近的魚的距離
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
					//將飼料配給選重的魚
					chooseFish.setMyMove(FishMove.EATING);
					chooseFish.setGoalPosition(null);
					chooseFish.setFightTarget(null);
					ArrayList<int[]> fishFeedArray=chooseFish.getFeedArray();
					fishFeedArray.add(feed);
					chooseFish.setFeedArray(fishFeedArray);
					chooseFish.setSatiation(chooseFish.getSatiation()+5);	//之後是抓飼料的值
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
