package event.enviromentEvent;

import java.util.ArrayList;

import deviceCatalog.DeviceCatalog;
import enviroment.Enviroment;
import event.EnviromentEvent;
import fish.Fish;
import fish.Fish.FishStatus;
import landScape.LandScape;
import timer.Timerr;
import java.security.SecureRandom;
public class Move extends EnviromentEvent {
	private static final SecureRandom randomNumbers = new SecureRandom();
	
	//移動一隻魚
	public void moveAFish(Fish fish, Enviroment enviroment,LandScape landSpace)
	{
		//先判斷如果現在位置在擺設中的話
		//取消打架
		//擺設的每一格*15等於實際座標
		
		if(fish.getFishStatus()!=FishStatus.DEATH)
		{
			int[] longerXY=new int[3];
			longerXY[0]=fish.getGoalPosition()[0]-fish.getNowPosition()[0];
			longerXY[1]=fish.getGoalPosition()[1]-fish.getNowPosition()[1];
			longerXY[2]=fish.getGoalPosition()[2]-fish.getNowPosition()[2];
			double longer;	//與目的地的距離
			longer=Math.sqrt((longerXY[0]*longerXY[0])
							+(longerXY[1]*longerXY[1])
							+(longerXY[2]*longerXY[2]));
			int[] nowXY=fish.getNowPosition();
			if(longer>=11)
			{
				nowXY[0]+=(int) ((longerXY[0]/longer)*10);
				nowXY[1]+=(int) ((longerXY[1]/longer)*10);
				nowXY[2]+=(int) ((longerXY[2]/longer)*10);
			}
			else if(fish.getGoalPosition()[0]==fish.getNowPosition()[0]
					&&fish.getGoalPosition()[1]==fish.getNowPosition()[1]
							&&fish.getGoalPosition()[2]==fish.getNowPosition()[2])
			{
				fish.setGoalPosition(null);
			}
			else
			{
				nowXY=fish.getGoalPosition();
				
				
			}
			fish.setNowPosition(nowXY);
		}
		else
		{
			int[] nowXY=fish.getNowPosition();
			if(enviroment.getFishTankXYZSize()[1]-nowXY[1]>=10)
				nowXY[1]+=10;
			else
				nowXY[1]=enviroment.getFishTankXYZSize()[1]+10;
			fish.setNowPosition(nowXY);
		}
		
	}
	
	
	//設定所有魚的目的地並移動牠
	@Override
	public void check(Fish[] fishs, Enviroment enviroment, Timerr timer, int nFishs,LandScape landSpace,DeviceCatalog device
			,int[] eventArray,String[] eventArrayDescription,int nEvent[]) {
		ArrayList<Fish> aliveFish=new ArrayList<Fish>();	
		for(int i=0;i<nFishs;i++)
		{
			if(fishs[i].getFishStatus()!=FishStatus.DEATH)
				aliveFish.add(fishs[i]);
		}
		for(Fish fish:aliveFish)
		{
			//將前一次的飼料清掉
			if(fish.getMyMove()==Fish.FishMove.EATING && fish.getGoalPosition()==null) {
				/*if(fish.getMyMove()==Fish.FishMove.EATING)
				{*/
					ArrayList<int[]> a=fish.getFeedArray();
					a.remove(0);
					fish.setFeedArray(a);
				//}
			}
			if(fish.getMyMove()==Fish.FishMove.EATING)
			{
				if(fish.getFeedArray().size()!=0) {
					fish.setGoalPosition(fish.getFeedArray().get(0));
				}
				else {
					fish.setMyMove(Fish.FishMove.NATURAL);
					fish.naturalMove(enviroment.getFishTankXYZSize());
				}
			}
			else if(fish.getMyMove()==Fish.FishMove.FIGHTING)
			{
				Fish fightTarget=fish.getFightTarget();
				if(fightTarget.getMyMove()==Fish.FishMove.EATING)
				{
					int[] goalXY=new int[3];
					goalXY[0]=(fightTarget.getNowPosition()[0]+fightTarget.getFeedArray().get(0)[0])/2;
					goalXY[1]=(fightTarget.getNowPosition()[1]+fightTarget.getFeedArray().get(0)[1])/2;
					goalXY[2]=(fightTarget.getNowPosition()[2]+fightTarget.getFeedArray().get(0)[2])/2;
					fish.setGoalPosition(goalXY);
					fish.setMyMove(Fish.FishMove.NATURAL);
				}
				else if(fightTarget.getMyMove()==Fish.FishMove.FIGHTING)
				{
					if(fish.getGoalPosition()==null)
					{
						int[] goalXY=new int[3];
						goalXY[0]=(fightTarget.getNowPosition()[0]+fish.getNowPosition()[0])/2;
						goalXY[1]=(fightTarget.getNowPosition()[1]+fish.getNowPosition()[1])/2;
						goalXY[2]=(fightTarget.getNowPosition()[2]+fish.getNowPosition()[2])/2;
						fish.setGoalPosition(goalXY);
						if(fightTarget.getNowPosition()[0]==fish.getNowPosition()[0] &&
								fightTarget.getNowPosition()[1]==fish.getNowPosition()[1] &&
								fightTarget.getNowPosition()[2]==fish.getNowPosition()[2])
						{
							int[] bound=enviroment.getFishTankXYZSize();
							int[] bounce=new int[3];
							int[] goalXY1=new int[3];
							goalXY1[0]=goalXY[0];
							goalXY1[1]=goalXY[1];
							goalXY1[2]=goalXY[2];
							
							
							bounce[0]=randomNumbers.nextInt(13)-6;
							bounce[1]=randomNumbers.nextInt(13)-6;
							bounce[2]=randomNumbers.nextInt(13)-6;
							goalXY[0]+=(bounce[0]+randomNumbers.nextInt(3)-1);
							goalXY[1]+=(bounce[1]+randomNumbers.nextInt(3)-1);
							goalXY[2]+=(bounce[2]+randomNumbers.nextInt(3)-1);
							//邊界判斷
							if(bound[0]<goalXY[0])	goalXY[0]=bound[0];
							if(0>goalXY[0])	goalXY[0]=0;
							if(bound[1]<goalXY[1])	goalXY[1]=bound[1];
							if(0>goalXY[1])	goalXY[1]=0;
							if(bound[2]<goalXY[2])	goalXY[2]=bound[2];
							if(0>goalXY[2])	goalXY[2]=0;
							
							fish.setGoalPosition(goalXY);
							
							
						
							goalXY1[0]-=(bounce[0]+randomNumbers.nextInt(3)-1);
							goalXY1[1]-=(bounce[1]+randomNumbers.nextInt(3)-1);
							goalXY1[2]-=(bounce[2]+randomNumbers.nextInt(3)-1);
							//邊界判斷
							if(bound[0]<goalXY1[0])	goalXY1[0]=bound[0];
							if(0>goalXY1[0])	goalXY1[0]=0;
							if(bound[1]<goalXY1[1])	goalXY1[1]=bound[1];
							if(0>goalXY1[1])	goalXY1[1]=0;
							if(bound[2]<goalXY1[2])	goalXY1[2]=bound[2];
							if(0>goalXY1[2])	goalXY1[2]=0;
							
							fightTarget.setGoalPosition(goalXY1);
						}
					}
				}
			}
			else if(fish.getGoalPosition()==null)
			{
				//呼叫正常移動訂出目標點
				fish.naturalMove(enviroment.getFishTankXYZSize());
			}
		}
		//移動魚
		for(int i=0;i<nFishs;i++)
			this.moveAFish(fishs[i], enviroment,landSpace);
	}

	@Override
	protected void description(Fish[] fishs, Enviroment enviroment, Timerr timer, int nFishs,LandScape landSpace,DeviceCatalog device
			,int[] eventArray,String[] eventArrayDescription,int nEvent[]) {
	}
}