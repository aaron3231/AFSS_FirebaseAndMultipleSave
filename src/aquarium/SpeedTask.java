package aquarium;

import java.util.Timer;
import java.util.TimerTask;

public class SpeedTask extends TimerTask{
	private Aquarium aquarium;
	private Timer timer;
	private int[] speedInformation;
	public SpeedTask(Aquarium temp,Timer timer,int[] speedInformation)
	{
		aquarium=temp;
		this.timer=timer;
		this.speedInformation=speedInformation;
	}
	@Override
	public void run() {
		aquarium.speedUP(speedInformation);
		AquariumTask task=new AquariumTask(aquarium);
		timer.schedule(task, 0,1000);
	}
}
