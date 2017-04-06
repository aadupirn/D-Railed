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
	private int multiplier;
	private TrainController trainController;

	Timer timer;

	class DRailedTask extends TimerTask
	{
		public void run()
		{
			trainController.update();
		}
	}

	public DTime(TrainController iTrainController)
	{
		multiplier = 10;
		int intervalMS = 1000/multiplier;
		trainController = iTrainController;
		timer = new Timer();
		timer.schedule(new DRailedTask(), 0, intervalMS);
	}
}
