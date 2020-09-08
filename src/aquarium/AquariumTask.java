package aquarium;

import java.util.TimerTask;

public class AquariumTask extends TimerTask{
	private Aquarium aquarium;
	public AquariumTask(Aquarium temp)
	{
		aquarium=temp;
	}
	@Override
	public void run() {
		aquarium.run();
	}
}
