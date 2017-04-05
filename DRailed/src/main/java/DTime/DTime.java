package DTime;

import TrainController.TrainController;
import javafx.concurrent.Task;

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
			trainController.update();
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
		timer.schedule(new DRailedTask(), 0, intervalMS);
	}
}
