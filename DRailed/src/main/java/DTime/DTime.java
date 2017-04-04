package DTime;

import TrainController.TrainController;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by aadu on 4/3/17.
 */
public class DTime
{
	private int intervalMS = 1000;
	private TrainController trainController;

	Timer timer;

	class DRailedTask extends TimerTask
	{
		private int counter = 0;
		public void run()
		{
			counter++;
			trainController.Update();
			if(counter == 60)
			{
				timer.cancel();
			}
		}
	}

	public DTime(TrainController iTrainController)
	{
		intervalMS = 1000;
		trainController = iTrainController;
		timer = new Timer();
		timer.schedule(new DRailedTask(), intervalMS);
	}

	private void Step()
	{
		trainController.Update();
	}
}
